
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class D extends Application {

	private File selectedFile;
	private TextArea resultArea;
	private Equations E;
	private Stack<Sections>[] cursorStack;
	private Button nextb;
	private Button prevb;
	private int currentSections = 0;
	private String xmlString;
	private static List<String> startTags = new ArrayList();
	private static List<String> endTags = new ArrayList();

	public static void main(String[] args) throws IOException {
		Application.launch(args);

	}

//check tags are balanced or not 
	public static boolean isTagBalanced(String s) {
		Stack<String> tagStack = new Stack<>();
		String tag = "";
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '<') {
				tag += ch;
				while (i < s.length() && ch != '>') {
					ch = s.charAt(++i);
					tag += ch;
				}
				if (startTags.contains(tag)) {
					tagStack.push(tag);
				} else if (endTags.contains(tag)) {
					String poppedTag = tagStack.pop();
					if (!poppedTag.equals(tag.replace("/", ""))) {
						System.out.print(tag);
						return false;
					}
				} else {
					System.out.println(tag + " not found");
					return false;
				}
				tag = "";
			}
		}

		return tagStack.isEmpty();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		startTags.add("<242>");
		endTags.add("</242>");
		startTags.add("<section>");
		endTags.add("</section>");
		startTags.add("<infix>");
		endTags.add("</infix>");
		startTags.add("<equation>");
		endTags.add("</equation>");
		startTags.add("<postfix>");
		endTags.add("</postfix>");
//-----------------------------------------Screen equations section--------------------------------------------		
		Button loadb = new Button("Load");
		nextb = new Button("Next");
		prevb = new Button("Prev");
		loadb.setStyle("-fx-color:red");
		nextb.setStyle("-fx-color:Blue");
		prevb.setStyle("-fx-color:Blue");
		loadb.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		nextb.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		prevb.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));

		resultArea = new TextArea();
		resultArea.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		resultArea.setEditable(false);

		cursorStack = new Stack[2];
		cursorStack = Arrays.copyOf(cursorStack, cursorStack.length + 2);

		Label l1 = new Label("File:");
		l1.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 18));
		TextField tfile = new TextField();

		Label l2 = new Label("Equation Section");
		l2.setFont(Font.font("Times new Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));

		HBox B1 = new HBox(10);
		B1.getChildren().addAll(l1, tfile, loadb);
		B1.setAlignment(Pos.TOP_CENTER);

		HBox B2 = new HBox(10);
		B2.getChildren().addAll(prevb, nextb);
		B2.setAlignment(Pos.BOTTOM_CENTER);

		VBox V1 = new VBox(10);
		V1.setAlignment(Pos.CENTER);
		V1.getChildren().addAll(B1, l2, resultArea, B2);

		Scene s = new Scene(V1, 600, 650);
		arg0.setScene(s);
		arg0.setTitle("Equation Section");
		arg0.show();
		// load file
		loadb.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			selectedFile = fileChooser.showOpenDialog(null);

			if (selectedFile != null) {
				tfile.setText(selectedFile.getPath());
				processEquationsFile();

			}
		});
		// next section display
		nextb.setOnAction(e -> {
			try {

				if (currentSections < cursorStack.length - 1) {
					currentSections++;
					displayEquations(xmlString);
				} else {
					Alert a = new Alert(AlertType.WARNING);
					a.setTitle("WARNING");
					a.setContentText("No more next sections");
					a.showAndWait();
				}

			} catch (ArrayIndexOutOfBoundsException ee) {
				ee.getStackTrace();

			}

		});

		// prev section display
		prevb.setOnAction(e -> {
			try {
				if (currentSections > 0) {
					currentSections--;
					displayEquations(xmlString);

				} else {
					Alert a = new Alert(AlertType.WARNING);
					a.setTitle("WARNING");
					a.setContentText("No more prev sections");
					a.showAndWait();
				}
			} catch (ArrayIndexOutOfBoundsException ee) {
				ee.getStackTrace();
				prevb.setDisable(true);

			}

		});
	}

