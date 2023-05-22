package br.com.ong.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

import java.net.URI;
import java.net.URISyntaxException;

import br.com.pagamento.view.PagamentoMain;
import br.com.pessoa_fisica.dao.PessoaFisicaDao;
import br.com.ranking.result.RankingResult;


public class PrimeiraOng extends JFrame {

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

    private void abrirSite(String url) {
        try {
            URI uri = new URI(url);
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    
    public PrimeiraOng(String username) {
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
        txtBemvindoAoSistema.setText("Associação Prato Cheio");
        txtBemvindoAoSistema.setBounds(220, 20, 350, 50);
        contentPane.add(txtBemvindoAoSistema);
        txtBemvindoAoSistema.setColumns(10);

        JTextArea txtrDescricaoOng = new JTextArea();
        txtrDescricaoOng.setLineWrap(true);
        txtrDescricaoOng.setWrapStyleWord(true);
        txtrDescricaoOng.setEditable(false);
        txtrDescricaoOng.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtrDescricaoOng.setText("Bem-vindos à Associação Prato Cheio!\r\n"
        		+ "\r\n"
        		+ "A Associação Prato Cheio é uma ONG certificada como OSCIP, fundada em 2001 por um grupo de universitários preocupados com o desperdício de alimentos no Mercado Municipal de São Paulo. Seu objetivo principal é promover o acesso à alimentação adequada para pessoas em situação de vulnerabilidade e risco social.\r\n"
        		+ "\r\n"
        		+ "A atuação da Prato Cheio baseia-se em três pilares estratégicos. O primeiro é a Colheita Urbana, em que a organização coleta alimentos frescos que seriam descartados de forma segura e os redistribui para quem mais precisa. O segundo pilar é a Educação e Sistematização do Conhecimento, que visa fornecer conteúdo educativo sobre nutrição e práticas sustentáveis para diversos públicos. O terceiro pilar, Alimentação e Desenvolvimento Sustentável, busca promover sistemas alimentares justos e sustentáveis, reduzindo o desperdício de alimentos e resíduos orgânicos na cidade de São Paulo.\r\n"
        		+ "\r\n"
        		+ "Os projetos da Prato Cheio estão alinhados com os Objetivos do Desenvolvimento Sustentável da ONU, contribuindo diretamente para o combate à fome (ODS 2), a promoção de cidades e comunidades sustentáveis (ODS 11) e o consumo e produção responsáveis (ODS 12). Essas ações visam reduzir o desperdício, minimizar os impactos ambientais e levar alimentos de qualidade para pessoas em situação de vulnerabilidade social.\r\n"
        		+ "\r\n"
        		+ "Junte-se à Associação Prato Cheio nessa missão! Com o apoio de cada um, é possível fazer a diferença, nutrindo vidas e alimentando esperanças.\r\n");
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
                    
                } else if (button == primeiraOng) {
                	PrimeiraOng primeiraOng = new PrimeiraOng(username);
                	primeiraOng.setVisible(true);
                    dispose();
                } else if (button == btnDoar) {
                	PagamentoMain pagamentoMain = new PagamentoMain(username, "1");
                	pagamentoMain.setVisible(true);
                    dispose();
                } else if (button == btnAcesseOSite) {
                	abrirSite("https://pratocheio.org.br/");
                
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
