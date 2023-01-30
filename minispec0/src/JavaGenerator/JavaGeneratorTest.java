package JavaGenerator;

import XMLIO.XMLAnalyser;
import XMLIO.XMLAnalyserImport;
import metaModel.Model;
import metaModel.ModelPackage;
import metaModel.Package;
import org.junit.jupiter.api.Test;
import prettyPrinter.PrettyPrinter;

public class JavaGeneratorTest {

    @Test
    void test() {
        XMLAnalyser analyser = new XMLAnalyser();
        Model model = analyser.getModelFromFilenamed("Exemple2.xml");
        XMLAnalyserImport analyserImport = new XMLAnalyserImport();
        ModelPackage modelPackage = analyserImport.getModelFromFilenamed("Exemple1.xml");
        JavaGenerator jg = new JavaGenerator(modelPackage);
        model.accept(jg);
        System.out.println(jg.result());
    }
}
