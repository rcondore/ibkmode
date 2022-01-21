package ibk.entrevista.demo.expose.web;

import ibk.entrevista.demo.model.api.*;
import ibk.entrevista.demo.service.Service;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
public class Controller {

    private Service service;

    @PostMapping(
            value = "/operation-exchange",
            produces ={
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_NDJSON_VALUE
            })
    public Single<ExchangeResponse> operationExchange(@RequestBody ExchangeRequest request){
        return service.operationExchange(request);
    }

    @GetMapping(
            value = "/list-exchange",
            produces ={
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_NDJSON_VALUE
            })
    public Observable<ExchangeRegisterResponse> getHistoricalExchange(){
        return service.listHistoricalExchange();
    }

    @PutMapping(
            value = "/put-exchange",
            produces ={
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_NDJSON_VALUE
            })
    public Single<ExchangeRegisterResponse> putExchange(@RequestBody ExchangeRegisterRequest request){
        return service.registerExchange(request);
    }

    @PostMapping(
            value = "/update-exchange",
            produces ={
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_NDJSON_VALUE
            })
    public Single<ExchangeRegisterResponse> updateExchange(@RequestBody ExchangeUpdateRequest request){
        return service.updateExchange(request);
    }
}
