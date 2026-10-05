# 🔴🔵 FC Barcelona Management API

<p align="center">
  <img src="https://img.shields.io/badge/Status-Concluído-success?style=for-the-badge">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen?style=for-the-badge&logo=springboot">
  <img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk">
  <img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge">
</p>

> *“Més que un club”* — API RESTful desenvolvida para a gestão completa do ecossistema desportivo e administrativo do FC Barcelona.

---

## 🔗 Links Essenciais para Avaliação

*   📖 **Documentação Interativa (Swagger UI):** [Abrir Swagger UI](http://localhost:8080/swagger-ui/index.html)[cite: 3]
*   📜 **Licença do Projeto:** [Ver Licença (MIT)](./LICENSE)
*   ⚙️ **Regras de Funcionamento e Endpoints:** [Saltar para a Documentação da API](#-regras-de-funcionamento-e-endpoints)

---

## 🏟️ Sobre o Projeto
Este sistema foi desenvolvido como projeto final da disciplina de desenvolvimento de APIs com Spring Boot. A arquitetura modela o universo do **FC Barcelona**, contemplando entidades relacionais complexas, segurança nas validações, paginação de dados, hipermídia com HATEOAS e consultas personalizadas.

### 🧩 Entidades e Relacionamentos
O sistema é composto por **5 entidades principais**:
1. **Time** (`One-to-Many` com Jogadores | `Many-to-Many` com Patrocínios)
2. **Jogador** (`One-to-One` com Contrato | `Many-to-One` com Time)
3. **Contrato** (Gestão salarial e de tempo)
4. **Patrocínio** (Parcerias comerciais do clube)
5. **Treinador** (Comissão técnica)

---

## 🚀 Tecnologias Utilizadas

*   **Java 17**[cite: 3]
*   **Spring Boot 3.3.0**[cite: 3]
*   **Spring Data JPA (Hibernate)**[cite: 3]
*   **Spring HATEOAS**[cite: 3]
*   **Springdoc OpenAPI (Swagger)**[cite: 3]
*   **Bean Validation**[cite: 3]
*   **Banco de Dados H2** (Em memória)[cite: 3]

---

## ⚙️ Regras de Funcionamento e Endpoints

Todas as rotas de listagem da API estão otimizadas com **paginação (`Pageable`)** e ordenação dinâmica, além de implementarem hiperligações HATEOAS para garantir a navegabilidade entre os recursos[cite: 3].

### Endprincipais Endpoints Disponíveis:
*   **Contratos:** `POST /contrato`, `GET /contrato` (Paginado), `GET /contrato/{id}`[cite: 3]
*   **Times:** `POST /time`, `GET /time` (Paginado), `GET /time/{id}`[cite: 3]
*   **Patrocínios:** `POST /patrocinio`, `GET /patrocinio` (Paginado)[cite: 3]
*   **Treinadores:** `POST /treinador`, `GET /treinador` (Paginado)[cite: 3]
*   **Jogadores:**
    *   `POST /jogador` (Requer `contratoId` e `timeId`)[cite: 3]
    *   `GET /jogador` (Com suporte a `?page=0&size=5&sort=name,ASC`)[cite: 3]
    *   `GET /jogador/posicao/{position}` (**Consulta Personalizada** por posição, ex: `PONTA_ESQUERDA`)[cite: 3]

---

## 🛠️ Como Executar o Projeto

1. Certifica-te de ter o **Java 17** instalado na tua máquina[cite: 3].
2. Clona este repositório.
3. Executa o comando do Maven Wrapper no terminal para iniciar a aplicação:
   ```bash
   ./mvnw spring-boot:run