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
			System.exit(-1);
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
			System.exit(-1);
		}
	}

	public void B() {
		if(this.t.tipo == Token.PUBLIC) {
			emparejar(Token.PUBLIC);
			emparejar(Token.DOSP);
			P();

			addRegla(3); //Regla 2
		}
		else if(this.t.tipo == Token.PRIVATE || this.t.tipo == Token.LLAVED) {
			addRegla(4);
		}
		else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.LLAVED;
			Token t2 = new Token();
			t2.tipo = Token.PUBLIC;
			Token t3 = new Token();
			t3.tipo = Token.PRIVATE;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column
				+"): encontrado ’"+this.t.lexema
				+"’, esperaba "+t1.toString()
				+" "+t2.toString()
				+" "+t3.toString());
			System.exit(-1);
		}
	}

	public void V() {
		if(this.t.tipo == Token.PRIVATE) {
			emparejar(Token.PRIVATE);
			emparejar(Token.DOSP);
			P();

			addRegla(5);
		} else if(this.t.tipo == Token.LLAVED) {
			addRegla(6);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.LLAVED;
			Token t2 = new Token();
			t2.tipo = Token.PRIVATE;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column
				+"): encontrado ’"+this.t.lexema
				+"’, esperaba "+t1.toString()
				+" "+t2.toString());
			System.exit(-1);
		}
	}

	public void P() {
		if(this.t.tipo == Token.INT || this.t.tipo == Token.FLOAT 
			|| this.t.tipo == Token.CLASS) {
			D();
			P();

			addRegla(7);

		} else if(this.t.tipo == Token.PRIVATE || this.t.tipo == Token.LLAVED) {
			addRegla(8);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.CLASS;
			Token t2 = new Token();
			t2.tipo = Token.LLAVED;
			Token t3 = new Token();
			t3.tipo = Token.PRIVATE;
			Token t4 = new Token();
			t4.tipo = Token.INT;
			Token t5 = new Token();
			t5.tipo = Token.FLOAT;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column
				+"): encontrado ’"+this.t.lexema
				+"’, esperaba "+t1.toString()
				+" "+t2.toString()
				+" "+t3.toString()
				+" "+t4.toString()
				+" "+t5.toString());
			System.exit(-1);
		}
		
	}

	public void D() {
		if(this.t.tipo == Token.INT || this.t.tipo == Token.FLOAT) {
			Tipo();
			emparejar(Token.ID);
			emparejar(Token.PARI);
			Tipo();
			emparejar(Token.ID);
			L();
			emparejar(Token.PARD);
			Cod();

			addRegla(9);
		}else if(this.t.tipo == Token.CLASS) {
			D();

			addRegla(10);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.CLASS;
			Token t2 = new Token();
			t2.tipo = Token.INT;
			Token t3 = new Token();
			t3.tipo = Token.FLOAT;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column
				+"): encontrado ’"+this.t.lexema
				+"’, esperaba "+t1.toString()
				+" "+t2.toString()
				+" "+t3.toString());
			System.exit(-1);
		}
	}

	public void Cod() {
		if(this.t.tipo == Token.PYC) {
			emparejar(Token.PYC);

			addRegla(11);
		} else if(this.t.tipo == Token.LLAVEI) {
			Bloque();

			addRegla(12);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.LLAVEI;
			Token t2 = new Token();
			t2.tipo = Token.PYC;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column
				+"): encontrado ’"+this.t.lexema
				+"’, esperaba "+t1.toString()
				+" "+t2.toString());
			System.exit(-1);
		}
	}

	public void L() {
		if(this.t.tipo == Token.COMA) {
			emparejar(Token.COMA);
			Tipo();
			emparejar(Token.ID);
			L();

			addRegla(13);
		} else if(this.t.tipo == Token.PARD) {
			addRegla(14);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.PARD;
			Token t2 = new Token();
			t2.tipo = Token.COMA;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column
				+"): encontrado ’"+this.t.lexema
				+"’, esperaba "+t1.toString()
				+" "+t2.toString());
			System.exit(-1);
		}
	}

	public void Tipo() {
		if(this.t.tipo == Token.INT) {
			emparejar(Token.INT);

			addRegla(15);
		} else if(this.t.tipo == Token.FLOAT) {
			emparejar(Token.FLOAT);

			addRegla(16);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.INT;
			Token t2 = new Token();
			t2.tipo = Token.FLOAT;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column
				+"): encontrado ’"+this.t.lexema
				+"’, esperaba "+t1.toString()
				+" "+t2.toString());
			System.exit(-1);
		}
	}

	public void Bloque() {
		if(this.t.tipo == Token.LLAVEI) {
			emparejar(Token.LLAVEI);
			SecInstr();
			emparejar(Token.LLAVED);

			addRegla(17);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.LLAVEI;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column
				+"): encontrado ’"+this.t.lexema
				+"’, esperaba "+t1.toString());
			System.exit(-1);
		}
	}
INT
	public void SecInstr() {
		if(this.t.tipo == Token.INT || this.t.tipo == Token.FLOAT
			|| this.t.tipo == Token.ID || this.t.tipo == Token.LLAVEI
			|| this.t.tipo == Token.RETURN) {
			Instr();
			emparejar(Token.PYC);
			SecInstr();

			addRegla(18);
		} else if(this.t.tipo == Token.LLAVED) {
			addRegla(19);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.CLASS;
			Token t2 = new Token();
			t2.tipo = Token.ID;
			Token t3 = new Token();
			t3.tipo = Token.LLAVEI;
			Token t4 = new Token();
			t4.tipo = Token.INT;
			Token t5 = new Token();
			t5.tipo = Token.FLOAT;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column
				+"): encontrado ’"+this.t.lexema
				+"’, esperaba "+t1.toString()
				+" "+t2.toString()
				+" "+t3.toString()
				+" "+t4.toString()
				+" "+t5.toString());
			System.exit(-1);
		}		
	}

	public void Instr() {
		if(this.t.tipo == Token.INT || this.t.tipo == Token.FLOAT) {
			Tipo();
			emparejar(Token.id);

			addRegla(20);
		} else if(this.t.tipo == Token.ID) {
			emparejar(Token.ID);
			emparejar(Token.ASIG);
			Expr();

			addRegla(21);
		} else if(this.t.tipo == Token.LLAVEI) {
			Bloque();

			addRegla(22);
		} else if(this.t.tipo == Token.RETURN) {
			emparejar(Token.RETURN);
			Expr();

			addRegla(23);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.ID;
			Token t2 = new Token();
			t2.tipo = Token.LLAVEI;
			Token t3 = new Token();
			t3.tipo = Token.INT;
			Token t4 = new Token();
			t4.tipo = Token.FLOAT;
			Token t5 = new Token();
			t5.tipo = Token.RETURN;
			System.err.println("Error sintactico ("this.t.row+","+this.t.column
				+"): encontrado ’"+this.t.lexema
				+"’, esperaba "+t1.toString()
				+" "+t2.toString()
				+" "+t3.toString()
				+" "+t4.toString()
				+" "+t5.toString());
			System.exit(-1);
		}
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