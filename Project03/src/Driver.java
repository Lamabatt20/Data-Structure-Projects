import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Driver extends Application {
	public static AvlTree<Yeary> yAvl = new AvlTree<Yeary>();
	public TextArea text;
	public TextField hy;
	public TextField hm;
	public TextField hd;
	public TextField rhy;
	public TextField rhm;
	public TextField rhd;
	public DatePicker d;

	public static void main(String[] args) {
		Application.launch(args);

	}

	// print trees level by level
	private void PrintTrees(LocalDate searchDate) {
		if (searchDate != null) {
			int year = searchDate.getYear();
			int month = searchDate.getMonthValue();
			int day = searchDate.getDayOfMonth();

			TreeNode<Yeary> currentYear = yAvl.find(new Yeary(year));
			if (currentYear != null) {
				StringBuilder result = new StringBuilder();
				result.append("Years Tree:\n");
				yAvl.traverseLevell(result);

				Monthm mm = new Monthm(month);
				TreeNode<Monthm> currentMonth = currentYear.data.getmAvl().find(mm);
				if (currentMonth != null) {
					result.append("Months Tree:\n");
					currentYear.data.getmAvl().traverseLevell(result);

					Day dd = new Day(day);
					TreeNode<Day> currentDay = currentMonth.data.getdAvl().find(dd);
					if (currentDay != null) {
						result.append("Days Tree:\n");
						currentMonth.data.getdAvl().traverseLevell(result);
					} else {
						result.append("Day not found.\n");
					}
				} else {
					result.append("Month not found.\n");
				}
				text.setText(result.toString());
			} else {
				text.setText("Year not found");
			}
		}
	}

	// Search record by date
	private Electricity Search(LocalDate date) {
		TreeNode<Yeary> currentYear = yAvl.find(new Yeary(date.getYear()));
		if (currentYear != null) {
			int yearHeight = yAvl.heighte(currentYear);
			hy.setText(yearHeight + "");
			rhy.setText(yAvl.root + "," + yAvl.root.Height);
			Monthm m1 = new Monthm(date.getMonthValue());
			String mm = m1.getMonthName(date.getMonthValue());
			Monthm m = new Monthm(mm);
			TreeNode<Monthm> currentMonth = currentYear.data.getmAvl().find(m);
			if (currentMonth != null) {
				int monthHeight = currentYear.data.getmAvl().heighte(currentMonth);
				hm.setText(monthHeight + "");
				rhm.setText(yAvl.root.data.getmAvl().root + "," + yAvl.root.data.getmAvl().root.Height);
				TreeNode<Day> currentDay = currentMonth.data.getdAvl().find(new Day(date.getDayOfMonth(), null));
				if (currentDay != null) {
					int dayHeight = currentMonth.data.getdAvl().heighte(currentDay);
					hd.setText(dayHeight + "");
					rhd.setText(yAvl.root.data.getmAvl().root.data.getdAvl().root + ","
							+ yAvl.root.data.getmAvl().root.data.getdAvl().root.Height);
					return currentDay.data.getE();
				}
			}
		}
		return null;
	}

	public void ReadFile(Stage s) {
		FileChooser f = new FileChooser();
		f.setTitle("selected file");
		File selectedFile = f.showOpenDialog(s);

		try {

			Scanner in = new Scanner(selectedFile);
			while (in.hasNext()) {

				String[] str = in.nextLine().split(",");
				String date = str[0].trim();

				String[] dateParts = date.split("/");
				if (dateParts.length == 3) {
					int day = Integer.parseInt(dateParts[1].trim());
					int month = Integer.parseInt(dateParts[0].trim());
					int year = Integer.parseInt(dateParts[2].trim());

					Yeary r = new Yeary(year);
					Monthm m1 = new Monthm(month);
					String mm = m1.getMonthName(month);
					Monthm m = new Monthm(mm);
					DateTimeFormatter date1 = DateTimeFormatter.ofPattern("M/d/yyyy");
					LocalDate d = LocalDate.parse(date, date1);
					Electricity e = new Electricity(d, Double.parseDouble(str[1]), Double.parseDouble(str[2]),
							Double.parseDouble(str[3]), Double.parseDouble(str[4]), Double.parseDouble(str[5]),
							Double.parseDouble(str[6]), Double.parseDouble(str[7]));
					Day d1 = new Day(day, e);
					try {
						if (yAvl.find(r) != null) {
							if (yAvl.root.data.getmAvl().find(m) != null) {
								yAvl.root.data.getmAvl().root.data.getdAvl().insert(d1);
							} else {
								yAvl.root.data.getmAvl().insert(m);
								yAvl.root.data.getmAvl().root.data.getdAvl().insert(d1);
							}

						} else {
							yAvl.insert(r);
							if (yAvl.root.data.getmAvl().find(m) != null) {
								yAvl.root.data.getmAvl().root.data.getdAvl().insert(d1);
							} else {
								yAvl.root.data.getmAvl().insert(m);
								yAvl.root.data.getmAvl().root.data.getdAvl().insert(d1);
							}

						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}

			}

			yAvl.traverseInOrder();
			yAvl.root.data.getmAvl().traverseInOrder();
			yAvl.root.data.getmAvl().root.data.getdAvl().traverseInOrder();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public void start(Stage s) throws Exception {
		Button file = new Button("File chooser");
		Button print = new Button("Print Tree");
		print.setStyle("-fx-color:AZURE");
		text = new TextArea();
		hy = new TextField();
		hy.setPromptText("Height Year");
		hm = new TextField();
		hm.setPromptText("Height Month");
		hd = new TextField();
		hd.setPromptText("Height Day");
		text = new TextArea();
		rhy = new TextField();
		rhy.setPromptText("root Year,Height");
		rhm = new TextField();
		rhm.setPromptText("root Month,Height");
		rhd = new TextField();
		rhd.setPromptText("root Day,Height");
		file.setStyle("-fx-color:AZURE");
		Button add = new Button("Insert ");
		add.setStyle("-fx-color:AQUA");
		Button delete = new Button("Delete");
		delete.setStyle("-fx-color:AQUA");
		Button update = new Button("Update");
		update.setStyle("-fx-color:AQUA");
		Button search = new Button("Search");
		search.setStyle("-fx-color:AQUA");
		Label l1 = new Label("IsraeliLines");
		l1.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		Label l2 = new Label("GazaPowerPlant");
		l2.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		Label l3 = new Label("EgyptianLines");
		l3.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		Label l4 = new Label("TotaldailySupplyavailablein");
		l4.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		Label l5 = new Label("Overalldemand");
		l5.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		Label l6 = new Label("PowerCutshoursday");
		l6.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		Label l7 = new Label("Temp");
		l7.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		TextField t4 = new TextField();
		TextField t5 = new TextField();
		TextField t6 = new TextField();
		TextField t7 = new TextField();
		HBox hh1 = new HBox(10);
		hh1.getChildren().addAll(l1, t1);
		HBox hh2 = new HBox(10);
		hh2.getChildren().addAll(l2, t2);
		HBox hh3 = new HBox(10);
		hh3.getChildren().addAll(l3, t3);
		HBox hh4 = new HBox(10);
		hh4.getChildren().addAll(l4, t4);
		HBox hh5 = new HBox(10);
		hh5.getChildren().addAll(l5, t5);
		HBox hh6 = new HBox(10);
		hh6.getChildren().addAll(l6, t6);
		HBox hh7 = new HBox(10);
		hh7.getChildren().addAll(l7, t7);
		VBox vv = new VBox(10);
		vv.getChildren().addAll(hh1, hh2, hh3, hh4, hh5, hh6, hh7);
		vv.setAlignment(Pos.CENTER);
		TilePane r = new TilePane();
		d = new DatePicker();
		r.getChildren().add(d);
		r.setAlignment(Pos.CENTER);
		VBox vvv = new VBox(10);
		vvv.getChildren().addAll(d, vv);
		vvv.setAlignment(Pos.CENTER);
		HBox b = new HBox(10);
		b.getChildren().addAll(add, delete, update, search, print);

		b.setAlignment(Pos.BOTTOM_CENTER);
		HBox h = new HBox(10);
		h.getChildren().add(file);
		h.setAlignment(Pos.CENTER);
		HBox hh = new HBox();
		hh.getChildren().addAll(hy, hm, hd);
		hh.setAlignment(Pos.CENTER);
		HBox hh01 = new HBox();
		hh01.getChildren().addAll(rhy, rhm, rhd);
		hh01.setAlignment(Pos.CENTER);
		VBox bb = new VBox(10);
		bb.getChildren().addAll(vvv, b, h, text, hh, hh01);

		// Read file
		file.setOnAction(e -> {
			ReadFile(s);
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("INFORMATION");
			a.setContentText("The file read successfully");
			a.showAndWait();
		});
		// print trees level by level
		print.setOnAction(e -> {
			LocalDate date = d.getValue();
			PrintTrees(date);
		});

		// Search record by date
		search.setOnAction(e -> {
			t1.clear();
			t2.clear();
			t3.clear();
			t4.clear();
			t5.clear();
			t6.clear();
			t7.clear();

			LocalDate date = d.getValue();
			Electricity E = Search(date);

			if (E != null) {
				t1.setText(E.getIsraeli_Lines() + "\n");
				t2.setText(E.getGaza_Power_Plant() + "\n");
				t3.setText(E.getEgyptian_Lines() + "\n");
				t4.setText(E.getTotal_daily_Supply_available_in() + "\n");
				t5.setText(E.getOverall_demand() + "\n");
				t6.setText(E.getPower_Cuts_hours_day() + "\n");
				t7.setText(E.getTemp() + "\n");

			} else {
				hy.clear();
				hm.clear();
				hd.clear();
				rhy.clear();
				rhm.clear();
				rhd.clear();
				Alert a = new Alert(AlertType.WARNING);
				a.setTitle("WARNING");
				a.setContentText("Record not found");
				a.showAndWait();
			}
		});

		// insert electricity record
		add.setOnAction(e -> {
			if (d.getValue() != null) {
				int year = d.getValue().getYear();
				int month = d.getValue().getMonthValue();
				int day = d.getValue().getDayOfMonth();

				TreeNode<Yeary> currentYear = yAvl.find(new Yeary(year));

				if (currentYear != null) {
					Monthm m1 = new Monthm(month);
					String mm = m1.getMonthName(month);
					Monthm m = new Monthm(mm);
					TreeNode<Monthm> currentMonth = currentYear.data.getmAvl().find(m);
					if (currentMonth != null) {
						TreeNode<Day> currentDay = currentMonth.data.getdAvl().find(new Day(day, null));

						if (currentDay != null) {
							Electricity e1 = new Electricity(d.getValue(), Double.parseDouble(t1.getText()),
									Double.parseDouble(t2.getText()), Double.parseDouble(t3.getText()),
									Double.parseDouble(t4.getText()), Double.parseDouble(t5.getText()),
									Double.parseDouble(t6.getText()), Double.parseDouble(t7.getText()));
							currentDay.data.setE(e1);
							Alert a = new Alert(AlertType.INFORMATION);
							a.setTitle("INFORMATION");
							a.setContentText("Record updated successfully");
							a.showAndWait();
						} else {
							Electricity e1 = new Electricity(d.getValue(), Double.parseDouble(t1.getText()),
									Double.parseDouble(t2.getText()), Double.parseDouble(t3.getText()),
									Double.parseDouble(t4.getText()), Double.parseDouble(t5.getText()),
									Double.parseDouble(t6.getText()), Double.parseDouble(t7.getText()));
							Day d1 = new Day(day, e1);
							currentMonth.data.getdAvl().insert(d1);
							yAvl.root.data.getmAvl().root.data.getdAvl().traverseInOrder();
							Alert a = new Alert(AlertType.INFORMATION);
							a.setTitle("INFORMATION");
							a.setContentText("add the record successfully");
							a.showAndWait();
						}
					} else {
						Monthm mon = new Monthm(month);
						String monm = mon.getMonthName(month);
						Monthm mS = new Monthm(monm);
						currentYear.data.getmAvl().insert(mS);

						Electricity e1 = new Electricity(d.getValue(), Double.parseDouble(t1.getText()),
								Double.parseDouble(t2.getText()), Double.parseDouble(t3.getText()),
								Double.parseDouble(t4.getText()), Double.parseDouble(t5.getText()),
								Double.parseDouble(t6.getText()), Double.parseDouble(t7.getText()));
						Day d1 = new Day(day, e1);
						currentYear.data.getmAvl().find(mS).data.getdAvl().insert(d1);
						yAvl.root.data.getmAvl().root.data.getdAvl().traverseInOrder();
						Alert a = new Alert(AlertType.INFORMATION);
						a.setTitle("INFORMATION");
						a.setContentText("add the record successfully");
						a.showAndWait();
					}
				} else {

					Yeary y = new Yeary(year);
					yAvl.insert(y);

					Monthm mon = new Monthm(month);
					String monm = mon.getMonthName(month);
					Monthm mS = new Monthm(monm);
					yAvl.find(y).data.getmAvl().insert(mS);
					Electricity e1 = new Electricity(d.getValue(), Double.parseDouble(t1.getText()),
							Double.parseDouble(t2.getText()), Double.parseDouble(t3.getText()),
							Double.parseDouble(t4.getText()), Double.parseDouble(t5.getText()),
							Double.parseDouble(t6.getText()), Double.parseDouble(t7.getText()));
					Day d1 = new Day(day, e1);
					yAvl.find(y).data.getmAvl().find(mS).data.getdAvl().insert(d1);
					yAvl.traverseInOrder();
					yAvl.root.data.getmAvl().root.data.getdAvl().traverseInOrder();
					Alert a = new Alert(AlertType.INFORMATION);
					a.setTitle("INFORMATION");
					a.setContentText("add the record successfully");
					a.showAndWait();
				}
			}
		});
		// remove electricity record
		delete.setOnAction(e -> {
			if (d.getValue() != null) {
				int year = d.getValue().getYear();
				int month = d.getValue().getMonthValue();
				int day = d.getValue().getDayOfMonth();

				TreeNode<Yeary> currentYear = yAvl.find(new Yeary(year));

				if (currentYear != null) {
					Monthm mon = new Monthm(month);
					String monm = mon.getMonthName(month);
					Monthm mS = new Monthm(monm);
					TreeNode<Monthm> currentMonth = currentYear.data.getmAvl().find(mS);

					if (currentMonth != null) {
						TreeNode<Day> currentDay = currentMonth.data.getdAvl().find(new Day(day, null));

						if (currentDay != null) {

							currentMonth.data.getdAvl().delete(currentDay.data);
							currentMonth.data.getdAvl().traverseInOrder();
							t1.clear();
							t2.clear();
							t3.clear();
							t4.clear();
							t5.clear();
							t6.clear();
							t7.clear();

							Alert a = new Alert(AlertType.INFORMATION);
							a.setTitle("INFORMATION");
							a.setContentText("Remove the record successfully");
							a.showAndWait();
						} else {

							Alert a = new Alert(AlertType.WARNING);
							a.setTitle("WARNING");
							a.setContentText("Day not found");
							a.showAndWait();
						}
					} else {

						Alert a = new Alert(AlertType.WARNING);
						a.setTitle("WARNING");
						a.setContentText("Month not found");
						a.showAndWait();
					}
				} else {

					Alert a = new Alert(AlertType.WARNING);
					a.setTitle("WARNING");
					a.setContentText("Year not found");
					a.showAndWait();
				}
			}
		});
		// update electricity record
		update.setOnAction(e -> {
			if (d.getValue() != null) {
				int year = d.getValue().getYear();
				int month = d.getValue().getMonthValue();
				int day = d.getValue().getDayOfMonth();

				TreeNode<Yeary> currentYear = yAvl.find(new Yeary(year));

				if (currentYear != null) {
					Monthm mon = new Monthm(month);
					String monm = mon.getMonthName(month);
					Monthm mS = new Monthm(monm);
					TreeNode<Monthm> currentMonth = currentYear.data.getmAvl().find(mS);

					if (currentMonth != null) {
						TreeNode<Day> currentDay = currentMonth.data.getdAvl().find(new Day(day, null));

						if (currentDay != null) {

							currentDay.data.getE().setDate(d.getValue());
							currentDay.data.getE().setIsraeli_Lines(Double.parseDouble(t1.getText()));
							currentDay.data.getE().setGaza_Power_Plant(Double.parseDouble(t2.getText()));
							currentDay.data.getE().setEgyptian_Lines(Double.parseDouble(t3.getText()));
							currentDay.data.getE().setTotal_daily_Supply_available_in(Double.parseDouble(t4.getText()));
							currentDay.data.getE().setOverall_demand(Double.parseDouble(t5.getText()));
							currentDay.data.getE().setPower_Cuts_hours_day(Double.parseDouble(t6.getText()));
							currentDay.data.getE().setTemp(Double.parseDouble(t7.getText()));

							currentMonth.data.getdAvl().traverseInOrder();

							Alert a = new Alert(AlertType.INFORMATION);
							a.setTitle("INFORMATION");
							a.setContentText("Update the record successfully");
							a.showAndWait();
						} else {

							Alert a = new Alert(AlertType.WARNING);
							a.setTitle("WARNING");
							a.setContentText("Day not found");
							a.showAndWait();
						}
					} else {

						Alert a = new Alert(AlertType.WARNING);
						a.setTitle("WARNING");
						a.setContentText("Month not found");
						a.showAndWait();
					}
				} else {

					Alert a = new Alert(AlertType.WARNING);
					a.setTitle("WARNING");
					a.setContentText("Year not found");
					a.showAndWait();
				}
			}
		});
//------------------------------------------------Statistics screen---------------------------------------------------------------
		ToggleGroup toggleGroup = new ToggleGroup();

		RadioButton radioButton1 = new RadioButton("Year");
		radioButton1.setToggleGroup(toggleGroup);

		RadioButton radioButton2 = new RadioButton("Month");
		radioButton2.setToggleGroup(toggleGroup);

		RadioButton radioButton3 = new RadioButton("Day");
		radioButton3.setToggleGroup(toggleGroup);

		RadioButton radioButton4 = new RadioButton("All Data");
		radioButton4.setToggleGroup(toggleGroup);

		radioButton1.setSelected(true);
		HBox hbox = new HBox(10);
		hbox.getChildren().addAll(radioButton1, radioButton2, radioButton3, radioButton4);
		hbox.setAlignment(Pos.CENTER);
		ComboBox<Integer> comboBox1 = new ComboBox<>();
		ComboBox<Integer> comboBox2 = new ComboBox<>();
		ComboBox<Integer> comboBox3 = new ComboBox<>();
		ComboBox<String> comboBox4 = new ComboBox<>();

		comboBox1.getItems().addAll(2017, 2018, 2019, 2020, 2021, 2022, 2023);
		comboBox2.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
		comboBox3.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
				24, 25, 26, 27, 28, 29, 30, 31);
		comboBox4.getItems().addAll("IsraeliLines", "GazaPowerPlant", "EgyptianLines", "TotaldailySupplyavailablein",
				"Overalldemand", "PowerCutshoursday", "Temp");

		Label ll1 = new Label("Year");
		ll1.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		Label ll2 = new Label("Month");
		ll2.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		Label ll3 = new Label("Day");
		ll3.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		Label ll4 = new Label("Column");
		ll4.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		VBox vb1 = new VBox(10);
		vb1.getChildren().addAll(ll1, comboBox1);
		vb1.setAlignment(Pos.CENTER);
		VBox vb2 = new VBox(10);
		vb2.getChildren().addAll(ll2, comboBox2);
		vb2.setAlignment(Pos.CENTER);
		VBox vb3 = new VBox(10);
		vb3.getChildren().addAll(ll3, comboBox3);
		vb3.setAlignment(Pos.CENTER);
		VBox vb4 = new VBox(10);
		vb4.getChildren().addAll(ll4, comboBox4);
		vb4.setAlignment(Pos.CENTER);
		Button bbb = new Button("Compute");
		bbb.setStyle("-fx-color:blue");

		Label tl = new Label("Total(sum)");
		tl.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		Label al = new Label("Average");
		al.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		Label minl = new Label("Min");
		minl.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		Label maxl = new Label("Max");
		maxl.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		TextField tt = new TextField();
		TextField at = new TextField();
		TextField mint = new TextField();
		TextField maxt = new TextField();
		VBox tv = new VBox(10);
		tv.getChildren().addAll(tl, tt);
		VBox av = new VBox(10);
		av.getChildren().addAll(al, at);
		VBox minv = new VBox(10);
		minv.getChildren().addAll(minl, mint);
		VBox maxv = new VBox(10);
		maxv.getChildren().addAll(maxl, maxt);
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.add(tv, 0, 0);
		pane.add(av, 1, 0);
		pane.add(minv, 0, 1);
		pane.add(maxv, 1, 1);
		pane.setAlignment(Pos.CENTER);
		VBox vx = new VBox(10);
		vx.getChildren().addAll(hbox, vb1, vb2, vb3, vb4, bbb, pane);
		vx.setAlignment(Pos.CENTER);
		vb1.setVisible(true);
		vb2.setVisible(false);
		vb3.setVisible(false);
		vb4.setVisible(true);
		bbb.setVisible(true);
		// A specific electricity statistic for a given year
		radioButton1.setOnAction(e -> {
			vb1.setVisible(true);
			vb2.setVisible(false);
			vb3.setVisible(false);
			vb4.setVisible(true);
			bbb.setVisible(true);
			bbb.setOnAction(e2 -> {
				tt.clear();
				at.clear();
				maxt.clear();
				mint.clear();
				double sum = 0;
				double avg = 0;
				double max = 0;
				double count = 0;
				double min = Double.MAX_VALUE;
				int selectedYear = comboBox1.getSelectionModel().getSelectedItem();
				TreeNode<Yeary> current = yAvl.root;
				for (int i = 0; i < yAvl.height(); i++) {
					Yeary year = yAvl.get(i);
					if (selectedYear == year.getYear()) {
						for (int j = 0; j < year.getmAvl().height(); j++) {
							Monthm month = year.getmAvl().get(j);
							for (int k = 0; k < month.getdAvl().height(); k++) {
								Day day = month.getdAvl().get(k);
								Electricity record = day.getE();
								if (comboBox4.selectionModelProperty().getValue().getSelectedItem() == "IsraeliLines") {
									sum += record.getIsraeli_Lines();

									avg = sum / count;

									if (record.getIsraeli_Lines() > max) {
										max = record.getIsraeli_Lines();

									}
									min = Math.min(min, record.getIsraeli_Lines());

								} else if (comboBox4.selectionModelProperty().getValue()
										.getSelectedItem() == "GazaPowerPlant") {
									sum += record.getGaza_Power_Plant();

									avg = sum / count;

									if (record.getGaza_Power_Plant() > max) {
										max = record.getGaza_Power_Plant();

									}
									min = Math.min(min, record.getGaza_Power_Plant());

								} else if (comboBox4.selectionModelProperty().getValue()
										.getSelectedItem() == "EgyptianLines") {
									sum += record.getEgyptian_Lines();

									avg = sum / count;

									if (record.getEgyptian_Lines() > max) {
										max = record.getEgyptian_Lines();

									}
									min = Math.min(min, record.getEgyptian_Lines());

								} else if (comboBox4.selectionModelProperty().getValue()
										.getSelectedItem() == "TotaldailySupplyavailablein") {
									sum += record.getTotal_daily_Supply_available_in();

									avg = sum / count;

									if (record.getTotal_daily_Supply_available_in() > max) {
										max = record.getTotal_daily_Supply_available_in();

									}
									min = Math.min(min, record.getTotal_daily_Supply_available_in());

								} else if (comboBox4.selectionModelProperty().getValue()
										.getSelectedItem() == "Overalldemand") {
									sum += record.getOverall_demand();

									avg = sum / count;

									if (record.getOverall_demand() > max) {
										max = record.getOverall_demand();

									}
									min = Math.min(min, record.getOverall_demand());

								} else if (comboBox4.selectionModelProperty().getValue()
										.getSelectedItem() == "PowerCutshoursday") {
									sum += record.getPower_Cuts_hours_day();
									avg = sum / count;
									if (record.getPower_Cuts_hours_day() > max) {
										max = record.getPower_Cuts_hours_day();

									}
									min = Math.min(min, record.getPower_Cuts_hours_day());

								} else if (comboBox4.selectionModelProperty().getValue().getSelectedItem() == "Temp") {
									sum += record.getTemp();

									avg = sum / count;

									if (record.getTemp() > max) {
										max = record.getTemp();

									}
									min = Math.min(min, record.getTemp());

								}
								count++;

							}

						}

					}
				}
				tt.setText(sum + "\n");
				at.setText(avg + "\n");
				maxt.setText(max + "\n");
				mint.setText(min + "\n");

			});

		});
		// A specific electricity statistic for a given month
		radioButton2.setOnAction(e -> {
			tt.clear();
			at.clear();
			maxt.clear();
			mint.clear();
			vb1.setVisible(false);
			vb2.setVisible(true);
			vb3.setVisible(false);
			vb4.setVisible(true);
			bbb.setVisible(true);
			bbb.setOnAction(e1 -> {
				double sum = 0;
				double avg = 0;
				double max = 0;
				double min = Double.MAX_VALUE;
				int selectedMonth = comboBox2.getSelectionModel().getSelectedItem();
				TreeNode<Yeary> current = yAvl.root;
				for (int i = 0; i < yAvl.height(); i++) {
					Yeary year = yAvl.get(i);
					if (selectedMonth <= year.getmAvl().height()) {
						Monthm month = year.getmAvl().get(selectedMonth - 1);
						for (int j = 0; j < month.getdAvl().height(); j++) {
							Day day = month.getdAvl().get(j);
							Electricity record = day.getE();
							if (comboBox4.selectionModelProperty().getValue().getSelectedItem() == "IsraeliLines") {
								sum += record.getIsraeli_Lines();

								avg = sum / month.getdAvl().height();

								if (record.getIsraeli_Lines() > max) {
									max = record.getIsraeli_Lines();

								}
								min = Math.min(min, record.getIsraeli_Lines());

							} else if (comboBox4.selectionModelProperty().getValue()
									.getSelectedItem() == "GazaPowerPlant") {
								sum += record.getGaza_Power_Plant();

								avg = sum / month.getdAvl().height();

								if (record.getGaza_Power_Plant() > max) {
									max = record.getGaza_Power_Plant();

								}
								min = Math.min(min, record.getGaza_Power_Plant());

							} else if (comboBox4.selectionModelProperty().getValue()
									.getSelectedItem() == "EgyptianLines") {
								sum += record.getEgyptian_Lines();

								avg = sum / month.getdAvl().height();

								if (record.getEgyptian_Lines() > max) {
									max = record.getEgyptian_Lines();

								}
								min = Math.min(min, record.getEgyptian_Lines());

							} else if (comboBox4.selectionModelProperty().getValue()
									.getSelectedItem() == "TotaldailySupplyavailablein") {
								sum += record.getTotal_daily_Supply_available_in();

								avg = sum / month.getdAvl().height();

								if (record.getTotal_daily_Supply_available_in() > max) {
									max = record.getTotal_daily_Supply_available_in();

								}
								min = Math.min(min, record.getTotal_daily_Supply_available_in());

							} else if (comboBox4.selectionModelProperty().getValue()
									.getSelectedItem() == "Overalldemand") {
								sum += record.getOverall_demand();

								avg = sum / month.getdAvl().height();

								if (record.getOverall_demand() > max) {
									max = record.getOverall_demand();

								}
								min = Math.min(min, record.getOverall_demand());

							} else if (comboBox4.selectionModelProperty().getValue()
									.getSelectedItem() == "PowerCutshoursday") {
								sum += record.getPower_Cuts_hours_day();

								avg = sum / month.getdAvl().height();

								if (record.getPower_Cuts_hours_day() > max) {
									max = record.getPower_Cuts_hours_day();

								}
								min = Math.min(min, record.getPower_Cuts_hours_day());

							} else if (comboBox4.selectionModelProperty().getValue().getSelectedItem() == "Temp") {
								sum += record.getTemp();

								avg = sum / month.getdAvl().height();

								if (record.getTemp() > max) {
									max = record.getTemp();

								}
								min = Math.min(min, record.getTemp());

							}

						}

					}
				}
				tt.setText(sum + "\n");
				at.setText(avg + "\n");
				maxt.setText(max + "\n");
				mint.setText(min + "\n");
			});

		});
		// A specific electricity statistic for a given day
		radioButton3.setOnAction(e -> {
			vb1.setVisible(false);
			vb2.setVisible(false);
			vb3.setVisible(true);
			vb4.setVisible(true);
			bbb.setVisible(true);
			bbb.setOnAction(e2 -> {
				tt.clear();
				at.clear();
				maxt.clear();
				mint.clear();
				double sum = 0;
				double avg = 0;
				double max = 0;
				double min = Double.MAX_VALUE;
				int selectedDay = comboBox3.getSelectionModel().getSelectedItem();
				TreeNode<Yeary> current = yAvl.root;
				for (int i = 0; i < yAvl.height(); i++) {
					Yeary year = yAvl.get(i);
					for (int j = 0; j < year.getmAvl().height(); j++) {
						Monthm month = year.getmAvl().get(i);
						if (selectedDay <= month.getdAvl().height()) {
							Day day = month.getdAvl().get(selectedDay - 1);
							Electricity record = day.getE();
							if (comboBox4.selectionModelProperty().getValue().getSelectedItem() == "IsraeliLines") {
								sum += record.getIsraeli_Lines();

								avg = sum / month.getdAvl().height();

								if (record.getIsraeli_Lines() > max) {
									max = record.getIsraeli_Lines();

								}
								min = Math.min(min, record.getIsraeli_Lines());

							} else if (comboBox4.selectionModelProperty().getValue()
									.getSelectedItem() == "GazaPowerPlant") {
								sum += record.getGaza_Power_Plant();

								avg = sum / month.getdAvl().height();

								if (record.getGaza_Power_Plant() > max) {
									max = record.getGaza_Power_Plant();

								}
								min = Math.min(min, record.getGaza_Power_Plant());

							} else if (comboBox4.selectionModelProperty().getValue()
									.getSelectedItem() == "EgyptianLines") {
								sum += record.getEgyptian_Lines();

								avg = sum / month.getdAvl().height();

								if (record.getEgyptian_Lines() > max) {
									max = record.getEgyptian_Lines();

								}
								min = Math.min(min, record.getEgyptian_Lines());

							} else if (comboBox4.selectionModelProperty().getValue()
									.getSelectedItem() == "TotaldailySupplyavailablein") {
								sum += record.getTotal_daily_Supply_available_in();

								avg = sum / month.getdAvl().height();

								if (record.getTotal_daily_Supply_available_in() > max) {
									max = record.getTotal_daily_Supply_available_in();

								}
								min = Math.min(min, record.getTotal_daily_Supply_available_in());

							} else if (comboBox4.selectionModelProperty().getValue()
									.getSelectedItem() == "Overalldemand") {
								sum += record.getOverall_demand();

								avg = sum / month.getdAvl().height();

								if (record.getOverall_demand() > max) {
									max = record.getOverall_demand();

								}
								min = Math.min(min, record.getOverall_demand());

							} else if (comboBox4.selectionModelProperty().getValue()
									.getSelectedItem() == "PowerCutshoursday") {
								sum += record.getPower_Cuts_hours_day();

								avg = sum / month.getdAvl().height();

								if (record.getPower_Cuts_hours_day() > max) {
									max = record.getPower_Cuts_hours_day();

								}
								min = Math.min(min, record.getPower_Cuts_hours_day());

							} else if (comboBox4.selectionModelProperty().getValue().getSelectedItem() == "Temp") {
								sum += record.getTemp();

								avg = sum / month.getdAvl().height();

								if (record.getTemp() > max) {
									max = record.getTemp();

								}
								min = Math.min(min, record.getTemp());

							}
						}
					}
				}
				tt.setText(sum + "\n");
				at.setText(avg + "\n");
				maxt.setText(max + "\n");
				mint.setText(min + "\n");

			});

		});
		// A total statistic for all data
		radioButton4.setOnAction(e -> {
			tt.clear();
			at.clear();
			maxt.clear();
			mint.clear();
			vb1.setVisible(false);
			vb2.setVisible(false);
			vb3.setVisible(false);
			vb4.setVisible(true);
			bbb.setVisible(true);
			bbb.setOnAction(e2 -> {
				double sum = 0;
				double avg = 0;
				double max = 0;
				double min = Double.MAX_VALUE;
				TreeNode<Yeary> current = yAvl.root;

				for (int i = 0; i < yAvl.height(); i++) {
					Yeary year = yAvl.get(i);
					for (int j = 0; j < year.getmAvl().height(); j++) {
						Monthm month = year.getmAvl().get(j);
						for (int k = 0; k < month.getdAvl().height(); k++) {
							Day day = month.getdAvl().get(k);
							if (day != null) {
								Electricity record = day.getE();
								if (record != null) {
									if (comboBox4.selectionModelProperty().getValue()
											.getSelectedItem() == "IsraeliLines") {
										sum += record.getIsraeli_Lines();

										avg = sum / month.getdAvl().height();

										if (record.getIsraeli_Lines() > max) {
											max = record.getIsraeli_Lines();

										}
										min = Math.min(min, record.getIsraeli_Lines());

									} else if (comboBox4.selectionModelProperty().getValue()
											.getSelectedItem() == "GazaPowerPlant") {
										sum += record.getGaza_Power_Plant();

										avg = sum / month.getdAvl().height();

										if (record.getGaza_Power_Plant() > max) {
											max = record.getGaza_Power_Plant();

										}
										min = Math.min(min, record.getGaza_Power_Plant());

									} else if (comboBox4.selectionModelProperty().getValue()
											.getSelectedItem() == "EgyptianLines") {
										sum += record.getEgyptian_Lines();

										avg = sum / month.getdAvl().height();

										if (record.getEgyptian_Lines() > max) {
											max = record.getEgyptian_Lines();

										}
										min = Math.min(min, record.getEgyptian_Lines());

									} else if (comboBox4.selectionModelProperty().getValue()
											.getSelectedItem() == "TotaldailySupplyavailablein") {
										sum += record.getTotal_daily_Supply_available_in();

										avg = sum / month.getdAvl().height();

										if (record.getTotal_daily_Supply_available_in() > max) {
											max = record.getTotal_daily_Supply_available_in();

										}
										min = Math.min(min, record.getTotal_daily_Supply_available_in());

									} else if (comboBox4.selectionModelProperty().getValue()
											.getSelectedItem() == "Overalldemand") {
										sum += record.getOverall_demand();

										avg = sum / month.getdAvl().height();

										if (record.getOverall_demand() > max) {
											max = record.getOverall_demand();

										}
										min = Math.min(min, record.getOverall_demand());

									} else if (comboBox4.selectionModelProperty().getValue()
											.getSelectedItem() == "PowerCutshoursday") {
										sum += record.getPower_Cuts_hours_day();

										avg = sum / month.getdAvl().height();

										if (record.getPower_Cuts_hours_day() > max) {
											max = record.getPower_Cuts_hours_day();

										}
										min = Math.min(min, record.getPower_Cuts_hours_day());

									} else if (comboBox4.selectionModelProperty().getValue()
											.getSelectedItem() == "Temp") {
										sum += record.getTemp();

										avg = sum / month.getdAvl().height();

										if (record.getTemp() > max) {
											max = record.getTemp();

										}
										min = Math.min(min, record.getTemp());

									}
								}
							}
						}
					}
				}
				tt.setText(sum + "\n");
				at.setText(avg + "\n");
				maxt.setText(max + "\n");
				mint.setText(min + "\n");

			});

		});

//--------------------------------------------------Save screen ------------------------------------------------------------------
		Label lfile = new Label("File Name");
		lfile.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		TextField f = new TextField();
		Button bfile = new Button("Save");
		bfile.setStyle("-fx-color:red");
		HBox hb = new HBox(10);
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(lfile, f, bfile);
		// save data in file
		bfile.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save File");
			fileChooser.setInitialFileName(f.getText());
			File selectedFile = fileChooser.showSaveDialog(s);

			if (selectedFile == null) {
				return;
			}

			try (FileOutputStream fos = new FileOutputStream(selectedFile);
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos))) {
				TreeNode<Yeary> currentYear = yAvl.root;
				while (currentYear != null) {
					TreeNode<Monthm> currentMonth = currentYear.data.getmAvl().root;
					while (currentMonth != null) {
						TreeNode<Day> currentDay = currentMonth.data.getdAvl().root;
						while (currentDay != null) {
							try {
								bw.write(currentDay.data.getE().toString());
								bw.newLine();
								bw.flush();
								currentDay = currentDay.right;
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						currentMonth = currentMonth.right;
					}
					currentYear = currentYear.right;
				}

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("INFORMATION");
			a.setContentText(" Save the file successfully ");
			a.showAndWait();
		});

		// ---------------------------------------------------------------------------------------------------------------------------------
		TabPane tb = new TabPane();
		Tab ta1 = new Tab("Mangement");
		Tab ta2 = new Tab("Statistics");
		Tab ta3 = new Tab("Save");
		tb.getTabs().add(ta1);
		tb.getTabs().add(ta2);
		tb.getTabs().add(ta3);
		ta1.setContent(bb);
		ta2.setContent(vx);
		ta3.setContent(hb);
		VBox v = new VBox(10);
		v.getChildren().add(tb);
		v.setStyle("-fx-background-color:gray");
		Scene sc = new Scene(v, 700, 700);
		s.setScene(sc);
		s.show();

	}

}