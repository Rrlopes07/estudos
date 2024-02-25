package br.com.mygamesforum.games.utilitario

import br.com.mygamesforum.games.dados.PlanoAssinaturaEntity
import br.com.mygamesforum.games.dados.PlanoAvulsoEntity
import br.com.mygamesforum.games.dados.PlanoEntity
import br.com.mygamesforum.games.modelo.Plano
import br.com.mygamesforum.games.modelo.PlanoAssinatura
import br.com.mygamesforum.games.modelo.PlanoAvulso

fun Plano.toEntity(): PlanoEntity {
    return if (this is PlanoAssinatura)
        PlanoAssinaturaEntity(this.tipo, this.mensalidade, this.jogosIncluidos, this.percentualDescontoReputacao, this.id)
    else
        PlanoAvulsoEntity(this.tipo, this.id)
}

fun PlanoEntity.toModel(): Plano {
    return if (this is PlanoAssinaturaEntity)
        PlanoAssinatura(this.tipo, this.mensalidade, this.jogosIncluidos, this.percentualDescontoReputacao, this.id)
    else
        PlanoAvulso(this.tipo, this.id)
}