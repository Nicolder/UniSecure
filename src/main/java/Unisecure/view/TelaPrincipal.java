package Unisecure.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Tela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(400, 400));

        setLayout(new BorderLayout());

        // Barra superior (vermelha)
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(255, 102, 102));
        topBar.setPreferredSize(new Dimension(getWidth(), 50));

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(getWidth() - 90, 5, 80, 30);
        btnLogin.setBackground(new Color(44, 62, 80)); // Azul escuro
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(e -> {
            new JLogin().setVisible(true); // Abrir tela de login do socorrista
            dispose(); // Fecha essa tela
        });

        add(topBar, BorderLayout.NORTH);

        // Painel principal (central, azul claro)
        JPanel centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(173, 216, 230));
            }
        };
        centerPanel.setLayout(null); // vamos posicionar manualmente
        add(centerPanel, BorderLayout.CENTER);

        // Texto informativo (base)
        JLabel infoText = new JLabel("Clique para solicitar ajuda em situações de emergência.");
        infoText.setForeground(new Color(72, 61, 139));
        infoText.setFont(new Font("Arial", Font.PLAIN, 16));
        centerPanel.add(infoText);

        // Botão Emergência
        JButton emergencyButton = new JButton("EMERGÊNCIA");
        emergencyButton.setBackground(Color.RED);
        emergencyButton.setForeground(Color.WHITE);
        emergencyButton.setFont(new Font("Arial", Font.BOLD, 24));
        emergencyButton.setFocusPainted(false);
        emergencyButton.setBorder(new RoundedBorder(999)); // circular
        centerPanel.add(emergencyButton);

        // Redimensionamento responsivo
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int panelWidth = centerPanel.getWidth();
                int panelHeight = centerPanel.getHeight();

                // Define o tamanho do botão circular com base no menor lado do painel
                int diameter = Math.min(panelWidth, panelHeight) / 2;
                emergencyButton.setSize(diameter, diameter);
                emergencyButton.setLocation((panelWidth - diameter) / 2, (panelHeight - diameter) / 2 - 30);

                emergencyButton.setBorder(new RoundedBorder(diameter / 2));

                // Posiciona o texto logo abaixo do botão
                Dimension textSize = infoText.getPreferredSize();
                infoText.setSize(textSize);
                infoText.setLocation((panelWidth - textSize.width) / 2, emergencyButton.getY() + diameter + 20);
            }
        });
    }

    // Borda arredondada customizada
    private static class RoundedBorder implements Border {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 0, 0);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(c.getBackground());
            g.drawRoundRect(x, y, width - 1, height - 1, radius * 2, radius * 2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
