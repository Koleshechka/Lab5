package product;

import java.time.ZonedDateTime;

public class Product{
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double price; //Поле не может быть null, Значение поля должно быть больше 0
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private Organization manufacturer; //Поле не может быть null

    public Product(String name, Coordinates coordinates, Double price, UnitOfMeasure unitOfMeasure, Organization manufacturer) {
        this.id = (Long)Math.round((double) Math.random()*2147483647);
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
        this.creationDate = ZonedDateTime.now();
    }

    public Product(String name, int x, int y, Double price, UnitOfMeasure unitOfMeasure, String nameOfOrg, Double annualTurnover, OrganizationType type) {
        this.id = (Long)Math.round((double) Math.random()*2147483647);
        this.name = name;
        this.coordinates = new Coordinates(x,y);
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = new Organization(nameOfOrg, annualTurnover, type);
        this.creationDate = ZonedDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Organization manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        String s = "";
        s+=" id: "+ this.id.toString()+",\n name: "+this.name+",\n coordinates: \n"+this.coordinates.toString()+",\n creationDate: "+this.creationDate.toString()+",\n price: "+this.price.toString()+",\n unitOfMeasure: "+this.unitOfMeasure.toString()+",\n manufacturer: \n"+this.manufacturer.toString();
        return s;
    }
}
