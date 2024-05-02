public class Carta
{
    protected int id;
    protected String descricao;
    protected Jogador dono;

    public int getId()
    {
        return id;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Jogador getDono()
    {
        return dono;
    }

    public void setDono(Jogador dono)
    {
        this.dono = dono;
    }

    public Boolean temDono()
    {
        return dono != null;
    }
}
