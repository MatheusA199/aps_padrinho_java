package br.com.pagamento.modelo;
import java.util.Date;

public class Pagamento {
	private int id;
	private String numeroCartao;
	private String cvv;
	private double valor;
	private Date dataPagamento;
	private String email;
	
	
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public double getValor() {
		return valor;
	}
	
	public void setValor(double parseDouble) {
		// TODO Auto-generated method stub
		this.valor = parseDouble;
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}