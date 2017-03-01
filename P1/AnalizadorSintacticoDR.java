import java.lang.StringBuilder;


public class AnalizadorSintacticoDR {
	private AnalizadorLexico al;
	private StringBuilder str;
	private boolean imprimir;
	private Token t;

	public AnalizadorSintacticoDR(AnalizadorLexico a) {
		this.al = a;
		this.imprimir=true;
		this.str = new StringBuilder();
	}

	//////////////
	// FUNCIONES DE NO TERMINALES
	//////////////

	//ORDEN DE TOKENS ESPERADOS:
	//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
	//coma, int, float, asig, return, addop, mulop, real, entero

	/*
	public final void A()
	{
		if (token.tipo == Token.TRES || token.tipo == Token.CUATRO) {
			B();
			emparejar(Token.UNO);
		}
		else if (token.tipo == Token.DOS) {
			emparejar(Token.DOS);
		}
		else errorSintaxis(Token.TRES,Token.CUATRO,Token.DOS);
	}
	*/

	public void S() {
		//Una condicion por cada conjunto de prediccion
		if(this.t.tipo == Token.CLASS) {
			C(); 	//LLamamos a su metodo para los no terminales
					//Si es terminal, llamamos a emparejar
		}
		else {
			Token t1 = new Token();
			t1.tipo = Token.CLASS;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column+"): encontrado ’"+this.t.lexema+"’, esperaba "+t1.toString());
		}
	}	

	public void C() {
		if(this.t.tipo == Token.CLASS) {
			emparejar(Token.CLASS);
			emparejar(Token.ID);
			emparejar(Token.LLAVEI);
			B();
			V();
			emparejar(Token.LLAVED);

			addRegla(2); //Regla 2
		}
		else {
			Token t1 = new Token();
			t1.tipo = Token.CLASS;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column+"): encontrado ’"+this.t.lexema+"’, esperaba "+t1.toString());
		}
	}

	public void B() {
		if(this.t.tipo == Token.PUBLIC) {
			emparejar(Token.PUBLIC);
			emparejar(Token.DOSP);
			P();

			addRegla(2); //Regla 2
		}
		else if(this.t.tipo == Token.PRIVATE || this.t.tipo == Token.LLAVED) {}
		else {
			Token t1 = new Token();
			t1.tipo = Token.CLASS;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column+"): encontrado ’"+this.t.lexema+"’, esperaba "+t1.toString());
		}
	}

	public void V() {
		
	}

	public void P() {
		
	}

	public void D() {
		
	}

	public void Cod() {
		
	}

	public void L() {
		
	}

	public void Tipo() {
		
	}

	public void Bloque() {
		
	}

	public void SecInstr() {
		
	}

	public void Instr() {
		
	}

	public void Expr() {
		
	}

	public void ExprPr() {
		
	}

	public void Term() {
		
	}

	public void TermPr() {
		
	}

	public void Factor() {
		
	}

	//////////////////////////

	public void comprobarFinFichero() {
		if(this.t.tipo = Token.EOF) { //Fin de fichero
			if(this.imprimir) {
				imprimirReglas();
			}
		}
		else {
			System.err.println("Error sintactico ("this.t.row+","+this.t.column+"): encontrado ’"+this.t.lexema+"’, esperaba eof");
			System.exit(-1);
		}
	}

	public final void emparejar(int tokEsperado) {
		if(this.t.tipo == tokEsperado) {
			this.t = this.al.siguienteToken();
		}
		else {
			Token t2 = new Token;
			t2.tipo = tokEsperado;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column+"): encontrado ’"+this.t.lexema+"’, esperaba "+ t2.toString());
		}
	}

	////////////////
	// FUNCIONES AUXILIARES
	///////////////

	private void addRegla(int r) {
		str.append(" ");
		str.append(r);
	}

	private void imprimirReglas() {
		System.out.println(this.str);
	}
}