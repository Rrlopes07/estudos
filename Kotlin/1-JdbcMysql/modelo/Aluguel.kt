package br.com.mygamesforum.games.modelo

data class Aluguel(
    val gamer: Gamer,
    val jogo: Jogo,
    val periodo: Periodo) {

    var id = 0
    val valorDoAluguel = gamer.plano.obterValor(this)

    override fun toString(): String {
        return "Aluguel do jogo ${jogo.titulo} por ${gamer.nome}, no valor de R$$valorDoAluguel"
    }

}
