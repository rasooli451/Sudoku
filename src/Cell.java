import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Cell extends JLabel implements MouseListener{
	int row;
	int column;
	int val;
	boolean active = false;
	static ArrayList <Cell> activecell = new ArrayList<>();
	boolean editable;
	Border border=BorderFactory.createLineBorder(Color.black, 1);
	
	Cell(int row, int column, int val,boolean editable){
		this.row = row;
		this.column = column;
		this.val=val;
		this.editable = editable;
		this.setText(String.valueOf(val));
		this.setPreferredSize(new Dimension(50,50));
		this.setBorder(border);
		this.setForeground(Color.black);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
		this.setFont(new Font(null, Font.PLAIN, 35));
		this.addMouseListener(this);
		this.setOpaque(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	    	if (this.getText().equals("0") || this.editable) {
	    	this.setBackground(new Color(141, 211, 224));
			if (!active) {
				active = true;
				if (this.activecell.contains(this)) {
					this.activecell.remove(this);
					this.activecell.add(this);
				}
				else {
				this.activecell.add(this);}
			}
			else {
				active = false;
			}
			for (int i=0;i<this.activecell.size() - 1;i++) {
				if (this.activecell.get(i).getBackground().equals(Game.Wrong)) {
					this.activecell.get(i).active = false;
					continue;
				}
		    	this.activecell.get(i).setBackground(Color.white);
		    	this.activecell.get(i).active = false;
		    }
	    	}
	    	
	    
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return ""+this.row+" "+ this.column + " "+this.active;
	}


}
