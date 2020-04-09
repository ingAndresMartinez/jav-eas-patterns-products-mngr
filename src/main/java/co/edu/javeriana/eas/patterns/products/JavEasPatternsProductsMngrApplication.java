package co.edu.javeriana.eas.patterns.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.javeriana.eas", "co"})
public class JavEasPatternsProductsMngrApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavEasPatternsProductsMngrApplication.class);
    }

}
