
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
		contents = contents.replace("X\n", "B\n").replaceAll("\\n\\s*X", "\nB");
		contents = contents.replace("XB", "BB");
		
		String[] linhas = contents.split("\n");
		
		linhas[0]=linhas[0].replace("X", "B");
		linhas[linhas.length-1] = linhas[linhas.length-1].replace("X", "B");
		
		char[][] tabuleiro = new char[linhas.length][];
		for (int i = 0; i < linhas.length; i++) {
			tabuleiro[i]=linhas[i].toCharArray();
		}
		
		
		for (int i = 1; i < linhas.length - 1; i++) {
			for (int j = 0; j < linhas[i].length(); j++) {
				if (tabuleiro[i][j] == 'X' && tabuleiro[i-1][j] == 'B') {
					tabuleiro[i][j] = 'B';
				}
				
				if (tabuleiro[i][j] == 'X' && tabuleiro[i][j-1] == 'B') {
					tabuleiro[i][j] = 'B';
				}
			}
		}
		
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






