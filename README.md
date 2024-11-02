# Sistema para Controle de Assinatura

## Sobre o Projeto

Este projeto foi desenvolvido na disciplina de **Construção de Software** e dividido em duas fases, abordando diferentes conceitos de um backend.

### Fase 1 - Serviço Monolítico

Na primeira parte do projeto, desenvolvemos o **serviço SCAA**, um sistema monolítico baseado na arquitetura **CLEAN**. Essa arquitetura promove a separação de responsabilidades e facilita a manutenção e escalabilidade do código. O serviço consiste em um serviço de controle de assinatura para empresas.

### Fase 2 - Microserviços

Na segunda parte do projeto, desenvolvemos o **serviço ASS-CACHE**, um microserviço baseado na mesma arquitetura da primeira etapa do projeto. O objetivo desse serviço, era desenvolver uma aplicação de cache, onde quando houver uma requisição, o cache vai verificar suas estruturas de dados para ver se contêm a assinatura, se não tiver, vai perguntar para o serviço **SCAA**, o serviço da primeira etapa do projeto.

<br>

A segunda parte consistiu na migração para uma arquitetura de **microserviços**, utilizando:
- **Name Server (Eureka)** para registro e descoberta de serviços.
- **API Gateway** para roteamento de requisições.
- **Serviço de Cache** para otimizar a resposta de dados frequentemente acessados.

A aplicação foi construída com **Java** e o framework **Spring Boot**.

## Tecnologias Usadas

- Java  
- Spring Boot  
- Eureka (Name Server para descoberta de serviços)  
- API Gateway  
- Cache Service (para otimização de dados)  
- Arquitetura CLEAN na fase monolítica  

## Como executar

Para rodar a aplicação, será necessário ter as seguintes ferramentas instaladas:
- Java 22
- Docker  
- Ferramenta para requisições de API, Postman, Insomnia, Bruno...

1. Clone o repositório:
```bash
git clone https://github.com/ThiagoGoulart02/subscription-control.git
```
2. Navegue para dentro do diretório subscription-control e suba os serviços com Docker Compose:
```bash
cd subscription-control
docker compose up
```
3. Use ferramentas como o Postman para testar as requisições na API.

## Estrutura das pastas

```
- /subscription-control  
  - /api-gateway - Serviço do API Gateway
  - /ass-cache - Serviço de cache  
    - /src/main/java/ass_cache/project/com 
      - /application  
        - /config - Configurações do RabbitMQ
        - /dto - DTO da resposta da assinatura
        - /useCases - Casos de uso do serviço
      - /domain
        - /entity - Entidade assinatura
        - /repository - Repositório do sistema
        - /service - Serviços do cache de assinatura
      - /infrastructure
        - /web
          - /controller - Endpoints do sistema
  - /name-server - Serviço do Eureka
  - /scaa - Serviço do controle de assinaturas
      - /src/main/java/scaa/project/com 
      - /application  
        - /config - Configurações do RabbitMQ
        - /dto - DTOs das requisições e respostas do sistema
        - /useCases - Casos de uso dos serviços
      - /domain
        - /entity - Entidades do sistema
        - /enums - Classe Enum utilizado no desenvolvimento
        - /repository - Repositórios do sistema
        - /service - Serviços do sistema
      - /infrastructure
        - /persistence - Repositórios do sistema
        - /web
          - /controller - Endpoints do sistema 
```
## Funcionalidades
<div style="text-align: justify;">
Um dos requisitos seria ter apenas um único usuário no banco de dados, como se uma empresa tivesse comprado o sistema para ela usar, então não há rotas refentes a usuários. Um único usuário é inserido no banco de dados ao inicializar a aplicação, juntamente com alguns outros dados, populando o banco. Os dados inserido estão no seguinte caminho: /subscription-control/scaa/src/main/resources/data.sql  
</div>

### Mapeamento dos serviços
Serviço do SCAA: http://localhost:8000  
Serviço do ASS-CACHE: http://localhost:8080  
Serviço do Name Server: http://localhost:8761  
Com a presença da API Gateway, todos os endpoints estão mapeados para o endereço: http://localhost:8765

