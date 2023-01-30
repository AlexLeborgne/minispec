package metaModel;

import java.util.ArrayList;
import java.util.List;

public class Package implements MinispecElement {

    List<PrimitiveName> primitiveNames;
    List<Model> models;

    public Package() {
        this.primitiveNames = new ArrayList<>();
        this.models = new ArrayList<>();
    }

    public void addPrimitiveName(PrimitiveName pn) {
        this.primitiveNames.add(pn);
    }

    public void addModel(Model m) {
        this.models.add(m);
    }

    public List<PrimitiveName> getPrimitiveNames() {
        return primitiveNames;
    }

    public List<Model> getModels() {
        return models;
    }


}
