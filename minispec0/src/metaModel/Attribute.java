package metaModel;

public class Attribute implements MinispecElement{

    String name;
    String type;
    String defaultvalue;

    public Attribute() {
    }

    public void accept(Visitor v) {
        v.visitAttribute(this);
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue;
    }
}
