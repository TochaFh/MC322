public class CartaSorte extends Carta
{
    TipoCarta tipo;

    private int movimento;
    private int efeito;
    private int valor;
    private String acao;
    private int tempo;
    private String restricao;
    
    // Construtores
    public CartaSorte(TipoCarta tipo, String descricao, int valor) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
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
    
    public int getValor()
    {
        return valor;
    }
    
    public void setValor(int valor)
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

    public void ExecutarAcao(Jogador jogador)
    {
        AppUtils.log("Executando ação da carta " + tipo.toString() + ":\n" + this.descricao);
        jogador.somarDinheiro(valor);
    }
}
