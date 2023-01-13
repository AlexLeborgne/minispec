package metaModel;

import java.util.ArrayList;
import java.util.List;

public class Model implements MinispecElement {

	List<Entity> entities;
	String name;
	
	public Model () {
		this.entities = new ArrayList<>();
	}
	
	public void accept(Visitor v) {
		v.visitModel(this);
	}
	
	public void addEntity(Entity e) {
		this.entities.add(e);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
