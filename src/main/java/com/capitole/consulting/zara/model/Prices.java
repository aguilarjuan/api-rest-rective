package com.capitole.consulting.zara.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "prices")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Prices implements Serializable {

	@Id
	@Column(name = "price_list", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prices_generator")
	@SequenceGenerator(name="prices_generator", sequenceName = "PRICES_SEQ",  allocationSize = 1)
	private Long priceList;

	@Column(name = "brand_id")
	private Integer brandId;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "priority")
	private Integer priority;

	@Column(name = "price")
	private Double price;

	@Column(name = "currency")
	private String currency;

	@Column(name = "last_update")
	private String lastUpdate;

	@Column(name = "last_update_by")
	private String lastUpdateBy;

}
