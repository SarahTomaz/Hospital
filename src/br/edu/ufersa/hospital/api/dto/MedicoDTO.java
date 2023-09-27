package br.edu.ufersa.hospital.api.dto;

public class MedicoDTO extends PessoaDTO{
    
    private int codigoDoConselho;
    private double valorDaConsulta;
    
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
    
}