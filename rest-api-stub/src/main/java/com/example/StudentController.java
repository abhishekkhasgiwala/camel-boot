package com.example;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class StudentController {

	private final AtomicLong counter = new AtomicLong();
	private volatile HashMap map = new HashMap();

	@RequestMapping(method = RequestMethod.GET, value = "/student/{id}")
	public Student getRecord(@PathVariable(value = "id") long id) {
		Student student = (Student) map.get(id);
		return student;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/student")
	public Student insertRecord(@RequestBody Student inputRequest) {
		System.out.println("******** Recived POST request " + inputRequest.toString());
		long id = counter.incrementAndGet();
		Student student = new Student(id, inputRequest.getName(), inputRequest.getSubject());
		map.put(id, student);
		System.out.println("******** Response POST " + student.toString());
		return student;
	}
}
