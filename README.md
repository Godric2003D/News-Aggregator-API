# 📰 News Aggregator API

### A personalized news feed service built with Spring Boot and JWT authentication.

[![Java 17](https://img.shields.io/badge/Java-17-blue.svg?style=flat-square)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot 3.5.4](https://img.shields.io/badge/Spring_Boot-3.5.4-green.svg?style=flat-square)](https://spring.io/projects/spring-boot)
[![JWT](https://img.shields.io/badge/Authentication-JWT-orange?style=flat-square)](https://jwt.io/)
[![H2 Database](https://img.shields.io/badge/Database-H2-lightgrey?style=flat-square)](https://www.h2database.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](https://opensource.org/licenses/MIT)

---

## ✨ Features

- **🔐 Secure User Authentication:** Register and log in users with **JWT (JSON Web Tokens)** for secure, stateless access.
- **⚙️ Dynamic Preferences:** Users can set and update news preferences (e.g., technology, science, health) to tailor their feed.
- **🌐 Personalized News Feed:** Fetches and aggregates news articles from external APIs like **GNews** or **NewsAPI** based on user-defined preferences.
- **⚡ Lightweight Database:** Utilizes the **H2 in-memory database** for fast setup, testing, and development.

---

## 📦 Tech Stack

- **Backend:** Java 17, Spring Boot 3.5.4
- **Security:** Spring Security, JWT
- **Data:** Spring Data JPA, H2 Database
- **External Services:** GNews API / NewsAPI

---

## ⚡ Getting Started

Follow these steps to get a local copy of the project up and running.

### Prerequisites

Make sure you have the following installed on your machine:
- **JDK 17** or higher
- **Gradle** (or use the included Gradle Wrapper)

### 1. Clone the repository

```bash
git clone [https://github.com/Godric2003D/News-Aggregator-API.git](https://github.com/Godric2003D/News-Aggregator-API.git)
cd News-Aggregator-API
