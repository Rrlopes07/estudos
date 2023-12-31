module "prod" {
  source = "../../infra"

  nome_repositorio   = "producao"
  cargoIAM           = "producao"
  ambiente           = "prod"
  capacidade_fargate = 5
}

output "ip_alb" {
  value = module.prod.dns_alb
}