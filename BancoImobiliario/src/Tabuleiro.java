import java.util.ArrayList;

/* Armazena uma lista de jogadores e outra de propriedades
*/
public class Tabuleiro
{
    private int numJogadores;
    private ArrayList<Jogador> jogadores;
    private ArrayList<Propriedade> propriedades;

    // Construtores
    public Tabuleiro(int numJogadores)
    {
        this.numJogadores = numJogadores;
        jogadores = new ArrayList<Jogador>(numJogadores);
        propriedades = new ArrayList<Propriedade>();
    }

    public Jogador getJogador(int id)
    {
        for (int i = 0; i < jogadores.size(); i++)
        {
            if (jogadores.get(i).getId() == id)
            {
                return jogadores.get(i);
            }
        }

        //throw new Exception("Jogador de id " + id + " inexistente no tabuleiro!");
        AppUtils.log("Jogador de id " + id + " inexistente no tabuleiro!");
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

    @Override
    public String toString() {
        return "Tabuleiro:\nJogadores: " + jogadores + "\nPropriedades: " + propriedades + "\n";
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
}
