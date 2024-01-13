package learn.venus;

import learn.venus.data.OrbiterFileRepository;
import learn.venus.domain.OrbiterService;
import learn.venus.ui.Controller;
import learn.venus.ui.View;

public class App {
    public static void main(String[] args) {
        OrbiterFileRepository repository=new OrbiterFileRepository("./data/orbiters.csv");
        OrbiterService service=new OrbiterService(repository);

        View view=new View();

        Controller controller=new Controller(service,view);
        controller.run();
    }
}
