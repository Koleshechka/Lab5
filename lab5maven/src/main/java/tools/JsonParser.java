package tools;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
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
        JSONObject productsJsonObject;
        JSONArray productJsonArray = null;

        try (FileReader reader = new FileReader(path)) {
            //productsJsonObject = (JSONObject) parser.parse(reader);
            //productJsonArray = (JSONArray) productsJsonObject.get("products");
            productJsonArray = (JSONArray) parser.parse(reader);
        } catch (FileNotFoundException e) {
            System.out.println("Файл с коллекцией не найден." + e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (ParseException e) {
            System.out.println("Parsing Error" + e.toString());
        }

        try {
            for (Object it : productJsonArray) {
                JSONObject productJsonObject = (JSONObject) it;
                JSONObject coordinatesJSO = (JSONObject) productJsonObject.get("coordinates");
                int x = 0;
                int y = 0;
                if (coordinatesJSO.get("x") == null || coordinatesJSO.get("y") == null || coordinatesJSO.get("x").equals("") || coordinatesJSO.get("y").equals("")) {
                    if (coordinatesJSO.get("x") == null || coordinatesJSO.get("x").equals("")) {
                        x = 0;
                    }
                    if (coordinatesJSO.get("y") == null || coordinatesJSO.get("y").equals("")) {
                        y = 0;
                    }
                } else {
                    x = (int) (long) coordinatesJSO.get("x");
                    y = (int) (long) coordinatesJSO.get("y");
                }
                Coordinates coordinates = new Coordinates(x, y);


                JSONObject organizationJSO = (JSONObject) productJsonObject.get("manufacturer");
                long idOfOrg;
                if (organizationJSO.get("id") == null || organizationJSO.get("id").equals("") || (long) organizationJSO.get("id") <= 0) {
                    idOfOrg = (Long) Math.round((double) Math.random() * 2147483647);
                } else {
                    idOfOrg = (long) organizationJSO.get("id");
                }

                String nameOfOrg;
                if (organizationJSO.get("name") == null || organizationJSO.get("name").equals("")) {
                    nameOfOrg = "noname";
                } else {
                    nameOfOrg = (String) organizationJSO.get("name");
                }

                Double annualTurnover;
                if (organizationJSO.get("annualTurnover") == null || organizationJSO.get("annualTurnover").equals("") || (double) organizationJSO.get("annualTurnover") <= 0) {
                    annualTurnover = 1.0;
                } else {
                    annualTurnover = (double) organizationJSO.get("annualTurnover");
                }

                String typeString = (String) organizationJSO.get("type");
                OrganizationType type;
                switch (typeString) {
                    case "COMMERCIAL":
                        type = OrganizationType.COMMERCIAL;
                        break;
                    case "PUBLIC":
                        type = OrganizationType.PUBLIC;
                        break;
                    case "GOVERNMENT":
                        type = OrganizationType.GOVERNMENT;
                        break;
                    case "PRIVATE_LIMITED_COMPANY":
                        type = OrganizationType.PRIVATE_LIMITED_COMPANY;
                        break;
                    case "OPEN_JOINT_STOCK_COMPANY":
                        type = OrganizationType.OPEN_JOINT_STOCK_COMPANY;
                        break;
                    default:
                        continue;
                }
                Organization organization = new Organization(nameOfOrg, annualTurnover, type);
                organization.setId(idOfOrg);


                long id;
                if (productJsonObject.get("id") == null || productJsonObject.get("id").equals("") || (long) productJsonObject.get("id") <= 0) {
                    id = (Long) Math.round((double) Math.random() * 2147483647);
                } else {
                    id = (long) productJsonObject.get("id");
                }

                String name;
                if (productJsonObject.get("name") == null || productJsonObject.get("name").equals("")) {
                    name = "noname";
                } else {
                    name = (String) productJsonObject.get("name");
                }

                Double price;
                if (productJsonObject.get("price") == null || productJsonObject.get("price").equals("") || (double) productJsonObject.get("price") <= 0) {
                    price = 1.0;
                } else {
                    price = (double) productJsonObject.get("price");
                }


                String unitOfMeasureString = (String) productJsonObject.get("unitOfMeasure");
                UnitOfMeasure unitOfMeasure;
                switch (unitOfMeasureString) {
                    case "KILOGRAMS":
                        unitOfMeasure = UnitOfMeasure.KILOGRAMS;
                        break;
                    case "CENTIMETERS":
                        unitOfMeasure = UnitOfMeasure.CENTIMETERS;
                        break;
                    case "LITERS":
                        unitOfMeasure = UnitOfMeasure.LITERS;
                        break;
                    default:
                        continue;
                }
                Product product = new Product(name, coordinates, price, unitOfMeasure, organization);
                product.setId(id);
                try {
                    ZonedDateTime creationDate = ZonedDateTime.parse(productJsonObject.get("creationDate").toString());
                    product.setCreationDate(creationDate);
                } catch (Exception e) {
                    System.out.println("CreationDate error");
                }
                minicollection.add(product);
            }
            return minicollection;

        } catch (Exception e) {
            System.out.println("Reading error " + e.toString());
        }
        return minicollection;
    }


}
