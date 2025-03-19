-- Criando a tabela Usuarios
CREATE TABLE Usuarios (
    UsuarioID INT PRIMARY KEY,
    Nome VARCHAR(100),
    Email VARCHAR(100),
    Senha VARCHAR(255)
);

-- Auto incremento do id
CREATE SEQUENCE usuarios_seq
START WITH 1
INCREMENT BY 1
NOCACHE;
CREATE OR REPLACE TRIGGER trg_usuarios_id
BEFORE INSERT ON Usuarios
FOR EACH ROW
BEGIN
    IF :NEW.UsuarioID IS NULL THEN
        SELECT usuarios_seq.NEXTVAL INTO :NEW.UsuarioID FROM dual;
    END IF;
END;



-- Criando a tabela Tipos de Investimento
CREATE TABLE TiposInvestimento (
    TipoID INT PRIMARY KEY,
    Descricao VARCHAR(50),
    Risco VARCHAR(50)
);

-- Auto incremento do id
CREATE SEQUENCE tipoInvestimento_seq
START WITH 1
INCREMENT BY 1
NOCACHE;
CREATE OR REPLACE TRIGGER trg_tipoinvestimento_id
BEFORE INSERT ON TiposInvestimento
FOR EACH ROW
BEGIN
    IF :NEW.TipoID IS NULL THEN
        SELECT tipoInvestimento_seq.NEXTVAL INTO :NEW.TipoID FROM dual;
    END IF;
END;



-- Criando a tabela Investimentos
CREATE TABLE Investimentos (
    InvestimentoID INT PRIMARY KEY,
    UsuarioID INT,
    TipoID INT,
    Valor DECIMAL(10, 2),
    DataInvestimento DATE,
    FOREIGN KEY (UsuarioID) REFERENCES Usuarios(UsuarioID),
    FOREIGN KEY (TipoID) REFERENCES TiposInvestimento(TipoID)
);

-- Auto incremento do id
CREATE SEQUENCE investimen_seq
START WITH 1
INCREMENT BY 1
NOCACHE;
CREATE OR REPLACE TRIGGER trg_investimento_id
BEFORE INSERT ON Investimentos
FOR EACH ROW
BEGIN
    IF :NEW.InvestimentoID IS NULL THEN
        SELECT investimen_seq.NEXTVAL INTO :NEW.InvestimentoID FROM dual;
    END IF;
END;
