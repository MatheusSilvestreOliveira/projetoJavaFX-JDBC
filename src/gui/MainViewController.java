package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDeprtament;
	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}

	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			//Recupera a cena principal
			Scene mainScene = Main.getMainScene();
			//Recupera a VBox da cena principal para alterar a visualização
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			//Recupera a barra de menu pois ela será preservada
			Node mainMenu = mainVBox.getChildren().get(0);
			
			mainVBox.getChildren().clear(); //Limpa todo o conteudo da cena
			mainVBox.getChildren().add(mainMenu); //Adiciona o menu preservado
			mainVBox.getChildren().addAll(newVBox.getChildren()); //Adiciona o conteudo novo a cena
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	
	//MÉTODO PROVISÓRIO
	private synchronized void loadView2(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			//Recupera a cena principal
			Scene mainScene = Main.getMainScene();
			//Recupera a VBox da cena principal para alterar a visualização
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			//Recupera a barra de menu pois ela será preservada
			Node mainMenu = mainVBox.getChildren().get(0);
			
			mainVBox.getChildren().clear(); //Limpa todo o conteudo da cena
			mainVBox.getChildren().add(mainMenu); //Adiciona o menu preservado
			mainVBox.getChildren().addAll(newVBox.getChildren()); //Adiciona o conteudo novo a cena
			
			DepartmentListController controller = loader.getController();
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
