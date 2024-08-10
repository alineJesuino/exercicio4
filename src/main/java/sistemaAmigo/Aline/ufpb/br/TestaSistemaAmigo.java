package sistemaAmigo.Aline.ufpb.br;

import java.util.List;

public class TestaSistemaAmigo {

    public static void main(String[] args) {
        SistemaAmigo sistema = new SistemaAmigo();
        sistema.cadastraAmigo("José", "jose@jose.com");
        sistema.cadastraAmigo("Maria", "maria@maria.com");

        try {

            sistema.configuraAmigoSecretoDe("jose@email.com", "maria@email.com");
            sistema.configuraAmigoSecretoDe("maria@email.com", "jose@email.com");
        } catch (AmigoInexistenteException e) {
            System.out.println("Um dos amigos não foi encontrado.");
        }

        sistema.enviarMensagemParaAlguem("Maria", "jose@email.com", "Olá José!", true);

        sistema.enviarMensagemParaTodos("Maria", "Olá pessoal!", true);


        List<Mensagem> mensagensAnonimas = sistema.pesquisaMensagensAnonimas();
        for (Mensagem mensagem : mensagensAnonimas) {
            System.out.println(mensagem.getTextoCompletoAExibir());
        }


        try {
            String amigoSecretoDeJose = sistema.pesquisaAmigoSecretoDe("jose@email.com");
            if (amigoSecretoDeJose.equals("maria@email.com")) {
                System.out.println("Ok");
            }
        } catch (AmigoInexistenteException | AmigoNaoSorteadoException e) {
            System.out.println("Exceção estranha foi lançada" + e.getMessage());
        }
    }
}