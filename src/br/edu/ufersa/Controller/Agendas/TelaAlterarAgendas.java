package br.edu.ufersa.controller.Agendas;

import br.edu.ufersa.exception.CampoVazioException;
import br.edu.ufersa.model.Bo.ConsultasBo;
import br.edu.ufersa.model.Bo.FuncionarioBo;
import br.edu.ufersa.model.Bo.PacienteBo;
import br.edu.ufersa.model.entity.Consulta;
import br.edu.ufersa.model.entity.Funcionario;
import br.edu.ufersa.model.entity.Paciente;
import br.edu.ufersa.view.Telas;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class TelaAlterarAgendas
{
    @FXML DatePicker data;
    @FXML ChoiceBox<String> medico;
    @FXML Label nomeMedico = new Label();
    @FXML ChoiceBox<String> paciente;
    @FXML Label nomePaciente = new Label();
    private Long id = null;
    private Date d = null;

    public void initialize(Consulta con)
    {
        try
        {
            d = con.getData_consulta();
            data.setValue(d.toLocalDate());
            id = con.getId();

            FuncionarioBo funcBo = new FuncionarioBo();
            List<Funcionario> funcList = funcBo.listar();

            while (!funcList.isEmpty()) {
                medico.getItems().add(funcList.get(0).getCrm());
                funcList.remove(0);
            }

            PacienteBo pacBo = new PacienteBo();
            List<Paciente> pacList = pacBo.listar();

            while (!pacList.isEmpty()) {
                paciente.getItems().add(pacList.get(0).getCpf());
                pacList.remove(0);
            }

            ConsultasBo conBo = new ConsultasBo();

            String crm = conBo.buscarPorId(con).get(0).getMedico();
            medico.setValue(crm);
            Funcionario func = new Funcionario();
            func.setCrm(crm);
            funcList = funcBo.buscarPorCrm(func);
            nomeMedico.setText(funcList.get(0).getNome());

            String cpf = conBo.buscarPorId(con).get(0).getPaciente();
            paciente.setValue(cpf);
            Paciente pac = new Paciente();
            pac.setCpf(cpf);
            pacList = pacBo.buscarPorCpf(pac);
            nomePaciente.setText(pacList.get(0).getNome());

            medico.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1)
                {
                    func.setCrm(t1);
                    List<Funcionario> funcList = funcBo.buscarPorCrm(func);
                    nomeMedico.setText(funcList.get(0).getNome());
                }
            });

            paciente.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1)
                {
                    pac.setCpf(t1);
                    List<Paciente> pacList = pacBo.buscarPorCpf(pac);
                    nomePaciente.setText(pacList.get(0).getNome());
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    @FXML Label erroaut = new Label();

    public void inserir(ActionEvent actionEvent)
    {
        ConsultasBo conBo = new ConsultasBo();
        Consulta con = new Consulta();

        try
        {
            con.setId(id);
            con.setMedico(medico.getValue());
            con.setPaciente(paciente.getValue());
            con.setData_consulta(Date.valueOf(data.getValue()));

            if (con.getData_consulta().compareTo(Date.valueOf(LocalDate.now())) >= 0 ||
                    con.getData_consulta().compareTo(d) == 0)
            {
                conBo.alterar(con);

                Telas.telaAgendas();
            }
            else
            {
                erroaut.setText("Uma Consulta não pode ser remarcada para o passado");
                erroaut.setVisible(true);
            }
        }
        catch (CampoVazioException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void cancelar()
    {
        try
        {
            Telas.telaAgendas();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
//_____________________________________________________________________________________________________________________
    //Escolhas estilísticas

    @FXML
    Button realizarIns;
    @FXML Button cancelarIns;

    public void mudarCorAlt(MouseEvent mouseEvent)
    {
        realizarIns.setStyle("-fx-background-color: #00CED1;-fx-border-color: #2F4F4F");
    }
    public void voltarCorAlt(MouseEvent mouseEvent)
    {
        realizarIns.setStyle("-fx-background-color: #a9a9a9;-fx-border-color: #2F4F4F");
    }


    public void mudarCorCan(MouseEvent mouseEvent)
    {
        cancelarIns.setStyle("-fx-background-color: #00CED1;-fx-border-color: #2F4F4F");
    }
    public void voltarCorCan(MouseEvent mouseEvent)
    {
        cancelarIns.setStyle("-fx-background-color: #a9a9a9;-fx-border-color: #2F4F4F");
    }
}
