
public class Puzzle {
	private char[][] matrizEntrada;
	private StringBuilder resultado;
	
	public void loadGrid(String grid) {
		matrizEntrada = montarTabuleiro(grid.replaceAll(" ", ""));
		resultado = new StringBuilder();
	}

	public String getCrosswords() {
		resultado = new StringBuilder();
		
		for (int indiceLinha=0; indiceLinha<matrizEntrada.length; indiceLinha++){

			preencheBordaHorizontal(indiceLinha);
			preencheMioloLinha(indiceLinha);
			preencheMioloLinha(indiceLinha);
		}

		preencheBordaHorizontal(matrizEntrada.length);
		return resultado.toString();
	}

	private void preencheBordaHorizontal(int indiceLinha) {
		
		desenhaCanto(indiceLinha);

		int qtdColunas = matrizEntrada[0].length;

		for (int indiceColuna = 0; indiceColuna < qtdColunas; indiceColuna++) {

			// miolo
			if (hasBorda(indiceLinha, indiceColuna) || hasBorda(indiceLinha-1, indiceColuna) ){ // o de cima n�o � branco
				
				resultado.append("####");
			} else {
				resultado.append("    ");
			}	

			preencheEncruzilhada(indiceLinha, indiceColuna);
		}

		resultado.append("\n");
	}
	
	private void desenhaCanto(int indice) {
		if (hasBorda(indice, 0) || hasBorda(indice-1,0))
			//preenche a coluna da esquerda
			resultado.append('#');
		else
			resultado.append(' ');
	}
	
	private boolean hasBorda(int linha, int coluna) {
		return (linha >= 0) && (linha < matrizEntrada.length) &&
			(coluna >= 0) && (coluna < matrizEntrada[0].length) &&
			matrizEntrada[linha][coluna] != 'B';
	}
	
	private void preencheMioloLinha(int indice) {

		if (hasBorda(indice,0))
			//preenche a coluna da esquerda
			resultado.append('#');
		else
			resultado.append(' ');

		for (int i = 0; i < matrizEntrada[indice].length; i++) {
			if (matrizEntrada[indice][i] == 'X') {
				resultado.append("####");
			} else {
				resultado.append("    ");
			}
			preencheBordaVertical(indice, i);
		}
		resultado.append('\n');
	}

	private void preencheEncruzilhada(int indice, int i) {
		// borda (divisoria) desse com o proximo
		if (hasBorda(indice, i) || hasBorda(indice, i+1) || hasBorda(indice-1, i) || hasBorda(indice-1, i+1)){
			resultado.append("#");
		} else {
			resultado.append(' ');
		}
	}
	
	private void preencheBordaVertical(int indice, int i) {
		// borda (divisoria) desse com o proximo
		if (hasBorda(indice, i) || hasBorda(indice, i+1)){
			resultado.append("#");
		} else {
			resultado.append(' ');
		}
	}

	public String preProcessGrid() {
		int maxLin = matrizEntrada.length - 1;
		int maxCol = matrizEntrada[0].length - 1;

		//recursivo
		//primeira linha
		for (int x = 0; x < maxCol; x++) {
			if (matrizEntrada[0][x] == 'X') {
				trocaPraBranco(0, x);
			}
		}

		//ultima linha
		for (int x = 0; x < maxCol; x++) {
			if (matrizEntrada[maxLin][x] == 'X') {
				trocaPraBranco(maxLin, x);
			}
		}

		//primeira coluna
		for (int x = 0; x < maxLin; x++) {
			if (matrizEntrada[x][0] == 'X') {
				trocaPraBranco(x, 0);
			}
		}

		//ultima coluna
		for (int x = 0; x < maxLin; x++) {
			if (matrizEntrada[x][maxCol] == 'X') {
				trocaPraBranco(x, maxCol);
			}
		}

		return montarResultado(matrizEntrada);
	}

	private void trocaPraBranco(int linha, int coluna) {

		matrizEntrada[linha][coluna] = 'B';

		//Verifica direita
		if (coluna+1 < matrizEntrada[linha].length && matrizEntrada[linha][coluna+1] == 'X') {
			trocaPraBranco (linha, coluna+1);
		}

		//Verifica esquerda
		if (coluna-1 > 0 && matrizEntrada[linha][coluna-1] == 'X') {
			trocaPraBranco (linha, coluna-1);
		}

		//Verifica embaixo
		if (linha+1 < matrizEntrada.length && matrizEntrada[linha+1][coluna] == 'X') {
			trocaPraBranco (linha+1, coluna);
		}

		//Verifica "cima"
		if (linha-1 > 0 && matrizEntrada[linha-1][coluna] == 'X') {
			trocaPraBranco (linha-1, coluna);
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






