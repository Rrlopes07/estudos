USE [Filmes]
GO

-- Buscar nome e ano dos filmes
SELECT
	Nome, Ano
FROM
	Filmes
GO

-- Buscar nome e ano dos filmes, ordenados em ordem crescente pelo ano
SELECT
	Nome, Ano
FROM
	Filmes
ORDER BY 
	ANO
GO

-- Buscar o filme "De volta para o futuro"
SELECT
	Nome, Ano, Duracao
FROM
	Filmes
WHERE
	NOME = 'De Volta para o Futuro'
GO

-- Buscar filmes lançados em 1997
SELECT
	Nome, Ano
FROM
	Filmes
WHERE
	Ano = 1997
GO

-- Buscar filmes lançados após 2000
SELECT
	Nome, Ano
FROM
	Filmes
WHERE
	Ano > 2000
GO

-- Buscar filmes de duração entre 100 e 150, ordenados pela duração
SELECT
	Nome, Ano, Duracao
FROM
	Filmes
WHERE
	Duracao > 100 AND Duracao < 150
ORDER BY
	Duracao
GO

-- Quantidade de filmes lançada no ano, agrupada por ano, ordenada pela duração
SELECT
	Ano, COUNT(*) Quantidade
FROM
	Filmes
GROUP BY
	Ano
ORDER BY
	SUM(Duracao) DESC
GO

-- Buscar atores do gênero masculino, retornando o primeiro, ultimo nome
SELECT
	PrimeiroNome, UltimoNome
FROM
	Atores
WHERE
	Genero = 'M'
GO

-- Buscar atrizes do gênero feminino, retornando o primeiro, ultimo nome, ordenado pelo primeiro nome
SELECT
	PrimeiroNome, UltimoNome
FROM
	Atores
WHERE
	Genero = 'F'
ORDER BY
	PrimeiroNome
GO

-- Buscar nome do filme e o gênero
SELECT
	Nome, g.Genero
FROM
	Filmes f
	JOIN FilmesGenero fg ON f.Id = fg.Id
	JOIN Generos g ON fg.IdGenero = g.Id
GO

-- Buscar nome do filme e o gênero do tipo "Mistério"
SELECT
	Nome, g.Genero
FROM
	Filmes f
	JOIN FilmesGenero fg ON f.Id = fg.Id
	JOIN Generos g ON fg.IdGenero = g.Id
WHERE
	g.Genero = 'Mistério'
GO

-- Buscar nome do filme e atores, trazendo primeiro e último nome, papel
SELECT
	f.Nome, a.PrimeiroNome, a.UltimoNome, ef.Papel
FROM
	Filmes f
	JOIN ElencoFilme ef ON f.Id = ef.IdFilme
	JOIN Atores a ON ef.IdAtor = a.Id
GO