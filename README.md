 # Camel-Boot project
 	## Developed using Spring boot and camel, it contains two endpoints
 	
 	## GET : http://localhost:8080/api/student/hello/{name}
 	    ### it will return simple Hello + name message	
 	    ### Just to verify api it up and running
 	
 	POST : http://localhost:8080/api/student
 		It will submit student data by calling outgoing http call to stub and return response.
 		Endpoint orchestration is implemented using Apache Camel
 	
 	 Request
 	    {
			"name":"test",
			"subject":"testSubject"	
		}
	 Response
	 	{
			"id": 1	 	    
			"name":"test",
			"subject":"testSubject"	
		}	     
	  	      
 	
 rest-api-stub
 	Simple spring boot project, exposed two methods
 	
 	GET  : http://localhost:9090/mock/student
 	
 	POST : http://localhost:9090/mock/student
       Request:
           	    {
					"name":"test",
					"subject":"testSubject"	
				}
	   Response
	   			{
			   		"id": 1	 	    
					"name":"test",
					"subject":"testSubject"	
				}	     
 	     
Docker Image
	docker image build -t camel-boot-api-image . 	  	
 	 