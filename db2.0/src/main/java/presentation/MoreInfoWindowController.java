package presentation;

import api.service.IAchievementsService;
import api.service.IAwardsSphereService;
import api.service.IBiographyService;
import api.service.ICountryOfResidenceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.CountryOfResidence;
import model.interfaces.IPerson;
import service.AchievementsServiceImpl;
import service.AwardsSphereServiceImpl;
import service.BiographyServiceImpl;
import service.CountryOfResidenceServiceImpl;

import java.util.List;

public class MoreInfoWindowController {

    @FXML
    private ListView listView;

    @FXML
    private Button closeBtn;

    static ICountryOfResidenceService countryOfResidenceService = new CountryOfResidenceServiceImpl();
    static IAwardsSphereService awardsSphereService = new AwardsSphereServiceImpl();
    static IBiographyService biographyService = new BiographyServiceImpl();
    static IAchievementsService achievementsService = new AchievementsServiceImpl();

    static List<ObservableList> info;

    @FXML
    void initialize() {
        IPerson person = MyController.getPerson();
        String countries = String.valueOf(countryOfResidenceService.getByIdWithPersonAndCountry(person.getId()));
        String sphere = String.valueOf(awardsSphereService.getByIdWithPersonAndSphere(person.getId()));
        String biography = String.valueOf(biographyService.getByIdWithPerson(person.getId()));
        String achievement = String.valueOf(achievementsService.getByIdWithPersonAndAward(person.getId()));
        String infoString = countries + sphere + biography + achievement;
        ObservableList info = FXCollections.observableArrayList(infoString);

        listView.setItems(info);

        closeBtn.setOnAction(event -> closeWindow());
    }

    private void closeWindow(){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
