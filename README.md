# Finance System — API de Controle Financeiro Pessoal

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker)
![JWT](https://img.shields.io/badge/Security-JWT-black?style=for-the-badge&logo=jsonwebtokens)

O **Finance System** é uma API REST desenvolvida em Java com Spring Boot projetada para simplificar a gestão e o controle financeiro pessoal. A aplicação permite registrar transações, gerenciar contas bancárias, categorizar movimentações e acompanhar o saldo atualizado e métricas mensais por meio de dashboards integrados.

---

## Funcionalidades Principais

- **Autenticação & Segurança**
  - Autenticação stateless via **JWT (JSON Web Token)**.
  - Controle de acesso baseado em papéis (*Roles*: `ROLE_CUSTOM`, `ROLE_ADMIN`).
  - Criptografia de senhas com **BCrypt**.

- **Gestão de Contas & Bancos**
  - Cadastro de bancos (ex: Nubank, Santander, PicPay).
  - Vínculo de contas do usuário a bancos específicos (Carteira, Conta Corrente, Investimento, etc.).
  - Consulta de saldos consolidados e individuais.

- **Transações Financeiras (Receitas e Despesas)**
  - Lançamento de movimentações de **Entrada (`INCOME`)** e **Saída (`EXPENSE`)**.
  - **Atualização Automática de Saldo**: Ajusta o saldo da conta vinculada dinamicamente ao criar a transação.
  - Categorização de lançamentos (ex: Alimentação, Lazer, Transporte, Saúde).

- **Filtros Avançados e Paginação**
  - Busca customizada de transações utilizando **JPA Specifications** (filtragem por descrição, tipo, categoria, conta e período de datas).
  - Respostas paginadas para otimização de performance no consumo de dados.

- **Dashboard & Relatórios Financeiros**
  - Cálculo de totais mensais (Receita total, Despesa total, Balanço do mês e Saldo acumulado).
  - Agrupamento de despesas por categoria em um período.
  - Listagem rápida das últimas 10 transações realizadas.

- **Database Seeder Automatizado**
  - População inicial automática do banco de dados no ambiente de desenvolvimento/teste utilizando **DataFaker** para gerar massa de dados realistas.

---

## Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Framework Principal:** Spring Boot 3.5.5
- **Módulos Spring:**
  - Spring Web (API REST)
  - Spring Data JPA (Persistência e Queries)
  - Spring Security (Autenticação e Autorização)
  - Spring Validation (Validação de DTOs)
- **Banco de Dados:** PostgreSQL (Produção/Docker) / H2 (Testes/Dev)
- **Segurança:** Auth0 Java JWT & BCrypt
- **Ferramentas:** Lombok, DataFaker, JUnit 5, Mockito
- **Infraestrutura:** Docker & Docker Compose

---

## Estrutura do Projeto

```text
nicholas-amarante-finance-system/
└── Finance System/
    ├── docker-compose.yml
    ├── pom.xml
    └── src/
        ├── main/
        │   ├── java/com/example/demo/
        │   │   ├── config/          # Configurações gerais (DatabaseSeeder)
        │   │   ├── controller/      # Endpoints REST da aplicação
        │   │   ├── dto/             # Objetos de Transferência de Dados
        │   │   ├── models/          # Entidades JPA e Enums
        │   │   ├── repository/      # Interfaces de acesso ao banco (com Specifications)
        │   │   ├── security/        # Configurações de filtros JWT e SecurityFilterChain
        │   │   └── service/         # Regras de negócio da aplicação
        │   └── resources/           # Arquivos estáticos e propriedades
        └── test/                    # Testes unitários e de integração

```


## Endpoints

### Autenticação e Usuários (`/api/users`)
| Método | Endpoint | Descrição | Requer Auth |
|---|---|---|:---:|
| `POST` | `/api/users` | Cadastro de novos usuários no sistema | ❌ |
| `POST` | `/api/users/login` | Autenticação do usuário e geração de token JWT | ❌ |
| `GET` | `/api/users/profile` | Retorna as informações do perfil do usuário logado | ✅ |
| `PUT` | `/api/users/profile` | Atualiza os dados do perfil do usuário logado | ✅ |

### Contas Bancárias (`/api/accounts`)
| Método | Endpoint | Descrição | Requer Auth |
|---|---|---|:---:|
| `POST` | `/api/accounts` | Cadastra uma nova conta bancária para o usuário logado | ✅ |
| `GET` | `/api/accounts/my-accounts` | Lista todas as contas do usuário com banco e tipo | ✅ |

### Instituições Bancárias (`/api/banks`)
| Método | Endpoint | Descrição | Requer Auth |
|---|---|---|:---:|
| `POST` | `/api/banks` | Registra uma nova instituição bancária no sistema | ✅ |

### Categorias (`/api/category`)
| Método | Endpoint | Descrição | Requer Auth |
|---|---|---|:---:|
| `POST` | `/api/category` | Cria uma nova categoria financeira personalizada | ✅ |
| `GET` | `/api/category` | Lista as categorias disponíveis para o usuário | ✅ |

### Transações (`/api/transactions`)
| Método | Endpoint | Descrição | Requer Auth |
|---|---|---|:---:|
| `POST` | `/api/transactions` | Registra receita/despesa e atualiza o saldo da conta vinculada | ✅ |
| `GET` | `/api/transactions` | Histórico paginado e filtrado por descrição, tipo, categoria, conta e datas | ✅ |

### Dashboard (`/api/dashboard`)
| Método | Endpoint | Descrição | Requer Auth |
|---|---|---|:---:|
| `GET` | `/api/dashboard/by-monthly` | Resumo financeiro mensal (`totalIncome`, `totalExpense`, `balance`, `totalCurrentBalance`) | ✅ |
| `GET` | `/api/dashboard/by-category` | Total de despesas agrupadas por categoria no período | ✅ |
| `GET` | `/api/dashboard/ten-last` | Retorna o histórico das 10 movimentações mais recentes | ✅ |

---

## Como Executar o Projeto

### Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina:
* **Java 17** ou superior
* **Maven 3.8+** (opcional, pois o Maven Wrapper `./mvnw` já está incluído no repositório)
* **Docker & Docker Compose** (para execução do banco de dados PostgreSQL)

---

### Passo a Passo

1. **Clonar o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/nicholas-amarante-finance-system.git](https://github.com/seu-usuario/nicholas-amarante-finance-system.git)
   cd "nicholas-amarante-finance-system/Finance System"