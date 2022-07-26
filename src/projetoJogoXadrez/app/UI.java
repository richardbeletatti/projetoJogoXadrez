package projetoJogoXadrez.app;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import projetoJogoXadrez.xadrez.Color;
import projetoJogoXadrez.xadrez.PartidaDeXadrez;
import projetoJogoXadrez.xadrez.PecasDeXadrez;
import projetoJogoXadrez.xadrez.PosicaoXadrez;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void printPartida(PartidaDeXadrez partidaDeXadrez, List<PecasDeXadrez> capturados) {
		printTabuleiro(partidaDeXadrez.getPecas());
		System.out.println();
		printCapturarPecas(capturados);
		System.out.println();
		System.out.println("Turno: " + partidaDeXadrez.getTurno());
		if (!partidaDeXadrez.getCheckMate()) {
			System.out.println("Jogador em espera: " + partidaDeXadrez.getJogadorAtual());
			if (partidaDeXadrez.getCheck()) {
				System.out.println("CHECK !");
			}
		} else {
			System.out.println("CHECK-MATE!");
			System.out.println("Vencedor: " + partidaDeXadrez.getJogadorAtual());
		}
	}

	public static PosicaoXadrez lerPosicaoDaPecaXadrez(Scanner sc) {
		try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoXadrez(coluna, linha);
		} catch (Exception e) {
			throw new InputMismatchException("Erro lendo a posição do xadrez.Valores válido são de a1 até h8.");
		}
	}

	public static void printTabuleiro(PecasDeXadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPecas(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println(ANSI_GREEN + "  A B C D E F G H" + ANSI_RESET);
	}

	public static void printTabuleiro(PecasDeXadrez[][] pecas, boolean[][] possiveisMovimentos) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPecas(pecas[i][j], possiveisMovimentos[i][j]);
			}
			System.out.println();
		}
		System.out.println(ANSI_GREEN + "  A B C D E F G H" + ANSI_RESET);
	}

	private static void printPecas(PecasDeXadrez peca, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (peca.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + peca + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

	private static void printCapturarPecas(List<PecasDeXadrez> capturada) {
		List<PecasDeXadrez> white = capturada.stream().filter(x -> x.getColor() == Color.WHITE)
				.collect(Collectors.toList());
		List<PecasDeXadrez> black = capturada.stream().filter(x -> x.getColor() == Color.BLACK)
				.collect(Collectors.toList());
		System.out.println(ANSI_PURPLE + "Pecas capturadas: " + ANSI_RESET);
		System.out.println();
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);

		System.out.print(ANSI_YELLOW + "Pretas: " + ANSI_RESET);
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
	}
}
