public class Peca
{
    private String cor;
    private int posicao;
    private Jogador dono;

    // Construtor
    public Peca(String cor, Jogador j)
    {
        this.cor = cor;
        this.posicao = 1;
        dono = j;
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

    public Jogador getDono() {
        return dono;
    }


    // Métodos
    public void move(int numCasas, Tabuleiro tab)
    {
        posicao += numCasas;
        int posicaoMax = tab.getNumCasas();

        posicao -= 1;
        posicao = (posicao % posicaoMax);
        posicao += 1;
    }
}
