public class ServicoPublico extends Propriedade
{
    // Métodos

    public ServicoPublico(String nome, int preco, int aluguel) {
        super(nome, preco, aluguel);
    }

    public int calcularAluguel(int dados)
    {
        AppUtils.log("$ calculando aluguel SERVIÇO PÚBLICO " + nome);
        return dados * this.aluguel;
    }
}
