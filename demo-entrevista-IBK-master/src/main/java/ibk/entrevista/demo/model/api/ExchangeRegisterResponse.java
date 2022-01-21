package ibk.entrevista.demo.model.api;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class ExchangeRegisterResponse {

    private Double exchangeValue;
    private String exchangeDate;
    private String exchangeHour;
    private String currencyCodeOrigen;
    private String currencyCodeDestination;
    private Long id;
}
