package pl.waw.rubach;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 *  This Bean is not necessary when the bidding system doesn't change as the application
	 *  uses a persistence storage in form of a database (bridge.db). You can disable it by commenting the
	 *  @Bean annotation below
	 *
	 * @param bidRepo
	 * @param bidSystemRepo
	 * @return
	 */
	@Bean
	public CommandLineRunner loadBidsFromXlsx(BidRepository bidRepo, BidSystemRepository bidSystemRepo) {
		return (args) -> {
			List<Bid> bids = XlsBridgeReader.readBridgeBidsFromXls();
			BidSystem curBidSystem = null;
			for (Bid b : bids) {
				if (curBidSystem==null || !curBidSystem.getName().equals(b.getBidSystem()))
					curBidSystem = bidSystemRepo.save(b.getBidSystem());
				bidRepo.save(b);
			}
			//XlsBridgeWriter.writeBridgeBidsToXlsx(bidRepo.findAll());
		};
	}

	/**
	 *  This Bean is not necessary when the bidding system doesn't change as the application
	 *  uses a persistence storage in form of a database (bridge.db). You can disable it by commenting the
	 *  @Bean annotation below
	 *
	 * @param bidRepo
	 * @param bidSystemRepo
	 * @return
	 */
	//@Bean
	public CommandLineRunner writeBidsToXlsx(BidRepository bidRepo, BidSystemRepository bidSystemRepo) {
		return (args) -> {
			XlsBridgeWriter.writeBridgeBidsToXlsx(bidSystemRepo.findAll(), bidRepo);
		};
	}


}
