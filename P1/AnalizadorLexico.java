import java.io.RandomAccessFile;

public class AnalizadorLexico {
	private RandomAccessFile f;
	private int estado;

	//Para saber posicion de tokens
	private int row, column;
	private int lastRow;
	//Para saber la posicion en el fichero
	private long pos;
	//Para saber la ultima columna de la anterior linea
	private int lastColumn;

	public AnalizadorLexico(RandomAccessFile f) {
		this.f = f;
		this.estado = 0;
		this.row=1;
		this.column=0;
		this.pos=0;
	}


	//Lee estado actual y el caracter que hemos leido
	//y cambia el estado
	private void delta(int c) {
		//System.out.println("\tEstado anterior: "+this.estado);
		switch(this.estado) {
			//Estado base
			case 0:
				//Si es un digito
				if(isNumber(c)) {
					this.estado = 1;
				}
				//si es una letra
				else if(isChar(c)) {
					this.estado = 7;
				}
				//si es un asterisco
				else if(c == '*') {
					this.estado = 9;
				}
				else if(c == '/') {
					this.estado = 10;
				}
				else if(c == '(') {
					this.estado = 14;
				}
				else if(c == ')') {
					this.estado = 15;
				}
				else if(c == '+' || c == '-') {
					this.estado = 16;
				}
				else if(c == ';') {
					this.estado = 17;
				}
				else if(c == ':') {
					this.estado = 18;
				}
				else if(c == ',') {
					this.estado = 19;
				}
				else if(c == '=') {
					this.estado = 20;
				}
				else if(c == '{') {
					this.estado = 21;
				}
				else if(c == '}') {
					this.estado = 22;
				}
				else if((int)c == 32 || c == '\n' || c == '\t'){
					//System.out.println("Pasamos del caracter de la pos " + this.pos);
					this.estado = 0;
				}
				else if((int) c == 3) {
					this.estado = 23;
				}
				else {
					
					System.err.println("Error lexico ("+this.row+","+this.column+"): caracter "+(char)c+"' incorrecto");
					System.exit(-1);
				}
				break;
			//Estado digitos enteros
			case 1:
				//si es un numero
				if(isNumber(c)) {
					this.estado = 1;
				}
				else if(c == '.') {
					this.estado = 2;
				}
				else {
					this.estado = 6;
				}
				break;
			//Estado separador decimal
			case 2:
				if(isNumber(c)) {
					this.estado = 4;
				}
				else {
					this.estado = 3;
				}
				break;
			//Estado final error separador, numero entero
			case 3:
				this.estado = -1;
				break;
			//Estado numero real
			case 4:
				if(isNumber(c)) {
					this.estado = 4;
				}
				else  {
					this.estado = 5;
				}
				break;
			//Estado final numero real
			case 5:
				this.estado = -1;
				break;
			//Estado final numero entero
			case 6:
				this.estado = -1;
				break;
			//Estado identificador
			case 7:
				if(isNumber(c) || isChar(c)) {
					this.estado = 7;
				}
				else {
					this.estado = 8;
				}
				break;
			//Estado final identificador
			case 8:
				this.estado = -1;
				break;
			//Estado final mulop
			case 9:
				this.estado = -1;
				break;
			//Estado /
			case 10:
				if(c == '*') {
					this.estado = 11;
				}
				else {
					this.estado = 13;
				}
				break;
			//Estado comentario
			case 11:
				if(c == '*') {
					this.estado = 12;
				}
				else if((int) c == 3){
					System.err.println("Error lexico: fin de fichero inesperado");
					System.exit(-1);
				}
				else {
					this.estado = 11;
				}
				break;
			//Estado * en comentario
			case 12:
				if(c == '*') {
					this.estado = 12;
				}
				else if(c == '/') {
					this.estado = 0;
				}
				else if((int) c == 3) {
					System.err.println("Error lexico: fin de fichero inesperado");
					System.exit(-1);
				}
				else {
					this.estado = 11;
				}
				break;
			//Estado division
			case 13:
				this.estado = -1;
				break;
			//Estado (
			case 14:
				this.estado = -1;
				break;
			//Estado )
			case 15:
				this.estado = -1;
				break;
			//Estado + -
			case 16:
				this.estado = -1;
				break;
			//Estado ;
			case 17:
				this.estado = -1;
				break;
			//Estado :
			case 18:
				this.estado = -1;
				break;
			//Estado ,
			case 19:
				this.estado = -1;
				break;
			//Estado =
			case 20:
				this.estado = -1;
				break;
			//Estado {
			case 21:
				this.estado = -1;
				break;
			//Estado }
			case 22:
				this.estado = -1;
				break;
			//Estado EOF
			case 23:
				this.estado = -1;
			case 24:
				this.estado = -1;
			default:
				this.estado = -1;
		}

		//System.out.println("\tEstado actual: "+this.estado);
	}

