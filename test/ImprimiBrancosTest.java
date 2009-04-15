import junit.framework.Assert;

import org.junit.Test;

public class ImprimiBrancosTest {
	@Test
	public void testaMatrizComUmBranco(){
		String expected = "######     \n"+
		                  "#    #     \n"+
		                  "#    #     \n"+
		                  "######     \n";
		Puzzle puzzle= new Puzzle();
		puzzle.loadGrid("_ B");
		
		System.out.println(puzzle.getCrosswords());
		
		Assert.assertEquals(expected,puzzle.getCrosswords());
		
	}
	
	@Test
	public void testaMatrizComDoisBrancos(){
		String expected = "           \n"+
		                  "           \n"+
		                  "           \n"+
		                  "           \n";
		Puzzle puzzle= new Puzzle();
		puzzle.loadGrid("B B");
		
		System.out.println(puzzle.getCrosswords().replace(' ', '.'));
		
		Assert.assertEquals(expected,puzzle.getCrosswords());
		
	}
	
	@Test
	public void testaMatrizComMatrizEntreDoisBrancos(){
		String expected = "     ######     \n"+
        				  "     ######     \n"+
        				  "     ######     \n"+
        				  "     ######     \n";
		Puzzle puzzle= new Puzzle();
		puzzle.loadGrid("B X B");
		
		System.out.println(puzzle.getCrosswords().replace(' ', '.'));
		
		Assert.assertEquals(expected,puzzle.getCrosswords());
		
	}

	@Test
	public void testaMatrizComMatrizBrancoAcima(){
		String expected = "      \n"+
        				  "      \n"+
        				  "      \n"+
        				  "######\n"+
        				  "######\n"+
        				  "######\n"+
        				  "######\n";
		Puzzle puzzle= new Puzzle();
		puzzle.loadGrid(" B \n" +
				        " X ");
		
		System.out.println(puzzle.getCrosswords().replace(' ', '.'));
		
		Assert.assertEquals(expected,puzzle.getCrosswords());
		
	}
	
	@Test
	public void testaMatrizComMatrizBrancoAcimaAbaixo(){
		String expected = "      \n"+
        				  "      \n"+
        				  "      \n"+
        				  "######\n"+
        				  "######\n"+
        				  "######\n"+
        				  "######\n"+
        				  "      \n"+
        				  "      \n"+
        				  "      \n"; 
		Puzzle puzzle= new Puzzle();
		puzzle.loadGrid(" B \n" +
				        " X \n" +
				        " B ");
		
		System.out.println(puzzle.getCrosswords().replace(' ', '.'));
		
		Assert.assertEquals(expected,puzzle.getCrosswords());
		
	}

}
