package com.example.util;

import java.util.UUID;

import org.apache.camel.Body;
import org.apache.camel.Exchange;

import com.example.pojo.ProblemDetail;
import com.example.pojo.Student;

public class DataHelper {

	public void doRequestMapping(@Body Student student, Exchange exchange) throws Exception {
		// validate incoming request
		if (null != student.getName() && student.getName().length() == 0) {
			exchange.setProperty("BAD_REQUEST", "Name cannot be blank");
			throw new Exception("Bad Request");
		}

		// Student stu = (Student) exchange.getIn().getBody();
		System.out.println("******* stu" + student.toString());

	}

	public void doErrorResponse(Exchange exchange) {
		ProblemDetail error = new ProblemDetail();
		error.setId(UUID.randomUUID().toString());
		String str = exchange.getProperty("BAD_REQUEST", String.class);
		if (null != str) {
			error.setMessage(str);
			error.setTitle("BAD_REQUEST");
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "400");

		} else {
			error.setMessage("Endpoint not available");
			error.setTitle("Internal Server Error");
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "500");
		}
		exchange.getIn().setBody(error);
	}
}
