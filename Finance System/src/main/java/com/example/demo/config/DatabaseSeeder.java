package com.example.demo.config;

import com.example.demo.models.*;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("postg")
public class DatabaseSeeder implements CommandLineRunner {

    Faker faker=new Faker();

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
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;
    @Autowired
    private TransactionTypeRepository transactionTypeClassRepository;

    @Override
    @Transactional
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
            Category cat05=new Category();
            cat05.setName("Saúde");
            Category cat06=new Category();
            cat06.setName("Educação");
            Category cat08=new Category();
            cat08.setName("Salário");
            Category cat09=new Category();
            cat09.setName("Rendimentos");
            Category cat10=new Category();
            cat10.setName("Freelance");
            Category cat07=new Category();
            cat07.setName("Outros");
            categoryRepository.saveAll(Arrays.asList(cat01,cat02,cat03,cat04,cat05,cat06,cat07, cat08,cat09,cat10));
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
            user01.setName("Pedro Luan Moraes");
            user01.setCpf("45698712300");
            user01.setEmail("pedroluanmo44@email.com");
            LocalDate birthday01=LocalDate.of(2002,8,12);
            user01.setPassword(passwordEncoder.encode("senhaTeste"));
            user01.setRoles(roleRepository.getByName(RoleName.ROLE_CUSTOM));
            User user02 = new User();
            user02.setName("Luciana Brito dos Santos");
            user02.setCpf("12365478900");
            user02.setEmail("lucibritostts@email.com");
            LocalDate birthday02=LocalDate.of(1999,5,23);
            user02.setBirthday(birthday02);
            user02.setPassword(passwordEncoder.encode("senhaTeste"));
            user02.setRoles(roleRepository.getByName(RoleName.ROLE_CUSTOM));
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
            Bank bank04=new Bank();
            bank04.setName("Banco do Brasil");
            bankRepository.saveAll(Arrays.asList(bank01,bank02,bank03,bank04));
            System.out.println("Banks Preenchidos com sucesso!");
        }
        if (accountRepository.count() == 0) {
            System.out.println("Preenchendo accounts...");
            Account account01 = new Account();
            account01.setAccountType(AccountType.WALLET);
            account01.setBank(bankRepository.getReferenceById(1L));
            account01.setDescription("Reserva de Emergência");
            account01.setCurrentBalance(BigDecimal.valueOf(1000));
            account01.setUser(userRepository.getReferenceById(1L));
            Account account02 = new Account();
            account02.setAccountType(AccountType.INVESTMENT);
            account02.setBank(bankRepository.getReferenceById(2L));
            account02.setDescription("Conta Nubank");
            account02.setCurrentBalance(BigDecimal.valueOf(500));
            account02.setUser(userRepository.getReferenceById(2L));
            Account account03 = new Account();
            account03.setAccountType(AccountType.CHECKING_ACCOUNT);
            account03.setBank(bankRepository.getReferenceById(3L));
            account03.setDescription("Conta de Débito");
            account03.setCurrentBalance(BigDecimal.valueOf(1000));
            account03.setUser(userRepository.getReferenceById(1L));
            Account account04=new Account();
            account04.setAccountType(AccountType.CHECKING_ACCOUNT);
            account04.setBank(bankRepository.getReferenceById(4L));
            account04.setDescription("Conta de Débito");
            account04.setCurrentBalance(BigDecimal.valueOf(800));
            account04.setUser(userRepository.getReferenceById(2L));

            accountRepository.saveAll(Arrays.asList(account01,account02,account03,account04));
            System.out.println("Accounts Preenchidas com sucesso!");

        }
        if(transactionTypeRepository.count() == 0) {
            System.out.println("Preenchendo transactions...");
            TransactionTypeClass transactionTypeClass01 = new TransactionTypeClass();
            transactionTypeClass01.setName(TransactionType.INCOME);
            TransactionTypeClass transactionTypeClass02 = new TransactionTypeClass();
            transactionTypeClass02.setName(TransactionType.EXPENSE);
            transactionTypeRepository.saveAll(Arrays.asList(transactionTypeClass01,transactionTypeClass02));
            System.out.println("TransactionsTypes Preenchidas com sucesso!");
        }
        if(transactionRepository.count() == 0) {
            System.out.println("Preenchendo transactions...");
            Transaction transaction01 = new Transaction();
            transaction01=criarTransacao("Uber Curso", 25, 1L, 3L, 2L, 2L);
            Transaction transaction02=new Transaction();
            transaction02=criarTransacao("Remédio", 125, 2L, 4L, 2L, 5L);
            transactionRepository.saveAll(Arrays.asList(transaction01, transaction02));
        }

        var users=userRepository.findAll();
        var accounts=accountRepository.findAll();
        var categories=categoryRepository.findAll();
        var names=transactionRepository.findAll();

        if(users.isEmpty()||accounts.isEmpty()||categories.isEmpty()){
            System.out.println("Sem dados Banco...");
            return;
        }

        List<Transaction> randomTransactions=new ArrayList<>();
        int totalTransactions=150;

        System.out.println("Generating random transactions...");
        var typeClass=transactionTypeClassRepository.findAll();
        for (int i = 0; i < totalTransactions; i++) {
            Transaction t=new Transaction();
            var selectedUser=users.get(faker.random().nextInt(users.size()));
            var selectedAccount=accounts.get(faker.random().nextInt(accounts.size()));
            var selectedCategory=categories.get(faker.random().nextInt(categories.size()));
            var selectedTypeClass=typeClass.get(faker.random().nextInt(typeClass.size()));
            //Sortea 50%
            TransactionType selectedType=faker.bool().bool()?TransactionType.INCOME:TransactionType.EXPENSE;

            String name = faker.commerce().productName();
            String description = "Referente a compra de " + faker.commerce().department().toLowerCase();
            double doubleValue=faker.number().randomDouble(2, 5, 1500);
            BigDecimal value=BigDecimal.valueOf(doubleValue);
            long daysGone=faker.number().numberBetween(0, 180);
            LocalDateTime dateTime=LocalDateTime.now().minusDays(daysGone);

            t.setName(name);
            t.setDescription(description);
            t.setValue(value);
            t.setDateTime(dateTime);
            t.setTransactionType(selectedTypeClass);
            t.setUser(selectedUser);
            t.setAccount(selectedAccount);
            t.setCategory(selectedCategory);

            randomTransactions.add(t);
        }
        transactionRepository.saveAll(randomTransactions);

    }

    private Transaction criarTransacao(String name, double value, Long userId, Long accountId, Long typeId, Long categoryId) {
        Transaction transaction = new Transaction();
        transaction.setName(name);
        transaction.setValue(BigDecimal.valueOf(value));
        transaction.setDateTime(LocalDateTime.now());
        transaction.setDescription("");
        transaction.setUser(userRepository.getReferenceById(userId));
        transaction.setAccount(accountRepository.getReferenceById(accountId));
        transaction.setTransactionType(transactionTypeRepository.getReferenceById(typeId));
        transaction.setCategory(categoryRepository.getReferenceById(categoryId));

        return transaction;
    }
}