	/*
	public int fila;
	public int columna;
	public String lexema;
	public int tipo;	
	*/
	public Token siguienteToken(){
		//System.out.println("\tVamos leyendo por la posicion: " + this.pos);
		Token t = new Token();
		this.estado = 0; //Inicializamos la lectura

		do {
			//Leemos un caracter, comprobamos que no sea error,
			//comprobamos el nuevo estado, y lo ponemos en el lexema si corresponde
			char c = siguienteChar();
			
			//System.out.println("\t\tLeido char "+ c + "("+(int)c+")");
			//Una vez no ha habido error, cambiamos nuestro estado, y comprobamos
			//que no es final

			if((int) c == 0) {
				//Si hay un error en la lectura de fichero
				this.estado = 24;
			}
			if((int) c == 3) {
				//Si hemos llegado a EOF, hay que poner el tipo de lexema
				//System.out.println("--Hemos leido un EOF --");
				if(t.lexema != "") {
					//System.out.println("El lexema "+t.lexema+" no esta vacio");
					//System.out.println("Hay algo mas: " + t.lexema);
					//System.out.println("Cursor en la pos: " + this.pos);
					//Si ya hay algo más en el lexemac

					//Puede llegar desde 1 2 4 7
					switch(this.estado) {
						case 1:
							t.tipo = Token.ENTERO;
							break;
						case 2:
							t.tipo = Token.ENTERO;
							break;
						case 4:
							t.tipo = Token.REAL;
							break;
						case 7:
							t.tipo = Token.ID;
							break;
						default:
							t.lexema = "";
							t.tipo = Token.EOF;
					}

					return t;

				}
				else {
					//System.out.println("Fin de fichero");
					t.lexema = "";
					t.tipo = Token.EOF;
					return t;
				}
			}
			
			if(this.estado == 0) {
				t.lexema = "";
				t.fila = this.row;
				t.columna = this.column;	

				//System.out.println("Nuevo token en ("+this.row+","+this.column+")");
			}
			else if(this.estado == -1) {
				//ERROR here
				System.err.println("ERROR: Estado invalido");
				t.lexema = "";
				break;
			}

			delta(c);

			t.lexema += c;
			//Si es final, salimos
			if(isFinal(t)) {
				if(c == '\n') {
					this.row--;
					this.column=this.lastColumn;
				}
				break;
			}

		}while(true);
		setTokenType(t);

		int tipo = isReservedWord(t.lexema);
		//System.out.println("El tipo de "+ t.lexema+" es: "+tipo);
		if(tipo != -1) {
			t.tipo = tipo;
		}

		return t;
	}

	private void setTokenType(Token t) {
		switch(this.estado) {
			case 3:
				t.tipo = Token.ENTERO;
				break;
			case 5:
				t.tipo = Token.REAL;
				break;
			case 6:
				t.tipo = Token.ENTERO;
				break;
			case 8:
				t.tipo = Token.ID;
				break;
			case 9:
				t.tipo = Token.MULOP;
				break;				
			case 13:
				t.tipo = Token.MULOP;
				break;
			case 14:
				t.tipo = Token.PARI;
				break;
			case 15:
				t.tipo = Token.PARD;
				break;
			case 16:
				t.tipo = Token.ADDOP;
				break;
			case 17:
				t.tipo = Token.PYC;
				break;
			case 18:
				t.tipo = Token.DOSP;
				break;
			case 19:
				t.tipo = Token.COMA;
				break;
			case 20:
				t.tipo = Token.ASIG;
				break;
			case 21:
				t.tipo = Token.LLAVEI;
				break;
			case 22:	
				t.tipo = Token.LLAVED;
				break;
		}
	}

	private char siguienteChar() {
		try {
			//Vamos a leer un caracter del fichero
			char mander = (char) f.readByte();
			

			this.pos++;
			if(mander == '\n') {
				this.lastColumn=this.column;
				this.row++;
				this.column=0;
			}
			else {
				this.column++;
			}

			//System.out.println("\tLeido char "+mander+" en ("+this.row+","+this.column+")->"+this.pos);

			return mander;
		}
		catch(java.io.EOFException ex) {
			//System.out.println("Hemos leido EOF");
			//Llegado a fin de fichero, devolvemos ETX (end of text)
			return (char) 3;
		}
		catch(java.io.IOException ex) {
			//Error en la lectura de fichero;
			System.exit(-1);
		}

		return ' ';
	}

	private void backFileCursor(int n) {
		try {
			f.seek(this.pos-n);
		}catch(java.io.IOException ex)
		{}
	}

	private boolean isFinal(Token t) {
		switch(this.estado) {
			case 3:
				backFileCursor(2);
				this.pos-=2;
				this.column-=2;
				t.lexema = t.lexema.substring(0, t.lexema.length()-2);
				return true;
			case 5:
				backFileCursor(1);
				this.pos--;
				this.column--;
				t.lexema = t.lexema.substring(0, t.lexema.length()-1);
				return true;
			case 6:
				backFileCursor(1);
				this.pos--;
				this.column--;
				t.lexema = t.lexema.substring(0, t.lexema.length()-1);
				return true;
			case 8:
				//System.out.println("\t\tIdentificador en pos "+this.pos+" retrocediendo 1 posicion");

				backFileCursor(1);
				this.pos--;
				this.column--;
				t.lexema = t.lexema.substring(0, t.lexema.length()-1);
				return true;
			case 9:
				return true;
			case 13:
				backFileCursor(1);
				this.pos--;
				this.column--;
				t.lexema = t.lexema.substring(0, t.lexema.length()-1);
				return true;
			default:
				if(this.estado >= 14 && this.estado <= 24) {
					//Estado final tambien
					return true;
				}
				return false;
		}
	}

	private int isReservedWord(String tok) {
		switch(tok) {
			case "class":
				return Token.CLASS;
			case "public":
				return Token.PUBLIC;
			case "private":
				return Token.PRIVATE;
			case "float":
				return Token.FLOAT;
			case "int":
				return Token.INT;
			case "return":
				return Token.RETURN;
			default:
				return -1;
		}
	}

	private boolean isNumber(int c) {
		if(c >= '0' && c <= '9') {
			return true;
		}

		return false;
	}

	private boolean isChar(int c) {
		if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
			return true;
		}

		return false;
	}

}