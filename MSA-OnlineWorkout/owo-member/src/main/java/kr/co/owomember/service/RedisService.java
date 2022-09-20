package kr.co.owomember.service;

public interface RedisService {

    //get
    String getValue(String key);

    //create
    void createData(String key, String value, long expired);

    //delete
    void deleteData(String key);

    //common
    void checkValue(String refreshToken, String redisToken);
}
