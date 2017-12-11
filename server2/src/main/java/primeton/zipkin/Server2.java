package primeton.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class Server2 {

    public static void main(String[] args) {
        SpringApplication.run(Server2.class, args);
    }
}
