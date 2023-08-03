package com.org.springboot.rabbitmq.controller;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.springboot.rabbitmq.configuration.RabbitMQConfig;
import com.org.springboot.rabbitmq.model.Message;

@RestController
public class Producer
{

	private final RabbitTemplate rabbitTemplate;
	private final DirectExchange exchange;

	public Producer(RabbitTemplate rabbitTemplate, DirectExchange exchange)
	{
		this.rabbitTemplate = rabbitTemplate;
		this.exchange = exchange;
	}

	@PostMapping("/post")
	public String send(@RequestBody Message message)
	{
		rabbitTemplate.convertAndSend(exchange.getName(), RabbitMQConfig.ROUTING_A, message);
		return "Message sent successfully";
	}

}
