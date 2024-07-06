import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Classe de utilidades para o programa
 *
 * Possui métodos de saída, input e validação de email e cpf.
 * 
 * Está funcionando como um wrapper para saída e entrada, o que pode facilitar
 * mais pra frente para mudar a forma de saída / entrada do program, principalmente
 * para testes / debug.
 * 
 * Antes de usar os métodos de input, chamar o método 'openScan()'
*/
public class AppUtils
{
    // patterns para validação de email
    private static final String EMAIL_EXPRESSAO_REGEX = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    private static final Pattern PATTERN_EMAIL = Pattern.compile(EMAIL_EXPRESSAO_REGEX, Pattern.CASE_INSENSITIVE);

    private static Scanner scan;

    /**
     * Inicializa um scanner com input de System.in
     */
    public static void openScan()
    {
        scan = new Scanner(System.in);
    }

    /**
     * Dá um close no scanner, se houver e estiver aberto
     */
    public static void closeScan()
    {
        if (scan != null) scan.close();
    }
    
    /**
     * Mesmo que 'System.out.println(obj)'
     */
    public static void log(Object obj)
    {
        System.out.println(obj);
    }

    /**
     * Mesmo que 'System.out.println(str)'
     */
    public static void log(String str)
    {
        System.out.println(str);
    }

    /**
     * @return a instância de Scanner utilizada nesta classe
     */
    public static Scanner getScan()
    {
        return scan;
    }

    /**
     * Equivalente a 'scan.nextLine()'
     * @return String lida
     */
    public static String readLine()
    {
        return scan.nextLine();
    }

    /**
     * Equivalente a 'scan.nextLine()'
     * @param msg uma mensagem a ser impressa antes de ler o input
     * @return String lida
     */
    public static String readLine(String msg)
    {
        System.out.print(msg);
        return readLine();
    }

    /**
     * Lê o input de um int e leva o 'cursor' do scan para a próxima linha
     * @return int lido
     */
    public static int readInt()
    {
        int x;

        try
        {
            x = scan.nextInt();
            readLine();
        } 
        catch (InputMismatchException e)
        {
            readLine();
            System.out.print("**O termo digitado não é um número válido! Digite novamente: ");
            x = readInt();
        }
        
        return x;
    }

    /**
     * Lê o input de um int e leva o 'cursor' do scan para a próxima linha
     * @param msg uma mensagem a ser imprimida antes de ler o input
     * @return int lido
     */
    public static int readInt(String msg)
    {
        System.out.print(msg);
        return readInt();
    }

    // Validações

    /**
     * @param cpf String
     * @return true se o cpf for válido
     */
    public static boolean validarCpf(String cpf)
    {
        String digitos = cpf.replaceAll("[.-]", "");

        // se o número de dígitos é diferente de 11
        if (digitos.length() != 11) return false;
        
        // se todos os dígitos são iguais
        if (numerosTodosIguaisCpf(digitos)) return false;

        // transformando o cpf numa array de ints
        int[] numeros = new int[11];
        for (int i = 0; i < 11; i++)
        {
            numeros[i] = Integer.parseInt(String.valueOf(digitos.charAt(i)));
        }

        // cálculo dos dígitos verificadores
        int primeiroDV = calcularDV(numeros, 0);
        int segundoDV = calcularDV(numeros, 1);

        return primeiroDV == numeros[9] && segundoDV == numeros[10];
    }

    // auxiliar - diz se os dígitos do cpf são todos o mesmo
    private static boolean numerosTodosIguaisCpf(String digitos)
    {
        for (int i = 1; i < 11; i++)
        {
            if (digitos.charAt(0) != digitos.charAt(i)) return false;
        }

        return true;
    }

    // auxiliar - calcula o DV (dígito verificador) considerando os 9 numeros a partir do index start
    private static int calcularDV(int[] numeros, int start)
    {
        int soma = 0;

        // na prática, o start é quantas posições a frente de i=0 começa
        for (int i = 0; i < 9; i++)
        {
            soma += (10 - i) * numeros[i + start];
        }

        int resto = soma % 11;
        int dv = (resto <= 1) ? 0 : 11 - resto;

        return dv;
    }

    /**
     * @param email String
     * @return true se o email for válido
     */
    public static boolean validarEmail(String email)
    {
        Matcher matcher = PATTERN_EMAIL.matcher(email);
        return matcher.matches();
    }

    public static void escreverAqrquivoTxt(String caminhoArquivo, String texto) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo));
            writer.write(texto);

            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void delay(int millis)
    {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*public static void esperarEnterKey()
    {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
