class Gerenciador:
    def cadastrar(self, usuario):
        with open('./arquivo.txt', mode='a') as arquivo:
            arquivo.write(usuario.__str__())
            print("Usuário cadastrado com sucesso.")

    def autenticar(self, usuario):
        with open('./arquivo.txt', mode='r') as arquivo:
            login = ''
            nao_encontrou = True

            for linha in arquivo:
                verificado = linha.split(',')
                print(verificado)

                if usuario.login == verificado[1]:
                    login = verificado
                    nao_encontrou = False
                    break

            if nao_encontrou:
                print('Autenticação inválida!')

            if usuario.senha == login[2]:
                print(f'Seja bem-vindo, {login[0]}')
            else:
                print('Autenticação inválida!')
