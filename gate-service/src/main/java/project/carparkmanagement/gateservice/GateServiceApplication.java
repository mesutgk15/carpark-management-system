package project.carparkmanagement.gateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "project.carparkmanagement.client.carpark")
public class GateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateServiceApplication.class, args);
    }

}
