package maze.gui;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import maze.gui.GraficosJogo.EstadoJogo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class GeradorLabirinto implements MouseListener{

	public enum BonecoAtivo{
		NENHUM, HEROI, DRAGAO, ESPADA, PAREDE, CHAO, SAIDA
	}

	public JFrame frame;

	private final int TAMANHO_IMAGEM_LABIRINTO=40;
	
	private BonecoAtivo bonecoAtivo=BonecoAtivo.NENHUM;//= BonecoAtivo.SAIDA;

	private JPanel heroi;
	private JPanel dragao;
	private JPanel espada;
	private JPanel parede;
	private JPanel chao;
	private JPanel saida;
	private JPanel gerarLabirinto;
	private JLabel instrucoesUtilizador;

	private TomAndJerryGame janelaPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeradorLabirinto window = new GeradorLabirinto();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GeradorLabirinto() {
		initialize();
	}
	
	public GeradorLabirinto(TomAndJerryGame janelaPrincipal) {
		this();
		this.janelaPrincipal=janelaPrincipal;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNovoLabirinto = new JButton("Novo Labirinto");
		btnNovoLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((PainelGerarLabirinto)gerarLabirinto).inicializarLabirinto('Q');
				((PainelGerarLabirinto)gerarLabirinto).renicializarEstado();
				bonecoAtivo=BonecoAtivo.NENHUM;
				gerarLabirinto.repaint();
			}
		});
		btnNovoLabirinto.setBounds(30, 90, 121, 23);
		frame.getContentPane().add(btnNovoLabirinto);

		heroi = new CarregarImagem("jerryFront");
		heroi.setBounds(68, 156, 50, 50);
		frame.getContentPane().add(heroi);

		dragao = new CarregarImagem("tomFront");
		dragao.setBounds(68, 217, 50, 50);
		frame.getContentPane().add(dragao);

		parede = new CarregarImagem("wall");
		parede.setBounds(68, 278, 50, 50);
		frame.getContentPane().add(parede);

		chao = new CarregarImagem("floor");
		chao.setBounds(68, 339, 50, 50);
		frame.getContentPane().add(chao);

		espada = new CarregarImagem("cheese");
		espada.setBounds(68, 400, 50, 50);
		frame.getContentPane().add(espada);

		saida = new CarregarImagem("door");
		saida.setBounds(68, 462, 50, 50);
		frame.getContentPane().add(saida);

		gerarLabirinto = new PainelGerarLabirinto();
		gerarLabirinto.setBounds(194, 52, 550, 550);
		frame.getContentPane().add(gerarLabirinto);
		
		JButton btnNewButton = new JButton("Menu Principal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaPrincipal.getFrmJogo().setVisible(true);
				frame.setVisible(false);
				janelaPrincipal.preparaBotoesMenu(true);
			}
		});
		btnNewButton.setBounds(30, 56, 121, 23);
		frame.getContentPane().add(btnNewButton);
		
		instrucoesUtilizador = new JLabel("Carregue na imagem que quer colocar a seguir");
		instrucoesUtilizador.setBounds(246, 15, 300, 14);
		frame.getContentPane().add(instrucoesUtilizador);
		
		JButton btnJogar = new JButton("Jogar");
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janelaPrincipal.getDesenhoLabirinto().setEstadoJogo(EstadoJogo.COM_LABIRINTO);
				janelaPrincipal.getFrmJogo().setVisible(true);
				frame.setVisible(false);
				janelaPrincipal.setTodosBotoesMenosLabirinto(false);
				janelaPrincipal.getDesenhoLabirinto().setJogo(((PainelGerarLabirinto)gerarLabirinto).getJogo());
				janelaPrincipal.getDesenhoLabirinto().requestFocus();
				janelaPrincipal.getFrmJogo().setSize(janelaPrincipal.getDesenhoLabirinto().getX()+ 11 * TAMANHO_IMAGEM_LABIRINTO, janelaPrincipal.getDesenhoLabirinto().getY()+ 11 * TAMANHO_IMAGEM_LABIRINTO +20);
				janelaPrincipal.getFrmJogo().setSize(janelaPrincipal.getDesenhoLabirinto().getX()+ 11 * TAMANHO_IMAGEM_LABIRINTO, janelaPrincipal.getDesenhoLabirinto().getY()+ 11 * TAMANHO_IMAGEM_LABIRINTO);
			}
		});
		btnJogar.setBounds(500, 11, 89, 23);
		frame.getContentPane().add(btnJogar);
		btnJogar.setVisible(false);
		
		JButton btnTerminarLabirinto = new JButton("Terminar Labirinto\r\n");
		btnTerminarLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(((PainelGerarLabirinto)gerarLabirinto).terminarLabirinto()){
					btnJogar.setVisible(true);
					instrucoesUtilizador.setText("Carregue no botao jogar");
				}
			}
		});
		btnTerminarLabirinto.setBounds(30, 124, 121, 23);
		frame.getContentPane().add(btnTerminarLabirinto);

		frame.setSize(gerarLabirinto.getX()+ 11* 40+50, gerarLabirinto.getY()+ 11* 40+70);
		
		frame.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		Point p=new Point(arg0.getX(), arg0.getY()-30);
		processarClique(p);
		
		gerarLabirinto.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void processarClique(Point arg0){
		if(arg0.getX()>=heroi.getX() && arg0.getX()<= heroi.getX()+ heroi.getWidth())
			if(arg0.getY()>=heroi.getY() && arg0.getY() <= heroi.getY() + heroi.getHeight()){
				bonecoAtivo=BonecoAtivo.HEROI;
				return;
			}

		if(arg0.getX()>=dragao.getX() && arg0.getX()<= dragao.getX()+ dragao.getWidth())
			if(arg0.getY()>=dragao.getY() && arg0.getY() <= dragao.getY() + dragao.getHeight()){
				bonecoAtivo=BonecoAtivo.DRAGAO;
				return;
			}

		if(arg0.getX()>=espada.getX() && arg0.getX()<= espada.getX()+ espada.getWidth())
			if(arg0.getY()>=espada.getY() && arg0.getY() <= espada.getY() + espada.getHeight()){
				bonecoAtivo=BonecoAtivo.ESPADA;
				return;
			}

		if(arg0.getX()>=parede.getX() && arg0.getX()<= parede.getX()+ parede.getWidth())
			if(arg0.getY()>=parede.getY() && arg0.getY() <= parede.getY() + parede.getHeight()){
				bonecoAtivo=BonecoAtivo.PAREDE;
				return;
			}

		if(arg0.getX()>=chao.getX() && arg0.getX()<= chao.getX()+ chao.getWidth())
			if(arg0.getY()>=chao.getY() && arg0.getY() <= chao.getY() + chao.getHeight()){
				bonecoAtivo=BonecoAtivo.CHAO;
				return;
			}

		if(arg0.getX()>=saida.getX() && arg0.getX()<= saida.getX()+ saida.getWidth())
			if(arg0.getY()>=saida.getY() && arg0.getY() <= saida.getY() + saida.getHeight()){
				bonecoAtivo=BonecoAtivo.SAIDA;
				return;
			}

		if(arg0.getX()>=gerarLabirinto.getX() && arg0.getX()<= gerarLabirinto.getX()+ gerarLabirinto.getWidth())
			if(arg0.getY()>=gerarLabirinto.getY() && arg0.getY() <= gerarLabirinto.getY() + gerarLabirinto.getHeight()){
		
				Point p=new Point((int)arg0.getY()-gerarLabirinto.getY(),(int)arg0.getX()-gerarLabirinto.getX());	
				char letra='Q';
				switch (bonecoAtivo){
				case HEROI:
					letra='H';
					break;
				case DRAGAO:
					letra='D';
					break;
				case ESPADA:
					letra='E';
					break;
				case PAREDE:
					letra='X';
					break;
				case CHAO:
					letra=' ';
					break;
				case SAIDA:
					letra='S';
					break;
				}
				((PainelGerarLabirinto)gerarLabirinto).setLabirinto(p, letra);
				return;
			}
		
		bonecoAtivo=BonecoAtivo.NENHUM;


	}

	public JPanel getGerarLabirinto() {
		return gerarLabirinto;
	}

	public void setBonecoAtivo(BonecoAtivo bonecoAtivo) {
		this.bonecoAtivo = bonecoAtivo;
	}
	
}
