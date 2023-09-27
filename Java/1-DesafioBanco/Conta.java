public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;
    private final int agencia;
    private final int numero;
    private double saldo;
    private final Cliente cliente;

    public Conta(Cliente cliente){
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    public void sacar(double valor) {
        if (this.saldo >= valor){
            this.saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    @Override
    public void depositar(double valor) {this.saldo += valor;}


    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            contaDestino.saldo += valor;
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    protected void imprimirInfosComuns() {
        System.out.println("Agencia: " + this.cliente.getNome());
        System.out.printf("Agencia: %d%n", this.agencia);
        System.out.printf("Número: %d%n", this.numero);
        System.out.printf("Saldo: %.2f%n", this.saldo);
    }

    public int getAgencia() {return agencia;}

    public int getNumero() {return numero;}

    public double getSaldo() {return saldo;}
}
