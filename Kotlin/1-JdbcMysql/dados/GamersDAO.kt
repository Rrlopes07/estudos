package br.com.mygamesforum.games.dados

import br.com.mygamesforum.games.modelo.Gamer
import br.com.mygamesforum.games.utilitario.toEntity
import br.com.mygamesforum.games.utilitario.toModel
import javax.persistence.EntityManager

class GamersDAO(manager: EntityManager): DAO<Gamer, GamerEntity>(manager, GamerEntity::class.java) {

    override fun toEntity(objeto: Gamer): GamerEntity {
        return objeto.toEntity()
    }

    override fun toModel(objeto: GamerEntity): Gamer {
        return objeto.toModel()
    }

}