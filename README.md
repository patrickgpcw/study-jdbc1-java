# Study JDBC com Java

Projeto de estudos criado para praticar a comunicação entre uma aplicação Java e um banco de dados MySQL utilizando JDBC.

A proposta é aprender, de forma incremental, como abrir conexões, executar comandos SQL, consultar e manipular dados, controlar transações e organizar o acesso ao banco de dados em uma aplicação Java.

## Tecnologias

- Java 21
- Maven
- JDBC
- MySQL 8
- Docker Compose
- IntelliJ IDEA

## Ambiente de banco de dados

O MySQL é executado em um contêiner Docker. Dessa forma, não é necessário instalar o servidor MySQL diretamente na máquina.

Para iniciar o banco de dados, execute na raiz do projeto:

```bash
docker compose up -d
```

Para verificar se o contêiner está funcionando:

```bash
docker compose ps
```

Para acompanhar os logs:

```bash
docker compose logs -f mysql
```

## Configuração da conexão

Os dados do ambiente local são:

| Propriedade | Valor |
| --- | --- |
| Host | `localhost` |
| Porta | `3306` |
| Banco | `jdbc_study` |
| Usuário | `jdbc_user` |
| Senha | `jdbc_password` |
| URL JDBC | `jdbc:mysql://localhost:3306/jdbc_study` |

Essas credenciais são destinadas exclusivamente ao ambiente local de estudos.

## Driver JDBC

O MySQL Connector/J é gerenciado pelo Maven e está declarado no `pom.xml`. Ao importar ou recarregar o projeto Maven no IntelliJ IDEA, a dependência é baixada automaticamente.

## Executando o projeto

1. Tenha o Docker em execução.
2. Inicie o MySQL com `docker compose up -d`.
3. Abra o projeto no IntelliJ IDEA.
4. Recarregue o projeto Maven para baixar as dependências.
5. Execute a classe `Main`.

## Encerrando o ambiente

Para parar e remover o contêiner:

```bash
docker compose down
```

Os dados permanecem armazenados no volume Docker. Para remover também o volume e reiniciar o banco do zero:

```bash
docker compose down -v
```

## Aulas

### Recuperando dados

Nesta aula, foi implementada uma consulta à tabela `department` para praticar:

- criação e execução de consultas com `Statement`;
- recuperação dos resultados com `ResultSet`;
- navegação pelos registros utilizando `next()`;
- leitura das colunas com `getInt()` e `getString()`;
- fechamento automático de `Statement` e `ResultSet` com `try-with-resources`;
- tratamento de exceções do JDBC.

## Objetivos de estudo

Ao longo do projeto serão explorados assuntos como:

- conexão com o banco utilizando `DriverManager`;
- execução de comandos com `Statement` e `PreparedStatement`;
- leitura de resultados com `ResultSet`;
- operações de inserção, consulta, atualização e exclusão;
- tratamento de erros e fechamento de recursos;
- controle de transações;
- organização do acesso a dados.

> Este projeto possui finalidade educacional e será atualizado conforme o avanço dos estudos sobre JDBC.
