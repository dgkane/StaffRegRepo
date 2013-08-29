import java.util.*;
/**
 * Represents a salary level for staff members. Represented by a numberical value
 * in the range 1-10. A Grade object is linked to a specific Department object and
 * may have the same values as other Grade objects if the position has the same salary choices.
 * @author Rod Fretwell (modified by Paul Trundle)
 * @version September 2011
 */
public class Grade {
    // instance variables
    private String name;
    private Department dept;
    private Map<String,Staff> staffList; // list of staff on a given pay grade
    
    /**
     * Constructor for objects of class Grade
     * @param dept the department
     * @param name  the identifier for this stage of the course
     */
    public Grade(Department dept, String name) {
        this.name = name;
        this.dept = dept;
        staffList = new TreeMap<String,Staff>();
    }

    /**
     * Returns the name of the pay grade
     * @return the name of the stage
     */
    public String getName() { return name; }

    /**
     * Returns a string representation of the Grade object.
     * @return a string representation of the Grade object.
     */
    public String toString() { return dept.getName()+", grade "+name; }

    /**
     * Adds a Staff object to the collection
     * @param staff the Staff object to be added to this pay grade within the department
     */
    public void addStaff(Staff staff) {
        staffList.put(staff.getId(),staff);
    }

}
