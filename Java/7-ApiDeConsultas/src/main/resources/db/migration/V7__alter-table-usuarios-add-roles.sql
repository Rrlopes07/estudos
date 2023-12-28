ALTER TABLE usuarios ADD roles TEXT[] DEFAULT '{"USER"}';
UPDATE usuarios SET roles = '{"USER", "ADMIN"}';
ALTER TABLE usuarios ALTER COLUMN roles SET NOT NULL;