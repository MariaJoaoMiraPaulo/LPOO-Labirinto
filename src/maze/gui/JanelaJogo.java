package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import maze.logic.Jogo;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;

public class JanelaJogo {

	private JFrame frmJogoDoLabirinto;
	private JTextField dimensaoLabirinto;
	private JTextField numeroDragoes;
	private Jogo jogo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaJogo window = new JanelaJogo();
					window.frmJogoDoLabirinto.setVisible(true);
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
		frmJogoDoLabirinto = new JFrame();
		frmJogoDoLabirinto.setResizable(false);
		frmJogoDoLabirinto.setTitle("Jogo do Labirinto");
		frmJogoDoLabirinto.setBounds(100, 100, 594, 434);
		frmJogoDoLabirinto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJogoDoLabirinto.getContentPane().setLayout(null);

		JLabel lblDimensaoDoLabirinto = new JLabel("Dimensao do labirinto");
		lblDimensaoDoLabirinto.setBounds(54, 41, 114, 14);
		frmJogoDoLabirinto.getContentPane().add(lblDimensaoDoLabirinto);

		JLabel lblNumeroDeDragoes = new JLabel("Numero de dragoes\r\n");
		lblNumeroDeDragoes.setBounds(54, 70, 114, 14);
		frmJogoDoLabirinto.getContentPane().add(lblNumeroDeDragoes);

		JLabel lblTipoDeDragoes = new JLabel("Tipo de dragoes\r\n");
		lblTipoDeDragoes.setBounds(54, 102, 114, 14);
		frmJogoDoLabirinto.getContentPane().add(lblTipoDeDragoes);

		dimensaoLabirinto = new JTextField();
		dimensaoLabirinto.setBounds(192, 38, 86, 20);
		frmJogoDoLabirinto.getContentPane().add(dimensaoLabirinto);
		dimensaoLabirinto.setColumns(10);

		numeroDragoes = new JTextField();
		numeroDragoes.setColumns(10);
		numeroDragoes.setBounds(192, 67, 86, 20);
		frmJogoDoLabirinto.getContentPane().add(numeroDragoes);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Estatico", "Moveis", "A dormir"}));
		comboBox.setBounds(192, 99, 86, 20);
		frmJogoDoLabirinto.getContentPane().add(comboBox);

		JTextArea mostradorLabirinto = new JTextArea();
		mostradorLabirinto.setFont(new Font("Courier New", Font.PLAIN, 13));
		mostradorLabirinto.setEditable(false);
		mostradorLabirinto.setBounds(75, 144, 318, 250);
		frmJogoDoLabirinto.getContentPane().add(mostradorLabirinto);

		JButton btnGerarLabirinto = new JButton("Gerar Labirinto");
		btnGerarLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nDragoes=0;
				try{
					if(numeroDragoes.getText().equals("")){
						JOptionPane.showMessageDialog(frmJogoDoLabirinto, "Preencha a caixa com o numero de dragoes");
						return;
					}
					else {
						nDragoes=Integer.parseInt(numeroDragoes.getText());
						if(nDragoes>5){
							JOptionPane.showMessageDialog(frmJogoDoLabirinto, "Dragoes a mais!");
							return;
						}
					}
				}
				catch (NumberFormatException ex){
					JOptionPane.showMessageDialog(frmJogoDoLabirinto, "Formato não válido");
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
							JOptionPane.showMessageDialog(frmJogoDoLabirinto, "A dimensao do labirinto tem de ser impar!");
							return;
						}
					}
				}
				catch (NumberFormatException ex){
					JOptionPane.showMessageDialog(frmJogoDoLabirinto, "Formato não válido");
					return;
				}
				
				jogo=new Jogo(nDragoes,dimensao);
				jogo.setModoJogo(2);
				mostradorLabirinto.setText(jogo.getTab().paraString());
			}
		});
		btnGerarLabirinto.setBounds(425, 37, 130, 23);
		frmJogoDoLabirinto.getContentPane().add(btnGerarLabirinto);

		JButton btnTerminarPrograma = new JButton("Terminar Programa");
		btnTerminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnTerminarPrograma.setBounds(425, 98, 130, 23);
		frmJogoDoLabirinto.getContentPane().add(btnTerminarPrograma);


	}
}
