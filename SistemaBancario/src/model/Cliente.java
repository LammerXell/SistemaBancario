package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String CPF;
    private LocalDate dataNascimento;
    private List<Conta> contas;

    public Cliente(String nome, String CPF, LocalDate dataNascimento) {
        this.nome = nome;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.contas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return CPF;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
    }

    public void removerConta(Conta conta) {
        this.contas.remove(conta);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", CPF='" + CPF + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", contas=" + contas.size() +
                '}';
    }
}
