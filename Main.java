import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Main extends JFrame implements ActionListener{

    // 1 yen = 0.56 rupees
    // 1 dollar = 83.29 rupees
    // 1 chinese yuan = 11.55 rupees
    // 1 pakistani rupee = 0.29 rupees
    // 1 russian ruble = 0.93 rupees
    // 1 pound sterling = 103.74 rupees
   
    Main(){

        

        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        new Main();   

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
}