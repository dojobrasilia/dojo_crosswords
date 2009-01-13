
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
		
		String semEspacos =contents.replaceAll(" ",""); 
		
		StringBuffer resultado = new StringBuffer("#");
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
		resultado.append('#');
		for (int i = 0; i < semEspacos.length(); i++) {
			resultado.append("#####");
		}
		resultado.append("\n");
		return resultado.toString();
		/*
		if (semEspacos.length()>1) {
			
			if (semEspacos.startsWith("X")){
			
			return 	"###########\n"+
			  		"######    #\n"+
			  		"######    #\n"+
			  		"###########\n";
			
			} else if(semEspacos.startsWith("_")){
				if(semEspacos.length()==2){
				return 	"###########\n"+
						"#    ######\n"+
						"#    ######\n"+
						"###########\n";
				} else {
					return "################\n"+
					  	   "#    ######    #\n"+
					       "#    ######    #\n"+
				           "################\n";
				}
			}
			
		}
		return contents.equalsIgnoreCase(GRID1) ? QUADRADOCHEIO : QUADRADOVAZIO;
		*/
	}

}
