package com.example.demo.config;

import com.example.demo.models.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private AccountRepository accountRepository;

    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            System.out.println("Preenchendo categorias...");
            Category cat01 = new Category();
            cat01.setName("Alimentação");
            Category cat02 = new Category();
            cat02.setName("Transporte");
            Category cat03 = new Category();
            cat03.setName("Comunicação");
            Category cat04 = new Category();
            cat04.setName("Lazer");
            categoryRepository.saveAll(Arrays.asList(cat01,cat02,cat03,cat04));
            System.out.println("Categorias Preenchidas com sucesso!");
        }
        if (roleRepository.count() == 0) {
            System.out.println("Preenchendo roles...");
            Role role01 = new Role();
            role01.setName(RoleName.ROLE_CUSTOM);
            Role role02 = new Role();
            role02.setName(RoleName.ROLE_ADMIN);
            roleRepository.saveAll(Arrays.asList(role01,role02));
            System.out.println("Roles Preenchidas com sucesso!");
        }
        if (userRepository.count() == 0) {
            System.out.println("Preenchendo users...");
            User user01 = new User();
            user01.setName("usuario teste 01");
            user01.setCpf("12365478900");
            user01.setEmail("testeUser1@email.com");
            user01.setPassword(passwordEncoder.encode("senhaTeste"));
            User user02 = new User();
            user02.setName("usuario teste 02");
            user02.setCpf("12365478900");
            user02.setEmail("testeUser2@email.com");
            user02.setPassword(passwordEncoder.encode("senhaTeste"));
            userRepository.saveAll(Arrays.asList(user01,user02));
            System.out.println("Usuarios Preenchidos com sucesso!");
        }
        if (bankRepository.count() == 0) {
            System.out.println("Preenchendo banks...");
            Bank bank01 = new Bank();
            bank01.setName("PicPay");
            Bank bank02 = new Bank();
            bank02.setName("Nubank");
            Bank bank03 = new Bank();
            bank03.setName("Santander");
            bankRepository.saveAll(Arrays.asList(bank01,bank02,bank03));
            System.out.println("Banks Preenchidos com sucesso!");
        }
        if (accountRepository.count() == 0) {
            System.out.println("Preenchendo accounts...");
            Account account01 = new Account();
            account01.setAccountType(AccountType.CHECKING_ACCOUNT);
            account01.setBank(bankRepository.getReferenceById(3L));
            account01.setDescription("Reserva de emergência");
            account01.setCurrentBalance(BigDecimal.valueOf(1000));
            account01.setUser(userRepository.getReferenceById(1L));
            Account account02 = new Account();
            account02.setAccountType(AccountType.CHECKING_ACCOUNT);
            account02.setBank(bankRepository.getReferenceById(2L));
            account02.setDescription("Conta padrão");
            account02.setCurrentBalance(BigDecimal.valueOf(700));
            account02.setUser(userRepository.getReferenceById(2L));
            Account account03 = new Account();
            account03.setAccountType(AccountType.CHECKING_ACCOUNT);
            account03.setBank(bankRepository.getReferenceById(1L));
            account03.setDescription("Gastos com carro");
            account03.setCurrentBalance(BigDecimal.valueOf(1000));
            account03.setUser(userRepository.getReferenceById(2L));
            accountRepository.saveAll(Arrays.asList(account01,account02,account03));
            System.out.println("Accounts Preenchidas com sucesso!");

        }
    }
}
