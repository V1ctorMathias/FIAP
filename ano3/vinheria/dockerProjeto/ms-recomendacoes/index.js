const express = require('express');
const app = express();

app.get('/recomendacoes', (req, res) => {
  const auth = req.headers['authorization'];
  if (!auth) return res.status(401).json({ erro: 'Token necessario' });
  res.json({ vinhos: ['Malbec 2021', 'Chardonnay 2022'] });
});

app.listen(3002, () => console.log('ms-recomendacoes rodando na porta 3002'));