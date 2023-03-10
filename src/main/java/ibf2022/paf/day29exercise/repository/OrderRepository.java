package ibf2022.paf.day29exercise.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.day29exercise.Constants;
import ibf2022.paf.day29exercise.Utils;
import ibf2022.paf.day29exercise.model.Order;

@Repository
public class OrderRepository {
    
    @Autowired
    private MongoTemplate template;

    public void insertOrder(Order order) {
        // Calls on Utils.toDocument to take the Order entity and converts it to document for inserting
        Document doc = Utils.toDocument(order);
        template.insert(doc, Constants.COLLECTION_PURCHASE_ORDER);
    }

}
