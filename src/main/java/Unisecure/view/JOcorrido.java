package Unisecure.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class JOcorrido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOcorrido frame = new JOcorrido();
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
	public JOcorrido() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(JBotão.class.getResource("/view/Logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 640);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setForeground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Cabeçalho = new JPanel();
		Cabeçalho.setLayout(null);
		Cabeçalho.setForeground(Color.RED);
		Cabeçalho.setBackground(new Color(250, 90, 86));
		Cabeçalho.setAlignmentX(1.0f);
		Cabeçalho.setBounds(-21, 0, 889, 40);
		contentPane.add(Cabeçalho);
		
		JButton VoltarButao = new JButton("Voltar");
		VoltarButao.setForeground(Color.WHITE);
		VoltarButao.setFont(new Font("Tahoma", Font.BOLD, 13));
		VoltarButao.setBackground(new Color(73, 73, 200));
		VoltarButao.setAlignmentX(1.0f);
		VoltarButao.setBounds(748, 11, 89, 23);
		Cabeçalho.add(VoltarButao);
		
		JButton btnConfirm = new JButton("Confirmar");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnConfirm.setBackground(new Color(73, 73, 200));
		btnConfirm.setBounds(359, 527, 129, 50);
		contentPane.add(btnConfirm);
		
		JLabel lblOcorrido = new JLabel("OCORRIDO");
		lblOcorrido.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblOcorrido.setForeground(new Color(73, 73, 200));
		lblOcorrido.setBounds(364, 51, 118, 50);
		contentPane.add(lblOcorrido);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(102, 131, 642, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Trauma Físico");
		lblNewLabel.setFont(new Font("Dubai Medium", Font.PLAIN, 33));
		lblNewLabel.setBounds(46, 11, 409, 28);
		panel.add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton.setForeground(new Color(255, 128, 128));
		rdbtnNewRadioButton.setBounds(594, 0, 48, 50);
		panel.add(rdbtnNewRadioButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(null);
		panel_1.setBounds(102, 192, 642, 50);
		contentPane.add(panel_1);
		
		JLabel lblEmergnciaRespiratoria = new JLabel("Emergência Respiratória");
		lblEmergnciaRespiratoria.setFont(new Font("Dubai Medium", Font.PLAIN, 33));
		lblEmergnciaRespiratoria.setBounds(46, 11, 409, 28);
		panel_1.add(lblEmergnciaRespiratoria);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("");
		rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_1.setForeground(new Color(255, 128, 128));
		rdbtnNewRadioButton_1.setBounds(594, 0, 48, 50);
		panel_1.add(rdbtnNewRadioButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(null);
		panel_2.setBounds(102, 253, 642, 50);
		contentPane.add(panel_2);
		
		JLabel lblProblemaNeurologico = new JLabel("Problema Neurológico");
		lblProblemaNeurologico.setFont(new Font("Dubai Medium", Font.PLAIN, 33));
		lblProblemaNeurologico.setBounds(46, 11, 409, 28);
		panel_2.add(lblProblemaNeurologico);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("");
		rdbtnNewRadioButton_2.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_2.setForeground(new Color(255, 128, 128));
		rdbtnNewRadioButton_2.setBounds(594, 0, 48, 50);
		panel_2.add(rdbtnNewRadioButton_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(null);
		panel_3.setBounds(102, 314, 642, 50);
		contentPane.add(panel_3);
		
		JLabel lblEmergenciaCardiaca = new JLabel("Emergência Cardíaca");
		lblEmergenciaCardiaca.setFont(new Font("Dubai Medium", Font.PLAIN, 33));
		lblEmergenciaCardiaca.setBounds(46, 11, 409, 28);
		panel_3.add(lblEmergenciaCardiaca);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("");
		rdbtnNewRadioButton_3.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_3.setForeground(new Color(255, 128, 128));
		rdbtnNewRadioButton_3.setBounds(594, 0, 48, 50);
		panel_3.add(rdbtnNewRadioButton_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(null);
		panel_4.setBounds(102, 375, 642, 50);
		contentPane.add(panel_4);
		
		JLabel lblChoqueEltrico = new JLabel("Choque Elétrico");
		lblChoqueEltrico.setFont(new Font("Dubai Medium", Font.PLAIN, 33));
		lblChoqueEltrico.setBounds(46, 11, 409, 28);
		panel_4.add(lblChoqueEltrico);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("");
		rdbtnNewRadioButton_4.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_4.setForeground(new Color(255, 128, 128));
		rdbtnNewRadioButton_4.setBounds(594, 0, 48, 50);
		panel_4.add(rdbtnNewRadioButton_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(null);
		panel_5.setBounds(102, 436, 642, 50);
		contentPane.add(panel_5);
		
		JLabel lblIntoxicao = new JLabel("Intoxicação");
		lblIntoxicao.setFont(new Font("Dubai Medium", Font.PLAIN, 33));
		lblIntoxicao.setBounds(46, 11, 409, 28);
		panel_5.add(lblIntoxicao);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("");
		rdbtnNewRadioButton_5.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_5.setForeground(new Color(255, 128, 128));
		rdbtnNewRadioButton_5.setBounds(594, 0, 48, 50);
		panel_5.add(rdbtnNewRadioButton_5);
	}
}