package runner;

import hu.bme.mit.inf.dslreasoner.application.execution.StandaloneScriptExecutor;
import map.*;

public class Runner {
	
	private static String runWithPath(String path) {
		String message = StandaloneScriptExecutor.executeScript(path);
		return message;
	}
		
	public static void main(String args[]) {
		Utils.createInitialInput();
		
		String ecoreFile = args[0];
		System.out.println("Generation Started");
		String message = runWithPath(ecoreFile);
		if(message != null) {
			System.out.println(message);
			return;
		}
		System.out.println("Finished");
		DrawMap();
	}
	
	public static void DrawMap() {
		Map m = Utils.loadInstance("output/1.xmi");
		Grid[] grids = new Grid[100];
		for(Grid g : m.getGrids()) {
			grids[g.getId()-1] = g;
		}
		
		for(int i = 0; i < grids.length; i++) {
			if(grids[i].getTypes() instanceof Sea) {
				System.out.print("-");
			}else {
				System.out.print("x");
			}
			if(i % 10 == 9) {
				System.out.print("\n");
			}
			
		}
	}
	
	

}
