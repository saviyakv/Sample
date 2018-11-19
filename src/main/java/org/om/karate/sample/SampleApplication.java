package org.om.karate.sample;

import org.om.karate.sample.persistence.Emp;
import org.om.karate.sample.persistence.EmpRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Bean
    ApplicationRunner init(EmpRepository empRepository) {
        return args -> Stream.of("emp1", "emp2", "emp3").forEach(nickName -> {
            Emp emp = new Emp(nickName);
            empRepository.save(emp);
        });
    }
}
