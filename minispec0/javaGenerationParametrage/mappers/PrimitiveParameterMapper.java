package minispecMetaModelToClass.javaGenerationParametrage.mappers;

import java.util.Map;

import minispecMetaModelToClass.javaGenerationParametrage.model.PrimitiveParameter;
import org.w3c.dom.Element;

public class PrimitiveParameterMapper extends ParameterMapper<PrimitiveParameter> {

  public PrimitiveParameterMapper(Map<String, PrimitiveParameter> parameters) {
    super(parameters);
  }
  @Override
  public void map(Element e) {
    PrimitiveParameter parameter = new PrimitiveParameter();
    parameter.setPackageName(e.getAttribute("package"));
    parameter.setClassName(e.getAttribute("type"));
    parameters.put(e.getAttribute("name"), parameter);
  }

}
