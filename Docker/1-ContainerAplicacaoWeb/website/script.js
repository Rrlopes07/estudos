// Variaveis dos links
const inicio = document.querySelector("#link-inicio");
const formacao = document.querySelector("#link-formacao");
const expProfissional = document.querySelector("#link-exp-profissional");
const stacks = document.querySelector("#link-stacks");
const certificacoes = document.querySelector("#link-certificacoes");
const contato = document.querySelector("#link-contato");

// Variaveis dos containeres
let seletorApresentacao = document.querySelector("#apresentacao");
let seletorFormacao = document.querySelector("#formacao");
let seletorExpProfissional = document.querySelector("#exp-profissional");
let seletorStacks = document.querySelector("#stacks");
let seletorCertificacoes = document.querySelector("#certificacoes");
let seletorRedesSociais = document.querySelector("#redes-sociais");
let seletorContato = document.querySelector("#contato");

// Adiciona os eventos aos links
inicio.addEventListener("click", () => {
    seletorApresentacao.style.display = "block";
    seletorFormacao.style.display = "none";
    seletorExpProfissional.style.display = "none";
    seletorStacks.style.display = "none";
    seletorCertificacoes.style.display = "none";
    seletorRedesSociais.style.display = "none";
    seletorContato.style.display = "none";
})

formacao.addEventListener("click", () => {
    seletorApresentacao.style.display = "none";
    seletorFormacao.style.display = "block";
    seletorExpProfissional.style.display = "none";
    seletorStacks.style.display = "none";
    seletorCertificacoes.style.display = "none";
    seletorRedesSociais.style.display = "none";
    seletorContato.style.display = "none";
})

expProfissional.addEventListener("click", () => {
    seletorApresentacao.style.display = "none";
    seletorFormacao.style.display = "none";
    seletorExpProfissional.style.display = "block";
    seletorStacks.style.display = "none";
    seletorCertificacoes.style.display = "none";
    seletorRedesSociais.style.display = "none";
    seletorContato.style.display = "none";
})

stacks.addEventListener("click", () => {
    seletorApresentacao.style.display = "none";
    seletorFormacao.style.display = "none";
    seletorExpProfissional.style.display = "none";
    seletorStacks.style.display = "block";
    seletorCertificacoes.style.display = "none";
    seletorRedesSociais.style.display = "none";
    seletorContato.style.display = "none";
})

certificacoes.addEventListener("click", () => {
    seletorApresentacao.style.display = "none";
    seletorFormacao.style.display = "none";
    seletorExpProfissional.style.display = "none";
    seletorStacks.style.display = "none";
    seletorCertificacoes.style.display = "block";
    seletorRedesSociais.style.display = "none";
    seletorContato.style.display = "none";
})

contato.addEventListener("click", () => {
    seletorApresentacao.style.display = "none";
    seletorFormacao.style.display = "none";
    seletorExpProfissional.style.display = "none";
    seletorStacks.style.display = "none";
    seletorCertificacoes.style.display = "none";
    seletorRedesSociais.style.display = "flex";
    seletorContato.style.display = "flex";
})