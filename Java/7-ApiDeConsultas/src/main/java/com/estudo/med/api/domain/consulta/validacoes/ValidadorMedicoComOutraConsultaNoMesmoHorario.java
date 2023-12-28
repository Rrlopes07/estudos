package com.estudo.med.api.domain.consulta.validacoes;

import com.estudo.med.api.domain.ValidacaoException;
import com.estudo.med.api.domain.consulta.ConsultaRepository;
import com.estudo.med.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario)
            throw new ValidacaoException("Médico já possui outra consulta neste mesmo horário!");
    }

}
