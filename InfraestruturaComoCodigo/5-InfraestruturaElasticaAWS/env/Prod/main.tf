module "aws-prod" {
  source          = "../../infra"
  instancia       = "t2.micro"
  regiao_aws      = "us-east-2"
  chave           = "iac-prod"
  grupo_seguranca = "grupo_prod"
  minimo          = 1
  maximo          = 10
  nome_grupo      = "Prod"
  producao        = true
}
