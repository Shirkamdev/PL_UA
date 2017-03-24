/**
 * Code created by shirkam on 23/03/17
 * Project name: PLP2
 * Package: PACKAGE_NAME
 * Created by shirkam on 23/03/17.
 */
public class Token {
    public int fila;
    public int columna;
    public String lexema;
    public int tipo;

    public static final int
            PARI	=0,
            PARD	=1,
            PYC		=2,
            DOSP	=3,
            COMA 	=4,
            ASIG	=5,
            LLAVEI	=6,
            LLAVED	=7,
            CLASS 	=8,
            PUBLIC 	=9,
            PRIVATE =10,
            FLOAT	=11,
            INT 	=12,
            ENTERO	=13,
            ID		=14,
            REAL	=15,
            EOF		=16,
            ERROR   =-1;

    public Token() {
        this.fila =0;
        this.columna =0;
        this.lexema="";
        this.tipo=-1;
    }

    public Token(int t) {
        this.tipo = t;
    }

    public Token(int f, int c, String lex, int t) {
        this.fila = f;
        this.columna = c;
        this.lexema = lex;
        this.tipo = t;
    }

    public String toString() {
        switch (this.tipo) {
            case PARI:
                return "(";

            case PARD:
                return ")";

            case PYC:
                return ";";

            case DOSP:
                return ":";

            case COMA:
                return ",";

            case ASIG:
                return "=";

            case LLAVEI:
                return "{";

            case LLAVED:
                return "}";

            case CLASS:
                return "'class'";

            case PUBLIC:
                return "'public'";

            case PRIVATE:
                return "'private'";

            case FLOAT:
                return "'float'";

            case INT:
                return "'int'";

            case ENTERO:
                return "numero entero";

            case ID:
                return "identificador";

            case REAL:
                return "numero real";

            case EOF:
                return "EOF";

            case -1:
                return "ERROR";

        }

        return "ERROR";
    }

    public static String getName(int i) {
        switch(i) {
            case PARI:
                return "(";

            case PARD:
                return ")";

            case PYC:
                return ";";

            case DOSP:
                return ":";

            case COMA:
                return ",";

            case ASIG:
                return "=";

            case LLAVEI:
                return "{";

            case LLAVED:
                return "}";

            case CLASS:
                return "'class'";

            case PUBLIC:
                return "'public'";

            case PRIVATE:
                return "'private'";

            case FLOAT:
                return "'float'";

            case INT:
                return "'int'";

            case ENTERO:
                return "numero entero";

            case ID:
                return "identificador";

            case REAL:
                return "numero real";

            case EOF:
                return "EOF";

            case -1:
                return "ERROR";

        }

        return "ERROR";
    }

}