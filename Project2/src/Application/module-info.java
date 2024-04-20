module Project2 {
	requires javafx.controls;
	requires javafx.graphics;
	opens Application to javafx.graphics, javafx.fxml  ,javafx.base;
}