terraform {
  required_providers {
    google = {
      source = "hashicorp/google"
      version = "4.51.0"
    }
  }
}

provider "google" {
  credentials = file("chave.json")

  project = "idDoProjeto"
  region  = "us-central1"
  zone    = "us-central1-c"
}