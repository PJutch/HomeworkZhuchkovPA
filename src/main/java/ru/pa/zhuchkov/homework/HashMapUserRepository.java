package ru.pa.zhuchkov.homework;

import java.util.HashMap;

public class HashMapUserRepository implements UserRepository {
    private final HashMap<String, User> usersByMsisdn = new HashMap<>();

    @Override
    public User findByMsisdn(String msisdn) {
        return usersByMsisdn.get(msisdn);
    }

    @Override
    public void updateUserByMsisdn(String msisdn, User user) {
        usersByMsisdn.put(msisdn, user);
    }
}
