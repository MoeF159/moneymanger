# 💰 Money Manager API

A secure, production-ready RESTful API for managing personal finances.
Users can track income, expenses, and account balances while receiving automated email notifications and summaries.

---

## 🚀 Features

* 🔐 **Authentication & Security**

  * JWT-based authentication and authorization
  * Secure password handling
  * CORS configuration for safe client-server communication

* 📊 **Financial Management**

  * Track income and expenses
  * Categorize transactions
  * Maintain real-time account balances

* 📧 **Email Automation**

  * Account verification via activation tokens
  * Daily reminders to log financial activity
  * Automated daily expense summaries per user

* ⚙️ **Robust Backend Design**

  * RESTful API architecture
  * Input validation and exception handling
  * Data integrity enforcement

* ☁️ **Deployment & DevOps**

  * Docker containerization
  * Deployed on Render
  * Environment-based configuration

---

## 🛠 Tech Stack

* **Backend:** Java, Spring Boot
* **Database:** PostgreSQL
* **Authentication:** JWT
* **Email Services:** Email API
* **DevOps:** Docker, Render

---

## 🧠 Architecture Overview

* Layered architecture:

  * Controller → Service → Repository
* Stateless authentication using JWT
* Event-based email triggers for user-specific notifications
* Persistent storage with optimized relational schema

---

## 📌 API Endpoints (Sample)

### Auth

* `POST /api/auth/register` → Register user
* `POST /api/auth/login` → Authenticate user

### Transactions

* `GET /api/transactions` → Get all transactions
* `POST /api/transactions` → Create transaction
* `PUT /api/transactions/{id}` → Update transaction
* `DELETE /api/transactions/{id}` → Delete transaction

### User

* `GET /api/users/profile` → Get user profile

---

## 📬 Email Workflows

* **Account Verification:**
  Users receive an activation link with a token to verify their account.

* **Daily Reminders:**
  Automated emails prompt users to log their financial activity.

* **Daily Summaries:**
  Aggregated expense reports sent to each user.

---

## ⚡ Getting Started

### Prerequisites

* Java 21+
* Docker (optional)
* PostgreSQL

### Run Locally

```bash
git clone https://github.com/yourusername/money-manager-api.git
cd money-manager-api
./mvnw spring-boot:run
```

---

## 🐳 Run with Docker

```bash
docker build -t money-manager-api .
docker run -p 8080:8080 money-manager-api
```

---

## 🌐 Deployment

Deployed on **Render** for cloud accessibility and scalability.

---

## 📈 Future Improvements

* Budget tracking and alerts
* Multi-currency support
* Financial analytics dashboard
* Rate limiting & monitoring

---

## 👤 Author

**Osama Farag**
Software Developer | Backend & Full-Stack
