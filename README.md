# 🏆 Projeto Campeonato de Futebol - POO

Este projeto foi desenvolvido para a disciplina de **Programação Orientada a Objetos (POO)**.  
Ele implementa um sistema simples de gerenciamento de campeonato de futebol, utilizando **Java** e **MySQL**.

---

## 📌 Estrutura do Projeto

- `src/trabalho/`
  - `Main.java` → Classe principal (ponto de entrada do sistema).  
  - `Conexao.java` → Classe responsável pela conexão com o banco de dados via JDBC.  
  - `Time.java` → Classe de modelo para os times.  
  - `Jogador.java` → Classe de modelo para os jogadores.  
  - `Partida.java` → Classe de modelo para as partidas.  
  - `Campeonato.java` → Classe para regras/gestão do campeonato.  

- `Campeonato.sql` → Script para criação e povoamento do banco de dados no MySQL.  

---

## ⚙️ Tecnologias Utilizadas
- Java 8+  
- NetBeans (projeto configurado com **Ant**)  
- MySQL / MariaDB  
- JDBC  

---

## 🚀 Como Executar o Projeto

### 1. Configurar o Banco de Dados
1. Abra seu gerenciador de banco de dados (MySQL Workbench, DBeaver ou phpMyAdmin).  
2. Execute o script `Campeonato.sql`.  
   - Isso vai criar o banco de dados `Campeonato` com as tabelas `Time`, `Jogador` e `Partida`, além de inserir alguns dados de exemplo.  

### 2. Importar o Projeto no NetBeans
1. Abra o **NetBeans**.  
2. Vá em **File > Open Project...**  
3. Selecione a pasta `Trabalho`.  
4. Aguarde o NetBeans carregar as dependências do projeto.  

### 3. Configurar a Conexão com o Banco
- No arquivo `Conexao.java`, ajuste os parâmetros de conexão se necessário:
  ```java
  private static final String URL = "jdbc:mysql://localhost:3306/Campeonato";
  private static final String USER = "root";
  private static final String PASSWORD = "sua_senha";
