package de.smotastic.microservices.currencyconversion.application;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.smotastic.microservices.currencyconversion.domain.model.CurrencyConversion;
import de.smotastic.microservices.currencyconversion.domain.model.MultiplyCurrencyExchangeUseCaseCommand;
import de.smotastic.microservices.currencyconversion.domain.usecase.MultiplyCurrencyExchangeUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {

	private final MultiplyCurrencyExchangeUseCase multiplyCurrencyExchangeUseCase;

//	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
//	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
//			@PathVariable BigDecimal quantity) {
//
//		HashMap<String, String> uriVariables = new HashMap<>();
//		uriVariables.put("from", from);
//		uriVariables.put("to", to);
//
//		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
//				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
//
//		CurrencyConversion currencyConversion = responseEntity.getBody();
//
//		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
//				currencyConversion.getConversionMultiple(),
//				quantity.multiply(currencyConversion.getConversionMultiple()),
//				currencyConversion.getEnvironment());
//
//	}

	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		return multiplyCurrencyExchangeUseCase.execute(MultiplyCurrencyExchangeUseCaseCommand.builder() //
				.from(from) //
				.to(to) //
				.quantity(quantity).build());
	}

}
