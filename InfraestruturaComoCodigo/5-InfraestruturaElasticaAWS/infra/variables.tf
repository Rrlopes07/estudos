variable "regiao_aws" {
  type        = string
  description = "Região da AWS onde está sendo criada a máquina"
}

variable "chave" {
  type = string
}

variable "instancia" {
  type = string
}

variable "grupo_seguranca" {
  type = string
}

variable "nome_grupo" {
  type = string
}

variable "minimo" {
  type = number
}

variable "maximo" {
  type = number
}

variable "producao" {
  type = bool
}