//package com.company.banko;
//
//
//import com.company.banko.domain.FinancialAccount;
//import com.company.banko.domain.Person;
//import com.company.banko.repository.PersonRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@SpringBootTest
//public class OneToManyAnnotationIntegrationTest2 {
//
//    @Autowired
//    private PersonRepository personRepository;
//
//
//    @Test
//    public void givenManyAccounts_WhenInsertPerson_ThenCreats1toManyRelationShip() {
//        Person person = new Person(1L, "mahdi", "khoddam", 123456);
//
//        Set<FinancialAccount> financialAccounts = new HashSet<>();
//        financialAccounts.add(new FinancialAccount(1l, 123456, "hesab"));
//
//        person.setFinancialAccounts(financialAccounts);
//        personRepository.save(person);
//    }
//}
