from Firebase import Firebase

if __name__ == '__main__':
    firebase = Firebase()

    while True:
        option = int(input('''Selecione uma opção:
                    1: Cadastrar usuário no Firebase;
                    2: Autenticar usuário no Firebase;
                    3: Sair.'''))

        if option == 3:
            print('Saindo...')
            break
        elif option == 1 or option == 2:
            if option == 1:
                firebase.create_user()
            else:
                firebase.authenticate_user()
        else:
            print('Selecione uma opção válida!')