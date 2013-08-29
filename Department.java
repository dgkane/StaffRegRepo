import java.util.*;
/**
 * Each instance of class Department represents a department or job role within a
 * school of the university.
 * A department/job role is identified by a department code.
 * The class holds the collection of all departments/job roles available across all schools.
 * @author Rod Fretwell (modified by Paul Trundle)
 * @version September 2011
 */
public class Department {
    // class attribute
    private static Map<String,Department> allDepartments
                    = new TreeMap<String,Department>();
    // instance variables
    private String code;
    private String name;
    private Grade[] grades; // list pay grades available for staff working in this department/job role
    private School school; // school that the department/job role is in

    /**
     * Creates a Department object with the given attributes and adds that object
     * to the collection of departments provided that there is not already a department
     * recorded with the given department code. In that case the return value is
     * null.
     * A new Department record is not created if there is already a record with the
     * given department code. In that case the method returns the previously stored
     * Department object for the user's information.
     * @param code  the department code
     * @param name  the name of the department/job role
     * @param gradesOffered the array of strings representing the pay grades available
     *                      for new staff joing the department or job role
     * @param school the school that the department/job role is within
     * @return null for a new department/job role, otherwise previously stored Department object
     *              for the given department code
     */
    public static Department add(String code, String name, String[] gradesOffered,
                             School school) {
        Department retValue = allDepartments.get(code);
        if (retValue==null)
            allDepartments.put(code, new Department(code, name, gradesOffered, school));
        return retValue;
    }

    private Department(String code, String name, String[] gradesOffered, School school) {
        this.code = code;
        this.name = name;
        grades = new Grade[gradesOffered.length];
        for (int k=0; k<grades.length; k++)
            grades[k] = new Grade(this, gradesOffered[k]);
        this.school = school;
        school.add(this);
    }

    /**
     * Returns the Department object for the given course code
     * @param code the department code
     * @return the department for the given code or null if no department for that code
     *         is found
     */
    public static Department get(String code) { return allDepartments.get(code); }

    /**
     * Returns the Grade object for the given department code and pay grade
     * @param code the department code
     * @param gradeNumber the stage number
     * @return the pay grade for the given department code and garde number or null if
     *         either there is no such pay grade offered in that department or the department
     *         is not found
     */
    public static Grade get(String code, String gradeNumber) {
        Department department = Department.get(code);
        if (department==null)
            return null;
        else
            return department.getGrade(gradeNumber);
    }

    /**
     * Returns the department/job role code
     * @return the department/job role code
     */
    public String getCode() { return code; }


    /**
     * Returns the name of the department/job role.
     * @return the name of the department/job role.
     */
    public String getName() { return name; }

    /**
     * Returns the array of the pay grades available for staff in this department/job role
     * @return the array of pay grades
     */
    public Grade[] getGrades() { return grades; }

    /**
     * returns the Grade object for the given pay grade number
     * @param gradeNumber the pay grade number
     * @return the grade for the given stage number or null if there is no such
     *         stage in the course
     */
    public Grade getGrade(String gradeNumber) {
        for (Grade g : grades)
            if (g.getName().equals(gradeNumber))
                return g;
        return null;
    }
}
