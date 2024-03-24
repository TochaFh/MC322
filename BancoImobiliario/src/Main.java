public class Main
{
    public static void main(String[] args)
    {
        System.out.println("\n** BANCO IMOBILIARIO **\n\n");

        // criando jogadores

        // bob começa com cpf e email inválidos
        Jogador bob = new Jogador("Bob", "333.444.555-77", // cpf inválido
                                "email @invalido", "issoe/umcaminho/defoto/muitolegal");
        // o print do toString() mostra as informações de cpf e email do jogador e diz se são inválidos
        System.out.println(bob);
        
        // rafael tá tudo certinho
        Jogador rafael = new Jogador("Rafael", "572.980.310-90", // cpf válido meramente ilustrativo
                                "r175100@dac.unicamp.br", "issoe/outrocaminho/defoto/muitolegal");
        System.out.println(rafael);

        // colocando cpf e email válidos para o bob
        System.out.println("\nAtualizando dados do bob...\n");
        bob.setNome("Bob Atualizado");
        bob.setCpf("412.257.860-45");
        bob.setEmail("bob13@hotmail.com");
        System.out.println(bob);

        // criando uma peça e printando informações
        Peca peca1 = new Peca("vermelho", 2);
        System.out.println("Peça criada!\nCor: " + peca1.getCor() + "\nPosição: " + peca1.getPosicao());

        // criando uma carta de sorte
        CartaSorte cartinha = new CartaSorte(40028922, "Bom dia & cia!");
        // adicionando uma ação na carta
        cartinha.setAcao("Muita felicidade :D");
        System.out.println("\nCartinha de sorte criada!");
        System.out.println("Id: " + cartinha.getId() + "\nDescrição: " + cartinha.getDescricao() + "\nAção: " + cartinha.getAcao());
    }
}
