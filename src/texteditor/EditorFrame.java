package texteditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;

/**
 *
 * @author cs171059@uniwa.gr
 */
public class EditorFrame extends JFrame {
   
    boolean CheckSave = false;  //Check if saved
    String tmp = "";  //String that sets the content of textarea every time it changes
    String data = ""; //String that contains the textarea right before exiting
    
    // Variables declaration :
    private JPanel  bottompanel;
    private JButton ClearBtn;
    private JMenuItem ClearMenuItem;
    private JButton CopyBtn;
    private JMenuItem CopyItem;
    private JMenuItem DarkMenu;
    private JMenu EditMenu;
    private JMenuItem ExitItem;
    private JMenu FileMenu;
    private JMenu AboutMenu;
    private JMenuItem AboutMenuItem;
    private JMenuItem FindMenuItem;
    private JMenuBar MenuBar;
    private JButton NewBtn;
    private JMenuItem NewItem;
    private JButton OpenBtn;
    private JMenuItem OpenItem;
    private JButton SaveBtn;
    private JMenuItem SaveItem;
    private JButton StatisticBtn;
    private JMenu ThemesMenu;
    private JMenuItem TimeDateItem;
    private JMenuItem WhiteMenu;
    private JTextArea textArea;
    private JScrollPane scroll;
    private JTextField PathTF;
    
