package minispecMetaModelToClass.javaGenerationParametrage.mappers;

import java.util.Map;

import minispecMetaModelToClass.javaGenerationParametrage.model.ModelParameter;
import org.w3c.dom.Element;


public class ModelParameterMapper extends ParameterMapper<ModelParameter> {

  public ModelParameterMapper(Map<String, ModelParameter> parameters) {
    super(parameters);
  }

  @Override
  public void map(Element e) {
    ModelParameter parameter = new ModelParameter();
    parameter.setPackageName(e.getAttribute("package"));
    parameters.put(e.getAttribute("name"), parameter);
  }
}
