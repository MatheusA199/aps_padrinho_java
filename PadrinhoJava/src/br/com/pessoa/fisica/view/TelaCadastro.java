package br.com.pessoa.fisica.view;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.pessoa_fisica.dao.PessoaFisicaDao;
import padrinho.pessoa.fisica.modelo.EntidadeFisica;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class TelaCadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNome;
    private JTextField textFieldEmail;
    private JTextField textFieldSenha;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastro().setVisible(true);
            }
        });
    }

    public TelaCadastro() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255)); // Cor de fundo
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCadastro = new JLabel("Cadastro");
        lblCadastro.setFont(new Font("Lucida Grande", Font.BOLD, 20)); // Fonte e tamanho
        lblCadastro.setBounds(181, 17, 90, 25);
        contentPane.add(lblCadastro);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 60, 61, 16);
        contentPane.add(lblNome);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 100, 61, 16);
        contentPane.add(lblEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 140, 61, 16);
        contentPane.add(lblSenha);

        textFieldNome = new JTextField();
        textFieldNome.setBounds(100, 55, 300, 26);
        contentPane.add(textFieldNome);
        textFieldNome.setColumns(10);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(100, 95, 300, 26);
        contentPane.add(textFieldEmail);
        textFieldEmail.setColumns(10);

        textFieldSenha = new JTextField();
        textFieldSenha.setBounds(100, 135, 300, 26);
        contentPane.add(textFieldSenha);
        textFieldSenha.setColumns(10);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(144, 238, 144)); // Cor de fundo do botão
        btnCadastrar.setForeground(new Color(0, 100, 0)); // Cor do texto do botão
        btnCadastrar.setFont(new Font("Lucida Grande", Font.BOLD, 14)); // Fonte e tamanho do texto do botão
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText();
                String email = textFieldEmail.getText();
                String senha = textFieldSenha.getText();

                // ...

                // Verificar se os campos estão preenchidos
                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Cadastro", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Date date = new Date();

                EntidadeFisica pessoaFisica = new EntidadeFisica();
                pessoaFisica.setNome(nome);
                pessoaFisica.setEmail(email);
                pessoaFisica.setSenha(senha);
                pessoaFisica.setDataCadastro(date);

                PessoaFisicaDao dao = new PessoaFisicaDao();
                try {
                    if (dao.exists(email)) {
                        JOptionPane.showMessageDialog(null, "Já existe um usuário cadastrado com esse email.", "Cadastro", JOptionPane.WARNING_MESSAGE);
                    } else {
                        dao.save(pessoaFisica);
                        TelaInicial telaInicial = new TelaInicial();
                        telaInicial.setVisible(true);
                        dispose();
                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário.", "Cadastro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCadastrar.setBounds(100, 200, 117, 29);
        contentPane.add(btnCadastrar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(new Color(255, 192, 203)); // Cor de fundo do botão
        btnVoltar.setForeground(new Color(139, 0, 0)); // Cor do texto do botão
        btnVoltar.setFont(new Font("Lucida Grande", Font.BOLD, 14)); // Fonte e tamanho do texto do botão
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaInicial telaInicial = new TelaInicial();
                telaInicial.setVisible(true);
                dispose();
            }
        });
        btnVoltar.setBounds(250, 200, 117, 29);
        contentPane.add(btnVoltar);
    }
}
                
