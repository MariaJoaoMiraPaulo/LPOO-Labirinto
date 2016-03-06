package maze.test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import maze.logic.Dragao;
import maze.logic.Jogo;
import maze.logic.Dragao.EstadoDragao;

public class TestMazeRandomMove {

	@Test
	public void DragaoDormirNaoMata() {
		Jogo j= new Jogo();
		Dragao d=new Dragao(3,1,'D');
		assertEquals(false,j.isFimDeJogo());
		j.dragoes.add(d);
		j.dragoes.get(0).moverCima();
		j.dragoes.get(0).setEstado(EstadoDragao.DORMIR);
		j.verificaDragao();
		assertEquals(false,j.isFimDeJogo());
	}

}
