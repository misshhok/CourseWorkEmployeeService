package com.misshhok.coursework.infrastructure.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@Slf4j
public class MessageListener {
  @RabbitListener(queues = "notificationQueue")
  public void processQueue1(String message) {
    log.info("Received from notificationQueue: " + message);
  }
}
