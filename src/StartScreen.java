import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
public class StartScreen implements ActionListener{
    JFrame frame;

    //necessary variables to access in other methods later
    JTextField[] tf;
    JLabel southlabel;
    JToggleButton spawnenemies;
    public StartScreen(){
        //Creating the Frame
        frame = new JFrame("Tank Game Start Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout(10,10));
        JPanel midpanel = new JPanel(new GridLayout(5,1,20,20));

        //construct midpanel
        JPanel[] panel = new JPanel[3];
        tf = new JTextField[]{new JTextField(10), new JTextField(10)};

        panel[0] = new JPanel();
        JLabel label1 = new JLabel("Enter Player 1 Name");
        panel[0].add(label1);
        panel[0].add(tf[0]);

        panel[1]=new JPanel();
        JLabel label2 = new JLabel("Enter Player 2 Name");
        panel[1].add(label2);
        panel[1].add(tf[1]);
        tf[1].addKeyListener(
                new KeyListener(){
                    public void keyPressed(KeyEvent e){}
                    public void keyTyped(KeyEvent e) {}
                    public void keyReleased(KeyEvent e) {
                        if(e.getKeyCode()==KeyEvent.VK_ENTER)
                            startGame();
                    }
                }
        );

        panel[2] = new JPanel();
        JLabel label3 = new JLabel("");
        JButton begin = new JButton("Begin");
        begin.addActionListener(this);
        spawnenemies = new JToggleButton("Spawn Enemy Tanks");
        panel[2].add(spawnenemies);
        panel[2].add(begin);

        midpanel.add(new JPanel());
        for(int i=0; i<panel.length; i++) {
            //frame.getContentPane().add(BorderLayout.CENTER, panel[i]);
            midpanel.add(panel[i]);
        }

        //construct north frame
        JPanel northp = new JPanel(new FlowLayout());
        northp.add(new JLabel("<html><center>Welcome to the Tank Game<br/>Project for the computer science god Dr. Jozi</center></html>"));
        northp.setPreferredSize(new Dimension(400,60));
        frame.add(BorderLayout.NORTH, northp);

        //construct center frame
        frame.add(BorderLayout.CENTER, midpanel);
        frame.setVisible(true);

        //construct south frame
        JPanel southp = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        southlabel = new JLabel("");
        southp.add(southlabel);
        southp.setPreferredSize(new Dimension(400,40));
        frame.add(BorderLayout.SOUTH, southp);
        //new TankClient();

    }

    public void startGame(){
        String[] names = new String[tf.length];
        for(int i=0; i<tf.length; i++){
            names[i]=tf[i].getText();
        }
        for(int i=0; i<names.length; i++) {
            if (names[i].equals("")) {
                System.out.println("verify failed");
                southlabel.setText("Please enter a name for all players");
                southlabel.setForeground(Color.red);
                return;
            }
        }

        //start game
        frame.setVisible(false);
        new TankClient(names,spawnenemies.isSelected());
    }
    public void actionPerformed(ActionEvent e) {
        startGame();
    }
}
