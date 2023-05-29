package br.com.pessoa.fisica.aplicacao;

import java.sql.Connection;

import br.com.entidade.fisica.factory.ConnectionFactory;
import br.com.pessoa.fisica.view.TelaInicial;

public class Main {

	public static void main(String[] args) throws Exception {
		Connection  con = ConnectionFactory.createConnectionToMySQL();
		
		if (con!=null) {
			System.out.println("Conexão obtida com sucesso!");
			con.close();
		}
        new TelaInicial();
    }

}