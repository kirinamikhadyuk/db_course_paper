package presentation;

import api.service.IBiographyService;
import api.service.IModelService;
import api.service.IPersonService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Biography;
import model.Country;
import model.Person;
import service.BiographyServiceImpl;
import service.CountryServiceImpl;
import service.PersonServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CreatePersonWindowController {

    @FXML
    private TextField firstNameInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private TextField patronymicInput;
    @FXML
    private DatePicker dateOfBirthInput;
    @FXML
    private DatePicker dateOfDeathInput;
    @FXML
    private Button addBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private ChoiceBox countryPickList;

    static IPersonService personService = new PersonServiceImpl();
    static IModelService<Country> countryService = new CountryServiceImpl();

    @FXML
    void initialize() {
        List<Country> countries = countryService.getAll();
        List<String> countriesNames = new ArrayList<>();
        for(Country country : countries){
            countriesNames.add(country.getCountry());
        }
        countryPickList.setItems(FXCollections.observableArrayList(countriesNames));

        addBtn.setOnAction(event -> addPerson());

        cancelBtn.setOnAction(event -> closeWindow());
    }

    private void addPerson(){
        try {
            long id = personService.getAll().size()+2;
            String firstName = firstNameInput.getText();
            String lastName = lastNameInput.getText();
            String patronymic = patronymicInput.getText();
            Date dateOfBirth = Date.valueOf(dateOfBirthInput.getValue());
            Date dateOfDeath = Date.valueOf(dateOfDeathInput.getValue());

            String countryName = (String) countryPickList.getValue();
            List<Country> countries = countryService.getAll();
            Country countryToInsert = new Country();
            for(Country country: countries){
                if(countryPickList.getValue() == country.getCountry()){
                    countryToInsert = country;
                }
            }

            Person newPerson = new Person(id, lastName, firstName, patronymic, dateOfBirth, dateOfDeath, countryToInsert);
            personService.create(newPerson);

            Stage stage = (Stage) addBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void closeWindow(){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}
