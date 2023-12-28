package com.estudo.med.api.controller;

import com.estudo.med.api.domain.consulta.AgendaDeConsultas;
import com.estudo.med.api.domain.consulta.DadosDetalhamentoConsulta;
import com.estudo.med.api.domain.consulta.DadosCancelamentoConsulta;
import com.estudo.med.api.domain.consulta.DadosAgendamentoConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity<?> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);

        return ResponseEntity.noContent().build();
    }

}
