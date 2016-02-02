package org.learn.gson;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

class DateDeserializer implements JsonDeserializer<Date> {
		
	public Date deserialize(JsonElement json, Type typeOfT,
									JsonDeserializationContext context)
			throws JsonParseException {
		return json == null ? null : new Date(json.getAsLong());
	}
}

class DateSerializer implements JsonSerializer<Date> {	
	public JsonElement serialize(Date date, Type typeOfSrc,
									JsonSerializationContext context) {
		return date == null ? null : new JsonPrimitive(date.getTime());
	}
}

public class Date2Json {
	public static void main(String[] args) {
		GsonBuilder gson = new GsonBuilder();
		gson.registerTypeAdapter(Date.class, new DateDeserializer());
		gson.registerTypeAdapter(Date.class, new DateSerializer());
		
		Gson objGson = gson.setPrettyPrinting().create();
		Person objPerson = new Person("Mike", "harvey", new Date(), "00186");
		// Convert Person object to json
		String json = objGson.toJson(objPerson);
		System.out.println("1. Convert Person object to Json");
		System.out.println(json);

		// Convert to json to person object
		Type listType = new TypeToken<Person>() {		}.getType();
		System.out.println("2. Convert JSON to person object");
		Person objFromJson = objGson.fromJson(json, listType);
		System.out.println(objFromJson);
	}
}
