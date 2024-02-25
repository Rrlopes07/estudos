package br.com.mygamesforum.games.principal

import br.com.mygamesforum.games.modelo.Gamer
import br.com.mygamesforum.games.modelo.Jogo
import br.com.mygamesforum.games.services.ConsumoApi
import br.com.mygamesforum.games.utilitario.transformarEmIdade
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Idade do gamer: ${gamer.dataNascimento?.transformarEmIdade()}")

    do {
        println("Digite um código de jogo para buscar: ")
        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()
//        val meuInfoJogo = buscaApi.buscaJogo(busca)

        var meuJogo: Jogo? = null

        val resultado = runCatching {
//            meuJogo = Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb)
        }

        resultado.onFailure { println("Jogo inexistente, tente outro id.") }
        resultado.onSuccess {
            println("Deseja inserir uma descrição personalizada? S/N")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)) {
                println("Insira a descrição personalizada para o jogo: ")
                val descricaoPersonalizada = leitura.nextLine()
//                meuJogo?.descricao = descricaoPersonalizada
            } else {
//                meuJogo?.descricao = meuJogo?.titulo
            }

            gamer.jogosBuscados.add(meuJogo)
        }

        println("Você deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()
    } while (resposta.equals("s", true))

    println("Jogos ordenados por titulo: ")
    gamer.jogosBuscados.sortBy { it?.titulo }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }

    gamer.jogosBuscados.forEach { println("Titulo: ${it?.titulo}") }

    println(jogosFiltrados)

    println("Deseja excluir algum jogo da lista original? S/N")
    val desejaRemover = leitura.nextLine()
    if (desejaRemover.equals("s", true)) {
        println("Informe a posição do jogo que deseja excluir: ")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("Busca finalizada com sucesso")
}