import java.util.ArrayList;

/* Armazena uma lista de jogadores e outra de propriedades
*/
public class Tabuleiro implements ISalvavel
{
    private ArrayList<Jogador> jogadores;
    private ArrayList<CartaSorte> cartasDeSorteNaMesa;
    private ArrayList<Propriedade> propriedades;

    private ArrayList<Estacao> estacoes;
    private ArrayList<ServicoPublico> servicos;
    private ArrayList<Terreno> terrenos;

    private String log;
    private Jogada ultimaJogada;

    // TODO; adicionar sistema de casas no tabuleiro (casa inicial com salário e casas de carta de sorte ou reves)

    // Construtores
    public Tabuleiro(int numJogadores)
    {
        jogadores = new ArrayList<Jogador>(numJogadores);
        cartasDeSorteNaMesa = new ArrayList<CartaSorte>();
        propriedades = new ArrayList<Propriedade>();

        estacoes = new ArrayList<Estacao>();
        servicos = new ArrayList<ServicoPublico>();
        terrenos = new ArrayList<Terreno>();

        ultimaJogada = new Jogada();
    }

    /**
     * @param index NÃO é o id do jogador
     * @return jogador do index na lista de jogadores (Que ainda estão np jogo)
     */
    public Jogador getJogador(int index)
    {
        return jogadores.get(index);
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

    public Jogada getUltimaJogada()
    {
        return ultimaJogada;
    }

    public Propriedade getPropriedade(int id)
    {
        return propriedades.get(id - 1);
    }

    public boolean addPropriedade(Propriedade p)
    {
        if (p instanceof Terreno)
            terrenos.add((Terreno)p);

        else if (p instanceof ServicoPublico)
            servicos.add((ServicoPublico)p);

        else if (p instanceof Estacao)
            estacoes.add((Estacao)p);

        return propriedades.add(p);
    }

    /*public boolean removePropriedade(Propriedade p)
    {
        return propriedades.remove(p);
    }*/

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
        return jogadores.size();
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public ArrayList<Propriedade> getPropriedades() {
        return propriedades;
    }

    public int getNumCasas()
    {
        return propriedades.size();
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
                + j.getSaldo() + "\n";
        }

        return str;
    }

    public boolean todosServicosMesmoDono()
    {
        Jogador dono = servicos.get(0).getDono();
        for (ServicoPublico s : servicos) {
            if (dono != s.getDono())
            {
                return false;
            }
        }

        return true;
    }

    public boolean todasEstacoesMesmoDono()
    {
        Jogador dono = estacoes.get(0).getDono();
        for (Estacao e : estacoes) {
            if (dono != e.getDono())
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public void salvaLog() {

        //TODO: implementar log
        log = "log não carregado...";

        String nomeArquivo = "log_" + Main.getCodigoJogo() + ".txt";
        AppUtils.escreverAqrquivoTxt(nomeArquivo, log);
    }


    /**
     * Faz um novo lançaento de dados e move a peça do jogador que os lançou.
     * Atualiza o estado da 'ultimaJogada' chamando o método 'rejogar()'
     * @param j jogador que está jogando os dados
     */
    public void novaJogada(Jogador j, int numeroDaRodada)
    {
        ultimaJogada.rejogar(j, numeroDaRodada);
        j.getPeca().move(ultimaJogada.somaDados, this);
    }
}
