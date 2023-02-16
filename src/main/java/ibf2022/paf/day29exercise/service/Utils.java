package ibf2022.paf.day29exercise.service;

import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import java.util.List;

import ibf2022.paf.day29exercise.model.LineItem;
import ibf2022.paf.day29exercise.model.Order;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Utils {

    public static JsonObject toJson(String str) {
        Reader reader = new StringReader(str);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }

    public static JsonObject toJson(Order order) {

        // this will hold the line items
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        order.getItems()
            .stream()
            .map(v -> toJson(v))
            .forEach(v -> {
                arrBuilder.add(v);
            });

        JsonObject json = Json.createObjectBuilder()
            .add("name", order.getName())
            .add("email", order.getEmail())
            .add("deliveryDate", order.getDeliverDate().toString())
            .add("lineItems", arrBuilder.build())
            .build();

        return null;

    }

    public static JsonObject toJson(LineItem lineItem) {
        return Json.createObjectBuilder()
            .add("item", lineItem.getItemName())
            .add("quantity", lineItem.getItemQuantity())
            .build();
    }

    public static LineItem toLineItem(JsonObject json) {
        LineItem lineItem = new LineItem();
        lineItem.setItemName(json.getString("item"));
        lineItem.setItemQuantity(json.getInt("quantity"));
        return lineItem;
    }

    public static Order toOrder(JsonObject json) {
        Order order = new Order();
        order.setName("name");
        order.setEmail("email");
        // to do formatting for date
        order.setDeliverDate(new Date());
        ;

        List<LineItem> lineItems = json.getJsonArray("lineItems")
                .stream()
                .map(v -> (JsonObject) v)
                .map((v -> toLineItem(v)))
                .toList();

        order.setItems(lineItems);
        return order;
    }
}
