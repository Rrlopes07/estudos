module "prod" {
    source = "../../infra"
    nome_repositorio = "producao"
    cluster_name = "aplicacao"
}

output "endereco" {
  value = module.prod.URL
}