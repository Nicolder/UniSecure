package Unisecure.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TelaAcidente extends JFrame {

    private JCheckBox checkTrauma, checkRespiratoria, checkNeuro, checkCardiaca, checkChoque, checkIntoxicacao;
    private final List<String> tiposOcorrencia = List.of(
            "TRAUMA FÍSICO",
            "EMERGÊNCIA RESPIRATÓRIA",
            "PROBLEMA NEUROLÓGICO",
            "EMERGÊNCIA CARDÍACA",
            "CHOQUE ELÉTRICO",
            "INTOXICAÇÃO"
    );

    public TelaAcidente() {
        setTitle("Chamado de Emergência");
        setSize(700, 635);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Topo vermelho
        JPanel topo = new JPanel(null);
        topo.setBackground(new Color(255, 105, 97));
        topo.setBounds(0, 0, 700, 40);
        add(topo);

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(590, 10, 90, 25);
        voltar.setBackground(new Color(65, 66, 166));
        voltar.setForeground(Color.WHITE);
        voltar.setBorderPainted(false);
        voltar.setFocusPainted(false);
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaConfirmacaoEmergencial().setVisible(true);
            }
        });
        topo.add(voltar);

        // Fundo azul claro
        JPanel fundo = new JPanel(null);
        fundo.setBackground(new Color(159, 222, 255));
        fundo.setBounds(0, 40, 700, 600);
        add(fundo);

        JLabel titulo = new JLabel("OCORRIDO", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(new Color(54, 63, 150));
        titulo.setBounds(0, 20, 700, 40);
        fundo.add(titulo);

        // Criação dos blocos de ocorrência
        checkTrauma = criarBlocoOcorrencia(fundo, "TRAUMA FÍSICO", 80, "/injured-athlete[1] 1.png");
        checkRespiratoria = criarBlocoOcorrencia(fundo, "EMERGÊNCIA RESPIRATÓRIA", 140, "/image 2.png");
        checkNeuro = criarBlocoOcorrencia(fundo, "PROBLEMA NEUROLÓGICO", 200, "/image 3.png");
        checkCardiaca = criarBlocoOcorrencia(fundo, "EMERGÊNCIA CARDÍACA", 260, "/image 4.png");
        checkChoque = criarBlocoOcorrencia(fundo, "CHOQUE ELÉTRICO", 320, "/image 5.png");
        checkIntoxicacao = criarBlocoOcorrencia(fundo, "INTOXICAÇÃO", 380, "/image6.png");


        // Botão Confirmar
        JButton confirmar = new JButton("Confirmar");
        confirmar.setFont(new Font("SansSerif", Font.BOLD, 18));
        confirmar.setBackground(new Color(54, 63, 150));
        confirmar.setForeground(Color.WHITE);
        confirmar.setBounds(250, 460, 200, 50);
        confirmar.setBorderPainted(false);
        fundo.add(confirmar);

        // Ação do botão
        confirmar.addActionListener(e -> {
            List<String> ocorrenciasSelecionadas = getOcorrencias();

            if (ocorrenciasSelecionadas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selecione pelo menos uma ocorrência.");
                return;
            }

            // Envia as ocorrências para a próxima tela
            TelaLocal telaLocal = new TelaLocal(ocorrenciasSelecionadas);
            telaLocal.setVisible(true);
            this.dispose();
        });
    }

    private JCheckBox criarBlocoOcorrencia(JPanel fundo, String texto, int y, String nomeImagem) {
        JPanel bloco = new JPanel(null);
        bloco.setBackground(Color.WHITE);
        bloco.setBounds(50, y, 600, 50);
        fundo.add(bloco);

        // TEXTO primeiro
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        label.setBounds(10, 0, 250, 50); // Começa na esquerda
        bloco.add(label);

        // ÍCONE depois do texto
        JLabel img = new JLabel();
        img.setBounds(480, 5, 40, 40); // à direita do texto
        Image imagemRedimensionada = new ImageIcon(getClass().getResource(nomeImagem)).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        img.setIcon(new ImageIcon(imagemRedimensionada));
        bloco.add(img);

        // CHECKBOX à direita do ícone
        JCheckBox check = new JCheckBox();
        check.setBounds(540, 15, 20, 20);
        check.setOpaque(false);
        bloco.add(check);

        return check;
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
