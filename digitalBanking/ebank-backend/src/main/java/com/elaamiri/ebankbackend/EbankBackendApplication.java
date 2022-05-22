package com.elaamiri.ebankbackend;

import com.elaamiri.ebankbackend.entities.*;
import com.elaamiri.ebankbackend.entities.enumerations.AccountStatus;
import com.elaamiri.ebankbackend.entities.enumerations.OperationType;
import com.elaamiri.ebankbackend.repositories.AccountOperationRepository;
import com.elaamiri.ebankbackend.repositories.BankAccountRepository;
import com.elaamiri.ebankbackend.repositories.CustomerRepository;
import com.elaamiri.ebankbackend.services.interfaces.BankAccountService;
import com.elaamiri.ebankbackend.services.interfaces.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankBackendApplication.class, args);
    }

    //@Bean
    CommandLineRunner TestingDAO(CustomerRepository customerRepository,
                          BankAccountRepository bankAccountRepository,
                          AccountOperationRepository accountOperationRepository){
        return (args)->{
            Stream.of("Hassan", "Laila", "Samia", "Ali").forEach( name -> {
                Customer customer = new Customer();
                customer.setId(UUID.randomUUID().toString());
                customer.setName(name);
                customer.setEmail(name.concat("@gmail.com"));

                customerRepository.save(customer);

            });
            customerRepository.findAll().forEach( customer -> {
                // create a currentAccount and a savingAccount or each customer
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setCurrency("MDH");
                currentAccount.setBalance(Math.random()*5000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.values()[(int)(Math.random()*3)]);
                currentAccount.setOverDraft(2000);
                currentAccount.setCustomer(customer);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setCurrency("MDH");
                savingAccount.setBalance(Math.random()*5000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.values()[(int)(Math.random()*3)]);
                savingAccount.setInterestRate(Math.random()* 10);
                savingAccount.setCustomer(customer);

                bankAccountRepository.save(currentAccount);
                bankAccountRepository.save(savingAccount);
            });

            bankAccountRepository.findAll().forEach(bankAccount -> {
                // créer 3 opérations pour chaque compte
                for(int i=1; i<=3; i++){
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setBankAccount(bankAccount);
                    accountOperation.setOperationType(OperationType.values()[(int)(Math.random()*2)]);
                    accountOperation.setAmount(Math.random()*300);
                    accountOperation.setDate(new Date());

                    accountOperationRepository.save(accountOperation);
                }
            });
        };

    }

    //@Bean
    CommandLineRunner TestingService(CustomerService customerService, BankAccountService bankAccountService){
        return (args)->{
            // creating an customer
            Customer customer = new Customer();
            customer.setName("Hajji Ilham");
            customer.setEmail("email@emal.cm");
            customer = customerService.saveCustomer(customer);

            // update customer
            customer.setName("Hajji Ilham idrissi");
            customer = customerService.updateCustomer(customer);


            // creating an account
            BankAccount b1 = bankAccountService.saveCurrentAccount(79000,310, customer.getId());
            BankAccount b2 =  bankAccountService.saveSavingAccount(79000,3.10, customer.getId());

            // operations
            bankAccountService.applyOperation(b1.getId(),12990, OperationType.DEBIT, "Debit");
            bankAccountService.applyOperation(b1.getId(),1000, OperationType.CREDIT, "Credit");
            //bankAccountService.applyOperation(b1.getId(),-1000, OperationType.CREDIT, "Credit");

            bankAccountService.transfer(b1.getId(), b2.getId(), 23990);




        };

    }

}
