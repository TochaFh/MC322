import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        AppUtils.openScan();

        System.out.println("\n** BANCO IMOBILIARIO **\n\n");

        Tabuleiro tab = setupTabuleiro();
        gerarPropriedadesTabuleiro(tab);

        String input = AppUtils.readLine("Ver informações do tabuleiro?\n[Y/n]: ");
        if (input.toUpperCase().startsWith("Y"))
        {
            AppUtils.log(tab);
        }
        AppUtils.log("\n");

        int id;
        Jogador j;

        for (int i = 1; ; i++)
        {
            AppUtils.log("------ RODADA " + i + " ------\n");

            AppUtils.log("Jogadores:\n" + tab.listagemJogadores());
            id = AppUtils.readInt("Digite o id do jogador a ser verificado (0 para encerrar o programa):");

            if (id == 0) break;

            j = tab.getJogador(id);

            AppUtils.log("Jogador " + j.getId() + ":");
            AppUtils.log(j.getNome() + "\n" + "$" + j.getDinheiro());
            AppUtils.log("Posição: " + j.getPeca().getPosicao());
            AppUtils.log("Propriedades: " + j.listaPropriedades());
            AppUtils.log("Cartas de sorte guardadas: " + j.listaCartasDeSorte());

            // TODO: aqui serão realizadas as jogadas de cada jogador
            // jogar os dados, mover a posição e interagir com a casa que cair
            // comprar propriedades
            // cartas de sorte

            AppUtils.log("\n\n");
        }

        AppUtils.closeScan();
    }

    // cria um tabuleiro e adiciona os jogadores do input
    public static Tabuleiro setupTabuleiro()
    {
        int n = AppUtils.readInt("Digite o número de jogadores: ");
        Tabuleiro tab = new Tabuleiro(n);

        Jogador.DINHEIRO_INICIAL = AppUtils.readInt("Digite a quantia de dinheiro inicial de cada jogadar: ");

        for (int i = 0; i < n; i++)
        {
            AppUtils.log("\n--------------\nJogador " + (i+1));
            tab.addJogador(lerJogador());
        }

        AppUtils.log("\n--------------\n");

        return tab;
    }

    // lê o input de um jogador
    public static Jogador lerJogador()
    {
        return new Jogador(
            AppUtils.readLine("Nome: "),
            AppUtils.readLine("CPF: "), 
            AppUtils.readLine("Email: "),
            AppUtils.readLine("Foto: "),
            AppUtils.readLine("Cor da peça: ")
        );
    }

    // 12 nomes de propriedade para a simulação
    private static final String[] NOMES_DE_TERRENO = {
        "Bangu",
        "Botafogo",
        "Av. Brasil",
        "Avenida Paulisata",
        "Jardim Europa",
        "Copacabana",
        "Avenida Vieira Souto",
        "Avenida Atlântica",
        "Ipanema",
        "Jardim Paulista",
        "Brooklin",
        "Leblon"
    };

    // adiciona propriedades ao tabuleiro
    // por enquanto é mais para testes
    public static void gerarPropriedadesTabuleiro(Tabuleiro tab)
    {
        Random rnd = new Random();
        Propriedade p;
        int preco, aluguel, valorCasa, valorHotel;

        for (int i = 0; i < 12; i++)
        {
            // obs: por motivos apenas de simulação... esse cálculo nem faz tanto sentido
            // na versão final haverá uma base de dados com valores pré determinados de cada coisa
            preco = 150 + rnd.nextInt(500);
            aluguel = preco / 16 + rnd.nextInt(7);
            valorCasa = (aluguel * 3) - rnd.nextInt(5);
            valorCasa = valorCasa - (valorCasa % 10);
            valorHotel = valorCasa + 10 + (rnd.nextBoolean() ? 0 : 5);

            p = new Terreno(NOMES_DE_TERRENO[i], preco, aluguel, valorCasa, valorHotel);
            tab.addPropriedade(p);
        }

        // TODO: adicionar estações e serviços públicos
    }
}
