package com.crc.rabbitmq;

import com.crc.rabbitmq.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void createExchange(){
		//创建exhcange
//		amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
//		System.out.println("amqpAdmin.exchange创建完成");

		//创建queue
//		amqpAdmin.declareQueue(new Queue("amqpAdmin.queue",true));
//		System.out.println("amqpAdmin.queue创建完成");

		//创建绑定规则
		amqpAdmin.declareBinding(new Binding("amqpAdmin.queue", Binding.DestinationType.QUEUE,"amqpAdmin.exchange","amqp.haha",null));


	}

	/**
	 * 1、单播（点对点）
	 */
	@Test
	public void contextLoads() {
		//Message需要自己构造
		//rabbitTemplate.send(exchange, routeKey, message);

		//object默认当成消息体，只需要传入要发送的对象，自动化序列发送给rabbitmq
		//rabbitTemplate.convertAndSend(exchange, routeKey, object);
		Map<String,Object> map = new HashMap<>();
		map.put("msg","这是第一个消息");
		map.put("data", Arrays.asList("helloworld",123,true));
		//对象被默认序列话之后发送
		rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("西游记","吴承恩"));
	}

	@Test
	public void receive(){

		Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
		System.out.println(o.getClass());
		System.out.println(o);
	}

	/**
	 * 广播方式：路由键不需要填写
	 */
	@Test
	public void sendMsg(){
		rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
	}
}
