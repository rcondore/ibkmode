package ibk.entrevista.demo.model.api;

import lombok.*;

import java.util.Date;

@Data
@ToString
public class ExchangeRegisterRequest {

    private Double exchangeValue;
    private String currencyCodeOrigen;
    private String currencyCodeDestination;
}
