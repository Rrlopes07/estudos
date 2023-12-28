package com.estudo.med.api.domain.consulta;

import com.estudo.med.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsultas;
import com.estudo.med.api.domain.medico.Medico;
import com.estudo.med.api.domain.medico.MedicoRepository;
import com.estudo.med.api.domain.paciente.PacienteRepository;
import com.estudo.med.api.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente()))
            throw new ValidacaoException("Paciente não existe na base!");
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico()))
            throw new ValidacaoException("Médico não existe na base!");

        validadores.forEach(validador -> validador.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);

        if (medico == null)
            throw new ValidacaoException("Não existe médico disponível nesta data!");

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        repository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null)
            return medicoRepository.getReferenceById(dados.idMedico());

        if (dados.especialidade() == null)
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não for especificado!");

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!repository.existsById(dados.idConsulta()))
            throw new ValidacaoException("Consulta não existe na base!");

        var consulta = repository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

}
