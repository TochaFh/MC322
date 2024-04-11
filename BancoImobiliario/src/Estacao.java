public class Estacao extends Propriedade
{
    // Métodos

    public Estacao(String nome, int preco, int aluguel) {
        super(nome, preco, aluguel);
    }

    public int calcularAluguel()
    {
        AppUtils.log("$ calculando aluguel da ESTACAO " + nome);
        return super.calcularAluguel();
    }
}
