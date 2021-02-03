package de.smotastic.microservices.currencyexchange.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.smotastic.microservices.currencyexchange.infrastructure.entity.CurrencyExchangeEntity;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeEntity, Long> {
	CurrencyExchangeEntity findByFromAndTo(String from, String to);
}
