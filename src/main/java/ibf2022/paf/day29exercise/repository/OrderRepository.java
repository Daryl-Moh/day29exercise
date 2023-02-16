package ibf2022.paf.day29exercise.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.day29exercise.model.Order;
import ibf2022.paf.day29exercise.service.Utils;
import jakarta.json.JsonObject;

import static ibf2022.paf.Constants.*;

@Repository
public class OrderRepository {
    
    @Autowired
    private MongoTemplate template;

    public void insertOrder(Order order) {
        JsonObject json = Utils.toJson(order);
        Document doc = Document.parse(json.toString());
        template.insert(COLLECTION_ORDER, Document.class);
    }


}
