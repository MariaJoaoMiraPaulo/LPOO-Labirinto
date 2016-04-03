package maze.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.gui.VitoriaDerrota.Imagem;
import maze.logic.Jogo;
import maze.logic.Jogo.Movimento;
import maze.logic.Tabuleiro;

public class GraficosJogo extends JPanel{

	public enum EstadoJogo{
		COM_LABIRINTO, SEM_LABIRINTO, HEROI_GANHOU, HEROI_PERDEU
	}

	private final int LARGURA_IMAGENS_LABIRINTO=40, ALTURA_IMAGENS_LABIRINTO=40, LARGURA_IMAGEM_FIM_DE_JOGO=400, ALTURA_IMAGEM_FIM_DE_JOGO=300;

	private BufferedImage heroi;
	private BufferedImage heroiEsquerda;
	private BufferedImage heroiDireita;
	private BufferedImage chao;
	private BufferedImage parede;
	private BufferedImage dragao;
	private BufferedImage dragaoEsquerda;
	private BufferedImage dragaoDireita;
	private BufferedImage espada;
	private BufferedImage saida;
	private BufferedImage heroiArmado;
	private BufferedImage heroiArmadoEsquerda;
	private BufferedImage espadaEDragao;
	private BufferedImage dragaoADormir;
	private BufferedImage vitoria;
	private BufferedImage derrota;
	private BufferedImage menu;


	//private Tabuleiro labirinto;
	private EstadoJogo estadoJogo=EstadoJogo.SEM_LABIRINTO;
	private Jogo jogo;
	private Movimento direcao=Movimento.CIMA;

