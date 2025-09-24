import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Conta {


    private double saldo;         
    private final int id;         
    private final Lock lock = new ReentrantLock(); 


    public Conta(int id, double saldoInicial) {
        this.id = id;
        this.saldo = saldoInicial;
    }

   
    public void sacar(double valor) {
        saldo -= valor;
    }


    public void depositar(double valor) {
        saldo += valor;
    }
    

    public int getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    // Getter essencial: expõe o "cadeado" para que a tarefa de transferência possa usá-lo
    public Lock getLock() {
        return lock;
    }
}