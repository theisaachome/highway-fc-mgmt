package com.highwayfc.teamservices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TeamServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeamServiceApplication.class, args);
    }
}