### Rotas

As rotas devem ser adicionadas ao final do endereço do API Gateway, por exemplo: http://localhost:8765/servcad/create-user  

As rotas estão descritas da seguinte forma:  
[SERVIÇO] [MÉTODO] [ROTA] - [DESCRIÇÃO]
- **Buscar por clientes**
  - [SCAA] [GET] /servcad/customers - Lista todos os clientes
- **Buscar/Editar aplicativos**
  - [SCAA] [GET] /servcad/applications - Lista todos os aplicativos
  - [SCAA] [PUT] /servcad/applications/updatecost/{id} - Atualiza o custo de um aplicativo
- **Criar/Buscar assinaturas**
  - [SCAA] [POST] /servcad/signatures - Cria uma assinatura
  - [SCAA] [GET] /servcad/signatures/{status} - Busca assinaturas pelo status (ALL, ACTIVE, CANCELED)
  - [SCAA] [GET] /servcad/signature-customer/{id} - Busca assinaturas de um cliente
  - [SCAA] [GET] /servcad/signature-application/{id} - Busca assinatura de um aplicativo pelo ID
  - [SCAA] [GET] /servcad/verify-signature/{id} - Busca um assinatura pelo ID
  - [SCAA] [GET] /servcad/signature-is-valid/{id} - Verifica pelo ID, se uma assinatura está ativa
  - [ASS-CACHE] [GET] /ass-cache/signature-is-valid/{id} - Verifica pelo ID, se uma assinatura está ativa
    - Microserviço verifica sua estrutuda de dados em busca da assinatura
    - Se não achar, faz a requisição ao SCAA
    - Se a assinatura vinda do SCAA estiver ATIVA, o ASS-CACHE salva em suas estrutura de dados
    - Se a assinatura estiver CANCELADA, ele apenas irá retornar a assinatura
    - Se uma assinatura salva ASS-CACHE e ela atualizar o status para CANCELADA, o serviço remove ela do cache
- **Criar pagamento**
  - [SCAA] [POST] /registerpayment - Cria pagamento

### Exemplos de requisições:

- **Clientes**
  - Rota: [GET] http://localhost:8765/servcad/customers - Lista todos os clientes
  <details>
  <summary>Resposta esperada</summary>
  
  ```json
  [
    {
      "id": 1,
      "name": "customer 1",
      "email": "customer1@test.com"
    },
    {
      "id": 2,
      "name": "customer 2",
      "email": "customer2@test.com"
    },
    {
      "id": 3,
      "name": "customer 3",
      "email": "customer3@test.com"
    },
    {
      "id": 4,
      "name": "customer 4",
      "email": "customer4@test.com"
    },
    {
      "id": 5,
      "name": "customer 5",
      "email": "customer5@test.com"
    },
    {
      "id": 6,
      "name": "customer 6",
      "email": "customer6@test.com"
    },
    {
      "id": 7,
      "name": "customer 7",
      "email": "customer7@test.com"
    },
    {
      "id": 8,
      "name": "customer 8",
      "email": "customer8@test.com"
    },
    {
      "id": 9,
      "name": "customer 9",
      "email": "customer9@test.com"
    },
    {
      "id": 10,
      "name": "customer 10",
      "email": "customer10@test.com"
    }
  ]
  ```
  </details>
