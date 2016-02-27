package maze.cli;

import java.util.Scanner;

public class CommandLineInterface {
	public CommandLineInterface() {
		
	}
	
	public void imprimir(String aEscrever){
		System.out.println(aEscrever);
	}
	
	public char lerDirecao(){
		System.out.println("Para onde pretende mover? (e,d,b,c)");
		char c;
		Scanner ler = new Scanner (System.in);

		c = ler.next().charAt(0); 
		c=Character.toLowerCase(c);

		while(c!='e' && c!='d' && c!='c' && c!='b'){ //|| c!='E' || c!='D' || c!='C' || c!='B'

			System.out.println("Introduza uma entrada valida (e,d,c,b) ");
			c = ler.next().charAt(0); 
			c=Character.toLowerCase(c);
		}

		//ler.close();

		return c;
	}
}
