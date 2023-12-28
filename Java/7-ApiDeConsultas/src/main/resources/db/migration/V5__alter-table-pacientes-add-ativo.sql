ALTER TABLE pacientes ADD ativo boolean DEFAULT true;
UPDATE pacientes SET ativo = true;
ALTER TABLE pacientes ALTER COLUMN ativo SET NOT NULL;