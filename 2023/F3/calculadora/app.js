let n1, n2, resultado, operacaoAtual;

function exibirTextoNaTela(tag, texto) {
  let campo = document.querySelector(tag);
  campo.innerHTML = texto;
}

function atribuirValor() {
  n1 = parseFloat(document.getElementById("n1").value);
  n2 = parseFloat(document.getElementById("n2").value);
}

function validarCampo(operacao) {
  operacaoAtual = operacao;
  if (isNaN(n1) || isNaN(n2)) {
    exibirTextoNaTela("p", "Dados em branco, favor digitar algo!");
    return false;
  } else if (n2 === 0 && operacao === "dividir") {
    exibirTextoNaTela("p", "Não há divisão por zero");
    return false;
  }
  return true;
}

function exibirResultado() {
  exibirTextoNaTela("p", `${resultado}`);
}

function somar() {
  atribuirValor();
  if (validarCampo("somar")) {
    resultado = n1 + n2;
    exibirResultado();
  }
}

function subtrair() {
  atribuirValor();
  if (validarCampo("subtrair")) {
    resultado = n1 - n2;
    exibirResultado();
  }
}

function multiplicar() {
  atribuirValor();
  if (validarCampo("multiplicar")) {
    resultado = n1 * n2;
    exibirResultado();
  }
}

function dividir() {
  atribuirValor();
  if (validarCampo("dividir")) {
    resultado = n1 / n2;
    exibirResultado();
  }
}
