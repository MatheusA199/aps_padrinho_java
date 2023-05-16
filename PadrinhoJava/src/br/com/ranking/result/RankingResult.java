package br.com.ranking.result;

public class RankingResult {
        private int idPessoaFisica;
        private double totalDepositado;
        private String nome;

        public RankingResult(int idPessoaFisica, double totalDepositado, String nome) {
            this.idPessoaFisica = idPessoaFisica;
            this.totalDepositado = totalDepositado;
            this.nome = nome;
        }

        public int getIdPessoaFisica() {
            return idPessoaFisica;
        }

        public double getTotalDepositado() {
            return totalDepositado;
        }

        public String getNome() {
            return nome;
        }
    }