package learn.venus.data;
import learn.venus.models.Orbiter;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrbiterFileRepositoryTest  {
    private OrbiterFileRepository repository=new OrbiterFileRepository("./data/orbiters.csv");

    @Test
    void shouldFindFiveOrbiters(){
        List<Orbiter> actual = repository.findAll();
        assertNotNull(actual);

        assertEquals(5,actual.size());
    }
}