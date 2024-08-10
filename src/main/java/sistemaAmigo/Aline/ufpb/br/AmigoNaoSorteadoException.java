package sistemaAmigo.Aline.ufpb.br;

public class AmigoNaoSorteadoException extends Throwable {
    public class AmigoSorteadoException extends Exception {
    }
    public AmigoNaoSorteadoException() {
        super("O amigo secreto ainda não foi sorteado para este participante.");
    }
    public AmigoNaoSorteadoException(String msg) {
        super(msg);
    }

}
