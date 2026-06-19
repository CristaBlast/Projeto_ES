import org.junit.jupiter.api.Test;
import pt.futfever.model.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BilheteTest {


    @Test
    void testCompraBilhete() {
        GestorDados repo = GestorDados.getInstance();
        repo.popularDados();

        Selecao p = repo.procurarSelecaoPorId(1);
        Selecao e = repo.procurarSelecaoPorId(2);

        Estadio estadio = new Estadio(1, "Teste", 0, 0, 100);
        Jogo jogo = new Jogo(1, new Date(), p, e, estadio);


        Gestor gestor = new Gestor(1, "Ana", "a", "admin", "1234");
        gestor.abrirVendaBilhetes(jogo);

        UtilizadorPublico user = new UtilizadorPublico(1, "Cris", "x", "123", "999");

        Bilhete b = user.comprarBilhete(jogo, 2, "123", "999");

        assertNotNull(b);
    }
    }
