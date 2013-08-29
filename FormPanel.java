import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;


/**
 * Creates a JPanel with textFields for entering a new member of staff's ID and name.
 * Contains a subclass MyVerifier, which does not allow focus on the textFields to be yielded while
 * its contents are inappropriate.
 * 
 * @author David Kane
 * @version 0.1
 */
public class FormPanel extends JPanel
{
    private ChooserListener callback;

    private String[] labels = {"StaffID: ", "Name: ", "DeptID: "};
    private int numRows = labels.length;
    private JTextField[] textFields = new JTextField[labels.length];
    
    private String validStaffId;
    private String validStaffName;
    
    // private String[] format = {"########", "*", "UUUUU"};
    // private int[] numChars = {8, 20, 6};

    /**
     * Constructor for objects of class FormPanel
     * 
     * @param c : The ChooserListener of the RegHelper class that creates this window
     */
    public FormPanel(ChooserListener c)
    {
       super();
       
       callback = c;
       
       // Sets the layout of the panel to GridBagLayout and sets some constraints.
       this.setLayout(new GridBagLayout());
       GridBagConstraints c1 = new GridBagConstraints();
       GridBagConstraints c2 = new GridBagConstraints();
       GridBagConstraints c3 = new GridBagConstraints();
       
       // c1.fill = GridBagConstraints.CENTER;
       c1.ipady = 60;
       c2.anchor = GridBagConstraints.LINE_START;
       c2.ipady = 10;
       c2.gridy = 1;
       // c3.fill = GridBagConstraints.HORIZONTAL;
       c3.ipady = 20;
       c3.gridy = 1;
       c3.gridx = 1;
       
       // Adds a 'Staff Registration' JLabel to give the GUI a title.
       this.add(new JLabel("Staff Registration"), c1);
       
       // Adds a JLabel and textField for every element in the array of labels.
       // The components are added to constraints c2 and c3 respectively.
       // c2 occupies the left column in the panel (gridx = 0).
       // c3 occupies the right column (grid x = 1).
       // After adding the component to one of these constraints, its gridy value increases by one.
       // This means the next component in the connstraint will appear on the next line down from the
       // last one.
       // Each textField is added to an array, so they can be identified later
       for (int i = 0; i < numRows; i++)
       {
           JLabel label = new JLabel(labels[i]);
           this.add(label, c2);
           c2.gridy++;
           JTextField textField = new JTextField(10);
           textField.setColumns(10);
           label.setLabelFor(textField);
           textFields[i] = textField;
           this.add(textField, c3);
           c3.gridy++;
       }
       
       // Adds verifiers to the textFields to check input.
       textFields[0].setInputVerifier(new MyVerifier());
       textFields[1].setInputVerifier(new MyVerifier());
       // The department TextField is not editable; it will be displayed automatically when a
       // department is selected.
       textFields[2].setEditable(false);
       
         
    }
    
    
    /**
     * Stores the contents of the StaffID field and sends it to the callback class (RegHelper).
     * This method is called from the MyVerifier subclass when the relevant textField has been
     * verified.
     * 
     */
    public void setStaffID()
    {
        validStaffId = textFields[0].getText();
        callback.tell(validStaffId, 4);
        
        
    }
    
    
    /**
     * Stores the contents of the Name field and sends it to the callback class (RegHelper).
     * This method is called from the MyVerifier subclass when the relevant textField has been
     * verified.
     * 
     */
    public void setName()
    {
        validStaffName = textFields[1].getText();
        callback.tell(validStaffName, 5);
        
        
    }
    
    
    public void setDept(String s)
    {
        textFields[2].setText(s);
    }
    // public void clear()
    // {
    //     textFields[0].setText("");
    //     textFields[1].setText("");
    // }
    
    
    /**
     * When set as a verifier on a JComponent, this class will refuse to yield focus on the component
     * while its input is inverified according to a regex on the input String.
     * 
     * @author David Kane
     * @version 0.1
     */
    class MyVerifier extends InputVerifier {
      
       /**
       * Checks whether a JComponent's input is valid, in this case a textField (return true)
       * or if it is not (return false). The input window will not yield focus while false.
       * 
       * @param input : the component to be validated.
       * return : true if String passes validation check.
       */ 
      public boolean shouldYieldFocus(JComponent input)
      {
          boolean validInput = verify(input);
          
          if (validInput)
          {
              return true;
          }
          else
          {
              Toolkit.getDefaultToolkit().beep();
              return false;
              
              
          }
      }
          
      
      
       /**
       * If the named textField passes verification according to the regex, the relevant method
       * for setting the associated attribute is called.
       * 
       * @param input : the component to be validated.
       * 
       */ 
      public boolean verify(JComponent input)
      {
          if (input==textFields[0])
          {
            setStaffID(); 
            return validId();
          }
          if (input==textFields[1])
          {
            setName();
            return validName();
          }
          else {return true;}
          
          
      }
          
      
      /**
       * Checks that the StaffID textField is an 8-digit String.
       * If not, an error message is displayed and the compnent will consequently refuse to yield
       * focus.
       * 
       */ 
      protected boolean validId()
      {
         // Assumes the boolean is invalid
         boolean isValid = false;
         // Matches pattern against input
         Pattern p = Pattern.compile("[0-9]{8}");
         Matcher m = p.matcher(textFields[0].getText());
         // Returns true if there is a match
         if (m.matches())
         {
            isValid = true;
            
         }
         else
         {
         // Shows an error dialog and doesn't change return from false
         JOptionPane.showMessageDialog(null, "Staff ID must be an 8-digit number", "Error",
         JOptionPane.ERROR_MESSAGE);
         }
         
         return isValid;
         
         
      }
      
      
      /**
       * Checks that the Name textField String only contains letters or whitespace, and is not
       * null
       * 
       */ 
      protected boolean validName()
      {
         boolean isValid = false;
         Pattern q = Pattern.compile("[a-zA-Z ]+");
         Matcher n = q.matcher(textFields[1].getText());
         if (n.matches())
         {
         isValid = true;    
         }
         else {
           
         JOptionPane.showMessageDialog(null, "Staff name must only contain letters or whitespace,"
         + " and cannot be null", "Error", JOptionPane.ERROR_MESSAGE);    
                 
         }
         return isValid;
         
         
      }
    }
    
    public void clear()
    {
        textFields[0].setText("");
        textFields[1].setText("");
    }
    // protected MaskFormatter createFormatter(String s) {
    //     MaskFormatter formatter = null;
    //     try
    //     {
    //     formatter = new MaskFormatter(s);
    //     } catch (java.text.ParseException exc) {
    //     System.err.println("formatter is bad: " + exc.getMessage());
    //     System.exit(-1);
    // }
    // return formatter;
    
        
     
}
    
    
    

