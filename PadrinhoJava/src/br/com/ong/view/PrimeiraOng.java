package br.com.ong.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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


    public PrimeiraOng(String username) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 607, 463);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton telaPrincipalOng = createStyledButton("Início", new Color(205, 205, 205), Color.BLACK);
        telaPrincipalOng.setBounds(10, 10, 144, 75);
        contentPane.add(telaPrincipalOng);

        JButton primeiraOng = createStyledButton("Comida no Prato", new Color(0, 153, 51), Color.WHITE);

        primeiraOng.setBounds(10, 95, 144, 75);
        contentPane.add(primeiraOng);

        JButton segundaOng = createStyledButton("Instituto Regeneração Global (IRG)", new Color(0, 153, 51), Color.WHITE);
        segundaOng.setBounds(10, 177, 144, 75);
        contentPane.add(segundaOng);

        JButton terceiraOng = createStyledButton("Direito à alimentação", new Color(0, 153, 51), Color.WHITE);
        terceiraOng.setBounds(10, 261, 144, 75);
        contentPane.add(terceiraOng);

        JButton ranking = new JButton("Ranking");
        ranking.setBounds(10, 346, 144, 75);
        	    contentPane.add(ranking);

        txtBemvindoAoSistema = new JTextField();
        txtBemvindoAoSistema.setEditable(false);
        txtBemvindoAoSistema.setHorizontalAlignment(SwingConstants.CENTER);
        txtBemvindoAoSistema.setFont(new Font("Tahoma", Font.BOLD, 16));
        txtBemvindoAoSistema.setText("Comida no Prato");
        txtBemvindoAoSistema.setBounds(220, 20, 350, 50);
        contentPane.add(txtBemvindoAoSistema);
        txtBemvindoAoSistema.setColumns(10);

        JTextArea txtrDescricaoOng = new JTextArea();
        txtrDescricaoOng.setLineWrap(true);
        txtrDescricaoOng.setWrapStyleWord(true);
        txtrDescricaoOng.setEditable(false);
        txtrDescricaoOng.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtrDescricaoOng.setText("Comida no Prato é uma iniciativa no Brasil que busca reduzir o desperdício de alimentos. Consiste em oferecer aos clientes de restaurantes a opção de servir a quantidade de comida que desejam, evitando que se sirvam mais do que possam consumir e que se desperdicem comida. O conceito é simples: o cliente paga pelo peso da comida que se serviu no seu prato, lo que le da la liberdade de elegir a cantidad de comida que deseja comer e reduzir o desperdicio de alimentos ao mesmo tempo.\r\n"
        		+ ""
        		+ "Esta iniciativa também ajuda a los restaurantes a reduzir os custos de desperdício de alimentos, al time que permite a los clientes ahorrar dinero al não pagar por alimentos que não van a consumir. Além disso, promova uma alimentação saudável e sustentável, para que os clientes possam escolher uma variedade de alimentos e ajustar suas porções de acordo com suas necessidades.\r\n"
        		+ ""
        		+ "A iniciativa Comida no Prato tem sido adotada em vários restaurantes no Brasil e tem demonstrado ser uma forma eficaz de reduzir o desperdício de alimentos e promover uma alimentação mais consciente.");
        txtrDescricaoOng.setBounds(220, 95, 350, 325);
        JScrollPane scrollPane = new JScrollPane(txtrDescricaoOng);
        scrollPane.setBounds(220, 95, 350, 252);
        contentPane.add(scrollPane);
        
        JButton btnDoar = new JButton("Doe!");
        btnDoar.setBounds(258, 361, 131, 44);
        contentPane.add(btnDoar);
        
        JButton btnAcesseOSite = new JButton("Acesse o site!");
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
    }
}
