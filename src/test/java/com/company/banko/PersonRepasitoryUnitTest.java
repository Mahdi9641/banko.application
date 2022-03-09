/*
package com.company.banko;

import com.company.banko.domain.Person;
import com.company.banko.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PersonRepasitoryUnitTest {

    final String LAST_NAME = "khodam_2";
    final String FIRST_NAME = "MOBIN_2";

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void givenSavedPerson_WhenUpdate_ThenUpdatePerson() {


        personRepository.save(new Person(1l, "mahdi", "khoddam", 123456));

        Person person = personRepository.findPartyByNationalNumber(123456);
        person.setFirstName("mobin");
        person.setLastName("khoddam");
        person.setNationalNumber(78910);
        personRepository.save(person);

        assertThat(personRepository.findPartyByNationalNumber(78910).getFirstName()).isEqualTo(FIRST_NAME);


    }

    @Test
    public void givenSavedPerson_WhenDelete_ThenDeletePerson() {

        personRepository.save(new Person(1L, "mahdi", "khoddam", 123456));

        personRepository.deleteById(1l);

        Boolean percent = personRepository.findById(1l).isPresent();
        assertThat(percent).isEqualTo(false);
    }

    @Test
    public void givenPersonsInDB_whenFindAllWithSortByLastName_ThenReturnPersonsSorted() {
        personRepository.save(new Person(2l, "mahdi", "khoddam", 123456));
        personRepository.save(new Person(3l, "mobin", "khodami", 78910));
        personRepository.save(new Person(4l, "ali", "khoddamali", 11121314));

        List<Person> PersonsSortedByLastName = personRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));

        assertThat(PersonsSortedByLastName.get(0).getLastName()).isEqualTo(LAST_NAME);
    }

    @Test
    public void givenPersonsInDB_whenFindAllWithPageRequestQuery_ThenReturnPageOfPersons() {
        personRepository.save(new Person(5l, "mahdi", "khoddami", 123456));
        personRepository.save(new Person(6l, "mobin", "khoddamaa", 78910));
        personRepository.save(new Person(6l, "ali", "khoddameeee", 11121314));
        personRepository.save(new Person(7l, "mohsen", "khoddamlll", 11121314));
        personRepository.save(new Person(8l, "mamad", "khoddamkkkk", 11121314));

        Page<Person> personPage = personRepository.findAll(PageRequest.of(0, 4));

        assertThat(personPage.getContent()
                .get(0)
                .getLastName()).isEqualTo(LAST_NAME);


    }
}

 */
