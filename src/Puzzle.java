
public class Puzzle {
	private String contents;

	public void loadGrid(String grid) {
		contents = grid;
	}

	public String getCrosswords() {

		String[] linhas = contents.replaceAll(" ","").split("\n");

		// tira os espaços
		StringBuffer resultado = new StringBuffer();

		for (int x=0; x<linhas.length; x++){

			preencheBordaHorizontal(linhas, x, resultado);
			preencheLinha(linhas[x], resultado);
			preencheLinha(linhas[x], resultado);
		}

		preencheBordaHorizontal(linhas, linhas.length-1, resultado);
		return resultado.toString();
	}

	private void desenhaCanto(String linha, StringBuffer resultado) {
		if (linha.charAt(0) != 'B')
			//preenche a coluna da esquerda
			resultado.append('#');
		else
			resultado.append(' ');
	}


	private boolean isBranco(String linha, int i) {
		return linha.charAt(i) == 'B';
	}

	private boolean proximoExisteENaoEhBranco(String linha, int i) {
		return i+1 < linha.length() && linha.charAt(i+1) != 'B';
	}

	private void preencheBordaHorizontal(String[] linha, int indice, StringBuffer resultado) {
		if  (indice == 0) {
			desenhaCanto(linha[indice], resultado);

			int qtdColunas = linha[indice].length();

			for (int i = 0; i < qtdColunas; i++) {

				// miolo
				if (!isBranco(linha[indice], i)){
					resultado.append("####");
				} else {

					resultado.append("    ");
				}	

				preencheBordaVertical(linha[indice], resultado, i);
			}

			resultado.append("\n");
		}else if(linha.length - 1 == indice){
			int qtdColunas = linha[indice].length();
		}
	}

	public void preencheLinha(String linha, StringBuffer resultado) {

		desenhaCanto(linha, resultado);

		for (int i = 0; i < linha.length(); i++) {

			if (linha.charAt(i) == 'X') {
				resultado.append("####");

			} else {
				resultado.append("    ");
			}

			preencheBordaVertical(linha, resultado, i);
		}
		resultado.append('\n');
	}

	private void preencheBordaVertical(String linha, StringBuffer resultado,
			int i) {
		// borda (divisoria) desse com o pr�ximo
		if (!isBranco(linha, i) || (proximoExisteENaoEhBranco(linha, i) )){
			resultado.append("#");
		} else {
			resultado.append(' ');
		}
	}

	public String preProcessGrid() {

		char[][] tabuleiro = montarTabuleiro(contents);

		int maxLin = tabuleiro.length - 1;
		int maxCol = tabuleiro[0].length - 1;

		//recursivo
		//primeira linha
		for (int x = 0; x < maxCol; x++) {
			if (tabuleiro[0][x] == 'X') {
				trocaPraBranco(0, x, tabuleiro);
			}
		}

		//ultima linha
		for (int x = 0; x < maxCol; x++) {
			if (tabuleiro[maxLin][x] == 'X') {
				trocaPraBranco(maxLin, x, tabuleiro);
			}
		}

		//primeira coluna
		for (int x = 0; x < maxLin; x++) {
			if (tabuleiro[x][0] == 'X') {
				trocaPraBranco(x, 0, tabuleiro);
			}
		}

		//ultima coluna
		for (int x = 0; x < maxLin; x++) {
			if (tabuleiro[x][maxCol] == 'X') {
				trocaPraBranco(x, maxCol, tabuleiro);
			}
		}

		return montarResultado(tabuleiro);
	}

	private void trocaPraBranco(int linha, int coluna, char [][] tabuleiro) {

		tabuleiro[linha][coluna] = 'B';

		//Verifica direita
		if (coluna+1 < tabuleiro[linha].length && tabuleiro[linha][coluna+1] == 'X') {
			trocaPraBranco (linha, coluna+1, tabuleiro);
		}

		//Verifica esquerda
		if (coluna-1 > 0 && tabuleiro[linha][coluna-1] == 'X') {
			trocaPraBranco (linha, coluna-1, tabuleiro);
		}

		//Verifica embaixo
		if (linha+1 < tabuleiro.length && tabuleiro[linha+1][coluna] == 'X') {
			trocaPraBranco (linha+1, coluna, tabuleiro);
		}

		//Verifica "cima"
		if (linha-1 > 0 && tabuleiro[linha-1][coluna] == 'X') {
			trocaPraBranco (linha-1, coluna, tabuleiro);
		}
	}

	private char[][] montarTabuleiro(String contents) {

		contents = contents.replace(" ", "");

		String[] linhas = contents.split("\n");

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






