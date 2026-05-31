# 🏥 Clinica API

### REST API para gerenciamento de consultas médicas

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-6DB33F?style=for-the-badge\&logo=spring\&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-316192?style=for-the-badge\&logo=postgresql\&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Latest-2496ED?style=for-the-badge\&logo=docker\&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?style=for-the-badge\&logo=apache-maven\&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

---

## 📖 Sobre o Projeto

A **Clinica API** é uma API REST desenvolvida para gerenciar o fluxo de consultas de uma clínica médica.

O sistema permite:

* Cadastro de pacientes
* Cadastro de médicos
* Agendamento de consultas
* Cancelamento de consultas
* Controle de acesso por perfis de usuário
* Autenticação segura com JWT
* Soft Delete para entidades críticas

> 🚧 Projeto em desenvolvimento ativo.

---

## ✨ Funcionalidades

### Implementadas

* [x] Setup inicial do projeto
* [x] Configuração PostgreSQL
* [x] Configuração Docker
* [x] Entidade Patient
* [x] Bean Validation
* [x] Soft Delete

### Em desenvolvimento

* [ ] Entidade Doctor
* [ ] Entidade Appointment
* [ ] DTOs Request e Response
* [ ] Camada Service
* [ ] Spring Security + JWT
* [ ] Tratamento global de exceções
* [ ] Testes unitários
* [ ] Swagger / OpenAPI
* [ ] Docker Compose

---

## 🏗 Arquitetura

O projeto segue arquitetura em camadas:

```text
src/main/java
│
├── controller
│   └── Endpoints REST
│
├── service
│   └── Regras de negócio
│
├── repository
│   └── Persistência de dados
│
├── domain
│   ├── entity
│   └── enums
│
├── infra
│   ├── security
│   └── exception
│
└── dto
    ├── request
    └── response
```

---

## 📊 Domain Model

### Entidades Principais

| Entidade    | Descrição                        |
| ----------- | -------------------------------- |
| Patient     | Paciente cadastrado              |
| Doctor      | Médico da clínica                |
| Appointment | Consulta entre paciente e médico |
| User        | Usuário autenticado do sistema   |

### Regras de Negócio

* Não é permitido agendar consultas em datas passadas
* Um médico não pode possuir duas consultas no mesmo horário
* Um paciente não pode possuir duas consultas simultâneas
* `cancelReason` só pode ser preenchido quando a consulta estiver cancelada
* Pacientes e médicos utilizam Soft Delete (`active = false`)

---

## 🛠 Tecnologias

| Tecnologia           | Uso                           |
| -------------------- | ----------------------------- |
| Java 17              | Linguagem principal           |
| Spring Boot 4.0.6    | Framework principal           |
| Spring Security      | Autenticação e autorização    |
| Spring Data JPA      | Persistência de dados         |
| Hibernate ORM        | Mapeamento objeto-relacional  |
| PostgreSQL           | Banco de dados relacional     |
| Bean Validation      | Validação de dados            |
| Lombok               | Redução de boilerplate        |
| Docker               | Containerização               |
| Maven                | Gerenciamento de dependências |
| JUnit 5              | Testes unitários              |
| Spring Security Test | Testes de segurança           |

---

## 🚀 Como Rodar Localmente

### Pré-requisitos

* Java 17+
* Maven
* Docker

### 1. Clone o projeto

```bash
git clone https://github.com/RuanSegrini/clinica-api.git

cd clinica-api
```

### 2. Inicie o PostgreSQL

```bash
docker run --name clinicadb \
-e POSTGRES_PASSWORD=postgres \
-e POSTGRES_DB=clinicadb \
-p 5433:5432 \
-d postgres:16
```

### 3. Configure o application.yaml

```yaml
spring:
  application:
    name: clinica-api

  datasource:
    url: jdbc:postgresql://localhost:5433/clinicadb
    username: postgres
    password: postgres

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

server:
  port: 8080
```

### 4. Execute a aplicação

```bash
./mvnw spring-boot:run
```

A API estará disponível em:

```text
http://localhost:8080
```

---

## 🔗 Endpoints

### Patients

| Método | Endpoint       | Descrição              |
| ------ | -------------- | ---------------------- |
| POST   | /patients      | Cadastrar paciente     |
| GET    | /patients      | Listar pacientes       |
| GET    | /patients/{id} | Buscar paciente por ID |
| PUT    | /patients/{id} | Atualizar paciente     |
| DELETE | /patients/{id} | Desativar paciente     |

### Doctors

🚧 Em desenvolvimento

### Appointments

🚧 Em desenvolvimento

### Authentication

🚧 Em desenvolvimento

---

## 🗺 Roadmap

### Fase 1 - Base

* [x] Spring Boot
* [x] PostgreSQL
* [x] Docker
* [x] Entidade Patient

### Fase 2 - CRUDs

* [ ] PatientRepository
* [ ] PatientService
* [ ] PatientController
* [ ] DoctorRepository
* [ ] DoctorService
* [ ] DoctorController

### Fase 3 - Regras de Negócio

* [ ] Appointment
* [ ] Validações de agendamento
* [ ] Cancelamento de consultas

### Fase 4 - Segurança

* [ ] Spring Security
* [ ] JWT
* [ ] Controle de perfis

### Fase 5 - Qualidade

* [ ] Testes unitários
* [ ] Swagger/OpenAPI
* [ ] Docker Compose

---

## 👨‍💻 Autor

**Ruan Carolino Segrini**

GitHub: https://github.com/RuanSegrini

LinkedIn: https://www.linkedin.com/in/ruan-carolino-segrini/

---

Desenvolvido por **Ruan Segrini**.

