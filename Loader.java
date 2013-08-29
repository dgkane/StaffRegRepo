
/**
 * Loads test data
 * 
 * @author Rod Fretwell (modified by Paul Trundle)
 * @version September 2011
 */
public class Loader {

    public static void load() {
        String[] gradesOneToThree = {"1","2","3"};
        String[] gradesOneToFour = {"1","2","3","4"};
        String[] one = {"1"};

        School.add("SCIM", "Computing, Informatics and Media");
        School school = School.get("SCIM");
        Department.add("COMP", "Computing", gradesOneToThree, school);
        Department.add("CT", "Creative Technology", gradesOneToFour, school);
        Department.add("BMS", "Bradford Media School", gradesOneToFour, school);
        Department.add("SUPP", "Support Staff", gradesOneToThree, school);
        Department.add("CLN", "Cleaning Staff", one, school);
        Staff.add("20012345", "Bob", Department.get("COMP","2"));

        School.add("ENG", "Engineering, Design and Technology");
        school = School.get("ENG");
        Department.add("STRUCT", "Structural Engineering", gradesOneToThree, school);

        School.add("SOH", "School of Health");
    }

}

