package br.edu.ufersa.hospital.api.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import br.edu.ufersa.hospital.model.entity.Paciente;


public class ProntuarioDTO{
	private int id;
	private LocalDate data;
	private LocalTime horario;
	private String obs;
	private Paciente paciente;
	

	//adicionado construtores
	public ProntuarioDTO(){}

	public ProntuarioDTO(LocalDate data, LocalTime horario, String obs, Paciente paciente){
		setData(data);
		setHorario(horario);
		setObs(obs);
		setPaciente(paciente);
	}
	
	public int getId(){
	      return this.id;
	   }

	public void setId(int id){
	      if(id < 0){
	          System.out.println("Id inválido");
	      } else this.id = id;
	   }

	public LocalDate getData(){
	      return data;
	   }

	public void setData(LocalDate data){
	      LocalDate limiteMinimo = LocalDate.now().minusYears(150);
	      LocalDate limiteMaximo = LocalDate.now();
	      if(data.isAfter(limiteMinimo)){
	         if(data.isBefore(limiteMaximo)){
	            this.data = data;
	         } 
	         else this.data = limiteMaximo;
	      }
	      else this.data = limiteMinimo;
	   }

	public void setObs(String obs){
	      if (obs == " "){
	         System.out.println("Esse nome é inválido.");
	      } else this.obs = obs;
	   }

	public String getObs(){
	      return obs;
	   }

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	
}