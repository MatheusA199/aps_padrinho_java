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

import br.com.pessoa_fisica.dao.PessoaFisicaDao;
import br.com.ranking.result.RankingResult;

public class Ranking extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBemvindoAoSistema;
    private JTextArea textoRankingOng;


    public Ranking(String resultado, String username) {
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 607, 463);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton telaPrincipalOng = new JButton("Início");
        telaPrincipalOng.setBounds(10, 10, 144, 75);
        contentPane.add(telaPrincipalOng);

        JButton primeiraOng = new JButton("Prato Verde Sustentável");
        primeiraOng.setBounds(10, 95, 144, 75);
        contentPane.add(primeiraOng);

        JButton segundaOng = new JButton("Segunda ONG");
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
        txtBemvindoAoSistema.setText("Ranking");
        txtBemvindoAoSistema.setBounds(220, 20, 350, 50);
        contentPane.add(txtBemvindoAoSistema);
        txtBemvindoAoSistema.setColumns(10);

        textoRankingOng = new JTextArea();
        textoRankingOng.setLineWrap(true);
        textoRankingOng.setWrapStyleWord(true);
        textoRankingOng.setEditable(false);
        textoRankingOng.setFont(new Font("Tahoma", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textoRankingOng);
        scrollPane.setBounds(220, 95, 350, 325);
        contentPane.add(scrollPane);
        textoRankingOng.setText(resultado);
        
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
                        textoRankingOng.setText(resultado);

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
    }
}

