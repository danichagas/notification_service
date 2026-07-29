# OmniNotify API - Event-Driven Notification System

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4-brightgreen?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-white?style=for-the-badge&logo=postgresql)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-white?style=for-the-badge&logo=rabbitmq)
![Redis](https://img.shields.io/badge/Redis-black?style=for-the-badge&logo=redis)
![Docker](https://img.shields.io/badge/Docker-white?style=for-the-badge&logo=docker)

## Sobre o Projeto
O **OmniNotify** é um microserviço robusto focado no processamento e disparo assíncrono de notificações.
O principal objetivo deste projeto é demonstrar a implementação de uma **Arquitetura Orientada a Eventos (EDA)**, focando em alta disponibilidade, tolerância a falhas e segurança contra abusos de requisições.

A aplicação não realiza o envio de forma síncrona. Em vez disso, ela aceita a requisição, salva o estado no banco de dados e delega o processamento pesado para uma fila de mensageria (RabbitMQ), garantindo respostas ultrarrápidas ao cliente (HTTP 202 Accepted).

## Destaques da Arquitetura
* **Mensageria & Assincronismo:** Uso do RabbitMQ para desacoplar a API do processo de envio real (Mailtrap).
* **Resiliência:** Implementação do padrão `Retry` com **Resilience4j**. Se o provedor de e-mail externo cair, o sistema tenta novamente de forma automática antes de marcar como falha.
* **Segurança & Rate Limiting:** Uso de **Redis** como interceptador na camada de controle para barrar abusos (DDoS/Spam), retornando `HTTP 429 Too Many Requests` caso um IP exceda o limite de requisições por minuto.
* **Conteinerização:** Projeto totalmente encapsulado em Docker (Multi-stage build) junto com sua infraestrutura, orquestrado via `docker-compose`.

## Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 4** (Web, Data JPA, AMQP, Data Redis, Mail)
* **PostgreSQL** (Persistência de estado da notificação)
* **RabbitMQ** (Message Broker)
* **Redis** (Controle de Rate Limit distribuído)
* **Resilience4j** (Tolerância a falhas)
* **Swagger/OpenAPI 3** (Documentação)
* **Docker & Docker Compose** (Infraestrutura as Code)

## Como Executar

Você não precisa ter o Java, Maven ou qualquer banco de dados instalado na sua máquina. Apenas o **Docker** é necessário.

1. Clone o repositório:
```bash
git clone https://github.com/danichagas/omni_notify_api.git
cd omni_notify_api
```

2. Suba a infraestrutura completa e a API com um único comando:
```bash
docker-compose up -d
```

3. O sistema estará disponível nas seguintes portas:

    **API & Swagger:** http://localhost:8080/swagger-ui.html (ou 8081 dependendo da configuração)

    **RabbitMQ Management:** http://localhost:15672 (guest / guest)

## Endpoints da API

A documentação interativa completa pode ser acessada via Swagger após subir a aplicação.

| Método | Rota | Descrição | Status Retorno |
|---|---|---|---|
| `POST` | `/v1/notifications` | Enfileira uma nova notificação para envio | `202 Accepted` |

**Exemplo de Payload:**

```json
{
  "destination": "cliente@email.com",
  "message": "Sua conta foi criada com sucesso!",
  "type": "EMAIL"
}
```

## 👨‍💻 Desenvolvedor

Criado por **Daniel Chagas**.

Se quiser trocar uma ideia sobre tecnlogia ou Java, me chama aí:
* [LinkedIn](https://www.linkedin.com/in/danichagasdev/)
* [GitHub](https://github.com/danichagas)