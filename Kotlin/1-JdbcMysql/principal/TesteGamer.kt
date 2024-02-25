package br.com.mygamesforum.games.principal

import br.com.mygamesforum.games.modelo.Gamer

fun main() {
    val gamer1 = Gamer("Joaozinho", "joao@email.com")
    println(gamer1)
    val gamer2 = Gamer("Joaquina",
        "joaquina@email.com",
        "22/08/1997",
        "joaquinazica")
    println(gamer2)

    gamer1.let {
        it.dataNascimento = "26/11/2003"
        it.usuario = "joaozinho"
    }.also {
        println(gamer1.idInterno)
    }
}