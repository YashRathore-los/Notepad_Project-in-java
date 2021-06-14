import com.sun.glass.events.KeyEvent;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.io.*;
class notepad extends JFrame implements ActionListener {
	JTextArea a;
	JScrollPane pane;
	String text;
	notepad(){
		setBounds(0,0,1950,1060);

		JMenuBar menubar=new JMenuBar();
		JMenu file=new JMenu("file");
		JMenuItem newdoc= new JMenuItem("new");
		newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		newdoc.addActionListener(this);
		JMenuItem open= new JMenuItem("open");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		open.addActionListener(this);
		JMenuItem save= new JMenuItem("save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		save.addActionListener(this);
		JMenuItem print= new JMenuItem("print");
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		print.addActionListener(this);
		JMenuItem exit= new JMenuItem("exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
		exit.addActionListener(this);
		file.add(newdoc);
		file.add(open);
		file.add(save);
		file.add(print);
		file.add(exit);
		JMenu edit=new JMenu("Edit");
		JMenuItem copy= new JMenuItem("copy");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		copy.addActionListener(this);
		JMenuItem paste= new JMenuItem("paste");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		paste.addActionListener(this);
		JMenuItem cut= new JMenuItem("cut");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		cut.addActionListener(this);
		JMenuItem selectall= new JMenuItem("selectall");
		selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		selectall.addActionListener(this);
		edit.add(copy);
		edit.add(paste);
		edit.add(cut);
		edit.add(selectall);
		JMenu help=new JMenu("Help");
		JMenuItem about= new JMenuItem("about");
		help.add(about);
        about.addActionListener(this);
		menubar.add(file);
		menubar.add(edit);
		menubar.add(help);
		setJMenuBar(menubar);
        a=new JTextArea();
        a.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
        a.setLineWrap(true);
        a.setWrapStyleWord(true);
        pane=new JScrollPane(a);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane,BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae){
			if(ae.getActionCommand().equals("new")){
				a.setText("");
			}
			else if(ae.getActionCommand().equals("save")){
				JFileChooser saveas=new JFileChooser();
				saveas.setApproveButtonText("Save");
				int action=saveas.showOpenDialog(this);
				if(action!=JFileChooser.APPROVE_OPTION){
					return;
				}
				File filename=new File(saveas.getSelectedFile()+".txt");
				BufferedWriter outfile=null;
				try{
					outfile=new BufferedWriter(new FileWriter(filename));
					a.write(outfile);
				}catch(Exception e){}	
			}
			else if(ae.getActionCommand().equals("open")){
				JFileChooser choose=new JFileChooser();
				choose.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter restrict=new FileNameExtensionFilter("only .txtfiles","txt");
				choose.addChoosableFileFilter(restrict);
				int action=choose.showOpenDialog(this);
				if(action!=JFileChooser.APPROVE_OPTION){
					return;
				}
				File file=choose.getSelectedFile();
				BufferedReader reader=null;
				try{
					reader=new BufferedReader(new FileReader(file));
					
					a.read(reader,null);
				}catch(Exception e){}	
			}
			else if(ae.getActionCommand().equals("print")){
				try{
					a.print();
				}
				catch(Exception e){}
			}
			else if(ae.getActionCommand().equals("exit")){
				System.exit(0);
			}
			else if(ae.getActionCommand().equals("copy")){
				text=a.getSelectedText();
			}
			else if(ae.getActionCommand().equals("paste")){
				a.insert(text,a.getCaretPosition());
			}
			else if(ae.getActionCommand().equals("cut")){
				text=a.getSelectedText();
				a.replaceRange("",a.getSelectionStart(),a.getSelectionEnd());
			}
			else if(ae.getActionCommand().equals("selectall")){
				a.selectAll();
			}
			else if(ae.getActionCommand().equals("about")){
				new About().setVisible(true);
			}


		}
	public static void main(String[] args) {
		new notepad().setVisible(true);

	}
}