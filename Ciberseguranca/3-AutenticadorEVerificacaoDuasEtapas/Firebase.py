import pyrebase
import requests.exceptions

from Authenticator import Authenticator


class Firebase:
    def __init__(self):
        self.config = self.obtain_config()
        self.firebase = pyrebase.initialize_app(self.config)
        self.auth = self.firebase.auth()
        self.status = ''

    def create_user(self, user, password):
        while True:
            user = input('Digite seu e-mail: ')
            password = input('Digite sua senha, com pelo menos 6 caracteres: ')

            if user != '' and len(password) > 6:
                account = self.auth.create_user_with_email_and_password(user, password)
                id_token = account['idToken']
                self.auth.send_email_verification(id_token)
                print('Usuário cadastrado com sucesso!')
                break
            else:
                print('Favor informar dados válidos para o cadastro!')

    def authenticate_user(self):
        while True:
            try:
                user = input('Digite seu e-mail: ')
                password = input('Digite sua senha, com pelo menos 6 caracteres: ')

                self.status = self.auth.sign_in_with_email_and_password(user, password)
                is_verified = self.status['users'][0]['emailVerified']

                if is_verified:
                    result = self.second_verification(self.status['email'])

                    if result:
                        print('Usuário autenticado com sucesso!')
                        break
                    else:
                        print('Usuário não autenticado!')
                        break
                else:
                    self.status = ''
                    print('Você ainda não realizou a verificação do email! Favor verificar para prosseguir')
                    break

            except requests.exceptions.HTTPError:
                print('Favor informar credenciais válidas!!')

    def second_verification(self, user):
        authenticator = Authenticator(user)

        result = authenticator.autenticate()

        return result

    @staticmethod
    def obtain_config():
        return {
            'apiKey': "",
            'authDomain': "",
            'projectId': "",
            'databaseURL': "",
            'storageBucket': "",
            'messagingSenderId': "",
            'appId': "",
            'measurementId': ""
        }
