package pl.waw.rubach;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.waw.rubach.model.Bid;
import pl.waw.rubach.model.BidSystem;
import pl.waw.rubach.repo.BidRepository;
import pl.waw.rubach.repo.BidSystemRepository;
import pl.waw.rubach.xls.CheckHsBids;
import pl.waw.rubach.xls.XlsBridgeReader;
import pl.waw.rubach.xls.XlsBridgeWriter;

import java.util.List;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Value("${webbridge.loadBidsFromXlsx}")
	private Boolean readBids = false;

	@Value("${webbridge.writeBidsToXlsx}")
	private Boolean writeBids = false;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 *  Importing data from the XLSX file to the database can be enabled/disabled by using the
	 *  webbridge.loadBidsFromXlsx property
	 *
	 * @param bidRepo
	 * @param bidSystemRepo
	 * @return
	 */
	@Bean
	public CommandLineRunner loadBidsFromXlsx(BidRepository bidRepo, BidSystemRepository bidSystemRepo) {
		return (args) -> {
			if (readBids) {
				List<Bid> bids = XlsBridgeReader.readBridgeBidsFromXls();
				BidSystem curBidSystem = null;
				for (Bid b : bids) {
					if (curBidSystem == null || !curBidSystem.getName().equals(b.getBidSystem().getName()))
						curBidSystem = bidSystemRepo.save(b.getBidSystem());
					bidRepo.save(b);
				}
			}
            log.info("Checking for HS records");
			new CheckHsBids(bidRepo, bidSystemRepo).checkHS();
            log.info("Data import finished!");
		};
	}


	/**
	 *  Exporting data from the database to an XLSX file can be enabled/disabled by using the
	 *  webbridge.writeBidsToXlsx property
	 *
	 * @param bidRepo
	 * @param bidSystemRepo
	 * @return
	 */
	@Bean
	public CommandLineRunner writeBidsToXlsx(BidRepository bidRepo, BidSystemRepository bidSystemRepo) {
		return (args) -> {
			if (writeBids) XlsBridgeWriter.writeBridgeBidsToXlsx(bidSystemRepo.findAll(), bidRepo);
		};
	}


}
