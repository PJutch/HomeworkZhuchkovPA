package ru.pa.zhuchkov.homework;

public interface UserRepository {
    record User(String firstName, String lastName) {}

    User findByMsisdn(String msisdn);

    void updateUserByMsisdn(String msisdn, User user);
}
