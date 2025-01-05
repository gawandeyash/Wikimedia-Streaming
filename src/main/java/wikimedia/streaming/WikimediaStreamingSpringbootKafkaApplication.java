package wikimedia.streaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WikimediaStreamingSpringbootKafkaApplication {

	public static void main(String[] args) {
		System.out.println("Application started");
		SpringApplication.run(WikimediaStreamingSpringbootKafkaApplication.class, args);
	}

}
