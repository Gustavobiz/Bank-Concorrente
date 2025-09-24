public class Transferencia implements Runnable {


    private final Conta contaOrigem;   
    private final Conta contaDestino;  
    private final double valor;        

   
    public Transferencia(Conta contaOrigem, Conta contaDestino, double valor) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
    }

   
    @Override
    public void run() {

    }
}
