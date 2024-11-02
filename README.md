# Sistema para Controle de Assinatura

## Sobre o Projeto

Este projeto foi desenvolvido na disciplina de **Construção de Software** e dividido em duas fases, abordando diferentes arquiteturas de software.

### Fase 1 - Serviço Monolítico

Na primeira parte do projeto, desenvolvemos o **serviço SCAA**, um sistema monolítico baseado na arquitetura **CLEAN**. Essa arquitetura promove a separação de responsabilidades e facilita a manutenção e escalabilidade do código. O serviço consiste em um serviço de controle de assinatura para empresas.

Na segunda parte do projeto, desenvolvemos o **serviço ASS-CACHE**, um microserviço baseado na mesma arquitetura da primeira etapa do projeto. O objetivo desse serviço, era desenvolver uma aplicação de cache, onde quando ouver uma requisição, o cache vai verificar suas estruturas de dados para ver se contêm a assinatura, se não tiver, vai perguntar para o serviço **SCAA**, o serviço da primeira etapa do projeto.

### Fase 2 - Microserviços

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
        - /dto - DTO da resposta da assintura
        - /useCases - Casos de uso do serviço
      - /domain
        - /entity - Entidade assintura
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

- **Criar/Listar/Editar/Remover usuário**
  - http://localhost:8000/servcad/create-user - Cria usuário
  - http://localhost:8000/servcad/get-user/{id} - Busca usuário por ID
  - http://localhost:8000/servcad/get-users - Lista usuários
  - http://localhost:8000/servcad/update-user/{id} - Editar um usuário pelo ID
  - http://localhost:8000/servcad/delete-user/{id} - Delete usuário pelo ID
- **Buscar por clientes**
  - http://localhost:8000/servcad/customers - Lista todos os clientes
- **Buscar/Editar aplicativos**
  - http://localhost:8000/servcad/applications - Lista todos os aplicativos
  - http://localhost:8000/servcad/applications/updatecost/{id} - Atualiza o custo de um aplicativo
- **Criar/Buscar assinaturas**
  - http://localhost:8000/servcad/signatures - Cria uma assinatura
  - http://localhost:8000/servcad/signatures/{status} - Busca assinaturas pelo status (ALL, ACTIVE, CANCELED)
  - http://localhost:8000/servcad/signature-customer/{id} - Busca assinaturas de um cliente
  - http://localhost:8000/servcad/signature-application/{id} - Busca assinatura pelo ID
  - http://localhost:8000/servcad/signature-is-valid/{id} - Verifica se o ID de uma assinatura é válida
- **Criar pagamento**
  - http://localhost:8000/registerpayment - Cria pagamento
