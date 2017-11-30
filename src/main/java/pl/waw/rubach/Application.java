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

	@Bean
	public CommandLineRunner demo(BidRepository bidRepo, BidSystemRepository bidSystemRepo) {
		return (args) -> {

			/*Bid bid1C = new Bid(0, 1, "C", 12, 37, "5+", "F", "", false, "Wieloznaczny trefl", "3 znaczenia", null);
			Bid bid1D = new Bid(0, 1, "D", 12, 18, "5", "S", "", false, "Słabe 1 karo", "Słabe 1 karo",null);
			Bid bid1DP = new Bid(1, 1, "P", 0, 6, "0+", "S", "", false, "Brak punktów","Brak punktów",  bid1D);
			Bid bid1C1D = new Bid(1, 1, "D", 0, 37, "0+", "S", "", false, "Negat na 1 trefl","Negat na 1 trefl", bid1C);
			Bid bid1C1H = new Bid(1, 1, "H", 7, 12, "0+", "S", "", false, "4ka kier", "4ka kier", bid1C);
			Bid bid1C1D1H = new Bid(2, 1, "H", 12, 18, "4+", "S", "", false, "Zgłoszenie 4ki kier", "Zgłoszenie 4ki kier", bid1C1D);
			Bid bid1C1D1S = new Bid(2, 1, "S", 12, 18, "4+", "S", "", false, "Zgłoszenie 4ki pik","Zgłoszenie 4ki pik", bid1C1D);
			Bid bid1C1D1H1NT = new Bid(3, 1, "NT", 12, 15, "4+", "S", "", false, "Słaba ręka, brak poparcia", "Słaba ręka, brak poparcia", bid1C1D1H);
			Bid bid1C1D1S1NT = new Bid(3, 1, "NT", 12, 15, "4+", "S", "", false, "Słaba ręka, brak poparcia", "Słaba ręka, brak poparcia", bid1C1D1S);
			Bid bid1C1H1NT = new Bid(2, 1, "NT", 12, 15, "0+", "S", "", false, "Słaba ręka brak 4ki kier","Słaba ręka brak 4ki kier", bid1C1H);
			Bid bid1C1H2H = new Bid(2, 2, "H", 12, 15, "0+", "S", "", false, "Słaba ręka 4ka kier","Słaba ręka 4ka kier", bid1C1H);
			bidRepo.save(bid1C);
			bidRepo.save(bid1D);
			bidRepo.save(bid1DP);
			bidRepo.save(bid1C1D);
			bidRepo.save(bid1C1H);
			bidRepo.save(bid1C1D1H);
			bidRepo.save(bid1C1D1S);
			bidRepo.save(bid1C1D1H1NT);
			bidRepo.save(bid1C1D1S1NT);
			bidRepo.save(bid1C1H1NT);
			bidRepo.save(bid1C1H2H);
*/
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

}
