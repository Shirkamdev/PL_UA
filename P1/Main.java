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
				System.out.println(i + ":\t" + t.toString() + ":\t"+t.lexema);
				i++;
			} while(t.lexema != "");
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}