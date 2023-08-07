package datagateways;

import java.util.ArrayList;

/**
 * The DataManager interface represents a contract for classes that provide data management operations.
 * It defines methods for reading data from a file and processing it.
 */
public interface DataManager <T>{

    /**
     * Reads data from an input with the specified filename and returns it as an ArrayList of String arrays.
     * Each String array represents a row of data in the CSV file, with each element being a value from a column.
     *
     * @param input The input to the method
     * @return An ArrayList of String arrays representing the data read from the CSV file.
     */
    ArrayList<String[]> readData(T input);
}
