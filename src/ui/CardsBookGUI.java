package ui;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.CardsBook;

public class CardsBookGUI {
	
	private Stage primaryStage;
	
	private CardsBook cardsbook;
	
	@FXML
    private ImageView mainPhoto1;

    @FXML
    private ImageView mainPhoto;

	@FXML
    private BorderPane mainPane;
	
	@FXML
    private RadioButton buttonMale;

    @FXML
    private RadioButton buttonFemale;

    @FXML
    private RadioButton buttonOther;
    
    @FXML
    private TextField txtProfilePhoto;
    
    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private DatePicker datePicker;
    
    @FXML
    private TextField txtPhone;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private ComboBox<String> emailBox;
    
    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

	public CardsBookGUI(CardsBook cb) {
		cardsbook = cb;
	}
	
	@FXML
	public void loadSignUp(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(root);
		primaryStage.close();
		primaryStage.show();
		emailBox.getItems().addAll("@gmail.com","@hotmail.com","@outlook.com","@yahoo.es");
	}
	
	@FXML
	public void loadLogin(ActionEvent event) throws IOException {
		loadLogin();
	}
	
	@FXML
	public void openFileChooser(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));
		File file = fileChooser.showOpenDialog(primaryStage);
		if (file != null) {
			txtProfilePhoto.setText(file.getAbsolutePath());
		}
	}
	
	@FXML
    public void selectMale(ActionEvent event) {
		buttonFemale.setSelected(false);
		buttonOther.setSelected(false);
    }
	
	@FXML
    public void selectFemale(ActionEvent event) {
		buttonMale.setSelected(false);
		buttonOther.setSelected(false);
    }
	
	@FXML
    public void selectOther(ActionEvent event) {
		buttonFemale.setSelected(false);
		buttonMale.setSelected(false);
    }
	
	@FXML
    public void verifyInformation(ActionEvent event) throws IOException {
		if((!buttonFemale.isSelected()&&!buttonMale.isSelected()&&!buttonOther.isSelected()) || 
				txtUserName.getText().trim().equals("") || txtPassword.getText().trim().equals("") || 
				txtProfilePhoto.getText().trim().equals("") || datePicker.getValue()==null || 
				txtPhone.getText().trim().equals("") || txtEmail.getText().trim().equals("") || 
				emailBox.getValue()==null || emailBox.getValue().trim().equals("")) {
			validationAlert("You must fill each field in the form", "error.png", "Validation Error", "OK");
		} else if(cardsbook.verifyUsername(txtUserName.getText())){
			validationAlert("The username entered already exists", "error.png", "Validation Error", "OK");
		} else {
			createAccount();
		}
    }
	
	@FXML
    public void verifyCredentials(ActionEvent event) throws IOException {
		if(loginUsername.getText().trim().equals("") || loginPassword.getText().trim().equals("")) {
			validationAlert("You must fill each field in the form", "error.png", "Validation Error", "OK");
		} else if(cardsbook.verifyCredentials(loginUsername.getText().trim(), loginPassword.getText().trim())) {
			loadPrincipal();
		} else{
			validationAlert("The credentials entered are not valid", "error.png", "Validation Error", "OK");
		}
    }
	
	private void loadPrincipal() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("principal.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(root);
		primaryStage.close();
		primaryStage.show();
		mainPhoto.setImage(new Image(cardsbook.getActiveUser().getPhoto()));
	}

	private void validationAlert(String msg, String icon, String tittle, String button) {
		ButtonType ok = new ButtonType(button);
		Alert user = new Alert(AlertType.NONE);
		user.getButtonTypes().addAll(ok);
		user.setTitle(tittle);
		user.setContentText(msg);
		Stage stage = (Stage)user.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(getClass().getResourceAsStream(icon)));
		user.showAndWait();
	}
	
	private void createAccount() throws IOException {
		
		String photo = "file:" + txtProfilePhoto.getText();
		String gender = "";
		if(buttonFemale.isSelected()) {
			gender = "Female";
		} else if(buttonMale.isSelected()) {
			gender = "Male";
		} else if(buttonOther.isSelected()) {
			gender = "Other";
		}
		
		cardsbook.createAccount(txtUserName.getText().trim(), txtPassword.getText().trim(), photo, gender, 
				datePicker.getValue(), txtPhone.getText().trim(), txtEmail.getText().trim()+emailBox.getValue());
		
		validationAlert("The account has been created", "validation.png", "Confirmation", "OK");
		
		loadLogin();
	}

	public void loadLogin() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(root);
		primaryStage.close();
		primaryStage.show();
	}
	
	public void setStage(Stage stage) {
		primaryStage = stage;
	}
}
