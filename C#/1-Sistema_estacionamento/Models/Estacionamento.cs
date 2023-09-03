using System.Data;

namespace Sistema_estacionamento.Models
{
    public class Estacionamento
    {
        private decimal _precoInicial = 0;
        private decimal _precoPorHora = 0;
        public decimal PrecoInicial { get; set; }  
        public decimal PrecoPorHora { get; set; }
        private List<string> _veiculos = new List<string>();
        public List<string> Veiculos { get; }

        public Estacionamento(decimal precoInicial, decimal precoPorHora)
        {
            this.PrecoInicial = precoInicial;
            this.PrecoPorHora = precoPorHora;
        }

        public void AdicionarVeiculo()
        {
            Console.WriteLine("Digite a placa do veículo para estacionar: ");
            string placa = Console.ReadLine();

            try
            {
                bool verificador = VerificaString(placa);

                if(verificador)
                {
                    throw new Exception("Já existe placa cadastrada com este valor!");
                }

                Veiculos.Add(placa.ToUpper());
                Console.WriteLine("Placa adicionada com sucesso");
            }
            catch (InvalidDataException e)
            {
                Console.WriteLine(e.Message);
            }
            
        }

        public void RemoverVeiculo()
        {
            Console.WriteLine("Digite a placa do veículo para remover:");
            string placa = Console.ReadLine();

            try
            {
                bool verificador = VerificaString(placa);

                // Verifica se o veículo existe
                if (verificador)
                {
                    Console.WriteLine("Digite a quantidade de horas que o veículo permaneceu estacionado:");

                    decimal horasEstacionado = decimal.Parse(Console.ReadLine());
                    decimal valorTotal = PrecoInicial + (PrecoPorHora * horasEstacionado);

                    Veiculos.Remove(placa);

                    Console.WriteLine($"O veículo {placa} foi removido e o preço total foi de: R$ {valorTotal}");
                }
                else
                {
                    Console.WriteLine("Desculpe, esse veículo não está estacionado aqui. Confira se digitou a placa corretamente");
                }
            } 
            catch (InvalidDataException e)
            {
                Console.WriteLine(e.Message);
            }

            
        }

        public void ListarVeiculos()
        {
            // Verifica se há veículos no estacionamento
            if (Veiculos.Any())
            {
                Console.WriteLine("Os veículos estacionados são:");
                Veiculos.ForEach(Console.WriteLine);
            }
            else
            {
                Console.WriteLine("Não há veículos estacionados.");
            }
        }

        public bool VerificaString(string palavra)
        {
            if(palavra.Length != 8)
            {
                throw new InvalidDataException("Devem ser informados 8 dígitos!");
            }

            bool possuiNaLista = Veiculos.Contains(palavra);

            return possuiNaLista;
        }
    }
}