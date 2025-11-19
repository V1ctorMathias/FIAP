USE HybridFlow;
GO

-- LOGIN
SELECT id_usuario, nome, email, tipo_usuario
FROM Usuario
WHERE email = @email AND senha_hash = @senha;

-- CRIAR USUARIO
INSERT INTO Usuario (nome, email, senha_hash, tipo_usuario)
VALUES (@nome, @email, @senha_hash, @tipo_usuario);

-- LISTAR USUÁRIOS
SELECT id_usuario, nome, email, tipo_usuario
FROM Usuario;

-- LISTAR RESERVAS
SELECT 
    r.id_reserva,
    u.nome AS usuario,
    s.nome AS sala,
    r.data_reserva,
    r.horario_inicio,
    r.horario_fim,
    r.status
FROM Reserva r
JOIN Usuario u ON u.id_usuario = r.id_usuario
JOIN Sala s ON s.id_sala = r.id_sala;

-- CRIAR RESERVA
INSERT INTO Reserva (id_usuario, id_sala, data_reserva, horario_inicio, horario_fim)
VALUES (@id_usuario, @id_sala, @data_reserva, @horario_inicio, @horario_fim);
