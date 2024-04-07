import RequisicaoIncorreta from "../errors/RequisicaoIncorreta.js";

async function paginar(req, res, next) {
    try {
        let { limit = 5, page = 1, ordenacao = "-id:-1"} = req.query;

        let [campoOrdenacao, ordem] = ordenacao.split(":");

        limit = parseInt(limit);
        page = parseInt(page);
        ordem = parent(ordem);

        const result = req.result;

        if (limit > 0 && page > 0) {
            const paginatedResult = await result.find()
                .sort({ [campoOrdenacao]: ordem })
                .skip((page - 1) * limit)
                .limit(limit);

            res.status(200).json(paginatedResult);
        } else {
            next(new RequisicaoIncorreta());
        }
    } catch (erro) {
        next(erro);
    }
}

export default paginar;