package sistemaAmigo.Aline.ufpb.br;

 public class MensagemParaTodos extends Mensagem {

        public MensagemParaTodos(String remetente, String texto, boolean anonima) {
            super(remetente, anonima, texto);
            this.texto = texto;
        }

        @Override
        public String getTextoCompletoAExibir() {
            if (super.anonima) {
                return "Mensagem para todos. Texto: " + texto;
            } else {
                return "Mensagem de " + super.getEmailRemetente() + " para todos. Texto: " + texto;
            }
        }
     }


