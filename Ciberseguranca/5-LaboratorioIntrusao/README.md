Script para inicializar um laboratório para detecção de intrusão. A segunda máquina é destinada à realização do ataque.

Pré-requisitos: Virtual Box e Vagrant


Clone este repositório, abra o terminal na pasta e digite o seguinte comando:
```
vagrant up
```

Isto irá instalar e inicializar as máquinas. Feito isto, é possível conectar a elas via terminal, para a máquina de detecação de intrusão o comando é:
```
vagrant ssh snort
```
Para a máquina de ataque:
```
vagrant ssh kali
```

Após logar na máquina do snort, utilize o usuário root(comando *su*), para não precisar usar sudo e não ter nenhum problema de permissão.

A máquina snort vem com as políticas padrão do aplicativo. Para remover estas políticas, basta editar o arquivo contido em **/etc/snort.conf**, comentando com **#** em todas as linhas que contenham ***include $RULE_PATH/*** que não seja o local.rules.

Terminado o uso, é possível remover todo o conteúdo utilizando os seguintes comandos:
```
vagrant destroy
vagrant box remove kalilinux/rolling
```