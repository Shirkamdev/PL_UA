import java.util.ArrayList;
import java.util.Arrays;

/**
 * Code created by shirkam on 24/03/17
 * Project name: PLP2
 * Package: PACKAGE_NAME
 * Created by shirkam on 24/03/17.
 */
public class ListaOperacionesNoTerminales {
    private ArrayList<OperacionNoTerminal> operaciones;

    public ListaOperacionesNoTerminales(OperacionNoTerminal... operaciones) {
        this.operaciones = new ArrayList<>(Arrays.asList(operaciones));
    }

    public ArrayList<OperacionNoTerminal> getOperaciones() {
        return operaciones;
    }
}
