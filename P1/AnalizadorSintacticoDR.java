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

		this.t = a.siguienteToken();
	}

	//////////////
	// FUNCIONES DE NO TERMINALES
	//////////////

	//ORDEN DE TOKENS ESPERADOS:
	//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
	//coma, int, float, asig, return, addop, mulop, real, entero

	public void S() {
		//Una condicion por cada conjunto de prediccion
		//System.err.println("\tEntramos en S");

		if(this.t.tipo == Token.CLASS) {
			addRegla(1);

			C(); 	//LLamamos a su metodo para los no terminales
					//Si es terminal, llamamos a emparejar
		}
		else {
			Token t1 = new Token();
			t1.tipo = Token.CLASS;

			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1);
			}
			imprimirErrorSintactico(t1);
			System.exit(-1);
		}
	}	

	public void C() {
		//System.err.println("\tEntramos en C");

		if(this.t.tipo == Token.CLASS) {
			addRegla(2); //Regla 2

			emparejar(Token.CLASS);
			emparejar(Token.ID);
			emparejar(Token.LLAVEI);
			B();
			V();
			emparejar(Token.LLAVED);
		}
		else {
			Token t1 = new Token();
			t1.tipo = Token.CLASS;
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1);
			}
			imprimirErrorSintactico(t1);
			System.exit(-1);
		}
	}

	public void B() {
		//System.err.println("\tEntramos en B");

		if(this.t.tipo == Token.PUBLIC) {
			addRegla(3); //Regla 3

			emparejar(Token.PUBLIC);
			emparejar(Token.DOSP);
			P();
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
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1,t2,t3);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1,t2,t3);
			System.exit(-1);
		}
	}

	public void V() {
		//System.err.println("\tEntramos en V");

		if(this.t.tipo == Token.PRIVATE) {
			addRegla(5);

			emparejar(Token.PRIVATE);
			emparejar(Token.DOSP);
			P();	
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
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1, t2);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1, t2);
			System.exit(-1);
		}
	}

	public void P() {
		//System.err.println("\tEntramos en P");

		if(this.t.tipo == Token.INT || this.t.tipo == Token.FLOAT 
			|| this.t.tipo == Token.CLASS) {
			addRegla(7);


			D();
			P();
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
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1, t2,t3,t4,t5);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1, t2,t3,t4,t5);
			System.exit(-1);
		}
		
	}

	public void D() {
		//System.err.println("\tEntramos en D");

		if(this.t.tipo == Token.INT || this.t.tipo == Token.FLOAT) {
			addRegla(9);

			Tipo();
			emparejar(Token.ID);
			emparejar(Token.PARI);
			Tipo();
			emparejar(Token.ID);
			L();
			emparejar(Token.PARD);
			Cod();
		}else if(this.t.tipo == Token.CLASS) {
			addRegla(10);

			C();
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
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1, t2,t3);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1, t2,t3);
			System.exit(-1);
		}
	}

	public void Cod() {
		//System.err.println("\tEntramos en Cod");

		if(this.t.tipo == Token.PYC) {
			addRegla(11);

			emparejar(Token.PYC);
		} else if(this.t.tipo == Token.LLAVEI) {
			addRegla(12);

			Bloque();
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.LLAVEI;
			Token t2 = new Token();
			t2.tipo = Token.PYC;
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1,t2);
			}
			imprimirErrorSintactico(t1,t2);
			System.exit(-1);
		}
	}

	public void L() {
		//System.err.println("\tEntramos en L");

		if(this.t.tipo == Token.COMA) {
			addRegla(13);

			emparejar(Token.COMA);
			Tipo();
			emparejar(Token.ID);
			L();
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
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1, t2);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1, t2);
			System.exit(-1);
		}
	}

	public void Tipo() {
		//System.err.println("\tEntramos en Tipo");

		if(this.t.tipo == Token.INT) {
			addRegla(15);

			emparejar(Token.INT);
		} else if(this.t.tipo == Token.FLOAT) {
			addRegla(16);

			emparejar(Token.FLOAT);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.INT;
			Token t2 = new Token();
			t2.tipo = Token.FLOAT;
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1, t2);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1, t2);
			System.exit(-1);
		}
	}

	public void Bloque() {
		//System.err.println("\tEntramos en Bloque");

		if(this.t.tipo == Token.LLAVEI) {
			addRegla(17);

			emparejar(Token.LLAVEI);
			SecInstr();
			emparejar(Token.LLAVED);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.LLAVEI;
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1);
			System.exit(-1);
		}
	}

	public void SecInstr() {
		//System.err.println("\tEntramos en SecInstr");

		if(this.t.tipo == Token.INT || this.t.tipo == Token.FLOAT
			|| this.t.tipo == Token.ID || this.t.tipo == Token.LLAVEI
			|| this.t.tipo == Token.RETURN) {
			addRegla(18);

			Instr();
			emparejar(Token.PYC);
			SecInstr();	
		} else if(this.t.tipo == Token.LLAVED) {
			addRegla(19);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.ID;
			Token t2 = new Token();
			t2.tipo = Token.LLAVEI;
			Token t3 = new Token();
			t3.tipo = Token.LLAVED;
			Token t4 = new Token();
			t4.tipo = Token.INT;
			Token t5 = new Token();
			t5.tipo = Token.FLOAT;
			Token t6 = new Token();
			t6.tipo = Token.RETURN;
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1, t2, t3, t4, t5, t6);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1, t2, t3, t4, t5, t6);
			System.exit(-1);
		}		
	}

	public void Instr() {
		//System.err.println("\tEntramos en Instr");

		if(this.t.tipo == Token.INT || this.t.tipo == Token.FLOAT) {
			addRegla(20);

			Tipo();
			emparejar(Token.ID);
		} else if(this.t.tipo == Token.ID) {
			addRegla(21);

			emparejar(Token.ID);
			emparejar(Token.ASIG);
			Expr();
		} else if(this.t.tipo == Token.LLAVEI) {
			addRegla(22);

			Bloque();
		} else if(this.t.tipo == Token.RETURN) {
			addRegla(23);

			emparejar(Token.RETURN);
			Expr();
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
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1, t2, t3, t4, t5);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1, t2, t3, t4, t5);
			System.exit(-1);
		}
	}

	public void Expr() {
		//System.err.println("\tEntramos en Expr");

		if(this.t.tipo == Token.REAL || this.t.tipo == Token.ENTERO || this.t.tipo == Token.ID
			|| this.t.tipo == Token.PARI) {
			addRegla(24);

			Term();
			ExprPr();	
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.ID;
			Token t2 = new Token();
			t2.tipo = Token.PARI;
			Token t3 = new Token();
			t3.tipo = Token.REAL;
			Token t4 = new Token();
			t4.tipo = Token.ENTERO;
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1, t2, t3, t4);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1, t2, t3, t4);
			System.exit(-1);
		}
	}

	public void ExprPr() {
		//System.err.println("\tEntramos en ExpPr");

		if(this.t.tipo == Token.ADDOP) {
			addRegla(25);

			emparejar(Token.ADDOP);
			Term();
			ExprPr();

			
		} else if(this.t.tipo == Token.PYC || this.t.tipo == Token.PARD) {
			addRegla(26);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.PARD;
			Token t2 = new Token();
			t2.tipo = Token.PYC;
			Token t3 = new Token();
			t3.tipo = Token.ADDOP;
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1, t2, t3);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1, t2, t3);
			System.exit(-1);
		}
	}

	public void Term() {
		//System.err.println("\tEntramos en Term");

		if(this.t.tipo == Token.REAL || this.t.tipo == Token.ENTERO || this.t.tipo == Token.ID
			|| this.t.tipo == Token.PARI) {
			addRegla(27);

			Factor();
			TermPr();	
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.ID;
			Token t2 = new Token();
			t2.tipo = Token.PARI;
			Token t3 = new Token();
			t3.tipo = Token.REAL;
			Token t4 = new Token();
			t4.tipo = Token.ENTERO;
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1, t2, t3, t4);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1, t2, t3, t4);
			System.exit(-1);
		}
	}

	public void TermPr() {
		//System.err.println("\tEntramos en TermPr");

		if(this.t.tipo == Token.MULOP) {
			addRegla(28);


			emparejar(Token.MULOP);
			Factor();
			TermPr();
		} else if(this.t.tipo == Token.ADDOP || this.t.tipo == Token.PARD || this.t.tipo == Token.PYC) {
			addRegla(29);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.PARD;
			Token t2 = new Token();
			t2.tipo = Token.PYC;
			Token t3 = new Token();
			t3.tipo = Token.ADDOP;
			Token t4 = new Token();
			t4.tipo = Token.MULOP;
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1, t2, t3, t4);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1, t2, t3, t4);
			System.exit(-1);
		}
	}

	public void Factor() {
		//System.err.println("\tEntramos en Factor");

		if(this.t.tipo == Token.REAL) {
			addRegla(30);

			emparejar(Token.REAL);
		} else if(this.t.tipo == Token.ENTERO) {
			addRegla(31);

			emparejar(Token.ENTERO);
		} else if(this.t.tipo == Token.ID) {
			addRegla(32);

			emparejar(Token.ID);
		} else if(this.t.tipo == Token.PARI) {
			addRegla(33);

			emparejar(Token.PARI);
			Expr();
			emparejar(Token.PARD);
		} else {
			//Orden tokens error:
			//class, id, llavei, llaved, public, dosp, private, pari, pard, pyc, 
			//coma, int, float, asig, return, addop, mulop, real, entero
			Token t1 = new Token();
			t1.tipo = Token.ID;
			Token t2 = new Token();
			t2.tipo = Token.PARI;
			Token t3 = new Token();
			t3.tipo = Token.REAL;
			Token t4 = new Token();
			t4.tipo = Token.ENTERO;
			if(this.t.tipo == Token.EOF) {
				imprimirErrorSintacticoFinFicheroInesperado(t1, t2, t3, t4);
				System.exit(-1);
			}
			imprimirErrorSintactico(t1.toString(), t2.toString(),t3.toString(), t4.toString());
			System.exit(-1);
		}
	}

	//////////////////////////

	public void comprobarFinFichero() {
		if(this.t.tipo == Token.EOF) { //Fin de fichero
			if(this.imprimir) {
				imprimirReglas();
			}
		}
		else {
			imprimirErrorSintactico("eof");
			System.exit(-1);
		}
	}

	public final void emparejar(int tokEsperado) {
		//System.out.println("\t\tEmparejando...");

		if(this.t.tipo == tokEsperado) {
			this.t = this.al.siguienteToken();
			//System.out.println("\t\tNuevo token leido.");
		}
		else {
			Token t2 = new Token();
			t2.tipo = tokEsperado;
			imprimirErrorSintactico(t2);
			System.exit(-1);
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

	private void imprimirErrorSintactico(Token... tokens) {
		System.err.print("Error sintactico ("+this.t.fila+","+this.t.columna
				+"): encontrado '"+this.t.lexema
				+"', esperaba");

		for(Token s: tokens) {
			System.err.print(" "+s.toString());
		}
		System.err.println();
	}

	private void imprimirErrorSintacticoFinFicheroInesperado(Token... tokens) {

		System.err.print("Error sintactico: entoncrado fin de fichero, esperaba");
		for(Token s: tokens) {
			System.err.print(" "+s.toString());
		}
		System.err.println();
	}

	private void imprimirErrorSintactico(String... tokens) {

		System.err.print("Error sintactico: entoncrado fin de fichero, esperaba");
		for(String s: tokens) {
			System.err.print(" "+s);
		}
		System.err.println();
	}

	private void imprimirErrorSintacticoFinFicheroInesperado(String... tokens) {

		System.err.print("Error sintactico: entoncrado fin de fichero, esperaba");
		for(String s: tokens) {
			System.err.print(" "+s);
		}
		System.err.println();
	}
}