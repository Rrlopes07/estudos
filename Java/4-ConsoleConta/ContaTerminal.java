import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Por favor, digite o número da agência:");
        String agencia = scan.next();

        System.out.print("Por favor, digite o número da conta:");
        int conta = scan.nextInt();

        System.out.print("Por favor, digite o nome do cliente:");
        String nome = scan.next();

        System.out.print("Por favor, digite o saldo da conta:");
        String entrada = scan.next();
        double saldo = Double.parseDouble(entrada);

        System.out.print("Olá, " + nome + ", obrigado por criar uma conta em nosso banco, sua agência é: ");
        System.out.print(agencia + ", conta " + conta + " e seu saldo " + saldo + " já está disponível para saque.");
    }
}