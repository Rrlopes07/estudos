CREATE TABLE consultas(

    id serial PRIMARY KEY,
    medico_id serial not null,
    paciente_id serial not null,
    data timestamp not null,
    FOREIGN KEY (medico_id) references medicos(id),
    FOREIGN KEY(paciente_id) references pacientes(id)

);