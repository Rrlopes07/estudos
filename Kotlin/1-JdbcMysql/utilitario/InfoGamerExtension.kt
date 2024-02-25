package br.com.mygamesforum.games.utilitario

import br.com.mygamesforum.games.modelo.Gamer
import br.com.mygamesforum.games.modelo.InfoGamerJson

fun InfoGamerJson.criaGamer(): Gamer {
    return Gamer(this.nome, this.email, this.dataNascimento, this.usuario)
}