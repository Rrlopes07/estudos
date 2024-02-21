Para esta atividade, foram realizadas as seguintes etapas:

1 - Criação de um Resource Group, com os recursos necessários: Azure AI Search, Azure AI Service e Storage account;

2 - No Storage Account foi criado um container com permissão de acesso público e adicionados os arquivos de consulta

3 - No AI Search foi estabeleccida a conexão com o container contendo os arquivos de consulta que estão no Storage Account;

4 - Feito isso, foi utilizado o mecanismo Search explorer, no AI Search para a realização de consultas aos documentos;

O texto utilizado para consulta está no arquivo input.json, enquanto os resultados estão no arquivo output.json.

Documentação de referência: https://microsoftlearning.github.io/mslearn-ai-fundamentals/Instructions/Labs/11-ai-search.html