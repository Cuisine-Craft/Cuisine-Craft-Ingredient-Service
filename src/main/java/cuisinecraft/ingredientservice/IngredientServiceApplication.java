package cuisinecraft.ingredientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Enable Feign for REST client
public class IngredientServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(IngredientServiceApplication.class, args);
	}
}

