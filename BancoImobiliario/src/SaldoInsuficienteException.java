public class SaldoInsuficienteException extends Exception
{
    public SaldoInsuficienteException(Jogador j, Propriedade p)
    {
        super("O jogador " + j.getNome() + " não possui saldo suficiente para comprar a propriedade " + p.getNome() + "!");
    }

    public SaldoInsuficienteException(Jogador j, int valorAPagar)
    {
        super("O jogador " + j.getNome() + " não possui saldo suficiente para pagar $" + valorAPagar + "!");
    }
}
