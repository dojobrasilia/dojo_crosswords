
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

		preencheBordaHorizontalBottom(matrizEntrada.length-1);
		return resultado.toString();
	}

	private void preencheBordaHorizontal(int indiceLinha) {
		
		desenhaCanto(indiceLinha);

		int qtdColunas = matrizEntrada[indiceLinha].length;

		for (int indiceColuna = 0; indiceColuna < qtdColunas; indiceColuna++) {

			// miolo
			if (	!isBranco(indiceLinha, indiceColuna) // esse n‹o Ž branco
					|| (indiceLinha >0 && !isBranco(indiceLinha-1, indiceColuna)) ){ // o de cima n‹o Ž branco
				
				resultado.append("####");
			} else {

				resultado.append("    ");
			}	

			preencheBordaVertical(indiceLinha, indiceColuna);
		}

		resultado.append("\n");
	}
	
	private void preencheBordaHorizontalBottom(int indiceLinha) {
		
		desenhaCantoLinha(indiceLinha); //lado esquerdo

		int qtdColunas = matrizEntrada[indiceLinha].length;
		
		// para cada coluna
		for (int indiceColuna = 0; indiceColuna < qtdColunas; indiceColuna++) {

			// miolo
			if(!isBranco(indiceLinha, indiceColuna)){ // esse n‹o Ž branco
				resultado.append("####");
			}else{
				resultado.append("    ");
			}
						
			preencheBordaVerticalLinha(indiceLinha, indiceColuna);
		}

		resultado.append("\n");

		
	}

	private void desenhaCanto(int indice) {
		if (matrizEntrada[indice][0] != 'B'  || (indice>0 && matrizEntrada[indice-1][0] != 'B'))
			//preenche a coluna da esquerda
			resultado.append('#');
		else
			resultado.append(' ');
	}
	
	private void desenhaCantoLinha(int indice) {
		if (matrizEntrada[indice][0] != 'B' )
			//preenche a coluna da esquerda
			resultado.append('#');
		else
			resultado.append(' ');
	}

	private boolean isBranco(int linha, int coluna) {
		return matrizEntrada[linha][coluna] == 'B';
	}

	private boolean proximoExisteENaoEhBranco(int linha, int coluna) {
		return coluna+1 < matrizEntrada[linha].length && matrizEntrada[linha][coluna+1] != 'B';
	}



	public void preencheMioloLinha(int indice) {

		desenhaCantoLinha(indice);

		for (int i = 0; i < matrizEntrada[indice].length; i++) {

			if (matrizEntrada[indice][i] == 'X') {
				resultado.append("####");

			} else {
				resultado.append("    ");
			}

			preencheBordaVerticalLinha(indice, i);
		}
		resultado.append('\n');
	}

	private void preencheBordaVertical(int indice, int i) {
		// borda (divisoria) desse com o proximo
		if (!isBranco(indice, i) || (proximoExisteENaoEhBranco(indice, i) || logoAcimaNaoEhBranco(indice, i))){
			resultado.append("#");
		} else {
			resultado.append(' ');
		}
	}
	
	private void preencheBordaVerticalLinha(int indice, int i) {
		// borda (divisoria) desse com o proximo
		if (!isBranco(indice, i) || (proximoExisteENaoEhBranco(indice, i))){
			resultado.append("#");
		} else {
			resultado.append(' ');
		}
	}

	private boolean logoAcimaNaoEhBranco(int indice, int i) {
		return ((indice-1 > 0) && (matrizEntrada[indice-1][i] != 'B' ));
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
		// monta a string de saÃ­da
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






