package br.usjt.arqdesis.sistemapredial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.usjt.arqdesis.sistemapredial.model.Empresa;
import br.usjt.arqdesis.sistemapredial.model.Usuario;

public class EmpresaDAO {

		public int criar(Empresa empresa) {
			String sqlInsert = "INSERT INTO usuario(cnpj, razaoSocial, conjunto, horarioDeFuncionamento, temperaturaDoArCondicionado, horarioDoArCondicionado) VALUES (?, ?, ?, ?, ?, ?)";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
				stm.setString(1, empresa.getCnpj());
				stm.setString(2, empresa.getRazaoSocial());
				stm.setString(3, empresa.getConjunto());
				stm.setString(4, empresa.getHorarioDeFuncionamento());
				stm.setString(5, empresa.getTemperaturaDoArCondicionado());
				stm.setString(6, empresa.getHorarioDoArCondicionado());
				stm.execute();
				String sqlQuery = "SELECT LAST_INSERT_ID()";
				try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
						ResultSet rs = stm2.executeQuery();) {
					if (rs.next()) {
						empresa.setId(rs.getInt(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return empresa.getId();
		}
		
		public void atualizar( Empresa empresa) {
			String sqlUpdate = "UPDATE usuario SET cnpj=?, razaoSocial=?, conjunto=?, horarioDeFuncionamento=?, temperaturaDoArCondicionado=?, horarioDoArCondicionado=? WHERE id=?";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
				stm.setString(1, empresa.getCnpj());
				stm.setString(2, empresa.getRazaoSocial());
				stm.setString(3, empresa.getConjunto());
				stm.setString(4, empresa.getHorarioDeFuncionamento());
				stm.setString(5, empresa.getTemperaturaDoArCondicionado());
				stm.setString(6, empresa.getHorarioDoArCondicionado());
				stm.setInt(7, (int) empresa.getId());
				stm.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		public void excluir(int id) {
			String sqlDelete = "DELETE FROM usuario WHERE id = ?";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
				stm.setInt(1, id);
				stm.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public Empresa carregar(int id) {
			Empresa empresa = new Empresa();
			empresa.setId(id);
			String sqlSelect = "SELECT nome, cpf, rg, endereco, dataDeNascimento, nomeEmpresa FROM usuario WHERE usuario.id = ?";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setInt(1, empresa.getId());
				try (ResultSet rs = stm.executeQuery();) {
					if (rs.next()) {
						empresa.setCnpj(rs.getString("CNPJ"));
						empresa.setRazaoSocial(rs.getString("Razão social"));
						empresa.setConjunto(rs.getString("Conjunto"));
						empresa.setHorarioDeFuncionamento(rs.getString("Horario de funcionamento"));
						empresa.setTemperaturaDoArCondicionado(rs.getString("Temperatura do ar condicionado"));
						empresa.setHorarioDoArCondicionado(rs.getString("Horario do ar condicionado"));
					} else {
						empresa.setId(-1);
						empresa.setCnpj(null);
						empresa.setRazaoSocial(null);
						empresa.setConjunto(null);
						empresa.setHorarioDeFuncionamento(null);
						empresa.setTemperaturaDoArCondicionado(null);
						empresa.setHorarioDoArCondicionado(null);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
			return empresa;
		}




}
