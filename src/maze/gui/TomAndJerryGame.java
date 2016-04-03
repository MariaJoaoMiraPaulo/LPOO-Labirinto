package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import maze.gui.GraficosJogo.EstadoJogo;
import maze.logic.Jogo.Movimento;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;   
import javax.swing.JPanel;
import javax.swing.JSlider;    


public class TomAndJerryGame {
	private final int TAMANHO_IMAGEM_LABIRINTO=40, ALTURA_MINIMA_FRAME=300, LARGURA_MINIMA_FRAME=400,LARGURA_IMAGEM=600,ALTURA_IMAGEM=500, ALTURA_MENU_CONFIGURACOES=400,LARGURA_MENU_CONFIGURACOES=400;

	private JFrame frmJogo;   
	private JTextArea mostradorLabirinto;
	private JPanel desenhoLabirinto;
	private JComboBox modosJogo;
	private JButton btnTerminarPrograma;
	private JButton btnGerarLabirinto;
	private JLabel lblDimensaoDoLabirinto;
	private JLabel lblNumeroDeDragoes;
	private JLabel lblTipoDeDragoes;
	private JSlider dimensaoLab;
	private JLabel lblConfiguraes;
	private JSlider nrDragoes;
	public boolean jogadorGanhou;
	public boolean jogadorPerdeu;
	public boolean menu;
	private JButton btnJogar;
	private JButton btnConfiguraes;
	private JButton btnCriarLabirinto;
	private JButton btnSair;

    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TomAndJerryGame window = new TomAndJerryGame();
					window.frmJogo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TomAndJerryGame() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frmJogo = new JFrame();
		frmJogo.setResizable(true);
		frmJogo.setTitle("Jogo do Labirinto");
		frmJogo.setBounds(10, 10, 448, 372);
		frmJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJogo.getContentPane().setLayout(null);


		desenhoLabirinto = new GraficosJogo();
		desenhoLabirinto.setBounds(0, 0, 530, 402);
		frmJogo.getContentPane().add(desenhoLabirinto);
		desenhoLabirinto.setLayout(null);



		mostradorLabirinto = new JTextArea();
		mostradorLabirinto.setBounds(146, 12, 0, 15);
		desenhoLabirinto.add(mostradorLabirinto);
		mostradorLabirinto.setFont(new Font("Courier New", Font.PLAIN, 13));
		mostradorLabirinto.setEditable(false);

		btnJogar = new JButton("Jogar");
		btnJogar.setBounds(295, 326, 138, 29);
		desenhoLabirinto.add(btnJogar);

