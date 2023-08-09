//packge de paciente
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Paciente {

    String Nome;
    String Cpf;
    LocalDate DataDeNascimentoPaciente;
    String EnderecoPaciente;
    String TipoDeConsulta;

    public void paciente(String Nome, String Cpf, LocalDate DataDeNascimentoPaciente, String EnderecoPaciente, String TipoDeConsulta) {

        this.Nome = Nome;
        this.Cpf = Cpf;
        this.DataDeNascimentoPaciente = DataDeNascimentoPaciente;
        this.EnderecoPaciente = EnderecoPaciente;
        this.TipoDeConsulta = TipoDeConsulta;
    }

    public class cadastroPaciente {

        private List<Paciente> pacientes;

        public cadastroPaciente(Paciente paciente){

            pacientes = new ArrayList<>();

        }

        public void cadastroPaciente(Paciente paciente){
            pacientes.add(paciente);
        }

    }



}