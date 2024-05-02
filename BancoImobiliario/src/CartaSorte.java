public class CartaSorte extends Carta
{
    private int movimento;
    private int efeito;
    private float valor;
    private String acao;
    private int tempo;
    private String restricao;
    
    // Construtores
    public CartaSorte(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    // Getters e setters
    
    public int getMovimento()
    {
        return movimento;
    }
    
    public void setMovimento(int movimento)
    {
        this.movimento = movimento;
    }
    
    public int getEfeito()
    {
        return efeito;
    }
    
    public void setEfeito(int efeito)
    {
        this.efeito = efeito;
    }
    
    public float getValor()
    {
        return valor;
    }
    
    public void setValor(float valor)
    {
        this.valor = valor;
    }
    
    public String getAcao()
    {
        return acao;
    }
    
    public void setAcao(String acao)
    {
        this.acao = acao;
    }
    
    public int getTempo()
    {
        return tempo;
    }
    
    public void setTempo(int tempo)
    {
        this.tempo = tempo;
    }
    
    public String getRestricao()
    {
        return restricao;
    }
    
    public void setRestricao(String restricao)
    {
        this.restricao = restricao;
    }
}
