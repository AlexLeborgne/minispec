package JavaGenerator;

import metaModel.*;
import metaModel.Package;

import java.util.List;
import java.util.Map;

public class JavaGenerator extends Visitor {

    String result = "";
    String imports = "";
    String declaration;
    String attribut;
    String getter;
    String setter;

    ModelPackage modelPackage;

    public JavaGenerator(ModelPackage modelPackage) {
        this.modelPackage = modelPackage;
    }


    public void visitModel(Model e) {
        result = "package " + e.getName() + ";\n\nimport ???;\n\n";
        Map<String, Package> listPackage = modelPackage.getListPackage();


        for (Entity entity : e.getEntities()) {
            //for on listPackage
            for (Map.Entry<String, Package> entry : listPackage.entrySet()) {
                if (entry.getKey().equals(entity.getName())) {
                    imports += "import " + entry.getValue().getPackageName() + ";\n";
                }
            }
        }

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
        Map<String, PrimitiveName> listPrimitiveName = modelPackage.getListPrimitiveName();

        for (Attribute a : e.getAttributes()) {
            for (Map.Entry<String, PrimitiveName> entry : listPrimitiveName.entrySet()) {
                if (entry.getKey().equals(a.getType())) {
                    imports += "import " + entry.getValue().getName() + "." + a.getType() + ";\n";
                }
            }
        }

        for (Attribute a : e.getAttributes()){
            attribut = attribut + "  " + a.getType() + " " + a.getName();
            if(!a.getDefaultvalue().isEmpty()){
                attribut = attribut + " = " + a.getDefaultvalue();
            }
            attribut = attribut + ";\n";
            getter = getter +  "  public " + a.getType() + " get"+a.getName().substring(0,1).toUpperCase() + a.getName().substring(1) + "(){ return " + a.getName() + "; }\n";
            setter = setter + "  public void set"+a.getName().substring(0,1).toUpperCase() + a.getName().substring(1) + "(" + a.getType() + " " + a.getName() +"){ this." + a.getName() + " = " + a.getName() + "; }\n";
            a.accept(this);
        }
        result = result + imports + attribut + getter + setter + "}\n\n";
    }

    public String result() {
        return result;
    }
}
