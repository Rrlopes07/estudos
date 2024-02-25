package br.com.mygamesforum.games.dados

import br.com.mygamesforum.games.modelo.Periodo
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "aluguel")
class AluguelEntity(
    @ManyToOne
    val gamer: GamerEntity = GamerEntity(),

    @ManyToOne
    val jogo: JogoEntity = JogoEntity(),

    @Embedded
    val periodo: Periodo = Periodo()
) {

    var valorDoAluguel = BigDecimal(0.0)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0

}