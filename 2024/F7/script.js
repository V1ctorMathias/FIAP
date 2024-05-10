// REFERENTE AO CONSUMO
const url = "https://api-stg-catalogo.redeancora.com.br/superbusca/api/integracao/catalogo/v2/produtos/query/sumario";
const token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IkRFQkJDQUJBMjIwQjRGOTVDOTA5NTNFMURBMTlENEUzQzFDRDFGRDciLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiIzcnZLdWlJTFQ1WEpDVlBoMmhuVTQ4SE5IOWMifQ.eyJuYmYiOjE3MTUyMTc2NjIsImV4cCI6MTcxNTMwNDA2MiwiaXNzIjoiaHR0cHM6Ly9zc28tY2F0YWxvZ28ucmVkZWFuY29yYS5jb20uYnIiLCJhdWQiOiJTZWFyY2hFbmdpbmVBcGkuc3RnIiwiY2xpZW50X2lkIjoiNjV0dmg2cnZuNGQ3dWVyM2hxcW0ycDhrMnB2bm01d3giLCJyb2xlIjoicmVhZCIsInNjb3BlIjpbInNlYXJjaGVuZ2luZWFwaS5zdGciXX0.j_6T-aGDge8VxQTuTOLAk0ZATkLSAq5b77jypeJMI75hDEYqlwNgPS-aBKETUREP-PW6YcTNRv7eMf-abwjNVj6t8Ik-ZXe80foZw_Mpqv10mB4MpLTMT2dvhoN4Hhfh4AUK8NwuB51xOSn-pz-1wvwathRTo0jOA4_Sy5705VN6pLZvfJN5VZrPCetl2OhAH-dTKm7wx1VgL4hEs6dKUc2449zW7ModI8Gpwbten4fvN57aOP7gqa0J3dVXm9BObEZj2-RJ6HvHuJQoNDPmrFt-XyklO6T5Q2w3RetFIi54mLFPExdnTnjry-5krp5IyzC9vDrNl6bBo9_iVjToeNkEP3-VaJed5D9CJLrDh_ZDVdwwOfeoMWIaLI5e0E4aln3166UCC8oOv0L1kgLhzRBFT8gz5ji6bvkT0niDMqjma2sr8wxzs3ILEwVVm8OaKvdEw3PGSXqHvSBhmJb8E5071f0LCQLKrKgI-JLgzOp7TH5xtoqBytRls2r2Ukuty68Wtj3aHYGr7bpCVhVVUIoWgHx1-b-biqF_0VCrnu8P3wVQZ1rf3RlnVf8zLBxdzn0nE2Ow-CD5Oa8pMJb2-HFasStJQvVUqKlSngtK5NaSz4f6x-7wBUG04MNZ0K8beDad7DWowLsUmpzqnfoX8qdCg8LQWXcXkYd3YkhTqDo";
const tokenType = "Bearer";

// CAPTURA DOS OBJETOS DO HTML
const formulario = document.querySelector('#formulario');
const inputBuscar = document.querySelector('#inputBuscar');
const listaOpcoes = document.querySelector('.produtos')

lstOpcoes = [];

// CONSULTA DA API
async function buscarProduto(pesquisa) {
    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `${tokenType} ${token}`,
            },
            body: JSON.stringify({
                veiculoFiltro: {
                    veiculoPlaca: "DME8I14",
                },
                superbusca: `${pesquisa}`,
                pagina: 0,
                itensPorPagina: 9,
            }),
        });
        // reset lista
        lstOpcoes = [];
        const data = await response.json();
        for (let item of data['pageResult']['data']) {
            lstOpcoes.push(item)
        }
        console.log("Resposta da solicitação:", lstOpcoes);
    } catch (error) {
        console.error("Erro ao fazer a solicitação:" + error);
    }
};


function mostrarOpcoes() {
    // reset innerHTML
    listaOpcoes.innerHTML = ''
    let count = 0;
    if (lstOpcoes.length > 0) {
        for (let i of lstOpcoes) {
            count += 1;
            listaOpcoes.innerHTML += `<div${count}>${i['cna']}</div>`;
        }
    }
}

formulario.addEventListener('submit', (e) => {
    buscarProduto(inputBuscar.value);
    mostrarOpcoes();
    e.preventDefault();
})