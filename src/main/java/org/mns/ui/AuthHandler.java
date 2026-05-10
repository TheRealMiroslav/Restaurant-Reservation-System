package org.mns.ui;

import org.mns.dto.LoginCredentials;
import org.mns.model.Customer;
import org.mns.service.CustomerService;

import java.util.Optional;
import java.util.Scanner;

import static org.mns.ui.CliHelper.*;

public class AuthHandler {
    private final Scanner sc;
    private final SessionContext session;
    private final CustomerService customerService;

    public AuthHandler(Scanner sc, SessionContext session, CustomerService customerService) {
        this.sc = sc;
        this.session = session;
        this.customerService = customerService;
    }

    public void handleLogin() {
        title("Přihlášení");

        System.out.print("E-mail: ");
        String email = sc.nextLine().trim();

        System.out.print("Heslo:  ");
        String password = sc.nextLine().trim();

        info("Zpracovávání přihlášení...");
        nextLine();

        try {
            Optional<Customer> customer = customerService.login(new LoginCredentials(email, password));

            if (customer.isEmpty()) {
                error("Nesprávný e-mail nebo heslo.");
                return;
            }

            session.setLoggedInUser(customer.get());
            success("Přihlášení úspěšné. Vítej, " + customer.get().getFirstName() + "!");

        } catch (Exception e) {
            error("Chyba při přihlášení: " + e.getMessage());
        }
    }

    public void handleRegistration() {
        title("Registrace nového účtu");

        System.out.print("Jméno:          ");
        String firstName = sc.nextLine().trim();

        System.out.print("Příjmení:       ");
        String lastName = sc.nextLine().trim();

        System.out.print("E-mail:         ");
        String email = sc.nextLine().trim();

        System.out.print("Telefon:        ");
        String phoneNumber = sc.nextLine().trim();

        System.out.print("Heslo:          ");
        String password = sc.nextLine().trim();

        System.out.print("Heslo znovu:    ");
        String passwordConfirm = sc.nextLine().trim();

        if (!password.equals(passwordConfirm)) {
            error("Hesla se neshodují. Registrace zrušena.");
            return;
        }

        if (firstName.isBlank() || email.isBlank() || password.isBlank()) {
            error("Jméno, e-mail a heslo jsou povinné údaje.");
            return;
        }

        nextLine();
        info("Zpracovávání registrace...");
        nextLine();

        try {
            customerService.register(firstName, lastName, email, phoneNumber, password);

            success("Registrace proběhla úspěšně. Nyní se přihlaste.");
        } catch (IllegalArgumentException e) {
            error(e.getMessage()); // duplicitní e-mail
        } catch (Exception e) {
            error("Chyba při registraci: " + e.getMessage());
        }
    }
}
