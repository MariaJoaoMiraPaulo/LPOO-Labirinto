package maze.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Jogo;
import maze.logic.Tabuleiro;

public class PainelGerarLabirinto extends JPanel{

	public enum EstadoSaida{
		COLOCADA, NAO_COLOCADA
	}

	public enum EstadoHeroi{
		COLOCADO, NAO_COLOCADO
	}

	public enum EstadoDragao{
		COLOCADO, NAO_COLOCADO
	}

	public enum EstadoEspada{
		COLOCADA, NAO_COLOCADA
	}

	private final int LARGURA_IMAGENS_LABIRINTO=40, ALTURA_IMAGENS_LABIRINTO=40;

	private BufferedImage quadricula;
	private BufferedImage heroi;
	private BufferedImage dragao;
	private BufferedImage espada;
	private BufferedImage parede;
	private BufferedImage saida;
	private BufferedImage chao;

	private Tabuleiro labirinto;
	private Heroi heroiColocado;
	private ArrayList<Dragao> dragoes= new ArrayList<Dragao>();
	private Espada espadaColocada;
	private Jogo jogo;
	private int dimensao=11;

	private EstadoSaida estadoSaida=EstadoSaida.NAO_COLOCADA;
	private EstadoHeroi estadoHeroi=EstadoHeroi.NAO_COLOCADO;
	private EstadoDragao estadoDragao=EstadoDragao.NAO_COLOCADO;
	private EstadoEspada estadoEspada=EstadoEspada.NAO_COLOCADA;

	GeradorLabirinto frame;

	public PainelGerarLabirinto(int d){
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

		dimensao=d;

	}

	public PainelGerarLabirinto(GeradorLabirinto frame, int dimensao){
		this(dimensao);
		this.frame=frame;
		
		inicializarLabirinto('Q',dimensao);
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

		switch(letra){
		case 'S':
			if(labirinto.retornaChar(p2)=='X' && estadoSaida.equals(EstadoSaida.NAO_COLOCADA)){
				labirinto.inserirChar(p2, letra);
				estadoSaida=EstadoSaida.COLOCADA;
			}
			break;
		case 'H':
			if(labirinto.retornaChar(p2)=='Q' && estadoHeroi.equals(EstadoHeroi.NAO_COLOCADO)){
				labirinto.inserirChar(p2, letra);
				estadoHeroi=EstadoHeroi.COLOCADO;
				heroiColocado=new Heroi(p2.x,p2.y, 'H');
			}
			break;
		case 'D':
			if(labirinto.retornaChar(p2)=='Q'){
				labirinto.inserirChar(p2, letra);
				estadoDragao=EstadoDragao.COLOCADO;
				dragoes.add(new Dragao(p2.x,p2.y, 'D'));
			}
			break;
		case 'E':
			if(labirinto.retornaChar(p2)=='Q' && estadoEspada.equals(EstadoEspada.NAO_COLOCADA)){
				labirinto.inserirChar(p2, letra);
				estadoEspada=EstadoEspada.COLOCADA;
				espadaColocada=new Espada(p2.x,p2.y, 'E');
			}
			break;
		case 'X':
			if(labirinto.retornaChar(p2)=='Q'){
				labirinto.inserirChar(p2, letra);
			}
			break;
		case 'Q':
			break;
		default:
			break;
		}

	}

	public void inicializarLabirinto(char letra, int d){
		jogo=null;
		dimensao=frame.getJanelaPrincipal().getDimensao();
		System.out.println("dimensao:" +dimensao);

		char[][] labInicial = new char[dimensao][dimensao];

		for(int i=0; i<labInicial.length;i++){
			for(int j=0; j<labInicial[i].length;j++){
				labInicial[i][j]=letra;
			}
		}

		for(int i=0;i<labInicial.length;i++){
			labInicial[i][0]='X';
			labInicial[i][labInicial.length-1]='X';
		}

		for(int i=0;i<labInicial.length;i++){
			labInicial[0][i]='X';
			labInicial[labInicial.length-1][i]='X';
		}

		labirinto = new Tabuleiro(labInicial);

		frame.getFrame().setSize(getX()+ dimensao* 40+50, getY()+ dimensao* 40+50);
		setSize( dimensao* 40+50,  dimensao* 40+50);
	}

	public boolean terminarLabirinto(){
		if(estadoDragao.equals(EstadoDragao.COLOCADO) && estadoEspada.equals(EstadoEspada.COLOCADA) && estadoHeroi.equals(EstadoHeroi.COLOCADO) && estadoSaida.equals(EstadoSaida.COLOCADA)){
			for(int i=0; i<labirinto.getLabirinto().length;i++){
				for(int j=0; j<labirinto.getLabirinto()[i].length;j++){
					if(labirinto.retornaChar(i, j)=='Q')
						labirinto.getLabirinto()[i][j]=' ';
				}
			}
			repaint();

			jogo=new Jogo(heroiColocado, dragoes, espadaColocada, labirinto);

			jogo.setModoJogo(2);

			return true;
		}

		return false;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void renicializarEstado(){
		estadoSaida=EstadoSaida.NAO_COLOCADA;
		estadoHeroi=EstadoHeroi.NAO_COLOCADO;
		estadoDragao=EstadoDragao.NAO_COLOCADO;
		estadoEspada=EstadoEspada.NAO_COLOCADA;

		dragoes.clear();
	}
}


