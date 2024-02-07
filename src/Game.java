import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game extends JFrame implements KeyListener{
	int GAMEBOARD[][] = new int[9][9];
	int SOLUTIONS[][] = new int[9][9];
	Cell cells[][] = new Cell[9][9];
    static Color Wrong = new Color(232, 86, 70);
    static int WIDTH = 540;
    static int HEIGHT = 540;
    static Color TEXTColor = new Color(11, 87, 128);
	/*
	 * The constructor: sets up the frame and make the Jframe ready for start of the game.
	 */
    Game(){
		generate();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Sudoku");
		this.addKeyListener(this);
		this.setVisible(true);
		this.setResizable(false);
	}
    /*
     * officialy start the game, sets the frame's layout and adds the cells to frame.
     */
	void start() {
		this.setLayout(new GridLayout(9,9));
		draw();
		this.setVisible(true);
	}
	/*
	 * used to generate a solvable unique sudoku table.
	 */
	 void generate() {
		Random random = new Random();
		int numofsol = 0;
		int rownum = 0; int colnum = 0;
		int val = 0;
		int count = 0;
		while (numofsol!=1) {
			rownum = random.nextInt(0,9);
			colnum = random.nextInt(0,9);
			val = random.nextInt(1,10);
			if (checkrow(GAMEBOARD, rownum, val,false,0) && checkcol(GAMEBOARD, colnum, val,false,0) && checkbox(GAMEBOARD, rownum, colnum, val,false))
				GAMEBOARD[rownum][colnum] = val;
			numofsol = checkuniqueness(GAMEBOARD , 0);
			count++;
			if (numofsol==0) {
				GAMEBOARD[rownum][colnum] = 0;
			}
		}
	}
	 /*
	  * helps the generate method, basically checks that given a board, how many solutions it has and returns the number of solutions.
	  * Uses recursion.
	  */
	int checkuniqueness(int [][]board, int count) {
		for (int i=0;i < 9; i++) {
			for (int j=0;j < 9; j++) {
				if (board[i][j]==0) {
					for (int n=1;n<=9 && count < 2;n++) {
						if (checkrow(board, i , n,false,0) && checkcol(board, j , n,false,0) && checkbox(board, i , j, n,false)) {
							board[i][j]=n;
							int temp = checkuniqueness(board, count);
							if (temp > count) {
								count = temp;
								for (int k=0;k < 9; k++) {
									for (int l=0;l<9;l++) {
										if (board[k][l]!=0) {
											SOLUTIONS[k][l] = board[k][l];
										}
									}
								}
							}
						   board[i][j]=0;
						}
					}
				return count;}
			}
		}
	return count + 1;
	}
	/*
	 * returns true, if n can be inserted in the row of the board, it is also used to check if a user's input obeys game rules.
	 */
	boolean checkrow(int board[][], int row, int n,boolean checkinput, int col) {
		if (!checkinput) {
		   for (int i=0;i<9;i++) {
			 if (board[row][i] == n)
				  return false;
		        }
		   return true;}
		else {
			for (int i=0;i < 9;i++) {
				if (i==col)
					continue;
				if (cells[row][i].val==n) {					
					cells[row][i].setBackground(Wrong);
					cells[row][col].setBackground(Wrong);
				}
				else {
					cells[row][i].setBackground(Color.white);
				}
			}
		}
		return true;
	}
	/*
	 * returns true, if n can be inserted in the col column of board, it is also used to check if a user's input is okay to be placed in col.
	 */
	boolean checkcol(int board[][], int col, int n,boolean checkinput, int row) {
		if (!checkinput) {
		    for (int i = 0; i<9;i++) {
			    if (board[i][col]==n) {
				   return false;
			     }
		      }
		    return true;}
		else {
			for (int i=0;i < 9; i++) {
				if (i==row)
					continue;
				if (cells[i][col].val==n) {
					cells[i][col].setBackground(Wrong);
					cells[row][col].setBackground(Wrong);
					}
				else {
					cells[i][col].setBackground(Color.white);
				}
			}
		}
		return true;
	}
	/*
	 * returns true, if n can be inserted in the specified 3x3 box of the board, and also checks user's input.
	 */
	boolean checkbox(int board[][], int row, int col, int n, boolean checkinput) {
		int nrow = row/3;
		int ncol = col/3;
		if (!checkinput) {
		    for (int i=0;i<3;i++) {
			    for (int j=0;j<3;j++) {
				    if (board[(nrow*3)+i][(ncol*3)+j]==n) {
					    return false;
				     }
			     }
		      }
		    return true;}
		else {
			for (int i=0;i<3;i++) {
				for (int j=0;j<3;j++) {
					if ((nrow*3)+i == row && (ncol*3) + j==col) {
						continue;
						}
					if (cells[(nrow*3)+i][(ncol*3)+j].val==n) {
						cells[(nrow*3)+i][(ncol*3)+j].setBackground(Wrong);
						cells[row][col].setBackground(Wrong);
					}
					else {
						cells[(nrow*3)+i][(ncol*3)+j].setBackground(Color.white);
					}
				}
			}
		}
		return true;
	}
	/*
	 * makes cell objects and adds them to the frame.
	 */
	void draw() {
		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				Cell cell;
				if (GAMEBOARD[i][j]==0) {
				     cell = new Cell(i,j,GAMEBOARD[i][j],true);}
				else {
					cell= new Cell(i,j,GAMEBOARD[i][j],false);}
				this.add(cell);
				this.cells[i][j] = cell;
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * handles key inputs of user
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		checkactivity();
		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				Cell temp = cells[i][j];
				if (temp.active) {
					char key = e.getKeyChar();
					if (key > '0' && key <='9') {
					temp.val = Integer.valueOf(String.valueOf(key));
					temp.setText(String.valueOf(temp.val));
					temp.setForeground(TEXTColor);
					temp.active = false;
					GAMEBOARD[i][j] = temp.val;
					checkrow(GAMEBOARD, i, temp.val , true, j);
					checkcol(GAMEBOARD, j, temp.val, true,i);
					checkbox(GAMEBOARD, i, j, temp.val, true);
				    }
				}
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	void checkactivity() {
		ArrayList<Cell>cells = Cell.activecell;
			for (int i=0;i< cells.size() - 1;i++) {
				cells.get(i).active = false;
				cells.remove(i);
				i--;
			}
		
		
}
	int isrunning() {
		if (Arrays.deepEquals(SOLUTIONS, GAMEBOARD)){
			int answer = JOptionPane.showOptionDialog(null, "Do you want to play again?", "Game finished!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
			return answer;
		}
		return 10;
	}
	void Clear() {
		this.dispose();
	}
}

  
