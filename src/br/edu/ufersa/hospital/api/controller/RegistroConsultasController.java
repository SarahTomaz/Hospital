package br.edu.ufersa.hospital.api.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ufersa.hospital.api.dto.ConsultaDTO;
import br.edu.ufersa.hospital.api.dto.ProntuarioDTO;
import br.edu.ufersa.hospital.model.entity.Paciente;
import br.edu.ufersa.hospital.model.service.ConsultaBO;

//Fazer os import do JavaFX e das views quando implementar
public class RegistroConsultasController implements Initializable {
    

    @FXML private Button menu;
    @FXML private Button menuClose;
    @FXML private AnchorPane slider;
    @FXML private TextField busca;
    @FXML private TableView<ConsultaDTO> tabelaConsultas;
    @FXML private TableColumn<ConsultaDTO, String> columnPaciente;
    @FXML private TableColumn<ConsultaDTO, String> columnData;
    @FXML private TableColumn<ConsultaDTO, String> columnHorario;
    @FXML private TableColumn<ConsultaDTO, String> columnMedico;
    @FXML private TableColumn<ConsultaDTO, String> columnStatus;
    @FXML private TableColumn<ConsultaDTO, String> columnEmitirProntuario;
    private ConsultaBO bo = new ConsultaBO();
    private ObservableList<ConsultaDTO> listaDeConsultas;
    private ObservableList<ConsultaDTO> listaConsultasFiltradas;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
        listarConsultas();

    }
    
    public void abrirMenu() {
        // menu.setOnMouseClicked(event -> {});
           TranslateTransition slide = new TranslateTransition();
           slide.setDuration(Duration.seconds(0.4));
           slide.setNode(slider);
           slide.setToY(0);
           slide.play();
           
           slider.setTranslateY(-107);
           slide.setOnFinished((ActionEvent e) -> {
               slider.setVisible(true);
               menu.setVisible(false);
               menuClose.setVisible(true);
           });
    }
    
    public void fecharMenu() {
     // menu.setOnMouseClicked(event -> {});
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToY(-107);
        slide.play();
        
        slider.setTranslateY(0);
        slide.setOnFinished((ActionEvent e) -> {
            slider.setVisible(false);
            menu.setVisible(true);
            menuClose.setVisible(false);
        });
    }
    
    public void logout() {
        Telas.logout();
    }
    
    public void listarConsultas() {
        List<ConsultaDTO> consultas = bo.listar();
        listaDeConsultas = FXCollections.observableArrayList(consultas);
        columnPaciente.setCellValueFactory(new PropertyValueFactory<>("idPaciente"));
        columnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        columnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        columnMedico.setCellValueFactory(new PropertyValueFactory<>("idMedico"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnEmitirProntuario.setCellValueFactory(new PropertyValueFactory<>("emitirProntuario"));
        tabelaConsultas.setItems(listaDeConsultas);
    }
    
    public void buscar() {
    	ConsultaDTO dto = new ConsultaDTO();
    	dto.setIdPaciente(Integer.parseInt(busca.getText())); // busca consulta pelo id do paciente
    	dto.setIdMedico(Integer.parseInt(busca.getText())); // busca consulta pelo id do medico
    	List<ConsultaDTO> consultas = bo.listarPorCpfPaciente(dto);
    	listaConsultasFiltradas = FXCollections.observableArrayList(consultas);
    	columnPaciente.setCellValueFactory(new PropertyValueFactory<>("idPaciente"));
        columnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        columnHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        columnMedico.setCellValueFactory(new PropertyValueFactory<>("idMedico"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnEmitirProntuario.setCellValueFactory(new PropertyValueFactory<>("emitirProntuario"));
        tabelaConsultas.setItems(listaConsultasFiltradas);
    }
    
    public void telaListarConsultas() {
    	Telas.listarConsultas();
    }
    public void listarPacientes() {
        Telas.listarPacientes();
    }
    public void listarMedicos() { 
        Telas.listarMedicos();
    }
    public void cadastrar() {
    	Telas.telaCadastroConsulta();
    }
    public void editar() {
    	Telas.telaEdicaoConsulta();
    }
    public void excluir() {
    	Telas.telaConfirmarExclusao();
    }
    public void prontuario() {
        //temporario
    	Telas.telaProntuarios(new ProntuarioDTO(LocalDate.now(), LocalTime.now(), "teste", new Paciente()));
    }
    
}