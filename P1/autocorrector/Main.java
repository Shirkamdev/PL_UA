import java.io.RandomAccessFile;

public class Main {
	private static final String path = "autocorrector/fuentes/p03.txt";

	public static void main(String args[]) {
		RandomAccessFile f;

		try {
			f = new RandomAccessFile(path, "r");

			AnalizadorLexico al = new AnalizadorLexico(f);
			AnalizadorSintacticoDR as = new AnalizadorSintacticoDR(al);
			//Token t;
			
			System.out.println("-- Comenzamos comprobacion de fichero --");
			as.S();
			System.out.println("-- Comprobando si hemos llegado a fin de fichero correctamente --");
			as.comprobarFinFichero();

		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public static String toString(Token t){
		return "("+t.fila+","+t.columna+"): "+t.lexema+" es de tipo "+t.tipo+'\n'; 
    }
}