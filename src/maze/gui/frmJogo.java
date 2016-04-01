package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maze.gui.GraficosJogo.EstadoJogo;
import maze.gui.VitoriaDerrota.Imagem;
import maze.logic.Jogo;
import maze.logic.Jogo.Movimento;

public class frmJogo extends JFrame {

	private JPanel contentPane;
	private Jogo jogo;
	private JPanel desenhoLabirinto;
	private JPanel vencedor;

	/**   
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmJogo frame = new frmJogo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmJogo() {
		initialize();

	}


	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 598);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		desenhoLabirinto = new GraficosJogo();
		desenhoLabirinto.setBounds(6, 6, 294, 264);
		getContentPane().add(desenhoLabirinto);
		//this.pack();

		int dimensao=11;
		jogo=new Jogo(1,dimensao);
		jogo.setModoJogo(1);
		
		vencedor = new VitoriaDerrota();
		vencedor.setBounds(303, 11, 400, 300);
		getContentPane().add(vencedor);

		((GraficosJogo) desenhoLabirinto).mudarEstadoJogo(EstadoJogo.COM_LABIRINTO);
		((GraficosJogo) desenhoLabirinto).setLabirinto(jogo.getTab());
		setBounds(0, 0,desenhoLabirinto.getX()+ dimensao * 40+50, desenhoLabirinto.getY()+ dimensao * 50 +50);
		desenhoLabirinto.setBounds(desenhoLabirinto.getX(), desenhoLabirinto.getY(), desenhoLabirinto.getX()+ dimensao * 40, desenhoLabirinto.getY()+ dimensao * 50);
		desenhoLabirinto.setVisible(true);
		desenhoLabirinto.repaint();
		
		desenhoLabirinto.requestFocus();
		
		
		
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT: 
					processarDirecao(Movimento.ESQUERDA);
					break;
					
				case KeyEvent.VK_RIGHT: 
					processarDirecao(Movimento.DIREITA);
					//System.out.println("x=" + x);
					break;

				case KeyEvent.VK_UP: 
					processarDirecao(Movimento.CIMA);
					break;

				case KeyEvent.VK_DOWN: 
					processarDirecao(Movimento.BAIXO);
					break;
				}
				repaint();
  
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}			
		});
}
	
	public void processarDirecao(Movimento direcao){
		if(jogo.jogada(direcao)){
			acabouJogo();
		}
		//mostradorLabirinto.setText(jogo.getTab().paraString());
		else desenhoLabirinto.repaint();
	}
	
	public void acabouJogo(){
		
		if(!jogo.dragoesVivos()){
			JOptionPane.showMessageDialog(this, "Ganhou o jogo!!");
			}
		else {
			JOptionPane.showMessageDialog(this, "Perdeu o jogo!!");
		}
		
	}
}


