public class Main {

    public static void main(String[] args) {
        Conta cc = new ContaCorrente(new Cliente("Juarez"));
        Conta poupanca = new ContaPoupanca(new Cliente("Marco"));
        Banco xpto = new Banco("XPTO");
        cc.depositar(10d);
        cc.transferir(10d, poupanca);
        poupanca.sacar(5d);

        cc.imprimirExtrato();
        poupanca.imprimirExtrato();
    }
}
