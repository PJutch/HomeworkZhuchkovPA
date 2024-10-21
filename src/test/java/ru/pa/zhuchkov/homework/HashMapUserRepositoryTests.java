package ru.pa.zhuchkov.homework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class HashMapUserRepositoryTests {
    @Test
    void putAndFind() {
        UserRepository userRepository = new HashMapUserRepository();
        userRepository.updateUserByMsisdn("88005553535",
                new UserRepository.User("Vasya", "Ivanov"));
        userRepository.updateUserByMsisdn("88005553727",
                new UserRepository.User("Petya", "Petrov"));

        final var expected = new UserRepository.User("Vasya", "Ivanov");
        final var actual = userRepository.findByMsisdn("88005553535");

        assertEquals(expected, actual);
    }

    @Test
    void findNotExisting() {
        UserRepository userRepository = new HashMapUserRepository();
        userRepository.updateUserByMsisdn("88005553727",
                new UserRepository.User("Petya", "Petrov"));

        final var actual = userRepository.findByMsisdn("88005553535");

        assertNull(actual);
    }
}
