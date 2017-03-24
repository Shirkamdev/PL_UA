import java.util.List;

/**
 * Code created by shirkam on 23/03/17
 * Project name: PLP2
 * Package: PACKAGE_NAME
 * Created by shirkam on 23/03/17.
 */
public class TablaTerminales {
	//EL orden de aparicion de los tokens ha de ser el siguiente:
	//pari, pard, pyc, dosp, coma, asig, llavei, llaved, class, public, private, float, int, entero, id, real

	/**
	 * Index: El numero de regla a la que se refiere.
	 * Este array almacena las operaciones que puede realizar un tipoToken, sus transiciones.
	 * Solo almacena aquellas transiciones realizadas con no terminales.
	 */
    public static final ListaOperacionesTerminales reglas[];

	/**
	 * Devuelve en un string los nombres de los tokens esperados para una cierta regla.
	 * @param index La regla que se se desea comprobar
	 * @return String con los tokens esperados para la regla dada
	 */
	public static String getTokensEsperados(int index) {
		if(index < 0 || index >= reglas.length)
			return "";

		List<Token> lista = reglas[index].getTokensEsperados();
		String result="";

		for(Token t: lista) {
			result += " "+t.toString();
		}

		return result;
	}

	/**
	 * Comprueba si un token se esparaba para una determinada regla.
	 * Para ello, comprieba si el tipo del token que se le pasa coincide
	 * con alguno de los esperados.
	 * @param t El token a comprobar
	 * @param index El indice de la regla que se desea comprobar
	 * @return Si el token se espera para esa regla o no
	 */
	public static boolean isExpected(Token t, int index) {
    	if(index < 0 || index >= reglas.length)
    		return false;

    	List<Token> lista = reglas[index].getTokensEsperados();
    	for(Token t2: lista) {
    		if(t2.tipo == t.tipo)
    			return true;
		}

		return false;
	}

	/**
	 * Devuelve el tipo de operacion que se realiza al leer el token dado en la regla especificada.
	 * En caso de no encontrar ninguna, devuelve 'z'.
	 * @param t El token que determina la operacion a realizar
	 * @param index El indice de la regla a consultar
	 * @return El char representativo de la operacion (d, r, a) o 'z' en caso de no encontrar el token.
	 */
	public static OperacionTerminales getOperacion(Token t, int index) {
		if(index < 0 || index >= reglas.length) {
			OperacionTerminales o =  new OperacionTerminales('z', -1, Token.ERROR);
			return o;
		}

		List<OperacionTerminales> lista = reglas[index].getOperaciones();

		for(OperacionTerminales o: lista) {
			if(o.tipoToken == t.tipo)
				return o;
		}

		OperacionTerminales o =  new OperacionTerminales('z', -1, Token.ERROR);
		return o;
	}


