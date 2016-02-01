import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Nicholas on 2016-01-31.
 */

//"The quick brown fox jumps over the lazy dog"
public class Main extends JFrame implements KeyListener {
    private boolean start = false;
    private boolean btnPressed = false;
    private int keyPressed = -1;
    double[] keyTime = new double[26];
    double[] numKeyPressed = new double[26];
    double[] posTime = new double[26];
    char[] keys = new char[26];
    JTextArea newText = new JTextArea(10, 30);
    private int width = 690, height = 420;
    private JPanel window;
    public Main () {
        window = new JPanel();
        JTextArea textArea = new JTextArea(20, 60);
        window.add(new JLabel("Please Type: \"The quick brown fox jumps over the lazy dog\" Press enter and start typing."));
        window.add(textArea);
        JButton btn = new JButton("Stop");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPressed = true;
            }
        });
        window.add(btn);
        window.addKeyListener(this);
        textArea.addKeyListener(this);

        setTitle("Keyboard Mapper");
        setSize(new Dimension(width, height));
        setContentPane(window);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        getData();
        setTimeToPos();
        matchKeys();
        formattedOutput();
    }
    public void getData(){
        long time1, time2;
        while (!start)
            sleep(1);
        while (!btnPressed){
            time1 = System.currentTimeMillis();
            while (keyPressed == -1) {
                sleep(1);
                if (btnPressed)
                    break;
            }
            time2 = System.currentTimeMillis();
            if (!btnPressed) {
                keyTime[keyPressed - 65] += (time2 - time1);
                numKeyPressed[keyPressed - 65]++;
            }
            keyPressed = -1;

        }
    }

    public void setTimeToPos () {
        for (int i = 0 ; i < 26 ; i++) {
            if (keyTime[i] == 0){
                numKeyPressed[i] = 1;
                keyTime[i] = 89888;
            }
        }
        posTime[0] = keyTime[16] / numKeyPressed[16];
        posTime[1] = keyTime[23] / numKeyPressed[23];
        posTime[2] = keyTime[4] / numKeyPressed[4];
        posTime[3] = keyTime[17] / numKeyPressed[17];
        posTime[4] = keyTime[19] / numKeyPressed[19];
        posTime[5] = keyTime[24] / numKeyPressed[24];
        posTime[6] = keyTime[20] / numKeyPressed[20];
        posTime[7] = keyTime[8] / numKeyPressed[8];
        posTime[8] = keyTime[14] / numKeyPressed[14];
        posTime[9] = keyTime[15] / numKeyPressed[15];
        posTime[10] = keyTime[0] / numKeyPressed[0];
        posTime[11] = keyTime[18] / numKeyPressed[18];
        posTime[12] = keyTime[3] / numKeyPressed[3];
        posTime[13] = keyTime[5]/ numKeyPressed[5];
        posTime[14] = keyTime[6]/ numKeyPressed[6];
        posTime[15] = keyTime[7]/ numKeyPressed[7];
        posTime[16] = keyTime[9]/ numKeyPressed[9];
        posTime[17] = keyTime[10]/ numKeyPressed[10];
        posTime[18] = keyTime[11]/ numKeyPressed[11];
        posTime[19] = keyTime[25]/ numKeyPressed[25];
        posTime[20] = keyTime[23]/ numKeyPressed[23];
        posTime[21] = keyTime[2]/ numKeyPressed[2];
        posTime[22] = keyTime[21]/ numKeyPressed[21];
        posTime[23] = keyTime[1]/ numKeyPressed[1];
        posTime[24] = keyTime[13]/ numKeyPressed[13];
        posTime[25] = keyTime[12]/ numKeyPressed[12];
    }

    public void matchKeys (){
        for (int i = 0 ; i < 26 ; i++){
            double lowTime = 10000;
            int lowTimePos = 0;
            double highPress = 0;
            int highPressPos = 0;
            for (int j = 0 ; j < 26 ; j++) {
                if (posTime[j] <= lowTime){
                    lowTime = posTime[j];
                    lowTimePos = j;
                }
                if (numKeyPressed[j] >= highPress){
                    highPress = numKeyPressed[j];
                    highPressPos = j;
                }
            }
            posTime[lowTimePos] = 999999;
            numKeyPressed[highPressPos] = 0;
            keys[lowTimePos] = (char) (highPressPos + 65);
        }
    }

    public void formattedOutput () {
        for (int i =0  ; i <= 9 ; i++) {
            System.out.print(keys[i] + " ");
        }
        System.out.print("\n ");
        for (int i = 10  ; i <= 18 ; i++) {
            System.out.print(keys[i] + " ");
        }
        System.out.print("\n  ");
        for (int i = 19 ; i <= 25 ; i++) {
            System.out.print(keys[i] + " ");
        }
        System.out.println();
        System.out.println("\nDone!");
        System.out.println("\nDone!");
    }
    public void sleep(int t)
    {
        try {
            Thread.sleep(t);
        } catch (Exception e) {}
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            start = true;
        if (e.getKeyCode() >=65 && e.getKeyCode() <= 90)
            keyPressed = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main (String[] args) {
        new Main();

    }
}
