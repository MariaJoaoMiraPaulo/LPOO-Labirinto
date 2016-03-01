package maze.cli;

import java.util.Scanner;
import maze.logic.Jogo.*;

public class CommandLineInterface {
	public CommandLineInterface() {
		
	}
	
	public void imprimir(String aEscrever){
		System.out.println(aEscrever);
	}
	
	public int retornaModoJogo(){
		
		imprimir("Com que modo de Jogo pretende jogar?");
		imprimir("1.Dragao Parado");
		imprimir("2.Dragao com movimentaçao aleatoria");
		imprimir("3.Dragao com movimentaçao aleatoria intercalada com dormir");
		int modo;
		Scanner ler = new Scanner(System.in);
		modo=ler.nextInt();
		while (modo!=1 && modo!=2 && modo!=3)
		{
			imprimir("Introduza um modo de jogo valido (1,2,3) ");
			modo=ler.nextInt();
		}
		return modo;
	}
	
	public Movimento lerDirecao(){
		
		imprimir("Para onde pretende mover? (e,d,b,c)");
		char c;
		Movimento direcao=Movimento.CIMA;
		Scanner ler = new Scanner (System.in);

		c = ler.next().charAt(0); 
		c=Character.toLowerCase(c);

		while(c!='e' && c!='d' && c!='c' && c!='b'){ //|| c!='E' || c!='D' || c!='C' || c!='B'

			imprimir("Introduza uma entrada valida (e,d,c,b) ");
			c = ler.next().charAt(0); 
			c=Character.toLowerCase(c);
		}
		
		switch(c){
		case 'e':
			direcao=Movimento.ESQUERDA;
			break;
		case 'd':
			direcao=Movimento.DIREITA;
			break;
		case 'b':
			direcao=Movimento.BAIXO;
			break;
		case 'c':
			direcao=Movimento.CIMA;
			break;
		default:
			break;
			}

		return direcao;
	}
	
}
