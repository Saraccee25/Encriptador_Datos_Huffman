package huffman;

import static huffman.Archivos.descomprimirTexto;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Inicio {

    public static void main(String[] args) {

        int opc = 0;
        String textoDescomprimido = "";

        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog("Ingrese una opción: \n"
                    + "1. Comprimir texto \n"
                    + "2. Descomprimir texto \n"
                    + "0. Salir"));

            switch (opc) {
                case 1: 
                     try {
                    // Paso 1: Leer el archivo de texto
                    String text = LectorArchivos.leerArchivo("./texto.txt"); //listo

                    // Paso 2: Calcular la frecuencia de cada carácter
                    HashMap<Character, Integer> frecuencia = ContadorFrecuencia.calcularFrecuencia(text);//listo

                    // frecuencia de caracteres en consola
                    System.out.println("\nFrecuencia de caracteres:");
                    for (char c : frecuencia.keySet()) {
                        System.out.println(c + ": " + frecuencia.get(c));
                    }

                    // Paso 3: Construir el árbol de Huffman
                    Nodo root = ArbolHuffman.crearArbol(frecuencia);//listo

                    // Paso 4: Generar códigos Huffman
                    HashMap<Character, String> codigos = ArbolHuffman.generarCodigosHuffman(root);

                    // Paso 5: Comprimir el texto
                    String textoComprimido = Archivos.comprimirTexto(text, codigos);
                    System.out.println(textoComprimido);

                    // Paso 6: Escribir el texto comprimido en un archivo
                    String rutaArchivo = "./textocomprimido.sara";
                    Archivos.escribirTextoComprimido(textoComprimido, rutaArchivo);

                    // Paso 7: Descomprimir el texto
                     textoComprimido = Archivos.leerTextoComprimido("./textocomprimido.sara");
                    textoDescomprimido = descomprimirTexto(textoComprimido, root);
                    System.out.println(textoComprimido);
                    System.out.println(textoDescomprimido);

                    JOptionPane.showMessageDialog(null, "El texto ha sido comprimido exitosamente en la carpeta raíz del programa");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
                case 2:
                    JOptionPane.showMessageDialog(null, "El texto descomprimido es: " + textoDescomprimido);
                    String rutaArchivoDescomprimido = "./textodescomprimido.txt";
                    Archivos.escribirTextoDescomprimido(textoDescomprimido, rutaArchivoDescomprimido);

            }
        } while (opc != 0);
    }
}
