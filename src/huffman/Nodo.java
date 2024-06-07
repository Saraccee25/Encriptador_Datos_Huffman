package huffman;

/**
 *
 * @author Sara Castañeda
 */
public class Nodo implements Comparable<Nodo> {

    //atributos
    private char caracter;
    private int frecuencia;
    private Nodo ligaIzquierda;
    private Nodo ligaDerecha;

    public Nodo() {
        this.ligaDerecha=null;
        this.ligaIzquierda=null;
        
    }
    public Nodo(char caracter, int frecuencia) {
        this.ligaDerecha=null;
        this.ligaIzquierda=null;
        this.caracter = caracter;
        this.frecuencia = frecuencia;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Nodo getLigaIzquierda() {
        return ligaIzquierda;
    }

    public void setLigaIzquierda(Nodo ligaIzquierda) {
        this.ligaIzquierda = ligaIzquierda;
    }

    public Nodo getLigaDerecha() {
        return ligaDerecha;
    }

    public void setLigaDerecha(Nodo ligaDerecha) {
        this.ligaDerecha = ligaDerecha;
    }

    @Override
    public int compareTo(Nodo otro) {
        return this.frecuencia - otro.frecuencia;
    }

}
