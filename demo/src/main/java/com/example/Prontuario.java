package com.example;

//import package.paciente;
//import package.medico;
//import package.consulta

import java.time.LocalDateTime;


public class Prontuario {
    private String Alergias;
    private String Sangue;
    private String Medicacao;
    private String SinaisVitais;

    public Prontuario(){}
    public Prontuario(String Alergias, String Sangue, String Medicacao, String SinaisVitais)
    {
        this.Alergias = Alergias;
        this.Sangue = Sangue;
        this.Medicacao = Medicacao;
        this.SinaisVitais = SinaisVitais;
    }

    public void editarProntuario()
    {
        System.out.println("Prontuário editado");
        
    }
    public void excluirProntuario()
    {
        System.out.println("Prontuário excluído");
    }
    public class gerarProntuario{
        public gerarProntuario(){
            
        }
        return prontuario;
    }
    
}
