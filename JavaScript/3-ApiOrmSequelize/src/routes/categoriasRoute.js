const { Router } = require('express');
const CategoriaController = require('../controllers/CategoriaController.js');

const categoriaController = new CategoriaController();

const router = Router();

router.get('/categorias', (req, res) => categoriaController.listAll(res, res));
router.get('/categorias/:id', (req, res) => categoriaController.findById(res, res));
router.post('/categorias', (req, res) => categoriaController.create(res, res));
router.put('/categorias', (req, res) => categoriaController.update(res, res));
router.delete('/categorias:id', (req, res) => categoriaController.exclude(res, res));

module.exports = router;