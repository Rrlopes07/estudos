const { Router } = require('express');
const CursoController = require('../controllers/CursoController.js');

const cursoController = new CursoController();

const router = Router();

router.get('/cursos', (req, res) => cursoController.listaCursos(res, res));
router.get('/cursos/:id', (req, res) => cursoController.findById(res, res));
router.post('/cursos', (req, res) => cursoController.create(res, res));
router.put('/cursos', (req, res) => cursoController.update(res, res));
router.delete('/cursos:id', (req, res) => cursoController.exclude(res, res));

module.exports = router;