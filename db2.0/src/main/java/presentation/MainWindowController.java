package presentation;

import api.dao.IAwardsSphereDao;
import api.service.IAwardsSphereService;
import api.service.IPersonService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AwardsSphere;
import model.Person;
import model.interfaces.IPerson;
import service.AwardsSphereServiceImpl;
import service.PersonServiceImpl;

import java.io.IOException;
import java.util.Optional;

public class MainWindowController {

    @FXML
    private ListView<IPerson> listView;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button moreInfoBtn;

    private ObservableList listOfPersons;

    private IPerson selectedPerson;

    static IPersonService personService = new PersonServiceImpl();
    static IAwardsSphereService awardsSphereService = new AwardsSphereServiceImpl();

    @FXML
    void initialize() {
        listOfPersons = FXCollections.observableArrayList(personService.getAll());

        listView.setItems(listOfPersons);

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/createPersonWindow.fxml"));
                    Scene scene = new Scene(root, 350, 300);
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Add Person");
                    newWindow.setScene(scene);
                    newWindow.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteBtn.setOnAction(event -> {this.selectedPerson = listView.getSelectionModel().getSelectedItem();
        deletePerson();});

        moreInfoBtn.setOnAction(event -> {this.selectedPerson = listView.getSelectionModel().getSelectedItem();
        MyController.setPerson((Person) this.selectedPerson);
        getMoreInfo();});
    }

    private void deletePerson(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Person");
        alert.setHeaderText("Вы уверены что хотите удалить эту запись?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            personService.delete((Person) this.selectedPerson);
            listOfPersons = FXCollections.observableArrayList(personService.getAll());
            listView.setItems(listOfPersons);
        }
    }

    private void getMoreInfo(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/moreInfoWindow.fxml"));
            Scene scene = new Scene(root, 600, 352);
            Stage newWindow = new Stage();
            newWindow.setTitle("Guest Info Window");
            newWindow.setScene(scene);
            newWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
