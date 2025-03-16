# 🚀 Investment API

Este repositório contém a implementação do backend da aplicação. Siga os passos abaixo para configurar e executar o projeto corretamente.

## 🔧 Tecnologias utilizadas

- Java 21 (Azul)
- Spring Framework
- PostgreSQL
- Docker
- Swagger (Springdoc-openapi)

## 📌 Requisitos

Antes de iniciar, certifique-se de ter instalado em sua máquina:

- [Docker](https://www.docker.com/) para executar a aplicação
- [Insomnia](https://insomnia.rest/) para testar a API

## 👥 Instalação

1. Clone este repositório:

   ```sh
   git clone https://github.com/GabrielHenriqueHE/investments-api.git
   cd /caminho/do/repositorio
   ```

2. Suba o container do banco de dados para compilar a aplicação pela primeira vez.
   ```sh
   docker-compose up db
   ```
    Aguarde até que apareça a seguinte mensagem, que representa que o banco de dados está pronto para receber conexões:

   ```sh
   db-investments-api | 2025-03-16 03:54:17.434 UTC [41] LOG:  database system is ready to accept connections
   ```

3. Compile o projeto
   ```sh
   ./mvnw clean package
   ```

4. Pare o container do banco de dados e suba ambos simultaneamente
   ```sh
    docker-compose down
    docker-compose up --build
   ```
Após a execução desse comando a aplicação estará em execução.

## Endpoints da API

`POST /investimentos`

Este é o endpoint que irá receber os novos investimentos. Cada investimento possui nome, tipo, valor investido e a data quando foi realizado o investimento:
```json
{
  "name": "Fundo X",
  "type": "FUNDS",
  "amount": 120.00,
  "datetime": "2025-03-15T12:34:56.789Z"
}
```

Este endpoint aceitará investimentos que:
1. Tenham todos os campos preenchidos.
2. Tenha o valor investido maior que 0.
3. Não possua uma data futura para a data de investimento.

Respostas possíveis:
- `201 Created` - Dados do investimento
    - O investimento foi validado e cadastrado.
  ```json
      {
          "id": 1,
          "name": "Fundo X",
          "type": "FUNDS",
          "amount": 120.00,
          "datetime": "2025-03-15T12:34:56.789Z"
      }
  ```
- `422 Unprocessable Entity`
    - O cadastro do investimento não foi aceito por qualquer motivo (um ou mais dos critérios de aceitação não foram atendidos).
- `400 Bad Request`
    - A API não compreendeu a requisição do cliente.

`GET /investimentos`

Este endpoint é responsável por retornar todos os investimentos cadastrados.

Respostas possíveis:
- `200 OK` - Lista com as entidades que representam os investimentos
  ```json
      [
          {
              "id": 1,
              "name": "Fundo X",
              "type": "FUNDS",
              "amount": 120.00,
              "datetime": "2025-03-15T12:34:56.789Z"
          },
          {
              "id": 2,
              "name": "Ação Y",
              "type": "STOCKS",
              "amount": 250.00,
              "datetime": "2025-03-15T12:34:56.789Z"
          }
      ]
  ```

`PUT /investimentos/{id}`

Este é o endpoint que permite a edição dos dados de um investimento específico. A atualização pode ser geral ou parcial:

```json
    {
      "name": "Fundo Y",
      "amount": 100.00
    }
```

As restrições permanecem como as definidas para o endpoint `POST /investimentos`.

Respostas possíveis:
- `201 Created` - Dados do investimento
    - O investimento foi validado e cadastrado.
  ```json
      {
          "id": 1,
          "name": "Fundo Y",
          "type": "FUNDO",
          "amount": 100.00,
          "datetime": "2025-03-15T12:00:00Z"
      }
  ```
- `404 Not Found`
    - Não foi encontrado nenhum registro para o ID correspondente.
- `422 Unprocessable Entity`
    - O cadastro do investimento não foi aceito por qualquer motivo (um ou mais dos critérios não foram atendidos).
- `400 Bad Request`
    - A API não compreendeu a requisição do cliente.

`DELETE /investimentos/{id}`

Este endpoint é responsável por fazer a remoção de um investimento do sistema.

Respostas possíveis:
- `200 OK`
    - O investimento foi excluído corretamente no sistema.
- `404 Not Found`
    - Não foi encontrado nenhum registro para o ID correspondente.
- `400 Bad Request`
    - A API não compreendeu a requisição do cliente.

`GET /docs/swagger-ui`

Este endpoint está configurado para exibir a interface gráfica do Swagger OpenAPI com a documentação da aplicação.