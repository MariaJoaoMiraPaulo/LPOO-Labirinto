package maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class VitoriaDerrota extends JPanel {

	public enum Imagem {
		COM_IMAGEM, SEM_IMAGEM
	}
	
	private final int WIDTH=400, HEIGHT=300;

	private Imagem imagem = Imagem.SEM_IMAGEM;
	private BufferedImage vitoria;
	private BufferedImage derrota;
	private boolean ratoGanha;

	public VitoriaDerrota(){
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
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		switch(imagem){
		case COM_IMAGEM:
			if(ratoGanha){
				System.out.println("Vou desenhar");
				g.drawImage(vitoria, 0, 0, WIDTH, HEIGHT, 0, 0, vitoria.getWidth(), vitoria.getHeight(),null);
			}
				
			else 
				g.drawImage(derrota, 0, 0, WIDTH, HEIGHT, 0, 0, derrota.getWidth(), derrota.getHeight(),null);
			break;
		}
	}
	
	public void quemGanhou(boolean flag){ //se a flag for verdadeira o rato ganhou o jogo
		if(flag)
			ratoGanha=true;
		else ratoGanha=false;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
}
