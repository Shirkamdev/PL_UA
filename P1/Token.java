public class Token {
	public int fila;
	public int columna;
	public String lexema;
	public int tipo;

	public static final int
		PARI	=0,
		PARD	=1,
		MULOP	=2,
		ADDOP	=3,
		PYC		=4,
		DOSP	=5,
		COMA 	=6,
		ASIG	=7,
		LLAVEI	=8,
		LLAVED	=9,
		CLASS 	=10,
		PUBLIC 	=11,
		PRIVATE =12,
		FLOAT	=13,
		INT 	=14,
		RETURN	=15,
		ENTERO	=16,
		ID		=17,
		REAL	=18,
		EOF		=19;

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
			case 0:
				return "(";
				
			case 1:
				return ")";
				
			case 2:
				return "* /";
				
			case 3:
				return "+ -";
				
			case 4:
				return ";";
				
			case 5:
				return ":";
				
			case 6:
				return ",";
				
			case 7:
				return "=";
				
			case 8:
				return "{";
				
			case 9:
				return "}";
				
			case 10:
				return "'class'";
				
			case 11:
				return "'public'";
				
			case 12:
				return "'private'";
				
			case 13:
				return "'float'";
				
			case 14:
				return "'int'";
				
			case 15:
				return "'return'";
				
			case 16:	
				return "numero entero";
				
			case 17:
				return "identificador";
				
			case 18:
				return "numero real";

			case 29:
				return "EOF";			

		}

		return "ERROR";
	}

}