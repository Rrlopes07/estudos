package com.estudo.med.api.domain.consulta.validacoes;

import com.estudo.med.api.domain.ValidacaoException;
import com.estudo.med.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsultas{

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();

        var diferenceEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferenceEmMinutos < 30)
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos!");
    }

}
