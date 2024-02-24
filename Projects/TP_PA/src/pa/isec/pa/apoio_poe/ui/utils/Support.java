package pa.isec.pa.apoio_poe.ui.utils;

import java.io.*;
import java.util.List;
public class Support {
    public static final String DELIMITER = ",";
    public static final int MAX_PROPOSTAS_EM_CANDIDATURAS = 6;

    public static String generateCSV(List<List<String>> info, String filename){
        try{
            FileWriter fw = new FileWriter(filename,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            info.forEach((row)->{
                row.forEach((col)-> pw.print(col+DELIMITER));
                pw.println();
            });
            pw.flush();
            pw.close();
            return "Record saved";
        } catch (IOException e) {
         //   e.printStackTrace();
            return "Erro na gravação de dados";

        }
    }


    public static String[] removeTheElement(String[] arr, int index)
    {

        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null
                || index < 0
                || index >= arr.length) {

            return arr;
        }

        // Create another array of size one less
        String[] anotherArray = new String[arr.length - 1];

        // Copy the elements from starting till index
        // from original array to the other array
        System.arraycopy(arr, 0, anotherArray, 0, index);

        // Copy the elements from index + 1 till end
        // from original array to the other array
        System.arraycopy(arr, index + 1,
                anotherArray, index,
                arr.length - index - 1);

        // return the resultant array
        return anotherArray;
    }
}
