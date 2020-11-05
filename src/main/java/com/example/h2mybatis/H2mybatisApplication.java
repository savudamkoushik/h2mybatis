package com.example.h2mybatis;

import com.example.h2mybatis.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class H2mybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2mybatisApplication.class, args);
		}
//	@Bean
//	CommandLineRunner runner(StudentRepo studRepo){
//		return args->{
//			studRepo.save(Student.builder().name("koushik").branch("cse").email("sa@gamil.com").build());
//		};
//	}

}
