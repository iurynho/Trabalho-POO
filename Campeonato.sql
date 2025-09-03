CREATE DATABASE IF NOT EXISTS Campeonato;
USE Campeonato;

-- Tabela Time
CREATE TABLE IF NOT EXISTS Time (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    pontos INT DEFAULT 0,
    golsMarcados INT DEFAULT 0,
    golsSofridos INT DEFAULT 0,
    saldoGols INT DEFAULT 0
);

-- Tabela Jogador
CREATE TABLE IF NOT EXISTS Jogador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    numero INT NOT NULL,
    posicao VARCHAR(50) NOT NULL,
    time_id INT,
    golsMarcados INT DEFAULT 0,
    assistencias INT DEFAULT 0,
    FOREIGN KEY (time_id) REFERENCES Time(id) ON DELETE CASCADE
);

-- Tabela Partida
CREATE TABLE IF NOT EXISTS Partida (
    id INT AUTO_INCREMENT PRIMARY KEY,
    timeCasa_id INT,
    timeVisitante_id INT,
    placarTimeCasa INT DEFAULT 0,
    placarTimeVisitante INT DEFAULT 0,
    FOREIGN KEY (timeCasa_id) REFERENCES Time(id) ON DELETE CASCADE,
    FOREIGN KEY (timeVisitante_id) REFERENCES Time(id) ON DELETE CASCADE
);
-- Inserir times
INSERT INTO Time (nome, cidade) VALUES ('Time A', 'Cidade A');
INSERT INTO Time (nome, cidade) VALUES ('Time B', 'Cidade B');

-- Inserir jogadores
INSERT INTO Jogador (nome, numero, posicao, time_id) VALUES ('Jogador 1', 10, 'Atacante', 1);
INSERT INTO Jogador (nome, numero, posicao, time_id) VALUES ('Jogador 2', 7, 'Meio-Campo', 1);
INSERT INTO Jogador (nome, numero, posicao, time_id) VALUES ('Jogador 3', 9, 'Atacante', 2);

-- Inserir partidas
INSERT INTO Partida (timeCasa_id, timeVisitante_id, placarTimeCasa, placarTimeVisitante) VALUES (1, 2, 2, 1);
INSERT INTO Partida (timeCasa_id, timeVisitante_id, placarTimeCasa, placarTimeVisitante) VALUES (2, 1, 0, 0);
-- Listar todos os times
SELECT * FROM Time;

-- Listar todos os jogadores de um time específico
SELECT * FROM Jogador WHERE time_id = 1;

-- Listar todas as partidas com os nomes dos times
SELECT 
    t1.nome AS TimeCasa, 
    t2.nome AS TimeVisitante, 
    p.placarTimeCasa, 
    p.placarTimeVisitante 
FROM Partida p
JOIN Time t1 ON p.timeCasa_id = t1.id
JOIN Time t2 ON p.timeVisitante_id = t2.id;


SELECT * FROM Time;

SELECT * FROM Jogador;

SELECT * FROM Jogador WHERE time_id = 1;

SELECT 
    t1.nome AS TimeCasa, 
    t2.nome AS TimeVisitante, 
    p.placarTimeCasa, 
    p.placarTimeVisitante 
FROM Partida p
JOIN Time t1 ON p.timeCasa_id = t1.id
JOIN Time t2 ON p.timeVisitante_id = t2.id;
SELECT 
    nome, 
    pontos, 
    saldoGols, 
    golsMarcados 
FROM Time 
ORDER BY pontos DESC, saldoGols DESC, golsMarcados DESC;
SELECT 
    j.nome AS NomeJogador, 
    t.nome AS Time, 
    j.golsMarcados, 
    j.assistencias 
FROM Jogador j
JOIN Time t ON j.time_id = t.id;
INSERT INTO Time (nome, cidade) VALUES ('Time C', 'Cidade C');
INSERT INTO Jogador (nome, numero, posicao, time_id) 
VALUES ('Jogador 4', 11, 'Atacante', 1);
INSERT INTO Partida (timeCasa_id, timeVisitante_id, placarTimeCasa, placarTimeVisitante) 
VALUES (1, 2, 3, 1);
UPDATE Time 
SET pontos = 10 
WHERE id = 1;
UPDATE Jogador 
SET golsMarcados = 5 
WHERE id = 1;
UPDATE Partida 
SET placarTimeCasa = 2, placarTimeVisitante = 2 
WHERE id = 1;
DELETE FROM Time WHERE id = 1;
DELETE FROM Jogador WHERE id = 1;
DELETE FROM Partida WHERE id = 1;
SELECT 
    t.nome AS Time, 
    SUM(p.placarTimeCasa) AS GolsMarcadosCasa, 
    SUM(p2.placarTimeVisitante) AS GolsMarcadosFora, 
    (SUM(p.placarTimeCasa) + SUM(p2.placarTimeVisitante)) AS TotalGolsMarcados
FROM Time t
LEFT JOIN Partida p ON t.id = p.timeCasa_id
LEFT JOIN Partida p2 ON t.id = p2.timeVisitante_id
GROUP BY t.id;
SELECT 
    t.nome AS Time,
    SUM(CASE WHEN p.placarTimeCasa > p.placarTimeVisitante THEN 1 ELSE 0 END) AS VitoriasCasa,
    SUM(CASE WHEN p.placarTimeCasa < p.placarTimeVisitante THEN 1 ELSE 0 END) AS DerrotasCasa,
    SUM(CASE WHEN p.placarTimeCasa = p.placarTimeVisitante THEN 1 ELSE 0 END) AS EmpatesCasa,
    SUM(CASE WHEN p2.placarTimeVisitante > p2.placarTimeCasa THEN 1 ELSE 0 END) AS VitoriasFora,
    SUM(CASE WHEN p2.placarTimeVisitante < p2.placarTimeCasa THEN 1 ELSE 0 END) AS DerrotasFora,
    SUM(CASE WHEN p2.placarTimeVisitante = p2.placarTimeCasa THEN 1 ELSE 0 END) AS EmpatesFora
FROM Time t
LEFT JOIN Partida p ON t.id = p.timeCasa_id
LEFT JOIN Partida p2 ON t.id = p2.timeVisitante_id
GROUP BY t.id;
SELECT 
    nome, 
    golsMarcados 
FROM Jogador 
ORDER BY golsMarcados DESC 
LIMIT 1;
SELECT 
    j.nome AS NomeJogador, 
    t.nome AS Time 
FROM Jogador j
JOIN Time t ON j.time_id = t.id;
SELECT 
    t1.nome AS TimeCasa, 
    t2.nome AS TimeVisitante, 
    p.placarTimeCasa, 
    p.placarTimeVisitante 
FROM Partida p
JOIN Time t1 ON p.timeCasa_id = t1.id
JOIN Time t2 ON p.timeVisitante_id = t2.id;

SELECT 
    t.nome AS Time, 
    COUNT(j.id) AS NumeroJogadores 
FROM Time t
LEFT JOIN Jogador j ON t.id = j.time_id
GROUP BY t.id;
SELECT 
    AVG(placarTimeCasa + placarTimeVisitante) AS MediaGolsPorPartida 
FROM Partida;
SELECT 
    nome, 
    golsMarcados 
FROM Jogador 
WHERE golsMarcados > (SELECT AVG(golsMarcados) FROM Jogador);