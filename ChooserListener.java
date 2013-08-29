/**
 * Classes that implement interface ChooserListener can be told of the
 * selection made by the user of a SchoolChooser or a DepartmentChooser.
 * The interface has just one method.
 * @author Rod Fretwell, David Kane
 * @version August 2012
 */

public interface ChooserListener {

    /**
     * Slightly modified from Rod Fretwell's version.
     * The method now tell()s a String rather than an object.
     * 
     * @param s : the String code of the school, department, grade, Staff ID or name corresponding to the
     *              user's selection
     * @param i : the integer code for the attribute to be written to.
     * 
     */
    void tell(String s, int i);
}