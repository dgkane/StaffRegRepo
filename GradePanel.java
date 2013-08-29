import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Displays a series of radio buttons for choosing which pay grade the registered member of staff will have.
 * The number of displayed radio buttons depends on how many pay grades are associated with the department selected
 * on the department chooser panel.
 * 
 * @author David Kane
 * @version 0.1
 */
public class GradePanel extends JPanel implements ActionListener
{
    private ChooserListener callback;
    
    private Grade[] grades;
    private Department dept;
    
    private JRadioButton[] radioButtons;
    private ButtonGroup payGradeButtonGroup;
    
    private String deptCode;
    private String selectedGrade;

    
    /**
     * Constructor for objects of class GradePanel
     * 
     * @param c : The ChooserListener of the RegHelper class that creates this window
     * @param s : String code of the currently selected Department, passed from RegHelper
     * 
     */
    public GradePanel(ChooserListener c, String s)
    {
        super();
        
        callback = c;
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        
        c1.fill = GridBagConstraints.HORIZONTAL;
        
        // Gets the relevant department object from the supplied string Code
        dept = Department.get(s);
        
        // Gets the array of possible grades for that department.
        grades = dept.getGrades();
        
        this.add(new JLabel("Grade: "), c1);
        
        // Sets the folloiwing 'for' loop to iterate only as many times as there are grades in the selected
        // department.
        int numGrades = grades.length;
        
        radioButtons = new JRadioButton[numGrades];
        
        // Creates a group for the buttons created by the following 'for' loop.
        // This will prevent multiple buttons from being selected at once.
        payGradeButtonGroup = new ButtonGroup();
        
        // For each possible pay grade of the selected department, create a radio button labelled with the
        // relevant grade, add an ActionListener and ActionCommand to the button, and add it to the button group
        // and panel
        for (int i = 0; i < numGrades; i++)
        {
           String buttonNo = "" + (i+1);
           JRadioButton payGradeButton = new JRadioButton(buttonNo);
           radioButtons[i] = payGradeButton;
           payGradeButton.addActionListener(this);
           payGradeButton.setActionCommand(buttonNo);
           payGradeButtonGroup.add(payGradeButton);
           this.add(payGradeButton, c1);
        }
        
        // Sets the first RadioButton as the initially selected button, and tells RegHelper.
        selectedGrade = "" + 1;
        radioButtons[0].setSelected(true);
        callback.tell("1", 3);
        
        
    }

    
    /**
     * When a grade radio button is selected, passes a String code to the RegHelper class.
     * 
     * This method is required by ActionListener.
     * 
     * @param  e : list selection event
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        selectedGrade = e.getActionCommand();
        callback.tell(selectedGrade, 3);
        
        
    }
}
