package Application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class D extends Application {
	public static DLL listd = new DLL();
	public static SLL queue = new SLL();
	public static SLL stack = new SLL();
	public static Stack s = new Stack();
	public static Queue q=new Queue(9);
	static File selectedFile=null;
	static File selectedFile1=null;

	public static void main(String[] args) {
		Application.launch(args);
	}

	public static void ReadCar(Stage s) {
		FileChooser file = new FileChooser();
		file.setTitle("selected file");
		selectedFile = file.showOpenDialog(s);
		try {
			Scanner in = new Scanner(selectedFile);
			while (in.hasNext()) {

				String[] str = in.nextLine().split(",");
				Brand l = new Brand(str[0].trim());
				CarInfo c = new CarInfo(str[1].trim(), str[2].trim(), str[3].trim(), str[4].trim());
				try {
					if (listd.isFind(l.getBrand()) == true) {
						listd.getLast().element.list.addLast(c);
						
					} else {
						listd.addLast(l);
						
						listd.getLast().element.list.addLast(c);
					
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public static void SaveCarFile() throws Exception {
		try {
			PrintWriter out = new PrintWriter(selectedFile);
			NodeDL S = listd.getfirst();
			String s = "";
			while (S!=null) {
				s += S.toString() + S.element.list.toString();
				S = S.getNext();
			}
			out.write(s);
			out.close();
		} catch (FileNotFoundException mo) {
			System.out.println(mo);
		}
	}

	@Override
	public void start(Stage primaryStage) {
		Button b1 = new Button("Client");
		Button b2 = new Button("Admin");
		Label l = new Label("Car Agency");
		l.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 40));
		ImageView imag1 = new ImageView("Imag1.png");
		HBox h1 = new HBox(10);
		HBox h2 = new HBox(10);
		h2.getChildren().addAll(l);
		h2.setAlignment(Pos.TOP_CENTER);
		h1.getChildren().addAll(b1, b2);
		h1.setAlignment(Pos.BOTTOM_CENTER);
		StackPane p1 = new StackPane();
		p1.getChildren().addAll(imag1, h2, h1);
		Scene s1 = new Scene(p1);
		primaryStage.setScene(s1);
		primaryStage.setTitle("Car agency");
		primaryStage.show();

//-----------------------------------------client---------------------------------------------------------------------------	
		Stage st1 = new Stage();
		TabPane tab = new TabPane();
		Tab tab1 = new Tab("Client");
		TextField tSearch1 = new TextField();
		TextField tSearch2 = new TextField();
		TextField tSearch3 = new TextField();
		TextField tSearch4 = new TextField();
		VBox tv = new VBox(10);
		tv.getChildren().addAll(tSearch1, tSearch2, tSearch3, tSearch4);
		VBox checkpane = new VBox(10);
		CheckBox ch1 = new CheckBox("Model");
		CheckBox ch2 = new CheckBox("Year");
		CheckBox ch3 = new CheckBox("Color");
		CheckBox ch4 = new CheckBox("Price");
		checkpane.getChildren().addAll(ch1, ch2, ch3, ch4);
		ComboBox<String> cbo2 = new ComboBox<>();
		cbo2.getItems().addAll("MERCEDES", "TESLA", "BMW", "FORD", "KIA");
		HBox h = new HBox(10);
		h.getChildren().addAll(checkpane, tv, cbo2);
		h.setAlignment(Pos.TOP_CENTER);
		tab.getTabs().add(tab1);
		Button bb0 = new Button("Load Car File");
		Scene s2 = new Scene(tab);
		st1.setScene(s2);
		st1.setTitle("client");
		TableView<CarInfo> table = new TableView();
		TableColumn model = new TableColumn("Model");
		model.setMinWidth(100);
		model.setCellValueFactory(new PropertyValueFactory<CarInfo, String>("model"));
		TableColumn year = new TableColumn("Year");
		year.setMinWidth(100);
		year.setCellValueFactory(new PropertyValueFactory<CarInfo, Date>("year"));
		TableColumn color = new TableColumn("Color");
		color.setMinWidth(100);
		color.setCellValueFactory(new PropertyValueFactory<CarInfo, String>("color"));
		TableColumn price = new TableColumn("Price");
		price.setMinWidth(100);
		price.setCellValueFactory(new PropertyValueFactory<CarInfo, String>("price"));
		table.getColumns().addAll(model, year, color, price);
		Label L = new Label();
		L.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
		Button bu1 = new Button("<");
		Button bu2 = new Button("ADD TO CART");
		Button bu4=new Button("Save");
		Button bu3 = new Button(">");
		Button b0 = new Button("<<<");
		b0.setAlignment(Pos.BOTTOM_RIGHT);
		b0.setOnAction(e -> {
			st1.close();
			primaryStage.show();

		});
		EventHandler<ActionEvent> handler = e1 -> {
			if (ch1.isSelected() && ch2.isSelected() && ch3.isSelected() && ch4.isSelected()) {
				table.getItems().clear();
				String sT1 = tSearch1.getText();
				String sT2 = tSearch2.getText();
				String sT3 = tSearch3.getText();
				String sT4 = tSearch4.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getModel().contains(sT1) && c.getYear().contains(sT2) && c.getColor().contains(sT3)
							&& c.getPrice().contains(sT4)) {

						table.getItems().add(c);
					}
					current = current.next;
				}

			} else if (ch1.isSelected() && ch2.isSelected() && ch3.isSelected()) {
				table.getItems().clear();
				String sT1 = tSearch1.getText();
				String sT2 = tSearch1.getText();
				String sT3 = tSearch1.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getModel().contains(sT1) && c.getYear().contains(sT2) && c.getColor().contains(sT3)) {

						table.getItems().add(c);
					}
					current = current.next;
				}
			} else if (ch1.isSelected() && ch2.isSelected()) {
				table.getItems().clear();
				String sT1 = tSearch1.getText();
				String sT2 = tSearch1.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getModel().contains(sT1) && c.getYear().contains(sT2)) {

						table.getItems().add(c);
					}
					current = current.next;
				}
			} else if (ch1.isSelected()) {
				table.getItems().clear();
				String sT1 = tSearch1.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getModel().contains(sT1)) {
						table.getItems().add(c);
					}
					current = current.next;

				}
			} else if (ch2.isSelected()) {
				table.getItems().clear();
				String sT2 = tSearch2.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getYear().contains(sT2)) {

						table.getItems().add(c);
					}
					current = current.next;

				}
			} else if (ch3.isSelected()) {
				table.getItems().clear();
				String sT3 = tSearch3.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getColor().contains(sT3)) {

						table.getItems().add(c);
					}
					current = current.next;
				}
			} else if (ch4.isSelected()) {
				table.getItems().clear();
				String sT4 = tSearch4.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getPrice().contains(sT4)) {
						table.getItems().add(c);
					}
					current = current.next;

				}
			} else if (ch1.isSelected() && ch3.isSelected()) {
				table.getItems().clear();
				String sT1 = tSearch1.getText();
				String sT3 = tSearch3.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getModel().contains(sT1) && c.getColor().contains(sT3)) {

						table.getItems().add(c);
					}
					current = current.next;
				}
			} else if (ch1.isSelected() && ch4.isSelected()) {
				table.getItems().clear();
				String sT1 = tSearch1.getText();
				String sT4 = tSearch4.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getModel().contains(sT1) && c.getPrice().contains(sT4)) {

						table.getItems().add(c);
					}
					current = current.next;
				}
			} else if (ch2.isSelected() && ch3.isSelected()) {
				table.getItems().clear();
				String sT2 = tSearch2.getText();
				String sT3 = tSearch3.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getYear().contains(sT2) && c.getColor().contains(sT3)) {

						table.getItems().add(c);
					}
					current = current.next;

				}
			} else if (ch2.isSelected() && ch4.isSelected()) {
				table.getItems().clear();
				String sT2 = tSearch2.getText();
				String sT4 = tSearch4.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getYear().contains(sT2) && c.getPrice().contains(sT4)) {

						table.getItems().add(c);
					}
					current = current.next;

				}
			} else if (ch3.isSelected() && ch4.isSelected()) {
				table.getItems().clear();
				String sT3 = tSearch3.getText();
				String sT4 = tSearch4.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getColor().contains(sT3) && c.getPrice().contains(sT4)) {

						table.getItems().add(c);
					}
					current = current.next;
				}
			} else if (ch1.isSelected() && ch2.isSelected() && ch4.isSelected()) {
				table.getItems().clear();
				String sT1 = tSearch1.getText();
				String sT2 = tSearch2.getText();
				String sT4 = tSearch4.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getModel().contains(sT1) && c.getYear().contains(sT2) && c.getPrice().contains(sT4)) {

						table.getItems().add(c);
					}
					current = current.next;

				}
			} else if (ch2.isSelected() && ch3.isSelected() && ch4.isSelected()) {
				table.getItems().clear();
				String sT2 = tSearch2.getText();
				String sT3 = tSearch3.getText();
				String sT4 = tSearch4.getText();
				NodeSL current = listd.first.element.list.first;
				for (int i = 0; i < listd.first.element.list.getSize(); i++) {
					CarInfo c = (CarInfo) current.element;
					if (c.getYear().contains(sT2) && c.getColor().contains(sT3) && c.getPrice().contains(sT4)) {

						table.getItems().add(c);
					}
					current = current.next;

				}

			}
		};
		ch1.setOnAction(handler);
		ch2.setOnAction(handler);
		ch3.setOnAction(handler);
		ch4.setOnAction(handler);

		HBox h3 = new HBox(10);
		h3.getChildren().addAll(bu1, bu2, bu3, b0,bu4);
		h3.setAlignment(Pos.BOTTOM_CENTER);
		VBox v1 = new VBox(10);
		v1.getChildren().addAll(h, L, table, h3, bb0);
		v1.setAlignment(Pos.CENTER);
		tab1.setContent(v1);
		cbo2.setOnAction(e -> {
			L.setText(cbo2.selectionModelProperty().getValue().getSelectedItem());
		});
		Stage ss = new Stage();
		Label l01 = new Label("Brand");
		Label l02 = new Label("Model");
		Label l3 = new Label("Year");
		Label l4 = new Label("Color");
		Label l5 = new Label("Price");
		Label l6 = new Label("CustomerName");
		Label l7 = new Label("CustomerMobile");
		Label l8 = new Label("OrderDate");
		Label l9 = new Label("OrderStatus");
		l01.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 11));
		l02.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 11));
		l3.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 11));
		l4.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 11));
		l5.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 11));
		l6.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 11));
		l7.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 11));
		l8.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 11));
		l9.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 11));
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		TextField t4 = new TextField();
		TextField t5 = new TextField();
		TextField t6 = new TextField();
		TextField t7 = new TextField();
		TextField t8 = new TextField();
		TextField t9 = new TextField();
		Button b011 = new Button("ADD TO CART");
		VBox vv = new VBox(5);
		vv.getChildren().addAll(l01, t1, l02, t2, l3, t3, l4, t4, l5, t5, l6, t6, l7, t7, l8, t8, l9, t9, b011);
		vv.setAlignment(Pos.TOP_LEFT);
		Scene ss0 = new Scene(vv);
		ss.setScene(ss0);
		bb0.setOnAction(e -> {
			table.getItems().clear();
			ReadCar(st1);
            NodeSL current=listd.getfirst().element.list.first;
            try {
				if (listd.isFind(L.getText()) == true) {
					for (int i = 0; i < listd.getLast().element.list.getSize(); i++) {
						CarInfo c = new CarInfo(((CarInfo) current.element).getModel(),
								((CarInfo) current.element).getYear(), ((CarInfo) current.element).getColor(),
								((CarInfo) current.element).getPrice());

						table.getItems().add(c);
						current = current.next;

					}
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		});
		bu3.setOnAction(e -> {
			NodeDL current1 = listd.first;
			table.getItems().clear();
			while (current1 != null && current1.next != null) {
				current1 = current1.next;
				L.setText(current1.element.getBrand());
				if (current1 != null && current1.next != null) {
					for (int i = 0; i < listd.first.element.list.getSize(); i++) {
						CarInfo c = new CarInfo(((CarInfo) current1.element.list.first.element).getModel(),
								((CarInfo) current1.element.list.first.element).getYear(),
								((CarInfo) current1.element.list.first.element).getColor(),
								((CarInfo) current1.element.list.first.element).getPrice());
						table.getItems().add(c);
						current1 = current1.next;

					}
				}

			}

		});
		bu1.setOnAction(e -> {
			NodeDL current1 = listd.last;
			while (current1 != null && current1.getPrev() != null) {
				current1 = current1.prev;
				L.setText(current1.element.getBrand());

				if (current1 != null && current1.getPrev() != null) {
					table.getItems().clear();
					for (int i = 0; i < listd.getSize() - 1; i++) {
						CarInfo c = new CarInfo(((CarInfo) current1.element.list.last.element).getModel(),
								((CarInfo) current1.element.list.last.element).getYear(),
								((CarInfo) current1.element.list.last.element).getColor(),
								((CarInfo) current1.element.list.last.element).getPrice());
						table.getItems().add(c);
						current1 = current1.prev;
					}
				}
			}

		});
		bu2.setOnAction(e -> {
			if (table.selectionModelProperty().getValue().getSelectedItems() != null) {
				t2.setText(table.getSelectionModel().getSelectedItem().getModel());
				t3.setText(table.getSelectionModel().getSelectedItem().getYear());
				t4.setText(table.getSelectionModel().getSelectedItem().getColor());
				t5.setText(table.getSelectionModel().getSelectedItem().getPrice());
				t1.setText(L.getText());
				ss.show();

			}
		});

		b1.setOnAction(e1 -> {
			primaryStage.close();
			st1.show();

		});
		bu4.setOnAction(e->{
			try {
				SaveCarFile();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});

//-------------------------------------------------Admin----------------------------------------------------------------------------
		Stage st2 = new Stage();
		TabPane tabp = new TabPane();
		Tab tab01 = new Tab("Queue");
		Tab tab02=new Tab("Stack");
		Tab tab03 = new Tab("SearchBrand");
		Tab tab04 = new Tab("SearchCar");
		tabp.getTabs().addAll(tab01,tab02, tab03, tab04);
		TableView<Order> table2 = new TableView();
		TableColumn CustomerName = new TableColumn("CustomerName");
		CustomerName.setMinWidth(100);
		CustomerName.setCellValueFactory(new PropertyValueFactory<Order, String>("CustomerName"));
		TableColumn CustomerMobile = new TableColumn("CustomerMobile");
		CustomerMobile.setMinWidth(100);
		CustomerMobile.setCellValueFactory(new PropertyValueFactory<Order, String>("CustomerMobile"));
		TableColumn Brand = new TableColumn("Brand");
		Brand.setMinWidth(100);
		Brand.setCellValueFactory(new PropertyValueFactory<Order, String>("Brand"));
		TableColumn Model = new TableColumn("Model");
		Model.setMinWidth(100);
		Model.setCellValueFactory(new PropertyValueFactory<Order, String>("Model"));
		TableColumn Year = new TableColumn("Year");
		Year.setMinWidth(100);
		Year.setCellValueFactory(new PropertyValueFactory<Order, String>("Year"));
		TableColumn Color = new TableColumn("Color");
		Color.setMinWidth(100);
		Color.setCellValueFactory(new PropertyValueFactory<Order, String>("Color"));
		TableColumn Price = new TableColumn("Price");
		Price.setMinWidth(100);
		Price.setCellValueFactory(new PropertyValueFactory<Order, String>("Price"));
		TableColumn orderDate = new TableColumn("OrderDate");
		orderDate.setMinWidth(100);
		orderDate.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDate"));
		TableColumn orderStatus = new TableColumn("OrderStatus ");
		orderStatus.setMinWidth(100);
		orderStatus.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatus"));
		table2.getColumns().addAll(CustomerName, CustomerMobile, Brand, Model, Year, Color, Price, orderDate,
				orderStatus);
		TableView<Order> table3 = new TableView();
		TableColumn customerName1 = new TableColumn("CustomerName");
		customerName1.setMinWidth(100);
		customerName1.setCellValueFactory(new PropertyValueFactory<Order, String>("customerName"));
		TableColumn customerMobile1 = new TableColumn("CustomerMobile");
		customerMobile1.setMinWidth(100);
		customerMobile1.setCellValueFactory(new PropertyValueFactory<Order, String>("customerMobile"));
		TableColumn brand1 = new TableColumn("Brand");
		brand1.setMinWidth(100);
		brand1.setCellValueFactory(new PropertyValueFactory<Order, String>("brand"));
		TableColumn modelc1 = new TableColumn("Model");
		modelc1.setMinWidth(100);
		modelc1.setCellValueFactory(new PropertyValueFactory<Order, String>("model"));
		TableColumn yearc1 = new TableColumn("Year");
		yearc1.setMinWidth(100);
		yearc1.setCellValueFactory(new PropertyValueFactory<Order, String>("year"));
		TableColumn colorc1 = new TableColumn("Color");
		colorc1.setMinWidth(100);
		colorc1.setCellValueFactory(new PropertyValueFactory<Order, String>("color"));
		TableColumn pricec1 = new TableColumn("Price");
		pricec1.setMinWidth(100);
		pricec1.setCellValueFactory(new PropertyValueFactory<Order, String>("price"));
		TableColumn orderDate1 = new TableColumn("OrderDate");
		orderDate1.setMinWidth(100);
		orderDate1.setCellValueFactory(new PropertyValueFactory<Order, String>("orderDate"));
		TableColumn orderStatus1 = new TableColumn("OrderStatus ");
		orderStatus1.setMinWidth(100);
		orderStatus1.setCellValueFactory(new PropertyValueFactory<Order, String>("orderStatus"));
		table3.getColumns().addAll(customerName1, customerMobile1, brand1, modelc1, yearc1, colorc1, pricec1,
				orderDate1,orderStatus1);
		Button b01 = new Button("Process");
		Button b003 = new Button("InProcess");
		Button b02 = new Button("Last 10 sold");
		Button bb6=new Button("Save");
		Label l1 = new Label("Queue");
		l1.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
		Label l2 = new Label("Stack");
		l2.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
		Button b03 = new Button("<<<");
		Button b04 = new Button("Load order file");
		b03.setAlignment(Pos.BOTTOM_RIGHT);
		b03.setOnAction(e -> {
			st2.close();
			primaryStage.show();
		});
		TableView<CarInfo> table6 = new TableView();
		TableColumn model1 = new TableColumn("Model");
		model1.setMinWidth(100);
		model1.setCellValueFactory(new PropertyValueFactory<CarInfo, String>("model"));
		TableColumn year1 = new TableColumn("Year");
		year1.setMinWidth(100);
		year1.setCellValueFactory(new PropertyValueFactory<CarInfo, Date>("year"));
		TableColumn color1 = new TableColumn("Color");
		color1.setMinWidth(100);
		color1.setCellValueFactory(new PropertyValueFactory<CarInfo, String>("color"));
		TableColumn price1 = new TableColumn("Price");
		price1.setMinWidth(100);
		price1.setCellValueFactory(new PropertyValueFactory<CarInfo, String>("price"));
		table6.getColumns().addAll(model1, year1, color1, price1);
		Button bb01 = new Button("Search");
		Button bb02 = new Button("Add");
		Button bb03 = new Button("Delete");
		Button bb04 = new Button("Update");
		TextField tt01 = new TextField();
		Label label1 = new Label("Model");
		label1.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
		TextField tt02 = new TextField();
		Label label2 = new Label("Year");
		label2.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
		TextField tt03 = new TextField();
		Label label3 = new Label("Color");
		label3.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
		TextField tt04 = new TextField();
		Label label4 = new Label("Price");
		label4.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
		HBox hh01 = new HBox(10);
		hh01.getChildren().addAll(label1, tt01, label2, tt02, label3, tt03, label4, tt04);
		HBox hh011 = new HBox(10);
		hh011.getChildren().addAll(bb01, bb02, bb03, bb04);
		hh011.setAlignment(Pos.BOTTOM_CENTER);
		VBox V01 = new VBox(10);
		V01.getChildren().addAll(table6, hh01, hh011);
		V01.setAlignment(Pos.CENTER);
		tab04.setContent(V01);
		TableView<Brand> table5 = new TableView();
		TableColumn brand = new TableColumn("Brand");
		brand.setMinWidth(10);
		brand.setCellValueFactory(new PropertyValueFactory<Brand, String>("brand"));
		table5.getColumns().add(brand);
		Button bb1 = new Button("Search");
		Button bb2 = new Button("Add");
		Button bb3 = new Button("Delete");
		Button bb4 = new Button("Update");
		Button bb5 = new Button("Load Car File");
		TextField tt = new TextField();
		Label l11 = new Label("Brand");
		l11.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
		HBox hh1 = new HBox(10);
		hh1.getChildren().addAll(l11, tt);
		HBox hh = new HBox(10);
		hh.getChildren().addAll(bb1, bb2, bb3, bb4, bb5);
		hh.setAlignment(Pos.BOTTOM_CENTER);
		VBox V1 = new VBox(10);
		V1.getChildren().addAll(table5, hh1, hh);
		V1.setAlignment(Pos.CENTER);
		tab03.setContent(V1);
		HBox hhh = new HBox(10);
		hhh.getChildren().addAll(b003, b01);
		hhh.setAlignment(Pos.CENTER);
		VBox v0 = new VBox(10);
		v0.getChildren().addAll(l1, table2, b003,b01, b03,b04);
		v0.setAlignment(Pos.TOP_CENTER);
		VBox v01 = new VBox(10);
		v01.getChildren().addAll(l2, table3, b02,bb6);
		v01.setAlignment(Pos.TOP_CENTER);
		HBox b = new HBox(10);
		b.getChildren().add(v0);
		HBox bbb=new HBox(10);
		bbb.getChildren().add(v01);
		tab01.setContent(b);
		tab02.setContent(bbb);
		Scene s3 = new Scene(tabp);
		st2.setScene(s3);
		st2.setTitle("Admin");
		b2.setOnAction(e -> {
			primaryStage.close();
			st2.show();
		});
		b011.setOnAction(e -> {
			Order o = new Order(t6.getText(), t7.getText(), t1.getText(), t2.getText(), t3.getText(), t4.getText(),
					t5.getText(), t8.getText(), t9.getText());
			queue.addFirst(o);
			table2.getItems().add(o);

		});
		bb5.setOnAction(e -> {
			ReadCar(st2);
			NodeDL current = listd.first;
			for (int i = 0; i < listd.getSize(); i++) {
				table5.getItems().add(current.element);
				current = current.next;
			}

		});
		bb1.setOnAction(e -> {
			table6.getItems().clear();
			NodeSL current = listd.first.element.list.first;
			if (table5.getSelectionModel().getSelectedItems() != null) {
				for (int i = 0; i < listd.getSize() - 1; i++) {
					CarInfo c = new CarInfo(((CarInfo) current.element).getModel(),
							((CarInfo) current.element).getYear(), ((CarInfo) current.element).getColor(),
							((CarInfo) current.element).getPrice());
					listd.getfirst().getElement().list.addLast(c);
					table6.getItems().add(c);
					current = current.next;
				}

			}
		});
		bb2.setOnAction(e -> {
			try {
				if (!tt.getText().equals(table5.getItems())) {
					Brand B = new Brand(tt.getText());
					listd.addFirst(B);
					table5.getItems().add(B);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		bb3.setOnAction(e -> {
			if (table5.getSelectionModel().getSelectedItems() != null) {
				listd.remove(table5.getSelectionModel().getSelectedItem());
				table5.getItems().removeAll(table5.getSelectionModel().getSelectedItem());
			}
		});
		bb4.setOnAction(e -> {
			NodeDL current = listd.first;
			if (table5.getSelectionModel().getSelectedItems() != null) {
				current.element.setBrand(tt.getText());
				table5.getSelectionModel().getSelectedItem().getBrand().contains(tt.getText());
			}
		});
		b04.setOnAction(e -> {
			FileChooser file = new FileChooser();
			file.setTitle("selected file");
		    selectedFile1 = file.showOpenDialog(st2);
			try {
				Scanner in = new Scanner(selectedFile1);
				while (in.hasNext()) {

					String[] str1 = in.nextLine().split(",");
					Order o = new Order(str1[0], str1[1], str1[2], str1[3], str1[4], str1[5], str1[6], str1[7],
							str1[8]);
					if (o.getOrderStatus().contains("InProcess")) {
					      queue.addLast(o);
					      table2.getItems().add(o);
					}
					else if (o.getOrderStatus().contains("Finished")) {
						stack.addFirst(o);
						table3.getItems().add(o);
					}
					
				}
				queue.printList();
				stack.printList();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		bb01.setOnAction(e -> {
			table6.getItems().clear();
			String sT2 = tt02.getText();
			NodeSL current = listd.first.element.list.first;
			for (int i = 0; i < listd.getSize(); i++) {
				CarInfo c = (CarInfo) current.element;
				if (c.getYear().contains(sT2)) {

					table6.getItems().add(c);
				}
				current = current.next;

			}
		});
		bb02.setOnAction(e -> {
			if (!tt01.getText().equals(table6.getItems()) && !tt02.getText().equals(table6.getItems())
					&& !tt03.getText().equals(table6.getItems()) && !tt04.getText().equals(table6.getItems())) {
				CarInfo c = new CarInfo(tt01.getText(), tt02.getText(), tt03.getText(), tt04.getText());
				listd.getfirst().getElement().getList().addLast(c);
				table6.getItems().add(c);
			}
		});
		bb03.setOnAction(e -> {
			if (table6.getSelectionModel().getSelectedItems() != null) {
				CarInfo c = new CarInfo(table6.getSelectionModel().getSelectedItem().getModel(),
						table6.getSelectionModel().getSelectedItem().getYear(),
						table6.getSelectionModel().getSelectedItem().getColor(),
						table6.getSelectionModel().getSelectedItem().getPrice());
				table6.getItems().removeAll(table6.getSelectionModel().getSelectedItems());
				listd.getfirst().element.getList().removeLast();
			}
			
		});
		bb04.setOnAction(e -> {
			if (table6.getSelectionModel().getSelectedItems() != null) {
				if (!tt01.getText().equals(table6.getItems()) && !tt02.getText().equals(table6.getItems())
						&& !tt03.getText().equals(table6.getItems()) && !tt04.getText().equals(table6.getItems())) {
					CarInfo c = new CarInfo(
							table6.getSelectionModel().getSelectedItem().getModel().concat(tt01.getText()),
							table6.getSelectionModel().getSelectedItem().getYear().concat(tt02.getText()),
							table6.getSelectionModel().getSelectedItem().getColor().concat(tt03.getText()),
							table6.getSelectionModel().getSelectedItem().getPrice().concat(tt04.getText()));
					table6.getSelectionModel().getSelectedItems().contains(c);
				}
			}
		});
		b01.setOnAction(e->{
			
			if(table2.getItems() !=null) {
				NodeSL current=queue.first;
				Order o1 = null;
				for(int i=0;i<queue.getSize();i++) {
					 o1 = new Order(((Order) current.element).getCustomerName(),
							((Order) current.element).getCustomerMobile(), ((Order) current.element).getBrand(),
							((Order) current.element).getModel(), ((Order) current.element).getYear(),
							((Order) current.element).getColor(), ((Order) current.element).getPrice(),
							((Order) current.element).getOrderDate(),((Order) current.element).getOrderStatus());
					 o1.setOrderStatus(" Finished");
					 
				}
				stack.addFirst(o1);
				table3.getItems().add(o1);
				table2.getItems().removeAll(((Order) current.element));
				queue.removeLast();
				current =current.next;
			}		
			
		});
		b003.setOnAction(e -> {

			if (table2.getItems() != null) {
				NodeSL current = queue.first.next;
				SLL q1=null;
				Order o=null;
				for (int i = 0; i < queue.getSize(); i++) {
					    q1 = new SLL();
					    o = new Order(((Order) current.element).getCustomerName(),
								((Order) current.element).getCustomerMobile(), ((Order) current.element).getBrand(),
								((Order) current.element).getModel(), ((Order) current.element).getYear(),
								((Order) current.element).getColor(), ((Order) current.element).getPrice(),
								((Order) current.element).getOrderDate(), ((Order) current.element).getOrderStatus());
					    
				}
				q1.addLast(o);
				table2.getItems().removeAll(((Order)current.element));
				queue.removeLast();
				table2.getItems().add(o);
				 
				
				
			}
		});
		b02.setOnAction(e->{
			table3.getItems().clear();
			if (table3.getItems() != null) {
				NodeSL current = stack.first;
				SLL s=null;
				Order o=null;
				for(int i=0 ;i<10;i++) {
					 s=new SLL();
					   o = new Order(((Order) current.element).getCustomerName(),
								((Order) current.element).getCustomerMobile(), ((Order) current.element).getBrand(),
								((Order) current.element).getModel(), ((Order) current.element).getYear(),
								((Order) current.element).getColor(), ((Order) current.element).getPrice(),
								((Order) current.element).getOrderDate(), ((Order) current.element).getOrderStatus());
					s.addFirst(o);
					stack.removeLast();
					//table3.getItems().removeAll(((Order)current.element));
					s.removeLast();
					stack.addFirst(o);
					table3.getItems().add(o);
					current=current.next;
				}
				
			}
		});
		bb6.setOnAction(e->{
			try {
				PrintWriter out = new PrintWriter(selectedFile1);
				NodeSL S = queue.first;
				FileOutputStream fos = new FileOutputStream(selectedFile);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
				while(S!=null) {
					bw.write(((Order)S.element).toString());
					S=S.getNext();
				}
				out.close();
			} catch (FileNotFoundException mo) {
				System.out.println(mo);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		

	}

}
