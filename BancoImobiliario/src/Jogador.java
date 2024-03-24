import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jogador
{
    private String nome;
    private String cpf;
    private String email;
    private String foto;


    // Construtor
    public Jogador(String nome, String cpf, String email, String foto)
    {
        this.nome = nome;
        setCpf(cpf);
        setEmail(email);
        this.foto = foto;
    }


    // Getters e setters
    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        if (validarCpf(cpf))
        {
            this.cpf = cpf;
            return;
        }
        
        // cpf invalidado:
        this.cpf = "* cpf inválido - " + cpf;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        if (validarEmail(email))
        {
            this.email = email;
            return;
        }
        
        // cpf invalidado:
        this.email = "* email inválido - " + email;
    }

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }


    // Método toString
    @Override
    public String toString() {
        return "-- Jogador " + nome + " --\nCpf: " + cpf + "\nEmail: " + email + "\n";
    }


    // Validações
    // Fiz as validações static para poderem ser utilizadas de outros lugares, já que só trabalham com os parâmetros mesmo
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

    private static final String EMAIL_EXPRESSAO_REGEX = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    private static final Pattern PATTERN_EMAIL = Pattern.compile(EMAIL_EXPRESSAO_REGEX, Pattern.CASE_INSENSITIVE);

    public static boolean validarEmail(String email)
    {
        Matcher matcher = PATTERN_EMAIL.matcher(email);
        return matcher.matches();
    }
}
