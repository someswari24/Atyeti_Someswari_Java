package employeeJson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class EmployeejsonValueUpdate {
    public static void jsonUpdate(JsonNode node,String field,String newValue){
        if (node.isObject()){
            ObjectNode objectNode= (ObjectNode) node;

            if (objectNode.has(field)){
                objectNode.put(field,newValue);
            }

            objectNode.fields().forEachRemaining(
                    entry->jsonUpdate(entry.getValue(), field,newValue));
        }
        else if (node.isArray()) {
            for (JsonNode item:node){
                jsonUpdate(item,field,newValue);
            }
        }
    }
}
