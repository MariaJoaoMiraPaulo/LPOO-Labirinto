package maze.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Tabuleiro;

public class PainelGerarLabirinto extends JPanel{

	private final int LARGURA_IMAGENS_LABIRINTO=40, ALTURA_IMAGENS_LABIRINTO=40;
	
	private BufferedImage quadricula;
	private BufferedImage heroi;
	private BufferedImage dragao;
	private BufferedImage espada;
	private BufferedImage parede;
	private BufferedImage saida;
	private BufferedImage chao;

	private Tabuleiro labirinto;

	public PainelGerarLabirinto(){
		try {
			quadricula =  ImageIO.read(new File("imagens/quadricula.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

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

		inicializarLabirinto('Q');
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawLabirinto(g);
	}
	
	public void drawLabirinto(Graphics g) {
		Point ponto;
		ponto=new Point(0,0);
		
		for(int i=0;i<labirinto.getLabirinto().length;i++){
            for(int j=0;j<labirinto.getLabirinto()[i].length;j++){
				Point p=new Point(i,j);
				if(labirinto.retornaChar(p)=='Q')
					g.drawImage(quadricula, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, quadricula.getWidth(), quadricula.getHeight(),null);
				if(labirinto.retornaChar(p)=='X')
					g.drawImage(parede, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, parede.getWidth(), parede.getHeight(),null);
				if(labirinto.retornaChar(p)=='H')
					g.drawImage(heroi, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, heroi.getWidth(), heroi.getHeight(),null);
				if(labirinto.retornaChar(p)==' ')
					g.drawImage(chao, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, chao.getWidth(), chao.getHeight(),null);
				if(labirinto.retornaChar(p)=='D')
					g.drawImage(dragao, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, dragao.getWidth(), dragao.getHeight(),null);
				if(labirinto.retornaChar(p)=='E')
					g.drawImage(espada, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, espada.getWidth(), espada.getHeight(),null);
				if(labirinto.retornaChar(p)=='S')
					g.drawImage(saida, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, saida.getWidth(), saida.getHeight(),null);
				
				ponto.x=ponto.x+LARGURA_IMAGENS_LABIRINTO;
			}
			ponto.y=ponto.y+ALTURA_IMAGENS_LABIRINTO;
			ponto.x=0;
		}
	}
	
	public void setLabirinto(Point p, char letra){
		Point p2= (Point) p.clone();
		
		p2.x= p2.x/40;
		p2.y= p2.y/40;
		
		System.out.println("x: "+ p2.x);
		System.out.println("y: "+ p2.y);
		
		labirinto.inserirChar(p2, letra);
	}
	
	public void inicializarLabirinto(char letra){
		char[][] labInicial = new char[11][11];
		
		for(int i=0; i<labInicial.length;i++){
			for(int j=0; j<labInicial[i].length;j++){
				labInicial[i][j]=letra;
			}
		}
		labirinto = new Tabuleiro(labInicial);
	}
}


