package br.com.pessoa_fisica.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.entidade.fisica.factory.ConnectionFactory;
import br.com.ranking.result.RankingResult;
import padrinho.pessoa.fisica.modelo.EntidadeFisica;

public class PessoaFisicaDao{

    public void save(EntidadeFisica pessoaFisica) throws Exception {

        String sql = "INSERT INTO pessoa_fisica(nome, email, senha, dateCadastro) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, pessoaFisica.getNome());
            pstm.setString(2, pessoaFisica.getEmail());
            pstm.setString(3, pessoaFisica.getSenha());
            if (pessoaFisica.getDataCadastro() != null) {
                pstm.setDate(4, new Date(pessoaFisica.getDataCadastro().getTime()));
            } else {
                pstm.setDate(4, null);
            }

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir pessoa física: " + e.getMessage());
        }
    }
    
    public boolean autenticar(String email, String senha) throws Exception {

        String sql = "SELECT * FROM pessoa_fisica WHERE email = ? AND senha = ?";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, email);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new SQLException("Erro ao autenticar usuário: " + e.getMessage());
        }
    }

    public boolean login(String email, String senha) throws Exception {
        return autenticar(email, senha);
    }
    
    public EntidadeFisica findByEmail(String email) throws Exception {
        String sql = "SELECT * FROM pessoa_fisica WHERE email = ?";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, email);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                EntidadeFisica pessoaFisica = new EntidadeFisica();
                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setNome(rs.getString("nome"));
                pessoaFisica.setEmail(rs.getString("email"));
                pessoaFisica.setSenha(rs.getString("senha"));
                pessoaFisica.setDataCadastro(rs.getDate("dateCadastro"));

                return pessoaFisica;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar pessoa física pelo email: " + e.getMessage());
        }
    }
    
    public boolean exists(String email) throws Exception {
        String sql = "SELECT COUNT(*) AS count FROM pessoa_fisica WHERE email = ?";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, email);

            ResultSet rs = pstm.executeQuery();

            int count = 0;
            if (rs.next()) {
                count = rs.getInt("count");
            }

            return count > 0;

        } catch (SQLException e) {
            throw new SQLException("Erro ao verificar existência de registro: " + e.getMessage());
        }
    }
    
    public int findIdByEmail(String email) throws Exception {
        String sql = "SELECT id FROM pessoa_fisica WHERE email = ?";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, email);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new Exception("Não foi possível encontrar o id da pessoa física com o email " + email);
            }

        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar id da pessoa física pelo email: " + e.getMessage());
        }
    }
    
    public boolean savePagamento(String numeroCartao, String cvv, String email, String valor, String numeroDaOng) throws Exception {
    	
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        String sql = "INSERT INTO pagamento(numeroCartao, cvv, valor, id_pessoa_fisica, data_pagamento, numero_ong) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            int id = this.findIdByEmail(email);
            if (id == -1) {
                throw new Exception("Email informado inválido!");
            }

            pstm.setString(1, numeroCartao);
            pstm.setString(2, cvv);
            pstm.setString(3, valor);
            pstm.setInt(4, id);
            pstm.setDate(5, sqlDate);
            pstm.setString(6, numeroDaOng);
                
            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 1) {
            	System.out.print("Pagamento cadastrado com sucesso!");
                return true;
            } else {
                throw new SQLException("Erro ao inserir pagamento. Nenhuma linha foi afetada.");
            }

        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir pagamento: " + e.getMessage());
        }
        
    }
    
    public List<RankingResult> consultarRanking() throws Exception {
    	String sql = "SELECT p.id_pessoa_fisica, SUM(p.valor) AS total_depositado, pf.nome FROM pagamento p JOIN pessoa_fisica pf ON p.id_pessoa_fisica = pf.id GROUP BY p.id_pessoa_fisica ORDER BY total_depositado DESC";
        List<RankingResult> results = new ArrayList<>();
        System.out.print("Está sendo feito a consulta!");
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
                PreparedStatement pstm = conn.prepareStatement(sql)){
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    int idPessoaFisica = rs.getInt("id_pessoa_fisica");
                    double totalDepositado = rs.getDouble("total_depositado");
                    String nome = rs.getString("nome");
                    RankingResult result = new RankingResult(idPessoaFisica, totalDepositado, nome);
                    results.add(result);
                }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao consultar o ranking", e);
        }
        return results;
    }


}
        

