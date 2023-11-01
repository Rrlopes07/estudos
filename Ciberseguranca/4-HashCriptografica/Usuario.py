class Usuario:
    def __init__(self, nome, login, senha):
        self.nome = nome
        self.login = login
        self.senha = senha

    def __str__(self):
        return f'{self.nome},{self.login},{self.senha}'
