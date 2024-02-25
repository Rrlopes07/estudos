package br.com.mygamesforum.games.modelo

import java.math.BigDecimal

class PlanoAvulso(tipo: String, id: Int = 0): Plano(tipo, id) {

    override fun obterValor(aluguel: Aluguel): BigDecimal {
        var valorOriginal = super.obterValor(aluguel)

        if (aluguel.gamer.media > 8.0)
            valorOriginal -= valorOriginal.multiply(BigDecimal("0.1"))

        return valorOriginal
    }

    override fun toString(): String {
        return "Plano Avulso\n" +
                "Tipo: $tipo\n" +
                "Id: $id\n"
    }

}
