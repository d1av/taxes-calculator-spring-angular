# Projeto Spring Boot com Angular e MySQL - API de cálculo do valor/hora trabalhado

Este projeto consiste em uma API desenvolvida em Spring Boot que permite calcular o valor/hora trabalhado a partir das informações digitadas pelo usuário. Além disso, o projeto conta com um frontend desenvolvido em Angular para permitir o acesso à API através de uma interface gráfica. Para o armazenamento dos dados, utilizamos o banco de dados MySQL. A aplicação é disponibilizada através de um contêiner Docker, permitindo que possa ser executada em qualquer ambiente.

## Requisitos para execução do projeto

Antes de executar o contêiner Docker, certifique-se de ter os seguintes requisitos instalados em sua máquina:

- Docker
- JDK 11 ou superior
- Node.js
- Angular CLI

Além disso, é necessário ter um servidor MySQL configurado para utilizar como banco de dados da aplicação.

## Executando o projeto com Docker

O primeiro passo para executar o projeto com Docker é clonar este repositório em sua máquina. Para isso, execute o seguinte comando no terminal:

```
git clone https://github.com/d1av/taxes-calculator-spring-angular.git
```

Em seguida, navegue até o diretório do projeto clonado e execute o seguinte comando para gerar o arquivo `.jar` da aplicação:

```
./mvnw clean package
```

Com o arquivo `.jar` gerado, você pode construir a imagem do contêiner Docker executando o seguinte comando:

```
docker build -t valor-hora .
```

Neste exemplo, estamos usando o nome `valor-hora` para a imagem do contêiner, mas você pode escolher qualquer outro nome que desejar.

Após a construção da imagem, você pode executar o contêiner Docker com o seguinte comando:

```
docker run -p 8080:8080 valor-hora
```

Este comando irá iniciar o contêiner Docker e mapear a porta 8080 do contêiner para a porta 8080 do host. Com isso, você poderá acessar a API através do endereço `http://localhost:8080`.

## Configurando o MySQL

Antes de executar a aplicação, é necessário configurar o banco de dados MySQL. Para isso, abra o arquivo `src/main/resources/application.properties` e edite as seguintes propriedades:

```
spring.datasource.url=jdbc:mysql://<endereco-do-servidor>:3306/meu-banco-de-dados
spring.datasource.username=<usuario>
spring.datasource.password=<senha>
```

Substitua `<endereco-do-servidor>` pelo endereço do servidor MySQL, `<usuario>` pelo nome de usuário e `<senha>` pela senha do usuário.

## Executando o frontend

Para executar o frontend em Angular, navegue até o diretório `src/main/frontend` e execute o seguinte comando:

```
ng serve
```

Este comando irá iniciar o servidor de desenvolvimento do Angular, permitindo que você acesse a aplicação através do endereço `http://localhost:4200`.

## Utilizando a aplicação

Com a API e o frontend em execução, você pode utilizar a aplicação para calcular o valor/hora trabalhado enviando requisições HTTP para os endpoints disponíveis ou através da interface gráfica do frontend. A seguir, você encontrará a documentação dos endpoints disponíveis.

### Calcular valor/hora trabalhado

```
GET /api/hourvalues/monthly/{id}
```

Este endpoint permite calcular o valor/hora trabalhado com base nas informações fornecidas pelo usuário. A url da requisição deve conter o id do usuário.

Exemplo de requisição:

```
GET http://localhost:8080/api/hourvalues/monthly/998675f558eb45d1a455c8e835350d2a
Content-Type: application/json
```

Exemplo de resposta:

```
HTTP/1.1 200 OK
Content-Type: application/json

{
    "hourValue": 29.27777777777778,
    "hoursWorked": 144,
    "expectedSalary": 2000,
    "totalMonthlyCosts": 2216,
    "hourValueId": "4a43d73842bf4be6831b13015e677bd1",
    "variableTaxId": "0c52fa87cbb64dfba421b43e97f245aa",
    "fixedTaxId": "b403f1d288d64696af0c3b4180479ad3"
}
```

Exemplo de requisição:

```
GET http://localhost:8080/
