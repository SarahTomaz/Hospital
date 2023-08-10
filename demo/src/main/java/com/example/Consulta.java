//import paciente
// import medico
package com.example;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Consulta {

    private Paciente paciente;
    private Medico medico;
    private Prontuario prontuario;
    private LocalDateTime horario;

    public Consulta(Paciente paciente, Medico medico, LocalDateTime horario, Prontuario prontuario){

        this.paciente = paciente;
        this.medico = medico;
        this.horario = horario;
        this.prontuario = prontuario;
    }

    public boolean adicionarConsulta(Prontuario prontuario){

        if(this.prontuario == null){
            this.prontuario = prontuario;
            return true;
        }

        return false;
    }

    //adicionar os tipos desses parametros e metodos no salario de médico;

    public float valoDaConsulta(){

        float salarioMensal = medico.getSalarioMensal();
        int cargaHorariaSemanal = medico.getCargaHorariaSemanal();

        float salarioPorHora = salarioMensal/(cargaHorariaSemanal *4);

        float duracaoConsultaEmHoras = 1.5f;

        float valorConsulta = salarioPorHora * duracaoConsultaEmHora;

        return valorConsulta;

    }

}
