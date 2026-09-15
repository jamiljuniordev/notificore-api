# 📬 NotifiCore API

> Microserviço robusto para gerenciamento, agendamento e envio de notificações em múltiplos canais (E-mail, SMS, Push).

---

## 📌 Sobre o Projeto

O **NotifiCore API** foi desenvolvido para centralizar a mensageria e o envio de notificações em aplicações distribuídas. O serviço recebe solicitações de notificação, processa as regras de negócio de acordo com o canal especificado, aplica validações de formato e persiste o histórico de envios para auditoria.

### 🌟 Destaques do Projeto
* **Arquitetura em Camadas:** Divisão clara entre Controllers, Services, DTOs e Repositories.
* **Validação de Payload:** Garantia da integridade dos dados na entrada da API.
* **Tratamento Global de Exceções:** Respostas padronizadas para erros de requisição.
* **Suíte de Testes:** Cobertura de testes unitários e de integração com JUnit 5 e Mockito.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21 (LTS)
* **Framework:** Spring Boot 3.3.4
* **Persistência:** Spring Data JPA & H2 Database (In-Memory)
* **Documentação:** OpenAPI 3 / Swagger UI
* **Testes:** JUnit 5, Mockito & Spring Boot Test
* **Gerenciador de Dependências:** Apache Maven

---

## 📂 Estrutura do Projeto

```text
src/main/java/com/notificore/api/
├── config/        # Configurações globais e Swagger
├── controller/    # Endpoints REST (NotificacaoController)
├── dto/           # Objetos de Transferência de Dados (Requests/Responses)
├── enums/         # Tipos enumerados (Canais de Notificação, Status)
├── exception/     # Handlers de exceções customizadas
├── model/         # Entidades do JPA (Mapeamento de banco de dados)
├── repository/    # Interfaces de acesso ao banco (Spring Data JPA)
└── service/       # Regras de negócio e processamento de envios

Método,Endpoint,Descrição,Status Esperado
POST,/api/notificacoes/enviar,Envia ou agenda uma nova notificação,201 Created
GET,/api/notificacoes/{id},Busca os detalhes de uma notificação por ID,200 OK

📥 Exemplo de Payload (POST /api/notificacoes/enviar)
JSON
{
  "destinatario": "usuario@email.com",
  "mensagem": "Seu código de verificação é 123456.",
  "canal": "EMAIL"
}

📤 Exemplo de Resposta (201 Created)
{
  "id": 1,
  "destinatario": "usuario@email.com",
  "mensagem": "Seu código de verificação é 123456.",
  "canal": "EMAIL",
  "dataEnvio": "2026-09-15T11:56:45",
  "status": "ENVIADO"
}

🚀 Como Executar o Projeto
Pré-requisitos
Java 21 JDK instalado.

Git instalado.

Passo a passo
Clone o repositório:
git clone [https://github.com/jamiljuniordev/notificore-api.git](https://github.com/jamiljuniordev/notificore-api.git)

Navegue até a pasta do projeto:
cd notificore-api

Compile e execute a aplicação:

No Windows:
mvnw spring-boot:run

No Linux/Mac:
./mvnw spring-boot:run

Acesse a Documentação Interativa (Swagger UI):
Abra no seu navegador: http://localhost:8081/swagger-ui/index.html

🧪 Executando os Testes
Para garantir o funcionamento correto de toda a suíte de testes unitários e de integração:
mvnw test

Desenvolvido por Jamil Junior 👋
