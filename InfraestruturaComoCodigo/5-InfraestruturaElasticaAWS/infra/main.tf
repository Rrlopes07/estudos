terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.16"
    }
  }

  required_version = ">= 1.2.0"
}

provider "aws" {
  region = "us-east-2"
}

resource "aws_launch_template" "maquina" {
  image_id      = "ami-05fb0b8c1424f266b"
  instance_type = var.instancia
  key_name      = var.chave
  tags = {
    Name = "terraform-ansible-python"
  }
  security_group_names = [var.grupo_seguranca]
  user_data            = var.producao ? filebase64("ansible.sh") : ""
}

resource "aws_key_pair" "chaveSSH" {
  key_name   = var.chave
  public_key = file("${var.chave}.pub")
}

resource "aws_autoscaling_group" "grupo" {
  availability_zones = ["${var.regiao_aws}a", "${var.regiao_aws}b"]
  name               = var.nome_grupo
  max_size           = var.maximo
  min_size           = var.minimo
  launch_template {
    id      = aws_launch_template.maquina.id
    version = "$Latest"
  }
  target_group_arns = var.producao ? [aws_lb_target_group.alvo_load_balancer[0].arn] : []
}

resource "aws_default_subnet" "subnet_1" {
  availability_zone = "${var.regiao_aws}a"
}

resource "aws_default_subnet" "subnet_2" {
  availability_zone = "${var.regiao_aws}b"
}

resource "aws_lb" "load_balancer" {
  internal = false
  subnets  = [aws_default_subnet.subnet_1.id, aws_default_subnet.subnet_2.id]
  count    = var.producao ? 1 : 0
}

resource "aws_lb_target_group" "alvo_load_balancer" {
  name     = "maquinas_alvo"
  port     = "8000"
  protocol = "HTTP"
  vpc_id   = aws_default_vpc.default.id
  count    = var.producao ? 1 : 0
}

resource "aws_default_vpc" "default" {

}

resource "aws_lb_listener" "entrada_load_balancer" {
  load_balancer_arn = aws_lb.load_balancer[0].arn
  port              = "8000"
  protocol          = "HTTP"
  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.alvo_load_balancer[0].arn
  }
  count = var.producao ? 1 : 0
}

resource "aws_autoscaling_policy" "escala_producao" {
  name                   = "terraform-escala"
  autoscaling_group_name = var.nome_grupo
  policy_type            = "TargetTrackingScaling"
  target_tracking_configuration {
    predefined_metric_specification {
      predefined_metric_type = "ASGAverageCPUUtilization"
    }
    target_value = 50.0
  }
  count = var.producao ? 1 : 0
}