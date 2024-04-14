const Controller = require('./Controller.js');
const PessoaServices = require('../services/PessoaServices.js');

const pessoaService = new PessoaServices();

class PessoaController extends Controller {

  constructor() {
    super(pessoaService);
  }

  async listaMatriculasAtivas(req, res) {
    const { estudante_id } = req.params;
    try {
      const listaMatriculas = await pessoaService.listaMatriculasAtivasPorEstudante(Number(estudante_id));
      return res.status(200).json({ listaMatriculas });
    } catch (error) {
      return res.status(500).json({ erro: error.message });
    }
  }

  async listaTodasAsMatriculas(req, res) {
    const { estudante_id } = req.params;
    try {
      const listaMatriculas = await pessoaService.listaTodasMatriculasPorEstudante(Number(estudante_id));
      return res.status(200).json({ listaMatriculas });
    } catch (error) {
      return res.status(500).json({ erro: error.message });
    }
  }

  async findAllPersons(req, res) {
    try {
      const listaPessoas = await pessoaService.findRegistersByEscope();
      return res.status(200).json({ listaPessoas });
    } catch (error) {
      return res.status(500).json({ erro: error.message });
    }
  }

  async cancelRegisterEstudante(req, res) {
    const { estudante_id } = req.params;
    try {
      await pessoaService.cancelPessoaAndMatriculas(Number(estudante_id));
      return res.status(200).json({ message: `matrículas ref. estudante ${estudante_id} canceladas` });
    } catch (error) {
      return res.status(500).json({ erro: error.message });
    }
  }
  
}

module.exports = PessoaController;
