package com.viaflow.deserializer;

import java.io.IOException;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class GeoJsonDeserializer extends JsonDeserializer<GeoJsonPoint> {

	@Override
	public GeoJsonPoint deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

		final JsonNode tree = jsonParser.getCodec().readTree(jsonParser);
		final String type = tree.get("type").asText();
		final JsonNode coordsNode = tree.get("coordinates");

		System.out.println(tree.toString());
		System.out.println(type);
		System.out.println(coordsNode.toString());

		double x = 0;
		double y = 0;
		if ("Point".equalsIgnoreCase(type)) {
			x = coordsNode.get(0).asDouble();
			y = coordsNode.get(1).asDouble();
		} else {
			System.out.println(String.format("No logic present to deserialize %s ", tree.asText()));
		}

		final GeoJsonPoint point = new GeoJsonPoint(x, y);

		return point;
	}
}

