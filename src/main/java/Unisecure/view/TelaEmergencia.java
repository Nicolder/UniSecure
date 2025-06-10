package Unisecure.view;

import Unisecure.controller.PollingEmergencias;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

public class TelaEmergencia extends JFrame {
    private final JLabel labelLocalidade;
    private final JLabel labelOcorrencia;
    private final JPanel painelAlerta;
    private final JPanel painelCentral;
    private final JLabel labelAguardando; // Referência para o texto "Aguardando..."

    private final Preferences prefs = Preferences.userRoot().node("unisecure_login");

    public TelaEmergencia() {
        setTitle("Home Brigadistas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        setSize(1000, 700);
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(new Color(183, 230, 255)); // Fundo geral

        // --- TOPO ---
        JPanel topBar = new JPanel(new BorderLayout(10, 10));
        topBar.setBackground(new Color(255, 105, 97));
        topBar.setBorder(new EmptyBorder(5, 15, 5, 15));

        JButton botaoDeslogar = new JButton("Sair");
        botaoDeslogar.setBackground(new Color(73, 73, 200));
        botaoDeslogar.setForeground(Color.WHITE);
        botaoDeslogar.setFont(new Font("Tahoma", Font.BOLD, 13));
        botaoDeslogar.setFocusPainted(false);
        botaoDeslogar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoDeslogar.addActionListener(e -> {
            dispose();
            prefs.remove("usuario");
            prefs.remove("senha");
            new TelaInicial().setVisible(true);
        });
        topBar.add(botaoDeslogar, BorderLayout.WEST);
        contentPane.add(topBar, BorderLayout.NORTH);

        // --- CENTRO (Tela de Espera) ---
        painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setOpaque(false); // Transparente para mostrar o fundo azul

        labelAguardando = new JLabel("Aguardando novas emergências...");
        labelAguardando.setFont(new Font("SansSerif", Font.BOLD, 24));
        labelAguardando.setForeground(new Color(0, 51, 102));
        painelCentral.add(labelAguardando, new GridBagConstraints());
        contentPane.add(painelCentral, BorderLayout.CENTER);

        // --- PAINEL DE ALERTA (DIREITA) ---
        painelAlerta = new JPanel();
        painelAlerta.setLayout(new BoxLayout(painelAlerta, BoxLayout.Y_AXIS));
        painelAlerta.setBackground(new Color(255, 105, 97));
        painelAlerta.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelAlerta.setPreferredSize(new Dimension(350, 0)); // Largura preferencial
        painelAlerta.setVisible(false); // Começa invisível

        // Componentes do alerta
        JLabel tituloAlerta = new JLabel("ALERTA DE EMERGÊNCIA");
        tituloAlerta.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloAlerta.setFont(new Font("SansSerif", Font.BOLD, 22));
        tituloAlerta.setForeground(Color.WHITE);
        painelAlerta.add(tituloAlerta);
        painelAlerta.add(Box.createVerticalStrut(25));

        labelLocalidade = new JLabel();
        painelAlerta.add(criarCardAlerta("Localidade:", labelLocalidade));
        painelAlerta.add(Box.createVerticalStrut(15));
        labelOcorrencia = new JLabel();
        painelAlerta.add(criarCardAlerta("Ocorrência:", labelOcorrencia));
        painelAlerta.add(Box.createVerticalGlue());

        JButton botaoAlerta = new JButton("Fechar");
        botaoAlerta.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoAlerta.setBackground(Color.WHITE);
        botaoAlerta.setForeground(new Color(128, 0, 0));
        botaoAlerta.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoAlerta.addActionListener(e -> esconderAlerta());
        painelAlerta.add(botaoAlerta);

        contentPane.add(painelAlerta, BorderLayout.EAST);
    }

    private JPanel criarCardAlerta(String titulo, JLabel valorLabel) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(255, 235, 238));
        card.setBorder(new EmptyBorder(10, 15, 10, 15));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        tituloLabel.setForeground(new Color(0x000080));
        tituloLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        valorLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        valorLabel.setForeground(new Color(0x00004D));
        valorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(tituloLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(valorLabel);

        return card;
    }

    // *** MÉTODO CORRIGIDO ***
    public void exibirAlerta(String localidade, String ocorrencia) {
        labelLocalidade.setText("<html><p style='width:220px'>" + localidade + "</p></html>");
        labelOcorrencia.setText("<html><p style='width:220px'>" + ocorrencia + "</p></html>");

        // Apenas esconde o texto, não o painel inteiro
        labelAguardando.setVisible(false);
        painelAlerta.setVisible(true);

        // Revalida e redesenha o container para aplicar as mudanças
        revalidate();
        repaint();
    }

    public void esconderAlerta() {
        painelAlerta.setVisible(false);
        // Mostra o texto "Aguardando..." novamente
        labelAguardando.setVisible(true);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaEmergencia tela = new TelaEmergencia();
            tela.setVisible(true);
            new PollingEmergencias(tela);
        });
    }
}