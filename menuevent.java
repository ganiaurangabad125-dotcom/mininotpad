import java.awt.*;
import java.awt.event.*;
import java.io.*;

class menuevent extends Frame implements ActionListener {
    MenuBar mb;
    Menu m1, m2, m3;
    MenuItem i1, i2, i3, i4, i5, i6, i7, i8, i9;
    TextArea ta;
    Dimension d;

    public menuevent() {
        setTitle("File Example");
        setSize(300, 300);
        setLocation(300, 300);
        setVisible(true);

        d = getMaximumSize();
        setSize(d);

        ta = new TextArea();
        Font f = new Font(Font.SERIF, Font.BOLD, 30);
        ta.setFont(f);
        add(ta);
        ta.requestFocus();

        mb = new MenuBar();
        m1 = new Menu("File");
        
        i1 = new MenuItem("New");
        i2 = new MenuItem("Open");
        i3 = new MenuItem("Save");
        i4 = new MenuItem("Save as");

        m1.add(i1);
        m1.add(i2);
        m1.add(i3);
       // m1.addSeparator();
        m1.add(i4);
        
		//mb = new MenuBar();
        m2= new Menu("Edit");
        
        i6 = new MenuItem("Cut");
        i7 = new MenuItem("Copy");
        i8 = new MenuItem("Paste");
        m2.add(i6);m2.add(i7);m2.add(i8);
		
        
        mb.add(m1);
		mb.add(m2);
        setMenuBar(mb);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});

        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == i1) {
            ta.setText("");
        } 
        else if (ae.getSource() == i2) {
            FileDialog fd = new FileDialog(this, "File Open", FileDialog.LOAD);
            fd.setVisible(true);
            
            String path = fd.getDirectory() + fd.getFile();
            System.out.println(path);
            
            String st = "";
            try {
                FileInputStream fin = new FileInputStream(path);
                int k;
                while ((k = fin.read()) != -1) {
                    System.out.print((char) k);
                    st += (char) k;
                }
                ta.setText(st);
                fin.close();
            } catch (IOException fe) {
                fe.printStackTrace();
            }
        } 
        else if (ae.getSource() == i3) {
            FileDialog fd = new FileDialog(this, "File Save Dialog", FileDialog.SAVE);
            fd.setVisible(true);
            
            String path = fd.getDirectory() + fd.getFile();
            try {
                FileWriter fw = new FileWriter(path);
                String s = ta.getText();
                fw.write(s);
                fw.close();
            } catch (IOException se) {
                se.printStackTrace();
            }
        }
        else if (ae.getSource() == i4) {
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        menuevent me = new menuevent();
    }
}