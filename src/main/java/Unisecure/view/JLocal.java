package Unisecure.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class JLocal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLocal frame = new JLocal();
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
	public JLocal() {
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
		
		JLabel lblLocalDoOcorrido = new JLabel("LOCAL DO OCORRIDO");
		lblLocalDoOcorrido.setForeground(new Color(73, 73, 200));
		lblLocalDoOcorrido.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblLocalDoOcorrido.setBounds(303, 51, 241, 50);
		contentPane.add(lblLocalDoOcorrido);
		
		JLabel lblBloco = new JLabel("BLOCO:");
		lblBloco.setForeground(new Color(165, 22, 26));
		lblBloco.setForeground(new Color(165, 22, 26));
		lblBloco.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblBloco.setBounds(49, 140, 72, 31);
		contentPane.add(lblBloco);
		
		JLabel lblAndar = new JLabel("ANDAR:");
		lblAndar.setForeground(new Color(165, 22, 26));
		lblAndar.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAndar.setBounds(49, 324, 72, 31);
		contentPane.add(lblAndar);
		
		JButton btnConfirm = new JButton("Confirmar");
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnConfirm.setBackground(new Color(73, 73, 200));
		btnConfirm.setBounds(359, 527, 129, 50);
		contentPane.add(btnConfirm);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(59, 182, 116, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblA = new JLabel("A");
		lblA.setBounds(10, 0, 33, 49);
		lblA.setForeground(new Color(0, 0, 0));
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panel.add(lblA);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton.setBounds(77, 0, 39, 49);
		panel.add(rdbtnNewRadioButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(null);
		panel_1.setBounds(206, 182, 116, 50);
		contentPane.add(panel_1);
		
		JLabel lblB = new JLabel("B");
		lblB.setForeground(Color.BLACK);
		lblB.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblB.setBounds(10, 0, 33, 49);
		panel_1.add(lblB);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("");
		rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_1.setBounds(77, 0, 39, 49);
		panel_1.add(rdbtnNewRadioButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(null);
		panel_2.setBounds(355, 182, 116, 50);
		contentPane.add(panel_2);
		
		JLabel lblC = new JLabel("C");
		lblC.setForeground(Color.BLACK);
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblC.setBounds(10, 0, 33, 49);
		panel_2.add(lblC);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("");
		rdbtnNewRadioButton_2.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_2.setBounds(77, 0, 39, 49);
		panel_2.add(rdbtnNewRadioButton_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(null);
		panel_3.setBounds(658, 182, 116, 50);
		contentPane.add(panel_3);
		
		JLabel lblEf = new JLabel("E/F");
		lblEf.setForeground(Color.BLACK);
		lblEf.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblEf.setBounds(10, 0, 61, 49);
		panel_3.add(lblEf);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("");
		rdbtnNewRadioButton_3.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_3.setBounds(77, 0, 39, 49);
		panel_3.add(rdbtnNewRadioButton_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(null);
		panel_4.setBounds(506, 182, 116, 50);
		contentPane.add(panel_4);
		
		JLabel lblD = new JLabel("D");
		lblD.setForeground(Color.BLACK);
		lblD.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblD.setBounds(10, 0, 33, 49);
		panel_4.add(lblD);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("");
		rdbtnNewRadioButton_4.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_4.setBounds(77, 0, 39, 49);
		panel_4.add(rdbtnNewRadioButton_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(null);
		panel_5.setBounds(59, 366, 116, 50);
		contentPane.add(panel_5);
		
		JLabel lblA_1 = new JLabel("1");
		lblA_1.setForeground(Color.BLACK);
		lblA_1.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblA_1.setBounds(10, 0, 33, 49);
		panel_5.add(lblA_1);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("");
		rdbtnNewRadioButton_5.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_5.setBounds(77, 0, 39, 49);
		panel_5.add(rdbtnNewRadioButton_5);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(null);
		panel_1_1.setBounds(206, 366, 116, 50);
		contentPane.add(panel_1_1);
		
		JLabel lblB_1 = new JLabel("2");
		lblB_1.setForeground(Color.BLACK);
		lblB_1.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblB_1.setBounds(10, 0, 33, 49);
		panel_1_1.add(lblB_1);
		
		JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("");
		rdbtnNewRadioButton_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_1_1.setBounds(77, 0, 39, 49);
		panel_1_1.add(rdbtnNewRadioButton_1_1);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(null);
		panel_2_1.setBounds(355, 366, 116, 50);
		contentPane.add(panel_2_1);
		
		JLabel lblC_1 = new JLabel("3");
		lblC_1.setForeground(Color.BLACK);
		lblC_1.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblC_1.setBounds(10, 0, 33, 49);
		panel_2_1.add(lblC_1);
		
		JRadioButton rdbtnNewRadioButton_2_1 = new JRadioButton("");
		rdbtnNewRadioButton_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_2_1.setBounds(77, 0, 39, 49);
		panel_2_1.add(rdbtnNewRadioButton_2_1);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setLayout(null);
		panel_4_1.setBorder(null);
		panel_4_1.setBounds(506, 366, 116, 50);
		contentPane.add(panel_4_1);
		
		JLabel lblD_1 = new JLabel("4");
		lblD_1.setForeground(Color.BLACK);
		lblD_1.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblD_1.setBounds(10, 0, 33, 49);
		panel_4_1.add(lblD_1);
		
		JRadioButton rdbtnNewRadioButton_4_1 = new JRadioButton("");
		rdbtnNewRadioButton_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_4_1.setBounds(77, 0, 39, 49);
		panel_4_1.add(rdbtnNewRadioButton_4_1);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(null);
		panel_3_1.setBounds(658, 366, 116, 50);
		contentPane.add(panel_3_1);
		
		JLabel lblCo = new JLabel("CO");
		lblCo.setForeground(Color.BLACK);
		lblCo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblCo.setBounds(10, 0, 61, 49);
		panel_3_1.add(lblCo);
		
		JRadioButton rdbtnNewRadioButton_3_1 = new JRadioButton("");
		rdbtnNewRadioButton_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_3_1.setBounds(77, 0, 39, 49);
		panel_3_1.add(rdbtnNewRadioButton_3_1);
		
		
	}
}
