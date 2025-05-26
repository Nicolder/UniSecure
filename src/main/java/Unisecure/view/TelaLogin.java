package Unisecure.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.prefs.Preferences;

import Unisecure.controller.PollingEmergencias;
import Unisecure.controller.SocorristaController;
import Unisecure.model.Socorrista;

public class TelaLogin extends JFrame {

	private JPanel telaPadrao;
	private JTextField usuarioBox;
	private JPasswordField senhaBox;
	private JCheckBox lembrarCheck;

	private static final SocorristaController controller = new SocorristaController();
	private static final Preferences prefs = Preferences.userRoot().node("unisecure_login");

	public static void main(String[] args) {
		EventQueue.invokeLater(TelaLogin::new);
	}

	public TelaLogin() {
		Socorrista socorrista = loginAutomatico();
		if (socorrista != null) {
			dispose();
			new PollingEmergencias(new TelaEmergencia());
			return;
		}

		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 640);
		setLocationRelativeTo(null);

		iniciarTela();
		setVisible(true);
	}

	private void iniciarTela() {
		telaPadrao = new JPanel();
		telaPadrao.setBackground(new Color(173, 216, 230));
		telaPadrao.setBorder(new EmptyBorder(5, 5, 5, 5));
		telaPadrao.setLayout(null);
		setContentPane(telaPadrao);

		JPanel painelMenor = new JPanel();
		painelMenor.setBackground(new Color(123, 164, 213));
		painelMenor.setBounds(62, 57, 727, 496);
		painelMenor.setLayout(null);
		telaPadrao.add(painelMenor);

		JLabel bemVindoLabel = new JLabel("Bem-Vindo", SwingConstants.CENTER);
		bemVindoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bemVindoLabel.setBounds(300, 11, 101, 55);
		painelMenor.add(bemVindoLabel);

		ImageIcon logoIcon = new ImageIcon("src/main/resources/Logo.png");
		Image logoImg = logoIcon.getImage().getScaledInstance(182, 150, Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
		logoLabel.setBounds(265, 40, 170, 170);
		painelMenor.add(logoLabel);

		JLabel usuarioLabel = new JLabel("Usuário");
		usuarioLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usuarioLabel.setBounds(164, 220, 100, 14);
		painelMenor.add(usuarioLabel);

		usuarioBox = new JTextField();
		usuarioBox.setBounds(164, 239, 382, 46);
		painelMenor.add(usuarioBox);

		JLabel senhaLabel = new JLabel("Senha");
		senhaLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		senhaLabel.setBounds(164, 303, 100, 14);
		painelMenor.add(senhaLabel);

		senhaBox = new JPasswordField();
		senhaBox.setBounds(164, 328, 382, 46);
		painelMenor.add(senhaBox);

		lembrarCheck = new JCheckBox("Lembre-se de mim");
		lembrarCheck.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lembrarCheck.setBounds(164, 375, 200, 20);
		lembrarCheck.setOpaque(false);
		lembrarCheck.setForeground(Color.WHITE);
		painelMenor.add(lembrarCheck);

		JButton acessarBtn = new JButton("Acessar");
		acessarBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		acessarBtn.setForeground(Color.WHITE);
		acessarBtn.setBackground(new Color(73, 73, 200));
		acessarBtn.setBounds(275, 410, 150, 46);
		acessarBtn.addActionListener(e -> autenticar());
		painelMenor.add(acessarBtn);

		JPanel cabecalho = new JPanel();
		cabecalho.setBackground(new Color(255, 105, 97));
		cabecalho.setBounds(0, 0, 847, 40);
		cabecalho.setLayout(null);
		telaPadrao.add(cabecalho);

		JButton voltarBtn = new JButton("Voltar");
		voltarBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		voltarBtn.setForeground(Color.WHITE);
		voltarBtn.setBackground(new Color(73, 73, 200));
		voltarBtn.setBounds(748, 11, 89, 23);
		voltarBtn.addActionListener(e -> {
			dispose();
			new TelaInicial().setVisible(true);
		});
		cabecalho.add(voltarBtn);
	}

	private void autenticar() {
		String nome = usuarioBox.getText();
		String senha = new String(senhaBox.getPassword());

		Socorrista logado = controller.autenticar(nome, senha);

		if (logado != null) {
			JOptionPane.showMessageDialog(this, "Login bem-sucedido! Bem-vindo, " + logado.getNome());

			if (lembrarCheck.isSelected()) {
				prefs.put("usuario", nome);
				prefs.put("senha", senha);
				prefs.putBoolean("lembrar", true);
			} else {
				prefs.remove("usuario");
				prefs.remove("senha");
				prefs.putBoolean("lembrar", false);
			}

			dispose();
			new PollingEmergencias(new TelaEmergencia());
		} else {
			JOptionPane.showMessageDialog(this, "Nome ou senha incorretos.");
		}
	}

	private static Socorrista loginAutomatico() {
		if (!prefs.getBoolean("lembrar", false)) return null;

		String usuarioSalvo = prefs.get("usuario", null);
		String senhaSalva = prefs.get("senha", null);

		if (usuarioSalvo != null && senhaSalva != null) {
			return controller.autenticar(usuarioSalvo, senhaSalva);
		}
		return null;
	}
}
