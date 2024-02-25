package br.com.mygamesforum.games.dados

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "jogos")
class JogoEntity(
    val titulo: String = "Título do jogo",
    val capa: String = "Capa do jogo",
    val descricao: String? = null,
    val preco: BigDecimal = BigDecimal("0.0"),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0) {
}