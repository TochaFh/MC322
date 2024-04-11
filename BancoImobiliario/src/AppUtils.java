import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils
{
    // patterns para validação de email
    private static final String EMAIL_EXPRESSAO_REGEX = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    private static final Pattern PATTERN_EMAIL = Pattern.compile(EMAIL_EXPRESSAO_REGEX, Pattern.CASE_INSENSITIVE);

    private static Scanner scan;

    public static void openScan()
    {
        scan = new Scanner(System.in);
    }

    public static void closeScan()
    {
        scan.close();
    }
    
    public static void log(Object obj)
    {
        System.out.println(obj);
    }

    public static void log(String str)
    {
        System.out.println(str);
    }

    public static Scanner scan()
    {
        return scan;
    }

    public static String readLine()
    {
        return scan.nextLine();
    }

    public static String readLine(String msg)
    {
        System.out.print(msg);
        return readLine();
    }

    public static int readInt()
    {
        int x = scan.nextInt();
        readLine();
        return x;
    }

    public static int readInt(String msg)
    {
        System.out.print(msg);
        return readInt();
    }

    // Validações

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

    // retorna true se o email for válido
    public static boolean validarEmail(String email)
    {
        Matcher matcher = PATTERN_EMAIL.matcher(email);
        return matcher.matches();
    }
}