		static {
			reglas = new ListaOperacionesTerminales[]{
				new ListaOperacionesTerminales( //0
					new OperacionTerminales('d', 3, Token.CLASS)
				)
				, new ListaOperacionesTerminales( //1
					new OperacionTerminales('a', -1, Token.EOF)
				)
				, new ListaOperacionesTerminales( //2
					new OperacionTerminales('r', 1, Token.EOF)
				)
				, new ListaOperacionesTerminales( //3
					new OperacionTerminales('d', 4, Token.ID)
				)
				, new ListaOperacionesTerminales( //4
					new OperacionTerminales('d', 5, Token.LLAVEI)
				)
				, new ListaOperacionesTerminales( //5
					new OperacionTerminales('r', 4, Token.LLAVED)
					, new OperacionTerminales('d', 7, Token.PUBLIC)
					, new OperacionTerminales('r', 4, Token.PRIVATE)
				)
				, new ListaOperacionesTerminales( //6
					new OperacionTerminales('r', 6, Token.LLAVED)
					, new OperacionTerminales('d', 9, Token.PRIVATE)
				)
				, new ListaOperacionesTerminales( //7
					new OperacionTerminales('d', 10, Token.DOSP)
				)
				, new ListaOperacionesTerminales( //8
					new OperacionTerminales('d', 11, Token.LLAVED)
				)
				, new ListaOperacionesTerminales( //9
					new OperacionTerminales('d', 12, Token.DOSP)
				)
				, new ListaOperacionesTerminales( //10
					new OperacionTerminales('r', 8, Token.LLAVED)
					, new OperacionTerminales('d', 3, Token.CLASS)
					, new OperacionTerminales('r', 8, Token.PRIVATE)
					, new OperacionTerminales('d', 20, Token.FLOAT)
					, new OperacionTerminales('d', 19, Token.INT)
				)
				, new ListaOperacionesTerminales( //11
					new OperacionTerminales('r', 2, Token.LLAVED)
					, new OperacionTerminales('r', 2, Token.CLASS)
					, new OperacionTerminales('r', 2, Token.PRIVATE)
					, new OperacionTerminales('r', 2, Token.FLOAT)
					, new OperacionTerminales('r', 2, Token.INT)
					, new OperacionTerminales('r', 2, Token.EOF)
				)
				, new ListaOperacionesTerminales( //12
					new OperacionTerminales('r', 8, Token.LLAVED)
					, new OperacionTerminales('d', 3, Token.CLASS)
					, new OperacionTerminales('r', 8, Token.PRIVATE)
					, new OperacionTerminales('d', 20, Token.FLOAT)
					, new OperacionTerminales('d', 19, Token.INT)
				)
				, new ListaOperacionesTerminales( //13
					new OperacionTerminales('r', 3, Token.LLAVED)
					, new OperacionTerminales('r', 3, Token.PRIVATE)
				)
				, new ListaOperacionesTerminales( //14
					new OperacionTerminales('r', 8, Token.LLAVED)
					, new OperacionTerminales('d', 13, Token.CLASS)
					, new OperacionTerminales('r', 8, Token.PRIVATE)
					, new OperacionTerminales('d', 20, Token.FLOAT)
					, new OperacionTerminales('d', 19, Token.INT)
				)
				, new ListaOperacionesTerminales( //15
					new OperacionTerminales('r', 5, Token.LLAVED)
				)
				, new ListaOperacionesTerminales( //16
					new OperacionTerminales('r', 7, Token.LLAVED)
					, new OperacionTerminales('r', 7, Token.PRIVATE)
				)
				, new ListaOperacionesTerminales( //17
					new OperacionTerminales('d', 21, Token.ID)
				)
				, new ListaOperacionesTerminales( //18
					new OperacionTerminales('r', 10, Token.LLAVED)
					, new OperacionTerminales('r', 10, Token.CLASS)
					, new OperacionTerminales('r', 10, Token.PRIVATE)
					, new OperacionTerminales('r', 10, Token.FLOAT)
					, new OperacionTerminales('r', 10, Token.INT)
				)
				, new ListaOperacionesTerminales( //19
					new OperacionTerminales('r', 15, Token.ID)
				)
				, new ListaOperacionesTerminales( //20
					new OperacionTerminales('r', 16, Token.ID)
				)
				, new ListaOperacionesTerminales( //21
					new OperacionTerminales('d', 22, Token.PARI)
				)
				, new ListaOperacionesTerminales( //22
					new OperacionTerminales('d', 20, Token.FLOAT)
					, new OperacionTerminales('d', 19, Token.INT)
				)
				, new ListaOperacionesTerminales( //23
					new OperacionTerminales('d', 24, Token.ID)
				)
				, new ListaOperacionesTerminales( //24
					new OperacionTerminales('r', 14, Token.PARD)
					, new OperacionTerminales('d', 26, Token.COMA)
				)
				, new ListaOperacionesTerminales( //25
						new OperacionTerminales('d', 27, Token.PARD)
				)
				, new ListaOperacionesTerminales( //26
					new OperacionTerminales('d', 20, Token.FLOAT)
					, new OperacionTerminales('d', 19, Token.INT)
				)
				, new ListaOperacionesTerminales( //27
					new OperacionTerminales('d', 29, Token.PYC)
					, new OperacionTerminales('d', 31, Token.LLAVEI)
				)
				, new ListaOperacionesTerminales( //28
					new OperacionTerminales('d', 32, Token.ID)
				)
				, new ListaOperacionesTerminales( //29
					new OperacionTerminales('r', 11, Token.LLAVED)
					, new OperacionTerminales('r', 11, Token.CLASS)
					, new OperacionTerminales('r', 11, Token.PRIVATE)
					, new OperacionTerminales('r', 11, Token.FLOAT)
					, new OperacionTerminales('r', 11, Token.INT)
				)
				, new ListaOperacionesTerminales( //30
					new OperacionTerminales('r', 12, Token.LLAVED)
					, new OperacionTerminales('r', 12, Token.CLASS)
					, new OperacionTerminales('r', 12, Token.PRIVATE)
					, new OperacionTerminales('r', 12, Token.FLOAT)
					, new OperacionTerminales('r', 12, Token.INT)
				)
				, new ListaOperacionesTerminales( //31
					new OperacionTerminales('d', 31, Token.LLAVEI)
					, new OperacionTerminales('r', 19, Token.LLAVED)
					, new OperacionTerminales('d', 37, Token.ID)
				)
				, new ListaOperacionesTerminales( //32
					new OperacionTerminales('r', 14, Token.PARD)
					, new OperacionTerminales('d', 26, Token.COMA)
				)
				, new ListaOperacionesTerminales( //33
					new OperacionTerminales('r', 13, Token.PARD)
				)
				, new ListaOperacionesTerminales( //34
					new OperacionTerminales('r', 9, Token.LLAVED)
					, new OperacionTerminales('r', 9, Token.CLASS)
					, new OperacionTerminales('r', 9, Token.PRIVATE)
					, new OperacionTerminales('r', 9, Token.FLOAT)
					, new OperacionTerminales('r', 9, Token.INT)

				)
				, new ListaOperacionesTerminales( //36
					new OperacionTerminales('d', 39, Token.LLAVED)
				)
				, new ListaOperacionesTerminales( //36
					new OperacionTerminales('d', 45, Token.PYC)
				)
				, new ListaOperacionesTerminales( //37
					new OperacionTerminales('d', 40, Token.ASIG)
				)
				, new ListaOperacionesTerminales( //38
					new OperacionTerminales('r', 21, Token.PYC)
				)
				, new ListaOperacionesTerminales( //39
					new OperacionTerminales('r', 17, Token.LLAVED)
					, new OperacionTerminales('r', 17, Token.CLASS)
					, new OperacionTerminales('r', 17, Token.PRIVATE)
					, new OperacionTerminales('r', 17, Token.FLOAT)
					, new OperacionTerminales('r', 17, Token.INT)
				)
				, new ListaOperacionesTerminales( //40
					new OperacionTerminales('d', 41, Token.REAL)
					, new OperacionTerminales('d', 36, Token.ID)
					, new OperacionTerminales('d', 43, Token.ENTERO)
				)
				, new ListaOperacionesTerminales( //41
					new OperacionTerminales('r', 22, Token.PYC)
				)
				, new ListaOperacionesTerminales(  //42
						new OperacionTerminales('r', 20, Token.PYC)
				)
				, new ListaOperacionesTerminales(  //43
					new OperacionTerminales('r', 23, Token.PYC)
				)
				, new ListaOperacionesTerminales(  //44
					new OperacionTerminales('r', 18, Token.LLAVED)
				)
				, new ListaOperacionesTerminales(  //45
					new OperacionTerminales('d', 31, Token.LLAVEI)
					, new OperacionTerminales('r', 19, Token.LLAVED)
					, new OperacionTerminales('d', 37, Token.ID)
				)
				, new ListaOperacionesTerminales(  //46
					new OperacionTerminales('r', 24, Token.PYC)
				)
			};
		}
}
