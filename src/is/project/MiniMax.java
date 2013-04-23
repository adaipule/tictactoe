/**
 * 
 */
package is.project;

/**
 * @author Avinash
 *
 */
public class MiniMax {
	BoardUI boardUIObj;
			
	public Board findNextMove(Board currentState, Player computer){
		boardUIObj = BoardUI.getInstance();
		Board newBoard = new Board(currentState);
		Board prevBest = new Board(currentState.getSize());
		prevBest.setMinMaxValue(Integer.MIN_VALUE);
		for(int i=0; i<currentState.getSize(); i++){
			for(int j=0; j<currentState.getSize(); j++){				
				if(currentState.getElementAt(i, j) == '-'){					
					newBoard = new Board(currentState);;
					newBoard.setElementAt(i, j, computer.symbol);
					int val = minMove(newBoard, computer);					
					if(prevBest.getMinMaxValue() < val){
						prevBest = new Board(newBoard);	
						prevBest.setMinMaxValue(val);
					}
				}
			}
		}								
		return prevBest;		
	}
		
	private int minMove(Board currentState, Player computer){
		if(currentState.checkForWin(computer)){
			currentState.setMinMaxValue(1);
			return currentState.getMinMaxValue();
		}
		if(currentState.checkForWin(boardUIObj.human)){
			currentState.setMinMaxValue(-1);
			return currentState.getMinMaxValue();
		}
		if(currentState.isDraw()){
			currentState.setMinMaxValue(0);
			return currentState.getMinMaxValue();
		}
		boardUIObj = BoardUI.getInstance();
		Board prevBest = new Board(currentState.getSize());
		Board newBoard = new Board(currentState);
		prevBest.setMinMaxValue(Integer.MAX_VALUE);
		for(int i=0; i<currentState.getSize(); i++){
			for(int j=0; j<currentState.getSize(); j++){				
				if(currentState.getElementAt(i, j) == '-'){					
					newBoard = new Board(currentState);
					newBoard.setElementAt(i, j, boardUIObj.human.symbol);
					int val = maxMove(newBoard, boardUIObj.human);					
					if(prevBest.getMinMaxValue() > val){
						prevBest = new Board(newBoard);	
						prevBest.setMinMaxValue(val);
					}
				}
			}
		}			
		//System.out.println("MinMove:"+prevBest.minMaxValue);
		//prevBest.displayBoard();
		return prevBest.getMinMaxValue();
	}
	
		
   private int maxMove(Board currentState, Player human){
	   if(currentState.checkForWin(human)){
		    currentState.setMinMaxValue(-1);
			return currentState.getMinMaxValue();
	   }
	   if(currentState.checkForWin(boardUIObj.computer)){
			currentState.setMinMaxValue(1);
			return currentState.getMinMaxValue();
		}
		if(currentState.isDraw()){
			currentState.setMinMaxValue(0);
			return currentState.getMinMaxValue();
		}
	    boardUIObj = BoardUI.getInstance();
		Board prevBest = new Board(currentState.getSize());
		Board newBoard = new Board(currentState);
		prevBest.setMinMaxValue(Integer.MIN_VALUE);
		for(int i=0; i<currentState.getSize(); i++){
			for(int j=0; j<currentState.getSize(); j++){				
				if(currentState.getElementAt(i, j) == '-'){					
					newBoard = new Board(currentState);
					newBoard.setElementAt(i, j, boardUIObj.computer.symbol);
					int val = minMove(newBoard, boardUIObj.computer);					
					if(prevBest.getMinMaxValue() < val){
						prevBest = new Board(newBoard);		
						prevBest.setMinMaxValue(val);
					}
				}
			}
		}								
		//System.out.println("MaxMove:"+prevBest.minMaxValue);;
		return prevBest.getMinMaxValue();
	}
}
