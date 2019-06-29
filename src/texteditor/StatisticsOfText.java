package texteditor;


import javax.swing.JTextArea;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @author cs171059
 * email : cs171059@uniwa.gr
 */
public class StatisticsOfText extends JFrame {

 private JPanel contentPane;
 private JLabel ParagraphLB, WordsLB, CharLB;

 public StatisticsOfText(JTextArea _textArea) {
  setResizable(false);
  setTitle("Statistics");
  setVisible(true);

  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  setBounds(100, 100, 310, 100);
  contentPane = new JPanel();

  setContentPane(contentPane);
  contentPane.setLayout(null);
  this.setLocationRelativeTo(null);
  ParagraphLB = new JLabel();
  ParagraphLB.setBounds(10, 10, 200, 25);
  contentPane.add(ParagraphLB);
  WordsLB = new JLabel();
  WordsLB.setBounds(10, 25, 200, 25);
  contentPane.add(WordsLB);
  CharLB = new JLabel();
  CharLB.setBounds(10, 40, 300, 25);
  contentPane.add(CharLB);
  String text = _textArea.getText();
  /*
  For Paragraph : 
  paragraph1.
  
  paragraph2.
  
  int c = 1;
  boolean newline = false;

     try (Scanner scanner = new Scanner(text)) {
         while (scanner.hasNextLine()) {
             String line = scanner.nextLine();
             if(line.equals("")) {
                 if(!newline) {
                     c++;
                     newline = true;
                 }
             }
             else
                 newline=false;
         }
     if(newline)
        c--;
     }
   */
  
  StringTokenizer paragTk = new StringTokenizer(text,"\n"); //Delimiter new line
  StringTokenizer wordTk = new StringTokenizer(text);   //Delimiter white space
  if (!text.equals("")) {   //Text area not empty
   ParagraphLB.setText("There are " + paragTk.countTokens() + " paragraphs.");   
   //ParagraphLB.setText("There are " + c + " paragraphs.");  
   WordsLB.setText("There are " + wordTk.countTokens() + " words.");
   CharLB.setText("There are " + text.length() + " characters (including spaces).");
  } else
   ParagraphLB.setText("There are no characters.");

 }

}