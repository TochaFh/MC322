/**
 * Representa um jogador do banco imobiliário.
 * Contém as informações de cadastro (email, cpf etc).
 * 
 * Por enquanto, o email e o cpf são automaticamente validados em suas
 * respectivas funções setters e armazenados como "inválido", se for o caso
 */
public class Jogador
{
    private static int numJogadoresCriados = 0;

    private String nome;
    private String cpf;
    private String email;
    private String foto;
    private int id;
    private int dinheiro;

    // Construtores
    public Jogador(String nome, String cpf, String email, String foto)
    {
        this.nome = nome;
        setCpf(cpf);
        setEmail(email);
        this.foto = foto;

        this.dinheiro = 500;

        // contagem para ID (começa a partir do 1)
        numJogadoresCriados++;
        this.id = numJogadoresCriados;
    }


    // Getters e setters

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        if (AppUtils.validarCpf(cpf))
        {
            this.cpf = cpf;
            return;
        }
        
        // cpf invalidado:
        this.cpf = "* cpf inválido - " + cpf;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        if (AppUtils.validarEmail(email))
        {
            this.email = email;
            return;
        }
        
        // cpf invalidado:
        this.email = "* email inválido - " + email;
    }

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }

    // id tem apenas get
    public int getId()
    {
        return id;
    }

    public int getDinheiro()
    {
        return dinheiro;
    }

    public void setDinheiro(int dinheiro)
    {
        this.dinheiro = dinheiro;
    }

    /**
     * adiciona uma quantia (pode ser negativa) ao saldo do jogador
     * @param quantiaSomar
     * @return falso, se no final o saldo for menor ou igual a 0
     */
    public boolean somarDinheiro(int quantiaSomar)
    {
        dinheiro += quantiaSomar;
        return dinheiro > 0;
    }


    // Método toString
    @Override
    public String toString() {
        return "-- Jogador " + nome + "\nCpf: " + cpf + "\nEmail: " + email + "\nFoto: " + foto + "\nDinheiro: $" + dinheiro + "\n";
    }
}
