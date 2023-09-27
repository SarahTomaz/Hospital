package br.edu.ufersa.hospital.api.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ufersa.hospital.api.dto.MedicoDTO;
import br.edu.ufersa.hospital.model.service.MedicoBO;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
public class RegistroMedicosController implements Initializable {

    @FXML private Button menu;
    @FXML private Button menuClose;
    @FXML private AnchorPane slider;
    @FXML private TextField busca;
    @FXML private TableView<MedicoDTO> tabelaMedicos;
    @FXML private TableColumn<MedicoDTO, String> columnNome;
    @FXML private TableColumn<MedicoDTO, String> columnCpf;
    @FXML private TableColumn<MedicoDTO, String> columnEndereco;
    @FXML private TableColumn<MedicoDTO, String> columnCodConselho;
    @FXML private TableColumn<MedicoDTO, String> columnValorConsulta;
    @FXML private TableColumn<MedicoDTO, MedicoDTO> columnRelatorio;
    @FXML private TableColumn <MedicoDTO, MedicoDTO> columnEdit;
    @FXML private TableColumn <MedicoDTO, MedicoDTO> columnDelete;
    @FXML private Pane confirmarExclusao;
    private MedicoBO bo = new MedicoBO();
    private ObservableList<MedicoDTO> listaDeMedicos;
    private ObservableList<MedicoDTO> listaMedicosFiltrados;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
        listarMedicos();
        
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
        slide.setOnFinished((ActionEvent e) -> {	// ao fim da transição, dá pra tirar isso
            slider.setVisible(false);
            menu.setVisible(true);
            menuClose.setVisible(false);
        });
    }
    
    public void logout() {
        Telas.logout();
    }
    
    String pathIconRelatorio= "M7 11H10.75M7 14H10.75M7 17H10.75M13.75 17.75H16C16.5967 17.75 17.169 17.5129 17.591 17.091C18.0129 16.669 18.25 16.0967 18.25 15.5V5.108C18.25 3.973 17.405 3.01 16.274 2.916C15.9 2.88498 15.5256 2.85831 15.151 2.836M9.35 2.836C9.285 3.046 9.25 3.269 9.25 3.5C9.25 3.914 9.586 4.25 10 4.25H14.5C14.6989 4.25 14.8897 4.17098 15.0303 4.03033C15.171 3.88968 15.25 3.69891 15.25 3.5C15.2501 3.27491 15.2164 3.05109 15.15 2.836M9.35 2.836C9.49203 2.3767 9.77738 1.97493 10.1643 1.68954C10.5511 1.40414 11.0192 1.25011 11.5 1.25H13C14.012 1.25 14.867 1.918 15.15 2.836M9.35 2.836C8.974 2.859 8.6 2.886 8.226 2.916C7.095 3.01 6.25 3.973 6.25 5.108V7.25M6.25 7.25H2.875C2.254 7.25 1.75 7.754 1.75 8.375V19.625C1.75 20.246 2.254 20.75 2.875 20.75H12.625C13.246 20.75 13.75 20.246 13.75 19.625V8.375C13.75 7.754 13.246 7.25 12.625 7.25H6.25ZM4.75 11H4.758V11.008H4.75V11ZM4.75 14H4.758V14.008H4.75V14ZM4.75 17H4.758V17.008H4.75V17Z";
    String pathIconEditar = "M14.862 3.487L17.5 6.125M16 13V17.75C16 18.3467 15.7629 18.919 15.341 19.341C14.919 19.763 14.3467 20 13.75 20H3.25C2.65326 20 2.08097 19.763 1.65901 19.341C1.23705 18.919 1 18.3467 1 17.75V7.25C1 6.65327 1.23705 6.08097 1.65901 5.65901C2.08097 5.23706 2.65326 5 3.25 5H8M14.862 3.487L16.549 1.799C16.9007 1.44733 17.3777 1.24976 17.875 1.24976C18.3723 1.24976 18.8493 1.44733 19.201 1.799C19.5527 2.15068 19.7502 2.62766 19.7502 3.125C19.7502 3.62235 19.5527 4.09933 19.201 4.451L8.582 15.07C8.05332 15.5984 7.40137 15.9867 6.685 16.2L4 17L4.8 14.315C5.01328 13.5986 5.40163 12.9467 5.93 12.418L14.862 3.487V3.487Z";
    String pathIconExcluir = "M13.5 3.478V3.705C14.799 3.82379 16.0927 3.99459 17.378 4.217C17.4751 4.23381 17.5678 4.26957 17.6511 4.32224C17.7343 4.37491 17.8063 4.44345 17.8631 4.52396C17.9198 4.60447 17.9601 4.69536 17.9817 4.79145C18.0033 4.88755 18.0058 4.98695 17.989 5.084C17.9722 5.18105 17.9364 5.27383 17.8838 5.35706C17.8311 5.44029 17.7626 5.51233 17.682 5.56906C17.6015 5.6258 17.5106 5.66612 17.4146 5.68773C17.3185 5.70934 17.2191 5.71181 17.122 5.695L16.913 5.66L15.908 18.73C15.8501 19.4836 15.5098 20.1875 14.9553 20.7011C14.4008 21.2146 13.6728 21.5 12.917 21.5H5.08401C4.3282 21.5 3.60026 21.2146 3.04573 20.7011C2.4912 20.1875 2.15095 19.4836 2.09301 18.73L1.08701 5.66L0.878007 5.695C0.78096 5.71181 0.681552 5.70934 0.58546 5.68773C0.489368 5.66612 0.398473 5.6258 0.317964 5.56906C0.15537 5.45448 0.0449542 5.28 0.0110065 5.084C-0.0229412 4.88801 0.0223602 4.68655 0.136945 4.52396C0.25153 4.36137 0.426012 4.25095 0.622007 4.217C1.90727 3.99433 3.20099 3.82352 4.50001 3.705V3.478C4.50001 1.914 5.71301 0.578001 7.31601 0.527001C8.43872 0.491071 9.56229 0.491071 10.685 0.527001C12.288 0.578001 13.5 1.914 13.5 3.478ZM7.36401 2.026C8.45473 1.99112 9.54629 1.99112 10.637 2.026C11.39 2.05 12 2.684 12 3.478V3.591C10.0018 3.46965 7.99817 3.46965 6.00001 3.591V3.478C6.00001 2.684 6.60901 2.05 7.36401 2.026ZM7.00901 7.971C7.0052 7.87251 6.98203 7.77573 6.94082 7.6862C6.89961 7.59666 6.84117 7.51612 6.76883 7.44916C6.69649 7.38221 6.61168 7.33017 6.51923 7.29599C6.42678 7.26182 6.3285 7.24619 6.23001 7.25C6.13151 7.25381 6.03474 7.27698 5.9452 7.31819C5.85567 7.3594 5.77512 7.41784 5.70817 7.49018C5.64122 7.56251 5.58917 7.64733 5.555 7.73978C5.52083 7.83223 5.5052 7.93051 5.50901 8.029L5.85601 17.029C5.8637 17.2278 5.95004 17.4154 6.09604 17.5505C6.16833 17.6174 6.25309 17.6694 6.34548 17.7035C6.43787 17.7377 6.53608 17.7533 6.63451 17.7495C6.73293 17.7457 6.82964 17.7225 6.91912 17.6814C7.0086 17.6402 7.08909 17.5818 7.15599 17.5095C7.22289 17.4372 7.27491 17.3524 7.30905 17.26C7.3432 17.1676 7.35881 17.0694 7.35501 16.971L7.00901 7.971ZM12.489 8.029C12.4963 7.92862 12.4834 7.82779 12.4509 7.73251C12.4185 7.63724 12.3672 7.54947 12.3001 7.47444C12.233 7.39941 12.1515 7.33866 12.0604 7.29579C11.9694 7.25292 11.8706 7.22882 11.77 7.22493C11.6694 7.22104 11.5691 7.23743 11.475 7.27312C11.3809 7.30882 11.2949 7.36309 11.2222 7.43271C11.1496 7.50233 11.0916 7.58587 11.0519 7.67834C11.0122 7.77082 10.9915 7.87035 10.991 7.971L10.644 16.971C10.6363 17.1699 10.708 17.3637 10.8432 17.5098C10.9784 17.6559 11.1661 17.7423 11.365 17.75C11.5639 17.7577 11.7577 17.6861 11.9038 17.5508C12.0499 17.4156 12.1363 17.2279 12.144 17.029L12.489 8.029V8.029Z";
    
    public void listarMedicos() {
        List<MedicoDTO> medicos = bo.listar();
        listaDeMedicos = FXCollections.observableArrayList(medicos);
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        columnCodConselho.setCellValueFactory(new PropertyValueFactory<>("codigoDoConselho"));
        columnValorConsulta.setCellValueFactory(new PropertyValueFactory<>("valorDaConsulta"));
        //columnRelatorio.setCellValueFactory(new PropertyValueFactory<>("emitirRelatorio"));
        tabelaMedicos.setItems(listaDeMedicos);
        
        UtilsController.initButtons(columnRelatorio, 18, pathIconRelatorio, "icon-svg-editar", (MedicoDTO medDTO, ActionEvent event) -> {
        	Telas.telaRelatorios(new MedicoDTO());
        });
        UtilsController.initButtons(columnEdit, 18, pathIconEditar, "icon-svg-editar", (MedicoDTO medDTO, ActionEvent event) -> {
        	editar();
        });
        UtilsController.initButtons(columnDelete, 18, pathIconExcluir, "icon-svg-excluir", (MedicoDTO medDTO, ActionEvent event) -> {
        	confirmarExclusao.setVisible(true);
        });
    }
    
    public void buscar() {
    	MedicoDTO dto = new MedicoDTO();
    	
    	dto.setCpf(busca.getText());
    	dto.setNome(busca.getText());
    	dto.setCodigoDoConselho(1);
    	dto.setValorDaConsulta(1);
    	dto.setEndereco("0");
    	
    	if(dto.getCpf().equals("") || dto.getCpf().equals(" ")) {
    		List<MedicoDTO> todos = bo.listar();
        	listaMedicosFiltrados = FXCollections.observableArrayList(todos);
    	}
    	else {
    		List<MedicoDTO> medCPF = bo.listarPorCpf(dto);
    		List<MedicoDTO> medNome = bo.listarPorNome(dto);
    		List<MedicoDTO> med = new ArrayList<MedicoDTO>();
    		for (int i = 0; i < medCPF.size(); i++) {
    			med.add(medCPF.get(i));
    		}
    		for (int i = 0; i < medNome.size(); i++) {
    			med.add(medNome.get(i));
    		}
        	listaMedicosFiltrados = FXCollections.observableArrayList(med);
    	}
    	columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        columnCodConselho.setCellValueFactory(new PropertyValueFactory<>("codigoDoConselho"));
        columnValorConsulta.setCellValueFactory(new PropertyValueFactory<>("valorDaConsulta"));
        //columnRelatorio.setCellValueFactory(new PropertyValueFactory<>("emitirRelatorio"));
        tabelaMedicos.setItems(listaMedicosFiltrados);

    }
    
    
    public void telaListarMedicos() {
    	Telas.listarMedicos();
    }
    public void listarPacientes() {
        Telas.listarPacientes();
    }
    public void listarConsultas() {
        Telas.listarConsultas();
    }
    public void cadastrar() {
    	Telas.telaCadastroMedico();
    }
    public void editar() {
    	
    	MedicoDTO dto = new MedicoDTO();
    	
    	dto.setNome(tabelaMedicos.getSelectionModel().getSelectedItem().getNome());
    	dto.setCpf(tabelaMedicos.getSelectionModel().getSelectedItem().getCpf());
    	dto.setEndereco(tabelaMedicos.getSelectionModel().getSelectedItem().getEndereco());
    	dto.setCodigoDoConselho(tabelaMedicos.getSelectionModel().getSelectedItem().getCodigoDoConselho());
    	dto.setValorDaConsulta(tabelaMedicos.getSelectionModel().getSelectedItem().getValorDaConsulta());

    	EditarMedicoController.telaEditar(dto);

    	
    }
    public void excluir() {
    	MedicoDTO dto = new MedicoDTO();
    	
    	dto.setNome(tabelaMedicos.getSelectionModel().getSelectedItem().getNome());
    	dto.setCpf(tabelaMedicos.getSelectionModel().getSelectedItem().getCpf());
    	dto.setEndereco(tabelaMedicos.getSelectionModel().getSelectedItem().getEndereco());
    	dto.setCodigoDoConselho(tabelaMedicos.getSelectionModel().getSelectedItem().getCodigoDoConselho());
    	dto.setValorDaConsulta(tabelaMedicos.getSelectionModel().getSelectedItem().getValorDaConsulta());
    	
    	bo.apagar(dto);
    	
    	confirmarExclusao.setVisible(false);
    	listarMedicos();
    }
    public void cancelar() {
    	confirmarExclusao.setVisible(false);
    }
    public void relatorio() {
    	Telas.telaRelatorios(tabelaMedicos.getItems().get(tabelaMedicos.getFocusModel().getFocusedCell().getRow()));
    }
    
}