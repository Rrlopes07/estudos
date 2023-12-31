terraform {
  backend "s3" {
    bucket = "terraform-state-raphaelrlopes"
    key    = "homolog/terraform.tfstate"
    region = "us-east-2"
  }
}