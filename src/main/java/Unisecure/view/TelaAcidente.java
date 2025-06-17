package Unisecure.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaAcidente extends JFrame {

    private final JCheckBox checkTrauma, checkRespiratoria, checkNeuro, checkCardiaca, checkChoque, checkIntoxicacao;

    public TelaAcidente() {
        setTitle("Chamado de Emergência");
        setSize(700, 635);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 400));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- PAINEL DO TOPO ---
        JPanel topo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topo.setBackground(new Color(255, 105, 97));

        JButton voltar = new JButton("Voltar");
        voltar.setBackground(new Color(65, 66, 166));
        voltar.setForeground(Color.WHITE);
        voltar.setBorderPainted(false);
        voltar.setFocusPainted(false);
        voltar.addActionListener(e -> {
            dispose();
            new TelaInicial().setVisible(true);
        });
        topo.add(voltar);
        add(topo, BorderLayout.NORTH);

        // --- PAINEL CENTRAL ---
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBackground(new Color(159, 222, 255));
        painelCentral.setBorder(new EmptyBorder(10, 30, 10, 30));

        JLabel titulo = new JLabel("TIPO DE OCORRÊNCIA");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(new Color(54, 63, 150));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(new EmptyBorder(10, 0, 20, 0));
        painelCentral.add(titulo);

        // Painel para a lista de ocorrências, para colocar dentro do ScrollPane
        JPanel painelOcorrencias = new JPanel();
        painelOcorrencias.setLayout(new BoxLayout(painelOcorrencias, BoxLayout.Y_AXIS));
        painelOcorrencias.setBackground(new Color(159, 222, 255));

        // Criação dos blocos de ocorrência
        checkTrauma = new JCheckBox();
        painelOcorrencias.add(criarBlocoOcorrencia("TRAUMA FÍSICO", "/injured-athlete[1] 1.png", checkTrauma));
        painelOcorrencias.add(Box.createRigidArea(new Dimension(0, 10)));

        checkRespiratoria = new JCheckBox();
        painelOcorrencias.add(criarBlocoOcorrencia("EMERGÊNCIA RESPIRATÓRIA", "/image 2.png", checkRespiratoria));
        painelOcorrencias.add(Box.createRigidArea(new Dimension(0, 10)));

        checkNeuro = new JCheckBox();
        painelOcorrencias.add(criarBlocoOcorrencia("PROBLEMA NEUROLÓGICO", "/image 3.png", checkNeuro));
        painelOcorrencias.add(Box.createRigidArea(new Dimension(0, 10)));

        checkCardiaca = new JCheckBox();
        painelOcorrencias.add(criarBlocoOcorrencia("EMERGÊNCIA CARDÍACA", "/image 4.png", checkCardiaca));
        painelOcorrencias.add(Box.createRigidArea(new Dimension(0, 10)));

        checkChoque = new JCheckBox();
        painelOcorrencias.add(criarBlocoOcorrencia("CHOQUE ELÉTRICO", "/image 5.png", checkChoque));
        painelOcorrencias.add(Box.createRigidArea(new Dimension(0, 10)));

        checkIntoxicacao = new JCheckBox();
        painelOcorrencias.add(criarBlocoOcorrencia("INTOXICAÇÃO", "/image6.png", checkIntoxicacao));

        // Adiciona a lista de ocorrências a um painel com rolagem
        JScrollPane scrollPane = new JScrollPane(painelOcorrencias);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(159, 222, 255));
        painelCentral.add(scrollPane);

        add(painelCentral, BorderLayout.CENTER);

        // --- PAINEL INFERIOR (BOTÃO) ---
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setBackground(new Color(159, 222, 255));
        painelBotao.setBorder(new EmptyBorder(10, 0, 20, 0));

        JButton confirmar = new JButton("Confirmar");
        confirmar.setFont(new Font("SansSerif", Font.BOLD, 18));
        confirmar.setBackground(new Color(54, 63, 150));
        confirmar.setForeground(Color.WHITE);
        confirmar.setPreferredSize(new Dimension(200, 50));
        confirmar.setBorderPainted(false);
        confirmar.addActionListener(e -> {
            List<String> ocorrenciasSelecionadas = getOcorrencias();
            new TelaLocal(ocorrenciasSelecionadas).setVisible(true);
            this.dispose();
        });
        painelBotao.add(confirmar);
        add(painelBotao, BorderLayout.SOUTH);
    }

    private JPanel criarBlocoOcorrencia(String texto, String nomeImagem, JCheckBox checkBox) {
        JPanel bloco = new JPanel(new BorderLayout(10, 0)); // BorderLayout para alinhar
        bloco.setBackground(Color.WHITE);
        bloco.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Altura fixa, largura flexível
        bloco.setBorder(new EmptyBorder(5, 10, 5, 10));

        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bloco.add(label, BorderLayout.CENTER);

        JPanel painelIconeCheck = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        painelIconeCheck.setOpaque(false);

        JLabel img = new JLabel();
        try {
            Image imagemRedimensionada = new ImageIcon(getClass().getResource(nomeImagem)).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            img.setIcon(new ImageIcon(imagemRedimensionada));
            painelIconeCheck.add(img);
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + nomeImagem);
        }

        checkBox.setOpaque(false);
        painelIconeCheck.add(checkBox);

        bloco.add(painelIconeCheck, BorderLayout.EAST);
        return bloco;
    }

    public List<String> getOcorrencias() {
        List<String> selecionadas = new ArrayList<>();
        if (checkTrauma.isSelected()) selecionadas.add("Trauma Físico");
        if (checkRespiratoria.isSelected()) selecionadas.add("Emergência Respiratória");
        if (checkNeuro.isSelected()) selecionadas.add("Problema Neurológico");
        if (checkCardiaca.isSelected()) selecionadas.add("Emergência Cardíaca");
        if (checkChoque.isSelected()) selecionadas.add("Choque Elétrico");
        if (checkIntoxicacao.isSelected()) selecionadas.add("Intoxicação");
        return selecionadas;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaAcidente().setVisible(true));
    }
}