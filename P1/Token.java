public class Token {
	public int fila;
	public int columna;
	public String lexema;
	public int tipo;

	public static final int
		PARI	=1,
		PARD	=2,
		MULOP	=3,
		ADDOP	=4,
		PYC		=5,
		DOSP	=6,
		COMA 	=7,
		ASIG	=8,
		LLAVEI	=9,
		LLAVED	=10,
		CLASS 	=11,
		PUBLIC 	=12,
		PRIVATE =13,
		FLOAT	=14,
		INT 	=15,
		RETURN	=16,
		ENTERO	=17,
		ID		=18,
		REAL	=19,
		EOF		=20;

	public Token(int f, int c, String lex, int t) {
		this.fila = f;
		this.columna = c;
		this.lexema = lex;
		this.tipo = t;
	}

	public String toString() {
		switch(this.tipo) {
			case 1:
				return "(";
				break;
			case 2:
				return ")";
				break;
			case 3:
				return "* /";
				break;
			case 4:
				return "+ -";
				break;
			case 5:
				return ";";
				break;
			case 6:
				return ":";
				break;
			case 7:
				return ",";
				break;
			case 8:
				return "=";
				break;
			case 9:
				return "{";
				break;
			case 10:
				return "}";
				break;
			case 11:
				return "'class'";
				break;
			case 12:
				return "'public'";
				break;
			case 13:
				return "'private'";
				break;
			case 14:
				return "'float'";
				break;
			case 15:
				return "'int'";
				break;
			case 16:
				return "'return'";
				break;
			case 17:	
				return "numero entero";
				break;
			case 18:
				return "identificador";
				break;
			case 19:
				return "numero real";
				break;

		}

		return "";
	}

}