package is.project;

public class AlphaBeta {
	BoardUI boardUIObj;
	
	public Board findNextMove(Board currentState, Player computer){
		boardUIObj = BoardUI.getInstance();
		int depth=10;
		if(currentState.size > 3 && currentState.size < 10)
			depth = 5;
		if(currentState.size > 10)
			depth = 3;		
		return maxMove(currentState, Integer.MIN_VALUE, Integer.MAX_VALUE, boardUIObj.human, depth);
	}
		
	private Board minMove(Board currentState, int alpha, int beta, Player computer, int depthleft){
		if(currentState.checkForWin(computer)){
			currentState.setMinMaxValue(1);
			return currentState;
		}				
		if(currentState.isDraw()){
			currentState.setMinMaxValue(0);
			return currentState;
		}
		if ( depthleft == 0 ){
			currentState.setMinMaxValue(evaluate(currentState));
			return currentState;
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
					//int val = maxMove(newBoard, boardUIObj.human);
					//Board temp = maxMove(newBoard, beta, alpha, boardUIObj.human);
					Board temp = maxMove(newBoard, alpha, beta, boardUIObj.human, depthleft -1);
					if(temp.getMinMaxValue() < prevBest.getMinMaxValue()){
						prevBest = new Board(newBoard);	
						prevBest.setMinMaxValue(temp.getMinMaxValue());	
						beta = temp.getMinMaxValue();
					}
					if(beta <= alpha)
						return prevBest;
				}
			}
		}			
		return prevBest;
	}
	
		
   private Board maxMove(Board currentState, int alpha, int beta, Player human,int depthleft){
	   if(currentState.checkForWin(human)){
		    currentState.setMinMaxValue(-1);
			return currentState;
	   }	  		
		if(currentState.isDraw()){
			currentState.setMinMaxValue(0);
			return currentState;
		}
		 if ( depthleft == 0 ){			
			 currentState.setMinMaxValue(evaluate(currentState));
			 return currentState;
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
					Board temp = minMove(newBoard, alpha, beta, boardUIObj.computer,depthleft - 1);
					if(temp.getMinMaxValue() > prevBest.getMinMaxValue()){
						prevBest = new Board(newBoard);		
						prevBest.setMinMaxValue(temp.getMinMaxValue());
						alpha = temp.getMinMaxValue();				
					}
					if(beta <= alpha)
						return prevBest;
				}
			}
		}										
		return prevBest;
	}
   
    private int evaluate(Board currentState){
    	boardUIObj = BoardUI.getInstance();
    	int AINoOfWins = checkNoOfPossibleWins(currentState, boardUIObj.computer);
    	int humanNoOfWins = checkNoOfPossibleWins(currentState, boardUIObj.human);
    	if(AINoOfWins > humanNoOfWins)
    		return 1;
    	else if(AINoOfWins < humanNoOfWins)
    		return -1;
    	else 
    		return 0;    
    }
    
    private int checkNoOfPossibleWins(Board currentState, Player player){
    	int wins=0;
    	Player oppositePlayer;
    	if(player.playerType == Type.COMPUTER)
    		oppositePlayer = boardUIObj.human;
    	else
    		oppositePlayer = boardUIObj.computer;
    	for(int i=0; i< currentState.size; i++){
    		for(int j=0; j < currentState.size; j++){
    			if(currentState.getElementAt(i, j) != oppositePlayer.symbol){
    				if(j == currentState.size -1)
    					wins++;
    			}
    			else
    				break;
    		}
    	}
    	
    	for(int i=0; i< currentState.size; i++){
    		for(int j=0; j < currentState.size; j++){
    			if(currentState.getElementAt(j, i) != player.symbol){
    				if(j == currentState.size -1)
    					wins++;
    			}
    			else
    				break;
    		}
    	}
    	
    	for(int i=0; i< currentState.size; i++){
    		if(currentState.getElementAt(i, i) != player.symbol){
				if(i == currentState.size -1)
					wins++;
			}
			else
				break;
    	}
    	
    	int lastIndex = currentState.size -1;
    	for(int i=0; i< currentState.size; i++){
    		if(currentState.getElementAt(i, lastIndex - i) != player.symbol){
				if(i == currentState.size -1)
					wins++;
			}
			else
				break;
    	}
    	
    	return wins;
    }
    
    
}
