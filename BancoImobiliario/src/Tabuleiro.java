import java.util.ArrayList;

/* Armazena uma lista de IDs de jogadores e outra de IDs de propriedades
 *
 * Por enquanto não tem muita utilidade, será mudar para que as listas
 * armazenem as referências diretas dos jogadores e das propriedades.
*/
public class Tabuleiro
{
    private ArrayList<Integer> jogadores;

    private ArrayList<Integer> propriedades;

    // Construtores
    public Tabuleiro()
    {
        jogadores = new ArrayList<Integer>();
        propriedades = new ArrayList<Integer>();
    }

    public boolean addJogador(int id)
    {
        return jogadores.add(id);
    }

    public boolean removeJogador(int id)
    {
        // fazendo o "cast" para object usamos a sobrecarga do método remove que remove a
        // primeria ocorrência do elemento na lista (não trata o int como um index da ArrayList)
        return jogadores.remove((Object) id);
    }

    public boolean addPropriedade(int id)
    {
        return propriedades.add(id);
    }

    public boolean removePropriedade(int id)
    {
        return propriedades.remove((Object) id);
    }

    @Override
    public String toString() {
        return "Tabuleiro:\nJogadores: " + jogadores + "\nPropriedades: " + propriedades + "\n";
    }
}
