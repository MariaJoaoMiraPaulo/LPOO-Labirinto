package maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CarregarImagem extends JPanel{

	private final int LARGURA_IMAGEM=40, ALTURA_IMAGEM=40;

	private BufferedImage imagem;

	public CarregarImagem(String nomeImagem){
		nomeImagem= "imagens/"+nomeImagem+".png";
		try {
			imagem =  ImageIO.read(new File(nomeImagem));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imagem, 0, 0, LARGURA_IMAGEM, ALTURA_IMAGEM, 0, 0, imagem.getWidth(), imagem.getHeight(),null);
	}

}
