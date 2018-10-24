import javax.xml.bind.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        createXmlNote();

        addManager("Alex", "Alexandrov", "911", 1999, "department");
        addManager("John", "Jonson", "119", 1990, "department");

        addWorker("Sophia", "Sofonova", "89099099999", 1923, "Alex");
        addWorker("Ivan", "Ivanov", "777", 1993, "John");
/*
        getWorkerByName("Ivan");
        getWorkerByLastname("Ivanov");
        getWorkerByPhone("777");

        getManagerByName("Alex");
        getManagerByLastname("Alexandrov");
        getManagerByPhone("911");

        getWorkerByName("Sophia");
        sortByBirth();
 */
        //Console User Interface
        System.out.println("Would you like to add new user?");
        System.out.println("Input: 1 = Yes or 2 = No");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String n=reader.readLine();
        if (n.equals("1") || n.equals("2")){
switch(n){
    case "1" : // First branch;
        System.out.println("Will this User be Manager or Worker?"+ '\n'+"1.Manager" +'\n'+"2.Worker");
            String n2 = reader.readLine();
            if (n2.equals("1")|| n2.equals("2")){
                switch(n2){
                    case "1" :
                        System.out.println("Input first_name,last_name, phone, year(number), department"+'\n'+"(Press Enter after every input.)");
                        addManager(reader.readLine(),reader.readLine(),reader.readLine(),Integer.parseInt(reader.readLine()), reader.readLine());

                        System.out.println("Manager has been added");
                        break;
                    case "2" :
                        System.out.println("Input first_name,last_name, phone, year(number), name of Manager"+'\n'+"(Press Enter after every input.)");
                        addWorker(reader.readLine(),reader.readLine(),reader.readLine(),Integer.parseInt(reader.readLine()),reader.readLine());
                        System.out.println("Worker has been added");
                }

            } else { System.out.println("Error Input. Try again"); }
            break;

        case "2" : // Second branch;
        System.out.println("What would you like to do:"+'\n'+ "1.Find Manager by First_Name"+'\n'+ "2.Find Manager by Last_Name"+'\n'
                +"3.Find Manager by Phone_Number"+'\n'+"4.Find Worker by First_Name"+'\n'+"5.Find Worker by Last_Name"+'\n'+"6.Find Worker by Phone_Number"+'\n'
                +"7.Sort Users by Last_Name"+'\n'+"8.Sort Users by Birthday(Year)"+'\n'+"9.Remove Manager by First_Name"+'\n'+"10.Remove Manager by Last_Name"+'\n'
                +"11.Remove Worker by First_Name"+'\n'+"12.Remove Worker by Last_Name");
        String n3 = reader.readLine();
            if (n3.equals("1") || n3.equals("2") || n3.equals("3")|| n3.equals("4")|| n3.equals("5")||
            n3.equals("6")||n3.equals("7")||n3.equals("8")||n3.equals("9")||n3.equals("10")||n3.equals("11")||n3.equals("12")) {
             switch(n3) {
                 case "1" :
                     System.out.println("Write the manager's first name.Press Enter.");
                     getManagerByName(reader.readLine());
                     break;
                 case "2" :
                     System.out.println("Write the manager's last name. Press Enter.");
                     getManagerByLastname(reader.readLine());
                     break;
                 case "3" :
                     System.out.println("Write the manager's phone number. Press Enter");
                     getManagerByPhone(reader.readLine());
                     break;
                 case "4" :
                     System.out.println("Write the worker's first name.Press Enter.");
                     getWorkerByName(reader.readLine());
                     break;
                 case "5" :
                     System.out.println("Write the worker's last name.Press Enter.");
                     getWorkerByLastname(reader.readLine());
                     break;
                 case "6" :
                     System.out.println("Write the worker's phone number.Press Enter.");
                     getWorkerByPhone(reader.readLine());
                     break;
                 case "7" :
                     sortByLastName();
                     System.out.println("Sort has been done successfully.");
                     break;
                 case "8" :
                     sortByBirth();
                     System.out.println("Sort has been done successfully.");
                     break;
                 case "9" :
                     removeManagerByName(reader.readLine());
                     System.out.println("Manager has been removed");
                     break;
                 case "10" :
                     removeManagerByLastname(reader.readLine());
                     System.out.println("Manager has been removed");
                     break;
                 case "11" :
                     removeWorkerByName(reader.readLine());
                     System.out.println("Worker has been removed");
                     break;
                 case "12" :
                     removeWorkerByLastname(reader.readLine());
                     System.out.println("Worker has been removed");
                     break;



             }

            } else { System.out.println("Error Input. Try again"); }


    }

        } else { System.out.println("Error Input. Try again"); }



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
