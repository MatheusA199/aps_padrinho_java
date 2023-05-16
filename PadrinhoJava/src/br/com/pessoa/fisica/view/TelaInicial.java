package br.com.pessoa.fisica.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaInicial extends JFrame {

    private static final long serialVersionUID = 1L;

    public TelaInicial() {
        setTitle("Sistema de Padrinho");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(458, 320);
        setLocationRelativeTo(null);
        
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        
        JLabel lblTitulo = new JLabel("Seja Bem-Vindo ao sistema de Padrinho!");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
        gbc_lblTitulo.gridx = 0;
        gbc_lblTitulo.gridy = 0;
        gbc_lblTitulo.gridwidth = 2;
        gbc_lblTitulo.insets = new Insets(20, 0, 30, 0);
        contentPane.add(lblTitulo, gbc_lblTitulo);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 16));
        btnLogin.setPreferredSize(new Dimension(150, 50));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    TelaLogin telaLogin = new TelaLogin();
                    telaLogin.setVisible(true);
                    dispose();
                }
            }
        );
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.gridx = 0;
        gbc_btnLogin.gridy = 1;
        gbc_btnLogin.insets = new Insets(0, 20, 0, 10);
        contentPane.add(btnLogin, gbc_btnLogin);
        
        JButton btnCadastro = new JButton("Cadastro");
        btnCadastro.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCadastro.setPreferredSize(new Dimension(150, 50));
        btnCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	TelaCadastro telaCadastro = new TelaCadastro();
            	telaCadastro.setVisible(true);
                dispose();
            }
        });
        GridBagConstraints gbc_btnCadastro = new GridBagConstraints();
        gbc_btnCadastro.gridx = 1;
        gbc_btnCadastro.gridy = 1;
        gbc_btnCadastro.insets = new Insets(0, 10, 0, 20);
        contentPane.add(btnCadastro, gbc_btnCadastro);
        
        setVisible(true);
    }
}





