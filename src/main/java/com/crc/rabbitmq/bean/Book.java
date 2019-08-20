package com.crc.rabbitmq.bean;

import lombok.Data;

@Data
public class Book {
    private String bookName;
    private String author;

    public Book(String bookName, String author) {
        this.bookName = bookName;
        this.author = author;
    }

    public Book() {
    }
}
