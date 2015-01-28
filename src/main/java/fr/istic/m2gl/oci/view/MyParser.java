package fr.istic.m2gl.oci.view;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;
import org.xml.sax.SAXException;

public class MyParser {

	private Document document; 

	public MyParser(){

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder loader;
		try {
			loader = factory.newDocumentBuilder();
			document = loader.parse("src/main/resources/fxml/skeleton.fxml");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	public void parser(){

		NodeList nodeList = document.getElementsByTagName("*");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if(((Element) node).getAttribute("fx:id").equals("actions")){
					node = nodeList.item(i+1);
					addButton((Element)node, 62.0, 0.0, "button2");
				}
			}
		}
		printXML();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File("src/main/resources/fxml/fxml.fxml"));
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e ) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
	}

	private void addButton(Element noeud, double x, double y, String name){
		Element button = document.createElement("Button");

		Attr attributId = document.createAttribute("fx:id");
		attributId.setValue(name);
		button.setAttributeNode(attributId);

		Attr attributLayoutX = document.createAttribute("layoutX");
		attributLayoutX.setValue(Double.toString(x));
		button.setAttributeNode(attributLayoutX);

		Attr attributLayoutY = document.createAttribute("layoutY");
		attributLayoutY.setValue(Double.toString(y));
		button.setAttributeNode(attributLayoutY);

		Attr attributName = document.createAttribute("text");
		attributName.setValue(name);
		button.setAttributeNode(attributName);

		noeud.appendChild(button);
	}

	public void printXML(){
		DocumentTraversal traversal = (DocumentTraversal) document;

		TreeWalker walker = traversal.createTreeWalker(
				document.getDocumentElement(), NodeFilter.SHOW_ALL, null, true);
		walker.getRoot();
		traverseLevel(walker, "");
	}

	private void traverseLevel(TreeWalker walker, String indent) {
		Node noeud = walker.getCurrentNode();
		if (noeud instanceof Element) {
			System.out.println(indent + "- " + ((Element) noeud).getTagName()+" - fx:id="+((Element) noeud).getAttribute("fx:id"));
			for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
				traverseLevel(walker, indent + "  ");
			}
		}
		walker.setCurrentNode(noeud);
	}
}
