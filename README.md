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


5- Technical tradeoffs:
=======================

Pros: 
- Service Availability: The service is deployed on the cloud and would have a near perfect up time.
Cons:
- Request processing time: the processing would depend on the Email service provider interface and how long it would take to accept and acknowledge emails.

	
6- Correctness: 
===============

The service should behave as expected the only problem for now is SendGrid implementation because my test account was de-authorized for some reason and I would need more time to fix it.


7- Security: 
============

Spring-boot recommends using Cross-Site Request Forgery detection which I had to disable for now. But I think we can implement it using a random access token generation.


8- Improvement:
===============

Store Email API keys in system environment variables (currently it is set in system properties and i had to push them to github). That choice was made to cut the time needed for debugging how heroku store system environment variables.


9- Notes:
=========

- I chose to work with back-end technical track which is my field of speciality and also because of the time limit. But I can as well work with front-end however I might require minimum support.
- SoapUI project "email-server-heroku.xml" is included for testing the service API.
- Service public URL is "https://evening-ocean-75917.herokuapp.com/SendEmail".