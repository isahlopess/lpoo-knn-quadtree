/**
 * @author Isadora Fernandes Lopes
 * @author Mariana Cardeliquio Mesquita
 */

package app.utils;

import java.util.Scanner;

public class Console {

    // Scanner estático para ser reutilizado por toda a aplicação sem fechar o System.in
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Exibe uma mensagem formatada no console, encapsulando o System.out.printf.
     */
    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    /**
     * Exibe um prompt de comando sem quebrar a linha.
     */
    public static void prompt(String message) {
        System.out.print(message);
    }

    /**
     * Lê uma opção do usuário, garantindo em um laço de repetição que a tecla
     * digitada pertença à string de caracteres válidos.
     */
    public static char readOption(String title, String validOptions) {
        String input;
        while (true) {
            prompt(title + "<" + validOptions + ">: ");
            input = scanner.nextLine().trim().toLowerCase();

            // Valida se o usuário digitou exatamente 1 caractere e se ele está nas opções permitidas
            if (input.length() == 1 && validOptions.toLowerCase().contains(input)) {
                return input.charAt(0);
            }
            
            System.out.println("Opção inválida! Tente novamente.\n");
        }
    }

    /**
     * Método auxiliar extra para ler cadeias de caracteres.
     */
    public static String readString(String title) {
        prompt(title + ": ");
        return scanner.nextLine().trim();
    }

    /**
     * Método auxiliar extra para ler atributos numéricos sem quebrar o programa.
     */
    public static float readFloat(String title) {
        while (true) {
            try {
                prompt(title + ": ");
                return Float.parseFloat(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Por favor, digite um número válido.");
            }
        }
    }
}