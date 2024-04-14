const { Router } = require('express');
const PessoaController = require('../controllers/PessoaControler.js');
const MatriculaController = require('../controllers/MatriculaController.js');

const pessoaController = new PessoaController();
const matriculaController = new MatriculaController();

const router = Router();

router.get('/pessoas', (req, res) => pessoaController.listAll(res, res));
router.get('/pessoas/:id', (req, res) => pessoaController.findById(res, res));
router.get('/pessoas/all', (req, res) => pessoaController.findAllPersons(req, res));
router.post('/pessoas', (req, res) => pessoaController.create(res, res));
router.put('/pessoas', (req, res) => pessoaController.update(res, res));
router.put('/pessoas/:estudante_id/cancela', (req, res) => pessoaController.cancelRegisterEstudante(res, res));
router.delete('/pessoas:id', (req, res) => pessoaController.exclude(res, res));
router.post('/pessoas/:estudante_id/matriculas', (req, res) => matriculaController.create(res, res));
router.get('/pessoas/:estudante_id/matriculas', (req, res) => pessoaController.listaMatriculasAtivas(req, res));
router.get('/pessoas/:estudante_id/matriculas/todos', (req, res) => pessoaController.listaTodasAsMatriculas(req, res));
router.get('/pessoas/:estudante_id/matriculas/confirmadas', (req, res) => matriculaController.findByStudent(req, res));
router.get('/pessoas/matriculas/lotadas', (req, res) => matriculaController.findCursosLotados(req, res));
router.get('/pessoas/:estudante_id/matriculas/:id', (req, res) => matriculaController.findOne(req, res));
router.put('/pessoas/:estudante_id/matriculas/:id', (req, res) => matriculaController.update(res, res));
router.delete('/pessoas/:estudante_id/matriculas/:id', (req, res) => matriculaController.exclude(res, res));

module.exports = router;
