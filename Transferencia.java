import java.util.concurrent.TimeUnit;

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
        
        System.out.println(Thread.currentThread().getName() + " tentando pegar o lock da conta " + contaOrigem.getId());
        contaOrigem.getLock().lock();
        System.out.println(Thread.currentThread().getName() + " PEGOU o lock da conta " + contaOrigem.getId());

        // Ponto crítico para o dealock 
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    

    
        System.out.println(Thread.currentThread().getName() + " tentando pegar o lock da conta " + contaDestino.getId());
        contaDestino.getLock().lock();
        System.out.println(Thread.currentThread().getName() + " PEGOU o lock da conta " + contaDestino.getId());

        try {
            System.out.println(Thread.currentThread().getName() + " realizando a transferência...");
            contaOrigem.sacar(valor);
            contaDestino.depositar(valor);
            System.out.println("--- Transferência concluída com sucesso por " + Thread.currentThread().getName() + " ---");
            System.out.println("Saldo Conta " + contaOrigem.getId() + ": " + contaOrigem.getSaldo());
            System.out.println("Saldo Conta " + contaDestino.getId() + ": " + contaDestino.getSaldo());
        } finally {
            contaDestino.getLock().unlock();
            contaOrigem.getLock().unlock();
            System.out.println(Thread.currentThread().getName() + " liberou os locks.");
        }
    }
}