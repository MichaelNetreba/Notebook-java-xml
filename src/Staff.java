import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Staff {

    private List<Worker> worker = new ArrayList<>();

    private List<Manager> manager = new ArrayList<>();

    public List<Worker> getWorker() {
        return worker;
    }

    @XmlElement
    public void setWorker(List<Worker> workers) {
        this.worker = workers;
    }


    public List<Manager> getManager() {
        return manager;
    }

    @XmlElement
    public void setManager(List<Manager> managers) {
        this.manager = managers;
    }

}
