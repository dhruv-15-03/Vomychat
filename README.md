# Linktree Backend

A secure, efficient, and scalable backend for a platform similar to [Linktr.ee](https://linktr.ee) or [Bento.me](https://bento.me). This backend is built with **Spring Boot** and provides functionalities such as user registration, login (with JWT authentication), password recovery, referral tracking, and link management.

## Features

- **User Authentication**
    - User registration and login using JWT.
    - Secure password storage with BCrypt hashing.
    - Password recovery via email (with token generation).

- **Referral System**
    - Generate and track referral codes.
    - Fetch a list of users referred by the logged-in user.
    - Retrieve referral statistics (e.g., number of successful sign-ups).

- **Link Management**
    - Create and retrieve user-specific links.

- **Security & Scalability**
    - Stateless session management.
    - Role-based access control.

## Requirements

- Java 17 or higher
- Maven
- MySQL (or another compatible relational database)
- [Optional] An email service for password recovery

## Setup Instructions

1. **Clone the Repository**

   ```bash
   git clone https://your-repo-url.git
   cd your-repo-folder