- **Buscar/Editar aplicativos**
  - Rota: [GET] /servcad/applications - Lista todos os aplicativos
  <details>
  <summary>Resposta esperada</summary>
  
  ```json
  [
	{
		"id": 1,
		"name": "app 1",
		"monthlyCost": 10.0
	},
	{
		"id": 2,
		"name": "app 2",
		"monthlyCost": 20.0
	},
	{
		"id": 3,
		"name": "app 3",
		"monthlyCost": 30.0
	},
	{
		"id": 4,
		"name": "app 4",
		"monthlyCost": 40.0
	},
	{
		"id": 5,
		"name": "app 5",
		"monthlyCost": 50.0
	},
	{
		"id": 6,
		"name": "app 6",
		"monthlyCost": 60.0
	},
	{
		"id": 7,
		"name": "app 7",
		"monthlyCost": 70.0
	},
	{
		"id": 8,
		"name": "app 8",
		"monthlyCost": 80.0
	},
	{
		"id": 9,
		"name": "app 9",
		"monthlyCost": 90.0
	},
	{
		"id": 10,
		"name": "app 10",
		"monthlyCost": 100.0
	}
  ]
  ```
  </details>

  - Rota: [PUT] /servcad/applications/updatecost/{id} - Atualiza o custo de um aplicativo
  <details>
  <summary>JSON para o body da requisição</summary>
  
  ```json
  {
	  "monthlyCost": (valor)
  } 
  ```
  Exemplo: /servcad/applications/updatecost/10 - 10 referente ao ID do aplicativo
  ```json
    {
	    "monthlyCost": 200.0
    }
    ```
  </details>
  <details>
  <summary>Resposta esperada</summary>
  
  ```json
    {
	  "id": 10,
	  "name": "app 10",
	  "monthlyCost": 200.0
  }
  ```
  </details>
