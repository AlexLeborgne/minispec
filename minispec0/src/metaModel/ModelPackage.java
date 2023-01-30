package metaModel;

import java.util.HashMap;
import java.util.Map;

public class ModelPackage {
  Map<String, Package> listPackage;
  Map<String, PrimitiveName> listPrimitiveName;

  public ModelPackage () {
    this.listPackage = new HashMap<String, Package>();
    this.listPrimitiveName = new HashMap<String, PrimitiveName>();
  }

  //getter and setter
  public Map<String, Package> getListPackage() {
    return listPackage;
  }
  public void setListPackage(Map<String, Package> listPackage) {
    //copy listPackage to this.listPackage
    this.listPackage = listPackage;
  }
  public Map<String, PrimitiveName> getListPrimitiveName() {
    return listPrimitiveName;
  }
  public void setListPrimitiveName(Map<String, PrimitiveName> listPrimitiveName) {
    this.listPrimitiveName = listPrimitiveName;
  }
}
