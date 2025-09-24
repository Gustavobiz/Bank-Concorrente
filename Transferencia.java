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
    
        Conta primeiroLock = contaOrigem.getId() < contaDestino.getId() ? contaOrigem : contaDestino;
        Conta segundoLock = contaOrigem.getId() < contaDestino.getId() ? contaDestino : contaOrigem;


        System.out.println(Thread.currentThread().getName() + " tentando pegar o lock da conta " + primeiroLock.getId());
        primeiroLock.getLock().lock();
        System.out.println(Thread.currentThread().getName() + " PEGOU o lock da conta " + primeiroLock.getId());

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(Thread.currentThread().getName() + " tentando pegar o lock da conta " + segundoLock.getId());
        segundoLock.getLock().lock();
        System.out.println(Thread.currentThread().getName() + " PEGOU o lock da conta " + segundoLock.getId());


        try {
            System.out.println(Thread.currentThread().getName() + " realizando a transferência...");
            contaOrigem.sacar(valor);
            contaDestino.depositar(valor);
            System.out.println("--- Transferência concluída com sucesso por " + Thread.currentThread().getName() + " ---");
        } finally {
      
            segundoLock.getLock().unlock();
            primeiroLock.getLock().unlock();
            System.out.println(Thread.currentThread().getName() + " liberou os locks.");
        }
    }
}