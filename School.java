import java.util.*;
/**
 * Each instance of class School represents an University School that
 * has a number of departments/job roles available that new staff
 * may be registered with. A school is identified by a school code.
 * The class holds the collection of all academic schools.
 * @author Rod Fretwell (modified by Paul Trundle)
 * @version Septmeber 2011
 */
public class School {
    // class attribute
    private static Map<String,School> allSchools
                    = new TreeMap<String,School>();
    // instance variables
    private String code;
    private String name;
    private Map<String,Department> set; // list of departments/job roles within this school

    /**
     * Creates a School object with the given attributes and adds that
     * object to the collection of schools provided that there is not
     * already a school recorded with the given school code. In that
     * case the return value is null.
     * A new School record is not created if there is already a record with
     * the given school code. In that case the method returns the
     * previously stored School object for the user's information.
     * @param code  the school code
     * @param name  the name of the school
     * @return null for a new school, otherwise previously stored
     *              School object for the given department code
     */
    public static School add(String code, String name) {
        School retValue = allSchools.get(code);
        if (retValue==null)
            allSchools.put(code, new School(code, name));
        return retValue;
    }

    private School(String code, String name) {
        this.code = code;
        this.name = name;
        set = new TreeMap<String,Department>();
    }

    /**
     * Returns the school for a given code or null if there is no
     * school for the code
     * @param code the school code
     * @return the school for the given code or null if there is no such
     *         school
     */
    public static School get(String code) { return allSchools.get(code); }

    /**
     * Returns the collection of all schools.
     * The collection's iterator returns the schools in ascending order
     * of school code.
     * @return the collection of all schools
     */
    public static Collection<School> getCollection() {
        return allSchools.values();
    }

    /**
     * Returns the code for the school
     * @return the code for the school
     */
    public String getCode() { return code; }

    /**
     * Returns the name of the school
     * @return the name of the school
     */
    public String getName() { return name; }

    /**
     * Adds a Department object to the collection of departments/job roles available
     * for new staff members within the school
     * @param  course the Department to be added
     */
    public void add(Department dept) {
        set.put(dept.getCode(),dept);
    }

    /**
     * Returns the collection of all departments/job roles within the school.
     * The collection's iterator returns the departments/job roles in ascending order
     * of department/job role code.
     * @return the collection of all departments/job roles offered by the school
     */
    public Collection<Department> getDepartments() { return set.values(); }
}

