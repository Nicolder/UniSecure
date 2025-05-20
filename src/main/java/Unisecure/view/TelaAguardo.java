package Unisecure.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaAguardo extends JFrame {

    public TelaAguardo() {
        setTitle("Unisecure - Aguarde");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(159, 222, 255)); // Azul claro
        add(mainPanel);

        // Faixa superior vermelha
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(255, 105, 97)); // Vermelho claro

        // Botão voltar
        JButton btnVoltar = new JButton("voltar");
        btnVoltar.setBackground(new Color(73, 73, 200));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnVoltar.setFocusPainted(false);
        btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVoltar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(25, 25, 112), 1, true),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));

        btnVoltar.addActionListener((ActionEvent e) -> {
            dispose(); // fecha a tela atual
            new TelaInicial().setVisible(true); // volta para a tela inicial (ajuste conforme necessário)
        });

        JPanel voltarWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        voltarWrapper.setBackground(new Color(255, 105, 97));
        voltarWrapper.add(btnVoltar);
        topBar.add(voltarWrapper, BorderLayout.EAST);
        mainPanel.add(topBar, BorderLayout.NORTH);

        // Painel central com imagem
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        JLabel imagemLabel = new JLabel();

        ImageIcon icone = new ImageIcon("src/main/resources/socorrista.png"); // Coloque a imagem certa aqui
        Image imagemEscalada = icone.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imagemLabel.setIcon(new ImageIcon(imagemEscalada));

        centerPanel.add(imagemLabel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Texto inferior
        JLabel textoAguarde = new JLabel("Emergência à caminho, espere os socorristas...");
        textoAguarde.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textoAguarde.setForeground(new Color(25, 25, 112));
        textoAguarde.setHorizontalAlignment(SwingConstants.CENTER);
        textoAguarde.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        mainPanel.add(textoAguarde, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaAguardo tela = new TelaAguardo();
            tela.setVisible(true);
        });
    }
}
