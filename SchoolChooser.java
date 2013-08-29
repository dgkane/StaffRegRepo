import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;


/**
 * Displays a scroll list of the available schools (as contained in the School class's collection
 * attribute) and passes the String code of the selected school to the callback class (RegHelper).
 * 
 * @author David Kane
 * @version 0.1
 */
public class SchoolChooser extends JPanel implements ListSelectionListener
{
    private ChooserListener callback;
    
    private GridBagConstraints c1;
    private GridBagConstraints c2;
    
    private Collection<School> schoolColl;
    
    private School[] schools;
    
    private String[] schoolNames;
    private String selectedSchoolCode;
    
    private JList schoolList;
    
    
    /**
     * Constructor for objects of class SchoolChooser
     * 
     * @param c : The ChooserListener of the RegHelper class that creates this window
     * 
     */
    public SchoolChooser(ChooserListener c)
    {
       super();
       
       callback = c;
       
       this.setLayout(new GridBagLayout());
       c1 = new GridBagConstraints();
       c2 = new GridBagConstraints();
       
       c1.gridx = 0;
       c1.gridy = 0;
       c2.gridx = 0;
       c2.gridy = 1;
       
       // Gets the collection of schools as created by the Loader.load() call in RegHelper
       schoolColl = School.getCollection();
       
       // Sets size of school and school name arrays according to collection size
       int numSchools = schoolColl.size();
       schools = new School[numSchools];
       schoolNames = new String[numSchools];
       
       this.add(new JLabel("School: "), c1);
       
       // For each school object in collection, store the entire school object in one array
       // and its name (as String) in another
       int i = 0;
       for(School school : schoolColl)
       {
         schools[i] = school;
         schoolNames[i] = school.getName();
         i++;
       }
       
       // Sets up a JList from String array of school names
       schoolList = new JList(schoolNames);
       // Set the selection mode of the list to allow only one item to be chosen at a time
       schoolList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       
       // Adds a ListSelectionListener, which responds when a user clicks on the item
       schoolList.addListSelectionListener(this);
       
       // Adds the JList of school names to a scroll pane
       this.add(new JScrollPane(schoolList), c2);
       
       
    }

    
    /**
     * When a school name is selected from the list, stores its relevant (string) Code and triggers
     * the addition of a department chooser panel to the main container window.
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
           selectedSchoolCode = (schools[schoolList.getSelectedIndex()].getCode());
           callback.tell(selectedSchoolCode, 1);
           
           
        }
    }
    
    
    
}