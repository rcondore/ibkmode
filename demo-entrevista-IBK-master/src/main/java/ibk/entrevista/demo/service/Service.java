package ibk.entrevista.demo.service;

import ibk.entrevista.demo.model.api.*;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface Service {
    Single<ExchangeRegisterResponse> registerExchange(ExchangeRegisterRequest request);
    Single<ExchangeResponse> operationExchange(ExchangeRequest request);
    Observable<ExchangeRegisterResponse> listHistoricalExchange();
    Single<ExchangeRegisterResponse> updateExchange(ExchangeUpdateRequest request);

}
