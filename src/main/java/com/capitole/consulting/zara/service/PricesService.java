package com.capitole.consulting.zara.service;

import com.capitole.consulting.zara.dto.PricesDto;
import com.capitole.consulting.zara.model.Prices;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PricesService {

	Mono<List<Prices>> findPrice(long productId, int brandId,  String date);
	Mono<PricesDto> toDto(Mono<List<Prices>> pricesMono);

}
