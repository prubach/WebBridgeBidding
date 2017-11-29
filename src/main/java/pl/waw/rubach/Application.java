package pl.waw.rubach;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(BidRepository bidRepo) {
		return (args) -> {
			// save a couple of customers
			Bid bid1C = new Bid(0, 1, "C", 12, 37, 5, "F", "", false, "Wieloznaczny trefl", null);
			Bid bid1D = new Bid(0, 1, "D", 12, 18, 5, "S", "", false, "Słabe 1 karo", null);
			Bid bid1C1D = new Bid(1, 1, "D", 0, 37, 0, "S", "", false, "Negat na 1 trefl", bid1C);
			Bid bid1C1D1H = new Bid(2, 1, "H", 12, 18, 4, "S", "", false, "Zgłoszenie 4ki kier", bid1C1D);
			Bid bid1C1D1S = new Bid(2, 1, "S", 12, 18, 4, "S", "", false, "Zgłoszenie 4ki pik", bid1C1D);
			bidRepo.save(bid1C);
			bidRepo.save(bid1D);
			bidRepo.save(bid1C1D);
			bidRepo.save(bid1C1D1H);
			bidRepo.save(bid1C1D1S);
		};
	}

}
