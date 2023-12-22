resource "aws_security_group" "acesso_geral" {
  name = var.grupo_seguranca
  ingress {
    # libera todas as conexões, para todas as portas, para todos os protocolos
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
  }
  egress {
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
  }
  tags = {
    Name = var.grupo_seguranca
  }
}