import java.util.ArrayList;

/* Armazena uma lista de jogadores e outra de propriedades
*/
public class Tabuleiro
{
    private int numJogadores;
    private ArrayList<Jogador> jogadores;
    private ArrayList<Propriedade> propriedades;
    private ArrayList<CartaSorte> cartasDeSorteNaMesa;

    // TODO; definir o sistema que relacione as posições/casas do tabuleiro com as propriedades
    // pode ser a própria lista de propriedades em loop,
    // desde que todas as casas do tabuleiros tenham uma propriedade

    // Construtores
    public Tabuleiro(int numJogadores)
    {
        this.numJogadores = numJogadores;
        jogadores = new ArrayList<Jogador>(numJogadores);
        propriedades = new ArrayList<Propriedade>();
        cartasDeSorteNaMesa = new ArrayList<CartaSorte>();
    }

    public Jogador getJogador(int id)
    {
        // não necessariamente o id do jogador será igual sua chave na lista
        for (int i = 0; i < jogadores.size(); i++)
        {
            if (jogadores.get(i).getId() == id)
            {
                return jogadores.get(i);
            }
        }

        //throw new Exception("Jogador de id " + id + " inexistente no tabuleiro!");
        AppUtils.log("ERRO: Jogador de id " + id + " inexistente no tabuleiro!");
        return null;
    }

    public boolean addJogador(Jogador j)
    {
        return jogadores.add(j);
    }

    public boolean removeJogador(Jogador j)
    {
        return jogadores.remove(j);
    }

    public boolean removeJogadorPorID(int id)
    {
        for (int i = 0; i < jogadores.size(); i++)
        {
            if (jogadores.get(i).getId() == id)
            {
                jogadores.remove(i);
                return true; // já quebra o loop
            }
        }

        return false;
    }

    public Propriedade getPropriedade(int id)
    {
        return propriedades.get(id - 1);
    }

    public boolean addPropriedade(Propriedade p)
    {
        return propriedades.add(p);
    }

    public boolean removePropriedade(Propriedade p)
    {
        return propriedades.remove(p);
    }

    public boolean removePropriedadePorID(int id)
    {
        for (int i = 0; i < propriedades.size(); i++)
        {
            if (propriedades.get(i).getId() == id)
            {
                propriedades.remove(i);
                return true; // já quebra o loop
            }
        }

        return false;
    }

    public ArrayList<CartaSorte> getCartasDeSorteNaMesa() {
        return cartasDeSorteNaMesa;
    }
    
    public int getNumJogadores() {
        return numJogadores;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public ArrayList<Propriedade> getPropriedades() {
        return propriedades;
    }

    @Override
    public String toString() {
        return "Tabuleiro:\nJogadores: " + jogadores + "\nPropriedades: " + propriedades + "\n";
    }

    public String listagemJogadores()
    {
        String str = "";

        for (Jogador j : jogadores)
        {
            str += j.getId() + " - " + j.getNome() + " - $"
                + j.getDinheiro() + "\n";
        }

        return str;
    }
}
