USE HybridFlow;
GO

CREATE INDEX idx_reserva_usuario ON Reserva(id_usuario);
GO

CREATE INDEX idx_reserva_sala ON Reserva(id_sala);
GO

CREATE INDEX idx_sensor_sala ON Sensor(id_sala);
GO
