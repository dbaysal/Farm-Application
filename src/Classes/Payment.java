package Classes;
/**
 * Inteface for getting payment information of employees
 * @author Doğukan Baysal
 * @version 1.0
 */
public interface Payment {
    /**
     * Constant double value for salary calculations
     */
    double grossSalary = 10000;

    /**
     * Method to be implemented to get salary of an employee
     * @return double, salary amount
     */
    double getSalary();

}
