/**
 * 
 */
package is.project;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * @author Avinash
 
 * This class handles the complete UI of TicTacToe game. It also acts as a container which holds the objects 
 * of Human, computer Player and Board. There are four different states during the complete game 
 *  START: Indicates game is still going
 *  QUIT: Indicates user forcefully ends the game
 *  DRAW: Game is draw
 *  WIN: Indicates one of the player won the game
 *  END: Game Finished
 * This states are mentioned in GameState Enum.    
 *
 */

enum GameState {
    START, QUIT, DRAW, WIN, END
}

public class BoardUI extends JFrame implements ActionListener {
	GameState state;
	Board boardState;
	Player human;
	Player computer;

	JTextField inputX;
	JTextField inputY;
	JButton addButton;
	JButton subButton;
	JButton multButton;
	JButton divButton;
	JButton resetButton;
	JTextArea resultArea;
    Button buttons[][];

	JPanel firstInputLine;

	
	static BoardUI boardUIObj;
	public BoardUI() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        setMinimumSize(new Dimension(400,400));
                        setResizable(false);
                        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

                        firstInputLine= new JPanel();
                        firstInputLine.setLayout(new FlowLayout(FlowLayout.CENTER));
                        firstInputLine.setMaximumSize(new Dimension(400,25));
                        JLabel labelX = new JLabel();
                        labelX.setText("Enter size between (3-10)");
                        inputX = new JTextField(10);
                        firstInputLine.add(labelX);
                        inputX.setMinimumSize(new Dimension(120,20));
                        firstInputLine.add(inputX);
                        addButton = new JButton();
                        addButton.setText("Enter");
                        addButton.setMinimumSize(new Dimension(60, 20));
                        addButton.setActionCommand("+");
                        addButton.addActionListener(this);
                        firstInputLine.add(addButton);
                        add(firstInputLine);
                        pack();
                        setVisible(true);

			}
	
	public static BoardUI getInstance(){
		if(boardUIObj == null)
			boardUIObj = new BoardUI();
		return boardUIObj;		
	}
	
	
	
	public GameState getGameState() {
		return state;
	}

	public void setGameState(GameState state) {
		this.state = state;
	}

	public Board getBoardState() {
		return boardState;
	}

	public void setBoardState(Board boardState) {
		this.boardState = boardState;
	}

	public Player getHuman() {
		return human;
	}

	public void setHuman(Player human) {
		this.human = human;
	}

	public Player getComputer() {
		return computer;
	}

	public void setComputer(Player computer) {
		this.computer = computer;
	}

	private void updateState(){
		if(boardState.checkForWin(human) || boardState.checkForWin(computer))
			state = GameState.WIN;
		else if(boardState.isDraw())
			state = GameState.DRAW;		
	}
	
   /*
	 * actionPerformed: This method handles all the actions from TicTacToe Frame.
	 * It performs the operation according to type of action command set for that component.
	 * All error handling is also done in this function. Each time updateResultArea is also called
	 * to display result.
	 *
	 */

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		Double X = null;
		if(!inputX.getText().isEmpty()){
			try {
				X = Double.parseDouble(inputX.getText());
			}
			catch(Exception E){
				X= null;
			}
		}
		switch (actionCommand.charAt(0)) {
		case '+':
			if(X != null){

	                         boardState = new Board(X.intValue());
	                         human = new Player('o', -1);
	                         computer = new Player('x', 1);		
	                         state = GameState.START;
	                         
	                        firstInputLine.setVisible(false);
	                        JPanel secondInputLine= new JPanel();
	                        secondInputLine.setLayout(new GridLayout(X.intValue(),X.intValue()));
	                        buttons = new Button[X.intValue()][X.intValue()];
	                        for(int i=0;i<X.intValue();i++){
	                            for(int j=0;j<X.intValue();j++)
	                            {
	                            buttons[i][j]=new Button(" ");
	                            char[] position=new char[2];
	                            buttons[i][j].setMaximumSize(new Dimension(1,1));
	                            buttons[i][j].setFont(new Font("Serif", Font.BOLD,40));
	
	                            buttons[i][j].setActionCommand(Integer.toString(i).concat(" ").concat(Integer.toString(j)));
	                            buttons[i][j].addActionListener(this);
	                            secondInputLine.add(buttons[i][j]);
	                        }
	                       }
	                            this.add(secondInputLine);
	                             
	                        pack();
	                        setVisible(true);
	                        //this.playGame();
	                }
					break;
	
				default:                   
	                String loc[]=actionCommand.split(" ");
	                int i = Integer.parseInt(loc[0]);
	                int j = Integer.parseInt(loc[1]);
	                JLabel labelW = new JLabel();
	                JPanel ThirdInputLine= new JPanel();
	                ThirdInputLine.setLayout(new FlowLayout(FlowLayout.CENTER));
	                ThirdInputLine.setMaximumSize(new Dimension(200,25));
	
	                if(buttons[i][j].getLabel().equals(" "))
	                {
	                	if(state == GameState.START)
	                	{
	                        boardState = human.makeMove(boardState,i,j);
			                buttons[i][j].setForeground(Color.green);
	                        buttons[i][j].setLabel("0");
	                        updateState();
	
	                        if(state == GameState.START){
	                            boardState  = computer.makeMove(boardState);
	                            updateState();
	                            for(i=0; i<X.intValue(); i++){
	                                for(j=0; j<X.intValue(); j++){
	                                    if(boardState.board[i][j]=='o'){
	                                         buttons[i][j].setForeground(Color.green);
	                                        buttons[i][j].setLabel("0");
	                                    }
	
	                                    else if(boardState.board[i][j]=='x'){
	                                            buttons[i][j].setForeground(Color.red);
	                                            buttons[i][j].setLabel("x");
	                                        }
	                                   }//end of for j loop
	                                }//end of for i loop
	                        }//end of inner if
	                    }//end of the if
	                }
	                if(state == GameState.WIN){
	                    char symbol=boardState.getWinningPlayer().getSymbol();
	                        if(symbol=='x'){
	                            labelW.setText("Computer Won");
	                            labelW.setFont(new Font("Serif", Font.BOLD,20));
	                            ThirdInputLine.add(labelW);
	                            ThirdInputLine.setVisible(true);
	                            this.add(ThirdInputLine);
	                            pack();
	                        setVisible(true);
	                        }
	                        else{
	                            labelW.setText("Player Won");
	                            labelW.setFont(new Font("Serif", Font.BOLD,20));
	                            ThirdInputLine.add(labelW);
	                            ThirdInputLine.setVisible(true);
	                            this.add(ThirdInputLine);
	                            pack();
	                        setVisible(true);
	                        }
	                }
	                else if(state == GameState.DRAW)
	                {
	                labelW.setText("Draw");
	                labelW.setFont(new Font("Serif", Font.BOLD,20));
	                ThirdInputLine.add(labelW);
	                ThirdInputLine.setVisible(true);
	                this.add(ThirdInputLine);
	                pack();
	                        setVisible(true);
	                }                    
	            break;
			}//end of switch
        }//end of action performed

}//end of class
