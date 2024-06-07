package huffman;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/**
 *
 * @author Sara Castañeda
 */
public class Archivos {

    public static String comprimirTexto(String texto, HashMap<Character, String> codes) {
        StringBuilder textoComprimido = new StringBuilder();
        for (char c : texto.toCharArray()) {
            textoComprimido.append(codes.get(c));
        }
        return textoComprimido.toString();
    }

    public static void escribirTextoComprimido(String textoComprimido, String rutaArchivo) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(rutaArchivo))) {
            byte[] bytes = convertirTextoComprimidoABytes(textoComprimido);
            // Escribir la longitud del texto comprimido como los primeros 4 bytes
            bos.write(intABytes(textoComprimido.length()));
            bos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] convertirTextoComprimidoABytes(String textoComprimido) {
        int longitud = textoComprimido.length();
        int byteLength = (longitud + 7) / 8; // Calcular la cantidad de bytes necesarios (redondear hacia arriba)
        byte[] bytes = new byte[byteLength];
        for (int i = 0; i < longitud; i++) {
            if (textoComprimido.charAt(i) == '1') {
                int byteIndex = i / 8;
                int bitIndex = i % 8;
                bytes[byteIndex] |= (1 << (7 - bitIndex)); // Establecer el bit correspondiente en el byte
            }
        }
        return bytes;
    }

    private static byte[] intABytes(int value) {
        return new byte[]{
            (byte) (value >> 24),
            (byte) (value >> 16),
            (byte) (value >> 8),
            (byte) value
        };
    }

    public static String descomprimirTexto(String textoComprimido, Nodo root) {
        StringBuilder textoDescomprimido = new StringBuilder();
        Nodo nodoActual = root;
        for (char bit : textoComprimido.toCharArray()) {
            if (bit == '0') {
                nodoActual = nodoActual.getLigaIzquierda();
            } else if (bit == '1') {
                nodoActual = nodoActual.getLigaDerecha();
            }

            if (nodoActual.getLigaIzquierda() == null && nodoActual.getLigaDerecha() == null) {
                textoDescomprimido.append(nodoActual.getCaracter());
                nodoActual = root;
            }
        }
        return textoDescomprimido.toString();
    }

    public static void escribirTextoDescomprimido(String textoDescomprimido, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaArchivo)))) {
            writer.write(textoDescomprimido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String leerTextoComprimido(String rutaArchivo) {
        StringBuilder textoComprimido = new StringBuilder();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(rutaArchivo))) {
            // leer la longitud del texto comprimido (los primeros 4 bytes)
            byte[] lengthBytes = new byte[4];
            if (bis.read(lengthBytes) != 4) {
                throw new IOException("Error al leer la longitud del texto comprimido");
            }
            int length = bytesAInt(lengthBytes);
            int byteRead;
            while ((byteRead = bis.read()) != -1) {
                // para convertir cada byte a una cadena de bits de 8 caracteres
                String byteString = String.format("%8s", Integer.toBinaryString(byteRead & 0xFF)).replace(' ', '0');
                // Agregar la cadena de bits al texto comprimido
                textoComprimido.append(byteString);
            }
            // ajusta el tamaño del texto comprimido para quitar los bits adicionales
            if (textoComprimido.length() > length) {
                textoComprimido.setLength(length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textoComprimido.toString();
    }

    private static int bytesAInt(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24)
                | //lo desplaza 24 posiciones para dejar los bits menos significativos en su lugar
                ((bytes[1] & 0xFF) << 16)
                | ((bytes[2] & 0xFF) << 8)
                | (bytes[3] & 0xFF);
    }

}
