package Unisecure.view;

import Unisecure.controller.PollingEmergencias;
import Unisecure.controller.SocorristaController;
import Unisecure.model.Socorrista;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Component;
import java.awt.Toolkit;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class JLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel TelaPadrão;
	private JTextField UsuarioBox;
	private JPasswordField BoxSenha;
	private SocorristaController controller;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLogin frame = new JLogin();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Color azulClaro = new Color(173, 216, 230);
        Color azul = new Color(100, 149, 237);
        Color vermelho = new Color(255, 102, 102);
	 */
	public JLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JLogin.class.getResource("Logo.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 640);
		TelaPadrão = new JPanel();
		TelaPadrão.setBackground(new Color(173, 216, 230));
		TelaPadrão.setForeground(Color.BLACK);
		TelaPadrão.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(TelaPadrão);
		TelaPadrão.setLayout(null);

		controller = new SocorristaController();

		JPanel PainelMenor = new JPanel();
		PainelMenor.setBackground(new Color(123, 164, 213));
		PainelMenor.setForeground(Color.BLACK);
		PainelMenor.setBounds(62, 57, 727, 496);
		TelaPadrão.add(PainelMenor);
		PainelMenor.setLayout(null);

		JLabel UsuarioTxt = new JLabel("Usuario");
		UsuarioTxt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		UsuarioTxt.setBounds(164, 220, 46, 14);
		PainelMenor.add(UsuarioTxt);

		UsuarioBox = new JTextField();
		UsuarioBox.setBounds(164, 239, 382, 46);
		PainelMenor.add(UsuarioBox);
		UsuarioBox.setColumns(10);

		JLabel SenhaTxt = new JLabel("Senha");
		SenhaTxt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		SenhaTxt.setBounds(163, 303, 61, 23);
		PainelMenor.add(SenhaTxt);

		JButton BotaoAcessar = new JButton("Acessar");
		BotaoAcessar.setForeground(Color.WHITE);
		BotaoAcessar.setBackground( new Color(73, 73, 200));
		BotaoAcessar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		BotaoAcessar.addActionListener(e -> autenticar());
		BotaoAcessar.setBounds(275, 397, 150, 46);
		PainelMenor.add(BotaoAcessar);

		JLabel BemVindoTxt = new JLabel("Bem-Vindo");
		BemVindoTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BemVindoTxt.setHorizontalAlignment(SwingConstants.CENTER);
		BemVindoTxt.setToolTipText("");
		BemVindoTxt.setBounds(300, 11, 101, 55);
		PainelMenor.add(BemVindoTxt);

		JPanel panel_2 = new JPanel();
		panel_2.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
			}
		});
		panel_2.setBounds(268, 57, 164, 143);
		PainelMenor.add(panel_2);
		panel_2.setLayout(null);

		BoxSenha = new JPasswordField();
		BoxSenha.setBounds(164, 328, 382, 46);
		PainelMenor.add(BoxSenha);

		JPanel Cabeçalho = new JPanel();
		Cabeçalho.setAlignmentX(Component.RIGHT_ALIGNMENT);
		Cabeçalho.setBackground(new Color(250, 90, 86));
		Cabeçalho.setForeground(Color.RED);
		Cabeçalho.setBounds(0, 0, 847, 40);
		TelaPadrão.add(Cabeçalho);
		Cabeçalho.setLayout(null);

		JButton VoltarButao = new JButton("Voltar");
		VoltarButao.setFont(new Font("Tahoma", Font.BOLD, 13));
		VoltarButao.setForeground(Color.WHITE);
		VoltarButao.setBackground(new Color(73, 73, 200));
		VoltarButao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		VoltarButao.setAlignmentX(Component.RIGHT_ALIGNMENT);
		VoltarButao.setBounds(748, 11, 89, 23);
		Cabeçalho.add(VoltarButao);
	}

	private void autenticar() {
		String nome = UsuarioBox.getText();
		String senha = new String(BoxSenha.getPassword());

		Socorrista logado = controller.autenticar(nome, senha);

		if (logado != null) {
			JOptionPane.showMessageDialog(this, "Login bem-sucedido! Bem-vindo, " + logado.getNome());
			dispose(); // Fecha tela
			new PollingEmergencias(new TelaEmergencia()); // Abre tela principal do Socorrista
		} else {
			JOptionPane.showMessageDialog(this, "Nome ou senha incorretos.");
		}
	}
}
