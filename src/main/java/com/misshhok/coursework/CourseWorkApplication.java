package com.misshhok.coursework;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class CourseWorkApplication {

  public static void main(String[] args) {
    SpringApplication.run(CourseWorkApplication.class, args);
  }
}
