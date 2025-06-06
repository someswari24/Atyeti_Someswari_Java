package employeeJson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String filePath="src/main/resources/data.json";

        ObjectMapper objectMapper=new ObjectMapper();

        JsonNode root=objectMapper.readTree(new File(filePath));

        EmployeejsonValueUpdate.jsonUpdate(root,"name","someswari");

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath),root);

        System.out.println("data updated successfully");

    }
}
