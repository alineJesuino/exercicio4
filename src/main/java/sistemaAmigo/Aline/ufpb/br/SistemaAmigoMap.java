package sistemaAmigo.Aline.ufpb.br;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SistemaAmigoMap {
    private Map<String, Amigo> amigos;
    private List<Mensagem> mensagens;

    public SistemaAmigoMap() {
        amigos = new HashMap<>();
        mensagens = new ArrayList<>();
    }

    public void cadastraAmigo(String nome, String email) {
        Amigo amigo = new Amigo(nome, email);
        amigos.put(email, amigo);
    }
    public Amigo pesquisaAmigo(String email) {
        return amigos.get(email);
    }

    public void enviarMensagemParaTodos(String remetente, String texto, boolean anonima) {
        MensagemParaTodos mensagem = new MensagemParaTodos(remetente, texto, anonima);
        mensagens.add(mensagem);
    }

    public void enviarMensagemParaAlguem(String remetente, String destinatario, String texto, boolean anonima) {
        MensagemParaAlguem msg = new MensagemParaAlguem(remetente, destinatario, texto, anonima);
        mensagens.add(msg);
    }

    public List<Mensagem> pesquisaMensagensAnonimas() {
        List<Mensagem> mensagensAnonimas = new ArrayList<>();
        for (Mensagem mensagem : mensagens) {
            if (mensagem.getAnonima()) {
                mensagensAnonimas.add(mensagem);
            }
        }
        return mensagensAnonimas;
    }

    public List<Mensagem> pesquisaTodasAsMensagens() {
        return mensagens;
    }
    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException {
        Amigo pessoa = pesquisaAmigo(emailDaPessoa);
        if (pessoa == null) {
            throw new AmigoInexistenteException();
        } else {
            pessoa.setEmailDoAmigoSorteado(emailAmigoSorteado);
        }
    }
    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException {
        Amigo pessoa = pesquisaAmigo(emailDaPessoa);
        if (pessoa == null) {
            throw new AmigoInexistenteException();
        } else {
            String amigoSecreto = pessoa.getEmailDoAmigoSorteado();
            if (amigoSecreto == null) {
                throw new AmigoNaoSorteadoException();
            }
            return amigoSecreto;
        }
    }
}

