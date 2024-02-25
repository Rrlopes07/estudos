package br.com.mygamesforum.games.modelo

import java.math.BigDecimal

class PlanoAssinatura(
    tipo: String,
    val mensalidade: Double,
    val jogosIncluidos: Int,
    val percentualDescontoReputacao: BigDecimal,
    id: Int = 0): Plano(tipo, id) {

    override fun obterValor(aluguel: Aluguel): BigDecimal {
        val totalJogosNoMes = aluguel.gamer.jogosDoMes(aluguel.periodo.dataInicial.monthValue).size + 1

        return if (totalJogosNoMes <= jogosIncluidos)
            BigDecimal("0.0")
        else {
            var valorOriginal = super.obterValor(aluguel)

            if (aluguel.gamer.media > 8.0)
                valorOriginal -= valorOriginal.multiply(percentualDescontoReputacao)

            valorOriginal
        }

    }

    override fun toString(): String {
        return "Plano Assinatura\n" +
                "Tipo: $tipo\n" +
                "Id: $id\n" +
                "Mensalidade: $mensalidade\n" +
                "Jogos Incluidos: $jogosIncluidos\n" +
                "Percentual Desconto Reputacao: $percentualDescontoReputacao\n"
    }

}
