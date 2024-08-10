package sistemaAmigo.Aline.ufpb.br;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaAmigoMapTest {

    SistemaAmigoMap sistema;

    @BeforeEach
    void setUp() {
        this.sistema = new SistemaAmigoMap();
    }

    @Test
    void testSistemaAmigoMap() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        assertThrows(AmigoInexistenteException.class, () -> sistema.pesquisaAmigo("joao@teste.com"));
    }

    @Test
    void testPesquisaECadastraAmigo() {
        sistema.pesquisaAmigo("joao@teste.com");
        fail("Deveria falhar pois não existe ainda");
        sistema.cadastraAmigo("João", "joao@teste.com");
        Amigo a = sistema.pesquisaAmigo("joao@teste.com");
        assertEquals("João", a.getNomeAmigo());
        assertEquals("joao@teste.com", a.getEmailAmigo());
    }

    @Test
    void testEnviarMensagemParaTodos() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaTodos("texto", "joao@dcx.ufpb.br", true);
        List<Mensagem> mensagensAchadas = sistema.pesquisaTodasAsMensagens();
        assertTrue(mensagensAchadas.size() == 1);
        assertTrue(mensagensAchadas.get(0).getEmailRemetente().equals("joao@dcx.ufpb.br"));
    }

    @Test
    void testEnviarMensagemParaAlguem() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaAlguem("texto", "joao@dcx.ufpb.br", "maria@dcx.ufpb.br", true);
        List<Mensagem> mensagensAchadas = sistema.pesquisaTodasAsMensagens();
        assertEquals(1, mensagensAchadas.size());
        assertTrue(mensagensAchadas.get(0) instanceof MensagemParaAlguem);
        assertTrue(mensagensAchadas.get(0).getTexto().equals("texto"));
    }

    @Test
    void testPesquisaMensagensAnonimas() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaAlguem("texto 1", "joao@dcx.ufpb.br", "maria@dcx.ufpb.br", false);
        assertTrue(sistema.pesquisaMensagensAnonimas().isEmpty());
        sistema.enviarMensagemParaAlguem("texto 2", "joao@dcx.ufpb.br", "maria@dcx.ufpb.br", true);
        assertTrue(sistema.pesquisaMensagensAnonimas().size() == 1);
    }

    @Test
    void testPesquisaTodasAsMensagens() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaAlguem("texto 1", "joao@dcx.ufpb.br", "maria@dcx.ufpb.br", false);
        assertTrue(sistema.pesquisaTodasAsMensagens().size() == 1);
        sistema.enviarMensagemParaAlguem("texto 2", "joao@dcx.ufpb.br", "maria@dcx.ufpb.br", true);
        assertTrue(sistema.pesquisaTodasAsMensagens().size() == 2);
    }

    @Test
    void testPesquisaAmigoEConfiguraAmigoSecretoDe() {
        assertThrows(AmigoInexistenteException.class, () -> sistema.pesquisaAmigoSecretoDe("joao@dcx.ufpb.br"));
        try {
            sistema.cadastraAmigo("João", "joao@dcx.ufpb.br");
            sistema.cadastraAmigo("Maria", "maria@dcx.ufpb.br");
            sistema.configuraAmigoSecretoDe("joao@dcx.ufpb.br", "maria@dcx.ufpb.br");
            sistema.configuraAmigoSecretoDe("maria@dcx.ufpb.br", "joao@dcx.ufpb.br");
            assertEquals("maria@dcx.ufpb.br", sistema.pesquisaAmigoSecretoDe("joao@dcx.ufpb.br"));
            assertEquals("joao@dcx.ufpb.br", sistema.pesquisaAmigoSecretoDe("maria@dcx.ufpb.br"));
        } catch (AmigoInexistenteException | AmigoNaoSorteadoException e) {
            fail("Não deveria lançar exceção");
        }
    }

}