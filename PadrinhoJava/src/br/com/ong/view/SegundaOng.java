package br.com.ong.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import br.com.pagamento.view.PagamentoMain;
import br.com.pessoa_fisica.dao.PessoaFisicaDao;
import br.com.ranking.result.RankingResult;

public class SegundaOng extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBemvindoAoSistema;
    
    private JButton createStyledButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false); // Remove o contorno de foco quando o botão é selecionado
        button.setBorderPainted(false); // Remove a borda do botão
        // Outras configurações de estilo desejadas, como fonte, tamanho, etc.
        return button;
    }
    
    private static void abrirSite(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public SegundaOng(String username) {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 607, 463);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        Border borda = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED),  // Borda interna
                BorderFactory.createEmptyBorder(5, 5, 5, 5) // Margens
        );

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton telaPrincipalOng = createStyledButton("Início", new Color(186, 215, 233), Color.BLACK);
        telaPrincipalOng.setBounds(10, 10, 144, 75);
        telaPrincipalOng.setBorder(borda);
        contentPane.add(telaPrincipalOng);

        JButton primeiraOng = createStyledButton("Prato Cheio", new Color(127, 205, 145), Color.WHITE);

        primeiraOng.setBounds(10, 95, 144, 75);
        contentPane.add(primeiraOng);

        JButton segundaOng = createStyledButton("Cidade Sem Fome", new Color(127, 205, 145), Color.WHITE);
        segundaOng.setBounds(10, 177, 144, 75);
        contentPane.add(segundaOng);

        JButton terceiraOng = createStyledButton("Banco de Alimentos", new Color(127, 205, 145), Color.WHITE);
        terceiraOng.setBounds(10, 261, 144, 75);
        contentPane.add(terceiraOng);

        JButton ranking = createStyledButton("Ranking", new Color(186, 215, 233), Color.BLACK);
        ranking.setBounds(10, 346, 144, 75);
        	    contentPane.add(ranking);
        	    
        txtBemvindoAoSistema = new JTextField();
        txtBemvindoAoSistema.setEditable(false);
        txtBemvindoAoSistema.setHorizontalAlignment(SwingConstants.CENTER);
        txtBemvindoAoSistema.setFont(new Font("Tahoma", Font.BOLD, 16));
        txtBemvindoAoSistema.setText("Cidade Sem Fome");
        txtBemvindoAoSistema.setBounds(220, 20, 350, 50);
        contentPane.add(txtBemvindoAoSistema);
        txtBemvindoAoSistema.setColumns(10);

        JTextArea txtrDescricaoOng = new JTextArea();
        txtrDescricaoOng.setLineWrap(true);
        txtrDescricaoOng.setWrapStyleWord(true);
        txtrDescricaoOng.setEditable(false);
        txtrDescricaoOng.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtrDescricaoOng.setText("A ONG \"Cidade Sem Fome\" é uma organização brasileira que trabalha para combater a fome e promover a segurança alimentar. Eles implementam o programa \"Hortas Cidadãs\", que consiste em criar hortas comunitárias em áreas urbanas, como praças e terrenos abandonados. Essas hortas são administradas pelos moradores das comunidades, permitindo que eles cultivem alimentos saudáveis para o seu consumo e venda.\r\n"
        		+ "\r\n"
        		+ "Além disso, a ONG também oferece capacitação e educação alimentar para as comunidades, ensinando técnicas de cultivo, nutrição e como aproveitar integralmente os alimentos. O objetivo é capacitar as pessoas e proporcionar autonomia, fortalecendo a segurança alimentar e impulsionando a economia local.\r\n"
        		+ "\r\n"
        		+ "A missão da \"Cidade Sem Fome\" é construir um modelo de desenvolvimento sustentável e inclusivo, no qual as comunidades tenham acesso a alimentos saudáveis, ao mesmo tempo em que cuidam do meio ambiente e melhoram sua qualidade de vida.");
        txtrDescricaoOng.setBounds(220, 95, 350, 325);
        JScrollPane scrollPane = new JScrollPane(txtrDescricaoOng);
        scrollPane.setBounds(220, 95, 350, 252);
        contentPane.add(scrollPane);
        
        JButton btnDoar = createStyledButton("Doe!", new Color(186, 215, 233), Color.BLACK);
        btnDoar.setBounds(258, 361, 131, 44);
        contentPane.add(btnDoar);
        
        JButton btnAcesseOSite = createStyledButton("Acesse o site!", new Color(186, 215, 233), Color.BLACK);
        btnAcesseOSite.setBounds(419, 361, 131, 44);
        contentPane.add(btnAcesseOSite);

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Object button = actionEvent.getSource();
                if (button == telaPrincipalOng) {
                	TelaPrincipal telaPrincipal = new TelaPrincipal(username);
                	telaPrincipal.setVisible(true);
                    dispose();
                } else if (button == btnDoar) {
                    	PagamentoMain pagamentoMain = new PagamentoMain(username, "2");
                    	pagamentoMain.setVisible(true);
                        dispose();
                } else if (button == btnAcesseOSite) {
                	abrirSite("https://www.phomenta.com.br/ong-certificada/instituto-regeneracao-global");
                    
                } else if (button == primeiraOng) {
                	PrimeiraOng primeiraOng = new PrimeiraOng(username);
                	primeiraOng.setVisible(true);
                    dispose();
                } else if (button == segundaOng) {
                	SegundaOng segundaOng = new SegundaOng(username);
                	segundaOng.setVisible(true);
                    dispose();
                } else if (button == terceiraOng) {
                	TerceiraOng terceiraOng = new TerceiraOng(username);
                	terceiraOng.setVisible(true);
                    dispose();
                } else if (button == ranking) {
                	PessoaFisicaDao pfDao = new PessoaFisicaDao();
                    List<RankingResult> rs = null;
                    try {
                        rs = pfDao.consultarRanking();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        // Cria uma string para armazenar o resultado
                        String resultado = "Ranking dos doadores:\n\n";

                        // Itera sobre o resultado e adiciona cada linha na string de resultado
                        for(int i = 0; i < rs.size(); i++) {
                            RankingResult pf = rs.get(i);
                            resultado += String.format("%d. %s: R$%.2f\n", (i + 1), pf.getNome(), pf.getTotalDepositado());
                        }

                        // Define o resultado na JTextArea
                        Ranking rankingView = new Ranking(resultado, username);
                        rankingView.setVisible(true);
                        dispose();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                }
            
        };

        telaPrincipalOng.addActionListener(actionListener);
        primeiraOng.addActionListener(actionListener);
        segundaOng.addActionListener(actionListener);
        terceiraOng.addActionListener(actionListener);
        ranking.addActionListener(actionListener);
        btnDoar.addActionListener(actionListener);
        btnAcesseOSite.addActionListener(actionListener);
    }
}
