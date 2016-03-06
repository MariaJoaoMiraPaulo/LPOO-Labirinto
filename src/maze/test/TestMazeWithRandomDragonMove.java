package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.logic.Dragao;
import maze.logic.Dragao.EstadoDragao;
import maze.logic.Jogo.Movimento;
import maze.logic.Jogo;

public class TestMazeWithRandomDragonMove {

	@Test
	public void TentaColocarDragaoCimaEspada(){
		Jogo j= new Jogo();
		Point p=new Point(4,1);
		assertEquals(false,j.podeColocarDragao(p));
	}
	
	@Test
	public void TentaColocarDragaoCelulalivre(){
		Jogo j= new Jogo();
		Point p=new Point(6,1);
		assertEquals(true,j.podeColocarDragao(p));
	}
	
	@Test
	public void testDragaoDormirNaoMata() {
		Jogo j= new Jogo();
		Dragao d=new Dragao(1,3,'D');
		j.dragoes.add(d);
		assertEquals(false,j.isFimDeJogo());
		j.dragoes.get(0).moverEsquerda();
		d.setEstado(EstadoDragao.DORMIR);
		j.verificaDragao();
		assertEquals(false,j.isFimDeJogo());
	}

	@Test
	public void testEspadaCoberta(){
		Jogo j= new Jogo();
		Dragao d=new Dragao(5,1,'D');
		Point p=new Point(4,1);
		j.dragoes.add(d);
		assertEquals('E',j.getTab().retornaChar(p));
		j.dragoes.get(0).moverCima(); //Quando move atualiza logo coordenadas. nao faz sentido no dragaoeespada
		p=j.dragoes.get(0).p;
		j.dragoes.get(0).setPorCimaEspada(true);
		j.dragaoEEspada(p,0);
		assertEquals('F',j.getTab().retornaChar(p));
	}

	@Test
	public void doisDragoesUmMorre(){
		Jogo j= new Jogo();
		Dragao d=new Dragao(8,1,'D');
		Dragao d2=new Dragao(1,5,'D');
		j.dragoes.add(d);
		j.dragoes.add(d2);
		for(int i=0;i<3;i++){
			j.getHeroi().moverBaixo();
		}
		j.verificaEspada();
		assertEquals('A', j.getHeroi().getSimbolo());
		for(int i=0;i<3;i++){
			j.getHeroi().moverBaixo();
		}
		j.verificaDragao();
		assertEquals(EstadoDragao.MORTO,d.getEstado());
		assertEquals(EstadoDragao.ACORDADO,d2.getEstado());
	}
	
	@Test
	public void testDragaoDormirNaoAnda(){
		Jogo j= new Jogo();
		Dragao d=new Dragao(8,1,'D');
		Point p=new Point(8,1);
		j.dragoes.add(d);
		j.setModoJogo(3);
		assertEquals(p,j.dragoes.get(0).p);
		j.dragoes.get(0).setEstado(EstadoDragao.DORMIR);
		j.moverDragao();
		assertEquals(p,j.dragoes.get(0).p);
	}
	
	@Test
	public void testAlgumDragaoVivo(){
		Jogo j= new Jogo();
		Dragao d=new Dragao(8,1,'D');
		Dragao d1=new Dragao(1,6,'D');
		Dragao d2=new Dragao(6,1,'D');
		Dragao d3=new Dragao(7,8,'D');
		j.dragoes.add(d);
		j.dragoes.add(d1);
		j.dragoes.add(d2);
		j.dragoes.add(d3);
		assertEquals(true,j.dragoesVivos());
		j.dragoes.get(0).setEstado(EstadoDragao.MORTO);
		j.dragoes.get(1).setEstado(EstadoDragao.MORTO);
		j.dragoes.get(2).setEstado(EstadoDragao.MORTO);
		j.dragoes.get(3).setEstado(EstadoDragao.MORTO);
		assertEquals(false,j.dragoesVivos());
	}
	
	
	@Test
	public void moverDragaoContraParede(){
		Jogo j= new Jogo();
		Dragao d=new Dragao(8,1,'D');
		Dragao d1=new Dragao(5,1,'D');
		Dragao d2=new Dragao(6,1,'D');
		j.dragoes.add(d);
		j.dragoes.add(d1);
		j.dragoes.add(d2);
		j.dragoes.get(1).moverEsquerda();
		assertEquals(false,j.DragaoPodeMover(j.dragoes.get(1).p));
	}

	@Test
	public void vaiParaSaidaDragoesMortosGanha(){
		Jogo j= new Jogo();
		Dragao d=new Dragao(8,1,'D');
		Dragao d1=new Dragao(1,6,'D');
		Dragao d2=new Dragao(6,1,'D');
		Dragao d3=new Dragao(7,8,'D');
		j.dragoes.add(d);
		j.dragoes.add(d1);
		j.dragoes.add(d2);
		j.dragoes.add(d3);
		assertEquals(true,j.dragoesVivos());
		j.dragoes.get(0).setEstado(EstadoDragao.MORTO);
		j.dragoes.get(1).setEstado(EstadoDragao.MORTO);
		j.dragoes.get(2).setEstado(EstadoDragao.MORTO);
		j.dragoes.get(3).setEstado(EstadoDragao.MORTO);
		assertEquals(false,j.dragoesVivos());
		for(int i=0;i<7;i++)
			j.getHeroi().moverDireita();
		for(int i=0;i<4;i++)
			j.getHeroi().moverBaixo();
		assertEquals(true,j.podeMoverHeroi(Movimento.DIREITA));
	}
	
	@Test
	public void vaiParaSaidaDragoesVivos(){
		Jogo j= new Jogo();
		Dragao d=new Dragao(8,1,'D');
		Dragao d1=new Dragao(1,6,'D');
		Dragao d2=new Dragao(6,1,'D');
		Dragao d3=new Dragao(7,8,'D');
		j.dragoes.add(d);
		j.dragoes.add(d1);
		j.dragoes.add(d2);
		j.dragoes.add(d3);
		assertEquals(true,j.dragoesVivos());
		j.dragoes.get(0).setEstado(EstadoDragao.MORTO);
		j.dragoes.get(2).setEstado(EstadoDragao.MORTO);
		j.dragoes.get(3).setEstado(EstadoDragao.MORTO);
		assertEquals(true,j.dragoesVivos());
		for(int i=0;i<7;i++)
			j.getHeroi().moverDireita();
		for(int i=0;i<4;i++)
			j.getHeroi().moverBaixo();
		assertEquals(false,j.podeMoverHeroi(Movimento.DIREITA));
	}
	
	
	
	
	
	
	
	

}
