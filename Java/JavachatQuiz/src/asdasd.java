import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class asdasd extends JFrame implements ActionListener {
 
    private static final long serialVersionUID = 1L;
    private JTextArea jta = new JTextArea(50, 25);
    private JTextField jtf = new JTextField(25);
 //   private ServerBackground server = new ServerBackground();
 
    public asdasd() throws IOException {
 
        add(jta, BorderLayout.CENTER);
        add(jtf, BorderLayout.SOUTH);
        jtf.addActionListener(this);
 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(300, 200, 600, 800);
        setTitle("�����κ�");
 
     //   server.setGui(this);
     //   server.setting();
    }
 
    public static void main(String[] args) throws IOException {
        new asdasd();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = "���� : "+ jtf.getText() + "\n";
        System.out.print(msg);
  //      server.sendMessage(msg);
        jtf.setText("");
    }
 
    public void appendMsg(String msg) {
        jta.append(msg);
    }
 
}