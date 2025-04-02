package shrutosom.bala.csv2api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import shrutosom.bala.csv2api.repository.Csv2APIRepository;

@SpringBootApplication
public class Csv2APIApplication implements CommandLineRunner{

	private final Csv2APIRepository csv2apiRepository;
	public Csv2APIApplication(Csv2APIRepository csv2apiRepository) {
		this.csv2apiRepository = csv2apiRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Csv2APIApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		csv2apiRepository.createAndUpdateTable();
	}

}
