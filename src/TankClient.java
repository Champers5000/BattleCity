import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class TankClient extends Frame implements ActionListener {


	private static final long serialVersionUID = 1L;
	public static final int Fram_width = 800; 
	public static final int Fram_length = 800;
	public static boolean printable = true, spawnTanks;
	public static String[] names;
	MenuBar jmb = null;
	Menu jm1 = null, jm2 = null, jm3 = null, jm4 = null,jm5=null;
	MenuItem jmi1 = null, jmi2 = null, jmi3 = null, jmi4 = null, jmi5 = null,
			jmi6 = null, jmi7 = null, jmi8 = null, jmi9 = null,jmi10=null,jmi11=null;
	Image screenImage = null;
	
	Tank[] homeTank;
	Boolean Player2=true;
	GetBlood blood = new GetBlood();
	Home home = new Home(373, 50,1, this);
	Home home2 = new Home(373, 750, 2,this);
	Boolean win=false,lose=false;
	List<River> theRiver = new ArrayList<River>();
	List<Tank> tanks = new ArrayList<Tank>();
	List<BombTank> bombTanks = new ArrayList<BombTank>();
	List<Bullets> bullets = new ArrayList<Bullets>();
	List<Tree> trees = new ArrayList<Tree>();
	List<CommonWall> homeWall = new ArrayList<CommonWall>();
	List<CommonWall> otherWall = new ArrayList<CommonWall>();
	List<MetalWall> metalWall = new ArrayList<MetalWall>();

	public TankClient(String[] name, boolean spawnEnemyTanks) {

        //gui stuff
		jmb = new MenuBar();
		jm1 = new Menu("Game");
		jm2 = new Menu("Pause/Continue");
		jm3 = new Menu("Help");
		jm4 = new Menu("Level");
		//jm5 = new Menu("Addition");
		jm1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		jm2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		jm3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		jm4.setFont(new Font("Times New Roman", Font.BOLD, 15));

		jmi1 = new MenuItem("New Game");
		jmi2 = new MenuItem("Exit");
		jmi3 = new MenuItem("Stop");
		jmi4 = new MenuItem("Continue");
		jmi5 = new MenuItem("Help");
		jmi6 = new MenuItem("Level1");
		jmi7 = new MenuItem("Level2");
		jmi8 = new MenuItem("Level3");
		jmi9 = new MenuItem("Level4");
		//jmi10=new MenuItem("Add Player 2");
		jmi11= new MenuItem("Join other's game");
		jmi1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		jmi2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		jmi3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		jmi4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		jmi5.setFont(new Font("Times New Roman", Font.BOLD, 15));

		jm1.add(jmi1);
		jm1.add(jmi2);
		jm2.add(jmi3);
		jm2.add(jmi4);
		jm3.add(jmi5);
		jm4.add(jmi6);
		jm4.add(jmi7);
		jm4.add(jmi8);
		jm4.add(jmi9);
		//jm5.add(jmi10);
		//jm5.add(jmi11);

		jmb.add(jm1);
		jmb.add(jm2);

		jmb.add(jm4);
		//jmb.add(jm5);
		jmb.add(jm3);


		jmi1.addActionListener(this);
		jmi1.setActionCommand("NewGame");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("Exit");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("Stop");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("Continue");
		jmi5.addActionListener(this);
		jmi5.setActionCommand("help");
		jmi6.addActionListener(this);
		jmi6.setActionCommand("level1");
		jmi7.addActionListener(this);
		jmi7.setActionCommand("level2");
		jmi8.addActionListener(this);
		jmi8.setActionCommand("level3");
		jmi9.addActionListener(this);
		jmi9.setActionCommand("level4");
		//jmi10.addActionListener(this);
		//jmi10.setActionCommand("Player2");
		jmi11.addActionListener(this);
		jmi11.setActionCommand("Join");

		this.setMenuBar(jmb);
		this.setVisible(true);

        //construct tanks
		spawnTanks = spawnEnemyTanks;
		names = name;
        homeTank = new Tank[names.length];
        homeTank[0]=new Tank(377, 250,true,Direction.STOP,this,1, names[0]);
        homeTank[1] = new Tank(377, 550,true,Direction.STOP,this,2, names[1]);


		//construct bricks around the base(Home)
		for (int i = 0; i < 10; i++) {
			if (i < 4) {
				homeWall.add(new CommonWall(350, 780 - 21 * i, this));
				homeWall.add(new CommonWall(350, 42+21 * i, this));
			} else if (i < 7) {
				homeWall.add(new CommonWall(372 + 22 * (i - 4), 717, this));
				homeWall.add(new CommonWall(372 + 22 * (i - 4), 105, this));
			} else {
				homeWall.add(new CommonWall(416, 738 + (i - 7) * 21, this));
				homeWall.add(new CommonWall(416, 42 + (i - 7) * 21, this));
			}
		}
		//second layer of brick walls
		for(int i=0; i<5; i++){
			homeWall.add(new CommonWall(328, 42+21*i, this));
			homeWall.add(new CommonWall(438, 42+21*i, this));
			homeWall.add(new CommonWall(306, 42+21*i, this));
			homeWall.add(new CommonWall(460, 42+21*i, this));
			homeWall.add(new CommonWall(350 + 22 * i, 126, this));
			homeWall.add(new CommonWall(328, 696+21*i, this));
			homeWall.add(new CommonWall(438, 696+21*i, this));
			homeWall.add(new CommonWall(306, 696+21*i, this));
			homeWall.add(new CommonWall(460, 696+21*i, this));
			homeWall.add(new CommonWall(350 + 22 * i, 696, this));

		}

		//construct all the long brick walls
		for (int i = 0; i < 12; i++) {
			if (i < 16) {
				otherWall.add(new CommonWall(200 + 21 * i, 300, this));
				//otherWall.add(new CommonWall(400 + 21 * i, 180, this));
				otherWall.add(new CommonWall(200 + 21*i, 320, this));
				//otherWall.add(new CommonWall(400 + 21*i, 220, this));
				otherWall.add(new CommonWall(250, 540 + 21 * i, this));
				//otherWall.add(new CommonWall(292, 400 + 21 * i, this));
				otherWall.add(new CommonWall(520, 400 + 21 * i, this));
				otherWall.add(new CommonWall(542, 400 + 21 * i, this));
				otherWall.add(new CommonWall(560, 60 + 21 * i, this));
			}
		}

		for (int i = 0; i < 10; i++) {
				metalWall.add(new MetalWall(140 + 30 * i, 150, this));
				metalWall.add(new MetalWall(120, 400 + 20 * i, this));
				metalWall.add(new MetalWall(140 + 30 * i, 180, this));
				metalWall.add(new MetalWall(350+20*i,640,this));
		}

		for (int i = 0; i < 4; i++) {
			if (i < 4) {
				trees.add(new Tree(0 + 30 * i, 360, this));
				trees.add(new Tree(220 + 30 * i, 360, this));
				trees.add(new Tree(440 + 30 * i, 360, this));
				trees.add(new Tree(660 + 30 * i, 360, this));
			}

		}

		//add River
		theRiver.add(new River(85, 100, this));
		theRiver.add(new River(566,560,this));

		//add enemy tanks
		if(spawnEnemyTanks) {
			for (int i = 0; i < 6; i++) {
				tanks.add(new Tank(600 + 40 * i, 70, false, Direction.D, this, 0, ""));
				tanks.add(new Tank(10 + 40 * i, 660, false, Direction.D, this, 0, ""));
				tanks.add(new Tank(20, 40 + 100 * i, false, Direction.D, this, 0, ""));
				tanks.add(new Tank(780, 40 + 100 * i, false, Direction.D, this, 0, ""));
				/*
				if (i < 9)
					tanks.add(new Tank(150 + 70 * i, 100, false, Direction.D, this, 0, ""));
				else if (i < 15)
					tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D, this, 0, ""));
				else
					tanks.add(new Tank(10, 50 * (i - 12), false, Direction.D, this, 0, ""));
					*/
			}
		}

		this.setSize(Fram_width, Fram_length);
		this.setLocation(280, 50);
		this.setTitle("Battle City    Project stolen from some guy on Github for Dr. Jozi");

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0); }});
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.setVisible(true);

		//start running game
		this.addKeyListener(new KeyMonitor());
		new Thread(new PaintThread()).start();
	}



	public void update(Graphics g) {

		screenImage = this.createImage(Fram_width, Fram_length);

		Graphics gps = screenImage.getGraphics();
		Color c = gps.getColor();
		gps.setColor(Color.GRAY);
		gps.fillRect(0, 0, Fram_width, Fram_length);
		gps.setColor(c);
		framPaint(gps);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void framPaint(Graphics g) {
/*
		if (!Player2) {
			if (tanks.size() == 0 && home.isLive() && homeTank.isLive() && lose == false) {
				Font f = g.getFont();
				g.setFont(new Font("Times New Roman", Font.BOLD, 60));
				this.otherWall.clear();
				g.drawString("Congratulations! ", 200, 300);
				g.setFont(f);
				win = true;
			}

			if (homeTank.isLive() == false && win == false) {
				Font f = g.getFont();
				g.setFont(new Font("Times New Roman", Font.BOLD, 40));
				tanks.clear();
				bullets.clear();
				g.drawString("Sorry. You lose!", 200, 300);
				lose = true;
				g.setFont(f);
			}
		}else{
			if (home.isLive() && homeTank.isLive()&&!homeTank2.isLive()) {
				Font f = g.getFont();
				g.setFont(new Font("Times New Roman", Font.BOLD, 60));
				this.otherWall.clear();
				g.drawString("Player 1 wins!", 200, 300);
				g.setFont(f);
			}

			if (home2.isLive() && homeTank2.isLive()&&!homeTank.isLive()) {
				Font f = g.getFont();
				g.setFont(new Font("Times New Roman", Font.BOLD, 40));
				tanks.clear();
				bullets.clear();
				g.drawString("Player 2 wins", 200, 300);
				g.setFont(f);
			}
		}
*/
		home.draw(g);
		home2.draw(g);
		for(int i=0; i<homeTank.length; i++) {
			homeTank[i].draw(g);
			homeTank[i].eat(blood);
		}
		//g.setColor(c);

		for (int i = 0; i < theRiver.size(); i++) {
			River r = theRiver.get(i);
			r.draw(g);
		}

		for (int i = 0; i < bullets.size(); i++) { 
			Bullets m = bullets.get(i);
			m.hitTanks(tanks);
			m.hitTank(homeTank[0]);
			m.hitTank(homeTank[1]);
			m.hitHome(home);
            m.hitHome(home2);
            for(int j=0;j<bullets.size();j++){
				if (i==j) continue;
				Bullets bts=bullets.get(j);
				m.hitBullet(bts);
			}
			for (int j = 0; j < metalWall.size(); j++) { 
				MetalWall mw = metalWall.get(j);
				m.hitWall(mw);
			}

			for (int j = 0; j < otherWall.size(); j++) {
				CommonWall w = otherWall.get(j);
				m.hitWall(w);
			}

			for (int j = 0; j < homeWall.size(); j++) {
				CommonWall cw = homeWall.get(j);
				m.hitWall(cw);
			}
			m.draw(g); 
		}

		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i); 

			for (int j = 0; j < homeWall.size(); j++) {
				CommonWall cw = homeWall.get(j);
				t.collideWithWall(cw); 
				//cw.draw(g);
			}
			for (int j = 0; j < otherWall.size(); j++) { 
				CommonWall cw = otherWall.get(j);
				t.collideWithWall(cw);
				//cw.draw(g);
			}
			for (int j = 0; j < metalWall.size(); j++) {
				MetalWall mw = metalWall.get(j);
				t.collideWithWall(mw);
				// mw.draw(g);
			}
			for (int j = 0; j < theRiver.size(); j++) {
				River r = theRiver.get(j);
				t.collideRiver(r);
				// r.draw(g);
				// t.draw(g);
			}


			t.collideWithTanks(tanks);
			t.collideHome(home);

			t.draw(g);
		}

		//blood.draw(g);

		for (int i = 0; i < trees.size(); i++) { 
			Tree tr = trees.get(i);
			tr.draw(g);
		}

		for (int i = 0; i < bombTanks.size(); i++) { 
			BombTank bt = bombTanks.get(i);
			bt.draw(g);
		}

		for (int i = 0; i < otherWall.size(); i++) { 
			CommonWall cw = otherWall.get(i);
			cw.draw(g);
		}

		for (int i = 0; i < metalWall.size(); i++) { 
			MetalWall mw = metalWall.get(i);
			mw.draw(g);
		}

		for(int j=0; j< homeTank.length; j++) {

			homeTank[j].collideWithTanks(tanks);
			homeTank[j].collideHome(home);

			for (int i = 0; i < metalWall.size(); i++) {
				MetalWall w = metalWall.get(i);
				homeTank[j].collideWithWall(w);
				//w.draw(g);
			}

			for (int i = 0; i < otherWall.size(); i++) {
				CommonWall cw = otherWall.get(i);
				homeTank[j].collideWithWall(cw);
				//cw.draw(g);
			}

			for (int i = 0; i < homeWall.size(); i++) {
				CommonWall w = homeWall.get(i);
				homeTank[j].collideWithWall(w);
				//w.draw(g);
			}

			for (int i = 0; i < theRiver.size(); i++) {
				River r = theRiver.get(i);
				homeTank[i].collideRiver(r);
				//r.draw(g);
			}
		}

		//Top Text
		g.setColor(Color.green);
		Font f1 = g.getFont();
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		if(!Player2)g.drawString("Tanks left in the field: ", 200, 70);
		else g.drawString("Tanks left in the field: ", 100, 70);
		g.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		if(!Player2)g.drawString("" + tanks.size(), 400, 70);
		else g.drawString("" + tanks.size(), 300, 70);
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		if(!Player2)g.drawString("Health: ", 580, 70);
		else g.drawString("Health: ", 380, 70);
		g.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		if(!Player2) g.drawString("" + homeTank[0].getLife(), 650, 70);
		else g.drawString("Player1: " + homeTank[0].getLife()+"    Player2:"+homeTank[1].getLife(), 450, 70);
		g.setFont(f1);

		if (home.isLive() && homeTank[0].isLive()&&!homeTank[1].isLive()) {
			Font f = g.getFont();
			g.setFont(new Font("Times New Roman", Font.BOLD, 60));
			this.otherWall.clear();
			tanks.clear();
			bullets.clear();
			g.drawString(names[0]+ " wins", 200, 300);
			g.setFont(f);
		}

		if (home2.isLive() && homeTank[1].isLive()&&!homeTank[0].isLive()) {
			Font f = g.getFont();
			g.setFont(new Font("Times New Roman", Font.BOLD, 60));
			this.otherWall.clear();
			tanks.clear();
			bullets.clear();
			g.drawString(names[1]+" wins", 200, 300);
			g.setFont(f);
		}
	}



	private class PaintThread implements Runnable {
		public void run() {
			// TODO Auto-generated method stub
			while (printable) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class KeyMonitor extends KeyAdapter {

		public void keyReleased(KeyEvent e) { 
			homeTank[0].keyReleased(e);
			homeTank[1].keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			homeTank[0].keyPressed(e);
			homeTank[1].keyPressed(e);
		}

	}
	
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("NewGame")) {
			printable = false;
			Object[] options = { "Confirm", "Cancel" };
			int response = JOptionPane.showOptionDialog(this, "Confirm to start a new game?", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {

				printable = true;
				this.dispose();
				new TankClient(names, spawnTanks);
			} else {
				printable = true;
				new Thread(new PaintThread()).start(); 
			}

		} else if (e.getActionCommand().endsWith("Stop")) {
			printable = false;
			// try {
			// Thread.sleep(10000);
			//
			// } catch (InterruptedException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
		} else if (e.getActionCommand().equals("Continue")) {

			if (!printable) {
				printable = true;
				new Thread(new PaintThread()).start();
			}
		} else if (e.getActionCommand().equals("Exit")) {
			printable = false;
			Object[] options = { "Confirm", "Cancel" };
			int response = JOptionPane.showOptionDialog(this, "Confirm to exit?", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				System.out.println("break down");
				System.exit(0);
			} else {
				printable = true;
				new Thread(new PaintThread()).start(); 

			}

		} else /*if(e.getActionCommand().equals("Player2")){
			printable = false;
			Object[] options = { "Confirm", "Cancel" };
			int response = JOptionPane.showOptionDialog(this, "Confirm to add player2?", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				printable = true;
				this.dispose();
				TankClient Player2add=new TankClient();
				Player2add.Player2=true;
			} else {
				printable = true;
				new Thread(new PaintThread()).start();
			}
		}
		else*/ if (e.getActionCommand().equals("help")) {
			printable = false;
			JOptionPane.showMessageDialog(null, "Use WASD to control Player1's direction, use space to fire and restart with pressing R\nUse direction key to Control Player2, use Numpad0 to fire",
					"Help", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(true);
			printable = true;
			new Thread(new PaintThread()).start(); 
		} else if (e.getActionCommand().equals("level1")) {
			Tank.count = 12;
			Tank.speedX = 6;
			Tank.speedY = 6;
			Bullets.speedX = 10;
			Bullets.speedY = 10;
			this.dispose();
			new TankClient(names, spawnTanks);
		} else if (e.getActionCommand().equals("level2")) {
			Tank.count = 12;
			Tank.speedX = 10;
			Tank.speedY = 10;
			Bullets.speedX = 12;
			Bullets.speedY = 12;
			this.dispose();
			new TankClient(names, spawnTanks);

		} else if (e.getActionCommand().equals("level3")) {
			Tank.count = 20;
			Tank.speedX = 14;
			Tank.speedY = 14;
			Bullets.speedX = 16;
			Bullets.speedY = 16;
			this.dispose();
			new TankClient(names, spawnTanks);
		} else if (e.getActionCommand().equals("level4")) {
			Tank.count = 20;
			Tank.speedX = 16;
			Tank.speedY = 16;
			Bullets.speedX = 18;
			Bullets.speedY = 18;
			this.dispose();
			new TankClient(names, spawnTanks);
		} /*else if (e.getActionCommand().equals("Join")){
			printable = false;
			String s=JOptionPane.showInputDialog("Please input URL:");
			System.out.println(s);
			printable = true;
			new Thread(new PaintThread()).start();
		}*/
		
	}
}
