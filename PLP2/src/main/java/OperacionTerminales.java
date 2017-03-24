/**
 * Code created by shirkam on 23/03/17
 * Project name: PLP2
 * Package: PACKAGE_NAME
 * Created by shirkam on 23/03/17.
 */
public class OperacionTerminales {
    /**
     * El tipo de la operacion. Puede ser 'd' (desplazamiento),
     * 'r' (reduccion) o 'a' (acptacion)
     */
    public char tipo;
    /**
     * El destino a donde va la operacion.
     * Si el tipo es 'd', se refiere a la regla de la TABLA a la que nos desplazamos
     * Si el tipo es 'r', se refiere a la regla de la GRAMATICA que utilizamos
     */
    public int destino;
    /**
     * Es el tipo del token que se necesita leer para ejecutar esta operacion
     */
    public int tipoToken;

    public OperacionTerminales(char tipo, int destino, int tipoToken) {
        this.tipo = tipo;
        this.destino = destino;
        this.tipoToken = tipoToken;
    }
}
