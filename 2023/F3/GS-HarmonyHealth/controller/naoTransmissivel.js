function registarPassos() {
    var passos = parseInt(document.getElementById("passos").value);

    if (passos >= 0) {
        var mensagem = "Ótimo! Você deu um passo em direção à saúde.";
        if (passos < 5000) {
            mensagem =
                "Lembre-se de caminhar mais para manter um estilo de vida saudável.";
        }
        document.getElementById("mensagem").innerText = mensagem;
    } else {
        document.getElementById("mensagem").innerText =
            "Por favor, insira um número válido de passos.";
    }
}