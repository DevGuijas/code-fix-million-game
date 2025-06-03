package mainPKG;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Random rd = new Random();

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        System.out.println("\nSEJA MUITO BEM-VINDO AO JOGO DO MILHÃO!!");
        System.out.println("PREPARADO PARA VOLTAR PARA CASA MILIONÁRIO?");
        System.out.println("\n1 = Iniciar\n2 = Sair");
        System.out.print("Resposta: ");

        int play = sc.nextInt();
        sc.nextLine(); // Linha adicionada para limpar o buffer do Scanner.

        if (play == 1) {
            limparConsole();
            getPergunta();
        } else {
            System.out.println("Adeus!");
        }
    }

    public static void getPergunta() {
        int[] numero = new int[10];
        int posicao = -1;
        boolean gaming = true;
        int qttAcertos = 0;

        while (posicao < 9 && gaming) { // Trocada por "posicao < 9", mais seguro em questões de não causar erro.
            int teste = rd.nextInt(10); // Alterada para ficar de 0 a 9 sem causar invalidez ou problemas de acesso.
            boolean contem = false;

            for (int j = 0; j <= posicao; j++) {
                if (teste == numero[j]) {
                    contem = true;
                    break;
                }
            }

            if (!contem) {
                posicao++;
                numero[posicao] = teste;
                String resposta = "";

                gaming = resultPergunta(teste, resposta, qttAcertos); // vPos removido já que era o mesmo que teste (causava redundância).

                if (gaming) {
                    qttAcertos++;
                }

                if (qttAcertos == 10) {
                    System.out.println("PARABÉNS, CAMPEÃ(O)!!");
                    System.out.println("VOCÊ TERMINOU COM UM PRÊMIO DE R$ 1.000.000!");
                }
            }
        }
    }

    public static String[] premio = {
        "R$ 0", "R$ 500", "R$ 1.000", "R$ 5.000", "R$ 10.000", "R$ 50.000",
        "R$ 100.000", "R$ 250.000", "R$ 500.000", "R$ 750.000", "R$ 1.000.000"
    };

    public static void totalPremio(int qttAcertos) {
        System.out.println("VOCÊ TERMINOU COM UM PRÊMIO DE " + premio[qttAcertos] + "!");
    }

    public static boolean resultPergunta(int nPergunta, String resposta, int acertos) {
        System.out.println("\nPERGUNTA " + (acertos + 1) + ":");
        showPergunta(nPergunta);
        System.out.print("\nSUA RESPOSTA: ");
        resposta = sc.nextLine();
        return verify(resposta, nPergunta, acertos);
    }

    public static void showPergunta(int p) {
        System.out.println(perguntas[p][0].toUpperCase() + "\n");

        for (String a : alternativas[p]) { // Tirado o "p-1" já que a linha 38 foi alterada. (Manter essa alteração causaria invalidez ou problemas de acesso na matriz.)
            System.out.println(a.toUpperCase());
        }
    }

    public static boolean verify(String resposta, int posicaoDaPergunta, int qttAcertos) {
        if (resposta.equalsIgnoreCase(perguntas[posicaoDaPergunta][1])) {
            System.out.println("\nBOA!\n");
            return true;
        } else {
            System.out.println();
            totalPremio(qttAcertos);
            System.out.println("GAME OVER");
            return false;
        }
    }

    public static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String[][] perguntas = {
        {"Qual desses itens NÃO foi excluído do League of Legends?", "A"},
        {"Qual o bairro mais populoso de Salvador, segundo o Censo IBGE 2022?", "C"},
        {"Qual o elemento considerado mais importante em um computador?", "A"},
        {"Qual o mais novo pacote do jogo Valorant?", "C"},
        {"Qual a nota da UCSAL no MEC?", "D"},
        {"Qual o nome da maior empresa de tecnologia do mundo?", "C"},
        {"A linguagem JAVA é de qual década?", "D"},
        {"Qual o Estado mais tecnológico do Brasil?", "B"},
        {"Qual time de futebol do Nordeste possui a maior torcida?", "C"},
        {"Qual o jogo de videogame mais vendido do mundo até hoje?", "B"}
    };

    public static String[][] alternativas = {
        {"A) Criptoflora", "B) Pistola Laminar Hextec", "C) Hemodrenário", "D) Garra do Espreitador"},
        {"A) Brotas", "B) São Caetano", "C) Itapuã", "D) Pituba"},
        {"A) Mouse", "B) Tela", "C) Teclado", "D) CPU"},
        {"A) Cyrax", "B) Beta Remasted", "C) Divergência", "D) Arcane"},
        {"A) 10", "B) 3", "C) 6", "D) 5"},
        {"A) IBM", "B) COMPUTER ASSISTENCE", "C) APPLE", "D) CISCO"},
        {"A) 60", "B) 70", "C) 80", "D) 90"},
        {"A) Santa Catarina", "B) São Paulo", "C) Bahia", "D) Rio de Janeiro"},
        {"A) Vitória", "B) Bahia", "C) Sport", "D) Fortaleza"},
        {"A) Mario Kart 8", "B) Minecraft", "C) Grand Theft Auto V", "D) Pac-Man"}
    };
}
