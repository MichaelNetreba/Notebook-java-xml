import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        createXmlNote();

        addManager("Alex", "Alexandrov", "911", 1999, "department");
        addManager("John", "Jonson", "119", 1990, "department");

        addWorker("Sophia", "Sofonova", "89099099999", 1923, "Alex");
        addWorker("Ivan","Ivanov","777", 1993, "John");

        getWorkerByName("Ivan");
        getWorkerByLastname("Ivanov");
        getWorkerByPhone("777");

        getManagerByName("Alex");
        getManagerByLastname("Alexandrov");
        getManagerByPhone("911");

        getWorkerByName("Sophia");
        sortByBirth();
    }

    public static void createXmlNote() throws JAXBException {

        //Staff is a root node of our xml file;
        Staff staff = new Staff();

        //Creating xml root node;
        //Create JAXB context and instantiate marshaller;
        JAXBContext jaxbContext = JAXBContext.newInstance(Staff.class);
        //  Marshaller : Java Object to XML content
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(staff, new File("test.xml"));
    }

    public static void addManager(String name, String lastname, String phone, Integer year, String department) throws JAXBException {
        Staff staff = readFromXml();
        Manager manager = new Manager(name, lastname, phone, department, year);
        staff.getManager().add(manager);
        saveToXml(staff);
    }

    public static void addWorker(String name, String lastname, String phone, Integer year, String manager) throws JAXBException {
        Staff staff = readFromXml();
        Worker worker = new Worker(name, lastname, phone, manager, year);
        staff.getWorker().add(worker);
        saveToXml(staff);
    }

    public static Manager getManagerByName(String value) throws Exception {
        Staff staff = readFromXml();
        Manager manager = new Manager();

        List<Manager> managers = staff.getManager();
        for (Manager m: managers) {
            if (m.getName().equals(value)) {
                manager = m;
            }
        }
        System.out.println(manager.toString());
        return manager;
    }

    public static Worker getWorkerByName(String value) throws Exception {
        Staff staff = readFromXml();
        Worker worker = new Worker();

        List<Worker> workers = staff.getWorker();
        for (Worker w: workers) {
            if (w.getName().equals(value)) {
                worker = w;
            }
        }
        System.out.println(worker.toString());
        return worker;
    }

    public static Manager getManagerByLastname(String value) throws Exception {
        Staff staff = readFromXml();
        Manager manager = new Manager();

        List<Manager> managers = staff.getManager();
        for (Manager m: managers) {
            if (m.getLastname().equals(value)) {
                manager = m;
            }
        }
        System.out.println(manager.toString());
        return manager;
    }

    public static Worker getWorkerByLastname(String value) throws Exception {
        Staff staff = readFromXml();
        Worker worker = new Worker();

        List<Worker> workers = staff.getWorker();
        for (Worker w: workers) {
            if (w.getLastname().equals(value)) {
                worker = w;
            }
        }
        System.out.println(worker.toString());
        return worker;
    }

    public static Manager getManagerByPhone(String value) throws Exception {
        Staff staff = readFromXml();
        Manager manager = new Manager();

        List<Manager> managers = staff.getManager();
        for (Manager m: managers) {
            if (m.getPhone().equals(value)) {
                manager = m;
            }
        }
        System.out.println(manager.toString());
        return manager;
    }

    public static Worker getWorkerByPhone(String value) throws Exception {
        Staff staff = readFromXml();
        Worker worker = new Worker();

        List<Worker> workers = staff.getWorker();
        for (Worker w: workers) {
            if (w.getPhone().equals(value)) {
                worker = w;
            }
        }
        System.out.println(worker.toString());
        return worker;
    }

    public static void sortByLastName() throws JAXBException {
        Staff staff = readFromXml();
        staff.getWorker().sort(Comparator.comparing(Worker::getLastname));
        staff.getManager().sort(Comparator.comparing(Manager::getLastname));
        saveToXml(staff);
    }

    public static void sortByBirth() throws JAXBException {
        Staff staff = readFromXml();
        staff.getWorker().sort(Comparator.comparing(Worker::getYear));
        staff.getManager().sort(Comparator.comparing(Manager::getYear));
        saveToXml(staff);
    }

    public static void removeManagerByName(String name) throws Exception {
        Staff staff = readFromXml();
        Manager m = new Manager();
        Iterator iterator = staff.getManager().iterator();
        while (iterator.hasNext()) {
            Manager manager = (Manager) iterator.next();
            if (manager.getName().equals(name)){
                m = manager;
            }
        }
        staff.getManager().remove(m);
        saveToXml(staff);
    }

    public static void removeWorkerByName(String name) throws Exception {
        Staff staff = readFromXml();
        Worker w = new Worker();
        Iterator iterator = staff.getWorker().iterator();
        while (iterator.hasNext()) {
            Worker worker = (Worker) iterator.next();
            if (worker.getName().equals(name)){
                w = worker;
            }
        }
        staff.getWorker().remove(w);
        saveToXml(staff);
    }

    public static void removeManagerByLastname(String lastname) throws Exception {
        Staff staff = readFromXml();
        Manager m = new Manager();
        Iterator iterator = staff.getManager().iterator();
        while (iterator.hasNext()) {
            Manager manager = (Manager) iterator.next();
            if (manager.getLastname().equals(lastname)){
                m = manager;
            }
        }
        staff.getManager().remove(m);
        saveToXml(staff);
    }

    public static void removeWorkerByLastname(String lastname) throws Exception {
        Staff staff = readFromXml();
        Worker w = new Worker();
        Iterator iterator = staff.getWorker().iterator();
        while (iterator.hasNext()) {
            Worker worker = (Worker) iterator.next();
            if (worker.getLastname().equals(lastname)){
                w = worker;
            }
        }
        staff.getWorker().remove(w);
        saveToXml(staff);
    }

    public static Staff readFromXml() throws JAXBException {
        //Converting xml to java object;
        JAXBContext jaxbContext = JAXBContext.newInstance(Staff.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Staff)jaxbUnmarshaller.unmarshal(new File("test.xml"));
    }

    public static void saveToXml(Staff staff) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Staff.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(staff, new File("test.xml"));
    }

}