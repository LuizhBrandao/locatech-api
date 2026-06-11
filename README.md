#  Locatech API - Sistema de Locação de Veículos

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.0-brightgreen.svg)
![H2 Database](https://img.shields.io/badge/Database-H2-blue.svg)
![Swagger](https://img.shields.io/badge/Docs-Swagger%20UI-85EA2D.svg)

Uma API RESTful robusta desenvolvida para gerenciar o processo de locação de veículos de uma empresa (Locatech). Este projeto foi construído com foco em **Clean Architecture**, **Padrões de Projeto** e **Boas Práticas de Engenharia de Software**, servindo como demonstração de habilidades técnicas para o desenvolvimento backend com Java e Spring.

---

##  Funcionalidades e Regras de Negócio

* **Gestão de Clientes (Pessoas):** Cadastro e listagem com validação rigorosa de formato e unicidade de CPF e E-mail.
* **Gestão de Frota (Veículos):** Controle de veículos com bloqueio de placas duplicadas e monitoramento em tempo real de disponibilidade.
* **Sistema de Locação (Aluguéis):**
  * Verificação automática de disponibilidade do veículo (Padrão *Fail Fast*).
  * Validação cronológica (A data de devolução não pode ser anterior à data de início).
  * Bloqueio automático do status do veículo no momento da locação.

---

##  Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.x
* **Persistência e Banco de Dados:** Spring Data JPA, Hibernate, H2 Database (In-Memory)
* **Documentação:** Springdoc OpenAPI (Swagger UI)
* **Validações:** Jakarta Bean Validation (Hibernate Validator)

---

##  Arquitetura e Boas Práticas Aplicadas

1. **Separação de Responsabilidades (Clean Architecture):** Divisão clara entre `Controllers` (Web), `Services` (Regras de Negócio) e `Repositories` (Acesso a dados).
2. **Manipulação Global de Exceções:** Uso de `@RestControllerAdvice` para centralizar erros, retornando respostas padronizadas e semânticas (Status 400, 404, 500) sem expor *stack traces* ao cliente.
3. **Versionamento de API:** Estratégia de *URI Path Versioning* implementada (`/api/v1/` e simulação de retrocompatibilidade com `/api/v2/`).
4. **Injeção de Dependências:** Uso de construtores ao invés de `@Autowired` em atributos, promovendo maior modularidade e testabilidade do código.

---

##  Como Executar o Projeto

Como o projeto utiliza o banco de dados em memória **H2**, não é necessário instalar nenhum banco de dados externo. Apenas clone e rode!

### Pré-requisitos
* Java 21 instalado.
* Maven (opcional, o projeto já inclui o Maven Wrapper).

### Passos

1. Clone o repositório:
   ```bash
   git clone [https://github.com/LuizhBrandao/locatech-api.git](https://github.com/LuizhBrandao/locatech-api.git)
Entre na pasta do projeto:

```bash
cd locatech-api
```
Execute a aplicação usando o Maven Wrapper:

```bash
./mvnw spring-boot:run
```
A API estará rodando em http://localhost:8080.
http://localhost:8080/swagger-ui/index.html

## Documentação (Swagger)
A documentação interativa da API foi construída com OpenAPI 3 e pode ser acessada visualmente. Com a aplicação rodando, acesse no seu navegador:

 Acessar Swagger UI

Nesta interface, você pode testar todos os endpoints de CRUD e verificar os mapeamentos de requisições e respostas.

## Acesso ao Banco de Dados (H2 Console)
Para validar a persistência dos dados e visualizar as tabelas geradas automaticamente pelo Hibernate, utilize o painel do H2:

Acesse: http://localhost:8080/h2-console

Configure os campos exatamente como abaixo:

JDBC URL: jdbc:h2:mem:locatedb

User Name: sa

Password: (Deixe em branco)

Clique em Connect.

## Autor
Luiz Henrique Oliveira Brandão Desenvolvedor de Software Júnior
