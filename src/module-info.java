module ProjekatOOP {
	requires javafx.controls;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.mail;
	requires java.activation;
	
	opens application to javafx.graphics, javafx.fxml;
	opens login to javafx.graphics, javafx.fxml, java.mail, java.activation;
}
