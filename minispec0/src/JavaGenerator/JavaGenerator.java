package JavaGenerator;

import metaModel.Attribute;
import metaModel.Entity;
import metaModel.Model;
import metaModel.Visitor;

public class JavaGenerator extends Visitor {

    String result = "";
    String declaration;
    String attribut;
    String getter;
    String setter;

    public void visitModel(Model e) {
        result = "package " + e.getName() + ";\n\nimport ???;\n\n";

        for (Entity n : e.getEntities()) {
            result = result + "public class " + n.getName() + "{\n";
            declaration = "public "+ n.getName().substring(0,1).toUpperCase() + n.getName().substring(1) + "(){}";
            attribut = "";
            getter = "";
            setter = "";
            n.accept(this);
        }
        result = result + "\n\n";
    }

    public void visitEntity(Entity e) {

        for (Attribute a : e.getAttributes()){
            attribut = attribut + "  " + a.getType() + " " + a.getName() + ";\n";
            getter = getter +  "  public " + a.getType() + " get"+a.getName().substring(0,1).toUpperCase() + a.getName().substring(1) + "(){ return " + a.getName() + "; }\n";
            setter = setter + "  public void set"+a.getName().substring(0,1).toUpperCase() + a.getName().substring(1) + "(" + a.getType() + " " + a.getName() +"){ this." + a.getName() + " = " + a.getName() + "; }\n";
            a.accept(this);
        }
        result = result + attribut + getter + setter + "}\n\n";
    }

    public String result() {
        return result;
    }
}
