import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * Code created by shirkam on 23/03/17
 * Project name: PLP2
 * Package: PACKAGE_NAME
 * Created by shirkam on 23/03/17.
 */
public class plp2 {
    public static void main(String[] args) {
        if (args.length == 1)
        {
            try {
                RandomAccessFile entrada = new RandomAccessFile(args[0],"r");
                AnalizadorLexico al = new AnalizadorLexico(entrada);
                AnalizadorSintacticoSLR aslr = new AnalizadorSintacticoSLR(al);
                aslr.analizar();
            }
            catch (FileNotFoundException e) {
                System.out.println("Error, fichero no encontrado: " + args[0]);
            }
        }
        else System.out.println("Error, uso: java plp2 <nomfichero>");
    }
}
