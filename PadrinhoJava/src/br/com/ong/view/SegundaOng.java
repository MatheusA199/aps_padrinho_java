package br.com.ong.view;

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

public class SegundaOng extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBemvindoAoSistema;
    
    public SegundaOng(String username) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 607, 463);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton telaPrincipalOng = new JButton("Início");
        telaPrincipalOng.setBounds(10, 10, 144, 75);
        contentPane.add(telaPrincipalOng);

        JButton primeiraOng = new JButton("Comida no Prato");
        primeiraOng.setBounds(10, 95, 144, 75);
        contentPane.add(primeiraOng);

        JButton segundaOng = new JButton("Instituto Regeneração Global (IRG)");
        segundaOng.setBounds(10, 177, 144, 75);
        contentPane.add(segundaOng);

        JButton terceiraOng = new JButton("Direito à alimentação");
        terceiraOng.setBounds(10, 261, 144, 75);
        contentPane.add(terceiraOng);

        JButton ranking = new JButton("Ranking");
        ranking.setBounds(10, 346, 144, 75);
        	    contentPane.add(ranking);

        txtBemvindoAoSistema = new JTextField();
        txtBemvindoAoSistema.setEditable(false);
        txtBemvindoAoSistema.setHorizontalAlignment(SwingConstants.CENTER);
        txtBemvindoAoSistema.setFont(new Font("Tahoma", Font.BOLD, 16));
        txtBemvindoAoSistema.setText("Instituto Regeneração Global (IRG)");
        txtBemvindoAoSistema.setBounds(220, 20, 350, 50);
        contentPane.add(txtBemvindoAoSistema);
        txtBemvindoAoSistema.setColumns(10);

        JTextArea txtrDescricaoOng = new JTextArea();
        txtrDescricaoOng.setLineWrap(true);
        txtrDescricaoOng.setWrapStyleWord(true);
        txtrDescricaoOng.setEditable(false);
        txtrDescricaoOng.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtrDescricaoOng.setText("O Instituto Regeneração Global (IRG) é uma organização sem fins lucrativos brasileira que se dedica à promoção de práticas atraentes e regenerativas para a restauração e conservação do ecossistema. Fundada em 2019, a missão do IRG é inspirar e implementar soluções que trouxeram a contribuição global dos ecossistemas, visando à recuperação da biodiversidade, mitigação das mudanças climáticas e garantia da segurança alimentar.\r\n"
        		+ ""
        		+ "O IRG atua em diversas áreas, como agrofloresta, restauração de ecossistemas, desenvolvimento sustentável, tecnologias sociais e conservação da biodiversidade. Uma organização promove ações de sensibilização e conscientização da sociedade para a importância da imunidade global, além de fornecer capacitação técnica para profissionais e interessados ​​em se envolver em iniciativas motivacionais e regenerativas.\r\n"
        		+ ""
        		+ "Entre as iniciativas do IRG estão a criação de viveiros de mudas de espécies nativas, o estabelecimento de sistemas agroflorestais, a promoção de práticas de conservação do solo e da água, a realização de eventos e cursos de capacitação, entre outros projetos. O IRG também desenvolve parcerias com outras organizações, empresas e órgãos governamentais para ampliar o impacto de suas ações e fortalecer a rede de proteção global.");
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
                } else if (button == btnDoar) {
                    	PagamentoMain pagamentoMain = new PagamentoMain(username, "2");
                    	pagamentoMain.setVisible(true);
                        dispose();
                    
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
    }
}
