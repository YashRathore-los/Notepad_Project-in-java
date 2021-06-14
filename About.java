import com.sun.glass.events.KeyEvent;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.io.*;
public class About extends JFrame{
               About(){
                      setBounds(600,200,900,700);
                      setLayout(null);
                      ImageIcon i=new ImageIcon(ClassLoader.getSystemResource("image/windows.png"));
                      Image i1=i.getImage().getScaledInstance(400,80,Image.SCALE_DEFAULT);
                      ImageIcon i2=new ImageIcon(i1);
                      JLabel l=new JLabel(i2);
                      l.setBounds(90,50,600,200);
                      add(l);
                      setDefaultCloseOperation(EXIT_ON_CLOSE);
                      JLabel l2=new JLabel("<html>All rights reserved @yashrathore<br>It is a word processing program<br>which allows changing of text in the compiler form<br>It is notepad edition 1</html>");
                      l2.setBounds(300,200,400,400);
                      add(l2);
                      l2.setFont(new Font("SAN_SERIF",Font.PLAIN,22));
               }      
               public static void main(String[] args) {
               	            new About().setVisible(true);
               }
}