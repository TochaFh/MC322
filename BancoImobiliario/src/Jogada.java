import java.util.Random;

public class Jogada
{
    int numeroDaRodada;
    int dado1, dado2;
    int somaDados;
    Jogador jogador;

    public void rejogar(Jogador j, int numeroDaRodada)
    {
        jogador = j;
        
        Random rnd = new Random();
        dado1 = rnd.nextInt(6) + 1;
        dado2 = rnd.nextInt(6) + 1;
        somaDados = dado1 + dado2;
    }

    public String relatorioJogada()
    {
        // TODO: log da jogada
        return "relatorio (não implementado) rodada " + numeroDaRodada + ", jogador " + jogador.getId();
    }

    @Override
    public String toString()
    {
        return "Tirou " + dado1 + " e " + dado2 + ". Total: " + (dado1 + dado2);
    }
}
