package Classes;

import CustomExceptions.*;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

/**
 * Class for exception handling for GUI elements
 * @author Doğukan Baysal
 * @version 1.0
 */
public class CustomEventQueue extends EventQueue{
    /**
     * Override dispatchEvent method for exception handling
     * @param newEvent an instance of {@code java.awt.AWTEvent},
     *          or a subclass of it
     */
    protected void dispatchEvent(AWTEvent newEvent) {
        try {
            super.dispatchEvent(newEvent);
        } catch(AnimalNotFoundException ex){
            JOptionPane.showMessageDialog(new Frame(), "Animal does not exist in the system with the given TagNo");
        }
        catch(EmployeeNotFoundException ex){
            JOptionPane.showMessageDialog(new Frame(), "Employee does not exist in the system with the given EmpID");
        }
        catch(WrongEmployeeTypeException ex){
            JOptionPane.showMessageDialog(new Frame(), "Unxpected type of employee");
        }
        catch(WrongAnimalTypeException ex){
            JOptionPane.showMessageDialog(new Frame(), "Unexpected type of animal");
        }
        catch (NotUniqueTagException ex){
            JOptionPane.showMessageDialog(new Frame(), "Please use unique tag no");
        }
        catch(NotUniqueIdException ex){
            JOptionPane.showMessageDialog(new Frame(), "Please use unique Id");
        }
        catch(InputMismatchException ex){
            JOptionPane.showMessageDialog(new Frame(), "Please enter correct type of inputs");
        }
        catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(new Frame(), "Please enter correct type of inputs");
        }
        catch (DateTimeParseException ex){
            JOptionPane.showMessageDialog(new Frame(), "Please use supported date format");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(new Frame(), "An unexpected error occured");
            System.out.println(ex);
        }
    }
}
