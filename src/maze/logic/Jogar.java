package maze.logic;

import maze.cli.CommandLineInterface;
import maze.logic.Dragao.EstadoDragao;


public class Jogar {
	public Jogo jogo;
	public CommandLineInterface cli;

	public static void main(String[] args) {
		Jogar j1=new Jogar();
		j1.jogar();
	}

	/**
	 * Inicializa um novo jogo pela consola
	 */
	Jogar(){
		jogo=new Jogo();
		cli=new CommandLineInterface();
		jogo.setModoJogo(cli.retornaModoJogo());   
	}

	/**
	 * Verifica o estado do Jogo. Ciclo que permite jogar pela consola
	 */
	public void jogar(){
		boolean fimDeJogo=false;
		
		cli.imprimir(jogo.getTab().paraString());
		while(!fimDeJogo){
			fimDeJogo=jogo.jogada(cli.lerDirecao());
			cli.imprimir(jogo.getTab().paraString());
		}

		if(!jogo.dragoesVivos())
			cli.imprimir("O jogador ganhou!!");
		else cli.imprimir("O jogador perdeu!!");
		
		cli.imprimir("Fim do Jogo!");
	}



}
