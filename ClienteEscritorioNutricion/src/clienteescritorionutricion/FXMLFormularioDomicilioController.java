/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorionutricion;

import clienteescritorionutricion.modelo.dao.DomicilioDAO;
import clienteescritorionutricion.modelo.pojo.Domicilio;
import clienteescritorionutricion.modelo.pojo.Estado;
import clienteescritorionutricion.modelo.pojo.Mensaje;
import clienteescritorionutricion.modelo.pojo.Municipio;
import clienteescritorionutricion.modelo.pojo.Paciente;
import clienteescritorionutricion.utils.Utilidades;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class FXMLFormularioDomicilioController implements Initializable {

    private Paciente paciente;
    private ObservableList<Estado> estados;
    private ObservableList<Municipio> municipios;
    private Domicilio domicilio;

    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfNumero;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private ComboBox<Estado> cbEstado;
    @FXML
    private ComboBox<Municipio> cbMunicipio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarInformacionEstados();
        configurarSeleccionEstado();
    }

    @FXML
    private void btnGuardarDomicilio(ActionEvent event) {
        domicilio.setCalle(tfCalle.getText());
        domicilio.setColonia(tfColonia.getText());
        domicilio.setCodigoPostal(tfCodigoPostal.getText());
        domicilio.setNumero(tfNumero.getText());
        Municipio municipioSeleccion = municipios.get(cbMunicipio.getSelectionModel().getSelectedIndex());
        domicilio.setIdMunicipio(municipioSeleccion.getIdMunicipio());
        domicilio.setIdPaciente(this.paciente.getIdPaciente());

        if (domicilio.getIdDomicilio() == -1) {
            //Registrar Domicilio
            registrarDomicilio(domicilio);
        } else {
            //Editar Domicilio
            editarDomicilio();
        }
    }
    
    private void registrarDomicilio(Domicilio domicilio){
        Mensaje msj = DomicilioDAO.registrarDomicilio(domicilio);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Domicilio Guardado", msj.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al registrar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void editarDomicilio(){
        Mensaje msj = DomicilioDAO.editarDomicilio(domicilio);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Domicilio Guardado", msj.getMensaje(), Alert.AlertType.INFORMATION);
            cerrarVentana();
        }else{
            Utilidades.mostrarAlertaSimple("Error al registrar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        cerrarVentana();
    }

    public void inicializarInformacion(Paciente paciente) {
        this.paciente = paciente;
        cargarInformacionDomicilio(this.paciente.getIdPaciente());
    }

    private void cargarInformacionEstados() {
        estados = FXCollections.observableArrayList();
        List<Estado> info = DomicilioDAO.obtenerEstados();
        estados.addAll(info);
        cbEstado.setItems(estados);
    }

    private void cargarInformacionMunicipios(int idEstado) {
        municipios = FXCollections.observableArrayList();
        List<Municipio> info = DomicilioDAO.obtenerMunicipiosEstado(idEstado);
        municipios.addAll(info);
        cbMunicipio.setItems(municipios);
    }

    private void configurarSeleccionEstado() {
        cbEstado.valueProperty().addListener(new ChangeListener<Estado>() {
            @Override
            public void changed(ObservableValue<? extends Estado> observable, Estado oldValue, Estado newValue) {
                cargarInformacionMunicipios(newValue.getIdEstado());
            }
        });
    }

    private void cargarInformacionDomicilio(int idPaciente) {
        HashMap<String, Object> respuesta = DomicilioDAO.obtenerDomicilioPaciente(idPaciente);
        int codigo = (int) respuesta.get("codigo");
        switch (codigo) {
            case HttpURLConnection.HTTP_OK:
                domicilio = (Domicilio) respuesta.get("domicilio");
                llenarCamposVentana();
                break;

            case HttpURLConnection.HTTP_NO_CONTENT:
                domicilio = new Domicilio();
                domicilio.setIdDomicilio(-1);
                break;

            default:
                Utilidades.mostrarAlertaSimple("Error", "Lo sentimos no se puede mostrar la informacion del domicilio del paciente. 😥", Alert.AlertType.WARNING);
                cerrarVentana();
                break;
        }
    }

    private void llenarCamposVentana() {
        tfCalle.setText(domicilio.getCalle());
        tfCodigoPostal.setText(domicilio.getCodigoPostal());
        tfColonia.setText(domicilio.getColonia());
        tfNumero.setText(domicilio.getNumero());
        int posicionEstado = buscarIdEstado(domicilio.getIdEstado());
        cbEstado.getSelectionModel().select(posicionEstado);
        int posicionMunicipio = buscarIdMunicipio(domicilio.getIdMunicipio());
        cbMunicipio.getSelectionModel().select(posicionMunicipio);
    }

    private void cerrarVentana() {
        Stage stage = (Stage) tfCalle.getScene().getWindow();
        stage.close();
    }

    private int buscarIdEstado(int idEstado) {
        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).getIdEstado() == idEstado) {
                return i;
            }
        }
        return 0;
    }

    private int buscarIdMunicipio(int idMunicipio) {
        for (int i = 0; i < municipios.size(); i++) {
            if (municipios.get(i).getIdMunicipio() == idMunicipio) {
                return i;
            }
        }
        return 0;
    }
}
