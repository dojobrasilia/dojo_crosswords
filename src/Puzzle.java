
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
			String semEspacos = linhas[x]; 
			resultado.append('#');
			for (int i = 0; i < semEspacos.length(); i++) {
				resultado.append("#####");
			}
			resultado.append("\n");
			resultado.append('#');
			for (int i = 0; i < semEspacos.length(); i++) {

				if (semEspacos.charAt(i) == 'X') {
					resultado.append("#####");
				} else {
					resultado.append("    #");
				}
			}

			resultado.append('\n');
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
		resultado.append('#');
		for (int i = 0; i < linhas[0].length(); i++) {
			resultado.append("#####");
		}
		resultado.append("\n");
		return resultado.toString();
	}

}
