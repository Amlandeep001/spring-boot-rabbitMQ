package com.org.springboot.rabbitmqconsumer.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.org.springboot.rabbitmqconsumer.configuration.RabbitMQConfig;
import com.org.springboot.rabbitmqconsumer.model.Message;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consumer
{

	@RabbitListener(queues = RabbitMQConfig.QUEUE_A)
	public void receive(Message message)
	{
		log.info("Message received from QUEUEA-> {}", message);
	}

	@RabbitListener(queues = RabbitMQConfig.QUEUE_B)
	public void receiveFromB(Message message)
	{
		log.info("Message received from QUEUEB-> {}", message);
	}

}
