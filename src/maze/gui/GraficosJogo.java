package maze.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Jogo;
import maze.logic.Jogo.Movimento;
import maze.logic.Tabuleiro;

public class GraficosJogo extends JPanel{

	public enum EstadoJogo{
		COM_LABIRINTO, SEM_LABIRINTO
	}

	private final int WIDTH=40, HEIGHT=40;  

	private BufferedImage heroi;
	private BufferedImage chao;
	private BufferedImage parede;
	private BufferedImage dragao;
	private BufferedImage espada;
	private BufferedImage saida;
	private BufferedImage heroiArmado;
	private BufferedImage espadaEDragao;
	private BufferedImage dragaoADormir;
	private Movimento direcaoHeroi;
	
	private int x=0, y=0, width=100, height=100;
	private Tabuleiro labirinto;
	private EstadoJogo estadoJogo=EstadoJogo.SEM_LABIRINTO;

	public GraficosJogo(){
		try {
			heroi =  ImageIO.read(new File("imagens/jerryFront.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			chao =  ImageIO.read(new File("imagens/floor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			parede =  ImageIO.read(new File("imagens/wall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			dragao =  ImageIO.read(new File("imagens/tomFront.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			espada =  ImageIO.read(new File("imagens/cheese.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			saida =  ImageIO.read(new File("imagens/door.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			heroiArmado =  ImageIO.read(new File("imagens/jerryCheese.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			espadaEDragao =  ImageIO.read(new File("imagens/tomCheese.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			dragaoADormir =  ImageIO.read(new File("imagens/tomsleep.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		switch(estadoJogo){
		case COM_LABIRINTO:
			drawLabirinto(g);
			break;
		}	
	}
	
	public void setDirecao(Movimento direcao){
		this.direcaoHeroi=direcao;
	}



	public void drawLabirinto(Graphics g) {
		Point ponto;
		ponto=new Point(0,0);
		System.out.println("Entrei");
		for(int i=0;i<labirinto.getLabirinto().length;i++){
			for(int j=0;j<labirinto.getLabirinto()[i].length;j++){
				Point p=new Point(i,j);
				if(labirinto.retornaChar(p)=='X')
					g.drawImage(parede, ponto.x, ponto.y, ponto.x+WIDTH, ponto.y+HEIGHT, 0, 0, parede.getWidth(), parede.getHeight(),null);
				if(labirinto.retornaChar(p)=='H')
					g.drawImage(heroi, ponto.x, ponto.y, ponto.x+WIDTH, ponto.y+HEIGHT, 0, 0, heroi.getWidth(), heroi.getHeight(),null);
				if(labirinto.retornaChar(p)==' ')
					g.drawImage(chao, ponto.x, ponto.y, ponto.x+WIDTH, ponto.y+HEIGHT, 0, 0, chao.getWidth(), chao.getHeight(),null);
				if(labirinto.retornaChar(p)=='D')
					g.drawImage(dragao, ponto.x, ponto.y, ponto.x+WIDTH, ponto.y+HEIGHT, 0, 0, dragao.getWidth(), dragao.getHeight(),null);
				if(labirinto.retornaChar(p)=='E')
					g.drawImage(espada, ponto.x, ponto.y, ponto.x+WIDTH, ponto.y+HEIGHT, 0, 0, espada.getWidth(), espada.getHeight(),null);
				if(labirinto.retornaChar(p)=='S')
					g.drawImage(saida, ponto.x, ponto.y, ponto.x+WIDTH, ponto.y+HEIGHT, 0, 0, saida.getWidth(), saida.getHeight(),null);
				if(labirinto.retornaChar(p)=='A')
					g.drawImage(heroiArmado, ponto.x, ponto.y, ponto.x+WIDTH, ponto.y+HEIGHT, 0, 0, heroiArmado.getWidth(), heroiArmado.getHeight(),null);
				if(labirinto.retornaChar(p)=='F')
					g.drawImage(espadaEDragao, ponto.x, ponto.y, ponto.x+WIDTH, ponto.y+HEIGHT, 0, 0, espadaEDragao.getWidth(), espadaEDragao.getHeight(),null);
				if(labirinto.retornaChar(p)=='d')
					g.drawImage(dragaoADormir, ponto.x, ponto.y, ponto.x+WIDTH, ponto.y+HEIGHT, 0, 0, dragaoADormir.getWidth(), dragaoADormir.getHeight(),null);
				
				ponto.x=ponto.x+WIDTH;
			}
			ponto.y=ponto.y+HEIGHT;
			ponto.x=0;
		}


	}

	public void setLabirinto(Tabuleiro labirinto) {
		this.labirinto = labirinto;
	}

	public void mudarEstadoJogo(EstadoJogo estado){
		this.estadoJogo = estado;
	}
}
