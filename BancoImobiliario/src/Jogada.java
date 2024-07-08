import java.util.Random;

public class Jogada
{
    private int numeroDaRodada;
    private int dado1, dado2;
    private int somaDados;

    private Jogador jogador;
    private String logPlus;

    public int getSomaDados()
    {
        return somaDados;
    }

    public void rejogar(Jogador j, int numeroDaRodada)
    {
        this.numeroDaRodada = numeroDaRodada;
        jogador = j;
        
        Random rnd = new Random();
        dado1 = rnd.nextInt(6) + 1;
        dado2 = rnd.nextInt(6) + 1;
        somaDados = dado1 + dado2;

        logPlus = "";
    }

    public void log(String logMais)
    {
        logPlus += logMais + "\n"; // :D
    }

    public String relatorioJogada()
    {
        return "> Rodada " + numeroDaRodada + ", jogador " + jogador.getNome()
            + ":\n" + this.toString()
            + "\n" + logPlus;
    }

    @Override
    public String toString()
    {
        return "Tirou " + dado1 + " e " + dado2 + ". Total: " + (dado1 + dado2);
    }
}
