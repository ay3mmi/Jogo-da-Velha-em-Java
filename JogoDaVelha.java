import java.util.Scanner;

public class JogoDaVelha {
    private static char[][] tabuleiro = new char[3][3];
    private static char jogadorAtual = 'X';
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== JOGO DA VELHA ===");
        System.out.println("Jogadores: X e O");
        System.out.println("Digite linha e coluna de 1 a 3 para jogar\n");

        do {
            inicializarTabuleiro();
            jogarPartida();
            System.out.print("\nQuer jogar de novo? (s/n): ");
        } while (scanner.next().equalsIgnoreCase("s"));

        System.out.println("Valeu por jogar!");
        scanner.close();
    }

    private static void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
        jogadorAtual = 'X';
    }

    private static void jogarPartida() {
        boolean jogoAcabou = false;

        while (!jogoAcabou) {
            mostrarTabuleiro();
            System.out.println("\nVez do jogador: " + jogadorAtual);
            
            int linha, coluna;
            while (true) {
                try {
                    System.out.print("Digite a linha (1-3): ");
                    linha = scanner.nextInt() - 1;
                    System.out.print("Digite a coluna (1-3): ");
                    coluna = scanner.nextInt() - 1;

                    if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
                        System.out.println("Posição inválida! Use valores de 1 a 3.");
                        continue;
                    }
                    if (tabuleiro[linha][coluna] != ' ') {
                        System.out.println("Essa posição já está ocupada!");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Entrada inválida! Digite números.");
                    scanner.nextLine(); // limpa buffer
                }
            }

            tabuleiro[linha][coluna] = jogadorAtual;

            if (verificarVitoria()) {
                mostrarTabuleiro();
                System.out.println("\n>>> JOGADOR " + jogadorAtual + " VENCEU! <<<");
                jogoAcabou = true;
            } else if (verificarEmpate()) {
                mostrarTabuleiro();
                System.out.println("\n>>> DEU VELHA! EMPATE! <<<");
                jogoAcabou = true;
            } else {
                jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
            }
        }
    }

    private static void mostrarTabuleiro() {
        System.out.println("\n    1   2   3");
        for (int i = 0; i < 3; i++) {
            System.out.print(" " + (i + 1) + "  ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("   ---+---+---");
        }
    }

    private static boolean verificarVitoria() {
        // linhas e colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual)
                return true;
            if (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual)
                return true;
        }
        // diagonais
        if (tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual)
            return true;
        if (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual)
            return true;

        return false;
    }

    private static boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') return false;
            }
        }
        return true;
    }
}