package joaovitorlopes.com.github.searchcarbyfipe;

import joaovitorlopes.com.github.searchcarbyfipe.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchCarByFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {SpringApplication.run(SearchCarByFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.showMenu();
	}
}
