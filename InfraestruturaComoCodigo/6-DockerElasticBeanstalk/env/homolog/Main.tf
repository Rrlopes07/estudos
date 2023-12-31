module "homolagacao" {
  source = "../../infra"

  nome      = "homologacao"
  descricao = "Aplicacao de homolagacao"
  maximo    = 1
  maquina   = "t2.micro"
  ambiente  = "ambiente-de-homologacao"
}