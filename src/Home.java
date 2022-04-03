import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Home {
	private int x, y, player;
	private TankClient tc;
	public static final int width = 43, length = 43; 
	private boolean live = true;

	private static Toolkit tk = Toolkit.getDefaultToolkit(); 
	private static Image[] homeImags;
	static {
		homeImags = new Image[] { tk.getImage(CommonWall.class.getResource("Images/home1.png")), tk.getImage(CommonWall.class.getResource("Images/home2.png"))};
	}

	public Home(int x, int y, int p, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
		player=p;
/*
		homeImags = new Image[3];
		for(int i=1; i<=2; i++){
			homeImags[i]= tk.getImage(CommonWall.class.getResource("Images/home1.jpg"));
		}

 */
	}

	public void gameOver(Graphics g) {

		tc.tanks.clear();
		tc.metalWall.clear();
		tc.otherWall.clear();
		tc.bombTanks.clear();
		tc.theRiver.clear();
		tc.trees.clear();
		tc.bullets.clear();
		if(player==1) {
			tc.homeTank[0].setLive(false);
		}else if (player==2){
			tc.homeTank[1].setLive(false);
		}
		Color c = g.getColor(); 
		g.setColor(Color.green);
		Font f = g.getFont();
		g.setFont(new Font(" ", Font.PLAIN, 40));
		g.setFont(f);
		g.setColor(c);

	}

	public void draw(Graphics g) {

		if (live) { 
			g.drawImage(homeImags[player-1], x, y, null);

			for (int i = 0; i < tc.homeWall.size(); i++) {
				CommonWall w = tc.homeWall.get(i);
				w.draw(g);
			}
		} else {
			gameOver(g); 

		}
	}

	public boolean isLive() { 
		return live;
	}

	public int getPlayer(){ return player;}

	public void setLive(boolean live) { 
		this.live = live;
	}

	public Rectangle getRect() { 
		return new Rectangle(x, y, width, length);
	}

}
