package Unisecure.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        setTitle("Unisecure - Emergência");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);

        // O painel principal continua com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(159, 222, 255));

        // --- TOPO ---
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        topBar.setBackground(new Color(255, 105, 97));
        // ... (código do botão de login permanece o mesmo)
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(73, 73, 200));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
        loginBtn.setFocusPainted(false);
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(25, 25, 112), 1, true),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        loginBtn.addActionListener(e -> {
            dispose();
            new TelaLogin();
        });
        topBar.add(loginBtn);
        mainPanel.add(topBar, BorderLayout.NORTH);

        // --- CENTRO ---
        // Adicionamos nosso painel customizado ao centro do BorderLayout.
        mainPanel.add(new PainelBotaoDinamico(), BorderLayout.CENTER);


        // --- BASE ---
        JLabel instrucoes = new JLabel("Clique para solicitar ajuda em situações de emergência.");
        instrucoes.setFont(new Font("Tahoma", Font.PLAIN, 16));
        instrucoes.setForeground(new Color(25, 25, 112));
        instrucoes.setHorizontalAlignment(SwingConstants.CENTER);
        instrucoes.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 10));
        mainPanel.add(instrucoes, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Um painel customizado que gerencia o redimensionamento do botão de emergência.
     */
    private class PainelBotaoDinamico extends JPanel {
        private final JButton btnEmergencia;

        public PainelBotaoDinamico() {
            super(new GridBagLayout()); // Usamos GridBagLayout para manter o botão sempre centralizado
            setOpaque(false);

            btnEmergencia = new BotaoCircular("EMERGÊNCIA");
            btnEmergencia.addActionListener(e -> {
                // Para evitar referência a 'TelaInicial.this' de dentro da classe aninhada,
                // usamos SwingUtilities para encontrar o JFrame pai e fechá-lo.
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                if (topFrame != null) {
                    topFrame.dispose();
                }
                new TelaConfirmacaoEmergencial().setVisible(true);
            });

            // Adiciona o listener que vai disparar a lógica de redimensionamento
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    // Pega o menor lado do painel para usar como diâmetro
                    int diameter = Math.min(getWidth(), getHeight());
                    diameter = (int) (diameter * 0.70); // Usa 70% do espaço para ter uma margem

                    if (diameter > 0) {
                        // Define o novo tamanho preferencial do botão
                        btnEmergencia.setPreferredSize(new Dimension(diameter, diameter));
                        // Revalida o painel para que o layout manager aplique o novo tamanho
                        revalidate();
                    }
                }
            });

            add(btnEmergencia, new GridBagConstraints()); // Adiciona o botão ao centro
        }
    }

    private class BotaoCircular extends JButton {
        public BotaoCircular(String text) {
            super(text);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isArmed()) {
                g2.setColor(new Color(220, 0, 0));
            } else if (getModel().isRollover()) {
                g2.setColor(new Color(255, 70, 70));
            } else {
                g2.setColor(new Color(255, 45, 45));
            }

            // Preencher o oval
            g2.fillOval(0, 0, getWidth(), getHeight());

            // A fonte é dimensionada com base no tamanho atual do botão
            int fontSize = getWidth() / 8;
            g2.setFont(new Font("Tahoma", Font.BOLD, fontSize));
            g2.setColor(Color.WHITE);

            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(getText());
            g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + fm.getAscent()) / 2);
            g2.dispose();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaInicial().setVisible(true);
        });
    }
}