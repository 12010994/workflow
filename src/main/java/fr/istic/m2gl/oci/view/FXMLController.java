package fr.istic.m2gl.oci.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

public class FXMLController implements Initializable {

	@FXML private ListView<Pane> list;
	@FXML private Pane content;
	@FXML private Pane actions;

	private ObservableList<Pane> elems = FXCollections.observableArrayList ();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		addRow("Facture", 1, "started");
		addRow("Doc1", 2, "pending");
		addRow("Tache1", 3, "done");
		list.setItems(elems);
	}
	
	public void addRow(String name, int id, String status){
		Pane row = new Pane();

		Text text = new Text(Integer.toString(id));
		text.setX(0.0);
		text.setY(15.0);
		row.getChildren().add(text);

		text = new Text(name);
		text.setX(100.0);
		text.setY(15.0);
		row.getChildren().add(text);

		text = new Text(status);
		text.setX(400.0);
		text.setY(15.0);
		row.getChildren().add(text);

		elems.add(row);
	}
}
