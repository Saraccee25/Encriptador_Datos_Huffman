
package huffman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Sara Castañeda
 */
public class LectorArchivos {
    public static String leerArchivo(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder text = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            text.append(line);
        }
        br.close();
        return text.toString();
    }
}
