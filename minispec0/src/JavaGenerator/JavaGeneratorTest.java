package JavaGenerator;

import XMLIO.XMLAnalyser;
import metaModel.Model;
import org.junit.jupiter.api.Test;
import prettyPrinter.PrettyPrinter;

public class JavaGeneratorTest {

    @Test
    void test() {
        XMLAnalyser analyser = new XMLAnalyser();
        Model model = analyser.getModelFromFilenamed("Exemple2.xml");
        JavaGenerator jg = new JavaGenerator();
        model.accept(jg);
        System.out.println(jg.result());
    }
}
