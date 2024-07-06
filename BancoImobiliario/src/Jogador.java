import java.util.ArrayList;

/**
 * Representa um jogador do banco imobiliário.
 * Contém as informações de cadastro (email, cpf etc).
 * 
 * Por enquanto, o email e o cpf são automaticamente validados em suas
 * respectivas funções setters e armazenados como "inválido", se for o caso
 */
public class Jogador
{
    public static int DINHEIRO_INICIAL = 2558000; // 2.558.000

    private static int numJogadoresCriados = 0;

    private String nome;
    private String cpf;
    private String email;
    private String foto;
    private int id;
    private int saldo;
    private Peca peca;

    // faz mais sentido armazenar as cartas de sorte de forma separada das propriedades
    private ArrayList<Propriedade> propriedades;

    private ArrayList<CartaSorte> cartasDeSorte;




    // Construtores
    public Jogador(String nome, String cpf, String email, String foto, String corPeca)
    {
        this.nome = nome;
        setCpf(cpf);
        setEmail(email);
        this.foto = foto;
        this.peca = new Peca(corPeca, this);

        // valor default
        this.saldo = DINHEIRO_INICIAL;

        propriedades = new ArrayList<Propriedade>();
        cartasDeSorte = new ArrayList<CartaSorte>();

        // contagem para ID (começa a partir do 1)
        numJogadoresCriados++;
        this.id = numJogadoresCriados;
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
    
    public Peca getPeca() {
        return peca;
    }

    public ArrayList<Propriedade> getPropriedades() {
        return propriedades;
    }

    public ArrayList<CartaSorte> getCartasDeSorte() {
        return cartasDeSorte;
    }

    public int getSaldo()
    {
        return saldo;
    }

    public void setSaldo(int dinheiro)
    {
        this.saldo = dinheiro;
    }

    /**
     * adiciona uma quantia (pode ser negativa) ao saldo do jogador
     * @param quantiaSomar
     * @return falso, se no final o saldo for menor ou igual a 0
     */
    public boolean somarDinheiro(int quantiaSomar)
    {
        saldo += quantiaSomar;
        return saldo > 0;
    }

    public static int getNumJogadoresCriados() {
        return numJogadoresCriados;
    }


    // Método toString
    @Override
    public String toString() {
        return "\n-- Jogador " + id + "\nNome: " + nome + "\nCpf: " + cpf
        + "\nEmail: " + email + "\nFoto: " + foto + "\nDinheiro: $" + saldo + "\n";
    }

    public String listaPropriedades()
    {
        if (propriedades.size() == 0)
        {
            return "Não possui propriedades";
        }

        String str = "";
        for (Propriedade p : propriedades) {
            str += p.getNome() + ", ";
        }
        
        return  str.substring(0, str.length() - 2);
    }

    public String listaCartasDeSorte()
    {
        if (cartasDeSorte.size() == 0)
        {
            return "Não possui nenhuma carta de sorte";
        }

        String str = "";
        for (CartaSorte c : cartasDeSorte) {
            str += c.getDescricao() + ", ";
        }
        
        return  str.substring(0, str.length() - 2);
    }

    public void printBasico()
    {
        AppUtils.log("JOGADOR " + this.getId() + ":");
        AppUtils.log(this.getNome() + "\n" + "$" + this.getSaldo());
        AppUtils.log("Posição: " + this.getPeca().getPosicao());
        AppUtils.log("Propriedades: " + this.listaPropriedades());
        AppUtils.log("Cartas de sorte guardadas: " + this.listaCartasDeSorte());
    }

    public void comprarPropriedade(Propriedade p) throws SaldoInsuficienteException
    {
        if (p.getPreco() > this.saldo)
        {
            throw new SaldoInsuficienteException(this, p);
        }

        // jogador tem dinheiro:

        saldo -= p.getPreco();
        this.propriedades.add(p);
        p.setDono(this);
    }

    public void pagarAluguel(Propriedade p) throws SaldoInsuficienteException
    {
        int aluguel = p.calcularAluguel();
        if (aluguel > this.saldo)
        {
            throw new SaldoInsuficienteException(this, aluguel);
        }

        // jogador tem dinheiro:

        this.saldo -= aluguel;
        p.getDono().saldo += aluguel;
    }
}
