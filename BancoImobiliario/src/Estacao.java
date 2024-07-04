public class Estacao extends Propriedade
{
    private int tarifa;

    public Estacao(String nome, int preco, int aluguel) {
        super(nome, preco, aluguel);
        tarifa = aluguel + (preco / 10);
    }
    

    // Getters e setters

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }


    // Métodos

    public int calcularAluguel()
    {
        AppUtils.log("$ calculando aluguel da ESTACAO " + nome);

        int valor = this.tarifa;

        // caso o dono da estação tenha todas as estações do jogo, o valor é dobrado
        if (Main.getTabuleiro().todasEstacoesMesmoDono())
        {
            valor *= 2;
        }

        return valor;
    }
}
