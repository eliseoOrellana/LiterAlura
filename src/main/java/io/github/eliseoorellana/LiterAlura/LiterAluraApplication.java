package io.github.eliseoorellana.LiterAlura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
		ConsoleMenu consoleMenu = new ConsoleMenu();
        consoleMenu.showMenu();
	}

	


	
}
