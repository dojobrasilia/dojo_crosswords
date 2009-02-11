
public class Puzzle {
	private String contents;

	public static final String GRID1 = "X";
	public static final String GRID2 = "_";
	public static final String LINHA1 = "######";
	public static final String LINHA2 = "#    #";
	public static final String QUADRADOCHEIO = LINHA1+"\n"+LINHA1+"\n"+LINHA1+"\n"+LINHA1+"\n";
	public static final String QUADRADOVAZIO = LINHA1+"\n"+LINHA2+"\n"+LINHA2+"\n"+LINHA1+"\n";

	public void loadGrid(String grid) {
		contents = grid;		
	}

	public Object getCrosswords() {

		String[] linhas = contents.replaceAll(" ","").split("\n");

		// tira os espaços
		StringBuffer resultado = new StringBuffer();

		for (int x=0; x<linhas.length; x++){

			//preenche a coluna da esquerda
			resultado.append('#');

			for (int i = 0; i < linhas[x].length(); i++) {
				resultado.append("#####");
			}


			resultado.append('\n');
			preencheLinha(linhas[x], resultado);
			preencheLinha(linhas[x], resultado);

		}
		preenncheBordaInferior(linhas[0].length(), resultado);
		return resultado.toString();
	}

	private void preenncheBordaInferior(int qtdColunas, StringBuffer resultado) {
		resultado.append('#');
		for (int i = 0; i < qtdColunas; i++) {
			resultado.append("#####");
		}
		resultado.append("\n");
	}


	public void preencheLinha(String semEspacos, StringBuffer resultado) {

		resultado.append('#');
		for (int i = 0; i < semEspacos.length(); i++) {

			if (semEspacos.charAt(i) == 'X') {
				resultado.append("#####");
			} else {
				resultado.append("    #");
			}
		}
		resultado.append('\n');
	}

	public String preProcessGrid() {
		contents = contents.replace(" ", "");

		// troca os X's na borda da direita e da esquerda
		contents = contents.replace("X\n", "B\n").replaceAll("\\nX", "\nB");

		String[] linhas = contents.split("\n");

		// troca X por B na primeira e na última linha
		linhas[0]=linhas[0].replace("X", "B");
		linhas[linhas.length-1] = linhas[linhas.length-1].replace("X", "B");

		char[][] tabuleiro = montarTabuleiro(linhas);

		int maxLin = tabuleiro.length - 1;
		int maxCol = tabuleiro[0].length - 1;

		//recursivo
		//primeira linha
		for (int x = 0; x < maxCol; x++) {
			if (tabuleiro[0][x] == 'B') {
				verificaVizinhos(0, x, tabuleiro);
			}
		}

		//ultima linha
		for (int x = 0; x < maxCol; x++) {
			if (tabuleiro[maxLin][x] == 'B') {
				verificaVizinhos(maxLin, x, tabuleiro);
			}
		}

		//primeira coluna
		for (int x = 0; x < maxLin; x++) {
			if (tabuleiro[x][0] == 'B') {
				verificaVizinhos(x, 0, tabuleiro);
			}
		}

		//ultima coluna
		for (int x = 0; x < maxLin; x++) {
			if (tabuleiro[x][maxCol] == 'B') {
				verificaVizinhos(x, maxCol, tabuleiro);
			}
		}

		return montarResultado(tabuleiro);
	}

	private void verificaVizinhos(int linha, int coluna, char [][] tabuleiro) {

		//Verifica direita
		if (coluna+1 < tabuleiro[linha].length && tabuleiro[linha][coluna+1] == 'X') {
			tabuleiro[linha][coluna+1] = 'B';
			verificaVizinhos (linha, coluna+1, tabuleiro);
		}

		//Verifica esquerda
		if (coluna-1 > 0 && tabuleiro[linha][coluna-1] == 'X') {
			tabuleiro[linha][coluna-1] = 'B';
			verificaVizinhos (linha, coluna-1, tabuleiro);
		}

		//Verifica embaixo
		if (linha+1 < tabuleiro.length && tabuleiro[linha+1][coluna] == 'X') {
			tabuleiro[linha+1][coluna] = 'B';
			verificaVizinhos (linha+1, coluna, tabuleiro);
		}

		//Verifica "cima"
		if (linha-1 > 0 && tabuleiro[linha-1][coluna] == 'X') {
			tabuleiro[linha-1][coluna] = 'B';
			verificaVizinhos (linha-1, coluna, tabuleiro);
		}
	}

	private char[][] montarTabuleiro(String[] linhas) {
		// monta o tabuleiro de char[][]
		char[][] tabuleiro = new char[linhas.length][];
		for (int i = 0; i < linhas.length; i++) {
			tabuleiro[i]=linhas[i].toCharArray();
		}
		return tabuleiro;
	}

	private String montarResultado(char[][] tabuleiro) {
		// monta a string de saída
		StringBuffer retorno = new StringBuffer();
		for (int i = 0; i < tabuleiro.length; i++){
			for (int j = 0 ; j < tabuleiro[0].length ; j++){
				retorno.append(tabuleiro[i][j]);
			}
			retorno.append('\n');
		}
		return retorno.toString();
	}

}






