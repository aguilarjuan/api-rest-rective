package com.capitole.consulting.zara.controller;

import com.capitole.consulting.zara.dto.PricesDto;
import com.capitole.consulting.zara.repository.PricesRepository;
import com.capitole.consulting.zara.service.PricesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class PricesController {

	@Autowired
	private PricesService pricesService;

	@Autowired PricesRepository pricesRepository;

	@GetMapping(path = "/prices", produces = MediaType.APPLICATION_JSON_VALUE)
	public  Mono<ResponseEntity<PricesDto>>  pricesUnique(@RequestParam("brandId") int brandId, @RequestParam("startDate") String startDate, @RequestParam("productId") long productId ) throws Exception
	{    log.info("request get prices with brandId {}, startDate {}, productId {}",brandId,startDate,productId);
		 return pricesService.toDto(pricesService.findPrice(productId,brandId,startDate))
				         .map(p -> ResponseEntity.ok()
						 .body(p))
				         .defaultIfEmpty(ResponseEntity.notFound().build());
	}


}