	public GraficosJogo(){
		try {
			heroi =  ImageIO.read(new File("imagens/jerryFront.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			heroiEsquerda =  ImageIO.read(new File("imagens/jerryLeft.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}   

		try {
			heroiDireita =  ImageIO.read(new File("imagens/jerryRight.png"));
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
			dragaoEsquerda =  ImageIO.read(new File("imagens/tomLeft.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			dragaoDireita =  ImageIO.read(new File("imagens/tomRight.png"));
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

		try {
			vitoria =  ImageIO.read(new File("imagens/ratoGanha.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			derrota =  ImageIO.read(new File("imagens/gatoGanha.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			heroiArmadoEsquerda =  ImageIO.read(new File("imagens/jerryCheeseLeft.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			menu =  ImageIO.read(new File("imagens/menu.png"));
		} catch (IOException e) {
			e.printStackTrace();  
		}
   
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				 switch(e.getKeyCode()){
	                case KeyEvent.VK_LEFT:
	                	direcao=Movimento.ESQUERDA;
	                    processarDirecao(Movimento.ESQUERDA);
	                    break;

	                case KeyEvent.VK_RIGHT:
	                	direcao=Movimento.DIREITA;
	                    processarDirecao(Movimento.DIREITA);
	                    break;

	                case KeyEvent.VK_UP:
	                	direcao=Movimento.CIMA;
	                    processarDirecao(Movimento.CIMA);
	                    break;

	                case KeyEvent.VK_DOWN:
	                	direcao=Movimento.BAIXO;
	                    processarDirecao(Movimento.BAIXO);
	                    break;
	                }

			}
		});
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);


		switch(estadoJogo){
		case COM_LABIRINTO:
			drawLabirinto(g);
			break;
		case HEROI_GANHOU:
			g.drawImage(vitoria, 0, 0, 400, 300, 0, 0, vitoria.getWidth(), vitoria.getHeight(),null);
			break;
		case HEROI_PERDEU:
			g.drawImage(derrota, 0, 0, 400, 300, 0, 0, derrota.getWidth(), derrota.getHeight(),null);
			break;
		case SEM_LABIRINTO:
			g.drawImage(menu, 0, 0, null);
		
		}
	}



	public void drawLabirinto(Graphics g) {
		Point ponto;
		ponto=new Point(0,0);

//		for(int i=0;i<labirinto.getLabirinto().length;i++){
//			for(int j=0;j<labirinto.getLabirinto()[i].length;j++){
		for(int i=0;i<jogo.getTab().getLabirinto().length;i++){
            for(int j=0;j<jogo.getTab().getLabirinto()[i].length;j++){
				Point p=new Point(i,j);
				if(jogo.getTab().retornaChar(p)=='X')
					g.drawImage(parede, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, parede.getWidth(), parede.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='H'&& direcao==Movimento.BAIXO)
					g.drawImage(heroi, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, heroi.getWidth(), heroi.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='H'&& direcao==Movimento.CIMA)
					g.drawImage(heroi, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, heroi.getWidth(), heroi.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='H' && direcao==Movimento.DIREITA)
					g.drawImage(heroiDireita, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, heroi.getWidth(), heroi.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='H' && direcao==Movimento.ESQUERDA)
					g.drawImage(heroiEsquerda, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, heroi.getWidth(), heroi.getHeight(),null);
				if(jogo.getTab().retornaChar(p)==' ')
					g.drawImage(chao, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, chao.getWidth(), chao.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='D' && jogo.getMovimentoDragao()==Movimento.BAIXO)
					g.drawImage(dragao, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, dragao.getWidth(), dragao.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='D' && jogo.getMovimentoDragao()==Movimento.CIMA)
					g.drawImage(dragao, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, dragao.getWidth(), dragao.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='D' && jogo.getMovimentoDragao()==Movimento.ESQUERDA)
					g.drawImage(dragaoEsquerda, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, dragao.getWidth(), dragao.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='D' && jogo.getMovimentoDragao()==Movimento.DIREITA)
					g.drawImage(dragaoDireita, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, dragao.getWidth(), dragao.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='E')
					g.drawImage(espada, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, espada.getWidth(), espada.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='S')
					g.drawImage(saida, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, saida.getWidth(), saida.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='A' && direcao==Movimento.DIREITA)
					g.drawImage(heroiArmado, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, heroiArmado.getWidth(), heroiArmado.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='A' && direcao==Movimento.BAIXO)
					g.drawImage(heroiArmado, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, heroiArmado.getWidth(), heroiArmado.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='A' && direcao==Movimento.CIMA)
					g.drawImage(heroiArmado, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, heroiArmado.getWidth(), heroiArmado.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='A' && direcao==Movimento.ESQUERDA)
					g.drawImage(heroiArmadoEsquerda, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, heroiArmado.getWidth(), heroiArmado.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='F')
					g.drawImage(espadaEDragao, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, espadaEDragao.getWidth(), espadaEDragao.getHeight(),null);
				if(jogo.getTab().retornaChar(p)=='d')
					g.drawImage(dragaoADormir, ponto.x, ponto.y, ponto.x+LARGURA_IMAGENS_LABIRINTO, ponto.y+ALTURA_IMAGENS_LABIRINTO, 0, 0, dragaoADormir.getWidth(), dragaoADormir.getHeight(),null);

				ponto.x=ponto.x+LARGURA_IMAGENS_LABIRINTO;
			}
			ponto.y=ponto.y+ALTURA_IMAGENS_LABIRINTO;
			ponto.x=0;
		}


	}

//	public void setLabirinto(Tabuleiro labirinto) {
//		this.labirinto = labirinto;
//	}

	public void mudarEstadoJogo(EstadoJogo estado){
		this.estadoJogo = estado;
	}

	public void inicializarJogo(int nDragoes, int dimensao){
		jogo=new Jogo(nDragoes,dimensao);
		estadoJogo=estadoJogo.COM_LABIRINTO;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void processarDirecao(Movimento direcao){
		if(jogo.jogada(direcao)){
			acabouJogo();
		}
		//mostradorLabirinto.setText(jogo.getTab().paraString());
		else repaint();
	}

	public void acabouJogo(){

		if(!jogo.dragoesVivos())
			estadoJogo=EstadoJogo.HEROI_GANHOU;
		else estadoJogo=EstadoJogo.HEROI_PERDEU;   

		this.setSize(LARGURA_IMAGEM_FIM_DE_JOGO, ALTURA_IMAGEM_FIM_DE_JOGO);

		repaint();

		if(!jogo.dragoesVivos()){
			JOptionPane.showMessageDialog(this, "Ganhou o jogo!!");
			}
		else {
			JOptionPane.showMessageDialog(this, "Perdeu o jogo!!");
		}

	}

	public EstadoJogo getEstadoJogo() {
		return estadoJogo;
	}
}
