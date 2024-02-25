package br.com.mygamesforum.games.dados

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

object Banco {

    fun getEntityManager(): EntityManager {
        val factory: EntityManagerFactory = Persistence.createEntityManagerFactory("mygamesforum")
        return factory.createEntityManager()
    }

}