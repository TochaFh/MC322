import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        AppUtils.openScan();
        System.out.println("\n** BANCO IMOBILIARIO **\n\n");


        // LEITURA DE JOGADORES
        // por enquanto são adicionados a um vetor com chaves
        // equivalentes aos [IDs - 1] gerados pelo contrutor de Jogador
        // mesma coisa para as peças
        // Os IDs são adicionados au tabuleiro conforme a leitura

        int numJogadores = AppUtils.readInt("Digite o número de jogadores: ");

        Tabuleiro tab = new Tabuleiro();
        Jogador[] jogadores = new Jogador[numJogadores];
        Peca[] pecas = new Peca[numJogadores];

        Jogador j; // variável auxiliar

        // leitura das informações d ecada jogador / peça
        for (int i = 0; i < numJogadores; i++)
        {
            AppUtils.log("\n--------------\nJogador " + (i+1));
            
            j = new Jogador(
                AppUtils.readLine("Nome: "),
                AppUtils.readLine("CPF: "),
                AppUtils.readLine("Email: "),
                AppUtils.readLine("Foto: ")
            );

            jogadores[i] = j;
            tab.addJogador(j.getId());
            
            pecas[i] = new Peca(
                AppUtils.readLine("Cor da peça: ")
            );
        }

        // para testar o "somarDinheiro" de forma mais divertida
        Random rnd = new Random();

        // Printando os jogadores cadastrados
        AppUtils.log("\n\nJogadores cadastrados:\n");
        for (int i = 0; i < numJogadores; i++)
        {
            // somando uma quantia aleátoria (entre 0 e 200) ao saldo do jogador
            jogadores[i].somarDinheiro(rnd.nextInt(200));

            AppUtils.log(jogadores[i]); // fazendo uso do toString overrided
            AppUtils.log("Peça: " + pecas[i].getCor() + "\n");
        }

        // testando CartaSorte
        CartaSorte testeCartaSorte = new CartaSorte(34, "Curso 34");
        testeCartaSorte.setTempo(5);
        testeCartaSorte.setEfeito(-70);
        AppUtils.log("\nTeste CartaSorte: " + testeCartaSorte.getDescricao()
                     + " (" + testeCartaSorte.getId() + ")\n");

 
        // testando propriedades

        // por enquanto é só teste então cirei um vetor grande para
        // armazenar até 40 proprieades, mas só estaremos usando 4
        Propriedade[] props = new Propriedade[40];

        // propriedade do tipo propriedade base mesmo
        props[7] = new Propriedade("Propriedade apenas", 50, 5);
        tab.addPropriedade(7);

        // terreno
        props[19] = new Terreno("Av. Orosimbo Maia", 145, 12, 20, 30);
        tab.addPropriedade(19);

        // estação
        props[16] = new Estacao("Demogorgon", 75, 10);
        tab.addPropriedade(16);

        // serviço público
        props[30] = new ServicoPublico("Água", 60, 5);
        tab.addPropriedade(30);

        // o método 'calcularAluguel' deve ter saídas diferentes para cada tipo de propriedade

        // propriedade base
        props[7].calcularAluguel(); // não printa nada

        // terreno
        props[19].calcularAluguel();

        // estação
        props[16].calcularAluguel();

        // serviço público (precisa do argumento dados, como serviço, se não vai na implemenntação da base)
        props[30].calcularAluguel(); // não printa nada pois não foi implementada pelo ServicoPublico

        // precisa fazer um cast pois o obverload do método com o argumento 'dados' não existe na classe mãe Propriedade
        ((ServicoPublico)props[30]).calcularAluguel(7);

        // testando a função de remover propriedade do tabuleiro
        tab.removePropriedade(16);

        // printando o que há no tabuleiro
        AppUtils.log("\n");
        AppUtils.log(tab);

        
        // compra de terrenos / casas

        int id = AppUtils.readInt("\nEscolha um jogador (id) para tentar comprar o terreno na Av. Orosimbo Maia: ");

        // apenas teste... pegando o id que sabe-se que é um terreno e fazendo o cast
        Terreno t = (Terreno)props[19];

        if (id <= numJogadores && id > 0) // se o jogador do id existe
        {
            j = jogadores[id - 1];

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
        if (id <= numJogadores && id > 0) // se o jogador do id existe
        {
            tab.removeJogador(id);
        }

        // printando novamente o que há no tabuleiro (um jogador pode ter sido removido)
        AppUtils.log(tab);

        AppUtils.closeScan();
    } 
}
