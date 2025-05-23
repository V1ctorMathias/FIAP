function verificarVacinas() {
    var idade = parseInt(document.getElementById('idade').value);
    var vacinas = parseInt(document.getElementById('vacinas').value);

    if (idade >= 0 && vacinas >= 0) {
        var vacinasRecomendadas = idade * 2;
        var status = vacinas >= vacinasRecomendadas ? 'Está protegido' : 'Precisa de mais vacinas';
        document.getElementById('status').innerText = 'Status de Vacinação: ' + status;
    } else {
        document.getElementById('status').innerText = 'Por favor, preencha todos os campos corretamente.';
    }
}