import javax.swing.*;
import java.awt.*;


/**
 * Creates a window initially displaying a FormPanel for entering a new member of staff's ID and name,
 * a ButtonPanel for clearing FormPanel's textFields and registering staff with the selected details,
 * and a SchoolChooser panel for choosing which school the staff will belong to.
 * 
 * Creates a DepartmentChooser panel when a school is selected on the SchoolChooser with that school's
 * relevant departments.
 * 
 * Creates a GradePanel when a department is selected on the DepartmentChooser with that department's
 * relevant pay grades.
 * 
 * The class has the currently selected details as attributes to pass to the Registration object when
 * the user chooses.
 * 
 * @author David Kane 
 * @version 0.1
 */
public class RegHelper extends JFrame implements ChooserListener
{
    private ChooserListener callback;
    private Registration reg;
    
    private GridBagConstraints c1;
    private GridBagConstraints c2;
    private GridBagConstraints c3;
    private GridBagConstraints c4;
    private GridBagConstraints c5;
    
    private FormPanel inputForm;
    private JPanel buttonPanel;
    private JPanel schoolChooser;
    private JPanel deptChooser;
    private JPanel gradePanel;
    
    private String selectedSchool;
    private String selectedDept;
    private String selectedGrade;
    private String staffID;
    private String staffName;
    
    
    /**
     * Constructor for objects of class RegHelper
     * 
     * @param c : The ChooserListener of the registration class that creates this window
     * @param r : The Registration class that creates this window
     */
    public RegHelper(ChooserListener c, Registration r)
    {
         // Passes the class a reference to the Registration object that created it's ChooserListener.
        // When the tell() method is invoked on the ChooserListener, it allows Registration access
        // to this class's attributes.
        callback = c;
        
        // Passes the class a reference to the Registration obejct that created it.
        // This is so we can invoke the register() method on the Registration object.
        // Perhaps creating another interface would be a more elegant method.
        reg = r;
        
        // Sets the window's component layout.
        // We will create a new component for every element of the window's interface.
        this.setLayout(new GridBagLayout());
        c1 = new GridBagConstraints();
        c2 = new GridBagConstraints();
        c3 = new GridBagConstraints();
        c4 = new GridBagConstraints();
        c5 = new GridBagConstraints();
        
        c1.anchor = GridBagConstraints.PAGE_START;
        c1.gridx = 0;
        c1.gridy = 0;
        c1.ipadx = 5;
        c1.ipady = 5;
        c1.fill = GridBagConstraints.HORIZONTAL;
        c2.anchor = GridBagConstraints.PAGE_START;
        c2.gridx = 0;
        c2.gridy = 2;
        c2.ipadx = 5;
        c2.ipady = 5;
        c2.fill = GridBagConstraints.HORIZONTAL;
        c3.anchor = GridBagConstraints.PAGE_END;
        c3.gridx = 0;
        c3.gridy = 3;
        c3.ipadx = 5;
        c3.ipady = 5;
        c3.fill = GridBagConstraints.HORIZONTAL;;
        c4.anchor = GridBagConstraints.PAGE_START;
        c4.gridx = 3;
        c4.gridy = 0;
        c4.ipadx = 5;
        c4.ipady = 5;
        c4.fill = GridBagConstraints.BOTH;
        // c4.gridheight = 4;
        c5.anchor = GridBagConstraints.PAGE_START;
        c5.gridx = 3;
        c5.gridy = 2;
        c5.ipadx = 5;
        c5.ipady = 5;
        c5.fill = GridBagConstraints.BOTH;
        // c5.gridheight = 4;
        
        // Populates a collection of school objects, and a collection of department objects
        // within each school object.
        Loader.load();
        
        // Creates and adds FormPanel, SchoolChooser and ButtonPanel JPanel in the associated
        // constraints within the window.
        inputForm = new FormPanel(this);
        buttonPanel = new ButtonPanel(this, inputForm);
        schoolChooser = new SchoolChooser(this);
        this.add(inputForm, c1);
        this.add(buttonPanel, c3);
        this.add(schoolChooser, c4);
        
        // Sets the initial size of the window and makes it visible
        this.setSize(580, 460);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
       
        
    }

    
    /**
     * Overrides tell method in ChooserListener.
     * Sets class attributes (selectedSchool, selectedDept, selectedGrade, staffID, staffName)
     * according to the selections made in their respective panels.
     * 
     * If a school is selected, a DepartmentChooser panel is added.
     * If a department is selected, a GradePanel is added.
     *
     * This method is required by ChooserListener.
     * 
     * @param s : the String the attribute is to be set to.
     * @param i : the integer code for the attribute to be set.
     * 
     */
    @Override
    public void tell(String s, int i)
    {
        if (i == 1)
        {
        selectedSchool = s;
        addDepartmentChooser();
        // If user selects a different school, nullifies stored department and grade to avoid
        // user registering a member of staff with the wrong department/grade for the school.
        selectedDept = null;
        selectedGrade = null;
        if (gradePanel != null)
        {
            this.remove(gradePanel);
        }
        }
        if (i == 2)
        {
        selectedDept = s;
        addGradePanel();
        }
        if (i == 3)
        {
        selectedGrade = s;
        }
        if (i == 4)
        {
        staffID = s;
        }
        else if (i == 5)
        {
        staffName = s;
        }
        
        
    }
    
    
    /**
     * Adds a DepartmentChooser panel to the container, removing any existing ones.
     * Also widens the application window to allow all components to be comfortably displayed.
     * 
     */
    public void addDepartmentChooser()
    {
        // Hides RegHelper window while its contents are modified.
        // To avoid graphical glitches.
        this.setVisible(false);
        
        // Removes the current DepartmentChooser panel if one already exists
        if (deptChooser != null)
        {
            this.remove(deptChooser);
        }
        
        deptChooser = new DepartmentChooser(this, selectedSchool, inputForm);
        
        // Adds the DepartmentChooser panel to the right of the SchoolChooser.
        this.add(deptChooser, c5);
        
        // Makes the window wider so all components can be comfortably displayed.
        // this.setSize(720, 280);
        // Makes the window visible again.
        this.setVisible(true);
        
        
    }
    
    
    /**
     * Adds a grade chooser panel to the container, removing any existing ones.
     * 
     */
    public void addGradePanel()
    {
        this.setVisible(false);
        
        if (gradePanel != null)
        {
            this.remove(gradePanel);
        }
        
        gradePanel = new GradePanel(this, selectedDept);
        
        // Adds the new GradePanel just below the input form.
        this.add(gradePanel, c2);
        
        // Makes the window visible again.
        this.setVisible(true);
        
        
    }
    
    
    /**
     * Invoked when the 'enter' button is clicked on the ButtonPanel.
     * If all the required attributes required to create a new Staff object have a value,
     * Passes the attributes to the Registration objectvia its ChooserListener, and invokes that
     * Registration object's register() method to create the Staff object.
     * 
     * If any fields are null, a dialog box will be displayed prompting the user to complete
     * all fields.
     */
    public void passToRegistration()
    {
        if (staffID !=null && staffName!=null && selectedDept!=null && selectedGrade!=null)
        {
        
            callback.tell(staffID, 11);
            callback.tell(staffName, 12);
            callback.tell(selectedDept, 13);
            callback.tell(selectedGrade, 14);
            reg.register();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "All fields not completed", "Error",
            JOptionPane.ERROR_MESSAGE);
        }
         
    }
    
    public void clearFields()
    {
    }
      
}
