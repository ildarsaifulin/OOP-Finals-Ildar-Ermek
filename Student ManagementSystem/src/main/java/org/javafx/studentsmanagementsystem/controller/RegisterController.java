package main.java.org.javafx.studentsmanagementsystem.controller;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.org.javafx.studentsmanagementsystem.application.Main;
import main.java.org.javafx.studentsmanagementsystem.model.Professor;
import main.java.org.javafx.studentsmanagementsystem.model.SQLiteJDBC;

public class RegisterController {
	
	@FXML
	private TableView<Professor> registerTableView;
	@FXML
	private TableColumn<Professor,String> registerCourseColumn;
	@FXML
	private TableColumn<Professor,String> registerProfIdColumn;
	@FXML
	private TableColumn<Professor,String> registerProfColumn;
	
	@FXML
	private JFXButton studRegisterBack;
	
	@FXML
	private Button studRegisterBtn;
	
	private ArrayList<Professor> profs = SQLiteJDBC.findCoursesUnregistered(MainController.stud);
	private ObservableList<Professor> registerData = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		addData();

		registerCourseColumn.setCellValueFactory(

				new PropertyValueFactory<Professor,String>("course"));
		registerProfIdColumn.setCellValueFactory(new PropertyValueFactory<Professor,String>("id"));
		registerProfColumn.setCellValueFactory(new PropertyValueFactory<Professor,String>("name"));
		

		registerTableView.setItems(registerData);

		studRegisterBack.setOnAction(a -> {
			System.out.println("Going to back to student's dashboard fxml");
			try {

				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Main.FXMLS + "StudentController.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Main.setContent(root1, "Student Home");
			} catch (Exception es) {
				es.printStackTrace();
			}
		});
	}
	
	private void addData() {
		for (int j = 0; j < profs.size(); j++) {
			registerData.addAll(FXCollections.observableArrayList(profs.get(j)));
		}
	}
	
	@FXML
	private void onRegisterBtnClicked() {
		System.out.println("Trying to regsister on a course");

		Professor pro = registerTableView.getSelectionModel().getSelectedItem();
		System.out.println(pro.getCourse());
		
		SQLiteJDBC.enroll(pro.getCourse());

		registerTableView.getItems().removeAll(registerTableView.getSelectionModel().getSelectedItem());
		
	}
}
