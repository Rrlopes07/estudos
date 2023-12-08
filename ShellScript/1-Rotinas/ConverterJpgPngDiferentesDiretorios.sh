#!/bin/bash

DIRETORIO_VARRIDO=$(pwd)

converte_imagem(){
    local caminho_imagem=$1
    local imagem_sem_extensao=$(ls $caminho_imagem | awk -F. '{ print $1 }')

    convert "$imagem_sem_extensao.jpg" "$imagem_sem_extensao.png"
}

varrer_diretorio(){
    cd $1

    local diretorio_atual=$(pwd)

    for arquivo in *
    do

        local caminho_arquivo=$(find $diretorio_atual -name "$arquivo")

        if [ -d $caminho_arquivo ]
        then
            varrer_diretorio $caminho_arquivo
        else
            converte_imagem $caminho_arquivo
        fi

    done
}

varrer_diretorio $DIRETORIO_VARRIDO/imagens-livros 2 > erros.txt

if [ $? -eq 0 ]
then
    echo "Conversão realizada com sucesso"
else
    echo "Houve uma falha no processo de conversão"
fi
