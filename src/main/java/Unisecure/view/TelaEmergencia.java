package Unisecure.view;

import Unisecure.controller.PollingEmergencias;
import Unisecure.dao.EmergenciaDAO;
import Unisecure.model.Emergencia;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.prefs.Preferences;

public class TelaEmergencia extends JFrame {
    private final JLabel labelLocalidade;
    private final JLabel labelOcorrencia;
    private final JPanel painelAlerta; // Painel de alerta lateral (vermelho)
    private final JPanel painelConteudoPrincipal; // Painel central para o conteúdo principal
    private final JPanel painelEmergenciasList; // Painel para listar as emergências
    private final JLabel labelGreeting; // Label para a mensagem de boas-vindas

    private final Preferences prefs = Preferences.userRoot().node("unisecure_login");
    private final EmergenciaDAO emergenciaDAO = new EmergenciaDAO(); // Instância do DAO

    public TelaEmergencia() {
        // Obter o nome de usuário salvo
        String nomeUsuario = prefs.get("usuario", "Socorrista"); // Valor padrão se não encontrar

        setTitle("Home");
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

        // Botão "Sair" permanece na barra superior
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
            prefs.putBoolean("lembrar", false);
            new TelaInicial().setVisible(true);
        });
        topBar.add(botaoDeslogar, BorderLayout.EAST);
        contentPane.add(topBar, BorderLayout.NORTH);


        // --- CONTEÚDO PRINCIPAL ---
        painelConteudoPrincipal = new JPanel();
        painelConteudoPrincipal.setLayout(new BoxLayout(painelConteudoPrincipal, BoxLayout.Y_AXIS));
        painelConteudoPrincipal.setOpaque(false);
        painelConteudoPrincipal.setBorder(new EmptyBorder(20, 50, 20, 50));

        // Mensagem de boas-vindas
        labelGreeting = new JLabel("Olá, " + nomeUsuario + "!");
        labelGreeting.setFont(new Font("SansSerif", Font.BOLD, 22));
        labelGreeting.setForeground(new Color(48, 71, 157));
        labelGreeting.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinha à esquerda
        painelConteudoPrincipal.add(labelGreeting);
        painelConteudoPrincipal.add(Box.createVerticalStrut(15)); // Espaço abaixo da mensagem de boas-vindas

        // Título "Últimas emergências"
        JLabel tituloEmergencias = new JLabel("Últimas emergências");
        tituloEmergencias.setFont(new Font("SansSerif", Font.BOLD, 20));
        tituloEmergencias.setForeground(new Color(0, 51, 102));
        tituloEmergencias.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinha à esquerda
        painelConteudoPrincipal.add(tituloEmergencias);
        painelConteudoPrincipal.add(Box.createVerticalStrut(15)); // Espaço abaixo do título

        // Painel para as emergências individuais
        painelEmergenciasList = new JPanel();
        painelEmergenciasList.setLayout(new BoxLayout(painelEmergenciasList, BoxLayout.Y_AXIS));
        painelEmergenciasList.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(painelEmergenciasList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove a borda padrão do scroll
        scrollPane.getViewport().setBackground(new Color(183, 230, 255)); // Fundo do viewport
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinha o scroll à esquerda

        painelConteudoPrincipal.add(scrollPane);
        painelConteudoPrincipal.add(Box.createVerticalGlue());

        contentPane.add(painelConteudoPrincipal, BorderLayout.CENTER);


        // --- PAINEL DE ALERTA (DIREITA) ---
        painelAlerta = new JPanel();
        painelAlerta.setLayout(new BoxLayout(painelAlerta, BoxLayout.Y_AXIS));
        painelAlerta.setBackground(new Color(255, 105, 97));
        painelAlerta.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelAlerta.setPreferredSize(new Dimension(350, 0));
        painelAlerta.setVisible(false);

        // Componentes do alerta (permanecem inalterados)
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

        // Carrega as emergências existentes ao iniciar a tela
        carregarEmergenciasExistentes();

        setVisible(true);
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

    private JPanel criarEmergenciaCard(Emergencia emergencia) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(10, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(10, 15, 10, 15)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel detalhesPanel = new JPanel();
        detalhesPanel.setOpaque(false);
        detalhesPanel.setLayout(new BoxLayout(detalhesPanel, BoxLayout.Y_AXIS));

        JLabel localidadeLabel = new JLabel("<html><b>Local:</b> " + emergencia.getLocalidade() + "</html>");
        localidadeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        localidadeLabel.setForeground(new Color(50, 50, 50));
        detalhesPanel.add(localidadeLabel);

        JLabel ocorrenciaLabel = new JLabel("<html><b>Tipo:</b> " + emergencia.getTiposEmergencia() + "</html>");
        ocorrenciaLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        ocorrenciaLabel.setForeground(new Color(80, 80, 80));
        detalhesPanel.add(ocorrenciaLabel);

        card.add(detalhesPanel, BorderLayout.CENTER);

        JLabel dataHoraLabel = new JLabel(emergencia.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        dataHoraLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        dataHoraLabel.setForeground(new Color(150, 150, 150));
        card.add(dataHoraLabel, BorderLayout.EAST);

        return card;
    }

    public void adicionarNovaEmergencia(String localidade, String ocorrencia) {
        Emergencia novaEmergencia = new Emergencia(localidade, ocorrencia);
        JPanel novoCard = criarEmergenciaCard(novaEmergencia);
        painelEmergenciasList.add(novoCard, 0);
        painelEmergenciasList.add(Box.createRigidArea(new Dimension(0, 10)), 1);

        labelLocalidade.setText("<html><p style='width:220px'>" + localidade + "</p></html>");
        labelOcorrencia.setText("<html><p style='width:220px'>" + ocorrencia + "</p></html>");
        painelAlerta.setVisible(true);

        revalidate();
        repaint();
    }

    private void carregarEmergenciasExistentes() {
        List<Emergencia> emergencias = emergenciaDAO.listarTodas();
        if (!emergencias.isEmpty()) {
            for (Emergencia emergencia : emergencias) {
                JPanel card = criarEmergenciaCard(emergencia);
                painelEmergenciasList.add(card);
                painelEmergenciasList.add(Box.createRigidArea(new Dimension(0, 10)));
            }
            revalidate();
            repaint();
        }
    }

    public void esconderAlerta() {
        painelAlerta.setVisible(false);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaEmergencia tela = new TelaEmergencia();
            new PollingEmergencias(tela);
        });
    }
}