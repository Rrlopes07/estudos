package br.com.mygamesforum.games.utilitario

import br.com.mygamesforum.games.modelo.InfoJogoJson
import br.com.mygamesforum.games.modelo.Jogo
import java.math.BigDecimal

fun InfoJogoJson.criaJogo(): Jogo {
    return Jogo(this.titulo, this.capa, this.descricao, BigDecimal(this.preco.toString()))
}