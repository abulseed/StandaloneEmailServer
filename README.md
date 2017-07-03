1- Technical track: 
================
Back-end.


2- Technical stack:
==================
- Java: 9 years experience using it for production-level applications on professional level.
- Spring-boot: 2 years using it for personal projects.
- Heroku: first time to use.
- Maven: 6 years experience using it for production-level applications on professional level.


3- Architecture: 
================

Implemented the service using REST API to provide a clean separation of concerns and a loosley coupled approach, and also to be able to serve different types of clients when needed.


4- Clarity: 
===========

**Problem:** provide a service for receiving email requests and process them. 

**Solution:** Implement the service using the REST implementation of spring-boot.


Technical tradeoffs:
--------------------
Pros: 
- Service Availability: The service is deployed on the cloud and would have a near perfect up time.
Cons:
- Request processing time: the processing would depend on the Email service provider interface and how long it would take to accept and acknowledge emails.

	
5- Correctness: 
===============

The service should behave as expected the only problem for now is SendGrid implementation because my test account was de-authorized for some reason and I would need more time to fix it.

6- Security: 
============

Spring-boot recommends using Cross-Site Request Forgery detection which I had to disable for now. But I think we can implement it using a random access token generation.

Note: I chose to work with back-end technical track which is my speciality and also because of the time limit. But I can work also with front-end however I might require minimum support.
