package br.edu.ufersa.hospital.model.entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

import br.edu.ufersa.hospital.api.dto.PacienteDTO;

public class Paciente extends Pessoa {
   
   private int idade;
   private Vector<Prontuario> prontuarios;

   public Paciente(){}

   public Paciente(int id, String nome, String endereco, String cpf, int idade, Vector<Prontuario> prontuarios){
      setId(id);
      setNome(nome);
      setEndereco(endereco);
      setCpf(cpf);
      setIdade(idade);
      setProntuarios(prontuarios);
   }
   
   public Paciente(int id, String nome, String endereco, String cpf, int idade){
	      setId(id);
	      setNome(nome);
	      setEndereco(endereco);
	      setCpf(cpf);
	      setIdade(idade);
   }

   public int getIdade() {
       return idade;
   }

   public void setIdade(int idade) {
       if (idade < 0){ 
           System.out.println("Idade inválida.");
       } else this.idade = idade;
   }

   public void setProntuarios(Vector<Prontuario> Prontuarios){ 
      this.prontuarios = new Vector<Prontuario>(Prontuarios);
      if (!Prontuarios.isEmpty()){
      } else { 
         prontuarios.add(new Prontuario(LocalDate.now(), null, LocalTime.now()));
      }
   }

   public Vector<Prontuario> getProntuarios(){ return prontuarios; }
   
   public static Paciente converter(PacienteDTO dto) {
       Paciente pac = new Paciente();
       pac.setCpf(dto.getCpf());
       pac.setEndereco(dto.getEndereco());
       pac.setNome(dto.getNome());
       pac.setIdade(dto.getIdade());
       pac.setId(dto.getId());
       return pac;
   }

}
