public class Main
{
    public static void main(String[] args)
    {
        AppUtils.openScan();
        System.out.println("\n** BANCO IMOBILIARIO **\n\n");

        int numJogadores = AppUtils.readInt("Digite o número de jogadores: ");

        Tabuleiro tab = new Tabuleiro();
        Jogador[] jogadores = new Jogador[numJogadores];
        Peca[] pecas = new Peca[numJogadores];

        Jogador j;
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

        AppUtils.log("\n\nJogadores cadastrados:\n");
        for (int i = 0; i < numJogadores; i++)
        {
            AppUtils.log(jogadores[i]);
            AppUtils.log("Peça: " + pecas[i].getCor() + "\n");
        }

        CartaSorte testeCartaSorte = new CartaSorte(34, "Curso 34");
        testeCartaSorte.setTempo(5);
        testeCartaSorte.setEfeito(-70);
        AppUtils.log("\nTeste CartaSorte: " + testeCartaSorte.getDescricao()
                     + " (" + testeCartaSorte.getId() + ")");

 
        // testando propriedades
        Propriedade[] props = new Propriedade[40];

        // propriedade do tipo propriedade base mesmo
        props[7] = new Propriedade("Propriedade apenas", 50, 5);
        tab.addPropriedade(7);

        props[19] = new Terreno("Av. Orosimbo Maia", 145, 12, 20, 30);
        tab.addPropriedade(19);

        props[16] = new Estacao("Demogorgon", 75, 10);
        tab.addPropriedade(19);

        props[30] = new ServicoPublico("Água", 60, 5);
        tab.addPropriedade(30);

        // propriedade base
        props[7].calcularAluguel();

        // terreno
        props[19].calcularAluguel();

        // estação
        props[16].calcularAluguel();

        // serviço público (precisa do argumento dados, como serviço, se não vai na implemenntação da base)
        props[30].calcularAluguel();
        ((ServicoPublico)props[30]).calcularAluguel(7);

        tab.removePropriedade(16);

        AppUtils.log("\n\n");
        AppUtils.log(tab);

        AppUtils.closeScan();
    }
}
