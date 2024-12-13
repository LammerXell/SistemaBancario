package model;

public class Conta {
    private static int contador = 1; 
    private int numeroConta;
    private String tipoConta; 
    private double saldo;
    private Cliente cliente;

    public Conta(String tipoConta, Cliente cliente) {
        this.numeroConta = contador++;
        this.tipoConta = tipoConta;
        this.saldo = 0.0;
        this.cliente = cliente;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
        } else {
            throw new IllegalArgumentException("Saldo insuficiente ou valor inválido.");
        }
    }

    public void transferir(Conta contaDestino, double valor) {
        if (valor > 0 && saldo >= valor) {
            this.sacar(valor);
            contaDestino.depositar(valor);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente ou valor inválido.");
        }
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numeroConta=" + numeroConta +
                ", tipoConta='" + tipoConta + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}

