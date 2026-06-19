import org.junit.jupiter.api.Test;
import pt.futfever.model.Gestor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class GestorTest {

        @Test
        void loginSucesso() {
            Gestor g = new Gestor(1, "Ana", "a@mail.pt", "admin", "1234");

            assertTrue(g.login("admin", "1234"));
        }

        @Test
        void loginFalha() {
            Gestor g = new Gestor(1, "Ana", "a@mail.pt", "admin", "1234");

            assertFalse(g.login("admin", "errado"));
        }
    }

