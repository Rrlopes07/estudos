from hashlib import md5
from Gerenciador import Gerenciador
from Usuario import Usuario


def menu():
    while True:
        selecao = int(input('Selecione uma opção (1 - Cadastrar; 2 - Autenticar; 3 - Sair): '))
        gerenciador = Gerenciador()

        if selecao == 3:
            break
        elif selecao == 1:
            nome = input('Informe o nome do usuário: ')
            login = input('Informe o nome do usuário: ')
            senha = input('Informe a senha do usuário: ')
            senha = md5(senha.encode()).hexdigest()
            usuario = Usuario(nome, login, senha)
            gerenciador.cadastrar(usuario)
        elif selecao == 2:
            login = input('Informe o nome do usuário: ')
            senha = input('Informe a senha do usuário: ')
            senha = md5(senha.encode()).hexdigest()
            usuario = Usuario("", login, senha)
            gerenciador.autenticar(usuario)
        else:
            print('Selecione uma opção válida')
