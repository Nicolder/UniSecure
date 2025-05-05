package Unisecure.view;

import javax.swing.*;
import java.awt.*;

public class TelaEmergencia extends JFrame {
    private JLabel labelUsuario;
    private JPanel painelAlerta;
    private JLabel labelLocalidade;
    private JLabel labelOcorrencia;

    public TelaEmergencia() {
        setTitle("Home Brigadistas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // TOPO
        JPanel barraTopo = new JPanel();
        barraTopo.setBackground(Color.decode("#FF6B6B"));
        barraTopo.setPreferredSize(new Dimension(getWidth(), 50));
        barraTopo.setLayout(new FlowLayout(FlowLayout.RIGHT));
        barraTopo.add(new JLabel(new ImageIcon("icone.png"))); // Substituir por ícone real
        add(barraTopo, BorderLayout.NORTH);

        // CENTRO
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(Color.decode("#B3E5FC"));
        add(painelCentral, BorderLayout.CENTER);

        labelUsuario = new JLabel("Olá!");
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
        botaoAlerta.addActionListener(e -> painelAlerta.setVisible(false));
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