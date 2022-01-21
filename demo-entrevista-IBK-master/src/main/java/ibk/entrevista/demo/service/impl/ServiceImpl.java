package ibk.entrevista.demo.service.impl;

import ibk.entrevista.demo.model.api.*;
import ibk.entrevista.demo.model.entity.Exchange;
import ibk.entrevista.demo.model.event.message.EventExchange;
import ibk.entrevista.demo.repository.ExchangeRepository;
import ibk.entrevista.demo.service.Service;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@org.springframework.stereotype.Service
@Slf4j
@AllArgsConstructor
public class ServiceImpl implements Service {
    private static final String PATTERN_DATE = "dd-MM-yyyy";
    private static final String PATTERN_HOUR = "HH:mm:ss";
    private ExchangeRepository exchangeRepository;

    @Override
    public Single<ExchangeRegisterResponse> registerExchange(ExchangeRegisterRequest request) {
        log.info("registerExchange::: inicio");
        return Single.fromCallable(() -> exchangeRepository.save(parseRequestToEntity(request)))
                .doOnError(throwable -> log.error("registerExchange",throwable))
                .onErrorResumeNext(throwable -> Single.error(() -> new Exception("EX0001 ::: Error en el registro")))
                .doOnSuccess(exchange -> log.info("registerExchange::: complete"))
                .map(exchange -> getBuildExchangeRegisterResponse(exchange));
    }

    @Override
    public Single<ExchangeResponse> operationExchange(ExchangeRequest request) {
       return Maybe.fromCallable(() -> exchangeRepository
                .findLastExchange(request.getCurrencyCodeOrigin(),request.getCurrencyCodeDestination())
        ).timeout(5l, TimeUnit.SECONDS)
               .switchIfEmpty(Single.error(() -> new Exception("E0009 ::: Tipo de cambio no encontrado")))

               .map(exchange -> validateAmount (exchange,request))
               .doOnError(throwable -> log.error("operationExchange",throwable))
                .doOnSuccess(exchange -> log.info("operationExchange::: complete")) ;
    }

    @Override
    public Observable<ExchangeRegisterResponse> listHistoricalExchange() {
        return Observable.fromCallable(() -> exchangeRepository.findAll()
        ).flatMapIterable(exchanges -> exchanges)
                .map(exchange -> getBuildExchangeRegisterResponse(exchange))
                .timeout(5l, TimeUnit.SECONDS)
                .doOnError(throwable -> log.error("operationExchange",throwable));
                //.onErrorResumeNext(throwable -> Single.error(() -> new Exception("EX0001 ::: Error en operationExchange")))
                //.doOnSuccess(exchange -> log.info("operationExchange::: complete"));
    }

    @Override
    public Single<ExchangeRegisterResponse> updateExchange(ExchangeUpdateRequest request) {
        return Single.fromCallable(() -> exchangeRepository.save(parseRequestToEntityUpdate(request)))
                .doOnError(throwable -> log.error("registerExchange",throwable))
                .onErrorResumeNext(throwable -> Single.error(() -> new Exception("EX0001 ::: Error en el registro")))
                .doOnSuccess(exchange -> log.info("registerExchange::: complete"))
                .map(exchange -> getBuildExchangeRegisterResponse(exchange));

    }

    private ExchangeRegisterResponse getBuildExchangeRegisterResponse(Exchange exchange) {
        return ExchangeRegisterResponse.builder()
                .id(exchange.getId())
                .exchangeDate(getTimeFormatted(exchange.getExchangeDate(), PATTERN_DATE))
                .exchangeHour(getTimeFormatted(exchange.getExchangeDate(), PATTERN_HOUR))
                .exchangeValue(exchange.getExchangeVal())
                .currencyCodeDestination(exchange.getCurrencyCodeDestination())
                .currencyCodeOrigen(exchange.getCurrencyCodeOrigen())
                .build();
    }

    private Exchange parseRequestToEntity(ExchangeRegisterRequest request) {
        Exchange exchange= new Exchange();
        exchange.setExchangeVal(request.getExchangeValue());
        exchange.setExchangeDate(new Date());
        exchange.setCurrencyCodeOrigen(request.getCurrencyCodeOrigen());
        exchange.setCurrencyCodeDestination(request.getCurrencyCodeDestination());
        return exchange;
    }

    private Exchange parseRequestToEntityUpdate(ExchangeUpdateRequest request) {
        Exchange exchange= new Exchange();
        exchange.setExchangeVal(request.getExchangeValue());
        exchange.setExchangeDate(new Date());
        exchange.setCurrencyCodeOrigen(request.getCurrencyCodeOrigen());
        exchange.setCurrencyCodeDestination(request.getCurrencyCodeDestination());
        exchange.setId(request.getId());
        return exchange;
    }

    private String getTimeFormatted( Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
         return simpleDateFormat.format(date);
    }

    private ExchangeResponse validateAmount(Exchange exchange, ExchangeRequest request){
        return parseEntityToResponse(exchange,request);
    }
    private ExchangeResponse parseEntityToResponse(Exchange exchange, ExchangeRequest request) {
      return  ExchangeResponse.builder()
                .exchange(exchange.getExchangeVal())
                .currencyOrigin(Currency.builder()
                        .code(exchange.getCurrencyCodeOrigen())
                        .build())
                .currencyDestination(Currency.builder()
                        .code(exchange.getCurrencyCodeDestination())
                        .build())
              .amountInput(request.getAmount())
              .amountOutPut(request.getAmount().multiply(BigDecimal.valueOf(exchange.getExchangeVal())))
              .build();
    }
}
