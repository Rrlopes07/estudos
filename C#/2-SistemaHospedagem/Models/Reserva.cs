namespace DesafioProjetoHospedagem.Models
{
    public class Reserva
    {
        public List<Pessoa> Hospedes { get; set; }
        public Suite Suite { get; set; }
        public int DiasReservados { get; set; }

        public Reserva() { }

        public Reserva(int diasReservados)
        {
            DiasReservados = diasReservados;
        }

        public void CadastrarHospedes(List<Pessoa> hospedes)
        {
            bool possuiCapacidade = Suite.Capacidade >= hospedes.Count;

            if (possuiCapacidade)
            {
                Hospedes = hospedes;
            }
            else
            {
                throw new Exception("A capacidade desta suite é menor que a quantidade de hóspedes!");
            }
        }

        public void CadastrarSuite(Suite suite)
        {
            Suite = suite;
        }

        public int ObterQuantidadeHospedes()
        {
            return Hospedes.Count;
        }

        public decimal CalcularValorDiaria()
        {
            decimal valor = DiasReservados * Suite.ValorDiaria;
            bool verificaDezDias = DiasReservados >= 10;

            if (verificaDezDias)
            {
                decimal desconto = valor * 0.1m;
                valor -= desconto;
            }

            return valor;
        }
    }
}