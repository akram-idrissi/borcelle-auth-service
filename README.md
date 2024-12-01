# Borcelle Auth Service

This repository contains the **Auth Service** for the e-commerce platform, built using Spring Boot, Spring cloud and Spring OAuth2. It communicates with **Keycloak** to handle user authentication and authorization, providing access tokens for secure interaction with other microservices.

## Overview

The **Auth Service** is a microservice that interfaces with Keycloak to manage authentication and token generation. It acts as a centralized entry point for secure user access, handling:

- User login and token retrieval
- Token validation and refresh
- Secure communication with other microservices

---

## Features

- **User Authentication**: Validate user credentials and generate access tokens.
- **Token Refresh**: Issue new tokens when the current token expires.
- **Role-Based Access Control (RBAC)**: Integrates with Keycloak roles for fine-grained access management.
- **Secure Communication**: Uses OAuth2-compliant tokens for API security.

---

## Technologies

- **Spring Boot** - Framework for building Java-based microservices.
- **Keycloak** - Identity and Access Management solution for securing applications.
- **Spring Security** - Secures endpoints with token-based authentication.
- **Maven** - Build tool for managing dependencies and building the application.

---

## Setup

### Prerequisites

Before you begin, ensure that you have the following installed:

- Java 21
- Maven

### Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/akram-idrissi/borcelle-auth-service.git
   cd borcelle-auth-service
   ```
2. mvn clean install
3. mvn spring-boot:run
