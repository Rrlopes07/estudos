package com.estudo.med.api.domain.consulta;

import com.estudo.med.api.domain.medico.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,
        Especialidade especialidade
) {
}
