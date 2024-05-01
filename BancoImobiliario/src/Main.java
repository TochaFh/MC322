import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        AppUtils.openScan();

        System.out.println("\n** BANCO IMOBILIARIO **\n\n");

        Tabuleiro tab = setupTabuleiro();
        gerarPropriedadesTabuleiro(tab);

        AppUtils.log(tab);

        /*
        // testando CartaSorte
        CartaSorte testeCartaSorte = new CartaSorte(34, "Curso 34");
        testeCartaSorte.setTempo(5);
        testeCartaSorte.setEfeito(-70);
        AppUtils.log("\nTeste CartaSorte: " + testeCartaSorte.getDescricao()
                     + " (" + testeCartaSorte.getId() + ")\n");

 
        // testando propriedades

        // propriedade do tipo propriedade base mesmo
        var a = new Propriedade("Propriedade apenas", 50, 5);
        tab.addPropriedade(a);

        // terreno
        var b = new Terreno("Av. Orosimbo Maia", 145, 12, 20, 30);
        tab.addPropriedade(b);

        // estação
        var c = new Estacao("Demogorgon", 75, 10);
        tab.addPropriedade(c);

        // serviço público
        var d = new ServicoPublico("Água", 60, 5);
        tab.addPropriedade(d);

        // o método 'calcularAluguel' deve ter saídas diferentes para cada tipo de propriedade

        // propriedade base
        a.calcularAluguel(); // não printa nada

        // terreno
        b.calcularAluguel();

        // estação
        c.calcularAluguel();

        // serviço público (precisa do argumento dados, como serviço, se não vai na implemenntação da base)
        d.calcularAluguel(); // não printa nada pois não foi implementada pelo ServicoPublico

        // precisa fazer um cast pois o obverload do método com o argumento 'dados' não existe na classe mãe Propriedade
        d.calcularAluguel(7);

        // testando a função de remover propriedade do tabuleiro
        tab.removePropriedadePorID(2);

        // printando o que há no tabuleiro
        AppUtils.log("\n");
        AppUtils.log(tab);

        
        // compra de terrenos / casas

        int id = AppUtils.readInt("\nEscolha um jogador (id) para tentar comprar o terreno na Av. Orosimbo Maia: ");

        // apenas teste... pegando o id que sabe-se que é um terreno e fazendo o cast
        Terreno t = b;

        if (id <= tab.getNumJogadores() && id > 0) // se o jogador do id existe
        {
            j = tab.getJogador(id - 1);

            if (j.getDinheiro() >= t.getPreco()) // se tem dinheiro para comprar
            {
                AppUtils.log(j.getNome() + " tem $" + j.getDinheiro());

                // jogador comprando o terreno
                // por enquanto simbólico, depois haverá uma lista das propriedades do jogador
                j.somarDinheiro(- t.getPreco());

                AppUtils.log(j.getNome() + " comprou o terreno na Av. Orosimbo Maia por $" + t.getPreco());
                AppUtils.log(j.getNome() + " ficou com $" + j.getDinheiro());

                // jogador comprando o terreno
                j.somarDinheiro(- t.getValorCasa());
                t.comprarCasa();

                AppUtils.log(j.getNome() + " agora comprou uma casa na Av. Orosimbo Maia por $" + t.getValorCasa());
                AppUtils.log(j.getNome() + " ficou com $" + j.getDinheiro());
                AppUtils.log("Agora o jogador tem " + t.getNumeroCasas() + " casa(s) na Av. Orosimbo Maia.");
            }
            else
            {
                AppUtils.log(j.getNome() + " tem $" + j.getDinheiro());
                AppUtils.log("Não é o suficiente para comprar o terreno na Av. Orosimbo Maia que vale $" + t.getPreco());
            }
        }

        // testando a remoção de jogadores do tabuleiro
        id = AppUtils.readInt("\nDigite um jogador (id) para remover do tabuleiro: ");
        if (id <= tab.getNumJogadores() && id > 0) // se o jogador do id existe
        {
            tab.removeJogadorPorID(id);
        }

        // printando novamente o que há no tabuleiro (um jogador pode ter sido removido)
        AppUtils.log(tab); */

        AppUtils.closeScan();
    }

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
