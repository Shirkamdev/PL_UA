import java.util.List;

/**
 * Code created by shirkam on 24/03/17
 * Project name: PLP2
 * Package: PACKAGE_NAME
 * Created by shirkam on 24/03/17.
 */
public class TablaNoTerminales {
    public static final int
        S       =0,
        C       =1,
        B       =2,
        V       =3,
        P       =4,
        D       =5,
        Cod     =6,
        L       =7,
        Tipo    =8,
        Bloque  =9,
        SecInstr=10,
        Instr   =11,
        Expr    =12;

    public static final int[] longitudReglas =
        {1, 6, 3, 0, 3, 0, 2, 0, 8, 1, 1, 1, 4, 0, 1, 1, 3, 3, 0, 3, 1, 1, 1, 1};

    public static final int[] noTerminalesReglas =
        {S, C, B, B, V, V, P, P, D, D, Cod, Cod, L, L, Tipo, Tipo, Bloque, SecInstr, SecInstr, Instr, Instr, Expr, Expr, Expr};

    public static final ListaOperacionesNoTerminales[] ir_a;


    public static int ir_a(int r, int noTerminal) {
        if(ir_a[r] == null )
            return -1;

        List<OperacionNoTerminal> lista = ir_a[r].getOperaciones();

        for(OperacionNoTerminal o : lista) {
            if(o.noTerminal == noTerminal)
                return o.nuevaRegla;
        }

        return -1;
    }


    static {
        ir_a = new ListaOperacionesNoTerminales[]{
                new ListaOperacionesNoTerminales( //0
                        new OperacionNoTerminal(S, 1),
                        new OperacionNoTerminal(C, 2)
                )
                , null //1
                , null //2
                , null //3
                , null //4
                , new ListaOperacionesNoTerminales( //5
                new OperacionNoTerminal(B, 6)
        )
                , new ListaOperacionesNoTerminales( //6
                new OperacionNoTerminal(V, 8)
        )
                , null //7
                , null //8
                , null //9
                , new ListaOperacionesNoTerminales( //10
                new OperacionNoTerminal(C, 18),
                new OperacionNoTerminal(P, 13),
                new OperacionNoTerminal(D, 14),
                new OperacionNoTerminal(Tipo, 17)
        )
                , null //11
                , new ListaOperacionesNoTerminales( //12
                new OperacionNoTerminal(C, 18),
                new OperacionNoTerminal(P, 15),
                new OperacionNoTerminal(D, 14),
                new OperacionNoTerminal(Tipo, 17)
        )
                , null //13
                , new ListaOperacionesNoTerminales( //14
                new OperacionNoTerminal(C, 18),
                new OperacionNoTerminal(P, 16),
                new OperacionNoTerminal(D, 14),
                new OperacionNoTerminal(Tipo, 17)
        )
                , null //15
                , null //16
                , null //17
                , null //18
                , null //19
                , null //20
                , null //21
                , new ListaOperacionesNoTerminales( //22
                new OperacionNoTerminal(Tipo, 23)
        )
                , null //23
                , new ListaOperacionesNoTerminales( //24
                new OperacionNoTerminal(L, 25)
        )
                , null //25
                , new ListaOperacionesNoTerminales( //26
                new OperacionNoTerminal(Tipo, 28)
        )
                , new ListaOperacionesNoTerminales( //27
                new OperacionNoTerminal(Cod, 34),
                new OperacionNoTerminal(Bloque, 30)
        )
                , null //28
                , null //29
                , null //30
                , new ListaOperacionesNoTerminales( //31
                new OperacionNoTerminal(Bloque, 38),
                new OperacionNoTerminal(SecInstr, 35),
                new OperacionNoTerminal(Instr, 36)
        )
                , new ListaOperacionesNoTerminales( //32
                new OperacionNoTerminal(L, 33)
        )
                , null //33
                , null //34
                , null //35
                , null //36
                , null //37
                , null //38
                , null //39
                , new ListaOperacionesNoTerminales( //40
                new OperacionNoTerminal(Expr, 42)
        )
                , null //41
                , null //42
                , null //43
                , null //44
                , new ListaOperacionesNoTerminales( //45
                new OperacionNoTerminal(Bloque, 38),
                new OperacionNoTerminal(SecInstr, 44),
                new OperacionNoTerminal(Instr, 36)
        )
                , null //46

        };
    }

}
