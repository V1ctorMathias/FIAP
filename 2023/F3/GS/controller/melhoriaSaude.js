function registrarHidratacao() {
    var coposAgua = parseInt(document.getElementById('coposAgua').value);

    if (coposAgua >= 0) {
        var mensagem = 'Ótimo trabalho! Mantenha-se hidratado para promover a saúde.';
        if (coposAgua < 8) {
            mensagem = 'Lembre-se de beber mais água para garantir uma hidratação adequada.';
        }
        document.getElementById('mensagemHidratacao').innerText = mensagem;
    } else {
        document.getElementById('mensagemHidratacao').innerText = 'Por favor, insira um número válido de copos de água.';
    }
}