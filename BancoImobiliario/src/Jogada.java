import java.util.Random;

public class Jogada
{
    int dado1, dado2;
    int somaDados;
    Jogador jogador;

    public void rejogar(Jogador j)
    {
        jogador = j;
        
        Random rnd = new Random();
        dado1 = rnd.nextInt(6) + 1;
        dado2 = rnd.nextInt(6) + 1;
        somaDados = dado1 + dado2;
    }

    @Override
    public String toString()
    {
        return "tirou " + dado1 + " e " + dado2 + ". Total: " + (dado1 + dado2);
    }
}
