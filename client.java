 import java.awt.*;

    import javax.swing.*;
    import java.awt.event.*;
    import java.io.*;
    import java.net.*;

    public class client  extends JFrame implements ActionListener {
        JPanel p1;
        JTextField t1;
        JButton b1;
        static JTextArea A1;

        static Socket s;
        static DataInputStream din;
        static DataOutputStream dout;

        client(){
            p1= new JPanel();
            p1.setLayout(null);
            p1.setBackground(new Color(7,94,84));
            p1.setBounds(0,0,450,70);
            add(p1);
            ImageIcon I1= new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
            Image i2= I1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            ImageIcon i = new ImageIcon(i2);
            JLabel l1= new JLabel(i);
            setLayout(null);
            l1.setBounds(5,5,30,30);
            p1.add(l1);
            
           
    
            JLabel l3 = new JLabel("Client");
            l3.setFont(new Font("San_Serif",Font.BOLD,16));
            l3.setForeground(Color.white);
            l3.setBounds(110, 15, 100, 20);
            p1.add(l3);
           
            l1.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent me){
                    System.exit(0);
                }
    
            });
    
            //textarea
            A1 = new JTextArea();
            A1.setBounds(5, 75, 440, 580);
            A1.setFont(new Font("San_Serif",Font.BOLD,16));
            A1.setEditable(false);
            A1.setLineWrap(true);
            A1.setWrapStyleWord(true);
            // A1.setBackground(Color.BLUE);
            add(A1);
    
    
    
            t1= new JTextField();
            t1.setBounds(5,670,340,25);
    
            add(t1);
            b1= new JButton("Send");
            b1.setBounds(360, 670, 70, 25);
            b1.setBackground(Color.green);
            b1.setForeground(Color.white);
            b1.setFont(new Font("San_Serif",Font.PLAIN,16));
            b1.addActionListener(this) ;
            add(b1);
            setSize(450,700);
            setLocation(800,100);
            
            setUndecorated(true);
            setVisible(true);
        }
        public void actionPerformed(ActionEvent me){
            String out = t1.getText();
            A1.setText(A1.getText()+"\n\t\t\t"+out);
            try{
                dout.writeUTF(out);
            }catch(Exception e){}
            
            t1.setText("");
        }


       
        public static void main(String []args){
            new client();
            try{
                s= new Socket("127.0.0.1",6000);
                din = new DataInputStream(s.getInputStream()){};
                dout = new DataOutputStream(s.getOutputStream()){};
                String msginput="";
                msginput =din.readUTF();
                A1.setText(A1.getText()+"\n"+msginput);
            }catch(Exception e){}
        }
    
    }
        


