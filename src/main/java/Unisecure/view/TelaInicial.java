package Unisecure.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        setTitle("Unisecure - Emergência");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(159, 222, 255)); // Azul claro

        // Topo vermelho com botão de login
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(255, 105, 97)); // Vermelho claro

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(73, 73, 200));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
        loginBtn.setFocusPainted(false);
        loginBtn.setBounds(748, 11, 89, 23);
        loginBtn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(25, 25, 112), 1, true),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));

        JPanel loginWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        loginWrapper.setBackground(new Color(255, 105, 97));
        loginWrapper.add(loginBtn);
        topBar.add(loginWrapper, BorderLayout.EAST);
        mainPanel.add(topBar, BorderLayout.NORTH);

        // Painel central para o botão circular real
        JPanel centerPanel = new JPanel(null); // Layout livre para posicionar
        centerPanel.setOpaque(false);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Botão circular
        JButton btnEmergencia = new JButton("EMERGÊNCIA") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(220, 0, 0));
                } else if (getModel().isRollover()) {
                    g.setColor(new Color(255, 70, 70));
                } else {
                    g.setColor(new Color(255, 45, 45));
                }
                g.fillOval(0, 0, getWidth(), getHeight());

                g.setColor(Color.WHITE);
                g.setFont(getFont());
                FontMetrics fm = g.getFontMetrics();
                String text = getText();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();
                g.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 5);
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };
        btnEmergencia.setFont(new Font("Tahoma", Font.BOLD, 28));
        btnEmergencia.setFocusPainted(false);
        btnEmergencia.setContentAreaFilled(false);
        btnEmergencia.setOpaque(false);
        btnEmergencia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEmergencia.setBounds(300, 80, 280, 280); // posição inicial

        centerPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent e) {
                int panelWidth = centerPanel.getWidth();
                int panelHeight = centerPanel.getHeight();
                int size = (int)(Math.min(panelWidth, panelHeight) * 0.75); // Botão maior
                int x = (panelWidth - size) / 2;
                int y = (panelHeight - size) / 3;
                btnEmergencia.setBounds(x, y, size, size);

                // Tamanho da fonte proporcional ao botão
                int fontSize = size / 9; // você pode ajustar esse fator se quiser mais/menos
                btnEmergencia.setFont(new Font("Tahoma", Font.BOLD, fontSize));
            }
        });


        centerPanel.add(btnEmergencia);

        // Texto inferior
        JLabel instrucoes = new JLabel("Clique para solicitar ajuda em situações de emergência.");
        instrucoes.setFont(new Font("Tahoma", Font.PLAIN, 16));
        instrucoes.setForeground(new Color(25, 25, 112));
        instrucoes.setHorizontalAlignment(SwingConstants.CENTER);
        instrucoes.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 10));
        mainPanel.add(instrucoes, BorderLayout.SOUTH );

        add(mainPanel);

        // Ação de clique no botão de login
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaLogin();
            }
        });

        // Exemplo de ação no botão de emergência
        btnEmergencia.addActionListener(e -> {
            dispose();
            new TelaConfirmacaoEmergencial().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaInicial tela = new TelaInicial();
            tela.setVisible(true);
        });
    }
}
