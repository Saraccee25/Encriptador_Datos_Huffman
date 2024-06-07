package huffman;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author Sara Castañeda
 */
public class ArbolHuffman {

    public static Nodo crearArbol(HashMap<Character, Integer> frecuencia) {
        // Paso 1: Crear nodos para cada caracter con su frecuencia
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
        for (char c :frecuencia.keySet()) {
            colaPrioridad.offer(new Nodo(c, frecuencia.get(c))); //ya funciona, no con metodo add porque tira excepcion cuando la cola esta llena
        }

        // Paso 2: Crear una cola de prioridad de nodos
        while (colaPrioridad.size() > 1) {
            // Sacar los dos nodos con menor frecuencia
            Nodo izquierdo = colaPrioridad.poll(); //elimina y devuelve la cabeza de la cola, (el nodo)
            Nodo derecho = colaPrioridad.poll();

            // aqui se crea el nuevo nodo
            Nodo combinado = new Nodo('\0', izquierdo.getFrecuencia() + derecho.getFrecuencia());
            combinado.setLigaIzquierda(izquierdo);
            combinado.setLigaDerecha(derecho); //FUNCIONA

            
            colaPrioridad.offer(combinado);
        }

        //retornamos la punta 
        return colaPrioridad.poll();
    }

    public static HashMap<Character, String> generarCodigosHuffman(Nodo root) {
        HashMap<Character, String> codigos = new HashMap<>();
        generarCodigosHuffmanRecursivo(root, "", codigos);
        return codigos;
    }

   public static void generarCodigosHuffmanRecursivo(Nodo nodo, String codigo, HashMap<Character, String> codigos) {
        if (nodo != null) {
            if (nodo.getLigaIzquierda() == null && nodo.getLigaDerecha() == null) { // si es una hoja, hay carater y setea el codigo
                codigos.put(nodo.getCaracter(), codigo);
            } else {
                generarCodigosHuffmanRecursivo(nodo.getLigaIzquierda(), codigo + "0", codigos);
                generarCodigosHuffmanRecursivo(nodo.getLigaDerecha(), codigo + "1", codigos);
            }
        }
    }
}
