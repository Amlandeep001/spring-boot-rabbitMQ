package com.org.springboot.rabbitmqconsumer.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message
{

	int id;
	String name;
}
