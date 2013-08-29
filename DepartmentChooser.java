import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;


/**
 * Displays a scroll list of the available departments in a given school and passes the String code
 * of the selected department to the callback class 
 * (RegHelper).
 * 
 * 
 * @author David Kane
 * @version 0.1
 */
public class DepartmentChooser extends JPanel implements ListSelectionListener
{
    private ChooserListener callback;
    private FormPanel callForm;
    
    private GridBagConstraints c1;
    private GridBagConstraints c2;
    
    private Collection<Department> deptColl;
    
    private Department[] depts;
    
    private School school;
    private String[] deptNames;
    private String selectedDeptCode;
    
    private JList deptList;
    
    
    /**
     * Constructor for objects of class DepartmentChooser
     * 
     * @param c : The ChooserListener of the RegHelper class that creates this window
     * @param s : String code of the currently selected School, passed from RegHelper
     * 
     */
    public DepartmentChooser(ChooserListener c, String s, FormPanel f)
    {
        super();
        
        callback = c;
        callForm = f;
        
        this.setLayout(new GridBagLayout());
        c1 = new GridBagConstraints();
        c2 = new GridBagConstraints();
       
        c1.gridx = 0;
        c1.gridy = 0;
        c2.gridx = 0;
        c2.gridy = 1;
        
        // Gets the relevant school object from the String code
        school = School.get(s);
        
        // Gets the collection of departments from that school
        deptColl = school.getDepartments();
        
        
        // As in SchoolChooser
        int numDepts = deptColl.size();
        if (deptColl.size() != 0)
        {
        this.add(new JLabel("Department: "), c1);
        depts = new Department[numDepts];
        deptNames = new String[numDepts];
        
        int i=0;
        for(Department dept : deptColl)
        {
            depts[i] = dept;
            deptNames[i] = dept.getName();
            i++;
        }
        
        deptList = new JList(deptNames);
        
        deptList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        deptList.addListSelectionListener(this);
        
        this.add(new JScrollPane(deptList), c2);
        }
        else
        {
           JOptionPane.showMessageDialog(null, "School contains no departments", "Error",
           JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
    /**
     * When a department name is selected from the list, stores its relevant (string) Code and triggers
     * the addition of a grade chooser panel to the main container window.
     * 
     * This method is required by ListSelectionListener.
     * 
     * @param  e : list selection event
     */
    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        if (e.getValueIsAdjusting() == true)
        {
           selectedDeptCode = (depts[deptList.getSelectedIndex()].getCode());
           callback.tell(selectedDeptCode, 2);
           callForm.setDept(selectedDeptCode);
           
        }
    }
    
    
    
}
