package com.example.route;

import javax.ws.rs.core.MediaType;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.example.pojo.ProblemDetail;
import com.example.pojo.Student;
import com.example.util.DataHelper;

@Component
public class Route extends RouteBuilder {

	JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Student.class);
	JacksonDataFormat jsonErrorFormat = new JacksonDataFormat(ProblemDetail.class);

	DataHelper mapper = new DataHelper();

	@Override
	public void configure() {

		restConfiguration().component("servlet")
		                   .bindingMode(RestBindingMode.json);

		rest("/student").produces("application/json")
		                .get("/hello/{name}")
		                .route()
		                .transform()
		                .simple("Hello World ${header.name}")
		                .endRest()

		                .post("/records")
		                .consumes(MediaType.APPLICATION_JSON)
		                .produces(MediaType.APPLICATION_JSON)
		                .type(Student.class)
		                .to("direct:records");

		from("direct:records")// .marshal().json(JsonLibrary.Jackson)
		                      .doTry()
		                      .bean(mapper, "doRequestMapping")

		                      .process(new Process())
		                      .setHeader(Exchange.HTTP_METHOD, simple("POST"))
		                      .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		                      .setHeader("Accept", constant("application/json"))
		                      .removeHeader(Exchange.HTTP_PATH)
		                      .marshal(jsonDataFormat)
		                      .log("Outgoing Http started")
		                      .log("Request : ${body}")

		                      .to("http://localhost:9090/mock/student?bridgeEndpoint=true&amp;throwExceptionOnFailure=true")
		                      .doCatch(Exception.class)
		                      .bean(mapper, "doErrorResponse")
		                      .marshal(jsonErrorFormat)
		                      .endDoTry()
		                      .unmarshal()
		                      .json(JsonLibrary.Jackson);

	}
}
