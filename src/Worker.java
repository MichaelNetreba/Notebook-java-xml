import javax.xml.bind.annotation.XmlAttribute;

public class Worker {
    private String name;
    private String lastname;
    private String phone;
    private String manager;
    private Integer year;

    public Worker(String name, String lastname, String phone, String manager, Integer year) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.manager = manager;
        this.year = year;
    }
    //  This default constructor is required if there are other constructors;
    public Worker(){}

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) throws Exception {
        if (year > 2018 || year < 1920) throw new Exception("Invalid birth year");
        this.year = year;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", manager='" + manager + '\'' +
                ", year=" + year +
                '}';
    }
}
