terraform {
  backend "s3" {
    bucket = "terraform-state-raphaelrlopes"
    key    = "Prod/terraform.tfstate"
    region = "us-east-2"
  }
}