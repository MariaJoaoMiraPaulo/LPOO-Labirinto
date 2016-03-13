package maze.gui;

import java.awt.EventQueue;
import maze.logic.Jogo.*;
import maze.logic.Tabuleiro;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class JanelaJogo {

	private JFrame Labirinto;
	private JTextField Dimensao;
	private JTextField dragoes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaJogo window = new JanelaJogo();
					window.Labirinto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JanelaJogo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Labirinto = new JFrame();
		Labirinto.setEnabled(false);
		Labirinto.setBounds(100, 100, 605, 539);
		Labirinto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Labirinto.getContentPane().setLayout(null);
		
		JLabel DimensaoDoLabirinto = new JLabel("Dimensão do Labirinto");
		DimensaoDoLabirinto.setBounds(33, 34, 150, 16);
		Labirinto.getContentPane().add(DimensaoDoLabirinto);
		
		Dimensao = new JTextField();
		Dimensao.setBounds(188, 29, 61, 26);
		Labirinto.getContentPane().add(Dimensao);
		Dimensao.setColumns(10);
		
		JLabel NumeroDeDrages = new JLabel("Número de Dragões");
		NumeroDeDrages.setBounds(33, 79, 150, 16);
		Labirinto.getContentPane().add(NumeroDeDrages);
		
		dragoes = new JTextField();
		dragoes.setBounds(188, 74, 61, 26);
		Labirinto.getContentPane().add(dragoes);
		dragoes.setColumns(10);
		
		JLabel TipoDeDragoes = new JLabel("Tipo de Dragões");
		TipoDeDragoes.setBounds(33, 126, 129, 16);
		Labirinto.getContentPane().add(TipoDeDragoes);
		
		JTextArea AreaTabuleiro = new JTextArea();
		AreaTabuleiro.setBounds(33, 193, 311, 274);
		Labirinto.getContentPane().add(AreaTabuleiro);
		
		JComboBox OpcaoDragoes = new JComboBox();
		OpcaoDragoes.setBounds(188, 122, 129, 27);
		Labirinto.getContentPane().add(OpcaoDragoes);
		
		JButton btnGerarNovoLabirinto = new JButton("Gerar Novo Labirinto");
		btnGerarNovoLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				int dimensao;
//				dimensao=Integer.parseInt(Dimensao.getText());
//				Tabuleiro tab=new Tabuleiro(dimensao);
//				AreaTabuleiro.setText(tab.toString());
			}
		});
		btnGerarNovoLabirinto.setBounds(387, 29, 173, 29);
		Labirinto.getContentPane().add(btnGerarNovoLabirinto);
		
		JButton btnTerminarPrograma = new JButton("Terminar Programa");
		btnTerminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTerminarPrograma.setBounds(387, 97, 173, 29);
		Labirinto.getContentPane().add(btnTerminarPrograma);
		
		
		
		JButton btnCima = new JButton("Cima");
		btnCima.setBounds(419, 219, 117, 29);
		Labirinto.getContentPane().add(btnCima);
		
		JButton btnEsquerda = new JButton("Esquerda");
		btnEsquerda.setBounds(356, 260, 117, 29);
		Labirinto.getContentPane().add(btnEsquerda);
		
		JButton btnDireita = new JButton("Direita");
		btnDireita.setBounds(482, 260, 117, 29);
		Labirinto.getContentPane().add(btnDireita);
		
		JButton btnBaixo = new JButton("Baixo");
		btnBaixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBaixo.setBounds(419, 301, 117, 29);
		Labirinto.getContentPane().add(btnBaixo);
		
		JLabel InfJogo = new JLabel("");
		InfJogo.setBounds(33, 484, 311, 16);
		Labirinto.getContentPane().add(InfJogo);
	}
}
