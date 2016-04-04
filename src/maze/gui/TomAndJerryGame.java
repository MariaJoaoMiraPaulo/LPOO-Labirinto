package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import maze.gui.GraficosJogo.EstadoJogo;
import maze.gui.GraficosJogo.TipoJogo;
import maze.logic.Jogo.Movimento;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;   
import javax.swing.JPanel;
import javax.swing.JSlider;    


public class TomAndJerryGame {
	private final int TAMANHO_IMAGEM_LABIRINTO=40, ALTURA_MINIMA_FRAME=300, LARGURA_MINIMA_FRAME=400,LARGURA_IMAGEM=600,ALTURA_IMAGEM=500, ALTURA_MENU_CONFIGURACOES=400,LARGURA_MENU_CONFIGURACOES=400;

	private JFrame frmJogo;   
	private JTextArea mostradorLabirinto;
	private GraficosJogo desenhoLabirinto;
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
	private JButton btnJogarNovamente;
	private JButton btnMenuPrincipal;
	private GeradorLabirinto gerador;
	public int dimensao;
	public int Dragoes;
	public int modoJogo;
	private JButton btnNewButton;
	private JButton btnComoJogar;
	private JButton btnAbrirJogo;


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

		dimensao=11;
		Dragoes=1;

		desenhoLabirinto = new GraficosJogo(this);
		desenhoLabirinto.setTipoJogo(TipoJogo.SEGUNDA_PARTE);
		desenhoLabirinto.setBounds(0, 0, 550, 459);
		frmJogo.getContentPane().add(desenhoLabirinto);
		desenhoLabirinto.setLayout(null);

		gerador=new GeradorLabirinto(this);  

		mostradorLabirinto = new JTextArea();
		mostradorLabirinto.setBounds(146, 12, 0, 15);
		desenhoLabirinto.add(mostradorLabirinto);
		mostradorLabirinto.setFont(new Font("Courier New", Font.PLAIN, 13));
		mostradorLabirinto.setEditable(false);

		btnJogar = new JButton("Jogar");
		btnJogar.setBounds(295, 326, 138, 29);
		desenhoLabirinto.add(btnJogar);

