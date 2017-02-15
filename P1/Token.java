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

	public Token() {
		this.fila =0;
		this.columna =0;
		this.lexema="";
		this.tipo=-1;
	}
 
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
				
			case 2:
				return ")";
				
			case 3:
				return "* /";
				
			case 4:
				return "+ -";
				
			case 5:
				return ";";
				
			case 6:
				return ":";
				
			case 7:
				return ",";
				
			case 8:
				return "=";
				
			case 9:
				return "{";
				
			case 10:
				return "}";
				
			case 11:
				return "'class'";
				
			case 12:
				return "'public'";
				
			case 13:
				return "'private'";
				
			case 14:
				return "'float'";
				
			case 15:
				return "'int'";
				
			case 16:
				return "'return'";
				
			case 17:	
				return "numero entero";
				
			case 18:
				return "identificador";
				
			case 19:
				return "numero real";
				

		}

		return "";
	}

}