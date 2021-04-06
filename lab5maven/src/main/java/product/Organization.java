package product;

public class Organization {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Double annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null

    public Organization(String name, Double annualTurnover, OrganizationType type) {
        this.id = (Long)Math.round((double) Math.random()*2147483647);
        this.name = name;
        this.annualTurnover = annualTurnover;
        this.type = type;
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

    public Double getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(Double annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String s = "";
        s+=" id: "+this.id.toString()+",\n name: "+this.name+",\n annualTurnover: "+this.annualTurnover.toString()+",\n type: "+this.type+"\n -----\n";
        return s;
    }
}