- **Criar/Buscar assinaturas**
  - Rota: [POST] /servcad/signatures - Cria uma assinatura
  <details>
  <summary>JSON para o body da requisição</summary>
  
  ```json
  {
	  "applicationId": (ID),
	  "customerId": (ID)
  } 
  ```
  Exemplo: 
  ```json
    {
	  "applicationId": 7,
	  "customerId": 1
  }
    ```
  </details>
  <details>
  <summary>Resposta esperada</summary>
  
  ```json
    {
	    "id": 17,
	    "applicationId": 7,
	    "customerId": 1,
	    "beginningTerm": "2024-11-02",
	    "endTerm": "2024-11-09",
	    "status": "ACTIVE"
    }
  ```
  </details>

  - Rota: [GET] /servcad/signatures/{status} - Busca assinaturas pelo status (ALL, ACTIVE, CANCELED)
  <details>
  <summary>Resposta esperada</summary>

  /servcad/signatures/ALL
  ```json
    [
      {
        "id": 1,
        "applicationId": 1,
        "customerId": 1,
        "beginningTerm": "2024-05-01",
        "endTerm": "2024-06-01",
        "status": "CANCELED"
      },
      {
        "id": 2,
        "applicationId": 1,
        "customerId": 6,
        "beginningTerm": "2024-05-10",
        "endTerm": "2024-08-17",
        "status": "CANCELED"
      },
      {
        "id": 3,
        "applicationId": 2,
        "customerId": 7,
        "beginningTerm": "2024-04-01",
        "endTerm": "2024-05-01",
        "status": "CANCELED"
      },
      {
        "id": 4,
        "applicationId": 3,
        "customerId": 8,
        "beginningTerm": "2024-04-15",
        "endTerm": "2024-05-14",
        "status": "CANCELED"
      },
      {
        "id": 5,
        "applicationId": 4,
        "customerId": 9,
        "beginningTerm": "2024-05-01",
        "endTerm": "2024-09-30",
        "status": "CANCELED"
      },
      {
        "id": 6,
        "applicationId": 5,
        "customerId": 10,
        "beginningTerm": "2024-03-08",
        "endTerm": "2024-05-08",
        "status": "CANCELED"
      },
      {
        "id": 7,
        "applicationId": 1,
        "customerId": 10,
        "beginningTerm": "2024-05-01",
        "endTerm": "2024-05-08",
        "status": "CANCELED"
      },
      {
        "id": 8,
        "applicationId": 2,
        "customerId": 2,
        "beginningTerm": "2024-05-10",
        "endTerm": "2024-06-17",
        "status": "CANCELED"
      },
      {
        "id": 9,
        "applicationId": 3,
        "customerId": 3,
        "beginningTerm": "2024-05-01",
        "endTerm": "2024-06-30",
        "status": "CANCELED"
      },
      {
        "id": 10,
        "applicationId": 4,
        "customerId": 4,
        "beginningTerm": "2024-05-15",
        "endTerm": "2024-07-14",
        "status": "CANCELED"
      },
      {
        "id": 11,
        "applicationId": 5,
        "customerId": 5,
        "beginningTerm": "2024-03-01",
        "endTerm": "2024-04-30",
        "status": "CANCELED"
      },
      {
        "id": 12,
        "applicationId": 10,
        "customerId": 6,
        "beginningTerm": "2024-01-15",
        "endTerm": "2024-03-14",
        "status": "CANCELED"
      },
      {
        "id": 13,
        "applicationId": 9,
        "customerId": 7,
        "beginningTerm": "2024-03-01",
        "endTerm": "2024-04-30",
        "status": "CANCELED"
      },
      {
        "id": 14,
        "applicationId": 8,
        "customerId": 8,
        "beginningTerm": "2024-02-15",
        "endTerm": "2024-03-14",
        "status": "CANCELED"
      },
      {
        "id": 15,
        "applicationId": 7,
        "customerId": 9,
        "beginningTerm": "2024-05-02",
        "endTerm": "2024-05-30",
        "status": "CANCELED"
      },
      {
        "id": 16,
        "applicationId": 6,
        "customerId": 10,
        "beginningTerm": "2024-04-28",
        "endTerm": "2024-10-14",
        "status": "CANCELED"
      },
      {
        "id": 17,
        "applicationId": 7,
        "customerId": 1,
        "beginningTerm": "2024-11-02",
        "endTerm": "2024-11-09",
        "status": "ACTIVE"
      }
    ]
  ```
  /servcad/signatures/ACTIVES
  ```json
  [
    {
      "id": 17,
      "applicationId": 7,
      "customerId": 1,
      "beginningTerm": "2024-11-02",
      "endTerm": "2024-11-09",
      "status": "ACTIVE"
    }
  ]
  ```
  /servcad/signatures/CANCELED
  ```json
  [
    {
      "id": 1,
      "applicationId": 1,
      "customerId": 1,
      "beginningTerm": "2024-05-01",
      "endTerm": "2024-06-01",
      "status": "CANCELED"
    },
    {
      "id": 2,
      "applicationId": 1,
      "customerId": 6,
      "beginningTerm": "2024-05-10",
      "endTerm": "2024-08-17",
      "status": "CANCELED"
    },
    {
      "id": 3,
      "applicationId": 2,
      "customerId": 7,
      "beginningTerm": "2024-04-01",
      "endTerm": "2024-05-01",
      "status": "CANCELED"
    },
    {
      "id": 4,
      "applicationId": 3,
      "customerId": 8,
      "beginningTerm": "2024-04-15",
      "endTerm": "2024-05-14",
      "status": "CANCELED"
    },
    {
      "id": 5,
      "applicationId": 4,
      "customerId": 9,
      "beginningTerm": "2024-05-01",
      "endTerm": "2024-09-30",
      "status": "CANCELED"
    },
    {
      "id": 6,
      "applicationId": 5,
      "customerId": 10,
      "beginningTerm": "2024-03-08",
      "endTerm": "2024-05-08",
      "status": "CANCELED"
    },
    {
      "id": 7,
      "applicationId": 1,
      "customerId": 10,
      "beginningTerm": "2024-05-01",
      "endTerm": "2024-05-08",
      "status": "CANCELED"
    },
    {
      "id": 8,
      "applicationId": 2,
      "customerId": 2,
      "beginningTerm": "2024-05-10",
      "endTerm": "2024-06-17",
      "status": "CANCELED"
    },
    {
      "id": 9,
      "applicationId": 3,
      "customerId": 3,
      "beginningTerm": "2024-05-01",
      "endTerm": "2024-06-30",
      "status": "CANCELED"
    },
    {
      "id": 10,
      "applicationId": 4,
      "customerId": 4,
      "beginningTerm": "2024-05-15",
      "endTerm": "2024-07-14",
      "status": "CANCELED"
    },
    {
      "id": 11,
      "applicationId": 5,
      "customerId": 5,
      "beginningTerm": "2024-03-01",
      "endTerm": "2024-04-30",
      "status": "CANCELED"
    },
    {
      "id": 12,
      "applicationId": 10,
      "customerId": 6,
      "beginningTerm": "2024-01-15",
      "endTerm": "2024-03-14",
      "status": "CANCELED"
    },
    {
      "id": 13,
      "applicationId": 9,
      "customerId": 7,
      "beginningTerm": "2024-03-01",
      "endTerm": "2024-04-30",
      "status": "CANCELED"
    },
    {
      "id": 14,
      "applicationId": 8,
      "customerId": 8,
      "beginningTerm": "2024-02-15",
      "endTerm": "2024-03-14",
      "status": "CANCELED"
    },
    {
      "id": 15,
      "applicationId": 7,
      "customerId": 9,
      "beginningTerm": "2024-05-02",
      "endTerm": "2024-05-30",
      "status": "CANCELED"
    },
    {
      "id": 16,
      "applicationId": 6,
      "customerId": 10,
      "beginningTerm": "2024-04-28",
      "endTerm": "2024-10-14",
      "status": "CANCELED"
    }
  ]
  ```
  </details>

  - Rota: [GET] /servcad/signature-customer/{id} - Busca assinaturas de um cliente
  <details>
  <summary>Resposta esperada</summary>
  /servcad/signature-customer/1

  ```json
  [
      {
        "id": 1,
        "applicationId": 1,
        "customerId": 1,
        "beginningTerm": "2024-05-01",
        "endTerm": "2024-06-01",
        "status": "CANCELED"
      },
      {
        "id": 17,
        "applicationId": 7,
        "customerId": 1,
        "beginningTerm": "2024-11-02",
        "endTerm": "2024-11-09",
        "status": "ACTIVE"
      }
    ]
  ```
  </details>

  - Rota: [GET] /servcad/signature-application/{id} - Busca assinatura de um aplicativo pelo ID
  <details>
  <summary>Resposta esperada</summary>
  /servcad/signature-application/10

  ```json
  [
    {
      "id": 12,
      "applicationId": 10,
      "customerId": 6,
      "beginningTerm": "2024-01-15",
      "endTerm": "2024-03-14",
      "status": "CANCELED"
    }
  ]
  ```
  </details>

  - Rota: [GET] /servcad/verify-signature/{id} - Busca um assinatura pelo ID
  <details>
  <summary>Resposta esperada</summary>
  /servcad/signature-is-valid/1

  ```json
  false
  ```
  </details>
  
  - Rota: [GET] /servcad/signature-is-valid/{id} - Verifica pelo ID, se uma assinatura está ativa
  - Rota: [GET] /ass-cache/signature-is-valid/{id} - Verifica pelo ID, se uma assinatura está ativa

  <details>
  <summary>Resposta esperada</summary>
  /ass-cache/signature-is-valid/1

  ```json
  {
    "id": 1,
    "applicationId": 1,
    "customerId": 1,
    "beginningTerm": "2024-05-01",
    "endTerm": "2024-06-01",
    "status": "CANCELED"
  }
  ```
  </details>

- **Criar pagamento**
  - [SCAA] [POST] /registerpayment - Cria pagamento
  <details>
  <summary>JSON para o body da requisição</summary>
  /registerpayment

  ```json
  {
    "paymentDate": (data),
    "signatureId": (ID),
    "amountPaid": (valor)
  }
  ```
  exemplo
  ```json
  {
    "paymentDate": "2024-08-05",
    "signatureId": 1,
    "amountPaid": 1500
  }
  ```
  </details>
    <details>
  <summary>Resposta esperada</summary>

  ```json
  {
    "paymentDate": "2024-09-04",
    "paymentReversal": 0.0,
    "status": "PAYMENT_OK"
  }
  ```
  </details>
