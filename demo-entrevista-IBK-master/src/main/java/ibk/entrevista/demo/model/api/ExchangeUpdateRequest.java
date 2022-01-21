package ibk.entrevista.demo.model.api;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExchangeUpdateRequest {
    private Double exchangeValue;
    private String currencyCodeOrigen;
    private String currencyCodeDestination;
    private Long id;
}
