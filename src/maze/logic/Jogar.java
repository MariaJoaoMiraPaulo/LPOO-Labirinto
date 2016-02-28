package maze.logic;

import maze.cli.CommandLineInterface;


public class Jogar {
	public Jogo jogo;
	public CommandLineInterface cli;

	public static void main(String[] args) {
		Jogar j1=new Jogar();
		j1.jogar();
	}

	Jogar(){
		jogo=new Jogo();
		cli=new CommandLineInterface();
		jogo.setModoJogo(cli.retornaModoJogo());
	}

	public void jogar(){
		boolean fimDeJogo=false;
		cli.imprimir(jogo.getTab().paraString());
		while(!fimDeJogo){
			fimDeJogo=jogo.jogada();
			cli.imprimir(jogo.getTab().paraString());
		}

		if(jogo.getDragao().isMorto())
			cli.imprimir("O jogador ganhou!!");
		else cli.imprimir("O jogador perdeu!!");
		
		cli.imprimir("Fim do Jogo!");
	}



}
