package com.tcs.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tcs.in.Book_dao;

public class Test {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("beans.xml"); 
		
		ctx.getBean(Book_dao.class).get_book_data();

	}

}
