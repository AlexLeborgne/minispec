package minispecMetaModelToClass.javaGenerationParametrage.mappers;

import java.util.Map;

import minispecMetaModelToClass.javaGenerationParametrage.model.Parameter;
import org.w3c.dom.Element;

public abstract class ParameterMapper<T extends Parameter> {
  Map<String, T> parameters;
  public ParameterMapper(Map<String, T> parameters) {
    this.parameters = parameters;
  }
  public abstract void map(Element e);
}
