
USE HybridFlow;
GO

CREATE TABLE Usuario (
    id_usuario INT IDENTITY(1,1) PRIMARY KEY,
    nome NVARCHAR(100) NOT NULL,
    email NVARCHAR(120) NOT NULL UNIQUE,
    senha_hash NVARCHAR(300) NOT NULL,
    tipo_usuario VARCHAR(20) NOT NULL CHECK (tipo_usuario IN ('admin', 'colaborador'))
);
GO

CREATE TABLE Sala (
    id_sala INT IDENTITY(1,1) PRIMARY KEY,
    nome NVARCHAR(80) NOT NULL,
    capacidade_maxima INT NOT NULL,
    localizacao NVARCHAR(120) NULL
);
GO

CREATE TABLE Sensor (
    id_sensor INT IDENTITY(1,1) PRIMARY KEY,
    id_sala INT NOT NULL,
    temperatura DECIMAL(5,2),
    ocupacao INT,
    atualizado_em DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (id_sala) REFERENCES Sala(id_sala)
);
GO

CREATE TABLE Reserva (
    id_reserva INT IDENTITY(1,1) PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_sala INT NOT NULL,
    data_reserva DATE NOT NULL,
    horario_inicio TIME NOT NULL,
    horario_fim TIME NOT NULL,
    status VARCHAR(20) DEFAULT 'ativa',
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_sala) REFERENCES Sala(id_sala)
);
GO
