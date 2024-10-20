package ru.pa.zhuchkov.homework;

public interface UserRepository {
    record User() {}

    User findByMsisdn(String msisdn);

    void updateUserByMsisdn(String msisdn, User user);
}
