module "producao" {
  source = "../../infra"

  nome      = "producao"
  descricao = "Aplicacao de producao"
  maximo    = 5
  maquina   = "t2.micro"
  ambiente  = "ambiente-de-producao"
}