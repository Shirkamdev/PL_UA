import java.io.RandomAccessFile;

class testRandomAccess {
	private static final String path = "./test.txt";

	public static void main(String args[]) {
		RandomAccessFile f;

		try {
			f = new RandomAccessFile(path, "r");

			while(true) {
				try {
					System.out.print((char)f.readByte());

				} catch(java.io.EOFException ex) {
					//Hemos lelgado a fin de fichero
					break;
				} catch(java.io.IOException ex) {
					System.err.println("-- ERROR EN LA LECTURA DEL FICHERO --");
				}
			}
			System.out.print("\n--Fichero leido completamente--\n");

			f.seek(0);
			System.out.println((char) f.readByte());
			f.seek(4);
			System.out.println((char) f.readByte());

			f.close();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
		
	}
}