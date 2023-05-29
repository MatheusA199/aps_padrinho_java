package br.com.entidade.fisica.factory;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {

	//Nome do usuario do mysql
	private static final String USERNAME = "root";
	
	//Senha do Banco
	private static final String PASSWORD = "Kgb02kgb02";
	
	//Caminho do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pessoa_fisica";

	//Conexão com o banco de dados
	public static Connection createConnectionToMySQL() throws Exception{
		//Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		return connection;
	}
}
