resource "aws_security_group" "alb" {
  name   = "alb_ECS"
  vpc_id = module.vpc.vpc_id
}

resource "aws_vpc_security_group_ingress_rule" "entrada_alb" {
  security_group_id = aws_security_group.alb.id

  cidr_ipv4   = "0.0.0.0/0"
  from_port   = 8000
  ip_protocol = "tcp"
  to_port     = 8000
}

resource "aws_vpc_security_group_egress_rule" "saida_alb" {
  security_group_id = aws_security_group.alb.id

  cidr_ipv4   = "0.0.0.0/0"
  from_port   = 8000
  ip_protocol = "tcp"
  to_port     = 8000
}

resource "aws_security_group" "private" {
  name   = "private_ECS"
  vpc_id = module.vpc.vpc_id
}

resource "aws_vpc_security_group_ingress_rule" "entrada_ECS" {
  security_group_id = aws_security_group.private.id

  referenced_security_group_id = aws_security_group.alb.id
  from_port                    = 0
  ip_protocol                  = "-1"
  to_port                      = 0
}

resource "aws_vpc_security_group_egress_rule" "saida_ECS" {
  security_group_id = aws_security_group.private.id

  cidr_ipv4                    = "0.0.0.0/0"
  referenced_security_group_id = aws_security_group.alb.id
  from_port                    = 0
  ip_protocol                  = "-1"
  to_port                      = 0
}