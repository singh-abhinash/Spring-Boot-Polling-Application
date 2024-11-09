# Spring Boot + Hibernate + Spring Security - Polling Application

[![Java Version](https://img.shields.io/badge/Java-23-green.svg)](https://docs.oracle.com/en/java/javase/11/)
[![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-3.3.4-green.svg)](https://spring.io/projects/spring-boot)
[![Spring Security Version](https://img.shields.io/badge/Spring%20Security-green.svg)](https://spring.io/projects/spring-security)
[![JWT Version](https://img.shields.io/badge/JWT-0.11.5-green.svg)](https://github.com/jwtk/jjwt)
[![MySQL Version](https://img.shields.io/badge/MySQL-8.0.33-blue.svg)](https://www.mysql.com/)

This repository demonstrate voting system for choosing a lunch spot, allowing one vote per person daily. Users select a restaurant based on the daily menu. Votes can be changed until 11:00 AM, after which changes are locked. Admin update each restaurant's menu daily with 2-5 items, including dish names and prices.

This Java project offers a `RESTful API` with basic authentication for Admin and Users. 


> **Voting system for deciding where to have lunch.** 
Only one vote per day per person, a person can change his vote until 11 a.m.
User chooses restaurant based upon today's dish.

 **Here you can find task and project requirements <a href="https://github.com/singh-abhinash/Spring-Boot-Polling-Application/bash.md">bash.md</a>**

 **Technology stack:**
 - Spring Boot
 - Spring Security
 - REST
 - Spring Data JPA
 - Hibernate
 - Maven
 - JUnit

## Features

> **This project is a voting system for deciding where to have lunch** 
Designed for two types of users: Admins and Users.

**Admin Features:**
- Admins can input daily restaurant options with a menu (2-5 items, including dish names and prices).
- Menus are updated daily for each restaurant.

**User Features:**
- Users vote on the restaurant they'd prefer for lunch each day.
- Only one vote per user is counted. Users can change their vote until 11:00 AM each day, after which votes are locked.	

## Prerequisites

- Java 
- MySQL
- MySQL-WorkBench

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/singh-abhinash/Spring-Boot-Polling-Application.git
   ```

2. Navigate to the project directory:

   ```bash
   cd springBootPollingApplication.git
   ```

3. Configure MySQL database properties in `src/main/resources/application.properties`.

4. Build and run the application:

   ```bash
   mvn spring-boot:run
   ```

5. Access the application at [http://localhost:8080](http://localhost:8080).

6. Use Postman to test this API, you can add it from Google Chrome Web Store or use another REST Client Tool 
on your own. See also examples of bash commands <a href="https://github.com/singh-abhinash/Spring-Boot-Polling-Application/bash.md">bash.md</a>

## Contributing

Contributions are welcome! Feel free to open issues and pull requests.

****HAPPY CODING!! - ABHINASH SINGH****

