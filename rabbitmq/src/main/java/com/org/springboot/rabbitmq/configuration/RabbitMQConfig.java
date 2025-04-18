package com.org.springboot.rabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig
{
	public static final String QUEUE_A = "queue.A";
	public static final String QUEUE_B = "queue.B";

	public static final String DIRECT_EXCHANGE = "exchange.direct";
	public static final String FANOUT_EXCHANGE = "exchange.fanout";

	public static final String ROUTING_A = "routing.A";
	public static final String ROUTING_B = "routing.B";

	@Bean
	Queue queueA()
	{
		return new Queue(QUEUE_A, false);
	}

	@Bean
	Queue queueB()
	{
		return new Queue(QUEUE_B, false);
	}

	/*@Bean
	DirectExchange exchange()
	{
		return new DirectExchange(DIRECT_EXCHANGE);
	}
	
	@Bean
	Binding binding(Queue queueA, DirectExchange exchange)
	{
		return BindingBuilder.bind(queueA)
				.to(exchange)
				.with(ROUTING_A);
	}
	
	@Bean
	Binding bindingB(Queue queueB, DirectExchange exchange)
	{
		return BindingBuilder.bind(queueB)
				.to(exchange)
				.with(ROUTING_B);
	}*/

	@Bean
	FanoutExchange exchange()
	{
		return new FanoutExchange(FANOUT_EXCHANGE);
	}

	@Bean
	Binding binding(Queue queueA, FanoutExchange exchange)
	{
		return BindingBuilder.bind(queueA)
				.to(exchange);
	}

	@Bean
	Binding bindingB(Queue queueB, FanoutExchange exchange)
	{
		return BindingBuilder.bind(queueB)
				.to(exchange);
	}

	@Bean
	MessageConverter messageConverter()
	{
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	RabbitTemplate rabbitTeamplate(ConnectionFactory factory, MessageConverter messageConverter)
	{
		RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
		rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	}
}
