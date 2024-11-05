# Spring Área Restrita - API de Gerenciamento de Resultados

Esta API é desenvolvida para mostrar ánalises dos formulários, oferecendo funcionalidades para listar e adicionar resultados com filtros opcionais de tipo e período.

## Tecnologias Utilizadas

- **Java** com Spring Boot
- **Swagger/OpenAPI** para documentação
- **Banco de Dados** com MongoDB
- **Maven** para gerenciamento de dependências

## Estrutura do Projeto

- **`controllers`**: Controladores que definem os endpoints da API.
- **`services`**: Classes de serviço que contêm a lógica de negócios.
- **`models`**: Modelos que representam as entidades.

## Endpoints

### ResultadoController

Esta classe gerencia os endpoints para operações relacionadas aos resultados de formulários.

- **`GET /api/resultados/listar`**  
  Retorna uma lista de resultados filtrados por tipo de resultado e/ou período.  
  **Parâmetros**:
  - `filtroResultado` (opcional): Permite filtrar por tipo de resultado, como `'Estudantil'` ou `'Não tenho motivação'`.
  - `filtroData` (opcional): Permite filtrar por período, com opções como `'Hoje'`, `'Essa Semana'`, e `'Esse mês'`.  
  **Status**: 200 - Lista de resultados retornada com sucesso.

- **`POST /api/resultados/adicionar`**  
  Adiciona um novo resultado de formulário.  
  **Body**:
  ```json
  {
    "nome": "Nome do participante",
    "email": "email@exemplo.com",
    "resultado": "Resultado do formulário"
  }
