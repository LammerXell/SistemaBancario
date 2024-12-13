package com.sistemabancario;

import model.Cliente;
import service.Banco;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("   _____ _     _                         ____                             _       \r\n"
        		+ "  / ____(_)   | |                       |  _ \\                           (_)      \r\n"
        		+ " | (___  _ ___| |_ ___ _ __ ___   __ _  | |_) | __ _ _ __   ___ __ _ _ __ _  ___  \r\n"
        		+ "  \\___ \\| / __| __/ _ \\ '_ ` _ \\ / _` | |  _ < / _` | '_ \\ / __/ _` | '__| |/ _ \\ \r\n"
        		+ "  ____) | \\__ \\ ||  __/ | | | | | (_| | | |_) | (_| | | | | (_| (_| | |  | | (_) |\r\n"
        		+ " |_____/|_|___/\\__\\___|_| |_| |_|\\__,_| |____/ \\__,_|_| |_|\\___\\__,_|_|  |_|\\___/ \r\n"
        		+ "                                                                                  \r\n"
        		+ "                                                                                  ");
        System.out.println("Bem-vindo ao Sistema Bancário!");

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcao) {
                case 1:
                    cadastrarCliente(banco, scanner);
                    break;
                case 2:
                    banco.listarClientes();
                    break;
                case 3:
                    consultarCliente(banco, scanner);
                    break;
                case 4:
                    removerCliente(banco, scanner);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Consultar Cliente por CPF");
        System.out.println("4. Remover Cliente");
        System.out.println("0. Sair");
    }

    private static void cadastrarCliente(Banco banco, Scanner scanner) {
        System.out.println("\n=== Cadastrar Cliente ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String dataNascimento = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Tenta converter a data para LocalDate
            LocalDate data = LocalDate.parse(dataNascimento, formatter);

            if (banco.cadastrarCliente(nome, cpf, data)) {
                System.out.println("Cliente cadastrado com sucesso!");
            } else {
                System.out.println("Erro: Cliente com o CPF já existe.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Erro: Formato de data inválido! Use o formato dd/MM/yyyy.");
        }
    }

    private static void consultarCliente(Banco banco, Scanner scanner) {
        System.out.println("\n=== Consultar Cliente ===");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = banco.consultarClientePorCPF(cpf);
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void removerCliente(Banco banco, Scanner scanner) {
        System.out.println("\n=== Remover Cliente ===");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        if (banco.removerCliente(cpf)) {
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Erro: Cliente não encontrado.");
        }
    }
}
