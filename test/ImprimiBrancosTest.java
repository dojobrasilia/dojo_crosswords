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
}
