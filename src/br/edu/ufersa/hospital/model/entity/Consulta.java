package br.edu.ufersa.hospital.model.entity;
import java.time.LocalDate;
// import java.time.LocalTime; import feito para classe ainda não implementada
import java.time.LocalTime;

import br.edu.ufersa.hospital.api.dto.ConsultaDTO;

public class Consulta{
    
    private int id;
    private int pacinteid;
    private int Medicoid;
    private LocalDate data;
    private int Prontuarioid;
	private LocalTime horario;

    public Consulta(int pacinteid,int Medicoid, LocalDate data,LocalTime horario, int Prontuarioid){
        setpacinteid(pacinteid);
        setMedicoid(Medicoid);
        setData(data);
        setProntuarioid(Prontuarioid);
        setHorario(horario);
    }

    public Consulta() {
	}

	public int getId(){
        return this.id;
    }

    public void setId(int id){
        if(id < 0){
            System.out.println("Id inválido");
        } else this.id = id;
    }

    /*public void setPaciente(Paciente temp){
        this.paciente.setNome(temp.getNome());
        this.paciente.setEndereco(temp.getEndereco());
        this.paciente.setCpf(temp.getCpf());
        this.paciente.setProntuarios(temp.getProntuarios());

    }
    public Paciente getPaciente(){
        return this.paciente;

    }*/
    
    /*public void setMedico(Medico temp){
        this.medico.setNome(temp.getNome());
        this.medico.setCodigoDoConselho(temp.getCodigoDoConselho());
        this.medico.setCpf(temp.getCpf());
        this.medico.setEndereco(temp.getEndereco());
        this.medico.setValorDaConsulta(temp.getValorDaConsulta());
    }
    public Medico getMedico(){
        return this.medico;
    }*/
    
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
    
    public void setProntuarioid(int Prontuarioid){
        if(Prontuarioid >= 0){
            this.Prontuarioid = Prontuarioid;
        }
    }
    public int getProntuarioid(){ 
        return this.Prontuarioid;
    }
    
    public LocalTime getHorario() {
    	return this.horario;
    }
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public int getpacinteid() {
		return this.pacinteid;
	}

	public void setpacinteid(int pacinteid) {
		this.pacinteid = pacinteid;
	}

	public int getMedicoid() {
		return this.Medicoid;
	}

	public void setMedicoid(int Medicoid) {
		this.Medicoid = Medicoid;
	}
	
	public static Consulta converter(ConsultaDTO dto) {
        Consulta cons = new Consulta();
        //cons.getPaciente().setCpf(dto.getPaciente().getCpf());
        //cons.getMedico().setCpf(dto.getMedico().getCpf());
        //cons.getProntuario().setId(dto.getProntuario().getId());
        cons.setMedicoid(dto.getMedicoid());
        cons.setpacinteid(dto.getpacienteid());
        cons.setProntuarioid(dto.getProntuarioid());
        //cons.setId(dto.getId());
        //cons.setData(dto.getData());
        //cons.setHorario(dto.getHorario());
        return cons;
    }
   
}