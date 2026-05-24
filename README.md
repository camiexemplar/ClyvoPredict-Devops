# Clyvo Predict

## Descrição do Projeto

O Clyvo Predict é uma API REST desenvolvida em Java com Spring Boot para gerenciamento de tutores, pets e eventos de saúde animal.

A solução permite o cadastro e monitoramento de informações importantes relacionadas aos animais, facilitando o controle de dados veterinários e acompanhamento de saúde.

---

# Benefícios para o Negócio

- Centralização das informações dos pets e tutores
- Facilidade no gerenciamento de dados veterinários
- Monitoramento de eventos de saúde animal
- Melhor organização e rastreabilidade das informações
- API preparada para integração com aplicações web e mobile
- Solução conteinerizada e escalável em nuvem

---

# Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- Docker
- Docker Compose
- Azure CLI
- Swagger / OpenAPI

---

# Arquitetura da Solução

Fluxo da arquitetura:

Usuário
↓
Browser / Postman
↓
Azure Virtual Machine (Linux)
↓
Docker Engine
↓
Container Spring Boot
↓
Banco H2 Persistente
↓
Volume Docker Nomeado

---

# Endpoints da API

## Tutores

### Listar tutores

```http
GET /api/tutores
```

### Buscar tutor por ID

```http
GET /api/tutores/{id}
```

### Criar tutor

```http
POST /api/tutores
```

### Login tutor

```http
POST /api/tutores/login
```

---

## Pets

### Listar pets

```http
GET /api/pets
```

### Buscar pet por ID

```http
GET /api/pets/{id}
```

### Criar pet

```http
POST /api/pets
```

### Atualizar pet

```http
PUT /api/pets/{id}
```

### Remover pet

```http
DELETE /api/pets/{id}
```

---

## Eventos de Saúde

### Criar evento

```http
POST /api/eventos
```

---

# Como Executar a Aplicação

## Clonar repositório

```bash
git clone https://github.com/camiexemplar/ClyvoPredict-Devops.git
```

---

## Entrar na pasta do projeto

```bash
cd ClyvoPredict-Devops
```

---

## Executar com Docker Compose

```bash
docker compose up -d
```

---

# Acesso da Aplicação

## API

```text
http://IP-DA-VM:8080
```

---

## Swagger

```text
http://IP-DA-VM:8080/swagger-ui/index.html
```

---

## H2 Console

```text
http://IP-DA-VM:8080/h2-console
```

---

# Configuração do Banco H2

## JDBC URL

```text
jdbc:h2:file:/data/clyvodb
```

## Usuário

```text
sa
```

## Senha

```text
(vazio)
```

---

# Persistência de Dados

A aplicação utiliza volume nomeado Docker para persistência do banco H2.

Volume utilizado:

```text
h2data
```

Mesmo após reiniciar os containers, os dados permanecem salvos.

---

# Docker

## Dockerfile

O projeto possui Dockerfile próprio para build da aplicação Java.

## Docker Compose

O projeto utiliza Docker Compose para orquestração da aplicação e persistência do banco.

---

# Script Azure CLI

O arquivo `azure-script.sh` automatiza:

- Criação da VM Linux na Azure
- Abertura das portas necessárias
- Instalação do Docker
- Instalação do Git
- Instalação do Nano
- Configuração do ambiente da aplicação

---

# Execução em Background

A aplicação é executada em background utilizando Docker Compose:

```bash
docker compose up -d
```

---

# Usuário Não Root

A aplicação é executada utilizando usuário sem privilégios administrativos dentro do container:

```text
appuser
```

---

# Evidências da Entrega

A entrega contempla:

- CRUD completo
- Persistência de dados
- Banco H2 conteinerizado
- Volume nomeado
- Execução em nuvem Azure
- Docker
- Docker Compose
- Swagger
- Azure CLI
- Persistência após reinicialização
- Execução em background

---

# Integrantes


Eduarda Weiss Ventura
RM: 564434
Maria Gabriela Landim Severo
RM: 565146
Samara Porto Souza
RM: 559072
Lucas Nunes Soares
RM: 566503
Camily Vitoria Pereira Maciel
RM: 566520
