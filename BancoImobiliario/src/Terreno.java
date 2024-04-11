public class Terreno extends Propriedade
{
    private int numeroCasas;
    private int valorCasa;
    private int valorHotel;
    private boolean hotel;


    // Construtores

    public Terreno(String nome, int preco, int aluguel, int valorCasa, int valorHotel) {
        super(nome, preco, aluguel);
        this.valorCasa = valorCasa;
        this.valorHotel = valorHotel;
    }


    // Getters e setters

    public int getNumeroCasas()
    {
        return numeroCasas;
    }

    public void setNumeroCasas(int numeroCasas)
    {
        this.numeroCasas = numeroCasas;
    }

    // os valores de casa e hotel não possuem setters pois não serão mudados durante o jogo

    public int getValorCasa()
    {
        return valorCasa;
    }

    public int getValorHotel()
    {
        return valorHotel;
    }

    public boolean getHotel()
    {
        return hotel;
    }

    public void setHotel(boolean hotel)
    {
        this.hotel = hotel;
    }


    // Métodos

    public int calcularAluguel()
    {
        AppUtils.log("$ calculando aluguel do TERRENO " + nome);
        return numeroCasas * this.aluguel;
    }

    public boolean comprarCasa()
    {
        numeroCasas += 1;
        return true;
    }

    public boolean comprarHotel()
    {
        hotel = true;
        return true;
    }
}
