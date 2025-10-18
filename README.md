# Sistema de Gerenciamento Escolar

## Visão Geral
Este é um sistema de gerenciamento escolar desenvolvido com Spring Boot, que oferece uma API REST para gerenciar alunos, professores, cursos e disciplinas. O sistema possui autenticação JWT e documentação via Swagger.

### Funcionalidades Principais
- Gerenciamento de Alunos
- Gerenciamento de Professores
- Gerenciamento de Cursos
- Gerenciamento de Disciplinas
- Sistema de Autenticação com JWT
- API REST documentada

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.5.6
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- Swagger/OpenAPI para documentação
- Maven
- SQL Database (via JPA)

## Estrutura do Projeto
O projeto segue uma arquitetura em camadas:

```
src/
├── main/
│   ├── java/
│   │   └── com/nadic/desafiobackend/
│   │       ├── auth/         # Configurações de segurança e JWT
│   │       ├── config/       # Configurações do Swagger
│   │       ├── controllers/  # Controladores REST
│   │       ├── dtos/        # Objetos de Transferência de Dados
│   │       ├── entities/    # Entidades JPA
│   │       ├── exceptions/  # Tratamento de exceções
│   │       ├── repositories/# Repositórios JPA
│   │       └── services/    # Camada de serviços
│   └── resources/
│       ├── application.properties
│       ├── application-dev.properties
│       ├── application-test.properties
│       └── data.sql
```

## Documentação
A documentação da API pode ser acessada através do Swagger UI após iniciar a aplicação:
```
http://localhost:8080/api/v1/swagger-ui.html
```

## Como Executar
1. Clone o repositório:
```bash
git clone https://github.com/J-Vicente/Desafio-API-Backend.git
```

2. Entre na pasta do projeto:
```bash
cd Desafio-API-Backend
```

3. Execute o projeto com Maven:
```bash
./mvnw spring-boot:run
```

## Ambientes
O projeto possui três perfis de configuração:
- `default`: Configuração padrão
- `dev`: Ambiente de desenvolvimento
- `test`: Ambiente de testes


## Segurança
O sistema utiliza autenticação JWT (JSON Web Token) para proteger os endpoints. É necessário obter um token através do endpoint de autenticação antes de acessar as rotas protegidas.
