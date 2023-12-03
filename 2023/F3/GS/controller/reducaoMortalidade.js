function calcularIMC() {
    var idade = parseFloat(document.getElementById('idade').value);
    var peso = parseFloat(document.getElementById('peso').value);
    var altura = parseFloat(document.getElementById('altura').value);

    if (idade && peso && altura) {
        var imc = peso / (altura * altura);
        document.getElementById('resultado').innerText = 'IMC: ' + imc.toFixed(2);
    } else {
        document.getElementById('resultado').innerText = 'Por favor, preencha todos os campos.';
    }
}