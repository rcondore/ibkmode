package ibk.entrevista.demo.repository;

import ibk.entrevista.demo.model.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ExchangeRepository extends JpaRepository<Exchange,Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM EXCHANGE where CURRENCY_CODE_ORIGEN =:currencyCodeOrigen AND"
            + " CURRENCY_CODE_DESTINATION =:currencyCodeDestination  ORDER BY ID DESC LIMIT 1", nativeQuery = true)
    Exchange findLastExchange(
        @Param("currencyCodeOrigen") String currencyCodeOrigen,
        @Param("currencyCodeDestination") String currencyCodeDestination
    );
}
