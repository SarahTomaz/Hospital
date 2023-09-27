package br.edu.ufersa.hospital.api.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import br.edu.ufersa.hospital.model.entity.Medico;
import br.edu.ufersa.hospital.model.entity.Paciente;
import br.edu.ufersa.hospital.model.entity.Prontuario;

public class ConsultaDTO{
	private int id;
    private int pacienteid;
    private int Medicoid;
    private int Prontuarioid;
    private LocalDate data;
    private LocalTime horario;
    
    public int getId(){
        return this.id;
    }

    public void setId(int id){
        if(id < 0){
            System.out.println("Id inválido");
        } else this.id = id;
    }
    
    public void setData(LocalDate temp){
        LocalDate agora = LocalDate.now();
        LocalDate limiteMinimo = agora.minusYears(150);
        LocalDate limiteMaximo = agora.plusYears(2);
        if(temp.isAfter(limiteMinimo) && temp.isBefore(limiteMaximo)){
            this.data = temp;
        }
        else this.data = agora.plusMonths(2);
    }
    public LocalDate getData(){
        return this.data;
    }
    
    public void setIdProntuario(int Prontuarioid){
        if(Prontuarioid >= 0){
            this.Prontuarioid = Prontuarioid;
        }
    }
    public int getProntuarioid(){ 
        return this.Prontuarioid;
    }

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public int getpacienteid() {
		return this.pacienteid;
	}

	public void setpacienteid(int pacienteid) {
		if(pacienteid >= 0){
            this.pacienteid = pacienteid;
        }
	}

	public int getMedicoid() {
		return this.Medicoid;
	}

	public void setMedicoid(int Medicoid) {
		if(Medicoid >= 0){
            this.Medicoid = Medicoid;
        }
	}
	
}