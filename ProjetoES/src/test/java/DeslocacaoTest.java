import org.junit.jupiter.api.Test;
import pt.futfever.model.Deslocacao;
import pt.futfever.model.GestorDados;
import pt.futfever.model.Selecao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class DeslocacaoTest {

    @Test
    void testCriarDeslocacao() {

        // setup
        GestorDados repo = GestorDados.getInstance();
        repo.popularDados();

        Selecao espanha = repo.procurarSelecaoPorId(2);

        Date data = new Date();

        Deslocacao d = new Deslocacao(
                1,
                "Madrid",
                "Lisboa",
                data,
                espanha
        );

        // testes
        assertNotNull(d);
        assertEquals("Madrid", d.getOrigem());
        assertEquals("Lisboa", d.getDestino());
        assertEquals(espanha, d.getSelecao());
    }
}