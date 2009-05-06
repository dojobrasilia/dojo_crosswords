
public class Puzzle {
	private char[][] matrizEntrada;

	public void loadGrid(String grid) {
		matrizEntrada = montarTabuleiro(grid.replaceAll(" ", ""));
	}

	public String getCrosswords() {
		
		StringBuffer resultado = new StringBuffer();

		for (int x=0; x<matrizEntrada.length; x++){

			preencheBordaHorizontal(matrizEntrada, x, resultado);
			preencheMioloLinha(matrizEntrada, x, resultado);
			preencheMioloLinha(matrizEntrada, x, resultado);
		}

		preencheBordaHorizontalBottom(matrizEntrada, matrizEntrada.length-1, resultado);
		return resultado.toString();
	}

	private void preencheBordaHorizontalBottom(char[][] matriz, int indiceLinha, StringBuffer resultado) {
		
		desenhaCantoLinha(matriz, indiceLinha, resultado); //lado esquerdo

		int qtdColunas = matriz[indiceLinha].length;
		
		// para cada coluna
		for (int indiceColuna = 0; indiceColuna < qtdColunas; indiceColuna++) {

			// miolo
			if(isBranco(matriz, indiceLinha, indiceColuna)){
				resultado.append("    ");
			}else{
				resultado.append("####");
			}
						
			preencheBordaVerticalLinha(matriz, indiceLinha, resultado, indiceColuna);
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
	
	private void desenhaCantoLinha(char[][] matriz,int indice, StringBuffer resultado) {
		if (matriz[indice][0] != 'B' )
			//preenche a coluna da esquerda
			resultado.append('#');
		else
			resultado.append(' ');
	}

	private boolean isBranco(char[][] matriz, int linha, int coluna) {
		return matriz[linha][coluna] == 'B';
	}

	private boolean proximoExisteENaoEhBranco(char[][] matriz, int linha, int coluna) {
		return coluna+1 < matriz[linha].length && matriz[linha][coluna+1] != 'B';
	}

	private void preencheBordaHorizontal(char[][] matriz, int indice, StringBuffer resultado) {
		
		desenhaCanto(matriz, indice, resultado);

		int qtdColunas = matriz[indice].length;

		for (int i = 0; i < qtdColunas; i++) {

			// miolo
			// FIXME indice, indice-1 ????
			if (indice == matriz.length && !isBranco(matriz, indice, indice-1) || !isBranco(matriz, indice, i) || (indice >0 && !isBranco(matriz, indice-1, i))){
				resultado.append("####");
			} else {

				resultado.append("    ");
			}	

			preencheBordaVertical(matriz, indice, resultado, i);
		}

		resultado.append("\n");
	}

	public void preencheMioloLinha(char[][] matriz,int indice, StringBuffer resultado) {

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
		// borda (divisoria) desse com o pr�ximo
		if (!isBranco(matriz, indice, i) || (proximoExisteENaoEhBranco(matriz, indice, i) || logoAcimaNaoEhBranco(matriz, indice, i))){
			resultado.append("#");
		} else {
			resultado.append(' ');
		}
	}
	
	private void preencheBordaVerticalLinha(char[][] matriz, int indice, StringBuffer resultado, int i) {
		// borda (divisoria) desse com o pr�ximo
		if (!isBranco(matriz, indice, i) || (proximoExisteENaoEhBranco(matriz, indice, i))){
			resultado.append("#");
		} else {
			resultado.append(' ');
		}
	}

	private boolean logoAcimaNaoEhBranco(char[][] matriz, int indice, int i) {
		return ((indice-1 > 0) && (matriz[indice-1][i] != 'B' ));
	}

	public String preProcessGrid() {
		int maxLin = matrizEntrada.length - 1;
		int maxCol = matrizEntrada[0].length - 1;

		//recursivo
		//primeira linha
		for (int x = 0; x < maxCol; x++) {
			if (matrizEntrada[0][x] == 'X') {
				trocaPraBranco(0, x, matrizEntrada);
			}
		}

		//ultima linha
		for (int x = 0; x < maxCol; x++) {
			if (matrizEntrada[maxLin][x] == 'X') {
				trocaPraBranco(maxLin, x, matrizEntrada);
			}
		}

		//primeira coluna
		for (int x = 0; x < maxLin; x++) {
			if (matrizEntrada[x][0] == 'X') {
				trocaPraBranco(x, 0, matrizEntrada);
			}
		}

		//ultima coluna
		for (int x = 0; x < maxLin; x++) {
			if (matrizEntrada[x][maxCol] == 'X') {
				trocaPraBranco(x, maxCol, matrizEntrada);
			}
		}

		return montarResultado(matrizEntrada);
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






