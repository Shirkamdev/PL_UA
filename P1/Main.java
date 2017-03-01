import java.io.RandomAccessFile;

public class Main {
	private static final String path = "./test.txt";

	public static void main(String args[]) {
		RandomAccessFile f;

		try {
			f = new RandomAccessFile(path, "r");

			AnalizadorLexico al = new AnalizadorLexico(f);
			Token t;
			int i = 0; 

			do {
				t = al.siguienteToken();
				System.out.print(i + ":\t" + toString(t));
				i++;
			} while(t.lexema != "");
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public static String toString(Token t){
		return "("+t.fila+","+t.columna+"): "+t.lexema+" es de tipo "+t.tipo+'\n'; 
    }
}