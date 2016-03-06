package maze.test;
import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;
import maze.logic.*;
import maze.logic.Dragao.EstadoDragao;
import maze.logic.Jogo.Movimento;

public class TestMazeWithStaticDragon {

	@Test
	public void testeHeroiParaUmaPosicaoLivre() {
		Jogo j= new Jogo();
		assertEquals(new Point(1,1), j.getHeroi().getP());
		j.getHeroi().moverDireita();
		assertEquals(new Point(1,2), j.getHeroi().getP());
	}

	@Test
	public void testeHeroiContraUmaParede(){
		Jogo j= new Jogo();
		assertEquals(new Point(1,1), j.getHeroi().getP());
		if(j.podeMoverHeroi(Movimento.ESQUERDA))
			j.getHeroi().moverEsquerda();
		assertEquals(new Point(1,1), j.getHeroi().getP());
	}

	@Test
	public void testaHeroiApanharEspada(){
		Jogo j= new Jogo();
		assertEquals('H', j.getHeroi().getSimbolo());
		j.getHeroi().moverBaixo();
		j.getHeroi().moverBaixo();
		j.getHeroi().moverBaixo();
		j.verificaEspada();
		assertEquals('A', j.getHeroi().getSimbolo());
	}

	@Test
	public void testaHeroiContaDragaoDesarmado(){
		Jogo j= new Jogo();
		Dragao d=new Dragao(1,2,'D');
		j.dragoes.add(d);
		Point p=new Point(1,2);

		assertEquals(false,j.isFimDeJogo());
		j.getTab().inserirChar(p, d.getSimbolo());
		j.verificaDragao();
		assertEquals(true,j.isFimDeJogo());
	}

	@Test
	public void testaHeroiContaDragaoArmado(){
		Jogo j= new Jogo();
		Dragao d=new Dragao(5,1,'D');
		j.dragoes.add(d);
		Point p=new Point(1,2);

		assertEquals(EstadoDragao.ACORDADO,d.getEstado());
		j.getHeroi().moverBaixo();
		j.getHeroi().moverBaixo();
		j.getHeroi().moverBaixo();
		j.verificaEspada();
		j.verificaDragao();
		assertEquals(EstadoDragao.MORTO,d.getEstado());
	}

	@Test
	public void testaHeroiGanharJogo(){
		Jogo j= new Jogo();
		Dragao d=new Dragao(5,1,'D');
		j.dragoes.add(d);
		Point p=new Point(1,2);

		assertEquals(false, j.isFimDeJogo());
		for(int i=0;i<3;i++)
			j.getHeroi().moverBaixo();
		j.verificaEspada();
		j.verificaDragao();
		for(int i=0;i<3;i++)
			j.getHeroi().moverCima();
		for(int i=0;i<7;i++)
			j.getHeroi().moverDireita();
		for(int i=0;i<4;i++)
			j.getHeroi().moverBaixo();
		j.getHeroi().moverDireita();
		j.verificaSaida();
		assertEquals(true, j.isFimDeJogo());
	}

	@Test
	public void testaHeroiAcabarJogoSemPoderDesarmado(){
		Jogo j= new Jogo();
		Dragao d=new Dragao(5,1,'D');
		j.dragoes.add(d);

		assertEquals(false, j.isFimDeJogo());
		for(int i=0;i<7;i++)
			j.getHeroi().moverDireita();
		for(int i=0;i<4;i++)
			j.getHeroi().moverBaixo();
		j.getHeroi().moverDireita();
		if(!j.dragoesVivos()){
			j.verificaSaida();
		}
		assertEquals(false, j.isFimDeJogo());
	}

	@Test
	public void testaHeroiAcabarJogoSemPoderArmado(){
		Jogo j= new Jogo();
		Dragao d=new Dragao(5,1,'D');
		j.dragoes.add(d);

		assertEquals(false, j.isFimDeJogo());
		j.getHeroi().moverBaixo();
		j.getHeroi().moverBaixo();
		j.getHeroi().moverBaixo();
		j.verificaEspada();
		for(int i=0;i<3;i++)
			j.getHeroi().moverCima();
		for(int i=0;i<7;i++)
			j.getHeroi().moverDireita();
		for(int i=0;i<4;i++)
			j.getHeroi().moverBaixo();
		j.getHeroi().moverDireita();
		if(!j.dragoesVivos())
			j.verificaSaida();
		assertEquals(false, j.isFimDeJogo());
	}
	
	@Test
	public void moverHeroiBaixo(){
	
	}
	
}
