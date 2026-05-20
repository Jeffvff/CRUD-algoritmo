package Application;

import java.util.Locale;
import java.util.Scanner;


public class Atividades {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int menuInicial;

        do {
            System.out.printf("[1] - adicionar compromisso%n[0] - voltar%n");
            System.out.print("Selecione: ");
            menuInicial = sc.nextInt();
            sc.nextLine();

            switch (menuInicial) {
                case 1:









                    break;
                case 0:
                    System.out.println("voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção invalida. tente novamente!!");
                    break;
            }
        } while(menuInicial != 0);

        sc.close();
    }
}
