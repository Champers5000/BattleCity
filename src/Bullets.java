import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bullets {
    public static int speedX = 12;
    public static int speedY = 12;

    public static final int width = 10;
    public static final int length = 10;

    private int x, y, origin;
    Direction direction;

    private boolean good, leftTank;
    private boolean live;

    private TankClient tc;

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] bulletImages;
    private static Map<String, Image> imgs = new HashMap<String, Image>();

    static {
        bulletImages = new Image[]{
                tk.getImage(Bullets.class.getResource(
                        "Images/bulletL.gif")),

                tk.getImage(Bullets.class.getResource(
                        "Images/bulletU.gif")),

                tk.getImage(Bullets.class.getResource(
                        "Images/bulletR.gif")),

                tk.getImage(Bullets.class.getResource(
                        "Images/bulletD.gif")),

        };

        imgs.put("L", bulletImages[0]);

        imgs.put("U", bulletImages[1]);

        imgs.put("R", bulletImages[2]);

        imgs.put("D", bulletImages[3]);

    }


    public Bullets(int x, int y, boolean good, Direction dir, TankClient tc, int or) {
        this.x = x;
        this.y = y;
        this.direction = dir;
        live=true;
        this.good = good;
        this.tc = tc;
        leftTank=false;
        origin = or;
    }

    private void move() {

        switch (direction) {
            case L:
                x -= speedX;
                break;

            case U:
                y -= speedY;
                break;

            case R:
                x += speedX;
                break;

            case D:
                y += speedY;
                break;

            case STOP:
                break;
        }

        if (x < 0 || y < 0 || x > TankClient.Fram_width
                || y > TankClient.Fram_length) {
            live = false;
        }
    }

    public void draw(Graphics g) {
        if (!live) {
            tc.bullets.remove(this);
            return;
        }

        switch (direction) {
            case L:
                g.drawImage(imgs.get("L"), x, y, null);
                break;

            case U:
                g.drawImage(imgs.get("U"), x, y, null);
                break;

            case R:
                g.drawImage(imgs.get("R"), x, y, null);
                break;

            case D:
                g.drawImage(imgs.get("D"), x, y, null);
                break;

        }

        move();
    }

    public boolean isLive() {
        return live;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, length);
    }

    public boolean hitTanks(List<Tank> tanks) {
        for (int i = 0; i < tanks.size(); i++) {
            if (hitTank(tanks.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean hitTank(Tank t) {
        if(t.getPlayer()==origin){
            return false;
        }
        boolean intersecting = this.live && this.getRect().intersects(t.getRect());

        if(intersecting){
            BombTank e = new BombTank(t.getX(), t.getY(), tc);
            tc.bombTanks.add(e);
            t.setLife(t.getLife() - 50);
            if (t.getLife() <= 0) {
                t.setLive(false);
            }
            this.live = false;
        }
        return intersecting;
        /*if (leftTank) {
            if (this.live && this.getRect().intersects(t.getRect()) && t.isLive()
                    && this.good != t.isGood()) {

                BombTank e = new BombTank(t.getX(), t.getY(), tc);
                tc.bombTanks.add(e);
                if (t.isGood()) {
                    t.setLife(t.getLife() - 50);
                    if (t.getLife() <= 0)
                        t.setLive(false);
                } else {
                    t.setLive(false);
                }
                this.live = false;

                return true;
        } else {
                if(this.getRect().intersects(t.getRect())){
                    return true;
                }
            }
        }
        */
    }

    public boolean hitWall(CommonWall w) {
        if (this.live && this.getRect().intersects(w.getRect())) {
            this.live = false;
            this.tc.otherWall.remove(w);
            this.tc.homeWall.remove(w);
            return true;
        }
        return false;
    }

    public boolean hitBullet(Bullets w) {
        if (this.live && this.getRect().intersects(w.getRect())) {
            this.live = false;
            this.tc.bullets.remove(w);
            return true;
        }
        return false;
    }

    public boolean hitWall(MetalWall w) {
        if (this.live && this.getRect().intersects(w.getRect())) {
            this.live = false;
            return true;
        }
        return false;
    }

    public boolean hitHome(Home h) {
        if(h.getPlayer()==origin){
            return false;
        }
        if (this.live && this.getRect().intersects(h.getRect())) {
            this.live = false;
            h.setLive(false);
            return true;
        }
        return false;
    }

}
