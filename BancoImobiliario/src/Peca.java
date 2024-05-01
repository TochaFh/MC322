public class Peca
{
    private String cor;
    private int posicao;
    private Jogador jogador;

    // Construtor
    public Peca(String cor, Jogador j)
    {
        this.cor = cor;
        this.posicao = 0;
        jogador = j;
    }

    // Getters e setters
    public String getCor()
    {
        return cor;
    }
    
    public void setCor(String cor)
    {
        this.cor = cor;
    }
    
    public int getPosicao()
    {
        return posicao;
    }
    
    public void setPosicao(int posicao)
    {
        this.posicao = posicao;
    }

    public Jogador getJogador() {
        return jogador;
    }
}
