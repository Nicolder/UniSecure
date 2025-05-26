package Unisecure.view;

import javax.swing.*;
import java.awt.*;
import java.util.prefs.Preferences;

public class TelaEmergencia extends JFrame {
    private JLabel labelUsuario;
    private JPanel painelAlerta;
    private JLabel labelLocalidade;
    private JLabel labelOcorrencia;

    private Preferences prefs = Preferences.userRoot().node("unisecure_login");

    public TelaEmergencia() {
        setTitle("Home Brigadistas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // TOPO
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(255, 105, 97));

        // Botão Deslogar
        JButton botaoDeslogar = new JButton("Deslogar");
        botaoDeslogar.setBackground(new Color(73, 73, 200)); // Mesmo tom do botão login
        botaoDeslogar.setForeground(Color.WHITE);
        botaoDeslogar.setFont(new Font("Tahoma", Font.BOLD, 13));
        botaoDeslogar.setFocusPainted(false);
        botaoDeslogar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoDeslogar.setBounds(10, 11, 89, 23); // mesma altura e largura do botão login
        botaoDeslogar.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        // Evento do botão
        botaoDeslogar.addActionListener(e -> {
            dispose(); // Fecha a janela atual
            prefs.remove("usuario");
            prefs.remove("senha");

            new TelaInicial().setVisible(true);
        });

        topBar.add(botaoDeslogar, BorderLayout.WEST);

        // Ícone à direita
        JLabel icone = new JLabel(new ImageIcon("logo.png")); // Substituir com seu ícone real
        JPanel painelIcone = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelIcone.setOpaque(false);
        painelIcone.add(icone);
        topBar.add(painelIcone, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // CENTRO
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(Color.decode("#B3E5FC"));
        add(painelCentral, BorderLayout.CENTER);

        labelUsuario = new JLabel("Tela de Espera");
        labelUsuario.setFont(new Font("SansSerif", Font.BOLD, 24));
        labelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        painelCentral.add(labelUsuario, BorderLayout.CENTER);

        // ALERTA
        painelAlerta = new JPanel();
        painelAlerta.setLayout(new BoxLayout(painelAlerta, BoxLayout.Y_AXIS));
        painelAlerta.setBackground(Color.decode("#FF6B6B"));
        painelAlerta.setPreferredSize(new Dimension(250, getHeight()));
        painelAlerta.setVisible(false); // inicialmente invisivel

        JLabel tituloAlerta = new JLabel("⚠ ALERTA");
        tituloAlerta.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloAlerta.setFont(new Font("SansSerif", Font.BOLD, 20));
        tituloAlerta.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        painelAlerta.add(tituloAlerta);

        labelLocalidade = new JLabel("Localidade: -");
        labelLocalidade.setFont(new Font("SansSerif", Font.PLAIN, 16));
        labelLocalidade.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelAlerta.add(labelLocalidade);

        painelAlerta.add(Box.createVerticalStrut(10));

        labelOcorrencia = new JLabel("Ocorrência: -");
        labelOcorrencia.setFont(new Font("SansSerif", Font.PLAIN, 16));
        labelOcorrencia.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelAlerta.add(labelOcorrencia);

        JButton botaoAlerta = new JButton("Confirmar");
        botaoAlerta.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoAlerta.setBounds(getWidth() - 90, 5, 80, 30);
        botaoAlerta.addActionListener(e -> esconderAlerta());
        painelAlerta.add(botaoAlerta);


        add(painelAlerta, BorderLayout.EAST);

        setVisible(true);
    }

    // Exibe alerta com dados recebidos
    public void exibirAlerta(String localidade, String ocorrencia) {
        labelLocalidade.setText("Localidade: " + localidade);
        labelOcorrencia.setText("Ocorrência: " + ocorrencia);
        painelAlerta.setVisible(true);
    }

    // Oculta o alerta
    public void esconderAlerta() {
        painelAlerta.setVisible(false);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaEmergencia());
    }
}
