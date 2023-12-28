ALTER TABLE medicos ADD ativo boolean DEFAULT true;
UPDATE medicos SET ativo = true;
ALTER TABLE medicos ALTER COLUMN ativo SET NOT NULL;