package br.com.mygamesforum.games.modelo

import br.com.mygamesforum.games.utilitario.formatoComDuasCasasDecimais
import com.google.gson.annotations.Expose
import java.math.BigDecimal

data class Jogo(
    @Expose val titulo: String,
    @Expose val capa: String): Recomendavel {

    var id = 0
    var preco = BigDecimal("0.0")
    var descricao: String? = null
    private val listaNotas = mutableListOf<Int>()
    override val media: Double
        get() = listaNotas.average().formatoComDuasCasasDecimais()

    constructor(titulo: String, capa: String, descricao: String?, preco: BigDecimal, id: Int = 0):
            this(titulo, capa) {
                this.descricao = descricao
                this.preco = preco
                this.id = id
            }

    override fun recomendar(nota: Int) {
        if (nota < 1 || nota > 10)
            println("Nota inválida")
        else
            listaNotas.add(nota)
    }

    override fun toString(): String {
        return "Meu Jogo: \n" +
                "Título: $titulo \n" +
                "Capa: $capa \n" +
                "Descricao: $descricao \n" +
                "Preço: $preco \n" +
                "Reputação: $media\n" +
                "Id: $id\n"
    }

}