package maze.cli;

import java.awt.Point;
import java.util.Scanner;
import maze.logic.Jogo.*;

public class CommandLineInterface {
	public CommandLineInterface() {
		
	}
	
	public void imprimir(String aEscrever){
		System.out.println(aEscrever);
	}
	
	public int retornaNumeroDragoes(){
		imprimir("Com quantos dragoes pretende jogar? (Maximo 5)");
		Scanner ler = new Scanner(System.in);
		int numero;
		numero=ler.nextInt();
		while(numero>5){
			imprimir("Demasiados Dragoes para jogar! Com quantos dragoes pretende jogar?");
			numero=ler.nextInt(); 
		}
		return numero;
	}
	
	public int retornaTamanhoTabuleiro(){
		imprimir("Qual é a altura tabuleiro com que pretende jogar?");
		Scanner ler=new Scanner(System.in);
		int numero=ler.nextInt();
		while(numero%2==0){
			imprimir("A altura do tabuleiro tem de ser ímpar!");
			numero=ler.nextInt();
		}
		return numero;
	}
	
	public Point retornaCoordenadaDragao(){
		int x,y;
		Scanner ler = new Scanner(System.in);
		imprimir("Qual a coordenada x do Dragao?");
		x=ler.nextInt();
		imprimir("Qual a coordenada y do Dragao?");
		y=ler.nextInt();
		Point ponto= new Point(x,y);
		return ponto;
		
	}

	public int retornaModoJogo(){
		
		imprimir("Com que modo de Jogo pretende jogar?");
		imprimir("1.Dragao Parado");
		imprimir("2.Dragao com movimentacao aleatoria");
		imprimir("3.Dragao com movimenta�cao aleatoria intercalada com dormir");
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
