package Unisecure.view;

import Unisecure.controller.PollingEmergencias;

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
        JButton botaoDeslogar = new JButton("Sair");
        botaoDeslogar.setBackground(new Color(73, 73, 200)); // Mesmo tom do botão login
        botaoDeslogar.setForeground(Color.WHITE);
        botaoDeslogar.setFont(new Font("Tahoma", Font.BOLD, 13));
        botaoDeslogar.setFocusPainted(false);
        botaoDeslogar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoDeslogar.setBounds(10, 11, 89, 23);
        botaoDeslogar.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

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
        painelAlerta.setBackground(new Color(255, 105, 97)); // vermelho claro
        painelAlerta.setLayout(new BoxLayout(painelAlerta, BoxLayout.Y_AXIS));
        painelAlerta.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        painelAlerta.setVisible(false);

        // Título com sombra
        JLabel tituloAlerta = new JLabel("ALERTA");
        tituloAlerta.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloAlerta.setFont(new Font("SansSerif", Font.BOLD, 28));
        tituloAlerta.setForeground(Color.WHITE);
        tituloAlerta.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        painelAlerta.add(tituloAlerta);

        // Ícone de sino (pode trocar por ImageIcon depois)
        JLabel iconem = new JLabel("🔔");
        iconem.setFont(new Font("SansSerif", Font.PLAIN, 48));
        iconem.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconem.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        painelAlerta.add(iconem);

        // Card de Localidade
        labelLocalidade = new JLabel("BLOCO B   SALA 210");
        painelAlerta.add(criarCardAlerta("Localidade", labelLocalidade));

        painelAlerta.add(Box.createVerticalStrut(20)); // espaçamento

        // Card de Ocorrência
        labelOcorrencia = new JLabel("CHOQUE ELÉTRICO");
        painelAlerta.add(criarCardAlerta("Ocorrência", labelOcorrencia));


        painelAlerta.add(Box.createVerticalStrut(30));

        // Botão Confirmar (opcional)
        JButton botaoAlerta = new JButton("Confirmar");
        botaoAlerta.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoAlerta.setBackground(Color.WHITE);
        botaoAlerta.setForeground(Color.RED);
        botaoAlerta.setFocusPainted(false);
        botaoAlerta.setMaximumSize(new Dimension(120, 40));
        botaoAlerta.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoAlerta.addActionListener(e -> esconderAlerta());
        painelAlerta.add(botaoAlerta);

        // Adiciona ao painel principal
        add(painelAlerta, BorderLayout.EAST);

        setVisible(true);
    }

    // Exibe alerta com dados recebidos
    public void exibirAlerta(String localidade, String ocorrencia) {
        labelLocalidade.setText(localidade);
        labelOcorrencia.setText(ocorrencia);
        painelAlerta.setVisible(true);
    }

    private JPanel criarCardAlerta(String titulo, JLabel valorLabel) {
        JPanel card = new JPanel();
        card.setBackground(new Color(0xE6F0FF));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        card.setMaximumSize(new Dimension(300, 80));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setOpaque(true);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(new Color(0xE6F0FF), 10, true)
        ));

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tituloLabel.setForeground(new Color(0x000080));
        tituloLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        valorLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        valorLabel.setForeground(new Color(0x00004D));
        valorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(tituloLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(valorLabel);

        return card;
    }


    // Oculta o alerta
    public void esconderAlerta() {
        painelAlerta.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaEmergencia tela = new TelaEmergencia();
            new PollingEmergencias(tela); // <- inicia o polling!
        });
    }
}
