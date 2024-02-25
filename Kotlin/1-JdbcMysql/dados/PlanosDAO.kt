package br.com.mygamesforum.games.dados

import br.com.mygamesforum.games.modelo.Plano
import br.com.mygamesforum.games.utilitario.toEntity
import br.com.mygamesforum.games.utilitario.toModel
import javax.persistence.EntityManager

class PlanosDAO(manager: EntityManager): DAO<Plano, PlanoEntity>(manager, PlanoEntity::class.java) {

    override fun toEntity(objeto: Plano): PlanoEntity {
        return objeto.toEntity()
    }

    override fun toModel(objeto: PlanoEntity): Plano {
        return objeto.toModel()
    }

}