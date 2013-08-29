import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Displays buttons for clearing the textFields on an associated input form and registering a new member of staff
 * with the details stored in the RegHelper class.
 * 
 * @author David Kane
 * @version 0.1
 */
public class ButtonPanel extends JPanel implements ActionListener
{
    
    
    private RegHelper callback;
    private FormPanel callForm;
    
    /**
     * Constructor for objects of class ButtonPanel
     * 
     * @param r : The ChooserListener of the RegHelper class that creates this window
     */
    public ButtonPanel(RegHelper r, FormPanel f)
    {
        
        callback = r;
        callForm = f;
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        
        c1.anchor = GridBagConstraints.CENTER;
        c1.fill = GridBagConstraints.HORIZONTAL;
        
        JButton clear = new JButton("Clear");
        JButton enter = new JButton("Enter");
        
        clear.addActionListener(this);
        enter.addActionListener(this);
        // Since the buttons share the same ActionListener, their functions will be distinguished with a String
        // ActionCommand.
        clear.setActionCommand("clear");
        enter.setActionCommand("enter");
        
        this.add(clear);
        this.add(enter);
    }

    
    /**
     * Triggers methods clearFields() or passToRegistration() in RegHelper when a button is pressed, depending
     * on the ActionCommand sent to the listener.
     * 
     * This method is required by ActionListener.
     * 
     * @param  e : list selection event
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
         if ("clear".equals(e.getActionCommand()))
         {
             
             callForm.clear();
         }
         if ("enter".equals(e.getActionCommand()))
         {
             callback.passToRegistration();
         }
        
     }
}
