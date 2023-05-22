package br.com.pagamento.view;

import javax.swing.*;

import br.com.ong.view.PrimeiraOng;
import br.com.ong.view.SegundaOng;
import br.com.ong.view.TelaPrincipal;
import br.com.ong.view.TerceiraOng;
import br.com.pessoa_fisica.dao.PessoaFisicaDao;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class PagamentoMain extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    // componentes da interface
    private JLabel tituloLabel, numeroCartaoLabel, cvvLabel, valorLabel, lblNumeroDaOng;
    private JTextField numeroCartaoField, cvvField, valorField;
    private JLabel numeroOngField;
    private JButton pagarButton, voltarButton;

    // construtor
    public PagamentoMain(String username, String numeroOngBtn) {
        // inicialização dos componentes
        super("Pagamento via Cartão");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tituloLabel = new JLabel("Doe via Cartão!");
        tituloLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        JPanel painelDados = new JPanel();
        painelDados.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        numeroCartaoLabel = new JLabel("Número do cartão:");
        numeroCartaoLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelDados.add(numeroCartaoLabel, gbc);

        numeroCartaoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelDados.add(numeroCartaoField, gbc);

        cvvLabel = new JLabel("CVV:");
        cvvLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelDados.add(cvvLabel, gbc);

        cvvField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelDados.add(cvvField, gbc);

        valorLabel = new JLabel("Valor:");
        valorLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelDados.add(valorLabel, gbc);

        valorField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        painelDados.add(valorField, gbc);

        lblNumeroDaOng = new JLabel("Nome da Ong:");
        lblNumeroDaOng.setFont(new Font("Tahoma", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 4;
        painelDados.add(lblNumeroDaOng, gbc);

        numeroOngField = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 4;
        painelDados.add(numeroOngField, gbc);

        // Lógica para definir o texto do JLabel com base no parâmetro recebido
        String parametro = numeroOngBtn; // Substitua "exemplo" pelo valor do parâmetro real que você recebe
        if (parametro.equals("1")) {
            numeroOngField.setText("Prato Cheio");
        } else if (parametro.equals("2")) {
            numeroOngField.setText("Cidade Sem Fome");
        } else if (parametro.equals("3")) {
            numeroOngField.setText("Banco de Alimentos");
        }

        pagarButton = new JButton("Pagar");
        pagarButton.addActionListener(this);

        voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(this);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(pagarButton);
        painelBotoes.add(voltarButton);

        add(tituloLabel, BorderLayout.NORTH);
        add(painelDados, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
	        ActionListener actionListener = new ActionListener() {
	            public void actionPerformed(ActionEvent actionEvent) {
	                Object button = actionEvent.getSource();
	                if (button == pagarButton) {
	                	realizarPagamento(username, parametro);
	                }else if (button == voltarButton) {
	                	voltarParaTelaAnterior(username, parametro);
	       			}
	        	}
	        };
	    pagarButton.addActionListener(actionListener);
	    voltarButton.addActionListener(actionListener);
        
        
        }

    // método para realizar o pagamento
    private void realizarPagamento(String username, String parametro) {
        // Implemente a lógica para realizar o pagamento aqui
        String numeroCartao = numeroCartaoField.getText();
        String cvv = cvvField.getText();
        String valor = valorField.getText();

        // valida se todos os campos foram preenchidos
        if (numeroCartao.isEmpty() || cvv.isEmpty() || valor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!");
            return;
        }

        try {
            double valorPagamento = Double.parseDouble(valor);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido!");
            return;
        }

        try {
            PessoaFisicaDao pfDao = new PessoaFisicaDao();

            if (pfDao.savePagamento(numeroCartao, cvv, username, valor, parametro)) {
                // realiza a transação
                // aqui deve estar o código para realizar a transação, utilizando as informações dos campos
                JOptionPane.showMessageDialog(this, "Pagamento realizado com sucesso!");
                dispose();
                // Abra a tela principal da ONG aqui
                new TelaPrincipal(username).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao salvar o pagamento");
            }
        } catch (HeadlessException | SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    // método para voltar para a tela anterior
    private void voltarParaTelaAnterior(String username, String parametro) {
        if (parametro.equals("1")) {
            PrimeiraOng primeiraOng = new PrimeiraOng(username);
        	primeiraOng.setVisible(true);
            dispose();
        } else if (parametro.equals("2")) {
            SegundaOng segundaOng = new SegundaOng(username);
        	segundaOng.setVisible(true);
            dispose();
        } else if (parametro.equals("3")) {
            TerceiraOng terceiraOng = new TerceiraOng(username);
	    	terceiraOng.setVisible(true);
	        dispose();
        }
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}

