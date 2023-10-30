package project.carparkmanagement.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CarparkServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarparkServiceApplication.class, args);
    }


}
