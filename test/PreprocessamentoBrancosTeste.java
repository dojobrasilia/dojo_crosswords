import org.junit.Test;

import junit.framework.Assert;


public class PreprocessamentoBrancosTeste {
	
	@Test
	public void testeUm() {
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(" _ _ _ _ \n" +
						" X _ _ X\n" +
						" X _ _ X\n");
		
		String preprocessado = puzzle.preProcessGrid();
		
		String esperado = "____\n" +
						  "B__B\n" +
						  "B__B\n";
		
		Assert.assertEquals(esperado, preprocessado);
		
	}
	
	@Test
	public void testeDois() {
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(" _ _ _ _\n" +
						" _ X _ X\n" +
						" _ _ _ _\n");
		
		String preprocessado = puzzle.preProcessGrid();
		
		String esperado = "____\n" +
						  "_X_B\n" +
						  "____\n";
		
		Assert.assertEquals(esperado, preprocessado);
		
	}
	
	@Test
	public void testeTres() {
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(" _ _ X _\n" +
						" _ X _ _\n" +
						" _ _ _ _\n");
		
		String preprocessado = puzzle.preProcessGrid();
		
		String esperado = "__B_\n" +
						  "_X__\n" +
						  "____\n";
		
		Assert.assertEquals(esperado, preprocessado);
		
	}
	
	@Test
	public void testeQuatro() {
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(" _ _ _ _\n" +
						" _ X _ _\n" +
						" _ _ X _\n");
		
		String preprocessado = puzzle.preProcessGrid();
		
		String esperado = "____\n" +
						  "_X__\n" +
						  "__B_\n";
		
		Assert.assertEquals(esperado, preprocessado);
		
	}
	
	@Test
	public void testeCinco() {
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(" _ _ _ _\n" +
						" _ X _ _\n" +
						" _ X _ _\n" +
						" _ _ X _\n");
		
		String preprocessado = puzzle.preProcessGrid();
		
		String esperado = "____\n" +
						  "_X__\n" +
						  "_X__\n" +
						  "__B_\n";
		
		Assert.assertEquals(esperado, preprocessado);
		
	}
	
	@Test
	public void testeSeis() {
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(" _ _ _ _\n" +
						" _ _ _ _\n" +
						" _ _ X X\n" +
						" _ _ _ _\n");
		
		String preprocessado = puzzle.preProcessGrid();
		
		String esperado = "____\n" +
						  "____\n" +
						  "__BB\n" +
						  "____\n";
		
		Assert.assertEquals(esperado, preprocessado);
		
	}
	
	@Test
	public void testeSete() {
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(" _ _ _ _\n" +
						" X X _ _\n" +
						" _ _ X X\n" +
						" _ _ _ _\n");
		
		String preprocessado = puzzle.preProcessGrid();
		
		String esperado = "____\n" +
						  "BB__\n" +
						  "__BB\n" +
						  "____\n";
		
		Assert.assertEquals(esperado, preprocessado);
		
	}
	
	@Test
	public void testeOito() {
		
		Puzzle puzzle = new Puzzle();
		puzzle.loadGrid(" _ X _ _\n" +
						" _ X _ _\n" +
						" _ _ _ _\n" +
						" _ _ _ _\n");
		
		String preprocessado = puzzle.preProcessGrid();
		
		String esperado = "_B__\n" +
						  "_B__\n" +
						  "____\n" +
						  "____\n";
		
		Assert.assertEquals(esperado, preprocessado);
		
	}
}





