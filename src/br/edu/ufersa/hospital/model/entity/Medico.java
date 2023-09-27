package br.edu.ufersa.hospital.model.entity;

import br.edu.ufersa.hospital.api.dto.MedicoDTO;

public class Medico extends Pessoa{

    private int codigoDoConselho;
    private double valorDaConsulta;

    public Medico(){}

    public Medico(String nome, String cpf, String endereco, int codConselho, double valorConsulta){
        setNome(nome);
        setCpf(cpf);
        setCodigoDoConselho(codConselho);
        setEndereco(endereco);
        setValorDaConsulta(valorConsulta);
    }
    public Medico(int id,String nome, String cpf, String endereco, int codConselho, double valorConsulta){
        setId(id);
    	setNome(nome);
        setCpf(cpf);
        setCodigoDoConselho(codConselho);
        setEndereco(endereco);
        setValorDaConsulta(valorConsulta);
    }

    public Medico(String nome, String cpf, int codConselho, double valorConsulta){
        setNome(nome);
        setCpf(cpf);
        setCodigoDoConselho(codConselho);
        this.endereco = "Condomínio dos Médicos";
        setValorDaConsulta(valorConsulta);
    }

    public Medico(String nome, String cpf, int codConselho){
        setNome(nome);
        setCpf(cpf);
        setCodigoDoConselho(codConselho);
        this.endereco = "Condomínio dos Médicos";
        this.valorDaConsulta = 5499.99;
    }
    
    public Medico(String nome, String cpf){
        setNome(nome);
        setCpf(cpf);
        setCodigoDoConselho(000);
        this.endereco = "Condomínio dos Médicos";
        this.valorDaConsulta = 5499.99;
    }
    
    public Medico(Medico med) {
		setNome(med.getNome());
		setCpf(med.getCpf());
		setId(med.getId());
		setCodigoDoConselho(med.getCodigoDoConselho());
		setValorDaConsulta(med.getValorDaConsulta());
		setEndereco(med.getEndereco());
	}

	public int getCodigoDoConselho() {
        return this.codigoDoConselho;
    }

    public void setCodigoDoConselho(int codigoDoConselho) { 
        if (codigoDoConselho == 0){
            System.out.println("Código do conselho inválido.");
        } else this.codigoDoConselho = codigoDoConselho;
    }
    
    public double getValorDaConsulta() {
        return this.valorDaConsulta;
    }

    public void setValorDaConsulta(double valorDaConsulta) {    
        if (valorDaConsulta == 0){
            System.out.println("Valor da consulta inválido.");
        } else this.valorDaConsulta = valorDaConsulta;
    }
    
    public static Medico converter(MedicoDTO dto) {
        Medico med = new Medico();
        med.setCpf(dto.getCpf());
        med.setEndereco(dto.getEndereco());
        med.setNome(dto.getNome());
        med.setId(dto.getId());
        med.setCodigoDoConselho(dto.getCodigoDoConselho());
        med.setValorDaConsulta(dto.getValorDaConsulta());
        return med;
    }
    
}