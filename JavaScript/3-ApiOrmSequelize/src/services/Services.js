const datasource = require('../database/models');

class Services {

  constructor(model) {
    this.model = model;
  }

  async listRegisters(where = {}) {
    return datasource[this.model].findAll({ where: { ...where } });
  }

  async findRegistersByEscope(escope) {
    return datasource[this.model].scope(escope).findAll();
  }

  async findById(id) {
    return datasource[this.model].findByPk(id);
  }

  async findOne(where) {
    return datasource[this.model].findOne({ where: {...where} });
  }

  async findAndCountRegisters(options) {
    return datasource[this.model].findAndCountAll({ ...options });
  }

  async create(registerData) {
    return datasource[this.model].create(registerData);
  }

  async update(updatedData, where, transaction = {}) {
    const listOfUpdatedRegisters = datasource[this.model].update(
      updatedData,
      { 
        where: { ...where },
        transaction: transaction 
      }
    );
    if (listOfUpdatedRegisters[0] === 0)
      return false;
    return true;
  }

  async exclude(id) {
    return datasource[this.model].destroy({ where: { id: id } });
  }

}

module.exports = Services;
