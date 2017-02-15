import java.io.RandomAccessFile;

public class AnalizadorLexico {
	private RandomAccessFile f;
	private int estado;

	//Para saber posicion de tokens
	private int row, column;
	//Para saber la posicion en el fichero
	private long pos;

	public AnalizadorLexico(RandomAccessFile f) {
		this.f = f;
		this.estado = 0;
		this.row=0;
		this.column=0;
		this.pos=0;
	}


	//Lee estado actual y el caracter que hemos leido
	private void delta(int c) {
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
				else if(c == ' ' || c == '\n' || c == '\t'){
					this.estado = 0;
				}
				else {
					this.estado = -1;
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
			default:
				this.estado = -1;
		}
	}

	/*
	public int fila;
	public int columna;
	public String lexema;
	public int tipo;	
	*/
	public String siguienteToken() {
		//TODO read here to return a token
		Token t = new Token;

		do {
			if(this.estado == 0) {
				t.lexema = "";
			}
			else if(isFinal()) {
				f.seek(this.pos);
				break;
			}
			//TODO finish this


		}while(true);
		//TODO if token == PALABRA_RESERVADA

		return "";
	}

	private boolean isFinal() {
		switch(this.estado) {
			case 3:
				this.pos--;
				this.column--;
				break;
			case 5:
				this.pos--;
				this.column--;
				break;
			case 6:
				this.pos--;
				this.column--;
				break;
			case 8:
				this.pos--;
				this.column--;
				break;
			case 9:
				this.pos--;
				this.column--;
				break;
			case 13:
				this.pos--;
				this.column--;
				break;
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
		if((c >= 'a' && c <= 'z') || (c >= 'A' || c <= 'Z')) {
			return true;
		}

		return false;
	}
}