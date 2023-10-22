#!/bin/bash

echo "Criando as imagens..."

docker build -t user/projeto-backend:1.0 backend/.
docker build -t user/projeto-database:1.0 database/.

echo "Realizando push das imagens..."

docker push user/projeto-backend:1.0
docker push user/projeto-database:1.0

echo "Criando serviços no cluster kubernetes..."

kubectl apply -f ./services.yml

echo "Criando os deployments..."

kubectl apply -f ./deployment.yml
