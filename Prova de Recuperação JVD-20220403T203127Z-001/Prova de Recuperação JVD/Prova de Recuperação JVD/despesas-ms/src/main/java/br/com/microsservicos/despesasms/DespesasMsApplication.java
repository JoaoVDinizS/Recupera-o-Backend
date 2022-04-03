package br.com.microsservicos.despesasms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DespesasMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DespesasMsApplication.class, args);
	}

}
