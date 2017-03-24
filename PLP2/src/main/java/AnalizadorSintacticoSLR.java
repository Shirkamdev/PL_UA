import java.util.Stack;
import java.util.Vector;

/**
 * Code created by shirkam on 23/03/17
 * Project name: PLP2
 * Package: PACKAGE_NAME
 * Created by shirkam on 23/03/17.
 *
 * Clase que realiza un analisis sintacto recursivo por la izquierda con una gramatica similar,
 * pero simplificada, a la de java.
 */
public class AnalizadorSintacticoSLR {
    /**
     * Analizador lexico que ira dando tokens
     * al analizador sintactico.
     */
    private AnalizadorLexico al;

    private Stack<Integer> pila;
    private Token t;

    private Vector<Integer> reglas = new Vector<>();


    public AnalizadorSintacticoSLR(AnalizadorLexico a) {
        this.al = a;

        this.pila = new Stack<>();

    }

    public void analizar() {
        pila.push(0);
        t = al.siguienteToken();
        bucle_principal: while(true) {
            int estadoActual = pila.peek();
            System.out.println("\tEstado actual "+estadoActual+" he leido "+
                    t.lexema+"("+t.toString()+")");
            if(TablaTerminales.isExpected(t, estadoActual)) {
                OperacionTerminales o = TablaTerminales.getOperacion(t, estadoActual);

                switch(o.tipo) {
                    case 'd':
                        System.out.println("\t\tAplico un desplazamiento a la regla " + o.destino);
                        pila.push(o.destino);
                        t = al.siguienteToken();
                        break;
                    case 'r':
                        System.out.println("\t\tAplico una reduccion por la regla "+o.destino);
                        int componentes = TablaNoTerminales.longitudReglas[o.destino-1];
                        System.out.println("\t\tHe de quitar "+componentes+" reglas de la pila");
                        for(int i=0; i<componentes; i++)
                            pila.pop();

                        System.out.println("\t\tAhora el estado de arriba de la pila es: "
                            +pila.peek());

                        //sea estado actual p
                        //sea A la parte izquierda de la regla o.destino
                        //push(Ir_a[p, A]

                        int noTerminal = TablaNoTerminales.noTerminalesReglas[o.destino-1];

                        int aux = TablaNoTerminales.ir_a(pila.peek(), noTerminal);
                        if(aux != -1) {
                            System.out.println("\t\tY apilamos la regla "+aux);
                            //Apilamos la regla usada al principio, para luego poder imprimir el vector de normal
                            reglas.add(0, o.destino);

                            pila.push(aux);
                        } else {
                            System.out.println("\t\tNo terminal no valido");
                            o.tipo = 'z'; //Estado error
                        }
                        break;
                    case 'a':
                        break bucle_principal;
                    default:
                        System.err.println("ERROR: tipo de operacion no valido en el token "
                                +t.lexema +" de tipo "+t.toString());
                        System.exit(-1);
                        break;
                }
            } else {
                String expected = TablaTerminales.getTokensEsperados(estadoActual);
                if(t.tipo == Token.EOF) {
                    imprimirErrorSintacticoFinFichero(expected);
                } else {
                    imprimirErrorSintactico(t, expected);
                }
                System.exit(-1);
            }
        }

        //Imprimimos por pantalla las reglas
        for(int i=0; i<reglas.size(); i++) {
            if(i==0)
                System.out.print(reglas.get(i));
            else
                System.out.print(" "+reglas.get(i));
        }
        System.out.println();
    }

    private void imprimirErrorSintactico(Token t, String esperados) {
        System.err.println("Error sintactico ("+t.fila+","+t.columna
                +"): encontrado '"+t.lexema+"', esperaba"+esperados);
    }

    private void imprimirErrorSintacticoFinFichero(String esperados) {
        System.err.println("Error sintactico: encontrado fin de fichero, " +
                "esperaba"+esperados);
    }
}
