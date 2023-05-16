package padrinho.pessoa.fisica.modelo;

import java.util.Date;

public class EntidadeFisica {
	private int id;
	private String nome;
	private String email;
	private Date dataCadastro;
	private String senha;
	private double totalDepositado;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public double getTotalDepositado() {
		return totalDepositado;
	}
	public void setTotalDepositado(double totalDepositado) {
		this.totalDepositado = totalDepositado;
	}
	
}
