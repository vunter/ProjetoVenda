package bancoDeDados;

import java.sql.*;

public class ComandosBancoDeDados {

	public Statement statement = null;
	public ResultSet resultSet = null;
	private Connection conn;
	FabricaConexao fabrica;
	
	int s;
	public ComandosBancoDeDados() {
		 fabrica = new FabricaConexao();
		 conn = fabrica.getConnection();
	}
	
	public void listarNomes(Connection conn) {
		try {
			String query = "SELECT * FROM clientes ORDER BY nome";
			this.resultSet = this.statement.executeQuery(query);
			while (this.resultSet.next()) {
				System.out.println(
						"Nome: " + this.resultSet.getString("nome") + " " + "CPF: " + this.resultSet.getString("cpf"));
			}
		} catch (Exception e) {

		}
	}

	public void cadastrarCliente(String cpf, String nome, int idade, String dataNascimento, int pontos) {
		try {
			String query = "INSERT INTO clientes (cpf, nome, idade, dataNascimento, ativo, pontos) VALUES ('" + cpf
					+ "', '" + nome + "', '" + idade + "', '" + dataNascimento + "', '1', '" + pontos + "');";

			this.statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void descadastrarCliente(String cpf) {

		try {
			String queryDelete = "DELETE FROM Clientes WHERE cpf = '" + cpf + "';";
			this.statement.executeUpdate(queryDelete);
			System.out.println("Cliente descadrastado com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao descadastrar cliente: " + e.getMessage());
		}

	}

	public void statusCliente(String cpf) {
		try {
			String query = "SELECT * FROM Clientes WHERE cpf = '" + cpf + "';";
			this.resultSet = this.statement.executeQuery(query);

			while (this.resultSet.next()) {
				System.out.println(
						"CPF: " + this.resultSet.getString("cpf") + "\nNome: " + this.resultSet.getString("nome")
								+ "\nData de Nascimento/Idade: " + this.resultSet.getString("dataNascimento") + " / "
								+ this.resultSet.getInt("idade") + " Anos\nAtivo no sistema de pontos: "
								+ this.resultSet.getBoolean("ativo") + "\nPontos: " + this.resultSet.getInt("pontos"));

			}

		} catch (SQLException e) {
			System.out.println("Erro de SQL: " + e);
		} catch (Exception e) {
			System.out.println("Erro! Cliente não encontrado no banco de dados! " + e.getMessage());
		}
	}

	public void pesquisarClientePorNome(String nome) {
		try {
			String query = "SELECT * FROM Clientes WHERE nome LIKE '" + nome + "%';";
			this.resultSet = this.statement.executeQuery(query);
			while (this.resultSet.next()) {
				System.out.println(
						"CPF: " + this.resultSet.getString("cpf") + "\nNome: " + this.resultSet.getString("nome")
								+ "\nData de Nascimento/Idade: " + this.resultSet.getString("dataNascimento") + " / "
								+ this.resultSet.getInt("idade") + " Anos\nAtivo no sistema de pontos: "
								+ this.resultSet.getBoolean("ativo") + "\nPontos: " + this.resultSet.getInt("pontos")
								+ "\n================================================================");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
