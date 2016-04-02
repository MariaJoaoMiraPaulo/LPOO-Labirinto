package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
	private final int TAMANHO_IMAGEM_LABIRINTO=40, ALTURA_MINIMA_FRAME=466, LARGURA_MINIMA_FRAME=623;

	private JFrame frmJogo;
	private JTextField dimensaoLabirinto;
	private JTextField numeroDragoes;
	private JButton btnDireita;
	private JButton btnBaixo;
	private JButton btnEsquerda;
	private JButton btnCima;
	private JTextArea mostradorLabirinto;
	private JPanel desenhoLabirinto;
	private JComboBox modosJogo;
	private JButton btnTerminarPrograma;
	private JButton btnGerarLabirinto;
	private JLabel lblDimensaoDoLabirinto;
	private JLabel lblNumeroDeDragoes;
	private JLabel lblTipoDeDragoes;
	private JSlider DimensaoLab;
	private JSlider slider;

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
		frmJogo.setBounds(100, 100, 307, 466);
		frmJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJogo.getContentPane().setLayout(null);

		desenhoLabirinto = new GraficosJogo();
		desenhoLabirinto.setBounds(0, 0, 291, 412);
		frmJogo.getContentPane().add(desenhoLabirinto);

		mostradorLabirinto = new JTextArea();
		desenhoLabirinto.add(mostradorLabirinto);
		mostradorLabirinto.setFont(new Font("Courier New", Font.PLAIN, 13));
		mostradorLabirinto.setEditable(false);

		lblDimensaoDoLabirinto = new JLabel("Dimensao do labirinto");
		lblDimensaoDoLabirinto.setBounds(30, 41, 174, 14);
		frmJogo.getContentPane().add(lblDimensaoDoLabirinto);

		lblNumeroDeDragoes = new JLabel("Numero de dragoes\r\n");
		lblNumeroDeDragoes.setBounds(30, 66, 141, 14);
		frmJogo.getContentPane().add(lblNumeroDeDragoes);

		lblTipoDeDragoes = new JLabel("Tipo de dragoes\r\n");
		lblTipoDeDragoes.setBounds(30, 102, 114, 14);
		frmJogo.getContentPane().add(lblTipoDeDragoes);

		dimensaoLabirinto = new JTextField();
		dimensaoLabirinto.setBounds(30, 9, 108, 20);
		frmJogo.getContentPane().add(dimensaoLabirinto);
		dimensaoLabirinto.setColumns(10);

		numeroDragoes = new JTextField();
		numeroDragoes.setColumns(10);
		numeroDragoes.setBounds(183, 77, 108, 20);
		frmJogo.getContentPane().add(numeroDragoes);

		modosJogo = new JComboBox();
		modosJogo.setModel(new DefaultComboBoxModel(new String[] {"Estaticos", "Moveis", "A dormir"}));
		modosJogo.setSelectedIndex(1);
		modosJogo.setBounds(178, 100, 111, 20);
		frmJogo.getContentPane().add(modosJogo);

		btnCima = new JButton("Cima");
		btnCima.setEnabled(false);
		btnCima.setBounds(103, 236, 117, 29);
		frmJogo.getContentPane().add(btnCima);

		btnEsquerda = new JButton("Esquerda");
		btnEsquerda.setEnabled(false);
		btnEsquerda.setBounds(30, 276, 117, 29);
		frmJogo.getContentPane().add(btnEsquerda);

		btnDireita = new JButton("Direita");
		btnDireita.setEnabled(false);
		btnDireita.setBounds(162, 276, 117, 29);
		frmJogo.getContentPane().add(btnDireita);

		btnBaixo = new JButton("Baixo");
		btnBaixo.setEnabled(false);
		btnBaixo.setBounds(103, 312, 117, 29);
		frmJogo.getContentPane().add(btnBaixo);


		btnCima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GraficosJogo)desenhoLabirinto).processarDirecao(Movimento.CIMA);
				desenhoLabirinto.requestFocus();
			}
		});

		btnEsquerda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GraficosJogo)desenhoLabirinto).processarDirecao(Movimento.ESQUERDA);
				desenhoLabirinto.requestFocus();
			}
		});

		btnDireita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GraficosJogo)desenhoLabirinto).processarDirecao(Movimento.DIREITA);
				desenhoLabirinto.requestFocus();
			}

		});


		btnBaixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GraficosJogo)desenhoLabirinto).processarDirecao(Movimento.BAIXO);
				desenhoLabirinto.requestFocus();
			}

		});

		btnTerminarPrograma = new JButton("Terminar Programa");
		btnTerminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnTerminarPrograma.setBounds(80, 188, 174, 23);
		frmJogo.getContentPane().add(btnTerminarPrograma);

		slider = new JSlider();
		slider.setBounds(183, 36, 108, 29);
		frmJogo.getContentPane().add(slider);
		slider.setMajorTickSpacing(6);
		slider.setValue(5);
		slider.setPaintTicks(true);


		//		mostradorLabirinto = new JTextArea();
		//		mostradorLabirinto.setFont(new Font("Courier New", Font.PLAIN, 13));
		//		mostradorLabirinto.setEditable(false);

		btnGerarLabirinto = new JButton("Gerar Labirinto");
		setTodosBotoesMenosLabirinto(true);
