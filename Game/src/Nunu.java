import java.util.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Nunu extends JFrame{
	Nunu()
	{
		setTitle("Nunu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		imgPanel p =new imgPanel();
		add(p,BorderLayout.CENTER);
		
		JButton start = new JButton("게임 시작");
		startAction l = new startAction();
		start.addActionListener(l);
		add(start,BorderLayout.SOUTH);
		
		setSize(500, 800);
		setVisible(true);
	}
	
	class imgPanel extends JPanel
	{
		ImageIcon icon= new ImageIcon("NunuMain.png");
		Image main_img=icon.getImage();
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			g.drawImage(main_img,0,0,getWidth(),getHeight(),this);
		}
	}
	
	class startAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new game_frame();
		}
	}

	public static void main(String[] ar)
	{
		new Nunu();
	}
}
