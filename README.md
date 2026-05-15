<div align="center">

# 🔮 Clyvo Predict

**Microsserviço de Predição Inteligente — Challenge FIAP 2026**

[![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-brightgreen?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Oracle](https://img.shields.io/badge/Oracle-DB-red?style=for-the-badge&logo=oracle&logoColor=white)](https://www.oracle.com/database/)
[![Maven](https://img.shields.io/badge/Maven-Build-blue?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI_3-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)](https://swagger.io/)

> Serviço backend desenvolvido como parte do **Challenge FIAP 2026** em parceria com a startup **Clyvo** — plataforma voltada à análise preditiva e inteligência de dados para decisões estratégicas de negócio.

</div>

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Arquitetura e Tecnologias](#-arquitetura-e-tecnologias)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Pré-requisitos](#-pré-requisitos)
- [Configuração e Instalação](#-configuração-e-instalação)
- [Executando a Aplicação](#-executando-a-aplicação)
- [Documentação da API](#-documentação-da-api)
- [Variáveis de Ambiente](#-variáveis-de-ambiente)
- [Testes](#-testes)
- [Time](#-time)
- [Licença](#-licença)

---

## 🎯 Sobre o Projeto

O **Clyvo Predict** é o núcleo de backend da solução desenvolvida no **Challenge 2026 da FIAP** em parceria com a startup **Clyvo**. O serviço é responsável por expor uma API RESTful que alimenta modelos preditivos, processa e persiste dados analíticos, e serve como base para a tomada de decisão inteligente dentro da plataforma Clyvo.

### Contexto do Desafio

O Challenge FIAP é um projeto interdisciplinar onde equipes de estudantes trabalham diretamente com empresas reais para desenvolver soluções tecnológicas com impacto no mercado. A **Clyvo** propôs o desafio de criar um serviço de predição capaz de processar dados e entregar insights relevantes de forma escalável e documentada.

### Funcionalidades Principais

- 📊 **Ingestão e persistência** de dados preditivos via API REST
- ✅ **Validação robusta** de entrada com Bean Validation
- 🗄️ **Integração com Oracle Database** via JPA/Hibernate
- 📖 **Documentação interativa** da API com Swagger UI
- 🔁 **Hot reload** em desenvolvimento com Spring DevTools

---

## 🛠️ Arquitetura e Tecnologias

### Stack Principal

| Tecnologia | Versão | Finalidade |
|---|---|---|
| **Java** | 17 (LTS) | Linguagem principal |
| **Spring Boot** | 3.5 | Framework de aplicação |
| **Spring Data JPA** | — | Abstração de persistência ORM |
| **Spring Web** | — | Camada REST (controllers, DTOs) |
| **Spring Validation** | — | Validação declarativa de entidades |
| **Oracle JDBC (ojdbc11)** | — | Driver de conexão com o banco Oracle |
| **Lombok** | — | Redução de boilerplate (getters, setters, builders) |
| **Springdoc OpenAPI** | 2.8 | Geração automática do Swagger UI |
| **Spring DevTools** | — | Reload automático em desenvolvimento |
| **Spring Boot Test** | — | Suporte a testes unitários e de integração |
| **Maven** | Wrapper | Gerenciamento de build e dependências |

### Padrão Arquitetural

A aplicação segue o padrão de **arquitetura em camadas (Layered Architecture)** típico de aplicações Spring Boot:

```
┌──────────────────────────────────────┐
│           Controller Layer           │  ← REST Endpoints (@RestController)
├──────────────────────────────────────┤
│            Service Layer             │  ← Regras de negócio (@Service)
├──────────────────────────────────────┤
│          Repository Layer            │  ← Acesso a dados (Spring Data JPA)
├──────────────────────────────────────┤
│           Database (Oracle)          │  ← Persistência
└──────────────────────────────────────┘
```

---

## 📁 Estrutura do Projeto

```
clyvo-predict/
│
├── .mvn/wrapper/               # Maven Wrapper (mvnw / mvnw.cmd)
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/fiap/clyvo/
│   │   │       ├── controller/     # Controllers REST
│   │   │       ├── service/        # Lógica de negócio
│   │   │       ├── repository/     # Interfaces JPA
│   │   │       ├── model/          # Entidades JPA
│   │   │       ├── dto/            # Data Transfer Objects
│   │   │       └── ClyvoApplication.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties   # Configurações gerais
│   │       └── application-dev.properties
│   │
│   └── test/
│       └── java/
│           └── br/com/fiap/clyvo/      # Testes unitários e de integração
│
├── .gitattributes
├── .gitignore
├── mvnw                        # Maven Wrapper (Unix)
├── mvnw.cmd                    # Maven Wrapper (Windows)
└── pom.xml                     # Configuração do projeto Maven
```

---

## ✅ Pré-requisitos

Antes de iniciar, certifique-se de ter instalado em sua máquina:

- **[Java 17+](https://adoptium.net/)** — JDK (não apenas JRE)
- **[Maven 3.8+](https://maven.apache.org/download.cgi)** — ou use o wrapper `./mvnw` incluído no projeto
- **[Oracle Database](https://www.oracle.com/database/)** — instância local, Docker, ou acesso remoto
- **[Git](https://git-scm.com/)** — para clonar o repositório

> 💡 **Dica:** Não tem Maven instalado? Sem problema. O projeto inclui o Maven Wrapper (`./mvnw`). Todos os comandos abaixo funcionam substituindo `mvn` por `./mvnw` (Linux/Mac) ou `mvnw.cmd` (Windows).

---

## ⚙️ Configuração e Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/gabrielalandim/clyvo-predict.git
cd clyvo-predict
```

### 2. Configure o banco de dados

Edite o arquivo `src/main/resources/application.properties` com as credenciais da sua instância Oracle:

```properties
# Datasource
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
```

> ⚠️ **Atenção:** Nunca commite credenciais reais no repositório. Use variáveis de ambiente (ver seção [Variáveis de Ambiente](#-variáveis-de-ambiente)).

### 3. Instale as dependências

```bash
./mvnw clean install -DskipTests
```

---

## ▶️ Executando a Aplicação

### Modo desenvolvimento (com hot reload)

```bash
./mvnw spring-boot:run
```

### Gerando o JAR e executando

```bash
# Build
./mvnw clean package -DskipTests

# Execução
java -jar target/clyvo-predict-0.0.1-SNAPSHOT.jar
```

### Com variáveis de ambiente inline

```bash
DB_URL=jdbc:oracle:thin:@localhost:1521:XE \
DB_USER=clyvo \
DB_PASS=senha123 \
./mvnw spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8080
```

---

## 📖 Documentação da API

O projeto utiliza **Springdoc OpenAPI 2.8** para geração automática da documentação interativa.

Após subir a aplicação, acesse:

| Interface | URL |
|---|---|
| **Swagger UI** | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |
| **OpenAPI JSON** | [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) |
| **OpenAPI YAML** | [http://localhost:8080/v3/api-docs.yaml](http://localhost:8080/v3/api-docs.yaml) |

> O Swagger UI permite testar todos os endpoints diretamente pelo navegador, sem necessidade de ferramentas externas como Postman.

---

## 🔐 Variáveis de Ambiente

Para evitar expor credenciais no código-fonte, configure as seguintes variáveis de ambiente:

| Variável | Descrição | Exemplo |
|---|---|---|
| `DB_URL` | URL de conexão JDBC com o Oracle | `jdbc:oracle:thin:@localhost:1521:XE` |
| `DB_USER` | Usuário do banco de dados | `clyvo_user` |
| `DB_PASS` | Senha do banco de dados | `senha_secreta` |
| `SERVER_PORT` | Porta da aplicação (padrão: 8080) | `8080` |

No `application.properties`, referencie as variáveis assim:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}
server.port=${SERVER_PORT:8080}
```

---

## 🧪 Testes

### Executar todos os testes

```bash
./mvnw test
```

### Executar com relatório de cobertura

```bash
./mvnw verify
```

Os relatórios de teste ficam disponíveis em `target/surefire-reports/`.

---

## 👥 Time

Desenvolvido por estudantes da **FIAP** como parte do **Challenge 2026** em parceria com a startup **Clyvo**.

| Nome | GitHub |
|---|---|
| Maria Gabriela Landim Severo | [@gabrielalandim](https://github.com/gabrielalandim) |

> 📌 Projeto acadêmico desenvolvido no contexto do **Challenge FIAP 2026 — Clyvo**.

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos no contexto do **Challenge FIAP 2026**. Todos os direitos reservados aos seus autores.

---

<div align="center">

Feito com ☕ e ❤️ por estudantes da **FIAP** · Challenge 2026 × **Clyvo**

</div>
