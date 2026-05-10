const express = require('express');
const app = express();

app.get('/qualidade', (req, res) => {
  res.json({ temperatura: 14.5, umidade: 68, status: 'OK' });
});

app.listen(3001, () => console.log('ms-qualidade rodando na porta 3001'));