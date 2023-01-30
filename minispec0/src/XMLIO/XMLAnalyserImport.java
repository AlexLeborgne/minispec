package XMLIO;

import metaModel.*;
import metaModel.Package;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class XMLAnalyserImport {

  protected Map<String, Package> listPackage;
  protected Map<String, PrimitiveName> listPrimitiveName;
  protected Map<String, MinispecElement> minispecIndex;
  // Map des elements XML
  protected Map<String, Element> xmlElementIndex;

  public XMLAnalyserImport() {
    this.listPackage = new HashMap<String, Package>();
    this.listPrimitiveName = new HashMap<String, PrimitiveName>();
    this.minispecIndex = new HashMap<String, MinispecElement>();
    this.xmlElementIndex = new HashMap<String, Element>();
  }

  public void packageFromElement(Element e) {
    Package pack = new Package();
    pack.setPackageName(e.getAttribute("package"));
    listPackage.put(e.getAttribute("name"), pack);
  }

  public void primitiveNameFromElement(Element e) {
    PrimitiveName primitiveName = new PrimitiveName();
    primitiveName.setName(e.getAttribute("package"));
    listPrimitiveName.put(e.getAttribute("name"), primitiveName);
  }

  protected MinispecElement minispecElementFromXmlElement(Element e) {
    String tag = e.getTagName();
    if (tag.equals("model")) {
      packageFromElement(e);
    } else if (tag.equals("primitive")) {
      primitiveNameFromElement(e);
    }
    return null;
  }

  protected void firstRound(Element el) {
    NodeList nodes = el.getChildNodes();
    for (int i = 0; i < nodes.getLength(); i++) {
      Node n = nodes.item(i);
      if (n instanceof Element) {
        Element child = (Element) n;
        String name = child.getAttribute("name");
        this.xmlElementIndex.put(name, child);
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
  public ModelPackage getModelFromDocument(Document document) {
    Element e = document.getDocumentElement();

    ModelPackage modelPackage = new ModelPackage();

    firstRound(e);

    secondRound(e);

    //Package aPackage = (Package) this.minispecIndex.get(e.getAttribute("model"));

    modelPackage.setListPackage(this.listPackage);
    modelPackage.setListPrimitiveName(this.listPrimitiveName);

    return modelPackage;
  }

  public ModelPackage getModelFromInputStream(InputStream stream) {
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

  public ModelPackage getModelFromFile(File file) {
    InputStream stream = null;
    try {
      stream = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return getModelFromInputStream(stream);
  }

  public ModelPackage getModelFromFilenamed(String filename) {
    File file = new File(filename);
    return getModelFromFile(file);
  }
}
