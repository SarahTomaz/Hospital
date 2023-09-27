package br.edu.ufersa.hospital.api.controller;

import java.util.List;

import br.edu.ufersa.hospital.api.dto.ConsultaDTO;
import br.edu.ufersa.hospital.api.dto.MedicoDTO;
import br.edu.ufersa.hospital.model.service.ConsultaBO;
//Fazer os import do JavaFX e das views quando implementar
public class CadastrarRelatorioController {

    //fields
    @FXML private Button botaoVoltar;
    @FXML private TextField busca;
    @FXML private Button menu;
    @FXML private Button menuClose;
    @FXML private AnchorPane slider;

    @FXML private TextField nomeMedico;
    @FXML private TextField horaInicial;
    @FXML private TextField dataInicial;
    @FXML private TextField horaFinal;
    @FXML private TextField dataFinal;
    @FXML private TextArea relatorioFinal;

    //configuração inicial
    public void set(MedicoDTO medico){
        String relatorio = new String();
        ConsultaBO bo = new ConsultaBO();
        List<ConsultaDTO> lista = bo.listar();

        if (!lista.isEmpty()){
            for(int i = 0; i < lista.size(); i++){
                relatorio += "consulta ao dia";
                relatorio += lista.get(i).getData().toString();
                relatorio += "\n\n";
            }
        } else
            relatorio = "Nenhuma Consulta hoje :)";

        nomeMedico.setText(medico.getNome());
        this.relatorioFinal.setText(relatorio);
    }

    //menu
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
    void buscar(ActionEvent event) {

    }

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
