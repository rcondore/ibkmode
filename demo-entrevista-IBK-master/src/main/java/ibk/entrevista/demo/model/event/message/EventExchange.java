package ibk.entrevista.demo.model.event.message;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class EventExchange {
    private BigDecimal amount;
    private Double exchangeValue;
    private String exchangeCurrencyOrigin;
    private String exchangeCurrencyDestination;
    private String date;
    private String hour;

}
