package com.example.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.example.pojo.Student;

public class Process implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("****** Process **********");

		Student out = exchange.getIn()
		                      .getBody(Student.class);

		System.out.println("****** " + out);

		// simple transformation of outgoing request
		final String name = "From Route " + out.getName();
		exchange.getIn()
		        .setBody(new Student(0L, name, "Camel SpringBoot"));
	}
}
