import java.io.RandomAccessFile;

public class AnalizadorLexico {
	private RandomAccessFile f;
	private int estado;

	public AnalizadorLexico(RandomAccessFile f) {
		this.f = f;
		this.estado = 0;
	}


	//Recibe el estado actual y el caracter que hemos leido
	private void delta(int estado, int c) {
		switch(this.estado) {
			//Estado base
			case 0:
				//Si es un digito
				if(c >= '0' && c <= '9') {
					this.estado = 1;
				}
				else if((c >= 'a' && c <= 'z') || (c >= 'A' || c <= 'Z')) {
					this.estado = 7;
				}
				else if(c == '*') {
					this.estado = 9;
				}
				else if(c == '/') {
					this.estado = 10;
				}
				break;
			//Estado digitos enteros
			case 1:
				break;
			//Estado separador decimal
			case 2:
				break;
			//Estado final error separador, numero entero
			case 3:
				break;
			//Estado numero real
			case 4:
				break;
			//Estado final numero real
			case 5:
				break;
			//Estado final numero entero
			case 6:
				break;
			//Estado identificador
			case 7:
				break;
			//Estado final identificador
			case 8:
				break;
			//Estado final mulop
			case 9:
				break;
			//Estado /
			case 10:
				break;
			//Estado comentario
			case 11:
				break;
			//Estado * en comentario
			case 12:
				break;
			//Estado (
			case 13:
				break;
			//Estado )
			case 14:
				break;
			//Estado + -
			case 15:
				break;
			//Estado ;
			case 16:
				break;
			//Estado :
			case 17:
				break;
			//Estado ,
			case 18:
				break;
			//Estado =
			case 19:
				break;
			//Estado {
			case 20:
				break;
			//Estado }
			case 21:
				break;
		}

		//TODO if(lexema == PALABRA_RESERVADA)

	}
}