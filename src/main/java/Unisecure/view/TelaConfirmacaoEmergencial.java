package Unisecure.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaConfirmacaoEmergencial extends JFrame {

    public TelaConfirmacaoEmergencial() {
        setTitle("Confirmação Emergencial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(159, 222, 255)); // azul claro

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // margem entre os componentes
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        // Título
        JLabel tituloLabel = new JLabel("CONFIRMAÇÃO EMERGENCIAL");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(48, 25, 166)); // azul escuro
        gbc.gridy = 0;
        add(tituloLabel, gbc);

        // Ícone de alerta
        ImageIcon alertaIcone = new ImageIcon(getClass().getResource("/alerta.png"));
        Image alertaImagem = alertaIcone.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel imagemLabel = new JLabel(new ImageIcon(alertaImagem));
        gbc.gridy = 1;
        add(imagemLabel, gbc);

        // Texto de aviso
        JLabel avisoLabel = new JLabel("Em caso de alarme falso punições serão aplicadas!");
        avisoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        avisoLabel.setForeground(new Color(48, 25, 166));
        gbc.gridy = 2;
        add(avisoLabel, gbc);

        // Painel de botões
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        botoesPanel.setOpaque(false); // fundo transparente

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setBackground(Color.RED);
        cancelarButton.setForeground(Color.WHITE);
        cancelarButton.setFocusPainted(false);
        cancelarButton.setPreferredSize(new Dimension(120, 40));
        cancelarButton.setFont(new Font("Arial", Font.BOLD, 16));

        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.setBackground(new Color(48, 25, 166));
        confirmarButton.setForeground(Color.WHITE);
        confirmarButton.setFocusPainted(false);
        confirmarButton.setPreferredSize(new Dimension(120, 40));
        confirmarButton.setFont(new Font("Arial", Font.BOLD, 16));

        // Ações dos botões (pode personalizar)
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaInicial().setVisible(true);
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaAcidente().setVisible(true);
            }
        });

        botoesPanel.add(cancelarButton);
        botoesPanel.add(confirmarButton);

        gbc.gridy = 3;
        add(botoesPanel, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaConfirmacaoEmergencial::new);
    }
}
