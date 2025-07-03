package Unisecure.view;

import Unisecure.dao.EmergenciaDAO;
import Unisecure.model.Emergencia;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TelaLocal extends JFrame {

    private final List<String> ocorrenciasSelecionadas;
    private final List<JCheckBox> blocosCheckboxes = new ArrayList<>();
    private final List<JCheckBox> andaresCheckboxes = new ArrayList<>();

    public TelaLocal(List<String> ocorrenciasSelecionadas) {
        this.ocorrenciasSelecionadas = ocorrenciasSelecionadas;

        setTitle("Local do Ocorrido");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 550));
        setSize(700, 635);
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();
        contentPane.setBackground(new Color(183, 230, 255));
        contentPane.setLayout(new BorderLayout());

        // --- TOPO ---
        JPanel header = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 8));
        header.setBackground(new Color(255, 105, 97));
        JButton voltarBtn = new JButton("Voltar");
        voltarBtn.setBackground(new Color(48, 71, 157));
        voltarBtn.setForeground(Color.WHITE);
        voltarBtn.setFocusPainted(false);
        voltarBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        voltarBtn.addActionListener(e -> {
            dispose();
            new TelaInicial().setVisible(true);
        });
        header.add(voltarBtn);
        contentPane.add(header, BorderLayout.NORTH);

        // --- CONTEÚDO CENTRAL ---
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        contentPane.add(centerPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;

        // Título
        JLabel titulo = new JLabel("LOCAL DO OCORRIDO");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(new Color(48, 71, 157));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5, 5, 25, 5);
        centerPanel.add(titulo, gbc);

        // Seção Bloco
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15, 5, 5, 5);
        centerPanel.add(criarLabelSecao("BLOCO:"), gbc);

        gbc.gridy++;
        gbc.insets = new Insets(5, 5, 5, 5);
        centerPanel.add(new PainelDeOpcoesDinamico(blocosCheckboxes, new String[]{"Bloco A", "Bloco B", "Bloco C", "Bloco D", "Bloco E/F"}), gbc);

        // Seção Andar
        gbc.gridy++;
        gbc.insets = new Insets(15, 5, 5, 5);
        centerPanel.add(criarLabelSecao("ANDAR:"), gbc);

        gbc.gridy++;
        gbc.insets = new Insets(5, 5, 5, 5);
        centerPanel.add(new PainelDeOpcoesDinamico(andaresCheckboxes, new String[]{"Andar 1º", "Andar 2º", "Andar 3º", "Andar 4º", "Andar CO"}), gbc);

        // Botão Confirmar
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setOpaque(false);
        JButton confirmarBtn = new JButton("Confirmar");
        confirmarBtn.setPreferredSize(new Dimension(200, 50));
        confirmarBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        confirmarBtn.setForeground(Color.WHITE);
        confirmarBtn.setBackground(new Color(48, 71, 157));
        confirmarBtn.addActionListener(e -> registrarEmergencia());
        painelBotao.add(confirmarBtn);

        gbc.gridy++;
        gbc.insets = new Insets(25, 5, 5, 5);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(painelBotao, gbc);

        // Espaçador Vertical
        gbc.gridy++;
        gbc.weighty = 1.0;
        centerPanel.add(new JPanel(){{setOpaque(false);}}, gbc);
    }

    private JLabel criarLabelSecao(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setForeground(new Color(128, 0, 0));
        return label;
    }

    private void registrarEmergencia() {
        List<String> locaisSelecionados = new ArrayList<>();
        blocosCheckboxes.stream().filter(JCheckBox::isSelected).map(AbstractButton::getText).forEach(locaisSelecionados::add);
        andaresCheckboxes.stream().filter(JCheckBox::isSelected).map(AbstractButton::getText).forEach(locaisSelecionados::add);

        if (locaisSelecionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione pelo menos um bloco ou andar.", "Local não especificado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String localFinal = String.join(", ", locaisSelecionados);
        String tiposConcatenados = ocorrenciasSelecionadas.isEmpty() ? "Não especificado" : String.join(", ", ocorrenciasSelecionadas);

        Emergencia emergencia = new Emergencia(localFinal, tiposConcatenados);
        if (new EmergenciaDAO().registrar(emergencia)) {
            JOptionPane.showMessageDialog(this, "Emergência registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new TelaAguardo().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao registrar emergência.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class PainelDeOpcoesDinamico extends JPanel {
        private final GridLayout gridLayout;
        private final int componentCount;
        private static final int PREFERRED_COMPONENT_WIDTH = 130; // Largura preferida para cada checkbox

        public PainelDeOpcoesDinamico(List<JCheckBox> checkList, String[] options) {
            this.componentCount = options.length;
            this.gridLayout = new GridLayout(0, 5, 10, 10); // Começa com 5 colunas
            setLayout(gridLayout);
            setOpaque(false);

            for (String option : options) {
                JCheckBox checkbox = criarCheckbox(option);
                checkList.add(checkbox);
                add(checkbox);
            }

            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    int panelWidth = getWidth();
                    if (panelWidth == 0) return;

                    // Calcula quantas colunas podem caber
                    int newColumns = panelWidth / PREFERRED_COMPONENT_WIDTH;
                    if (newColumns < 1) {
                        newColumns = 1;
                    }
                    if (newColumns > componentCount) {
                        newColumns = componentCount;
                    }

                    // Atualiza o layout apenas se o número de colunas mudar
                    if (gridLayout.getColumns() != newColumns) {
                        gridLayout.setColumns(newColumns);
                        revalidate(); // Re-aplica o layout
                    }
                }
            });
        }

        private JCheckBox criarCheckbox(String texto) {
            JCheckBox checkbox = new JCheckBox(texto);
            checkbox.setFont(new Font("SansSerif", Font.PLAIN, 16));
            checkbox.setBackground(Color.WHITE);
            checkbox.setOpaque(true);
            checkbox.setCursor(new Cursor(Cursor.HAND_CURSOR));
            checkbox.setBorder(new EmptyBorder(5, 10, 5, 10));
            checkbox.setHorizontalAlignment(SwingConstants.CENTER);
            checkbox.setFocusPainted(false);
            return checkbox;
        }
    }
}