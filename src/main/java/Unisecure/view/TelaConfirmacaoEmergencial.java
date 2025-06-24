package Unisecure.view;

import javax.swing.*;
import java.awt.*;

public class TelaConfirmacaoEmergencial extends JFrame {

    private class RoundedButton extends JButton {
        private final Color buttonColor;
        private final int cornerRadius;

        public RoundedButton(String text, Color color, int radius) {
            super(text);
            this.buttonColor = color;
            this.cornerRadius = radius;

            // Estilização padrão para um visual limpo
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(Color.WHITE); // Texto sempre branco, como no protótipo
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Pinta o fundo do botão com os cantos arredondados
            g2.setColor(buttonColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

            // Centraliza e pinta o texto do botão
            g2.setColor(getForeground());
            g2.setFont(getFont());
            FontMetrics metrics = g2.getFontMetrics(getFont());
            int x = (getWidth() - metrics.stringWidth(getText())) / 2;
            int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
            g2.drawString(getText(), x, y);

            g2.dispose();
        }
    }

    public TelaConfirmacaoEmergencial() {
        setTitle("Confirmação Emergencial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 635);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(159, 222, 255));

        // Retornando ao GridBagLayout original para centralizar os itens
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        // Título -
        JLabel tituloLabel = new JLabel("CONFIRMAÇÃO EMERGENCIAL");
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        tituloLabel.setForeground(new Color(48, 25, 166)); // Cor original mantida
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 20, 10);
        add(tituloLabel, gbc);

        // Ícone de alerta -
        ImageIcon alertaIcone = new ImageIcon(getClass().getResource("/alerta.png"));
        Image alertaImagem = alertaIcone.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel imagemLabel = new JLabel(new ImageIcon(alertaImagem));
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(imagemLabel, gbc);

        // Texto de aviso -
        JLabel avisoLabel = new JLabel("Em caso de alarme falso punições serão aplicadas!");
        avisoLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        avisoLabel.setForeground(new Color(48, 25, 166)); // Cor original mantida
        gbc.gridy = 2;
        add(avisoLabel, gbc);

        // Painel para os botões
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        botoesPanel.setOpaque(false);

        // Cores dos botões
        Color corCancelar = new Color(239, 83, 80); // Vermelho mais suave, fiel ao protótipo
        Color corConfirmar = new Color(48, 25, 166); // Azul original


        JButton cancelarButton = new RoundedButton("Cancelar", corCancelar, 25);
        cancelarButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        cancelarButton.setPreferredSize(new Dimension(160, 45));

        JButton confirmarButton = new RoundedButton("Confirmar", corConfirmar, 25);
        confirmarButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        confirmarButton.setPreferredSize(new Dimension(160, 45));

        // Ações dos botões -
        cancelarButton.addActionListener(e -> {
            dispose();
            new TelaInicial().setVisible(true);
        });
        confirmarButton.addActionListener(e -> {
            dispose();
            new TelaOcorrencia().setVisible(true);
        });

        botoesPanel.add(cancelarButton);
        botoesPanel.add(confirmarButton);

        gbc.gridy = 3;
        gbc.insets = new Insets(20, 10, 10, 10);
        add(botoesPanel, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaConfirmacaoEmergencial::new);
    }
}