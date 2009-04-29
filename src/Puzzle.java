
public class Puzzle {
	private String contents;

	public void loadGrid(String grid) {
		contents = grid;
	}

	public String getCrosswords() {

		// tira os espaços e quebra as linhas
		String[] linhas = contents.replaceAll(" ","").split("\n");
		
		char[][] matrizEntrada = new char[linhas.length][linhas[0].length()];
		for (int i=0 ; i<matrizEntrada.length ; i++) {
			for (int j=0 ; j<matrizEntrada[0].length ; j++) {
				matrizEntrada[i][j] = linhas[i].charAt(j);
			}
		}
		
		StringBuffer resultado = new StringBuffer();

		for (int x=0; x<matrizEntrada.length; x++){

			preencheBordaHorizontal(matrizEntrada, x, resultado);
			preencheLinha(matrizEntrada, x, resultado);
			preencheLinha(matrizEntrada, x, resultado);
		}

		preencheBordaHorizontalBottom(matrizEntrada, matrizEntrada.length-1, resultado);
		return resultado.toString();
	}

	private void preencheBordaHorizontalBottom(char[][] matriz, int indiceLinha, StringBuffer resultado) {
		
		desenhaCantinhoEsquerdoInferior(matriz, indiceLinha, resultado); //lado esquerdo

		int qtdColunas = matriz[indiceLinha].length;
		
		// para cada coluna
		for (int indiceColuna = 0; indiceColuna < qtdColunas; indiceColuna++) {

			// miolo
			if(isBranco(matriz[indiceLinha], indiceColuna)){
				resultado.append("    ");
			}else{
				resultado.append("####");
			}
						
			preencheBordaVerticalBottom(matriz, indiceLinha, resultado, indiceColuna);
		}

		resultado.append("\n");

		
	}

	private void desenhaCanto(char matriz[][],int indice, StringBuffer resultado) {
		if (matriz[indice][0] != 'B'  || (indice>0 && matriz[indice-1][0] != 'B'))
			//preenche a coluna da esquerda
			resultado.append('#');
		else
			resultado.append(' ');
	}

	private void desenhaCantinhoEsquerdoInferior(char[][] matriz,int indice, StringBuffer resultado) {
		if (matriz[indice][0] != 'B')
			//preenche a coluna da esquerda
			resultado.append('#');
		else
			resultado.append(' ');
	}
	
	private void desenhaCantoLinha(char[][] matriz,int indice, StringBuffer resultado) {
		if (matriz[indice][0] != 'B' )
			//preenche a coluna da esquerda
			resultado.append('#');
		else
			resultado.append(' ');
	}


	private boolean isBranco(char[] linha, int i) {
		return linha[i] == 'B';
	}

	private boolean proximoExisteENaoEhBranco(char[] linha, int i) {
		return i+1 < linha.length && linha[i+1] != 'B';
	}

	private void preencheBordaHorizontal(char[][] matriz, int indice, StringBuffer resultado) {
		
		desenhaCanto(matriz, indice, resultado);

		int qtdColunas = matriz[indice].length;

		for (int i = 0; i < qtdColunas; i++) {

			// miolo
			if (indice == matriz.length && !isBranco(matriz[indice], indice-1) || !isBranco(matriz[indice], i) || (indice >0 && !isBranco(matriz[indice-1], i))){
				resultado.append("####");
			} else {

				resultado.append("    ");
			}	

			preencheBordaVertical(matriz, indice, resultado, i);
		}

		resultado.append("\n");
	}

	public void preencheLinha(char[][] matriz,int indice, StringBuffer resultado) {

		desenhaCantoLinha(matriz, indice, resultado);

		for (int i = 0; i < matriz[indice].length; i++) {

			if (matriz[indice][i] == 'X') {
				resultado.append("####");

			} else {
				resultado.append("    ");
			}

			preencheBordaVerticalLinha(matriz, indice, resultado, i);
		}
		resultado.append('\n');
	}

	private void preencheBordaVertical(char[][] matriz, int indice, StringBuffer resultado, int i) {
		// borda (divisoria) desse com o prÔøΩximo
		if (!isBranco(matriz[indice], i) || (proximoExisteENaoEhBranco(matriz[indice], i) || logoAcimaNaoEhBranco(matriz, indice, i))){
			resultado.append("#");
		} else {
			resultado.append(' ');
		}
	}


	private void preencheBordaVerticalBottom(char[][] matriz, int indice, StringBuffer resultado, int i) {
		// borda (divisoria) desse com o prÔøΩximo
		if (!isBranco(matriz[indice], i) || (proximoExisteENaoEhBranco(matriz[indice], i))){
			resultado.append("#");
		} else {
			resultado.append(' ');
		}
	}

	
	private void preencheBordaVerticalLinha(char[][] matriz, int indice, StringBuffer resultado, int i) {
		// borda (divisoria) desse com o prÔøΩximo
		if (!isBranco(matriz[indice], i) || (proximoExisteENaoEhBranco(matriz[indice], i))){
			resultado.append("#");
		} else {
			resultado.append(' ');
		}
	}

	private boolean logoAcimaNaoEhBranco(char[][] matriz, int indice, int i) {
		return ((indice-1 > 0) && (matriz[indice-1][i] != 'B' ));
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
		// monta a string de sa√≠da
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






