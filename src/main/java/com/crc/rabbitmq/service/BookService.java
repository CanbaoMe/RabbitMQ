package com.crc.rabbitmq.service;

import com.crc.rabbitmq.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues = "atguigu.news")
    public void receive(Book book){
        System.out.println("收到的消息："+book);
    }

    @RabbitListener(queues = "atguigu")
    public void receive(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
