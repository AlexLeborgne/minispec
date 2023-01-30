package XMLIO;

import metaModel.MinispecElement;
import metaModel.Model;
import metaModel.Package;
import metaModel.PrimitiveName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XMLAnalyserImport {

  protected Map<String, MinispecElement> minispec;
  // Map des elements XML
  protected Map<String, Element> xmlElement;

  public XMLAnalyserImport() {
    this.minispec = new HashMap<String, MinispecElement>();
    this.xmlElement = new HashMap<String, Element>();
  }

  protected MinispecElement minispecElementFromXmlElement(Element e) {
    String name = e.getAttribute("name");
    MinispecElement result = this.minispec.get(name);
    if (result != null) return result;
    String tag = e.getTagName();
    if (tag.equals("Model")) {
      result = modelFromElement(e);
    } else {
      result = primitiveNameFromElement(e);
    }
    this.minispec.put(name, result);
    return result;
  }


  protected void firstRound(Element el) {
    NodeList nodes = el.getChildNodes();
    for (int i = 0; i < nodes.getLength(); i++) {
      Node n = nodes.item(i);
      if (n instanceof Element) {
        Element child = (Element) n;
        String name = child.getAttribute("name");
        this.xmlElement.put(name, child);
      }
    }
  }

  // alimentation du map des instances de la syntaxe abstraite (metamodel)
  protected void secondRound(Element el) {
    NodeList nodes = el.getChildNodes();
    for (int i = 0; i < nodes.getLength(); i++) {
      Node n = nodes.item(i);
      if (n instanceof Element) {
        minispecElementFromXmlElement((Element)n);
      }
    }
  }

  protected Model modelFromElement(Element e) {
    String name = e.getAttribute("name");
    String package_ = e.getAttribute("package");
    Model model = new Model();
    model.setName(name);
    model.setPackage(package_);
    return model;
  }

  protected PrimitiveName primitiveNameFromElement(Element e) {
    String name = e.getAttribute("name");
    String type = e.getAttribute("type");
    String package_ = e.getAttribute("package");
    PrimitiveName primitiveName = new PrimitiveName();
    primitiveName.setName(name);
    primitiveName.setType(type);
    primitiveName.setPackage(package_);
    return primitiveName;
  }
  public Package getModelFromDocument(Document document) {
    Element e = document.getDocumentElement();

    firstRound(e);

    secondRound(e);

    Package aPackage = (Package) this.minispec.get(e.getAttribute("model"));

    return aPackage;
  }

  public Package getModelFromInputStream(InputStream stream) {
    try {
      // création d'une fabrique de documents
      DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

      // création d'un constructeur de documents
      DocumentBuilder constructeur = fabrique.newDocumentBuilder();
      Document document = constructeur.parse(stream);
      return getModelFromDocument(document);

    } catch (ParserConfigurationException pce) {
      System.out.println("Erreur de configuration du parseur DOM");
      System.out.println("lors de l'appel à fabrique.newDocumentBuilder();");
    } catch (SAXException se) {
      System.out.println("Erreur lors du parsing du document");
      System.out.println("lors de l'appel à construteur.parse(xml)");
    } catch (IOException ioe) {
      System.out.println("Erreur d'entrée/sortie");
      System.out.println("lors de l'appel à construteur.parse(xml)");
    }
    return null;
  }

  public Package getModelFromString(String contents) {
    InputStream stream = new ByteArrayInputStream(contents.getBytes());
    return getModelFromInputStream(stream);
  }

  public Package getModelFromFile(File file) {
    InputStream stream = null;
    try {
      stream = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return getModelFromInputStream(stream);
  }

  public Package getModelFromFilenamed(String filename) {
    File file = new File(filename);
    return getModelFromFile(file);
  }
}