		btnConfiguraes = new JButton("Configuracoes\r\n");
		btnConfiguraes.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {
				setTodosBotoesMenosLabirinto(true);  
			}
		});
		btnConfiguraes.setBounds(295, 367, 138, 29);
		desenhoLabirinto.add(btnConfiguraes);

		btnCriarLabirinto = new JButton("Criar Labirinto");
		btnCriarLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {   
				frmJogo.setVisible(false);
				gerador.frame.setVisible(true);

			}
		});
		btnCriarLabirinto.setBounds(445, 326, 138, 29);
		desenhoLabirinto.add(btnCriarLabirinto);

		btnSair = new JButton("Sair");
		btnSair.setBounds(445, 367, 138, 29);
		desenhoLabirinto.add(btnSair);

		btnJogarNovamente = new JButton("Jogar Novamente");
		btnJogarNovamente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desenhoLabirinto.inicializarJogo(Dragoes, dimensao);

				desenhoLabirinto.getJogo().setModoJogo(2);

				frmJogo.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO, desenhoLabirinto.getY()+ dimensao * TAMANHO_IMAGEM_LABIRINTO +20);
				desenhoLabirinto.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO, desenhoLabirinto.getY()+ dimensao * TAMANHO_IMAGEM_LABIRINTO);

				desenhoLabirinto.setVisible(true);
				desenhoLabirinto.repaint();

				setTodosBotoesMenosLabirinto(false);    
				desenhoLabirinto.requestFocus();   
			}
		});
		btnJogarNovamente.setBounds(368, 399, 138, 29);
		desenhoLabirinto.add(btnJogarNovamente);

		btnMenuPrincipal = new JButton("Menu Principal");
		btnMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desenhoLabirinto.setEstadoJogo(EstadoJogo.SEM_LABIRINTO);
				preparaBotoesMenu(true);
			}
		});
		btnMenuPrincipal.setBounds(368, 440, 138, 29);
		desenhoLabirinto.add(btnMenuPrincipal);

		btnComoJogar = new JButton("Como Jogar");
		btnComoJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desenhoLabirinto.setEstadoJogo(EstadoJogo.INSTRUÇOES);
				
				desenhoLabirinto.repaint();
				desenhaInstruções(true);
				
			}
		});
		btnComoJogar.setBounds(295, 408, 138, 29);
		desenhoLabirinto.add(btnComoJogar);

		btnAbrirJogo = new JButton("Abrir Jogo");
		btnAbrirJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheiros de texto", "txt");
				fileChooser.setFileFilter(filter);
				int returnValue = fileChooser.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					try{
						
					InputStream ips=new FileInputStream(selectedFile); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String line;
					
					line=br.readLine();
					String ModoJogo=Character.toString(line.charAt(0));
					int mJ=Integer.parseInt(ModoJogo);
					line=br.readLine();
					char[][] labirinto = new char[line.length()][line.length()];
					int x=0;
					do{
						for(int i=0;i<line.length();i++){
							labirinto[x][i]=line.charAt(i);
						}
						x++;
					}while((line=br.readLine())!=null);   
					br.close(); 

					desenhoLabirinto.inicializarJogoAntigo(labirinto);
					desenhoLabirinto.getJogo().setModoJogo(mJ);

					frmJogo.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO, desenhoLabirinto.getY()+ dimensao * TAMANHO_IMAGEM_LABIRINTO+20);
					desenhoLabirinto.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO, desenhoLabirinto.getY()+ dimensao * TAMANHO_IMAGEM_LABIRINTO);

					desenhoLabirinto.setVisible(true);
					desenhoLabirinto.repaint();
					setTodosBotoesMenosLabirinto(false);
					desenhoLabirinto.requestFocus();
				}       
				catch (Exception e1){
					System.out.println(e1.toString());
				}


				}
			}
			
			});
		btnAbrirJogo.setBounds(445, 408, 138, 29);
		desenhoLabirinto.add(btnAbrirJogo);


		btnSair.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ){   
				System.exit(0);
			}
		});

		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dimensao=11;   
				Dragoes=1;
				desenhoLabirinto.inicializarJogo(Dragoes, dimensao);


				desenhoLabirinto.getJogo().setModoJogo(2);
				modoJogo=2;

				frmJogo.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO, desenhoLabirinto.getY()+ dimensao * TAMANHO_IMAGEM_LABIRINTO+20);
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



		lblConfiguraes = new JLabel("Configuracoes");
		lblConfiguraes.setFont(new Font("Courier New", Font.PLAIN, 20));
		lblConfiguraes.setBounds(113, 12, 174, 44);
		frmJogo.getContentPane().add(lblConfiguraes);

		btnGerarLabirinto = new JButton("Gerar Labirinto");

		btnGerarLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dragoes=nrDragoes.getValue();
				dimensao=dimensaoLab.getValue();
				if(dimensao%2==0)
					dimensao++;

				desenhoLabirinto.inicializarJogo(Dragoes, dimensao);   

				if(modosJogo.getSelectedItem().equals("Estaticos")){
					desenhoLabirinto.getJogo().setModoJogo(1);
					modoJogo=1;
				}
					
				else if(modosJogo.getSelectedItem().equals("Moveis")){
					desenhoLabirinto.getJogo().setModoJogo(2);
					modoJogo=2;
				}
					
				else {
					desenhoLabirinto.getJogo().setModoJogo(3);
					modoJogo=3;
				}


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

			btnJogarNovamente.setEnabled(false);
			btnJogarNovamente.setVisible(false);

			btnMenuPrincipal.setEnabled(false);
			btnMenuPrincipal.setVisible(false);

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

			btnComoJogar.setEnabled(flag);
			btnComoJogar.setVisible(flag);

			btnAbrirJogo.setEnabled(flag);
			btnAbrirJogo.setVisible(flag);

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

			desenhoLabirinto.setEstadoJogo(EstadoJogo.SEM_LABIRINTO);
			
			desenhoLabirinto.setVisible(flag);
			desenhoLabirinto.repaint();

			btnJogarNovamente.setEnabled(false);
			btnJogarNovamente.setVisible(false);

			btnMenuPrincipal.setEnabled(false);
			btnMenuPrincipal.setVisible(false);

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

			btnAbrirJogo.setVisible(flag);
			btnAbrirJogo.setEnabled(flag);

			btnComoJogar.setVisible(flag);   
			btnComoJogar.setEnabled(flag);

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

		public void desenhaGameOver(boolean flag){

			btnJogarNovamente.setEnabled(flag);
			btnJogarNovamente.setVisible(flag);

			btnMenuPrincipal.setEnabled(flag);
			btnMenuPrincipal.setVisible(flag);

			desenhoLabirinto.setVisible(flag);

			dimensaoLab.setVisible(!flag);
			dimensaoLab.setEnabled(!flag);

			mostradorLabirinto.setVisible(!flag);
			mostradorLabirinto.setEnabled(!flag);

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

			btnJogar.setVisible(!flag);
			btnJogar.setEnabled(!flag);

			btnConfiguraes.setVisible(!flag);
			btnConfiguraes.setEnabled(!flag);

			btnCriarLabirinto.setVisible(!flag);
			btnCriarLabirinto.setEnabled(!flag);

			btnSair.setVisible(!flag);
			btnSair.setEnabled(!flag);   

			btnComoJogar.setVisible(!flag);
			btnComoJogar.setEnabled(!flag);

			btnAbrirJogo.setVisible(!flag);
			btnAbrirJogo.setEnabled(!flag);

		}
		
		public void desenhaInstruções(boolean flag){

			btnJogarNovamente.setEnabled(!flag);
			btnJogarNovamente.setVisible(!flag);

			btnMenuPrincipal.setEnabled(flag);
			btnMenuPrincipal.setVisible(flag);

			desenhoLabirinto.setVisible(flag);

			dimensaoLab.setVisible(!flag);
			dimensaoLab.setEnabled(!flag);

			mostradorLabirinto.setVisible(!flag);
			mostradorLabirinto.setEnabled(!flag);

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

			btnJogar.setVisible(!flag);
			btnJogar.setEnabled(!flag);

			btnConfiguraes.setVisible(!flag);
			btnConfiguraes.setEnabled(!flag);

			btnCriarLabirinto.setVisible(!flag);
			btnCriarLabirinto.setEnabled(!flag);

			btnSair.setVisible(!flag);
			btnSair.setEnabled(!flag);   

			btnComoJogar.setVisible(!flag);
			btnComoJogar.setEnabled(!flag);

			btnAbrirJogo.setVisible(!flag);
			btnAbrirJogo.setEnabled(!flag);

		}

		public JFrame getFrmJogo() {
			return frmJogo;
		}

		public int getDimensao() {
			return dimensao;
		}

		public int getDragoes() {
			return Dragoes;
		}

		public int getModoJogo() {
			return modoJogo;
		}
	}
