import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Main
{
    private static Tabuleiro tabuleiroJogo;

    private static String codigoJogo;
    
    public static void main(String[] args)
    {
        AppUtils.openScan();

        codigoJogo = gerarCodigoJogo();
        
        System.out.println("\n** BANCO IMOBILIARIO **      (jogo: " + codigoJogo + ")\n\n");

        Tabuleiro tab = setupTabuleiro();
        gerarPropriedadesTabuleiro(tab);

        tabuleiroJogo = tab;

        if (AppUtils.yesOrNo("Ver informações do tabuleiro?\n[Y/n]: "))
        {
            AppUtils.log(tab);
            AppUtils.esperarEnterKey("Continuar (enter)");
        }

        AppUtils.log("\n");

        Jogador j;
        int posicao;
        Propriedade p;

        for (int i = 1; ; i++)
        {
            AppUtils.log("------ RODADA " + i + " ------\n");

            Jogada ultimaJogada = tab.getUltimaJogada();

            // jogadas
            for (int h = 1; h <= tab.getNumJogadores(); h++)
            {
                j = tab.getJogador(h);
                AppUtils.log("\n");
                j.printBasico();

                AppUtils.log("\nRolar dados...");
                AppUtils.delay(1000);
                
                // atualiza o estado da "ultimaJogada"
                tab.novaJogada(j, i);
                AppUtils.log(ultimaJogada);

                posicao = j.getPeca().getPosicao();
                AppUtils.log("> Caiu na posição " + posicao + ".");
                ultimaJogada.log("Caiu na posição " + posicao + ".");
                p = tab.getPropriedade(posicao);
                AppUtils.log("> Propriedade: " + p.getNome());

                if (!p.temDono()) // do banco
                {
                    AppUtils.log("> Preço: $" + p.getPreco());

                    if (AppUtils.yesOrNo("- Deseja comprar a propriedade (" + p.getNome() + ")? [Y/n] "))
                    {
                        // a função 'comprarPropriedade' lança uma excessão no caso do jogador não ter dinheiro suficiente
                        try
                        {
                            j.comprarPropriedade(p);
                            AppUtils.log("Propriedade (" + p.getNome() + ") comprada com sucesso!");
                            ultimaJogada.log("Comprou a propriedade " + p.getNome());
                        }
                        catch (SaldoInsuficienteException e)
                        {
                            AppUtils.log(e.getMessage());
                        }

                        AppUtils.esperarEnterKey("(pressione 'Enter')");
                    }

                }
                else if (p.getDono() != j) // tem dono e não é o próprio jogador j
                {
                    AppUtils.log("> [dono(a):" + p.getDono().getNome() + "]");
                    AppUtils.esperarEnterKey(">> Aluguel de $" + p.calcularAluguel() + " a pagar! (pressione 'Enter')");

                    try
                    {
                        j.pagarAluguel(p);
                        ultimaJogada.log("Pagou ALUGUEL da propriedade " + p.getNome());
                    }
                    catch (SaldoInsuficienteException e)
                    {
                        AppUtils.log(e.getMessage());

                        AppUtils.log("Jogador " + j.getNome() + " FALIU!");
                        ultimaJogada.log("Jogador " + j.getNome() + " FALIU! Ao ser taxado por aluguel");
                        tab.removeJogador(j);
                        if (tab.getNumJogadores() <= 1)
                        {
                            break;
                        }
                    }
                }
                else
                {
                    AppUtils.esperarEnterKey("Caiu em sua própria propriedade! Nada de especial aqui. (pressione 'Enter')");
                }

                // To do: opção de comprar casinhas/hotel

                tab.fimJogada();
            }

            AppUtils.log("\n\n");

            if (tab.getNumJogadores() <= 1)
            {
                j = tab.getJogadores().get(0);
                break;
            }
        }

        AppUtils.log("\n\n");

        for (int i = 0; i < 5; i++)
        {
            AppUtils.log("-");
            AppUtils.delay(200);
        }
        
        AppUtils.log("- Jogador " + j.getNome() + " VENCEU o jogo!");

        tab.salvaLog();

        AppUtils.closeScan();
    }

    // cria um tabuleiro e adiciona os jogadores do input
    private static Tabuleiro setupTabuleiro()
    {
        int n;
        do
        {
            n = AppUtils.readInt("Digite o número de jogadores (deve ser igual ou maior a 2): ");
        } while (n < 2);
        
        Tabuleiro tab = new Tabuleiro(n);

        int dinheiroInicial = AppUtils.readInt("Digite a quantia de dinheiro inicial de cada jogadar: ");

        while (dinheiroInicial <= 0)
        {
            dinheiroInicial = AppUtils.readInt("Dinheiro inicial deve ser maior que 0! Digite novamente: ");
        }

        Jogador.DINHEIRO_INICIAL = dinheiroInicial;

        for (int i = 0; i < n; i++)
        {
            AppUtils.log("\n--------------\nJogador " + (i+1));
            tab.addJogador(lerJogador());
        }

        AppUtils.log("\n--------------\n");

        return tab;
    }

    // lê o input de um jogador
    private static Jogador lerJogador()
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

    private static final String[] NOMES_DE_SERVICO = {
        "Companhia de água",
        "Companhia de luz",
        "Companhia de turismo"
    };

    private static final String[] NOMES_DE_ESTACAO = {
        "Estação da Luz",
        "Estação Estática",
        "Estação Atocha"
    };

    // adiciona propriedades ao tabuleiro
    // por enquanto é mais para testes
    private static void gerarPropriedadesTabuleiro(Tabuleiro tab)
    {
        Random rnd = new Random();
        Propriedade p;
        int preco, aluguel, valorCasa, valorHotel;

        int j = 0;

        for (int i = 1; i <= 12; i++)
        {
            // obs: por motivos apenas de simulação... esse cálculo nem faz tanto sentido
            // na versão final haverá uma base de dados com valores pré determinados de cada coisa
            preco = 150 + rnd.nextInt(500);
            aluguel = preco / 16 + rnd.nextInt(7);
            valorCasa = (aluguel * 3) - rnd.nextInt(5);
            valorCasa = valorCasa - (valorCasa % 10);
            valorHotel = valorCasa + 10 + (rnd.nextBoolean() ? 0 : 5);

            p = new Terreno(NOMES_DE_TERRENO[i - 1], preco, aluguel, valorCasa, valorHotel);
            tab.addPropriedade(p);

            // adiciona uma estação e um serviço publico a cada 4 casas
            if (i % 4 == 0)
            {
                tab.addPropriedade(new ServicoPublico(NOMES_DE_SERVICO[j], 300, 30));
                tab.addPropriedade(new Estacao(NOMES_DE_ESTACAO[j], 280, 50));
                j++;
            }
        }
    }

    // gambiarrinha :D
    // O tabuleiro do jogo fica de fácil acesso para todas as classes
    public static Tabuleiro getTabuleiro()
    {
        return tabuleiroJogo;
    }

    private static String gerarCodigoJogo()
    {
        String s = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        s = s.substring(0, 19);
        s = s.replace('-', '.');
        s = s.replace(':', '.');
        s = s.replace('T', '_');

        return s;
    }

    public static String getCodigoJogo()
    {
        return codigoJogo;
    }
}
