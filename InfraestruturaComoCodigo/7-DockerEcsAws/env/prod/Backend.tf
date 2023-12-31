terraform {
  backend "s3" {
    bucket = "terraform-state-estudo"
    key    = "Prod/terraform.tfstate"
    region = "us-east-2"
  }
}