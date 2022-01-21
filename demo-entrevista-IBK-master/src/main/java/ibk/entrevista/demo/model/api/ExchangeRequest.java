package ibk.entrevista.demo.model.api;

import lombok.*;

import java.math.BigDecimal;

@Data
@ToString
public class ExchangeRequest {

    private BigDecimal amount;
    private String currencyCodeOrigin;
    private String currencyCodeDestination;

}
