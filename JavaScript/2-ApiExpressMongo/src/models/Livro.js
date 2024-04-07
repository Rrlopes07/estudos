import mongoose from 'mongoose';

const livroSchema = new mongoose.Schema(
  {
    id: { type: String },
    titulo: { 
      type: String, 
      required: [true, 'O título do livro é obrigatório']
    },
    autor: { 
      type: mongoose.Schema.Types.ObjectId, 
      ref: 'autores', 
      required: [true, 'O autor(a) é obrigatório'],
      autopopulate: { select: "nome" } 
    },
    editora: { 
      type: String, 
      required: [true, 'A editora é obrigatória'],
      enum: {
        values: ['Casa do Código', 'FTD', 'Livrarias Curitiba', 'Amazon'],
        message: 'A editora {VALUE} não é um valor permitido'
      }
    },
    numeroPaginas: { 
      type: Number ,
      validate: {
        validator: (valor) => {
          return valor >= 10 && valor <= 5000
        },
        message: 'O número de páginas deve ser no mínimo 10 e no máximo 5000. Valor fornecido: {VALUE}'
      }
    }
  }
);

livroSchema.plugin(require('mongoose-autopopulate'));
const livros = mongoose.model('livros', livroSchema);

export default livros;
