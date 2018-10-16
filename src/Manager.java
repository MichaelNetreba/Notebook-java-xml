import javax.xml.bind.annotation.XmlAttribute;

public class Manager {
    private String name;
    private String lastname;
    private String phone;
    private String department;
    private Integer year;

    public Manager(String name, String lastname, String phone, String department, Integer year) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.department = department;
        this.year = year;
    }
    //  This default constructor is required if there are other constructors;
    public Manager() {}

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
        return "Manager{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", department='" + department + '\'' +
                ", year=" + year +
                '}';
    }
}