//		setTodosBotoesMenosLabirinto(false);
//		desenhoLabirinto.repaint();
		btnGerarLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nDragoes=0;
				try{
					if(numeroDragoes.getText().equals("")){
						JOptionPane.showMessageDialog(frmJogo, "Preencha a caixa com o numero de dragoes");
						return;
					}
					else {
						nDragoes=Integer.parseInt(numeroDragoes.getText());
						if(nDragoes>5){
							JOptionPane.showMessageDialog(frmJogo, "Dragoes a mais!");
							return;
						}
					}
				}
				catch (NumberFormatException ex){
					JOptionPane.showMessageDialog(frmJogo, "Formato nao valido");
					return;
				}

				int dimensao=0;
				try{
					if(dimensaoLabirinto.getText().equals("")){
						dimensao=11;
					}
					else {
						dimensao=Integer.parseInt(dimensaoLabirinto.getText());
						if(dimensao%2==0){
							JOptionPane.showMessageDialog(frmJogo, "A dimensao do labirinto tem de ser impar!");
							return;
						}
					}
				}
				catch (NumberFormatException ex){
					JOptionPane.showMessageDialog(frmJogo, "Formato nao valido");
					return;
				}

				((GraficosJogo)desenhoLabirinto).inicializarJogo(nDragoes, dimensao);
				if(modosJogo.getSelectedItem().equals("Estaticos"))
					((GraficosJogo)desenhoLabirinto).getJogo().setModoJogo(1);
				else if(modosJogo.getSelectedItem().equals("Moveis"))
					((GraficosJogo)desenhoLabirinto).getJogo().setModoJogo(2);
				else ((GraficosJogo)desenhoLabirinto).getJogo().setModoJogo(3);

				//mostradorLabirinto.setText(jogo.getTab().paraString());
				//((GraficosJogo) desenhoLabirinto).mudarEstadoJogo(EstadoJogo.COM_LABIRINTO);
				//((GraficosJogo) desenhoLabirinto).setLabirinto(jogo.getTab());
				if(desenhoLabirinto.getX()+ dimensao * 40+50 < LARGURA_MINIMA_FRAME &&  desenhoLabirinto.getY()+ dimensao * 40 +50 < ALTURA_MINIMA_FRAME)
					frmJogo.setSize(LARGURA_MINIMA_FRAME, ALTURA_MINIMA_FRAME);
				else if(desenhoLabirinto.getY()+ dimensao * 40 +50 < ALTURA_MINIMA_FRAME)
					frmJogo.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO+50, ALTURA_MINIMA_FRAME);
				else
					frmJogo.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO, desenhoLabirinto.getY()+ dimensao * TAMANHO_IMAGEM_LABIRINTO +20);

				desenhoLabirinto.setSize(desenhoLabirinto.getX()+ dimensao * TAMANHO_IMAGEM_LABIRINTO, desenhoLabirinto.getY()+ dimensao * TAMANHO_IMAGEM_LABIRINTO);
				desenhoLabirinto.setVisible(true);
				desenhoLabirinto.repaint();
				setEnableEmVariosBotoes(true);
				setTodosBotoesMenosLabirinto(false);
				desenhoLabirinto.requestFocus();

			}
		});
		btnGerarLabirinto.setBounds(80, 142, 174, 23);
		frmJogo.getContentPane().add(btnGerarLabirinto);




	}

	public void setEnableEmVariosBotoes(boolean flag){
		btnBaixo.setEnabled(flag);
		btnCima.setEnabled(flag);
		btnEsquerda.setEnabled(flag);
		btnDireita.setEnabled(flag);
	}

	public void setTodosBotoesMenosLabirinto(boolean flag){


		desenhoLabirinto.setVisible(!flag);


		btnBaixo.setVisible(flag);
		btnBaixo.setEnabled(flag);

		btnCima.setVisible(flag);
		btnCima.setEnabled(flag);

		btnDireita.setVisible(flag);
		btnDireita.setEnabled(flag);

		btnEsquerda.setVisible(flag);
		btnEsquerda.setEnabled(flag);

		dimensaoLabirinto.setVisible(flag);
		dimensaoLabirinto.setEnabled(flag);

		mostradorLabirinto.setVisible(flag);
		mostradorLabirinto.setEnabled(flag);

		numeroDragoes.setVisible(flag);
		numeroDragoes.setEditable(flag);

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

		slider.setVisible(flag);
		slider.setEnabled(flag);

		if(flag)
			frmJogo.setSize(400, 600);
	}	
	
}
