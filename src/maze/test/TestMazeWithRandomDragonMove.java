package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import maze.logic.Dragao;
import maze.logic.Dragao.EstadoDragao;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Jogo.Movimento;
import maze.logic.Jogo;
import maze.logic.Tabuleiro;

public class TestMazeWithRandomDragonMove {

	char labirinto[][]={
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'},
	};

	@Test
	public void TentaColocarDragaoCimaEspada(){
		Jogo j= new Jogo(labirinto);
		Point p=new Point(4,1);
		assertEquals(false,j.podeColocarDragao(p));
	}

	@Test
	public void TentaColocarDragaoCelulalivre(){
		Jogo j= new Jogo(labirinto);
		Point p=new Point(6,1);
		assertEquals(true,j.podeColocarDragao(p));
	}

	@Test
	public void testDragaoDormirNaoMata() {
		Jogo j= new Jogo(labirinto);
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
		Jogo j= new Jogo(labirinto);
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
		Jogo j= new Jogo(labirinto);
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
		Jogo j= new Jogo(labirinto);
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
		Jogo j= new Jogo(labirinto);
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
		Jogo j= new Jogo(labirinto);
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
		Jogo j= new Jogo(labirinto);
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
		Jogo j= new Jogo(labirinto);
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

	@Test
	public void testDragaoDormirMoveAleatoriamente() {
		boolean acordou = false, igual = false;
		while (!acordou || !igual) {
			Jogo j= new Jogo(labirinto);
			Dragao d=new Dragao(8,8,'D');
			j.dragoes.add(d);
			j.dragoes.get(0).setEstado(EstadoDragao.DORMIR);
			Point pi=new Point(8,8);
			j.moverDragao();
			Point pf=new Point(j.dragoes.get(0).getP());
			if (pi.equals(pf)==true && j.dragoes.get(0).getEstado()==EstadoDragao.DORMIR)
				igual = true;
			else if (j.dragoes.get(0).getEstado()==EstadoDragao.ACORDADO)
				acordou = true;
			else
				fail("some error message");
		}
	}

	@Test
	public void testDragaoMoveAleatoriamente() {
		boolean moveuCima = false, moveuEsquerda = false, igual=false;
		while (!moveuCima || !moveuEsquerda || !igual) {
			Jogo j= new Jogo(labirinto);
			Dragao d=new Dragao(8,8,'D');
			j.dragoes.add(d);
			Point pc=new Point(7,8);
			Point pe=new Point(8,7);
			Point pi=new Point(8,8);
			j.dragaoAcordado(0);
			Point p=new Point(j.dragoes.get(0).getP());
			if (pc.equals(p))
				moveuCima = true;
			else if (pe.equals(p)) 
				moveuEsquerda = true;
			else if(pi.equals(p))
				igual=true;
			else
				fail("some error message");
		}
	}

	@Test
	public void testDragaoMoveAleatoriamenteTodasDirecõesModo2() {
		boolean moveuCima = false, moveuEsquerda = false, moveuBaixo=false, moveuDireita=false, igual=false;
		while (!moveuCima || !moveuEsquerda || !igual || !moveuBaixo || !moveuDireita ) {
			Jogo j= new Jogo(labirinto);
			j.setModoJogo(2);
			Dragao d=new Dragao(5,4,'D');
			j.dragoes.add(d);
			Point pc=new Point(4,4); //cima, x diminui 
			Point pb=new Point(6,4); //baixo x aumenta 
			Point pe=new Point(5,3); //esquerda y diminui 
			Point pd=new Point(5,5); //direita y aumenta 
			Point pi=new Point(5,4); //fica igual 
			j.moverDragao();
			Point p=new Point(j.dragoes.get(0).getP());
			if (pc.equals(p))
				moveuCima = true;
			else if (pe.equals(p)) 
				moveuEsquerda = true;
			else if (pb.equals(p))
				moveuBaixo=true;
			else if (pd.equals(p))
				moveuDireita=true;
			else if(pi.equals(p))
				igual=true;
			else
				fail("some error message");
		}  
	}


	//	@Test
	//	public void testDragaoMoveAleatoriamenteTodasDirecõesModo3() {
	//		boolean moveuCima = false, moveuEsquerda = false, moveuBaixo=false, moveuDireita=false, igual=false, dormir=false;
	//		while (!moveuCima || !moveuEsquerda || !igual || !moveuBaixo || !moveuDireita || !dormir) {
	//			Jogo j= new Jogo(labirinto);
	//			j.setModoJogo(3);
	//			Dragao d=new Dragao(5,4,'D');
	//			j.dragoes.add(d);
	//			Point pc=new Point(4,4); //cima, x diminui 
	//			Point pb=new Point(6,4); //baixo x aumenta 
	//			Point pe=new Point(5,3); //esquerda y diminui 
	//			Point pd=new Point(5,5); //direita y aumenta 
	//			Point pi=new Point(5,4); //fica igual 
	//			j.dragaoAcordado(0);
	//			Point p=new Point(j.dragoes.get(0).getP());
	//			if (pc.equals(p))
	//				moveuCima = true;
	//			else if (pe.equals(p)) 
	//				moveuEsquerda = true;
	//			else if (pb.equals(p))
	//				moveuBaixo=true;
	//			else if (pd.equals(p))
	//				moveuDireita=true;
	//			else if(pi.equals(p))
	//				igual=true;
	//			else if(j.dragoes.get(0).getEstado()==EstadoDragao.DORMIR)
	//				dormir=true;
	//			else
	//				fail("some error message");
	//
	//		}
	//	}

	@Test
	public void testLabirinto() {
		Tabuleiro t= new Tabuleiro(10);

		assertEquals(10, t.getN());
	}

	@Test
	public void testNovoConstrutor(){
		int nDimensoes = 11;
		int nDragoes = 2;
		Jogo jogo= new Jogo(nDragoes, nDimensoes);

		Point heroi= new Point();

		for(int i=0;i < jogo.getTab().getLabirinto().length; i++){
			for(int j=0;j < jogo.getTab().getLabirinto()[i].length; i++){
				Point p=new Point(i,j);
				if(jogo.getTab().retornaChar(p)=='H'){
					heroi=(Point)p.clone();
					break;
				}
				if(heroi != null)
					break;
			}
		}

		assertNotNull(heroi);
	}

	@Test
	public void testNovoConstrutorv2(){
		int dimensao=11;
		Tabuleiro tab= new Tabuleiro(dimensao);

		Point pontoH= new Point();

		for(int i=0;i < tab.getLabirinto().length; i++){
			for(int j=0;j < tab.getLabirinto()[i].length; i++){
				Point p=new Point(i,j);
				if(tab.retornaChar(p)=='H'){
					pontoH=(Point)p.clone();
					break;
				}
				if(pontoH != null)
					break;
			}
		}

		Heroi heroi= new Heroi(pontoH.x, pontoH.y, 'H');

		tab.inserirChar(heroi.getP(), heroi.getSimbolo());

		Point pontoD= new Point();

		for(int i=0;i < tab.getLabirinto().length; i++){
			for(int j=0;j < tab.getLabirinto()[i].length; i++){
				Point p=new Point(i,j);
				if(tab.retornaChar(p)=='D'){
					pontoD=(Point)p.clone();
					break;
				}
				if(pontoD != null)
					break;
			}
		}

		ArrayList<Dragao> dragoes = new ArrayList<Dragao>();
		Dragao dragao= new Dragao(pontoD.x, pontoD.y, 'D');
		dragoes.add(dragao);

		Point pontoE= new Point();

		for(int i=0;i < tab.getLabirinto().length; i++){
			for(int j=0;j < tab.getLabirinto()[i].length; i++){
				Point p=new Point(i,j);
				if(tab.retornaChar(p)=='D'){
					pontoE=(Point)p.clone();
					break;
				}
				if(pontoE != null)
					break;
			}
		}

		Espada espada= new Espada(pontoE.x,pontoE.y,'E');

		Jogo jogo=new Jogo(heroi, dragoes, espada, tab);

		assertNotNull(jogo);
	}

	@Test
	public void testNovoConstrutorv3(){
		char lab[][]={
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X',' ','E',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X','D','X','X',' ','X',' ','X',' ','X'},
				{'X',' ',' ','H',' ',' ',' ','X',' ','S'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'},
		};

		Jogo jogo= new Jogo(lab, 1);

		Point p=new Point(5,3);
		assertEquals(p,jogo.getHeroi().getP());
	}

	@Test
	public void testMoverHeroiRandom(){
		Jogo j=new Jogo(labirinto);
		Point pontoHAntesMover=(Point)j.getHeroi().getP().clone();
		pontoHAntesMover.x++;

		j.moverHeroi(Movimento.DIREITA);
		j.moverHeroi(Movimento.ESQUERDA);
		j.moverHeroi(Movimento.CIMA);
		j.moverHeroi(Movimento.BAIXO);

		assertEquals(pontoHAntesMover, j.getHeroi().getP());
		
		j.jogada(Movimento.CIMA);
		pontoHAntesMover.x--;
		
		assertEquals(pontoHAntesMover, j.getHeroi().getP());

	}

}
