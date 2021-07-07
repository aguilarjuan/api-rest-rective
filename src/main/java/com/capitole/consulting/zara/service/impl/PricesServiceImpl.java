package com.capitole.consulting.zara.service.impl;

import com.capitole.consulting.zara.dto.PricesDto;
import com.capitole.consulting.zara.model.Prices;
import com.capitole.consulting.zara.repository.PricesRepository;
import com.capitole.consulting.zara.service.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PricesServiceImpl implements PricesService {

	@Autowired
	private PricesRepository pricesRepository;

	@Override
	public   Mono<List<Prices>> findPrice(long productId, int brandId,  String date){
				return Flux.fromIterable(pricesRepository.findByProductIdAndBrandId(productId,brandId))
				.limitRate(100)
				.filter(prices -> toLocalDate(date).isAfter(toLocalDate(prices.getStartDate())) && toLocalDate(date).isBefore(toLocalDate(prices.getEndDate())))
				.collectList().flatMap(s -> s.size() > 2 ? Mono.empty() : getPrices(getPriceWithPriority(s)));
	}

	@Override
	public Mono<PricesDto>toDto(Mono<List<Prices>> pricesMono){
		return pricesMono.flatMap(this::generateDto);
	}
	private Mono<PricesDto> generateDto(List<Prices> pricesList){
		Prices prices = pricesList.get(0);
		PricesDto pricesDto = PricesDto.builder()
				.priceList(prices.getPriceList())
				.brandId(prices.getBrandId())
				.productId(prices.getProductId())
				.startDate(prices.getStartDate())
				.endDate(prices.getEndDate())
				.price(prices.getPrice())
				.build();
		return Mono.just(pricesDto);
	}

	private LocalDateTime toLocalDate(String date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
		return LocalDateTime.parse(date, formatter);
	}

	private Flux<Prices> getPriceWithPriority(List<Prices> PricesList) {
		return PricesList.size() == 1 ? Flux.fromIterable(PricesList) : Flux.fromIterable(PricesList).filter(prices -> prices.getPriority() == 1);
	}

	private  Mono<List<Prices>> getPrices(Flux<Prices> pricesFlux){
		return pricesFlux.collectList();
	}

}
