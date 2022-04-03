public class starter {
    final static boolean skipstartscreen=false;
    public static void main(String[] args) {
        if(skipstartscreen) {
            String[] s  = new String[]{"bob", "joe"};
            new TankClient(s, true);
        }else {
            new StartScreen();
        }
    }
}
