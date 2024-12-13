package service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class Banco {
    private List<Cliente> clientes;
    private static final String ARQUIVO_DADOS = "clientes.txt";

    public Banco() {
        this.clientes = new ArrayList<>();
        carregarDados(); // Carrega os dados ao iniciar
    }

    public boolean cadastrarCliente(String nome, String CPF, LocalDate dataNascimento) {
        for (Cliente cliente : clientes) {
            if (cliente.getCPF().equals(CPF)) {
                return false; // CPF já existe
            }
        }
        clientes.add(new Cliente(nome, CPF, dataNascimento));
        salvarDados(); // Salva os dados no arquivo após cadastrar
        return true;
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public Cliente consultarClientePorCPF(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCPF().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public boolean removerCliente(String cpf) {
        boolean removido = clientes.removeIf(cliente -> cliente.getCPF().equals(cpf));
        if (removido) {
            salvarDados(); // Salva os dados no arquivo após remover
        }
        return removido;
    }

    private void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DADOS))) {
            for (Cliente cliente : clientes) {
                writer.write(cliente.getNome() + ";" + cliente.getCPF() + ";" + cliente.getDataNascimento() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private void carregarDados() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_DADOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                String nome = partes[0];
                String cpf = partes[1];
                LocalDate dataNascimento = LocalDate.parse(partes[2]);
                clientes.add(new Cliente(nome, cpf, dataNascimento));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum arquivo encontrado. Iniciando com base de dados vazia.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}
