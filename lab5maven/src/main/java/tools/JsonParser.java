package tools;

import java.io.*;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import product.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JsonParser {
    private final String path;

    public JsonParser(String path) {
        this.path = path;
    }

    public LinkedList<Product> reading() {
        LinkedList<Product> minicollection = new LinkedList<>();
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(path)) {
            JSONObject productsJsonObject = (JSONObject) parser.parse(reader);
            JSONArray productJsonArray = (JSONArray) productsJsonObject.get("products");
            for (Object it: productJsonArray) {
                JSONObject productJsonObject = (JSONObject) it;
                JSONObject coordinatesJSO = (JSONObject) productJsonObject.get("coordinates");
                int x = (int) (long) coordinatesJSO.get("x");
                int y = (int) (long) coordinatesJSO.get("y");
                Coordinates coordinates = new Coordinates(x, y);

                JSONObject organizationJSO = (JSONObject) productJsonObject.get("organization");
                long idOfOrg = (long) organizationJSO.get("id");
                String nameOfOrg = (String) organizationJSO.get("name");
                Double annualTurnover = (double) organizationJSO.get("annualTurnover");
                String typeString = (String) organizationJSO.get("type");
                OrganizationType type;
                switch (typeString) {
                    case "COMMERCIAL": type = OrganizationType.COMMERCIAL;break;
                    case "PUBLIC": type = OrganizationType.PUBLIC;break;
                    case "GOVERNMENT": type = OrganizationType.GOVERNMENT;break;
                    case  "PRIVATE_LIMITED_COMPANY": type = OrganizationType.PRIVATE_LIMITED_COMPANY;break;
                    case "OPEN_JOINT_STOCK_COMPANY": type = OrganizationType.OPEN_JOINT_STOCK_COMPANY;break;
                    default: throw new Exception();
                }
                Organization organization = new Organization(nameOfOrg, annualTurnover, type);
                organization.setId(idOfOrg);

                long id = (long) productJsonObject.get("id");
                String name = (String) productJsonObject.get("name");
                Double price = (double) productJsonObject.get("price");
                String unitOfMeasureString = (String) productJsonObject.get("unitOfMeasure");
                UnitOfMeasure unitOfMeasure;
                switch (unitOfMeasureString) {
                    case "KILOGRAMS": unitOfMeasure = UnitOfMeasure.KILOGRAMS;break;
                    case "CENTIMETERS": unitOfMeasure = UnitOfMeasure.CENTIMETERS;break;
                    case "LITERS": unitOfMeasure = UnitOfMeasure.LITERS;break;
                    default: throw new Exception();
                }
                Product product = new Product(name, coordinates, price, unitOfMeasure, organization);
                product.setId(id);
                minicollection.add(product);
                return minicollection;
            }



        } catch (Exception e) {
            System.out.println("Parsing error "+e.toString());
        }
        return minicollection;
    }

    public void writing() {

    }
}
