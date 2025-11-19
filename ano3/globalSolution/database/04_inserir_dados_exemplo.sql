-- ==========================================
-- 04 - DADOS DE EXEMPLO
-- ==========================================
USE HybridFlow;
GO

-- USUÁRIOS
INSERT INTO Usuario (nome, email, senha_hash, tipo_usuario)
VALUES 
('Administrador', 'admin@fiap.com', 'hash123', 'admin'),
('Victor', 'Victor@fiap.com', 'hash123', 'colaborador'),
('Iago', 'Iago@fiap.com', 'hash123', 'colaborador');
('Joao', 'Joao@fiap.com', 'hash123', 'colaborador');
('Ana Martins', 'ana.martins@fiap.com', 'hash123', 'colaborador'),
('Felipe Rocha', 'felipe.rocha@fiap.com', 'hash123', 'colaborador'),
('Ricardo Alves', 'ricardo.alves@fiap.com', 'hash123', 'admin');
GO

-- SALAS
INSERT INTO Sala (nome, capacidade_maxima, localizacao) VALUES
('Sala A1', 8, 'Andar 1'),
('Sala A2', 12, 'Andar 1'),
('Sala B1', 6, 'Andar 2'),
('Sala B2', 10, 'Andar 2'),
('Sala Reunião Central', 20, 'Andar 3'),
('Auditório Azul', 80, 'Andar 0'),
('Sala TI', 5, 'Andar -1'),
('Sala Criativa', 15, 'Andar 4');
GO

-- SENSORES 
INSERT INTO Sensor (id_sala, temperatura, ocupacao, atualizado_em) VALUES
(1, 22.1, 3, GETDATE()),
(2, 23.4, 5, GETDATE()),
(3, 21.9, 1, GETDATE()),
(4, 24.0, 2, GETDATE()),
(5, 20.8, 8, GETDATE()),
(6, 19.5, 20, GETDATE()),
(7, 22.7, 4, GETDATE()),
(8, 23.2, 7, GETDATE());

GO

-- RESERVAS
INSERT INTO Reserva (id_usuario, id_sala, data_reserva, horario_inicio, horario_fim, status) VALUES
(1, 1, '2025-02-01', '09:00', '10:00', 'ativa'),
(2, 2, '2025-02-01', '10:00', '11:30', 'cancelada'),
(3, 3, '2025-02-02', '14:00', '16:00', 'cancelada'),
(4, 4, '2025-02-03', '09:00', '10:30', 'ativa'),
(5, 5, '2025-02-03', '13:00', '15:00', 'ativa'),
(6, 1, '2025-02-04', '08:00', '09:00', 'cancelada'),
(7, 2, '2025-02-04', '09:30', '11:00', 'ativa'),
(8, 3, '2025-02-05', '15:00', '16:30', 'ativa'),
(9, 4, '2025-02-05', '10:00', '12:00', 'cancelada'),
(10, 5, '2025-02-06', '09:00', '11:00', 'ativa'),
(11, 6, '2025-02-06', '14:00', '16:00', 'ativa'),
(12, 7, '2025-02-07', '08:00', '09:30', 'cancelada'),
(3, 8, '2025-02-07', '10:00', '12:00', 'ativa'),
(4, 6, '2025-02-08', '09:00', '11:00', 'ativa'),
(5, 7, '2025-02-08', '14:00', '16:00', 'cancelada'),
(6, 8, '2025-02-09', '15:00', '17:00', 'ativa'),
(7, 1, '2025-02-10', '09:00', '10:00', 'ativa'),
(8, 2, '2025-02-10', '10:00', '12:00', 'ativa'),
(9, 3, '2025-02-11', '11:00', '12:00', 'ativa'),
(10, 4, '2025-02-11', '13:00', '14:00', 'cancelada');
GO
