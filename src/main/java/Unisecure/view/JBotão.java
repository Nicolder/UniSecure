package Unisecure.view;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JBotão extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JBotão frame = new JBotão();
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
	public JBotão() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JBotão.class.getResource("Logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 640);

		// Usando um layout adequado para redimensionamento
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setForeground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Alterar layout para BorderLayout
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Cabeçalho - Usar um layout adequado para redimensionamento
		JPanel Cabeçalho = new JPanel();
		Cabeçalho.setLayout(null);
		Cabeçalho.setForeground(Color.RED);
		Cabeçalho.setBackground(new Color(250, 90, 86));
		Cabeçalho.setAlignmentX(1.0f);
		Cabeçalho.setBounds(0, 0, getWidth(), 40); // Ajusta a largura com getWidth()
		contentPane.add(Cabeçalho);

		// Botão "Loguin"
		JButton LoguinButao = new JButton("Loguin");
		LoguinButao.setForeground(Color.WHITE);
		LoguinButao.setFont(new Font("Tahoma", Font.BOLD, 13));
		LoguinButao.setBackground(new Color(73, 73, 200));
		LoguinButao.setAlignmentX(1.0f);
		LoguinButao.setBounds(748, 11, 89, 23);
		LoguinButao.addActionListener(e -> {
			new JLogin().setVisible(true); // Abrir tela de login do socorrista
			dispose(); // Fecha essa tela
		});
		Cabeçalho.add(LoguinButao);

		// Redimensionando corretamente a janela
		setResizable(true); // Habilitar redimensionamento
	}
}
