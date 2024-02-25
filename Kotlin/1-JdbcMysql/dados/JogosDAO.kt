package br.com.mygamesforum.games.dados

import br.com.mygamesforum.games.modelo.Jogo
import br.com.mygamesforum.games.utilitario.toEntity
import br.com.mygamesforum.games.utilitario.toModel
import javax.persistence.EntityManager

class JogosDAO(manager: EntityManager): DAO<Jogo, JogoEntity>(manager, JogoEntity::class.java) {

    override fun toEntity(objeto: Jogo): JogoEntity {
        return objeto.toEntity()
    }

    override fun toModel(objeto: JogoEntity): Jogo {
        return objeto.toModel()
    }

}