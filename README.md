# 🚀 AI-Powered Ticket Assignment System

## 📌 Overview

This project is an **AI-driven ticket routing system** that automatically assigns incoming support tickets to the most relevant developer based on the ticket content.

It integrates **semantic search (vector embeddings)** with a **microservices architecture**, enabling intelligent, scalable, and platform-agnostic ticket assignment.

---

## 🧠 Key Features

* 🔍 **Semantic Ticket Understanding**

  * Uses embeddings (Ollama) to understand ticket context instead of keyword matching

* ⚡ **Automatic Developer Assignment**

  * Matches tickets to the most relevant developer using vector similarity search

* 🔗 **Dynamic Webhook Integration**

  * Supports multiple ticketing platforms (Jira, Zendesk, etc.)
  * Configurable payload templates for different APIs

* 🧩 **Microservices Architecture**

  * Spring Boot (business logic)
  * Python FastAPI (vector service)
  * ChromaDB (vector database)

* 📦 **External Ticket ID Support**

  * Works with third-party ticketing systems

---

## 🏗️ Architecture

```
Ticketing Platform (Jira/Zendesk)
        │
        ▼
Webhook → Spring Boot (Ticket Service)
        │
        ▼
Embedding Service (Ollama)
        │
        ▼
Python Vector Service (FastAPI)
        │
        ▼
ChromaDB (Vector Search)
        │
        ▼
Developer Selected
        │
        ▼
Webhook Response → Ticket Assigned
```

---

## ⚙️ Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Data JPA
* RestTemplate

### AI / Vector

* Ollama (nomic-embed-text)
* ChromaDB
* Python (FastAPI)

### Database

* PostgreSQL

### DevOps (optional enhancements)

* Docker
* Kafka (for async processing)
* Prometheus + Grafana

---

## 📂 Project Structure

```
ticket-assignment-system
│
├── controller
├── service
├── repository
├── model
├── dto
├── client
├── config
└── util
```

---

## 🔄 Workflow

1. Ticket is created in external platform
2. Webhook sends ticket to system
3. System generates embedding using Ollama
4. Embedding is sent to Python vector service
5. ChromaDB returns closest matching module
6. Developer is selected from Postgres
7. Assignment webhook is triggered
8. Ticket is assigned in external platform

---

## 📡 APIs

### 1. Process Ticket

```
POST /tickets/process
```

#### Request

```json
{
  "ticketId": "JIRA-101",
  "title": "Payment failed",
  "description": "Customer unable to complete payment"
}
```

#### Response

```json
{
  "ticketId": "JIRA-101",
  "assignedDeveloper": "John",
  "developerEmail": "john@company.com",
  "module": "billing"
}
```

---

## 🔗 Webhook Configuration

Supports dynamic payloads stored in DB:

Example:

```json
{
  "platform": "jira",
  "webhook_url": "https://jira.api/assign",
  "payload_template": "{\"ticket_id\":\"{ticketId}\",\"user_id\":\"{userId}\"}"
}
```

---

## 🧪 How to Run

### 1. Start Ollama

```
ollama run nomic-embed-text
```

---

### 2. Start Python Vector Service

```
uvicorn vector_server:app --port 9000
```

---

### 3. Start Spring Boot

```
mvn spring-boot:run
```

---

### 4. Setup PostgreSQL

Create required tables:

* developers
* tickets
* webhook_config

---

## 📈 Future Improvements

* ✅ Hybrid AI (LLM + embeddings)
* ✅ Developer load balancing
* ✅ Kafka-based async processing
* ✅ Feedback loop for learning
* ✅ Multi-tenant support
* ✅ Dashboard UI

---

## 💡 Key Concepts Demonstrated

* Semantic Search
* Vector Databases
* Microservices Architecture
* AI-powered Backend Systems
* Webhook-based Integration
* System Design (LLD)

---

## 📊 Resume Highlights

* Built an **AI-powered ticket routing system** using embeddings and vector similarity search
* Designed a **microservice architecture integrating Spring Boot, FastAPI, and ChromaDB**
* Implemented **dynamic webhook engine for multi-platform integrations**
* Developed **semantic search-based assignment logic for intelligent automation**

---

## 👨‍💻 Author

Naveen Muthusamy

---

## ⭐ Final Note

This project demonstrates how **AI + backend engineering** can be combined to build real-world automation systems similar to those used in modern support platforms.

---
