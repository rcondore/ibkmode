package ibk.entrevista.demo.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@Table(name = "exchange")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID")
    private Long id;

    @Column(name="EXCHANGE_VAL")
    private Double exchangeVal;

    @Column(name="EXCHANGE_DATE")
    private Date exchangeDate;

    @Column(name="currency_code_origen")
    private String currencyCodeOrigen;

    @Column(name="currency_code_destination")
    private String currencyCodeDestination;
}
