package presentation;

import api.service.*;
import javafx.scene.layout.Pane;
import service.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	static IAchievementsService achievementsService = new AchievementsServiceImpl();
	static IAwardNameService awardNameService = new AwardNameServiceImpl();
	static IAwardsSphereService awardsSphereService = new AwardsSphereServiceImpl();
	static IBiographyService biographyService = new BiographyServiceImpl();
	static ICountryOfResidenceService countryOfResidenceService = new CountryOfResidenceServiceImpl();
	static IPersonService personService = new PersonServiceImpl();

	static long id = 3;

	public static void main(String[] args) {

		launch(args);

//		getById();

//		create();
//
//		getAll();
//
//		getByIdWith();
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainWindow.fxml"));
		stage.setTitle("Famous People");
		stage.setScene(new Scene(root, 700, 400));
		stage.show();
	}

	public static void create() {

		System.out.println("CREATE");
		achievementsService.create(achievementsService.getByIdWithPersonAndAward(id));
		awardNameService.create(awardNameService.getByIdWithType(id));
		awardsSphereService.create(awardsSphereService.getByIdWithPersonAndSphere(id));
		biographyService.create(biographyService.getByIdWithPerson(id));
		countryOfResidenceService.create(countryOfResidenceService.getByIdWithPersonAndCountry(id));
		personService.create(personService.getByIdWithCountry(id));
	}

	public static void getById() {

		System.out.println("GET_BY_ID");
		System.out.println(achievementsService.getById(id));
		System.out.println(awardNameService.getById(id));
		System.out.println(awardsSphereService.getById(id));
		System.out.println(biographyService.getById(id));
		System.out.println(countryOfResidenceService.getById(id));
		System.out.println(personService.getById(id));
	}

	public static void getAll() {

		System.out.println("GET_ALL");
		System.out.println(achievementsService.getAll());
		System.out.println(awardNameService.getAll());
		System.out.println(awardsSphereService.getAll());
		System.out.println(biographyService.getAll());
		System.out.println(countryOfResidenceService.getAll());
		System.out.println(personService.getAll());
	}

	public static void getByIdWith() {

		System.out.println("GET_BY_ID_WITH");
		System.out.println(achievementsService.getByIdWithPersonAndAward(id));
		System.out.println(awardNameService.getByIdWithType(id));
		System.out.println(awardsSphereService.getByIdWithPersonAndSphere(id));
		System.out.println(biographyService.getByIdWithPerson(id));
		System.out.println(countryOfResidenceService.getByIdWithPersonAndCountry(id));
		System.out.println(personService.getByIdWithCountry(id));
	}
}