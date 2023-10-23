import smtplib

from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from random import randint


class Authenticator:
    def __init__(self, user):
        self.number = randint(1000, 9999)
        self.user = user
        self.server = 'smtp.gmail.com'
        self.port = 587
        self.username = 'email@email.com'
        self.password = 'xiws dsvp kpcp posd'

    def autenticate(self):
        self.send_email(self.user, self.number)

        information = int(input('Favor informar o número encaminhado para o seu email: '))

        if information == self.number:
            return True
        else:
            return False

    def send_email(self, user, number):
        message = self.obtain_message(user, number)

        connection = smtplib.SMTP(self.server, self.port)
        connection.starttls()
        connection.login(self.username, self.password)
        connection.send_message(message)
        connection.quit()

    def obtain_message(self, user, number):
        mail_subject = 'Verificação em duas etapas'
        mail_body = f'Aqui está o número para verificação: {number}'

        message = MIMEMultipart()
        message['From'] = self.username
        message['To'] = user
        message['Subject'] = mail_subject
        message.attach(MIMEText(mail_body, 'plain'))

        return message
