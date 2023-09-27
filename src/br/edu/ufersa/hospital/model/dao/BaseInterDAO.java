package br.edu.ufersa.hospital.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import br.edu.ufersa.hospital.model.entity.Consulta;
import br.edu.ufersa.hospital.model.entity.Medico;

public interface BaseInterDAO<entity> {
	public Connection getConnection();
	public boolean cadastrar (entity e);
	public boolean editar(entity e, String cpf);
	public boolean excluirPorId(entity e);
	public boolean excluirPorCPF(entity e);
	public ResultSet listar();
    public ResultSet encontrar(entity e);
    public entity encontrarPorId(entity e);
	public ResultSet encontrarPorCampoEspecifico(entity e, String field);
	public ResultSet encontrarPorNome(entity e);
	public ResultSet BuscarPorId(entity e);
	boolean editar(Consulta vo, int Pacienteid);
}
