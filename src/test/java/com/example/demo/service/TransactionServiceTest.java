    package com.example.demo.service;

    import com.example.demo.dto.TransactionDTO;
    import com.example.demo.models.*;
    import com.example.demo.repository.AccountRepository;
    import com.example.demo.repository.CategoryRepository;
    import com.example.demo.repository.TransactionRepository;
    import com.example.demo.repository.TransactionTypeRepository;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.DisplayName;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.junit.jupiter.MockitoExtension;
    import java.math.BigDecimal;
    import java.time.LocalDateTime;

    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.Assertions.assertNotNull;
    import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    public class TransactionServiceTest {
        @Mock
        private TransactionRepository transactionRepository;

        @Mock
        private AccountRepository accountRepository;

        @InjectMocks
        private TransactionService transactionService;

        @Mock
        private AuthenticationService authenticationService;

        @Mock
        private TransactionTypeRepository transactionTypeRepository;

        @Mock
        private CategoryRepository categoryRepository;

        private User authUser;
        private Bank userBank;
        private Account userAccount;
        private Category userCategory;

        @BeforeEach
        void setUp(){
            authUser=new User();
            authUser.setId(1L);
            authUser.setEmail("user@teste.com");

            userBank=new Bank();
            userBank.setId(2L);
            userBank.setName("Inter");

            userAccount=new Account();
            userAccount.setId(10L);
            userAccount.setDescription("Conta Teste");
            userAccount.setCurrentBalance(new BigDecimal("1000.00"));
            userAccount.setAccountType(AccountType.WALLET);
            userAccount.setBank(userBank);
            userAccount.setUser(authUser);

            userCategory=new Category();
            userCategory.setId(5L);
            userCategory.setName("Transporte");

        }

        @Test
        @DisplayName("Deve subtrair valor do saldo da conta ao criar uma transação de débito(EXPENSE)")
        void deveSubtrairValorDoSaldoDaContaAoCriarUmaTransacaoDeDebito() {
            TransactionDTO.CreateTransactionDTO createDTO=new TransactionDTO.CreateTransactionDTO(
                "Transporte", "Descrição", new BigDecimal("50.00"), LocalDateTime.parse("2026-08-15T23:44:00"), TransactionType.EXPENSE, userCategory.getName(), userAccount.getId());

            when(authenticationService.getLoggedUser()).thenReturn(authUser);

            when(accountRepository.findByIdAndUser(10L, authUser)).thenReturn(java.util.Optional.of(userAccount));

            when(transactionTypeRepository.findByName(TransactionType.EXPENSE)).thenReturn(java.util.Optional.of(new TransactionTypeClass(TransactionType.EXPENSE)));

            when(categoryRepository.findByNameAndUserOrNameAndUserIsNull(userCategory.getName(), authUser, userCategory.getName()))
                    .thenReturn(java.util.Optional.of(userCategory));

            when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

            TransactionDTO.ResponseTransactionDTO response = transactionService.createTransaction(createDTO);

            assertNotNull(response);

            assertEquals(new BigDecimal("950.00"), userAccount.getCurrentBalance());

            verify(accountRepository, times(1)).findByIdAndUser(10L, authUser);
            verify(transactionRepository, times(1)).save(any(Transaction.class));
        }

    }
