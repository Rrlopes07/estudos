Atividade dedicada ao estudo de implementação de machine learning, utilizando o serviço Azure Machine Learning, da Microsoft.

Para esta atividade, foram realizadas as seguintes etapas:

1 - Criação de um Resource Group, referente ao workspace;

2 - Criação de um job, via a funcionalidade Automated ML. Como base, foi uma tabela de dados econômicos referente à bicicletas. Foi utilizada regressão como tipo de aprendizado;

3 - Com o passo anterior finalizado, foi acessado o modelo e - a partir deste, foi realizado o deploy to teste de modelo;

4 - Com o deploy concluído, foi acessada a aba Endpoints, e a partir dali foi realizada requisição para previsão do modelo, utilizando a estrutura contida no arquivo input.json;

O retorno da API pode ser observado no arquivo output.json

Documentação de referência: https://microsoftlearning.github.io/mslearn-ai-fundamentals/Instructions/Labs/01-machine-learning.html