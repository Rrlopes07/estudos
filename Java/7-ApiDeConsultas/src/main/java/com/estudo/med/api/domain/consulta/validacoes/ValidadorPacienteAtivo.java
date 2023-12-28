package com.estudo.med.api.domain.consulta.validacoes;

import com.estudo.med.api.domain.ValidacaoException;
import com.estudo.med.api.domain.consulta.DadosAgendamentoConsulta;
import com.estudo.med.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo)
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído!");
    }

}
