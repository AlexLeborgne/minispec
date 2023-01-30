package prettyPrinter;

import metaModel.Attribute;
import metaModel.Entity;
import metaModel.Model;
import metaModel.Visitor;

public class PrettyPrinter extends Visitor {
	String result = "";
	
	public String result() {
		return result;
	}
	
	public void visitModel(Model e) {
		result = "model \n\n";
		
		for (Entity n : e.getEntities()) {
			n.accept(this);
		}
		result = result + "\nend model;\n";
	}
	
	public void visitEntity(Entity e) {
		result = result + "entity " + e.getName() + "\n";

		for (Attribute n : e.getAttributes()){
			n.accept(this);
		}

		result = result + "\nend entity;\n";
	}

	public void visitAttribute(Attribute e){
		result = result + "   " + e.getName() + " : " + e.getType();
		if(!e.getDefaultvalue().isEmpty()){
			result = result + " := " + e.getDefaultvalue();
		}
		result = result + ";\n";
	}

	
}
