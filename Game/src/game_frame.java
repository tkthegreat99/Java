import java.util.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class game_frame extends JFrame implements KeyListener,Runnable{
int x,y; //캐릭터의 위치 변수
	
	boolean KeyUp = false;
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	boolean KeySpace = false;
	
	int t,t2;
	
	int player_Speed;
	int missile_Speed;
	
	int enemy_Speed;
	int player_State = 0; 
	int game_Score;
	int player_HitPoint;
	
	Thread th;//스레드 생성
	
	Toolkit tk = Toolkit.getDefaultToolkit();

	Image []me; //자기자신 이미지 배열
	Image []explosion;
	
	Image enemy; //적 이미지
	
	
	ArrayList enemy_list = new ArrayList(); //다수의 적 배열
	ArrayList explosion_list=new ArrayList();
	
	Image buffImage;
	Graphics buffg;

	
	Enemy e; //에너미 접근 키
	
	game_frame()
	{
		init();
		start();
  
		setTitle("미니 프로젝트");
		setSize(600, 800);
		setBackground(Color.WHITE);

		setResizable(false); // 프레임의 크기를 임의로 변경못하게 설정
		setVisible(true); // 프레임을 눈에 보이게 띄웁니다.
	}
 
	public void init()
	{
		x = 280;
		y = 700;
		
		
		
		me=new Image[3];
		for(int i=0;i<me.length;++i)
		{
			me[i]=new ImageIcon("nunu_"+i+".png").getImage();
		}
		
		enemy=tk.getImage("enemy.png");
		
		
		explosion=new Image[3];
		for(int i=0;i<explosion.length;++i)
		{
			explosion[i]=new ImageIcon("explo_"+i+".png").getImage();
		}
		
		game_Score = 0;
		player_HitPoint=4;
		player_Speed=6;
		
		
		enemy_Speed=7;
		
	}

	public void start()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addKeyListener(this);
		
		th = new Thread(this);
		
		th.start();
		
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				KeyProcess();//키보드 입력 처리를 하여 x,y를 갱신
				EnemyProcess();
				
				
				repaint();
				Thread.sleep(20);
				t ++;
				t2+=133;
				game_Score++;
			}
		}catch(Exception e){}
	}
	
	public void Skill()
	{
		if(KeySpace==true)//스킬사용!//아직 미
		{
			player_State=1;
			
			
		}
		
		
		
	}
	
	public void EnemyProcess()
	{
		for(int i=0; i<enemy_list.size(); ++i)
		{
			e=(Enemy)(enemy_list.get(i));
			
			e.move();
			if(e.y < -200)
			{
				enemy_list.remove(i);
			}
			
			if(Crash(x,y,e.x,e.y,me[0],enemy))
			{
				player_HitPoint -=1;
				enemy_list.remove(i);
				
				
				if(player_HitPoint==0)
				{
					final int presentScore;
					presentScore=game_Score;
					class Scoreframe extends JFrame
					{
						class grades extends JFrame
						{
							ImageIcon Bronze = new ImageIcon("Bronze.png");
							ImageIcon Silver = new ImageIcon("Silver.png");
							ImageIcon Gold = new ImageIcon("Gold.png");
							ImageIcon Platinum = new ImageIcon("Platinum.png");
							ImageIcon Diamond= new ImageIcon("Diamond.png");
							
							JLabel B=new JLabel(Bronze);
							JLabel S=new JLabel(Silver);
							JLabel G=new JLabel(Gold);
							JLabel P=new JLabel(Platinum);
							JLabel D=new JLabel(Diamond);
							
							ImageIcon B2 = new ImageIcon("Bronze.png");
							ImageIcon S2 = new ImageIcon("Silver.png");
							ImageIcon G2 = new ImageIcon("Gold.png");
							ImageIcon P2 = new ImageIcon("Platinum.png");
							ImageIcon D2= new ImageIcon("Diamond.png");
							
							JLabel Br2=new JLabel(Bronze);
							JLabel Si2=new JLabel(Silver);
							JLabel go2=new JLabel(Gold);
							JLabel Pl2=new JLabel(Platinum);
							JLabel Di2=new JLabel(Diamond);
			
							grades()
							{
								setTitle("등급");
								setLayout(null);
								setBackground(Color.WHITE);
								setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								
								class gradesPanel extends JPanel
								{
									gradesPanel()
									{
										setLayout(null);
										if(presentScore<50)
										{	
											JLabel k = new JLabel("당신은 브론즈 입니다");
											k.setBounds(95, 20, 200, 15);
											add(k);
											Br2.setBounds(100,35,100,100);
											add(Br2);
										}
										else if(50<=presentScore&&presentScore<100)
										{	
											JLabel k = new JLabel("당신은 실버 입니다");
											k.setBounds(95, 20, 200, 15);
											add(k);
											Si2.setBounds(100,35,100,100);
											add(Si2);
										}
										else if(100<=presentScore&&presentScore<200)
										{	
											JLabel k = new JLabel("당신은 골드 입니다");
											k.setBounds(95, 20, 200, 15);
											add(k);
											go2.setBounds(100,35,100,100);
											add(go2);
										}
										else if(200<=presentScore&&presentScore<400)
										{	
											JLabel k = new JLabel("당신은 플레티넘 입니다");
											k.setBounds(95, 20, 200, 15);
											add(k);
											Pl2.setBounds(100,35,100,100);
											add(Pl2);
										}
										else
										{	
											JLabel k = new JLabel("당신은 다이아 입니다");
											k.setBounds(95, 20, 200, 15);
											add(k);
											Di2.setBounds(100,35,100,100);
											add(Di2);
										}
									}
								}								
								gradesPanel gP=new gradesPanel();
								gP.setBounds(0, 0, 300, 300);
								add(gP);
								JLabel l1 = new JLabel("브론즈(50점 미만)");
								JLabel l2 = new JLabel("실버(50점 이상 100점 미만)");
								JLabel l3 = new JLabel("골드(100점 이상 200점 미만)");
								JLabel l4 = new JLabel("플레티넘(200점 이상 400점 미만)");
								JLabel l5 = new JLabel("다이아(400점 이상)");
								
								B.setBounds(10,300,100,100); 
								S.setBounds(10,350,100,100);
								G.setBounds(10,400,100,100);
								P.setBounds(10,450,100,100);
								D.setBounds(10,500,100,100);
								l1.setBounds(100, 350, 300, 20);
								l2.setBounds(100, 400, 300, 20);
								l3.setBounds(100, 450, 300, 20);
								l4.setBounds(100, 500, 300, 20);
								l5.setBounds(100, 550, 300, 20);
								
								add(B);
								add(S);
								add(G);
								add(P);
								add(D);
								
								add(l1);
								add(l2);
								add(l3);
								add(l4);
								add(l5);
								setSize(300,700);
								setVisible(true);
							}
						}
						
						JButton b1;
						Scoreframe()
						{
							setTitle("점수판");
							setLayout(null);
							setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							String name = JOptionPane.showInputDialog("이름을 입력하시오.");
							JLabel t1=new JLabel(name+"님의 점수는"+game_Score+"점입니다.");
							t1.setSize(200,20);
							t1.setLocation(50,100);
							add(t1);
							b1 = new JButton("등급 보기");
							MyAction l = new MyAction();
							b1.addActionListener(l);
							b1.setBounds(95, 150, 100, 50);
							add(b1);
							
							setSize(300,300);
							setVisible(true);
						}	
						
						class MyAction implements ActionListener
						{
							public void actionPerformed(ActionEvent e)
							{
								new grades();
							}
						}
					}new Scoreframe();
				}
				game_Score+=10;
			}
			
			
		}
		
		
		
		if(t % 30 ==0)
		{
			Random random = new Random();
			
			int randompoint = 0;
			randompoint = random.nextInt(600);
			e = new Enemy(randompoint, 0,enemy_Speed);
			enemy_list.add(e);
			e = new Enemy(randompoint, 0,enemy_Speed);
			enemy_list.add(e);
			e = new Enemy(randompoint, 0,enemy_Speed);
			enemy_list.add(e);
			e = new Enemy(randompoint, 0,enemy_Speed);
			enemy_list.add(e);
			e = new Enemy(randompoint, 0,enemy_Speed);
			enemy_list.add(e);
		}
		if(t2 % 109 ==0)
		{
			Random random = new Random();
			
			int randompoint = 0;
			randompoint = random.nextInt(600);
			
			e = new Enemy(randompoint, 0,enemy_Speed);
			enemy_list.add(e);
			e = new Enemy(randompoint, 0,enemy_Speed);
			enemy_list.add(e);
			e = new Enemy(randompoint, 0,enemy_Speed);
			enemy_list.add(e);
			e = new Enemy(randompoint, 0,enemy_Speed);
			enemy_list.add(e);
		}
	}
	
	
	public boolean Crash(int x1, int y1, int x2, int y2, Image img1, Image img2)
	{
		boolean check = false;


		if (Math.abs((x1 + img1.getWidth(null) / 2)- (x2 + img2.getWidth(null) / 2)) < (img2.getWidth(null) / 2 + img1.getWidth(null) / 2)
				&& 
			Math.abs((y1 + img1.getHeight(null) / 2) - (y2 + img2.getHeight(null) / 2)) < (img2.getHeight(null) / 2 + img1.getHeight(null) / 2))
		{		
			check = true;//위 값이 true면 check에 true를 전달합니다.		
		}
		else
		{ check = false;}
		return check; //check의 값을 메소드에 리턴 시킵니다.
	}
	
	public void paint(Graphics g)
	{
		buffImage = createImage(600,800);

		buffg = buffImage.getGraphics();

		update(g);
	}
	
	public void update(Graphics g)
	{
		Draw();
		
		Draw_Enemy();
		
		
		
		Draw_StatusText();
		
		g.drawImage(buffImage,0,0,this);
	}
	
	public void Draw()
	{ 
		switch (player_State)
		{ 
			case 0 : // 평상시
			if((t / 5 %2) == 0)
			{ 
				buffg.drawImage(me[0], x, y, this);
			}else { buffg.drawImage(me[0], x, y, this); }
			break;


			case 1 : 
			//스킬 사용시 비워둠

			player_State = 0;
			


			break;


			case 2 : // 충돌
			break;
		}
	}

	
	
	
	public void Draw_Enemy()
	{
		for (int i = 0 ; i < enemy_list.size() ; ++i )
		{
			e = (Enemy)(enemy_list.get(i));
			buffg.drawImage(enemy, e.x, e.y, this);
		}
	}
	
	public void Draw_StatusText()
	{
		buffg.setFont(new Font("Defualt",Font.BOLD,20));
		buffg.drawString("점수: " + game_Score, 50, 50);
		buffg.drawString("몫 : " + player_HitPoint, 50, 70);
	}

	
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:KeyUp=true; break;
		case KeyEvent.VK_DOWN:KeyDown=true; break;
		case KeyEvent.VK_LEFT:KeyLeft=true; break;
		case KeyEvent.VK_RIGHT:KeyRight=true; break;
		case KeyEvent.VK_SPACE:KeySpace=true; break;
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:KeyUp=false; break;
		case KeyEvent.VK_DOWN:KeyDown=false; break;
		case KeyEvent.VK_LEFT:KeyLeft=false; break;
		case KeyEvent.VK_RIGHT:KeyRight=false; break;
		case KeyEvent.VK_SPACE:KeySpace=false; break;
		}
	}
	
	public void keyTyped(KeyEvent e){}
	
	public void KeyProcess()
	{
		if(KeyUp == true) y-=8;
		if(KeyDown == true) y+=8;
		if(KeyLeft == true) x-=8;
		if(KeyRight == true) x+=8;	
	}
	
	
	
	class Enemy
	{
		//여기서 속도를 조절하면 댈듯
		int x,y;
		
		int speed;
		
		Enemy(int x, int y,int speed)
		{
			this.x=x;
			this.y=y;
			this.speed=speed;
		}
		
		public void move()
		{
			y+=speed;
		}
	}
}