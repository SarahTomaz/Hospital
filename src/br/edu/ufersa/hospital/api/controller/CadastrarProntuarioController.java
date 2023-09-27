package br.edu.ufersa.hospital.api.controller;

import java.net.URL;
import java.util.ResourceBundle;
import br.edu.ufersa.hospital.api.dto.ProntuarioDTO;

//Fazer os import do JavaFX e das views quando implementar
public class CadastrarProntuarioController extends Object implements Initializable{

    @FXML private TextField busca;
    @FXML private Button menu;
    @FXML private Button menuClose;
    @FXML private AnchorPane slider;
    @FXML private Button botaoVoltar;

    @FXML private TextField paciente;
    @FXML private TextField horario;
    @FXML private TextField dia;
    @FXML private TextField prontuario;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("testes");
        teste();
        
    }

    public void teste(){
        System.out.println("alo");
    }

    //String medico, String paciente, String horario, String dia, String prontuario
    public void set(ProntuarioDTO prontuario)
    {
        this.paciente.setText("PACIENTE PLACEHOLDER");
        this.horario.setText(prontuario.getHorario().toString());
        this.dia.setText(prontuario.getData().toString());
        this.prontuario.setText(prontuario.getObs());
    }

    //botão menu
    @FXML
    void abrirMenu(ActionEvent event) {
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
            botaoVoltar.setVisible(false);
        });
    }

    @FXML
    void fecharMenu(ActionEvent event) {
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
            botaoVoltar.setVisible(true);
        });
    }
    
    @FXML
    public void buscar(ActionEvent event) {

    }

    //opções da tela
    public void teste(ActionEvent event) {}

    //trocar de tela
    public void logout() {
        Telas.logout();
    }
    public void listarPacientes() {
        Telas.listarPacientesAdmin();
    }
    public void listarMedicos() { 
        Telas.listarMedicosAdmin();
    }
    public void listarConsultas() {
    	Telas.listarConsultasAdmin();
    }

    
}

