resource "kubernetes_deployment" "aplicacao-API" {
  metadata {
    name = "aplicacao-api"
    labels = {
      app = aplicacao
    }
  }

  spec {
    replicas = 3

    selector {
      match_labels = {
        app = aplicacao
      }
    }

    template {
      metadata {
        labels = {
          app = aplicacao
        }
      }

      spec {
        container {
          image = "python:alpine"
          name  = "aplicacao"

          resources {
            limits = {
              cpu    = "0.5"
              memory = "512Mi"
            }
            requests = {
              cpu    = "250m"
              memory = "50Mi"
            }
          }

          liveness_probe {
            http_get {
              path = "/"
              port = 8000
            }

            initial_delay_seconds = 60
            period_seconds        = 5
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "LoadBalancer" {
  metadata {
    name = "loadbalancer_aplicacao"
  }

  spec {
    selector = {
        app = aplicacao
    }

    port {
      port        = 8000
      target_port = 8000
    }
    type = "LoadBalancer"
  }
}

data "kubernetes_service" "nomeDNS" {
  metadata {
    name = "loadbalancer_aplicacao"
  }
}

output "URL" {
  value = data.kubernetes_service.nomeDNS.status
}