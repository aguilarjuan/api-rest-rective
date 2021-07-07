package com.capitole.consulting.zara.scheduled;

import com.capitole.consulting.zara.model.Prices;
import com.capitole.consulting.zara.repository.PricesRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Component
public class LoadPricesTask {

	@Autowired PricesRepository pricesRepository;

	@Scheduled(cron = "${cron.expression}")
	public void updatePrices() throws IOException, URISyntaxException
	{
		Flux.fromIterable(inputData())
				.limitRate(100)
				.doOnNext(row ->{
				 Prices pricesUpdate = toPrice(row);
				 Prices pricesCurrent = pricesRepository.findByPriceList(pricesUpdate.getPriceList());
					if (pricesCurrent != null)
					{
						updatePrice(pricesUpdate, pricesCurrent);
						log.info("update price list {}",pricesCurrent.getPriceList());
						System.out.println("se update: " + pricesUpdate.toString());

					}
					else
					{
						pricesRepository.save(pricesUpdate);
						log.info("save new price list with productId {}",pricesUpdate.getProductId());
					}

				})
				.onErrorStop()
				.subscribe();

	}

	private List<String[]> inputData() throws URISyntaxException, IOException
	{
		Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("update-prices.csv").toURI()));

		CSVParser parser = new CSVParserBuilder()
				.withSeparator(',')
				.withIgnoreQuotations(true)
				.build();

		CSVReader csvReader = new CSVReaderBuilder(reader)
				.withSkipLines(1)
				.withCSVParser(parser)
				.build();

		List<String[]> list;
		list = csvReader.readAll();
		reader.close();
		csvReader.close();
		return list;
	}

	private void updatePrice(Prices pricesUpdate, Prices pricesCurrent){
		mapperUpdate(pricesCurrent,pricesUpdate);
		pricesRepository.save(pricesCurrent);
	}


	private Prices toPrice(String[] row){
	return 	Prices.builder()
		.brandId(Integer.valueOf(row[0]))
		.startDate(row[1])
		.endDate(row[2])
		.priceList(Long.valueOf(row[3]))
		.productId(Long.valueOf(row[4]))
		.priority(Integer.valueOf(row[5]))
		.price(Double.valueOf(row[6]))
		.currency(row[7])
		.lastUpdate(row[8])
		.lastUpdateBy(row[9]).build();

	}

	private void mapperUpdate(Prices pricesCurrent, Prices pricesUpdate){
		pricesCurrent.setBrandId(pricesUpdate.getBrandId());
		pricesCurrent.setStartDate(pricesUpdate.getStartDate());
		pricesCurrent.setEndDate(pricesUpdate.getEndDate());
		pricesCurrent.setPriority(pricesUpdate.getPriority());
		pricesCurrent.setPrice(pricesUpdate.getPrice());
		pricesCurrent.setCurrency(pricesUpdate.getCurrency());
		pricesCurrent.setLastUpdate(pricesUpdate.getLastUpdate());
		pricesCurrent.setProductId(pricesUpdate.getProductId());
		pricesCurrent.setLastUpdateBy(pricesUpdate.getLastUpdateBy());
	}

}
