module "aws-dev" {
  source          = "../../infra"
  instancia       = "t2.micro"
  regiao_aws      = "us-east-2"
  chave           = "iac-dev"
  grupo_seguranca = "grupo_dev"
  minimo          = 0
  maximo          = 1
  nome_grupo      = "Dev"
  producao        = false
}
