package br.com.mygamesforum.games.utilitario

import br.com.mygamesforum.games.dados.GamerEntity
import br.com.mygamesforum.games.modelo.Gamer

fun Gamer.toEntity(): GamerEntity {
    return GamerEntity(this.id, this.nome, this.email, this.dataNascimento, this.usuario, this.plano.toEntity())
}

fun GamerEntity.toModel(): Gamer {
    return Gamer(this.nome, this.email, this.dataNascimento, this.usuario, this.id)
}