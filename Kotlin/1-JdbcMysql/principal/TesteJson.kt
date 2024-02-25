package br.com.mygamesforum.games.principal

import br.com.mygamesforum.games.modelo.Periodo
import br.com.mygamesforum.games.modelo.PlanoAssinatura
import br.com.mygamesforum.games.services.ConsumoApi
import com.google.gson.GsonBuilder
import java.io.File
import java.math.BigDecimal
import java.time.LocalDate

fun main() {
    val consumo = ConsumoApi()
    val listaGamers = consumo.buscaGamers()
    val listaJogoJson = consumo.buscaJogosJson()

    val gamerCamila = listaGamers.get(5)
    gamerCamila.plano = PlanoAssinatura("PRATA", 9.90, 3, BigDecimal("0.15"))

    val periodo = Periodo(LocalDate.now(), LocalDate.now().plusDays(7))

    val jogoResidentVillage = listaJogoJson.get(10)
    val jogoSpider = listaJogoJson.get(13)
    val jogoTheLastOfUs = listaJogoJson.get(2)
    val jogoDandara = listaJogoJson.get(5)
    val jogoAssassins = listaJogoJson.get(4)
    val jogoCyber = listaJogoJson.get(6)
    val jogoGod = listaJogoJson.get(7)
    val jogoSkyrim = listaJogoJson.get(18)

    gamerCamila.recomendar(10)
    gamerCamila.recomendar(8)
    gamerCamila.recomendar(9)

    gamerCamila.alugaJogo(jogoSpider, periodo)
    gamerCamila.alugaJogo(jogoTheLastOfUs, periodo)
    gamerCamila.alugaJogo(jogoResidentVillage, periodo)
    gamerCamila.alugaJogo(jogoSpider,periodo)

//    gamerCamila.jogosAlugados.forEach { println(it) }

    gamerCamila.recomendarJogo(jogoResidentVillage, 7)
    gamerCamila.recomendarJogo(jogoTheLastOfUs, 10)
    gamerCamila.recomendarJogo(jogoAssassins, 8)
    gamerCamila.recomendarJogo(jogoCyber, 7)
    gamerCamila.recomendarJogo(jogoGod, 10)
    gamerCamila.recomendarJogo(jogoDandara, 8)
    gamerCamila.recomendarJogo(jogoSkyrim, 8)
    gamerCamila.recomendarJogo(jogoSpider, 6)

    val gson = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()
    val serializacao = gson.toJson(gamerCamila.jogosRecomendados)

    val arquivo = File("jogosRecomendados-${gamerCamila.nome}.json")
    arquivo.writeText(serializacao)
    println(arquivo.absolutePath)
}