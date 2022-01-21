package ibk.entrevista.demo.model.api;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class ExchangeResponse {

    private BigDecimal amountInput;
    private BigDecimal amountOutPut;
    private Currency currencyOrigin;
    private Currency currencyDestination;
    private Double exchange;
    private String operationType;
}
