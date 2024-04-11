public class Propriedade
{
    protected int id;
    protected String nome;
    protected int proprietario;
    protected int preco;
    protected int aluguel;
    
    public Propriedade(String nome, int preco, int aluguel)
    {
        this.nome = nome;
        this.preco = preco;
        this.aluguel = aluguel;
    }

    
    // Getters e setters

    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getNome()
    {
        return nome;
    }
    
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    public int getProprietario()
    {
        return proprietario;
    }
    
    public void setProprietario(int proprietario)
    {
        this.proprietario = proprietario;
    }
    
    public int getPreco()
    {
        return preco;
    }
    
    public void setPreco(int preco)
    {
        this.preco = preco;
    }
    
    public int getAluguel()
    {
        return aluguel;
    }
    
    public void setAluguel(int aluguel)
    {
        this.aluguel = aluguel;
    }


    // Métodos

    public int calcularAluguel()
    {
        return aluguel;
    }
}
