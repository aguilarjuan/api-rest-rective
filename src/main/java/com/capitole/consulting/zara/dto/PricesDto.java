package com.capitole.consulting.zara.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class PricesDto {

	private Long priceList;

	private Integer brandId;

	private Long productId;

	private String startDate;

	private String endDate;

	private Double price;

}
