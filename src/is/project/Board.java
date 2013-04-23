package is.project;

import java.awt.DisplayMode;

public class Board {
	int size;
	char [][]board;
	Player winningPlayer;
	int minMaxValue;
	int state;
		
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Board(int size) {			
		super();
		this.size = size;
		board = new char[size][size];		
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				board[i][j] = '-';											
			}			
		}
	}
	
	public Board(Board oldState){
		size = oldState.getSize();
		board = new char[size][size];
		for(int i = 0; i < size; i++){
			for(int j=0; j < size; j++){
				board[i][j] = oldState.getElementAt(i, j);
			}
		}
		winningPlayer = oldState.getWinningPlayer();
		minMaxValue = oldState.getMinMaxValue();		
	}
		
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
	public char[][] getBoard() {
		return board;
	}
	public void setBoard(char[][] board) {
		this.board = board;
	}
	public Player getWinningPlayer() {
		return winningPlayer;
	}
	public void setWinningPlayer(Player winningPlayer) {
		this.winningPlayer = winningPlayer;
	}
	public int getMinMaxValue() {
		return minMaxValue;
	}
	public void setMinMaxValue(int minMaxValue) {
		this.minMaxValue = minMaxValue;
	}		
	
	public char getElementAt(int i, int j){
		return board[i][j];
	}
	
	public void setElementAt(int i, int j, Player player){
		 board[i][j] = player.symbol;				 		 
	}
	
	public void setElementAt(int i, int j, char symbol){
		 board[i][j] = symbol;				 		 
	}
	
	public boolean isDraw(){
		int position = 0;
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if (board[i][j] == '-')							
					return false;
			}
		}
		return true;
	}
	
	public boolean checkForWin(Player player){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(isWinning(i,j,player))
					return true;
			}
		}
		return false;
	}
	
	public boolean isWinning(int iLocation, int jLocation, Player player){
		int numberOfLocations = 0;	
			numberOfLocations = checkSymbolPresence(iLocation, jLocation, 0, 1, player.symbol);
			numberOfLocations += checkSymbolPresence(iLocation, jLocation-1, 0, -1, player.symbol);
	        if(numberOfLocations >=size){
	        	winningPlayer = player;	        	
	        	return true;
	        }
	        
	        numberOfLocations = 0;
	        numberOfLocations = checkSymbolPresence(iLocation, jLocation, 1, 1, player.symbol);
			numberOfLocations += checkSymbolPresence(iLocation-1, jLocation-1, -1, -1, player.symbol);
	        if(numberOfLocations >=size){
	        	winningPlayer = player;	        	
	        	return true;
	        }
	        
	        numberOfLocations = 0;
	        numberOfLocations = checkSymbolPresence(iLocation, jLocation, 1, 0, player.symbol);
			numberOfLocations += checkSymbolPresence(iLocation-1, jLocation, -1, 0, player.symbol);
	        if(numberOfLocations >=size){
	        	winningPlayer = player;	        
	        	return true;
	        }
	        
	        numberOfLocations = 0;
	        numberOfLocations = checkSymbolPresence(iLocation, jLocation, 1, -1, player.symbol);
			numberOfLocations += checkSymbolPresence(iLocation-1, jLocation+1, -1, 1, player.symbol);
	        if(numberOfLocations >=size){
	        	winningPlayer = player;	        
	        	return true;
	        }
	    		
		return false;
	}
	
	private int checkSymbolPresence(int iLocation, int jLocation, int iCounter, int jCounter, char symbol){
		int noOfLocations = 0;		
		while(iLocation >=0 && iLocation < this.size && jLocation >=0 && jLocation < this.size){
			if(board[iLocation][jLocation] == symbol)
				noOfLocations++;
			else
				break;
			iLocation += iCounter;
			jLocation += jCounter;			
		}
		return noOfLocations;
	} 
	
	
	
	
	
	public void displayBoard(){
		System.out.println("Current Board State is: \n");
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				System.out.print(" " + board[i][j]);												
			}
			System.out.println();
		}
	}
	
	
}
