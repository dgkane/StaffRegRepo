import java.util.*;
/**
 * Each instance of class Staff represents one staff member 
 * employed by a particular school, in a single department or job role.
 * A staff member is identified by an id (identification number).
 * The Class holds the collection of all registered staff.
 * @author Rod Fretwell (modified by Paul Trundle)
 * @version September 2011
 */
public class Staff {
    // class attribute
    private static Map<String,Staff> allStaff
                    = new TreeMap<String,Staff>();
    // instance variables
    private String id;
    private String name;
    private Grade payGrade;
    
    /**
     * Creates a Staff object with the given attributes and adds that object
     * to the register of staff provided that there is not already a staff member
     * registered with the given id. In that case the return value is null.
     * A new Staff record is not created if there is already a record with the
     * given id. In that case the method returns the previously stored Staff
     * object for the user's information.
     * @param id    the staff identification number
     * @param name  the name of the staff member
     * @param grade the Grade object representing the salary level of the staff member
     * @return null for a new staff member, otherwise previously stored Staff object
     *              with the given id.
     */
    public static Staff add(String id, String name, Grade grade) {
        Staff retValue = allStaff.get(id);
        if (retValue==null) {
            Staff staff = new Staff(id, name, grade);
            allStaff.put(id,staff);
            grade.addStaff(staff);
        }
        return retValue;
    }

    private Staff(String id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.payGrade = grade;
    }

    /**
     * Returns the Staff object for the registered staff with the given id.
     * or null if there is no such staff member
     * @param id   a string containing the staff's id
     * @return the Staff for the given id. or null
     */
    public static Staff getStaff(String id) { return allStaff.get(id); }

    /**
     * returns staff id
     * @return the staff's id
     */
    public String getId() { return id; }

    /**
     * Returns a string representation of the Staff object.
     * @return a string representation of the Staff object
     */
    public String toString() { return "id:"+id+", "+name+", "+payGrade; }

}