		btnConfiguraes = new JButton("Configurações");
		btnConfiguraes.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {
				setTodosBotoesMenosLabirinto(true);  
			}
		});
		btnConfiguraes.setBounds(295, 367, 138, 29);
		desenhoLabirinto.add(btnConfiguraes);
		
		btnCriarLabirinto = new JButton("Criar Labirinto");
		btnCriarLabirinto.setBounds(445, 326, 138, 29);
		desenhoLabirinto.add(btnCriarLabirinto);
		
		btnSair = new JButton("Sair");
		btnSair.setBounds(445, 367, 138, 29);
		desenhoLabirinto.add(btnSair);
		
		
		btnSair.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ){
				System.exit(0);
			}
		});
		
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int dimensao=11;
				int nrDragoes=1;
				((GraficosJogo)desenhoLabirinto).inicializarJogo(nrDragoes, dimensao);

				((GraficosJogo)desenhoLabirinto).getJogo().setModoJogo(2);
				
				frmJogo.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO, desenhoLabirinto.getY()+ dimensao * TAMANHO_IMAGEM_LABIRINTO +20);
				desenhoLabirinto.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO, desenhoLabirinto.getY()+ dimensao * TAMANHO_IMAGEM_LABIRINTO);

				desenhoLabirinto.setVisible(true);
				desenhoLabirinto.repaint();
				setTodosBotoesMenosLabirinto(false);
				desenhoLabirinto.requestFocus();


			}
		});

		lblDimensaoDoLabirinto = new JLabel("Dimensao do labirinto");
		lblDimensaoDoLabirinto.setBounds(30, 79, 174, 14);
		frmJogo.getContentPane().add(lblDimensaoDoLabirinto);

		lblNumeroDeDragoes = new JLabel("Numero de dragoes\r\n");
		lblNumeroDeDragoes.setBounds(30, 131, 141, 14);
		frmJogo.getContentPane().add(lblNumeroDeDragoes);

		lblTipoDeDragoes = new JLabel("Tipo de dragoes\r\n");
		lblTipoDeDragoes.setBounds(30, 195, 114, 14);
		frmJogo.getContentPane().add(lblTipoDeDragoes);

		modosJogo = new JComboBox();
		modosJogo.setModel(new DefaultComboBoxModel(new String[] {"Estaticos", "Moveis", "A dormir"}));
		modosJogo.setSelectedIndex(1);
		modosJogo.setBounds(178, 193, 109, 20);
		frmJogo.getContentPane().add(modosJogo);

		btnTerminarPrograma = new JButton("Terminar Programa");
		btnTerminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnTerminarPrograma.setBounds(113, 289, 174, 23);
		frmJogo.getContentPane().add(btnTerminarPrograma);

		dimensaoLab = new JSlider();
		dimensaoLab.setPaintTicks(true);
		dimensaoLab.setPaintLabels(true);
		dimensaoLab.setMinorTickSpacing(1);
		dimensaoLab.setMinimum(5);
		dimensaoLab.setMaximum(19);
		dimensaoLab.setValue(11);
		dimensaoLab.setMajorTickSpacing(6);
		dimensaoLab.setBounds(178, 68, 167, 40);
		frmJogo.getContentPane().add(dimensaoLab);


		nrDragoes = new JSlider();
		nrDragoes.setValue(1);
		nrDragoes.setPaintTicks(true);    
		nrDragoes.setPaintLabels(true);
		nrDragoes.setMajorTickSpacing(2);
		nrDragoes.setMinorTickSpacing(1);
		nrDragoes.setMinimum(1);
		nrDragoes.setMaximum(7);
		nrDragoes.setBounds(178, 131, 167, 40);
		frmJogo.getContentPane().add(nrDragoes);



		lblConfiguraes = new JLabel("Configurações");
		lblConfiguraes.setFont(new Font("Courier New", Font.PLAIN, 20));
		lblConfiguraes.setBounds(113, 12, 174, 44);
		frmJogo.getContentPane().add(lblConfiguraes);

		btnGerarLabirinto = new JButton("Gerar Labirinto");

		btnGerarLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nDragoes=nrDragoes.getValue();
				int dimensao=dimensaoLab.getValue();
				if(dimensao%2==0)
					dimensao++;

				((GraficosJogo)desenhoLabirinto).inicializarJogo(nDragoes, dimensao);

				if(modosJogo.getSelectedItem().equals("Estaticos"))
					((GraficosJogo)desenhoLabirinto).getJogo().setModoJogo(1);
				else if(modosJogo.getSelectedItem().equals("Moveis"))
					((GraficosJogo)desenhoLabirinto).getJogo().setModoJogo(2);
				else ((GraficosJogo)desenhoLabirinto).getJogo().setModoJogo(3);


				System.out.println("entrei aqui");  


				//				if(desenhoLabirinto.getX()+ dimensao * 40+50 < LARGURA_MINIMA_FRAME &&  desenhoLabirinto.getY()+ dimensao * 40 +50 < ALTURA_MINIMA_FRAME)
				//					frmJogo.setSize(LARGURA_MINIMA_FRAME, ALTURA_MINIMA_FRAME);
				//				else if(desenhoLabirinto.getY()+ dimensao * 40 +50 < ALTURA_MINIMA_FRAME)
				//					frmJogo.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO+50, ALTURA_MINIMA_FRAME);
				//				else
				//					frmJogo.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO, desenhoLabirinto.getY()+ dimensao * TAMANHO_IMAGEM_LABIRINTO +20);

				frmJogo.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO, desenhoLabirinto.getY()+ dimensao * TAMANHO_IMAGEM_LABIRINTO +20);
				desenhoLabirinto.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO, desenhoLabirinto.getY()+ dimensao * TAMANHO_IMAGEM_LABIRINTO);

				desenhoLabirinto.setVisible(true);
				desenhoLabirinto.repaint();
				setTodosBotoesMenosLabirinto(false);
				desenhoLabirinto.requestFocus();


			}
		});
		btnGerarLabirinto.setBounds(113, 254, 174, 23);
		frmJogo.getContentPane().add(btnGerarLabirinto);

		preparaBotoesMenu(true);   
		desenhoLabirinto.repaint();

	}

	public void setTodosBotoesMenosLabirinto(boolean flag){


		desenhoLabirinto.setVisible(!flag);

		dimensaoLab.setVisible(flag);
		dimensaoLab.setEnabled(flag);

		mostradorLabirinto.setVisible(flag);
		mostradorLabirinto.setEnabled(flag);

		btnGerarLabirinto.setVisible(flag);
		btnGerarLabirinto.setEnabled(flag);

		btnTerminarPrograma.setEnabled(flag);
		btnTerminarPrograma.setVisible(flag);

		modosJogo.setVisible(flag);
		modosJogo.setEnabled(flag);

		lblDimensaoDoLabirinto.setVisible(flag);
		lblDimensaoDoLabirinto.setEnabled(flag);

		lblNumeroDeDragoes.setVisible(flag);
		lblNumeroDeDragoes.setEnabled(flag);

		lblTipoDeDragoes.setVisible(flag);
		lblTipoDeDragoes.setEnabled(flag);

		lblConfiguraes.setVisible(flag);
		lblConfiguraes.setEnabled(flag);

		nrDragoes.setVisible(flag);
		nrDragoes.setEnabled(flag);   

		btnJogar.setVisible(false);
		btnJogar.setEnabled(false);
		
		btnConfiguraes.setVisible(false);
		btnConfiguraes.setEnabled(false);
		
		btnCriarLabirinto.setVisible(false);
		btnCriarLabirinto.setEnabled(false);   
		
		btnSair.setVisible(false);
		btnSair.setEnabled(false);

		if(flag){   
			frmJogo.setSize(LARGURA_MENU_CONFIGURACOES, ALTURA_MENU_CONFIGURACOES);
			//desenhoLabirinto.setSize(500, 400);
		}

	}	

	public void preparaBotoesMenu(boolean flag){

		desenhoLabirinto.setVisible(flag);

		dimensaoLab.setVisible(!flag);
		dimensaoLab.setEnabled(!flag);

		mostradorLabirinto.setVisible(flag);
		mostradorLabirinto.setEnabled(flag);

		btnGerarLabirinto.setVisible(!flag);
		btnGerarLabirinto.setEnabled(!flag);

		btnTerminarPrograma.setEnabled(!flag);
		btnTerminarPrograma.setVisible(!flag);

		modosJogo.setVisible(!flag);
		modosJogo.setEnabled(!flag);

		lblDimensaoDoLabirinto.setVisible(!flag);
		lblDimensaoDoLabirinto.setEnabled(!flag);

		lblNumeroDeDragoes.setVisible(!flag);
		lblNumeroDeDragoes.setEnabled(!flag);
    
		lblTipoDeDragoes.setVisible(!flag);
		lblTipoDeDragoes.setEnabled(!flag);

		lblConfiguraes.setVisible(!flag);
		lblConfiguraes.setEnabled(!flag);

		nrDragoes.setVisible(!flag);
		nrDragoes.setEnabled(!flag);

		btnJogar.setVisible(flag);
		btnJogar.setEnabled(flag);
		
		btnConfiguraes.setVisible(flag);
		btnConfiguraes.setEnabled(flag);
		
		btnCriarLabirinto.setVisible(flag);
		btnCriarLabirinto.setEnabled(flag);
		
		btnSair.setVisible(flag);
		btnSair.setEnabled(flag);

		if(flag){
			frmJogo.setSize(LARGURA_IMAGEM, ALTURA_IMAGEM);
			desenhoLabirinto.setBounds(0, 0, LARGURA_IMAGEM, ALTURA_IMAGEM);
			//desenhoLabirinto.setSize(500, 400);
		}
	}
}
