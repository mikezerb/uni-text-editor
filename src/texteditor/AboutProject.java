package texteditor;


import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

/**
 * @author cs171059
 * email : cs171059@uniwa.gr
 */
public class AboutProject extends JFrame {
    
    // Variables declaration       
    private JFrame AboutFrame;
    private JTextPane InfoPanel;
    private JPanel ImagePanel;
    private JLabel LogoLbl;
    
    // End of variables declaration   
 
public AboutProject() {
    
    FrameInit();    //Creating the frame
    
    //Initializing Components:
    InfoPanel = new JTextPane();
    ImagePanel = new JPanel();
    LogoLbl = new JLabel();
        
    //Setting the Info panel:
    InfoPanel.setEditable(false);
    InfoPanel.setBorder(BorderFactory.createTitledBorder("Project Info"));
    InfoPanel.setText("\nΤελική Εργασία για το τμήμα ΜΑΕ03 (Τρίτη 9-11) : \n\n ΑΜ: 171059\n Ονοματεπώνυμο: Μιχαήλ Αποστολίδης\n Εξάμηνο: 4ο\n Email: cs171059@uniwa.gr\n\n Ημερομηνία Παράδοσης: 24/6/2019\n\n Άσκηση ανάπτυξης εφαρμογής Text Editor.");
    
    //Setting the Uniwa Logo:
    LogoLbl.setIcon(new ImageIcon(getClass().getResource("logo-pada.png")));        
    //Adding the components:
    ImagePanel.add(LogoLbl);
    AboutFrame.add(ImagePanel);
    AboutFrame.add(InfoPanel);
 
 }

    private void FrameInit() {
        AboutFrame = new JFrame();  
        AboutFrame.setTitle("About the Project");
        AboutFrame.setSize(600,270);
        
        AboutFrame.setLocationRelativeTo(null);
        AboutFrame.setResizable(false);
        AboutFrame.setAlwaysOnTop(true);
        AboutFrame.setVisible(true);
        
        AboutFrame.setLayout(new GridLayout());
    }
}