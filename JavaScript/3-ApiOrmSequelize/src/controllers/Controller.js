const converteId = require('../utils/conversorDeStringHelper.js');

class Controller {

  constructor(entityService) {
    this.entityService = entityService;
  }

  async listAll(req, res) {
    try {
      const listOfRegisters = await this.entityService.listRegisters();
      return res.status(200).json(listOfRegisters);
    } catch (error) {
      return res.status(500).json({ erro: error.message });
    }
  }

  async create(req, res) {
    const creationData = req.body;
    try {
      const newRegisterCreated = await this.entityService.create(creationData);
      return res.status(200).json(newRegisterCreated);
    } catch (error) {
      return res.status(500).json({ erro: error.message });
    }
  }

  async findById(req, res) {
    const { id } = req.params;
    try {
      const register = await this.entityService.findById(id);
      return res.status(200).json(register);
    } catch (error) {
      return res.status(500).json({ erro: error.message });
    }
  }

  async findOne(req, res) {
    const { ...params } = req.params;
    const where = converteId(params);
    try {
      const register = await this.entityService.findOne(where);
      return res.status(200).json(register);
    } catch (error) {
      return res.status(500).json({ erro: error.message });
    }
  }

  async update(req, res) {
    const { ...params } = req.params;
    const { updatedData } = req.body;

    const where = converteId(params);
    try {
      const isUpdated = await this.entityService.update(updatedData, where);
      if (!isUpdated)
        return res.status(400).json({ message: 'Registro não foi atualizado' });
      return res.status(200).json({ message: 'Atualizado com sucesso' });
    } catch (error) {
      return res.status(500).json({ erro: error.message });
    }
  }

  async exclude(req, res) {
    const { id } = req.params;
    try {
      await this.entityService.exclude(Number(id));
      return res.status(200).json({ message: `id ${id} deletado` });
    } catch (error) {
      return res.status(500).json({ erro: error.message });
    }
  }

}

module.exports = Controller;