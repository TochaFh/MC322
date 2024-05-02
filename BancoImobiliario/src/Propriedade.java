public class Propriedade extends Carta
{
    private static int numPropriedadesCriadas = 0;

    protected String nome;
    protected int preco;
    protected int aluguel;
    
    public Propriedade(String nome, int preco, int aluguel)
    {
        this.nome = nome;
        this.preco = preco;
        this.aluguel = aluguel;

        numPropriedadesCriadas++;
        this.id = numPropriedadesCriadas;
    }

    
    // Getters e setters

    // id tem apenas get
    public int getId()
    {
        return id;
    }
    
    public String getNome()
    {
        return nome;
    }
    
    public void setNome(String nome)
    {
        this.nome = nome;
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

    // Método toString
    @Override
    public String toString() {
        String s = "\n-- Propriedade " + id + "\nNome: " + nome + "\nPreço: $" + getPreco() + "\nAluguel: $" + getAluguel();
        
        if (this.temDono())
        {
            s += "\nProprietário: " + dono.getNome() + "\n";
        }
        else
        {
            s += "\nProprietário: BANCO" + "\n";
        }

        return s;
    }
}
