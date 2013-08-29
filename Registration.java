import javax.swing.*;


/**
 * Creates a new RegHelper window for entering a new member of staff's details.
 * Registers a member of staff with the entered details when the user presses the 'Enter' button on the ButtonPanel
 * (created by RegHelper).
 * 
 * @author David Kane
 * @version 0.1
 */
public class Registration implements ChooserListener
{
    private RegHelper gUIWindow;

    private String staffId;
    private String staffName;
    private String deptCode;
    private String staffGrade;
    
    private Staff target;

    
    /**
     * Constructor for objects of class Registration
     * 
     */
    public Registration()
    {
        // Creates the GUI window (RegHelper) for selection of staff details.
        gUIWindow = new RegHelper(this, this);
        
    }
    
    
    /**
     * Checks if the given Staff ID is currently in use (Staff object already exists with that ID), and if not,
     * creates a new staff object when called from RegHelper using the currently selected details.
     * Also print a String descriptor of the newly registered staff member to the terminal.
     * 
     * If the Staff ID is already in use, displays a dialog box prompting user to change Staff ID.
     * 
     */
    public void register()
    {
        target = Staff.getStaff(staffId);
        
        if (target == null)
        {
            // Gets the pay grade from the selected Department itself, using the String reference passed from the
            // GradePanel.
            // This way I could use the ChooserListener's tell(String) method, rather than creating another
            // tell(Grade) method to pass the whole Grade object up from the GradePanel.
            Staff.add(staffId, staffName, Department.get(deptCode, staffGrade));
            target = Staff.getStaff(staffId);
            System.out.println("New School object created : " + target.toString());
        
        }
        else
        {
           JOptionPane.showMessageDialog(null, "Staff ID already in use!", "Error",
           JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Overrides tell method in ChooserListener.
     * Sets class attributes (staffId, staffName, deptCode, staffGrade) according to the selections made in
     * RegHelper.
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
        if (i == 11)
        {
        staffId = s;
        }
        if (i == 12)
        {
        staffName = s;
        }
        if (i == 13)
        {
        deptCode = s;
        }
        else if (i == 14)
        {
        staffGrade = s;
        }
        
        
    }
}
