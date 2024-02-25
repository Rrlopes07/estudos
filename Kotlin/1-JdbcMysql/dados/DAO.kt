package br.com.mygamesforum.games.dados

import javax.persistence.EntityManager

abstract class DAO<T, E>(protected val manager: EntityManager,
                         protected val entityType: Class<E>) {

    abstract fun toEntity(objeto: T): E
    abstract fun toModel(objeto: E): T

    open fun getList(): List<T> {
        val query = manager.createQuery("FROM ${entityType.simpleName}", entityType)
        return query.resultList.map { entity -> toModel(entity) }
    }

    open fun add(objeto: T) {
        val entity = toEntity(objeto)
        manager.transaction.begin()
        manager.persist(entity)
        manager.transaction.commit()
    }

    open fun findById(id: Int): T {
        val query = manager.createQuery("FROM ${entityType.simpleName} WHERE id=:id", entityType)
        query.setParameter("id", id)
        val entity = query.singleResult

        return toModel(entity)
    }

    open fun remove(id: Int) {
        val query = manager.createQuery("FROM ${entityType.simpleName} WHERE id=:id", entityType)
        query.setParameter("id", id)
        val entity = query.singleResult

        manager.transaction.begin()
        manager.remove(entity)
        manager.transaction.commit()
    }

}