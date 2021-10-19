package com.zuka.coupon.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.annotation.Context;
import io.micronaut.core.util.StringUtils;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Context
@Converter(
		autoApply = true
)
public class JpaConverterJson implements AttributeConverter<Map<String, String>, String> {
	private static final Logger LOG = LoggerFactory.getLogger(JpaConverterJson.class);
	private static final TypeReference<HashMap<String, String>> TYPE_REFERENCE = new TypeReference<HashMap<String, String>>() {
	};
	private static ObjectMapper objectMapper = new ObjectMapper();

	public JpaConverterJson() {
	}

	@Inject
	public void setObjectMapper(ObjectMapper objectMapper) {
		JpaConverterJson.objectMapper = objectMapper;
	}

	public String convertToDatabaseColumn(Map<String, String> mapValue) {
		if (mapValue == null) {
			return null;
		} else {
			try {
				return objectMapper.writeValueAsString(mapValue);
			} catch (JsonProcessingException var3) {
				LOG.warn("Couldn't convert to database column", var3);
				return "";
			}
		}
	}

	public Map<String, String> convertToEntityAttribute(String stringValue) {
		if (StringUtils.isEmpty(stringValue)) {
			return null;
		} else {
			try {
				return (Map)objectMapper.readValue(stringValue, TYPE_REFERENCE);
			} catch (IOException var3) {
				LOG.warn("Couldn't convert to entity attribute", var3);
				return new HashMap();
			}
		}
	}
}