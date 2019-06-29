package texteditor;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author cs171059
 * email : cs171059@uniwa.gr
 */
public class SearchReplace extends JFrame implements ActionListener {
    
    private JFrame SearchFrame; 
    private JPanel contentPane;
    private JLabel FindLB, ReplaceLB;
    private JTextArea textArea;
    private boolean case_check;
    private JTextField FindTF, ReplaceTF;

    public SearchReplace(JTextArea ta) {
        SearchFrame = new JFrame();
        SearchFrame.setResizable(false);
	SearchFrame.setTitle("Search and Replace");
        
        textArea = ta;
        SearchFrame.setVisible(true);
        SearchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SearchFrame.setBounds(100, 100, 425, 156);
        SearchFrame.setLocationRelativeTo(null);
        contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	SearchFrame.setContentPane(contentPane);
	contentPane.setLayout(null);
        
       
        FindLB = new JLabel("Search :");
        FindLB.setBounds(8, 13, 60, 33);
        contentPane.add(FindLB);
        
        ReplaceLB = new JLabel("Replace with :");
        ReplaceLB.setBounds(8, 25, 80, 80);
        contentPane.add(ReplaceLB);
        
        FindTF = new JTextField();
        FindTF.setBounds(105, 18, 150, 25);
	contentPane.add(FindTF);
	FindTF.setColumns(10);
        
        ReplaceTF = new JTextField();
        ReplaceTF.setBounds(105, 54, 150, 25);
        contentPane.add(ReplaceTF);
       
        JButton Find = new JButton("Replace");
        
        Find.setBounds(300, 18, 89, 23);
	contentPane.add(Find);
      
        JButton Close = new JButton("Close");
        Close.setBounds(300, 54, 89, 23);
	contentPane.add(Close);
        
        Close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              CloseBtnActionPerformed(evt);
            }
        });
        
        JCheckBox Case_CheckBox = new JCheckBox("Match Case");
	Case_CheckBox.setBounds(105, 90, 97, 23);
	contentPane.add(Case_CheckBox);
        Case_CheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)  //checkbox has been selected
                    case_check = true;
                else //checkbox has been deselected
                    case_check = false;
            }
        });
            
        Find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              ReplaceBtnActionPerformed(evt);
            }
        });
        
    }
    
    //Button Actions:
    private void CloseBtnActionPerformed(ActionEvent evt) {
        contentPane.setVisible(false);
        SearchFrame.dispose();
    }
    
    private void ReplaceBtnActionPerformed(ActionEvent evt) {
        Replace_Word(FindTF.getText(), ReplaceTF.getText());
    }
    
   
    //Method to replace words:
    public void Replace_Word(String find, String replace) {      
        if (textArea.getText().length()==0) {
            JOptionPane.showMessageDialog(null,"There is nothing to replace");
            return;
        }
        int start = 0;
        boolean isFoundCase = textArea.getText().contains(find)? true: false;
        boolean isFoundNoCase = textArea.getText().toLowerCase().contains(find.toLowerCase())? true: false;
        
        if (case_check){
            start = textArea.getText().indexOf(find);
                if(!isFoundCase) {
                    JOptionPane.showMessageDialog(null, find + " not Found!", "Error", JOptionPane.ERROR_MESSAGE );
                    return;
                }
        }
        else
        {
            if(!isFoundNoCase){
                JOptionPane.showMessageDialog(null, find + " not Found!", "Error", JOptionPane.ERROR_MESSAGE );
                    return;
            }
            start = textArea.getText().toLowerCase().indexOf(find.toLowerCase());
            find = find.toLowerCase();
        }
               
        int rtn = JOptionPane.showConfirmDialog(null, "Found: " + find + "\nReplace with: " + replace, "Word Found", JOptionPane.YES_NO_OPTION);
        if (rtn == JOptionPane.YES_OPTION ) {
            if (start >= 0 && find.length() > 0)
                textArea.replaceRange(replace, start, start + find.length());
                if (case_check)
                    start = textArea.getText().indexOf(find);
                else
                    start = textArea.getText().toLowerCase().indexOf(find.toLowerCase());           
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