    public EditorFrame() {
        CreateUI();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                Exit();
            }
        });  
    }

    private void CreateUI() {
        //Buttons Initialization:
        NewBtn = new JButton("New");
        OpenBtn = new JButton("Open");
        SaveBtn = new JButton("Save");
        CopyBtn = new JButton("Copy");
        ClearBtn = new JButton("Clear");
        StatisticBtn = new JButton("Statistics");
        //Text Field Initialization:
        PathTF = new JTextField(35); 
        PathTF.setPreferredSize( new Dimension( 25, 30 ));  //Set size similar to JButtons
        PathTF.setEditable(false);  //Not editable
        //Menu Bar Initialization:
        MenuBar = new JMenuBar();
        //File Menu :
        FileMenu = new JMenu("File");
        NewItem = new JMenuItem("New");
        OpenItem = new JMenuItem("Open");
        SaveItem = new JMenuItem("Save");
        CopyItem = new JMenuItem("Copy");
        ExitItem = new JMenuItem("Exit");
        //Adding Menu Items in File Menu:
        FileMenu.add(NewItem);
        FileMenu.add(OpenItem);
        FileMenu.add(SaveItem);
        FileMenu.add(CopyItem);
        FileMenu.add(ExitItem);
        //Adding File Menu to Menu Bar:
        MenuBar.add(FileMenu);
        //Edit Menu :
        EditMenu = new JMenu("Edit");
        ClearMenuItem = new JMenuItem("Clear");
        FindMenuItem = new JMenuItem("Find and Replace");
        TimeDateItem = new JMenuItem("Time/Date");
        //Adding Menu Items in Edit Menu:
        EditMenu.add(ClearMenuItem);
        EditMenu.add(FindMenuItem);
        EditMenu.add(TimeDateItem);
        //Adding Edit Menu to Menu Bar:
        MenuBar.add(EditMenu);
        //Themes Menu :
        ThemesMenu = new JMenu("Themes");        
        WhiteMenu = new JMenuItem("White");
        DarkMenu = new JMenuItem("Dark");
        //Adding Menu Items in Themes Menu:
        ThemesMenu.add(WhiteMenu);
        ThemesMenu.add(DarkMenu);
        //Adding Themes Menu to Menu Bar:
        MenuBar.add(ThemesMenu);
        //About Menu :
        AboutMenu = new JMenu("About");
        AboutMenuItem = new JMenuItem("Info");
        //Adding Menu Items in About Menu:
        AboutMenu.add(AboutMenuItem);
        //Adding About Menu to Menu Bar:
        MenuBar.add(AboutMenu);
        
        textArea = new JTextArea(); //Creating text area
        enableDragAndDrop();    //Having Drag&Drop feature for txt files
        textArea.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16)); //Setting font and size 
        //  For text and word wrapping for the area
        textArea.setWrapStyleWord(true); 
        textArea.setLineWrap(true); 
        //Pop up menu for copy-paste-cut-select all:
        JPopupMenu popmenu = new JPopupMenu();
        
        //Initializing Actions of pop up menu:
        Action cutAction = textArea.getActionMap().get(DefaultEditorKit.cutAction);
        Action copyAction = textArea.getActionMap().get(DefaultEditorKit.copyAction);
        Action pasteAction = textArea.getActionMap().get(DefaultEditorKit.pasteAction);
        Action selectAllAction = textArea.getActionMap().get(DefaultEditorKit.selectAllAction);
        
        //Setting names of the actions:
        cutAction.putValue(Action.NAME, "Cut"); 
        copyAction.putValue(Action.NAME, "Copy");
        pasteAction.putValue(Action.NAME, "Paste");
        selectAllAction.putValue(Action.NAME, "Select All");
        
        //Adding to pop up menu:
        popmenu.add(cutAction);
        popmenu.add(copyAction);
        popmenu.add(pasteAction);
        popmenu.addSeparator();
        popmenu.add(selectAllAction);
        //Adding the pop up menu to the text area:
        textArea.setComponentPopupMenu( popmenu );
        
        scroll = new JScrollPane(textArea); //ScrollPanel for adding scrollbar
        
        bottompanel = new JPanel(); //Creating the bottop panel for the buttons and path text field
        bottompanel.setLayout(new FlowLayout(FlowLayout.LEFT)); //Setting layout FlowLayout.
        
        //Adding buttons and path text field to bottom panel:
        bottompanel.add(NewBtn);
        bottompanel.add(OpenBtn);
        bottompanel.add(SaveBtn);
        bottompanel.add(CopyBtn);
        bottompanel.add(ClearBtn);
        bottompanel.add(StatisticBtn);
        bottompanel.add(PathTF);
        
        //Setting up the frame:
        setTitle("Text Editor");  
        setSize(850, 725);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png"))); //Setting the icon
        setLayout(new BorderLayout()); //Setting Layout BorderLayout to put bottom panel on the bottom
        add(MenuBar, BorderLayout.NORTH);  //Setting Menu Bar on the top
        add(bottompanel, BorderLayout.SOUTH);  //Setting bottompanel on the bottom
        add(scroll, BorderLayout.CENTER);  //Setting ScrollPanel on the middle
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);   //For custom WindowListener when exiting
        
        //Button Listeners : 
        NewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                NewBtnActionPerformed(evt);
            }
        });
       
        
        OpenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                OpenBtnActionPerformed(evt);
            }
        });

        
        SaveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SaveBtnActionPerformed(evt);
            }
        });

        
        CopyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CopyBtnActionPerformed(evt);
            }
        });

        
        ClearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ClearBtnActionPerformed(evt);
            }
        });

        
        StatisticBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                StatisticBtnActionPerformed(evt);
            }
        });
       
        //Menu Item Listeners: 
        
        NewItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        NewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                NewItemActionPerformed(evt);
            }
        });

        
        OpenItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        OpenItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                OpenItemActionPerformed(evt);
            }
        });

        
        SaveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        SaveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SaveItemActionPerformed(evt);
            }
        });
        
        
        CopyItem.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_P, InputEvent.CTRL_MASK));
        CopyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CopyItemActionPerformed(evt);
            }
        });
        

        ExitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
        ExitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ExitItemActionPerformed(evt);
            }
        });
        
        
        ClearMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ClearMenuItemActionPerformed(evt);
            }
        });
        
        
        FindMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        FindMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                FindMenuItemActionPerformed(evt);
            }
        });
        
        
        TimeDateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                TimeDateItemActionPerformed(evt);
            }
        });
        
        
        WhiteMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        WhiteMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                WhiteMenuActionPerformed(evt);
            }
        });
       
        
        DarkMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        DarkMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                DarkMenuActionPerformed(evt);
            }
        });
        
        
        AboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        AboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AboutMenuItemActionPerformed(evt);
            }
        });
        
    }
    
    //Buttons Actions :
    //New action:
    private void NewBtnActionPerformed(ActionEvent evt) {
        NewFile();
    }
    //Open action:
    private void OpenBtnActionPerformed(ActionEvent evt) {
        OpenFile();    
    }
    //Save action:
    private void SaveBtnActionPerformed(ActionEvent evt) {
        SaveFile();
    }
    //Copy action:
    private void CopyBtnActionPerformed(ActionEvent evt) {
        CopyFile();
    }
    //Clear action:
    private void ClearBtnActionPerformed(ActionEvent evt) {        
        Clear();
    }
    //Exit action:
    private void ExitItemActionPerformed(ActionEvent evt) {
        Exit();
    }
    //Statistics action:
    private void StatisticBtnActionPerformed(ActionEvent evt) {
       StatisticsOfText stats = new StatisticsOfText(textArea);
    }
      
    //Menu Items Actions:
    //File action: 
    private void NewItemActionPerformed(ActionEvent evt) {
        NewFile();
    }
    //Open action:
    private void OpenItemActionPerformed(ActionEvent evt) {
        OpenFile();
    }
    //Save action:
    private void SaveItemActionPerformed(ActionEvent evt) {
        SaveFile();
    }
    //Copy action:
    private void CopyItemActionPerformed(ActionEvent evt) {
        CopyFile();
    }
    
    //Edit Actions: 
    //Clear action:
    private void ClearMenuItemActionPerformed(ActionEvent evt) {
        Clear();
    }
    //Find and Replace action:
    private void FindMenuItemActionPerformed(ActionEvent evt) {
        SearchReplace searchReplace = new SearchReplace(textArea);
    }
    //Time/Date Action:
    private void TimeDateItemActionPerformed(ActionEvent evt) {
        java.util.Date c = new java.util.Date();
        textArea.insert(c.toString(),textArea.getCaretPosition());
    }
    
    //Theme Actions:
    //White action:
    private void WhiteMenuActionPerformed(ActionEvent evt) {
        WhiteMode();
    }
    //Dark action: 
     private void DarkMenuActionPerformed(ActionEvent evt) {
        DarkMode();
    }
     
     //About action:
     private void AboutMenuItemActionPerformed(ActionEvent evt) {
        AboutProject aboutProject = new AboutProject();
     }
    
       
    //Methods :
    public void Exit() {
        int res = JOptionPane.showConfirmDialog(null, "Want to exit the app?", "Exit",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (res == JOptionPane.YES_OPTION)
        {
            data = textArea.getText();
            if(!CheckSave && !textArea.getText().equals(tmp)) 
                ExitSave();
            else
            {
                 dispose();
                 System.exit(0);
            }
        }
    }
    
    
     public void ExitSave() {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Want to save the file before exiting?", "Save Exit", JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION)
        {
            SaveFile(); //Save file method
            dispose();
            System.exit(0);
        }
        else
        {
            dispose();
            System.exit(0);
        }        
    }
     
     
    public void NewFile() {
        Clear();    //Clearing textArea
        File NewFile;
        NewFile = new File("Untitled.txt");
        while(NewFile.exists()) {   //If txt exists then ask for new file name:
            JOptionPane.showMessageDialog(textArea, "The file " + NewFile + " already exists." , "File Exists", JOptionPane.INFORMATION_MESSAGE);
            Object[] NFoptions = {"Create New File" , "Cancel"}; //New file options
            JPanel OptionPanel = new JPanel();
            OptionPanel.add(new JLabel("Enter new file name: "));
            JTextField NewFileNameTF = new JTextField(10);
            OptionPanel.add(NewFileNameTF);
            int result = JOptionPane.showOptionDialog(textArea, OptionPanel, "Enter new file name",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, NFoptions, null);
            if (result == JOptionPane.YES_OPTION){
                String NewName = NewFileNameTF.getText();
                NewFile = new File(NewName+".txt");
            }
            else {
                return;
            }
        }
        try {
            NewFile.createNewFile();
            String s = NewFile.getPath();
            this.setTitle(s);
            PathTF.setText(NewFile.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(textArea, "Can't access " + NewFile , "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void OpenFile() {
        if(!CheckSave && !textArea.getText().equals(tmp)) { //Save if textArea is edited and not saved.
            int dialogResult = JOptionPane.showConfirmDialog(null, "Want to save the file before opening a new file?", "Save existing file", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION)
                SaveFile();
        }
        JFileChooser fs = new JFileChooser();
        FileFilter filt = new FileNameExtensionFilter("Text File", "txt","text");  //Filter for .txt files
        fs.setDialogTitle("Open File"); 
        fs.setFileFilter(filt); //Setting the txt filter
        
        int result = fs.showOpenDialog(null);   
        if (result == JFileChooser.APPROVE_OPTION) {    //Accept save option
            File f = fs.getSelectedFile();
            String s = fs.getSelectedFile().getPath();
            
            ReadingFile(s, textArea);   //Reading from file and setting textArea
            
            setTitle(f.getName());  //Set the name of the file as title
            PathTF.setText(s.trim());  //Setting the path to the textfield
            tmp = textArea.getText().trim();    //tmp of textArea for checking before exit
        }
    }
    
    private void ReadingFile(String filename, JTextArea textArea){
        try {
                FileReader fr = new FileReader(filename);
                textArea.read(fr, null);
                fr.close();
            }
            catch (IOException e) {
               JOptionPane.showMessageDialog(this, "Can't read " + filename , "File Access Error\n" +
                e.getMessage(), JOptionPane.ERROR_MESSAGE); 
            }
    }
    
    
    public void SaveFile() {
        File SavedFile; //File to save 
        String content; //Content of text area
        if(!PathTF.getText().isEmpty()) {
            Object[] options = {"Save", "Save As", "Cancel"};   //Custom options for JOption Panel
            int result = JOptionPane.showOptionDialog(null, "Do you want to save file in current path ?", "Save Option",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);    
            switch (result) {
                case JOptionPane.YES_OPTION:
                    SavedFile = new File(PathTF.getText());
                    content = textArea.getText();
                    BufferedWriter outFile;
                    try {
                        outFile = new BufferedWriter( new FileWriter( SavedFile ) );
                        try { 
                            outFile.write(content);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        outFile.close();
                        
                        JOptionPane.showMessageDialog(null, "Success.\nFile Saved!"); //Success message
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(textArea, "Can't save at " + PathTF.getText() , "Error", JOptionPane.ERROR_MESSAGE);
                    }   break;
                
                case JOptionPane.CANCEL_OPTION:
                    return;
                
                default:
                    SavingTheFile();
                    break;
            }
                CheckSave = true;   //Check if file is saved for exit
                tmp = textArea.getText().trim();   //tmp of textArea for checking before exit
        } else {
            SavingTheFile();
        }
    }
    

    private void SavingTheFile() {
        String content;
        File SavedFile;
        FileFilter filt = new FileNameExtensionFilter("Text File", "txt");  //Filter for .txt files
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filt); //Setting the txt filter
        fc.setDialogTitle("Save File");
        int result = fc.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {    //Accept save option
            content = textArea.getText();    //Text to save
            SavedFile = new File(fc.getSelectedFile().getAbsolutePath());
            if(SavedFile != null) {
                if(SavedFile.exists()) {
                    int response = JOptionPane.showConfirmDialog(null, SavedFile + " already exists.\nDo you want to replace it?",
                            "Confirm Save", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        BufferedWriter outFile;
                        try {
                            outFile = new BufferedWriter( new FileWriter( SavedFile ) );
                            try {
                                outFile.write(content); 
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Can't write the file", "File Writing Error\n" +
                                    ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                            }
                            outFile.close();
                            setTitle(SavedFile.getName()); //Set the name of the file as title
                            PathTF.setText(SavedFile.getAbsolutePath());   //Setting the path to the textfield
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Can't access file", "File Access Error\n" +
                            ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Abort.\nFile not saved!", "Saving Canceled", JOptionPane.PLAIN_MESSAGE); //Abort message
                        CheckSave = false;  //File not saved
                    }
                }
                else {
                    try {
                        try (FileWriter fw = new FileWriter(SavedFile.getPath())) {
                            fw.write(content);
                            fw.flush();
                        }  
                        String s = SavedFile.getPath(); //Path of saved file
                        setTitle(SavedFile.getName()); //Set the name of the file as title
                        PathTF.setText(s.trim());   //Setting the path to the textfield
                        JOptionPane.showMessageDialog(null, "Success.\nFile Saved!"); //Success message
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Can't access " + SavedFile.getPath() , "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    CheckSave = true;   //Check if file is saved for exit
                }
                tmp = textArea.getText().trim();   //tmp of textArea for checking before exit
            }  
        }
        else {
            JOptionPane.showMessageDialog(null, "Abort.\nFile not saved!", "Saving Canceled", JOptionPane.PLAIN_MESSAGE); //Abort message
            CheckSave = false;  //File not saved
        }
    }
    
    
    private void CopyFile() {
        String text = textArea.getText();   //Text to append
        if ( !text.isEmpty() ) {
            FileFilter filt = new FileNameExtensionFilter("Text File", "txt");  //Filter for .txt files
            JFileChooser fs = new JFileChooser();   
            fs.setDialogTitle("Copy File");
            fs.setFileFilter(filt); //Setting the txt filter
            int result = fs.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {    //Accept copy option
                File f = fs.getSelectedFile();  
                if(!f.exists()) {
                        JOptionPane.showMessageDialog(null, "Abort.\nUser's file does not exist!", "Copy Canceled", JOptionPane.ERROR_MESSAGE);
                        return;
                }
                String filepath = f.getPath();
                String s = fs.getSelectedFile().getPath();  //Path of file for path textfield 
                try {
                    FileWriter fr = new FileWriter(s, true);
                    try (BufferedWriter file = new BufferedWriter(fr)) {
                        file.newLine();
                        file.write(text);
                        file.close();
                    }
                    //Success Message:
                    JOptionPane.showConfirmDialog(null, "Record copied to " + filepath, 
                           "Copy Completed", JOptionPane.PLAIN_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Can't access "+ s, "Error", JOptionPane.ERROR_MESSAGE);
                } 
                //Question for the user if he wants to open the appended file
                int response = JOptionPane.showConfirmDialog(null, "Want to open the appended file?", 
                        "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {   //Accept the open option
                    Clear();
                    ReadingFile(filepath, textArea);
                    setTitle(f.getName()); //Set the name of the file as title
                    PathTF.setText(s.trim());   //Setting the path to the textfield
                } else 
                    return;
            } else {
                    JOptionPane.showConfirmDialog(null, "Copy calceled", "Cancel Message", JOptionPane.PLAIN_MESSAGE);
            }
            tmp = textArea.getText().trim();    //tmp of textArea for checking before exit   
        }
        else {
            JOptionPane.showMessageDialog(null, "There is nothing to copy", "Copy text", JOptionPane.WARNING_MESSAGE);
        }
    }
    

    public void Clear() {
        textArea.getText(); //For setText error
        textArea.setText("");
    }
         
    
    private void WhiteMode() {
        MenuBar.setBackground(null);
        MenuBar.setForeground(null);
        FileMenu.setForeground(null);
        EditMenu.setForeground(null);
        ThemesMenu.setForeground(null);
        AboutMenu.setForeground(null);
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.DARK_GRAY);
        textArea.setCaretColor(null);
        NewBtn.setBackground(null);
        NewBtn.setForeground(null);
        OpenBtn.setBackground(null);
        OpenBtn.setForeground(null);
        SaveBtn.setBackground(null);
        SaveBtn.setForeground(null);
        ClearBtn.setBackground(null);
        ClearBtn.setForeground(null);
        CopyBtn.setBackground(null);
        CopyBtn.setForeground(null);
        StatisticBtn.setBackground(null);
        StatisticBtn.setForeground(null);
        PathTF.setBackground(null);
        PathTF.setForeground(Color.DARK_GRAY);
        bottompanel.setBackground(null);
    }
     
    private void DarkMode() {
        MenuBar.setBackground(Color.DARK_GRAY);
        MenuBar.setBorderPainted(false);
        bottompanel.setBackground(Color.DARK_GRAY);
        FileMenu.setForeground(Color.WHITE);
        EditMenu.setForeground(Color.WHITE);
        ThemesMenu.setForeground(Color.WHITE);
        AboutMenu.setForeground(Color.WHITE);
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.WHITE);
        NewBtn.setBackground(Color.DARK_GRAY);
        NewBtn.setForeground(Color.WHITE);
        OpenBtn.setBackground(Color.DARK_GRAY);
        OpenBtn.setForeground(Color.WHITE);
        SaveBtn.setBackground(Color.DARK_GRAY);
        SaveBtn.setForeground(Color.WHITE);
        ClearBtn.setBackground(Color.DARK_GRAY);
        ClearBtn.setForeground(Color.WHITE);
        CopyBtn.setBackground(Color.DARK_GRAY);
        CopyBtn.setForeground(Color.WHITE);
        StatisticBtn.setBackground(Color.DARK_GRAY);
        StatisticBtn.setForeground(Color.WHITE);
        PathTF.setBackground(Color.DARK_GRAY);
        PathTF.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
    }
     private void enableDragAndDrop() {
        DropTarget target = new DropTarget(textArea, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {
            }
            @Override
            public void dragOver(DropTargetDragEvent dtde) {
            }
            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {
            }
            @Override
            public void dragExit(DropTargetEvent dte) {
            }          
            @Override
            public void drop(DropTargetDropEvent dtde) {
                try
                {
                    // Accept the drop first
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    
                    // Get the files that are dropped as java.util.List
                    java.util.List list=(java.util.List) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    
                    // Now get the first file from the list,
                    File file=(File)list.get(0);
                    textArea.read(new FileReader(file),null);
                    setTitle(file.getName()); //Set the name of the file as title
                    PathTF.setText(file.getAbsolutePath());   //Setting the path to the textfield
                    tmp = textArea.getText().trim();    //tmp of textArea for checking before exit  
                }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Cannot Open File", "Error", JOptionPane.ERROR);}     
            }
         });
    }
}