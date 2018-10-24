package Connect4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CONNECT4_GUI extends JFrame {
	private JPanel jpMain;
	private colPicker top;
	private CONNECT4BOARD c4Board;
	private Player currPlayer;
	private Player p1;
	private Player p2;
	private int [] nextRowAvail = {5,5,5,5,5,5,5};
	
	public CONNECT4_GUI(){
		p1 = new Player("P1", "O");
		p2 = new Player("P2", "0");
		currPlayer = p1;
		
		jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());
		
		top = new colPicker();
		jpMain.add(top, BorderLayout.NORTH);
		
		c4Board = new CONNECT4BOARD();
		jpMain.add(c4Board);
		
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(jpMain);
	}
	
	private class colPicker extends JPanel implements ActionListener {
		private JButton[] top;
		
		public colPicker(){
			setLayout(new GridLayout(1, 0)); 
			top = new JButton[7];
			displayTop();
		}
		
		public void displayTop(){
			for(int i = 0; i < top.length; i++){
				top[i] = new JButton();
				top[i].addActionListener(this);
				top[i].setEnabled(true);
				top[i].setText("");//if(player1 setForeground(red));
				add(top[i]);
			}
		}
		public void clearTop(){
			for(int i = 0; i < top.length; i++){
				top[i].setText("");
				top[i].setEnabled(true);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String chip = currPlayer.getChip();
			JButton btnClicked = (JButton)e.getSource();
			btnClicked.setText(currPlayer.getChip());
			for(int i = 0; i < top.length; i++){
				if(btnClicked.equals(top[i])){
					int rowToPlace = nextRowAvail[i];
					c4Board.board[rowToPlace][i].setText(chip);
					nextRowAvail[i]--;
				}
			}
			
			if(c4Board.isWinner()){
				JOptionPane.showMessageDialog(null, currPlayer.getName() + " IS THE WINNER!");
				int yesNo = JOptionPane.showConfirmDialog(null, "Again?", "Choose your fate!", JOptionPane.YES_NO_CANCEL_OPTION);
				if(yesNo == 0){
					clearTop();
					c4Board.clearBoard();
				}
				else {
					System.exit(1);
				}
			}
			c4Board.takeTurn();
		}
		

	}
	
	private class CONNECT4BOARD extends JPanel implements BoardInterface, PlayerInterface {
		private JLabel[][] board;
		private final int ROWS = 6;
		private final int COLS = 7;
		
		public CONNECT4BOARD(){
			setLayout(new GridLayout(ROWS, COLS)); 
			board = new JLabel[ROWS][COLS];
			displayBoard();
		}
		@Override
		public void displayBoard() {
			for(int r = 0; r < board.length; r++){
				for(int c = 0; c < board[r].length; c++){
					board[r][c] = new JLabel();
					board[r][c].setBorder(new LineBorder(Color.black, 1));
					board[r][c].setEnabled(true);
					board[r][c].setText("");
					add(board[r][c]);
				}
			}
		}
		
		@Override
		public void clearBoard() {
			for(int r = 0; r < board.length; r++){
				for(int c = 0; c < board[r].length; c++){
					board[r][c].setText("");
				}
			}
			
			for(int i=0; i < COLS; i++){//7
				nextRowAvail[i]= ROWS - 1;//6-1;
			}
		}

		@Override
		public boolean isWinner() {
			if(isWinnerInRow() || isWinnerInCol() || isWinnerInMainDiag() || isWinnerInSecDiag()){
				return true;
			}
			return false;
		}
		public boolean isWinnerInRow(){
			String chip = currPlayer.getChip();
			for(int r = 0; r < board.length; r++){
				int numMatchesInRow = 0;
				for(int c = 0; c < board[r].length; c++){
					if(board[r][c].getText().trim().equalsIgnoreCase(chip)){
						numMatchesInRow++;
					}
					else {
						numMatchesInRow = 0;
					}
					if(numMatchesInRow == 4){
						return true;
					}
				}

			}
			return false;
		}
		public boolean isWinnerInCol(){
			String chip = currPlayer.getChip();
			for(int c = 0; c < COLS; c++){
				int numMatchesInCol = 0;
				for(int r = 0; r < ROWS; r++){
					if(board[r][c].getText().trim().equalsIgnoreCase(chip)){
						numMatchesInCol++;
					}
					else {
						numMatchesInCol = 0;
					}
					if(numMatchesInCol == 4){
						return true;
					}
				}
			}
			return false;

		}
		public boolean isWinnerInMainDiag(){//↘ 
			String chip = currPlayer.getChip();
			for(int row1st = 2; row1st >= 0; row1st--){
				int col = 0;
				int row = row1st;
				int numMatchesInMainDiag = 0;
				while(col >= 0 && row < ROWS){
					if(board[row][col].getText().trim().equalsIgnoreCase(chip)){
						numMatchesInMainDiag++;
					}
					else {
						numMatchesInMainDiag = 0;
					}
					if(numMatchesInMainDiag == 4){
						return true;
					}
					col++;
					row++;
				}
			}
			for(int col1st = 0; col1st < 4; col1st++){
				int row = 0;
				int col = col1st;
				int numMatchesInMainDiag = 0;
				while(col < COLS && row < ROWS){
					if(board[row][col].getText().trim().equalsIgnoreCase(chip)){
						numMatchesInMainDiag++;
					}
					else {
						numMatchesInMainDiag = 0;
					}
					if(numMatchesInMainDiag == 4){
						return true;
					}
					col++;
					row++;
				}
			}
			return false;
		}
		public boolean isWinnerInSecDiag(){//⤦ 
			String chip = currPlayer.getChip();
			for(int row1st = 3; row1st < ROWS; row1st++){
				int col = 0; 
				int row = row1st;
				int numMatchesInSecDiag = 0;
				while(col < COLS && row >= 0){
					if(board[row][col].getText().trim().equalsIgnoreCase(chip)){
						numMatchesInSecDiag++;
					}
					else {
						numMatchesInSecDiag = 0;
					}
					if(numMatchesInSecDiag == 4){
						return true;
					}
					col++;
					row--;
				}
			}		
			for(int col1st = 0; col1st < 4; col1st++){
				int row = ROWS-1;
				int col = col1st;
				int numMatchesInSecDiag = 0 ;
				while(col < COLS && row >= 0){
					if(board[row][col].getText().trim().equalsIgnoreCase(chip)){
						numMatchesInSecDiag++;
					}
					else {
						numMatchesInSecDiag = 0;
					}
					if(numMatchesInSecDiag == 4){
						return true;
					}
					col++;
					row--;
				}
			}
			return false;
		}
		
		@Override
		public void takeTurn() {
			if(currPlayer.equals(p1)){
				currPlayer = p2;
			}
			else {
				currPlayer = p1;
			}
		}
	}
}
