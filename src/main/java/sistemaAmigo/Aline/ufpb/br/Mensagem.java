package sistemaAmigo.Aline.ufpb.br;

public abstract class Mensagem {
        protected String texto;
        protected String remetente;
        protected boolean anonima;

        public Mensagem(String remetente, boolean anonima, String texto) {
            this.remetente = remetente;
            this.anonima = anonima;
            this.texto = texto;
        }

        public abstract String getTextoCompletoAExibir();

     public boolean getAnonima() {
         return false;
      }

    protected String getEmailRemetente() {
         return remetente;
    }

    public String getTexto() {
         return texto;
    }
}

    // Subclasse MensagemParaAlguem
    class MensagemParaAlguem extends Mensagem {
        private String destinatario;
        private String texto;

        public MensagemParaAlguem(String remetente, String destinatario, String texto, boolean anonima) {
            super(remetente, anonima, texto);
            this.destinatario = destinatario;
            this.texto = texto;
        }

        @Override
        public String getTextoCompletoAExibir() {
            if (anonima) {
                return "Mensagem para " + destinatario + ". Texto: " + texto;
            } else {
                return "Mensagem de: " + remetente + " para " + destinatario + ". Texto: " + texto;
            }
        }
    }


