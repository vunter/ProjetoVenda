package bancoDeDados;

import java.sql.*;

public final class FabricaConexao {

	public Statement statement = null;
	public ResultSet resultSet = null;
	
	String servidor = "jdbc:mysql://db4free.net:3306/vunterprogram?useSSL=false";
	String usuario = "vunter";
	String senha = "leoeifert2";

	public Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(servidor, usuario, senha);

		} catch (SQLException e) {
			System.out.println("erro de sql: " + e);
		} catch (Exception e) {
			System.out.println("Não foi possivel conectar no banco de dados! Erro: " + e);
		}
		return connection;
	}

	public void desconectar(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String estaConectado(Connection conn) {
		if (conn != null) {
			return "Conectado com sucesso!";
		} else {
			return "OPS! Algo deu errado, não foi possível conectar ao banco de dados";
		}
	}

}
