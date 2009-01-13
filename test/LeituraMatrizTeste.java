import junit.framework.Assert;

import org.junit.Test;


public class LeituraMatrizTeste {
	
	@Test
	public void testMatriz1x1Cheio(){
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(Puzzle.GRID1);
		
		Assert.assertEquals(Puzzle.QUADRADOCHEIO, puzzle.getCrosswords());
	}
	
	@Test
	public void testMatriz1x1Vazio(){
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(Puzzle.GRID2);
		
		Assert.assertEquals(Puzzle.QUADRADOVAZIO, puzzle.getCrosswords());
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

}
