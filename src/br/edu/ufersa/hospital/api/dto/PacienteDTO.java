package br.edu.ufersa.hospital.api.dto;


public class PacienteDTO extends PessoaDTO {
    
    private int idade;
    
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade < 0){ 
            System.out.println("Idade inválida.");
        } else this.idade = idade;
    }
}