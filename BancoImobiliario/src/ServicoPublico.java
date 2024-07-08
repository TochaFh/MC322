public class ServicoPublico extends Propriedade
{
    private int multiplicador;

    public ServicoPublico(String nome, int preco, int aluguel) {
        super(nome, preco, aluguel);
    }
    

    // Getters e setters

    public int getMultiplicador() {
        return multiplicador;
    }
    
    public void setMultiplicador(int multiplicador) {
        this.multiplicador = multiplicador;
    }
    

    // Métodos

    public int calcularAluguel()
    {
        multiplicador = Main.getTabuleiro().getUltimaJogada().getSomaDados();

        AppUtils.log("$ calculando aluguel SERVIÇO PÚBLICO " + nome);
        int valor = multiplicador * this.aluguel;

        // caso o dono do serviço tenha todos os serviços do jogo, o valor é dobrado
        if (Main.getTabuleiro().todosServicosMesmoDono())
        {
            valor *= 2;
        }

        return valor;
    }
}
