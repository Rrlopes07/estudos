package br.com.mygamesforum.games.utilitario

import br.com.mygamesforum.games.dados.JogoEntity
import br.com.mygamesforum.games.modelo.Jogo

fun Jogo.toEntity(): JogoEntity {
    return JogoEntity(this.titulo, this.capa, this.descricao, this.preco, this.id)
}

fun JogoEntity.toModel(): Jogo {
    return Jogo(this.titulo, this.capa, this.descricao, this.preco, this.id)
}