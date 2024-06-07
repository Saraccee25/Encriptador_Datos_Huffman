
package huffman;

import java.util.HashMap;

/**
 *
 * @author Sara Castañeda
 */
public class ContadorFrecuencia {
    public static HashMap<Character, Integer> calcularFrecuencia(String text) {
        HashMap<Character, Integer> frequencia = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencia.put(c, frequencia.getOrDefault(c, 0) + 1);
        }
        return frequencia;
    }
}
