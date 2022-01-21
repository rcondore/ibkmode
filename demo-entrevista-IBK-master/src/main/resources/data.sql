DROP TABLE IF EXISTS exchange;

CREATE TABLE exchange (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  exchange_val NUMERIC NOT NULL,
  exchange_date DATE NOT NULL,
  currency_code_origen VARCHAR(10) NOT NULL,
  currency_code_destination VARCHAR(10) NOT NULL
);

insert into exchange (id, currency_code_destination, currency_code_origen, exchange_date, exchange_val) values (null,'USD', 'PEN', {ts '2021-11-04 09:00:00.00'}, 4.0);
insert into exchange (id, currency_code_destination, currency_code_origen, exchange_date, exchange_val) values (null,'PEN', 'USD', {ts '2021-11-04 09:00:00.00'}, 4.1);
insert into exchange (id, currency_code_destination, currency_code_origen, exchange_date, exchange_val) values (null,'PEN', 'EUR', {ts '2021-11-04 09:00:00.00'}, 4.5);
insert into exchange (id, currency_code_destination, currency_code_origen, exchange_date, exchange_val) values (null,'EUR', 'PEN', {ts '2021-11-04 09:00:00.00'}, 4.4);
