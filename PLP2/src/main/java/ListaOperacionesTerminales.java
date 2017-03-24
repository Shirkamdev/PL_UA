import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Code created by shirkam on 23/03/17
 * Project name: PLP2
 * Package: PACKAGE_NAME
 * Created by shirkam on 23/03/17.
 */
public class ListaOperacionesTerminales {
    private ArrayList<OperacionTerminales> operaciones;

    public ListaOperacionesTerminales(OperacionTerminales... operaciones) {
        this.operaciones = new ArrayList<>(Arrays.asList(operaciones));
    }

    public List<Token> getTokensEsperados() {
        List<Token> lista = new ArrayList<>();

        for(OperacionTerminales o: operaciones) {
            lista.add(new Token(o.tipoToken));
        }

        return lista;
    }

    public ArrayList<OperacionTerminales> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(ArrayList<OperacionTerminales> operaciones) {
        this.operaciones = operaciones;
    }
}