//check file is valid or Invalid file and display equations
	private void processEquationsFile() {
		try (Scanner scanner = new Scanner(selectedFile)) {
			StringBuilder xmls = new StringBuilder();

			while (scanner.hasNext()) {
				xmls.append(scanner.nextLine());
			}

			xmlString = xmls.toString();
			if (isTagBalanced(xmlString)) {
				Alert a = new Alert(AlertType.CONFIRMATION);
				a.setTitle("CONFIRMATION");
				a.setContentText("valid file. Tags are balanced");
				a.showAndWait();
				displayEquations(xmlString);

			} else {
				Alert a = new Alert(AlertType.WARNING);
				a.setTitle("WARNING");
				a.setContentText("Invalid file. Tags are not balanced");
				a.showAndWait();
				resultArea.clear();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void displayEquations(String xmlString) {
		StringBuilder resultText = new StringBuilder();
		Stack<Sections> sectionStack = new Stack<>();
		String[] sections = xmlString.split("</section>");
		for (int h = 0; h < sections.length; h++) {
			if (h == currentSections) {
				Sections currentSection = new Sections();
				sectionStack.push(currentSection);
				String[] infixs = sections[h].split("<postfix>");
				resultText.append("infix:").append("\n");
				String[] equation = infixs[0].split("<equation>");
				for (int i = 0; i < equation.length; i++) {
					if (equation[i].contains("</equation>")) {
						String infixString = equation[i].substring(0, equation[i].indexOf("</equation>")).trim();
						Infix infix = new Infix(infixString);
						currentSection.getInfix().push(infix);
						resultText.append(infix).append("\t==>\t");
						String postfix = E.infixToPostfix(infixString);
						resultText.append(postfix).append("\t==>\t");
						resultText.append(E.evaluatePostfix(postfix)).append("\n");
					}
				}

				String[] postfixs = infixs[1].split("</postfix>");
				resultText.append("postfix:").append("\n");
				String[] equations = postfixs[0].split("<equation>");
				for (int j = 0; j < equations.length; j++) {
					if (equations[j].contains("</equation>")) {
						String postfixString = equations[j].substring(0, equations[j].indexOf("</equation>")).trim();
						Postfix postfix = new Postfix(postfixString);
						currentSection.getPostfix().push(postfix);
						resultText.append(postfix).append("\t==>\t");
						String prefix = E.postfixToPrefix(postfixString);
						resultText.append(prefix).append("\t==>\t");
						resultText.append(E.evaluatePrefix(prefix)).append("\n");
					}
				}
			}

		}

		printResults(sectionStack);
		resultArea.setText(resultText.toString());
	}

	private void printResults(Stack<Sections> sectionStack) {

		while (!sectionStack.isEmpty()) {
			Sections section = sectionStack.pop();
			System.out.println("Section:");
			System.out.println("infix:");
			while (!section.infix.isEmpty()) {
				Infix infix = section.infix.pop();
				System.out.println(infix);
			}

			System.out.println("postfix:");
			while (!section.postfix.isEmpty()) {
				Postfix postfix = section.postfix.pop();
				System.out.println(postfix);
			}

		}
	}

	/*
	 * public Stack<Sections> processEquationsFile(File filePath) throws IOException
	 * { Stack<Sections> sectionStack = new Stack<>(); Sections section = new
	 * Sections(); Equation e1 = new Equation(); Infix infix = null;
	 * 
	 * Postfix postfix = null;
	 * 
	 * try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	 * String line; while ((line = reader.readLine()) != null) { line = line.trim();
	 * System.out.println(line); switch (line) { case "<section>": section = new
	 * Sections(); break; case "</section>": sectionStack.push(section); break;
	 * 
	 * case "<infix>":
	 * 
	 * infix = new Infix(); break; case "</infix>": if (infix != null) {
	 * section.infix.push(infix); } break;
	 * 
	 * case "<postfix>": postfix = new Postfix(); break; case "</postfix>": if
	 * (postfix != null) { section.postfix.push(postfix); } break; case
	 * "<equation>": e1 = new Equation(line); System.out.println(e1); break; case
	 * "</equation>": if (infix != null) { infix.e.push(e1); } else if (postfix !=
	 * null) { postfix.getE().push(e1); } break; default: if (infix != null) {
	 * infix.getE().push(e1); } else if (postfix != null) { postfix.getE().push(e1);
	 * } break; } } } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * return sectionStack; }
	 * 
	 * private void displayEquations(String xmlString) { try { Stack<Sections>
	 * sectionsStack = processEquationsFile(selectedFile);
	 * printResults(sectionsStack); } catch (IOException e) { e.printStackTrace(); }
	 * }
	 */

}
