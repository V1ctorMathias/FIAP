-- DML

-- Inserindo dados na tabela Usuarios
INSERT INTO Usuarios (Nome, Email, Senha) VALUES ('João Pedro', 'jpcoelhomaran@yahoo.com.br', 'senha123');
INSERT INTO Usuarios (Nome, Email, Senha) VALUES ('Victor Porto', 'victormpporto@gmail.com', 'senha456');
INSERT INTO Usuarios (Nome, Email, Senha) VALUES ('Iago Bortolo', 'iagobortolo@hotmail.com', 'senha789');


-- Inserindo dados na tabela Tipos de Investimento
INSERT INTO TiposInvestimento (Descricao, Risco) VALUES ('Renda Fixa', 'Baixo');
INSERT INTO TiposInvestimento (Descricao, Risco) VALUES ('Ações', 'Alto');

-- Inserindo dados na tabela Investimentos
INSERT INTO Investimentos (UsuarioID, TipoID, Valor) VALUES
(1, 1, 5000.00);
INSERT INTO Investimentos (UsuarioID, TipoID, Valor) VALUES
(2, 2, 10000.00);


-- Atualizando um valor em Investimentos
UPDATE Investimentos
SET Valor = 6000.00
WHERE InvestimentoID = 1;

-- Excluindo registro da tabela LogsAcesso
DELETE FROM LogsAcesso
WHERE LogID = 1;

-- Consultando informações dos usuários e seus investimentos
SELECT Usuarios.Nome, Investimentos.Valor
    FROM Usuarios
INNER JOIN Investimentos ON Usuarios.UsuarioID = Investimentos.UsuarioID;