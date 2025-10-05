<a id="readme-top"></a>
<br /> 
<div align="center">
   <a href="https://github.com/lopesthomas/MediLabo_Solutions">
      <img src="https://user.oc-static.com/upload/2023/08/09/16915753889047_fr_DA-JAVA_P7-P8-P9_P9-02%20%281%29%20Large.jpeg" alt="Logo" ">
   </a> 
   <p align="center"> 
      <h2>🧑‍⚕️ MediLabo - Microservices Project</h2>
      <h3>📌 Overview</h3>
      <div align="justify">
         <p>
         <strong>MediLabo</strong> is a microservices-based application designed for managing patient medical records, notes, and diabetes risk reports  
         <br><br>
         🧩 With this project, you can:<br><br>
         &nbsp;&nbsp;&nbsp;✅ Manage patients (CRUD operations)<br>
         &nbsp;&nbsp;&nbsp;✅ Store and access medical notes<br>
         &nbsp;&nbsp;&nbsp;✅ Generate diabetes risk reports<br>
         &nbsp;&nbsp;&nbsp;✅ Secure access via <strong>Keycloak</strong> (OAuth2)<br>
         &nbsp;&nbsp;&nbsp;✅ Use a modern microservices architecture with <strong>Spring Boot</strong>, <strong>Docker Compose</strong> & <strong>MySQL</strong><br>
         </p>
      </div>
   </p> 
</div>

---

<h2 align="center">🛠️ Architecture</h2>

The system is composed of the following services:

- **microservice-patient** 🩺 → Manages patient data (CRUD)  
- **microservice-note** 📝 → Stores patient medical notes  
- **microservice-rapport** 📊 → Generates diabetes risk reports  
- **microservice-gateway** 🔗 → API Gateway for routing & securing requests  
- **microservice-frontend** 💻 → Web UI with Thymeleaf & Bootstrap  
- **Keycloak** 🔒 → Identity & Access Management (OAuth2)  
- **MySQL** 🗄️ → Databases for patients and Keycloak  

All services are containerized and orchestrated with **Docker Compose**

---

<h2 align="center">🚀 Installation & Setup</h2>

### 🔧 **Prerequisites**
- Docker  
- Docker Compose  
- Java 17+ (for local development)  
- Maven  

### 📦 **Clone the Repository**
```sh
git clone https://github.com/lopesthomas/MediLabo_Solutions.git
cd MediLabo_Solutions
```

### 🗄 **Initialize Databases**

Script SQL initialization in:
`docker-entrypoint-initdb.d-patient` (for patient DB)

**Keycloak realm import JSON** in `keycloak-import`

### ▶ **Start All Services**
```sh
docker compose up --build
```
⏳ The first startup may take a few minutes (databases & Keycloak initialization)

<h2 align="center">🌐 Access Points</h2>

- Frontend: http://localhost:8082
- Keycloak Admin: http://localhost:8080
(default admin: admin / admin unless changed)
- API Gateway: http://localhost:8090

<h2 align="center">🧪 Test Account</h2>

A ready-to-use account is available for testing the application:

- Username: testuser
- Email: test@test.com
- Password: test

You can log in directly with this account from the frontend after startup ✅

<h2 align="center">🔐 Authentication</h2>

- Authentication is handled by Keycloak (OAuth2)
- Users log in through the frontend
- User & realm setup is managed via the Keycloak import JSON

<h2 align="center">⚙️ Configuration</h2>

- DB credentials & Keycloak settings → `compose.yaml`
- Spring Boot properties → `src/main/resources/application.properties` (per microservice)
- Frontend templates → Thymeleaf with static JS/CSS

<h2 align="center">👨‍💻 Development</h2>

- Each microservice is a standalone Spring Boot project
- You can run/debug services individually in your IDE
- Unit & integration tests are provided for core logic & controllers

<h2 align="center">⚠️ Troubleshooting (OS-specific setup)</h2>

Depending on your operating system, Docker networking may require adjustments :  

### 🐧 Linux (tested on Linux Mint)
On Linux, `host.docker.internal` may not resolve by default  
To fix this, add the following line to `/etc/hosts`:
- `127.0.0.1 host.docker.internal`

Then restart Docker and relaunch the containers:
```sh
docker compose down -v
docker compose up --build
```

### 🪟 Windows

No extra configuration needed. `host.docker.internal` works out of the box

### 🍏 macOS

⚠️ Not tested yet, but Docker Desktop for Mac normally supports `host.docker.internal` natively.
If issues occur, the same `/etc/hosts` fix as on Linux may be required

<h2 align="center">🌱 Green Code & Eco-Design</h2>

As part of a sustainable and energy-efficient approach, here are several Green Code best practices to minimize its environmental impact.

### ♻️ Limiting Energy-Intensive Code

- 🚫 Avoid unnecessary network calls — group Feign or REST requests to reduce latency and bandwidth usage
- ⚙️ Optimize algorithms — prevent looping through all patients or notes when only a subset is required
- 💾 Use caching where appropriate (e.g., for static or rarely updated data)
- 📦 Reduce payload size — design lightweight and context-appropriate DTOs to limit data transfer volume

### 🔄 Green Refactoring Guidelines

- 🧩 Factorize redundant code — create reusable methods or shared services to improve maintainability
- 🧹 Remove unused endpoints or methods to simplify the codebase and reduce maintenance overhead
- 🗃️ Optimize SQL/NoSQL queries — use indexes, projections, and pagination to reduce database load
- ✅ Automate testing — detect issues early to avoid costly (and energy-hungry) fixes later
- 📊 Monitor container memory and CPU usage — adjust allocated resources to match actual service needs

<h2 align="center">📝 Notes</h2>

- 🚫 Do not expose internal services (patient/note/report) directly — only the gateway & frontend should be public
- 🗄️ DB initialization scripts only run when the volume is created (not on each restart)
- 📅 Date & time formatting in the frontend is localized for French display
