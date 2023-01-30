package minispecMetaModelToClass.javaGenerationParametrage;

import java.util.HashMap;
import java.util.Map;

import minispecMetaModelToClass.javaGenerationParametrage.mappers.ModelParameterMapper;
import minispecMetaModelToClass.javaGenerationParametrage.mappers.ParameterMapper;
import minispecMetaModelToClass.javaGenerationParametrage.mappers.PrimitiveParameterMapper;
import minispecMetaModelToClass.javaGenerationParametrage.model.ModelParameter;
import minispecMetaModelToClass.javaGenerationParametrage.model.Parameter;
import minispecMetaModelToClass.javaGenerationParametrage.model.PrimitiveParameter;
import org.w3c.dom.Element;
import xmlUtils.XmlReader;

public class ParametrageAnalyser extends XmlReader {

	private final Map<String, PrimitiveParameter> primitiveParameters;
	private final Map<String, ModelParameter> modelParameters;
	private final Map<String, ParameterMapper<? extends Parameter>> parameterMappers = new HashMap<>();

	public ParametrageAnalyser() {
		this.primitiveParameters = new HashMap<>();
		this.modelParameters = new HashMap<>();
		parameterMappers.put("primitive", new PrimitiveParameterMapper(primitiveParameters));
		parameterMappers.put("model", new ModelParameterMapper(modelParameters));
	}

	public Map<String, PrimitiveParameter> getPrimitiveParameters() {
		return primitiveParameters;
	}

	public Map<String, ModelParameter> getModelParameters() {
		return modelParameters;
	}

	private void parametragesFromXmlElement(Element e) {
		String tag = e.getTagName();
		ParameterMapper<? extends Parameter> mapper = parameterMappers.get(tag);
		if (mapper == null) {
			throw new RuntimeException("No mapper for tag " + tag);
		}
		mapper.map(e);
	}

	public void analyse(String xmlFile) {
		Element root = getElementFromFilenamed(xmlFile);
		round(root, this::parametragesFromXmlElement);
	}
}
