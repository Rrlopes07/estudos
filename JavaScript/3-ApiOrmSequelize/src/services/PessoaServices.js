const dataSource = require('../database/models');
const Services = require('./Services.js');

class PessoaServices extends Services {

  constructor() {
    super('Pessoa');
    this.matriculaServices = new Services('Matricula');
  }

  async listaMatriculasAtivasPorEstudante(id) {
    const estudante = await super.findById(id);
    const listaMatriculas = await estudante.getAulasMatriculadas();
    return listaMatriculas;
  }

  async listaTodasMatriculasPorEstudante(id) {
    const estudante = await super.findById(id);
    const listaMatriculas = await estudante.getTodasAsMatriculas();
    return listaMatriculas;
  }

  async findPersonsEscopeAll() {
    const listaPessoas = await super.findRegistersByEscope('allRegisters');
    return listaPessoas;
  }

  async cancelPessoaAndMatriculas(estudanteId) {
    return dataSource.sequelize.transaction(async (transaction) => {
      await super.update({ ativo: false }, { id: estudanteId }, transaction);
      await this.matriculaServices.update({ status: 'cancelado' }, { estudante_id: estudanteId }, transaction);
    });
  }

}

module.exports = PessoaServices;
