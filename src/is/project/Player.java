package is.project;


enum Type{HUMAN, COMPUTER};

public class Player {	
	char symbol;
	int minMaxValue;
	boolean isWinning;
	AlphaBeta miniMaxObj;
	Type playerType; 
	
	
		
	public Player(char symbol, int minMaxValue) {
		super();
		this.symbol = symbol;
		this.minMaxValue = minMaxValue;
		if(symbol == 'o')
			playerType = Type.HUMAN;
		else
			playerType = Type.COMPUTER;
		miniMaxObj = new AlphaBeta();
	}

	public char getSymbol() {
		return symbol;
	}
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public int getMinMaxValue() {
		return minMaxValue;
	}
	
	public void setMinMaxValue(int minMaxValue) {
		this.minMaxValue = minMaxValue;
	}
	
	public boolean isWinning() {
		return isWinning;
	}
	
	public void setWinning(boolean isWinning) {
		this.isWinning = isWinning;
	}
	
	public Board makeMove(Board currentState){		
		currentState = miniMaxObj.findNextMove(currentState, this);
        return currentState;
	}	

	public Board makeMove(Board currentState,int i,int j){
		currentState.setElementAt(i, j , this);
        return currentState;
	}

	
	
}
