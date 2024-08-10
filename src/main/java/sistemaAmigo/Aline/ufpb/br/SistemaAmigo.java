package sistemaAmigo.Aline.ufpb.br;

import java.util.List;
import java.util.ArrayList;

public class SistemaAmigo {
    private List<Amigo> amigos;
    private List<Mensagem> mensagens;

    public SistemaAmigo() {
        amigos = new ArrayList<>();
        mensagens = new ArrayList<>();
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) {
        Amigo amigo = new Amigo(nomeAmigo, emailAmigo);
        amigos.add(amigo);
    }

    public Amigo pesquisaAmigo(String emailAmigo) {
        for (Amigo amigo : amigos) {
            if (amigo.getEmailAmigo().equals(emailAmigo)) {
                return amigo;
            }
        }
        return null;
    }

    public void enviarMensagemParaTodos(String emailRemetente, String texto, boolean ehAnonima) {
        MensagemParaTodos mensagem = new MensagemParaTodos(emailRemetente, texto, ehAnonima);
        mensagens.add(mensagem);
    }

    public void enviarMensagemParaAlguem(String emailRemetente, String emailDestinatario, String texto, boolean ehAnonima) {
        MensagemParaAlguem mensagem = new MensagemParaAlguem(emailRemetente, emailDestinatario, texto, ehAnonima);
        mensagens.add(mensagem);
    }

    public List<Mensagem> pesquisaMensagensAnonimas() {
        List<Mensagem> mensagensAnonimas = new ArrayList<>();
        for (Mensagem mensagem : mensagens) {
            if (mensagem.anonima) {
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
            pessoa.setNomeAmigo(emailAmigoSorteado);
        }
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException {
        Amigo pessoa = pesquisaAmigo(emailDaPessoa);
        if (pessoa == null) {
            throw new AmigoInexistenteException();
        } else {
            String amigoSecreto = pessoa.getNomeAmigo();
            if (amigoSecreto == null) {
                throw new AmigoNaoSorteadoException();
            }
            return amigoSecreto;
        }
    }
}






