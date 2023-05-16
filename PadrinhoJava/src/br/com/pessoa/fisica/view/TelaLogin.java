package br.com.pessoa.fisica.view;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.ong.view.TelaPrincipal;
import br.com.pessoa_fisica.dao.PessoaFisicaDao;

public class TelaLogin extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtInsiraAsInformaes;
    private JTextField txtUsername;
    private JTextField txtSenha;

    /**
     * Launch the application.
     */
    

    /**
     * Create the frame.
     */
    public TelaLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 518, 319);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        SpringLayout sl_contentPane = new SpringLayout();
        contentPane.setLayout(sl_contentPane);

        txtInsiraAsInformaes = new JTextField();
        txtInsiraAsInformaes.setEditable(false);
        txtInsiraAsInformaes.setBorder(null);
        sl_contentPane.putConstraint(SpringLayout.NORTH, txtInsiraAsInformaes, 41, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, txtInsiraAsInformaes, 25, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, txtInsiraAsInformaes, -25, SpringLayout.EAST, contentPane);
        txtInsiraAsInformaes.setHorizontalAlignment(SwingConstants.CENTER);
        txtInsiraAsInformaes.setFont(new Font("Tahoma", Font.BOLD, 18));
        txtInsiraAsInformaes.setText("Login");
        contentPane.add(txtInsiraAsInformaes);
        txtInsiraAsInformaes.setColumns(10);

        JLabel lblNewLabel = new JLabel("Email");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 87, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 60, SpringLayout.WEST, contentPane);
        contentPane.add(lblNewLabel);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtUsername.setBorder(null);
        sl_contentPane.putConstraint(SpringLayout.NORTH, txtUsername, 108, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, txtUsername, 60, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, txtUsername, -60, SpringLayout.EAST, contentPane);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
        sl_contentPane.putConstraint(SpringLayout.NORTH, lblSenha, 133, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, lblSenha, 60, SpringLayout.WEST, contentPane);
        contentPane.add(lblSenha);

        txtSenha = new JTextField();
        txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtSenha.setBorder(null);
        sl_contentPane.putConstraint(SpringLayout.NORTH, txtSenha, 154, SpringLayout.NORTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.WEST, txtSenha, 60, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, txtSenha, -60, SpringLayout.EAST, contentPane);
        contentPane.add(txtSenha);
        txtSenha.setColumns(10);

        JButton btnNewButton = new JButton("Entrar");
        sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 124, SpringLayout.WEST, contentPane);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton, -37, SpringLayout.SOUTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, -268, SpringLayout.EAST, contentPane);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    PessoaFisicaDao pfDao = new PessoaFisicaDao();
                    String username = txtUsername.getText();
                    String senha = txtSenha.getText();

                    if (pfDao.login(username, senha)) {
                        JOptionPane.showMessageDialog(null, "Bem vindo!");
                        dispose();
                        TelaPrincipal tp = new TelaPrincipal(username);
                        tp.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
                    }

                } catch (HeadlessException | SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPane.add(btnNewButton);
        
        JButton btnVoltarTelaInicial = new JButton("Voltar");
        sl_contentPane.putConstraint(SpringLayout.NORTH, btnVoltarTelaInicial, 0, SpringLayout.NORTH, btnNewButton);
        sl_contentPane.putConstraint(SpringLayout.WEST, btnVoltarTelaInicial, 53, SpringLayout.EAST, btnNewButton);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, btnVoltarTelaInicial, -37, SpringLayout.SOUTH, contentPane);
        sl_contentPane.putConstraint(SpringLayout.EAST, btnVoltarTelaInicial, -113, SpringLayout.EAST, contentPane);
        btnVoltarTelaInicial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	TelaInicial telaInicial = new TelaInicial();
                telaInicial.setVisible(true);
                dispose();
            }
        });
        btnVoltarTelaInicial.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPane.add(btnVoltarTelaInicial);
    }
}
