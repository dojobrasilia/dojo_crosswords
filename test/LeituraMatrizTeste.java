import junit.framework.Assert;

import org.junit.Test;


public class LeituraMatrizTeste {
	
	public static final String GRID1 = "X";
	public static final String GRID2 = "_";
	public static final String LINHA1 = "######";
	public static final String LINHA2 = "#    #";
	public static final String QUADRADOCHEIO = LINHA1+"\n"+LINHA1+"\n"+LINHA1+"\n"+LINHA1+"\n";
	public static final String QUADRADOVAZIO = LINHA1+"\n"+LINHA2+"\n"+LINHA2+"\n"+LINHA1+"\n";

	
	@Test
	public void testMatriz1x1Cheio(){
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(GRID1);
		
		Assert.assertEquals(QUADRADOCHEIO, puzzle.getCrosswords());
	}
	
	@Test
	public void testMatriz1x1Vazio(){
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(GRID2);
		
		Assert.assertEquals(QUADRADOVAZIO, puzzle.getCrosswords());
	}
	
	@Test
	public void testMatriz10() {
		String esperado = "###########\n"+
						  "######    #\n"+
						  "######    #\n"+
					      "###########\n";
		
		
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid("X _");
		
		Assert.assertEquals(esperado, puzzle.getCrosswords());
	}
	
	@Test
	public void testMatriz01() {
		String esperado = "###########\n"+
						  "#    ######\n"+
						  "#    ######\n"+
					      "###########\n";
		
		
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid("_ X");
		
		Assert.assertEquals(esperado, puzzle.getCrosswords());
	}
	
	@Test
	public void testMatriz010() {
		String esperado = "################\n"+
						  "#    ######    #\n"+
						  "#    ######    #\n"+
					      "################\n";
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid("_ X _");
		
		Assert.assertEquals(esperado, puzzle.getCrosswords());
	}

	@Test
	public void testMatriz101() {
		String esperado = "################\n"+
						  "######    ######\n"+
						  "######    ######\n"+
						  "################\n";
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid("X _ X");
		
		Assert.assertEquals(esperado, puzzle.getCrosswords());
	}
	
	@Test
	public void testMatriz0101() {
		String esperado = "#####################\n"+
						  "#    ######    ######\n"+
						  "#    ######    ######\n"+
					      "#####################\n";
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid("_ X _ X");
		
		Assert.assertEquals(esperado, puzzle.getCrosswords());
	}
	
	@Test
	public void testMatriz1111() {
		String esperado = "#####################\n"+
						  "#####################\n"+
						  "#####################\n"+
					      "#####################\n";
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid("X X X X");
		
		Assert.assertEquals(esperado, puzzle.getCrosswords());
	}
	
	@Test
	public void testMatriz0000() {
		String esperado = "#####################\n"+
						  "#    #    #    #    #\n"+
						  "#    #    #    #    #\n"+
					      "#####################\n";
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(" _ _ _ _ ");
		
		Assert.assertEquals(esperado, puzzle.getCrosswords());
	}
	
	@Test
	public void testMatriz10_01() {
		String esperado = "###########\n"+
						  "######    #\n"+
						  "######    #\n"+
					      "###########\n"+
						  "#    ######\n"+
						  "#    ######\n"+
					      "###########\n";
		
		
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid("X _ \n"
					   +"_ X \n");
		
		String crosswords = puzzle.getCrosswords();
		
		Assert.assertEquals(crosswords+"", esperado, crosswords);
	}
	
	@Test
	public void testMatriz0000_0000() {
		String esperado = "#####################\n"+
						  "#    #    #    #    #\n"+
						  "#    #    #    #    #\n"+
					      "#####################\n"+
						  "#    #    #    #    #\n"+
						  "#    #    #    #    #\n"+
					      "#####################\n";
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(" _ _ _ _ \n" +
						" _ _ _ _\n");
		
		Assert.assertEquals(esperado, puzzle.getCrosswords());
	}
	
	@Test
	public void testMatriz0000_1001() {
		String esperado = "#####################\n"+
						  "#    #    #    #    #\n"+
						  "#    #    #    #    #\n"+
					      "#####################\n"+
						  "######    #    ######\n"+
						  "######    #    ######\n"+
					      "#####################\n";
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(" _ _ _ _ \n" +
						" X _ _ X\n");
		
		Assert.assertEquals(esperado, puzzle.getCrosswords());
	}
	
	@Test
	public void testMatriz0000_1001_1001() {
		String esperado = "#####################\n"+
						  "#    #    #    #    #\n"+
						  "#    #    #    #    #\n"+
					      "#####################\n"+
						  "######    #    ######\n"+
						  "######    #    ######\n"+
					      "#####################\n"+
						  "######    #    ######\n"+
						  "######    #    ######\n"+
						  "#####################\n";
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(" _ _ _ _ \n" +
						" X _ _ X\n" +
						" X _ _ X\n");
		
		Assert.assertEquals(esperado, puzzle.getCrosswords());
	}
	
}
