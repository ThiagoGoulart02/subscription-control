package ass_cache.project.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AssCacheApplication {
	public static void main(String[] args) {
		SpringApplication.run(AssCacheApplication.class, args);
	}
}