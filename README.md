# Calculador de Taxas e Valor/Hora

Este é um projeto desenvolvido utilizando as tecnologias Java 17, Angular 15, MySQL 8, Flyway, Redis e padrões de arquitetura Domain Driven Design. A aplicação consiste em uma calculadora de taxas e valor por hora de trabalho.

### [Site está online Link aqui.](https://taxes-calculator.herokuapp.com/)

# Video do aplicativo

https://user-images.githubusercontent.com/107776531/226207960-e5583935-b171-4127-8359-4621e627b42f.mp4

## Pré-requisitos

Antes de rodar o projeto, é necessário ter instalado em sua máquina as seguintes ferramentas:

- Docker
- Docker Compose
- Java 17

## Como rodar o projeto

### Clonando o repositório

Para clonar o repositório, execute o seguinte comando no seu terminal:

```
git clone https://github.com/d1av/taxes-calculator-spring-angular.git
```

### Iniciando o ambiente com Docker Compose

Com o repositório clonado, acesse a pasta raiz do projeto e execute o seguinte comando no seu terminal para iniciar o ambiente com Docker Compose:


``` 
docker-compose up
```

Este comando irá construir as imagens dos containers e iniciar os serviços do MySQL, Redis, API e aplicação Angular.

### Acessando a aplicação

Com o ambiente iniciado, acesse a aplicação no seu navegador em `http://localhost:4200`.

## Tecnologias utilizadas
- Java 17
- Angular 15
- MySQL 8
- Flyway
- Redis
- Domain Driven Design

## Como contribuir
- Faça um fork do repositório
- Crie uma branch com sua feature: git checkout -b minha-feature
- Faça commit das suas alterações: git commit -m 'Adicionando nova funcionalidade'
- Faça push para a sua branch: git push origin minha-feature
- Crie um pull request para o repositório original

## Licença
Este projeto está sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.


# API Endpoints

### [Link here.](https://taxes-calculator.herokuapp.com/swagger-ui/index.html)

