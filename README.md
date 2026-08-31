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

### Inserindo dados

Nesta aula, foram implementadas inserções nas tabelas `seller` e `department` para praticar:

- criação de comandos parametrizados com `PreparedStatement`;
- associação de valores aos parâmetros utilizando os métodos `setString()`, `setDate()`, `setDouble()` e `setInt()`;
- conversão e envio de datas para o banco de dados;
- execução de comandos de inserção com `executeUpdate()`;
- verificação da quantidade de linhas afetadas pela operação;
- fechamento automático do `PreparedStatement` com `try-with-resources`;
- tratamento de exceções durante a inserção.

### Atualizando dados

Nesta aula, foi implementada a atualização do salário-base de um vendedor para praticar:

- criação de um comando `UPDATE` parametrizado com `PreparedStatement`;
- associação do valor do reajuste e do nome do vendedor com `setDouble()` e `setString()`;
- execução da atualização com `executeUpdate()`;
- verificação da quantidade de linhas afetadas;
- fechamento automático do `PreparedStatement` com `try-with-resources`;
- fechamento da conexão e tratamento de exceções durante a atualização.

O método `updateBaseSalarySeller()` acrescenta `9.0` ao salário-base do vendedor chamado `Flavinha Pessanha`. O comando executado equivale a:

```sql
UPDATE seller
SET BaseSalary = BaseSalary + 9.0
WHERE Name = 'Flavinha Pessanha';
```

Como o reajuste é somado ao valor atual, cada nova execução do método acrescenta outros `9.0` ao salário dos vendedores selecionados.

### Excluindo dados

Nesta aula, foi implementada a exclusão de um departamento específico para praticar:

- criação de um comando `DELETE` parametrizado com `PreparedStatement`;
- associação do identificador do departamento com `setInt()`;
- execução da exclusão com `executeUpdate()`;
- tratamento de violações de integridade referencial com `DbIntegrityException`.

O método `removeDepartment()` tenta remover o departamento de ID `5`. O comando executado equivale a:

```sql
DELETE FROM department
WHERE Id = 5;
```

Se o departamento estiver associado a registros de outras tabelas, como `seller`, o banco impedirá a exclusão e a aplicação lançará uma `DbIntegrityException`.

### Transações

Nesta aula, foi implementada uma transação para atualizar o salário-base dos vendedores de dois departamentos. O método `transactionBaseSalaryFromSeller()` permite praticar:

- desativação do commit automático com `setAutoCommit(false)`;
- execução de múltiplas operações como uma única unidade de trabalho;
- confirmação das alterações com `commit()`;
- cancelamento de todas as alterações com `rollback()` quando ocorre um erro;
- tratamento de uma possível `SQLException` durante o próprio rollback;
- fechamento automático do `Statement` com `try-with-resources`.

Durante a transação, são executados dois comandos equivalentes a:

```sql
UPDATE seller
SET BaseSalary = 2090
WHERE DepartmentId = 1;

UPDATE seller
SET BaseSalary = 3090
WHERE DepartmentId = 2;
```

Com o auto-commit desativado, as atualizações somente são confirmadas no banco após a chamada de `commit()`. Para testar o rollback, o método contém um trecho comentado que pode lançar uma `SQLException` entre as duas atualizações. Nesse cenário, o segundo comando não é executado e o `rollback()` desfaz a primeira atualização, preservando a consistência dos dados.

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
