import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class Panel extends JPanel implements ActionListener
{
    JTextArea textArea;
    JMenuBar menu;
    JMenu openMenu,EditMenu;
    JMenuItem loadmMenu,saveMenu,Exit,NewFile,SelectAll,Paste,Copy,Cut;
    String text;
    JScrollPane Spane;
    Panel()
    {
    
    textArea = new JTextArea();
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    menu=new JMenuBar();
    openMenu=new JMenu("File"); 
    EditMenu=new JMenu("Edit");
    NewFile=new JMenuItem("New");    //menu item
    loadmMenu=new JMenuItem("Load"); //menu item
    saveMenu=new JMenuItem("Save");  //menu item
    Exit=new JMenuItem("Exit");  //Exit item

    SelectAll=new JMenuItem("Select All");
    Paste=new JMenuItem("Paste");
    Copy=new JMenuItem("Copy");
    Cut=new JMenuItem("Cut");


    loadmMenu.addActionListener(this);
    saveMenu.addActionListener(this);
    Exit.addActionListener(this);
    NewFile.addActionListener(this);

    SelectAll.addActionListener(this);
    Paste.addActionListener(this);
    Copy.addActionListener(this);
    Cut.addActionListener(this);

        
    openMenu.setMnemonic(KeyEvent.VK_F); // Alt+F to open file option
    loadmMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
    saveMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
    Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));  //  for ctrl+Q to exit
    NewFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));

    EditMenu.setMnemonic(KeyEvent.VK_E); // Alt+E to open edit option
    SelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));

    Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
    Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));  //  for ctrl+E to exit
    Cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));

    



    add(textArea);
    openMenu.add(NewFile);
    openMenu.add(loadmMenu);
    openMenu.add(saveMenu);
    openMenu.add(Exit);

    EditMenu.add(SelectAll);
    EditMenu.add(Paste);
    EditMenu.add(Copy);
    EditMenu.add(Cut);

    menu.add(openMenu);
    menu.add(EditMenu);
    
    Spane=new JScrollPane(textArea,ScrollPaneLayout.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneLayout.HORIZONTAL_SCROLLBAR_NEVER);
    add(Spane);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==loadmMenu)
        {
            //load;
            JFileChooser fileChooser= new JFileChooser(); // used to select a file
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter f=new FileNameExtensionFilter(".txt file","txt");// To select only file with .txt extension
            fileChooser.addChoosableFileFilter(f);
            int resp=fileChooser.showOpenDialog(null); // select file to open and returns response(cancel = 1 and 0 when we select a file)
            if(resp==JFileChooser.APPROVE_OPTION)  // The user has selected a file
            {
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                try
                {
                BufferedReader reader=new BufferedReader(new FileReader(file));
                textArea.read(reader,null);
                }
                catch(Exception e)
                {
                    System.out.println("Exception occured"+e.getMessage());
                }
            }
            
        }
        else if(ae.getSource()==saveMenu)
        {
                //save
            JFileChooser fileChooser= new JFileChooser(); // used to select a file

            int resp=fileChooser.showSaveDialog(null); // select file to open and returns response(cancel = 1 and 0 when we select a file)
            if(resp==JFileChooser.APPROVE_OPTION)  // The user has selected a file
            {
                String name=fileChooser.getSelectedFile().getAbsolutePath()+".txt";
                File file=new File(name);
                try
                {
                BufferedWriter out=new BufferedWriter(new FileWriter(file));
                textArea.write(out);
                }
                catch(Exception e)
                {
                    System.out.println("Exception occured"+e.getMessage());
                }
            }
                
        }
        else if(ae.getSource()==NewFile)
        {
            textArea.setText("");
        }
        else if(ae.getSource()==Exit)
        {
            System.exit(0);
        }
        else if(ae.getSource()==SelectAll)
        {
            textArea.selectAll();
        }
        else if(ae.getSource()==Paste)
        {
            textArea.insert(text, textArea.getCaretPosition());
        }
        else if(ae.getSource()==Cut)
        {
            text=textArea.getSelectedText();
            textArea.replaceRange("",textArea.getSelectionStart(), textArea.getSelectionEnd());
        }
        else if(ae.getSource()==Copy)
        {
            text=textArea.getSelectedText();
        }
    }



}
class Frame extends JFrame
{
    Frame()
    {
       
        Panel p=new Panel();
        this.setBounds(0,0,2100,2100);
        add(p);
        this.setJMenuBar(p.menu);
        this.setTitle("Notepad"); // sets the title of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the program when the cross button is clicked
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        this.setVisible(true);
        this.setResizable(true); //allow the gui to be resizable
    }
}
public class Project {
    public static void main(String[] args) {
        new Frame();
    }
}
