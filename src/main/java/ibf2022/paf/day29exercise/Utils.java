package ibf2022.paf.day29exercise;

import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import ibf2022.paf.day29exercise.model.LineItem;
import ibf2022.paf.day29exercise.model.Order;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Utils {

    // This method is to read the payload and convert it into a Json Object

    public static JsonObject toJson(String str) {
        Reader reader = new StringReader(str);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }

    // This section takes the Order entity and converts it into a Document for insert into mongo

    public static Document toDocument(Order order) {
        Document doc = new Document();
        doc.put("orderId", order.getOrderId());
        doc.put("name", order.getName());
        doc.put("email", order.getEmail());
		doc.put("deliveryDate", order.getDeliveryDate());

        List<Document> docs = order.getLineItems()
            .stream()
            .map(v -> toDocument(v)) // function from above for Line Items --> Document
            .toList();

        doc.put("lineItems", docs);
        return doc;
    }

    public static Document toDocument(LineItem lineItem) {
        Document doc = new Document();
        doc.put("item", lineItem.getItem());
        doc.put("quantity", lineItem.getQuantity());
        return doc;
    }

    // These methods convert the Json Object into an Order entity for processing in Java
    
    public static Order toOrder(JsonObject json) {
        Order order = new Order();
        order.setName(json.getString("name"));
        order.setEmail(json.getString("email"));
        // to do formatting for date
        order.setDeliveryDate(new Date());
        ;

        List<LineItem> lineItems = json.getJsonArray("lineItems")
                .stream()
                .map(v -> (JsonObject) v)
                .map((v -> toLineItem(v)))
                .toList();

        order.setLineItems(lineItems);
        return order;
    }

    public static LineItem toLineItem(JsonObject json) {
        LineItem lineItem = new LineItem();
        lineItem.setItem(json.getString("item"));
        lineItem.setQuantity(json.getInt("quantity"));
        return lineItem;
    }
}

    // These methods convert the Order entity into an Json Object

    // public static JsonObject toJson(Order order) {

    //     // this will hold the line items
    //     JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
    //     order.getLineItems()
    //         .stream()
    //         .map(v -> toJson(v)) // function from below for Line Items --> Json
    //         .forEach(v -> {
    //             arrBuilder.add(v);
    //         });

    //     return Json.createObjectBuilder()
    //         .add("orderID", order.getOrderId())
    //         .add("name", order.getName())
    //         .add("email", order.getEmail())
    //         .add("deliveryDate", order.getDeliveryDate().toString())
    //         .add("lineItems", arrBuilder.build())
    //         .build();

    // }

    // public static JsonObject toJson(LineItem lineItem) {
    //     return Json.createObjectBuilder()
    //         .add("item", lineItem.getItem())
    //         .add("quantity", lineItem.getQuantity())
    //         .build();
    // }
