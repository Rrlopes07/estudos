package br.com.mygamesforum.games.principal

import br.com.mygamesforum.games.dados.AluguelDAO
import br.com.mygamesforum.games.dados.Banco
import br.com.mygamesforum.games.dados.GamersDAO
import br.com.mygamesforum.games.dados.JogosDAO
import br.com.mygamesforum.games.modelo.Periodo
import java.time.LocalDate

fun main() {
   val manager = Banco.getEntityManager()

    val jogoDao = JogosDAO(manager)
    val gamerDao = GamersDAO(manager)
    val aluguelDao = AluguelDAO(manager)

    val gamer = gamerDao.findById(4)
    val jogo = jogoDao.findById(2)
    val aluguel = gamer.alugaJogo(jogo, Periodo(LocalDate.now(), LocalDate.now().plusDays(7)))

    aluguelDao.add(aluguel)

    val listaAlugel = aluguelDao.getList()
    println(listaAlugel)

    manager.close()
}