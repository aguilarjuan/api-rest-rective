package com.capitole.consulting.zara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PricesControllerTests {

	@Autowired
	private WebTestClient webClient;


@Test
@DisplayName("get price on 2020-06-14-10.00.00")
void shouldGetPricesListOne() throws Exception{
	webClient.get().uri(uriBuilder -> uriBuilder
			.path("/api/v1/prices")
			.queryParam("brandId", 1)
			.queryParam("startDate", "2020-06-14-10.00.00")
			.queryParam("productId", 35455)
			.build())
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(APPLICATION_JSON_VALUE)
			.expectBody()
			.jsonPath("$.priceList").isEqualTo(1)
			.jsonPath("$.brandId").isEqualTo(1)
			.jsonPath("$.price").isEqualTo(35.5)
			.jsonPath("$.productId").isEqualTo(35455)
			.jsonPath("$.startDate").isEqualTo("2020-06-14-00.00.00")
			.jsonPath("$.endDate").isEqualTo("2020-12-31-23.59.59");

}

	@Test
	@DisplayName("get price on 2020-06-14-16.00.00")
	void shouldGetPricesListTwo() throws Exception{
		webClient.get().uri(uriBuilder -> uriBuilder
				.path("/api/v1/prices")
				.queryParam("brandId", 1)
				.queryParam("startDate", "2020-06-14-16.00.00")
				.queryParam("productId", 35455)
				.build())
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(APPLICATION_JSON_VALUE)
				.expectBody()
				.jsonPath("$.priceList").isEqualTo(2)
				.jsonPath("$.brandId").isEqualTo(1)
				.jsonPath("$.price").isEqualTo(25.45)
				.jsonPath("$.productId").isEqualTo(35455)
				.jsonPath("$.startDate").isEqualTo("2020-06-14-15.00.00")
				.jsonPath("$.endDate").isEqualTo("2020-06-14-18.30.00");

	}

	@Test
	@DisplayName("get price on 2020-06-14-21.00.00")
	void ShouldGetAgainPricesListOne() throws Exception{
		webClient.get().uri(uriBuilder -> uriBuilder
				.path("/api/v1/prices")
				.queryParam("brandId", 1)
				.queryParam("startDate", "2020-06-14-21.00.00")
				.queryParam("productId", 35455)
				.build())
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(APPLICATION_JSON_VALUE)
				.expectBody()
				.jsonPath("$.priceList").isEqualTo(1)
				.jsonPath("$.brandId").isEqualTo(1)
				.jsonPath("$.price").isEqualTo(35.5)
				.jsonPath("$.productId").isEqualTo(35455)
				.jsonPath("$.startDate").isEqualTo("2020-06-14-00.00.00")
				.jsonPath("$.endDate").isEqualTo("2020-12-31-23.59.59");

	}

	@Test
	@DisplayName("get price on 2020-06-15-10.00.00")
	void ShouldGetPricesListTree() throws Exception{
		webClient.get().uri(uriBuilder -> uriBuilder
				.path("/api/v1/prices")
				.queryParam("brandId", 1)
				.queryParam("startDate", "2020-06-15-10.00.00")
				.queryParam("productId", 35455)
				.build())
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(APPLICATION_JSON_VALUE)
				.expectBody()
				.jsonPath("$.priceList").isEqualTo(3)
				.jsonPath("$.brandId").isEqualTo(1)
				.jsonPath("$.price").isEqualTo(30.5)
				.jsonPath("$.productId").isEqualTo(35455)
				.jsonPath("$.startDate").isEqualTo("2020-06-15-00.00.00")
				.jsonPath("$.endDate").isEqualTo("2020-06-15-11.00.00");

	}


	@Test
	@DisplayName("get price on 2020-06-16-21.00.00")
	void ShouldGetPricesListFour() throws Exception{
		webClient.get().uri(uriBuilder -> uriBuilder
				.path("/api/v1/prices")
				.queryParam("brandId", 1)
				.queryParam("startDate", "2020-06-16-21.00.00")
				.queryParam("productId", 35455)
				.build())
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(APPLICATION_JSON_VALUE)
				.expectBody()
				.jsonPath("$.priceList").isEqualTo(4)
				.jsonPath("$.brandId").isEqualTo(1)
				.jsonPath("$.price").isEqualTo(38.95)
				.jsonPath("$.productId").isEqualTo(35455)
				.jsonPath("$.startDate").isEqualTo("2020-06-15-16.00.00")
				.jsonPath("$.endDate").isEqualTo("2020-12-31-23.59.59");

	}

}
