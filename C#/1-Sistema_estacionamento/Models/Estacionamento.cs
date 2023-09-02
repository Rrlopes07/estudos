using System.Data;

namespace Sistema_estacionamento.Models
{
    public class Estacionamento
    {
        private decimal precoInicial = 0;
        private decimal precoPorHora = 0;
        private List<string> veiculos = new List<string>();

        public Estacionamento(decimal precoInicial, decimal precoPorHora)
        {
            this.precoInicial = precoInicial;
            this.precoPorHora = precoPorHora;
        }

        public void AdicionarVeiculo()
        {
            Console.WriteLine("Digite a placa do veículo para estacionar: ");
            string placa = Console.ReadLine();

            VerificaString(placa);

            if(this.veiculos.Contains(placa))
            {
                throw new Exception("Já existe placa cadastrada com este valor!");
            }

            this.veiculos.Add(placa.ToUpper());
            Console.WriteLine("Placa adicionada com sucesso");
        }

        public void RemoverVeiculo()
        {
            Console.WriteLine("Digite a placa do veículo para remover:");
            string placa = Console.ReadLine();

            VerificaString(placa);

            // Verifica se o veículo existe
            if (veiculos.Contains(placa.ToUpper()))
            {
                Console.WriteLine("Digite a quantidade de horas que o veículo permaneceu estacionado:");

                decimal horasEstacionado = decimal.Parse(Console.ReadLine());
                decimal valorTotal = this.precoInicial + (precoPorHora * horasEstacionado);

                this.veiculos.Remove(placa);

                Console.WriteLine($"O veículo {placa} foi removido e o preço total foi de: R$ {valorTotal}");
            }
            else
            {
                Console.WriteLine("Desculpe, esse veículo não está estacionado aqui. Confira se digitou a placa corretamente");
            }
        }

        public void ListarVeiculos()
        {
            // Verifica se há veículos no estacionamento
            if (veiculos.Any())
            {
                Console.WriteLine("Os veículos estacionados são:");
                this.veiculos.ForEach(Console.WriteLine);
            }
            else
            {
                Console.WriteLine("Não há veículos estacionados.");
            }
        }

        public void VerificaString(string palavra)
        {
            if(palavra == null)
            {
                throw new NoNullAllowedException();
            } 
            else if(palavra.Length != 8)
            {
                throw new InvalidDataException();
            }
        }
    }
}