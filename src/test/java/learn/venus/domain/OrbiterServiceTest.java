package learn.venus.domain;

import learn.venus.data.DataAccessException;
import learn.venus.data.OrbiterRepositoryDouble;
import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrbiterServiceTest {
    OrbiterService service = new OrbiterService(new OrbiterRepositoryDouble());
    @Test
    void shouldAddOrbiter(){

    }

    @Test
    void shouldNotAddNullOrbiter() throws DataAccessException {
        OrbiterResult result = service.add(null);
        assertFalse(result.isSuccess());
    }

    @Test
    void shoudNotAddAstroWithNoRoom() throws DataAccessException{
        OrbiterResult result = service.add(new Orbiter(0, "Test Astro", OrbiterType.ASTRONAUT,null));
        assertFalse(result.isSuccess());
    }

}