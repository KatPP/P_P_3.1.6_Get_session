package com.example.pp_3_1_5;

import com.example.pp_3_1_5.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;


public class UserChange {

    static RestTemplate restTemplate = new RestTemplate();
    static String URL = "http://94.198.50.185:7081/api/users";

    public static void exchangeMethodsOfRestTemplate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        System.out.println("что то должно показаться " + headers);
        // получение всех данных всех пользователей
        ResponseEntity<List> responseEntity = getListByExchangeMethod(requestEntity);

        // достаем session id из заголовка ответа
        headers.set("Cookie", String.join(";", Objects.requireNonNull(responseEntity.getHeaders().get("Set-Cookie"))));
        System.out.println("headers2: " + headers);
//        создаем нового юзера
        User sysUser = new User();
        sysUser.setId(3L);
        sysUser.setName("James");
        sysUser.setLastName("Brown");
        sysUser.setAge((byte) 18);
        requestEntity = new HttpEntity<>(sysUser,headers);
        addUserByExchangeMethod(requestEntity);

//      изменяем юзера
        sysUser.setName("Thomas");
        sysUser.setLastName("Shelby");
        updateUserBuExchangeMethod(requestEntity);

//      удаляем юзера
        deleteUserBuExchangeMethod(requestEntity);
    }

    // удаляем юзера
    private static void deleteUserBuExchangeMethod(HttpEntity<Object> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/3", HttpMethod.DELETE, requestEntity, String.class);
//        HttpStatus statusCode = responseEntity.getStatusCode();
//        System.out.println("status code: " + statusCode);

        String userDetails = responseEntity.getBody();
        System.out.println("User body: " + userDetails);

        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println("response Headers: " + responseHeaders);
    }

    // изменяем юзера
    private static void updateUserBuExchangeMethod(HttpEntity<Object> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);
//        HttpStatus statusCode = responseEntity.getStatusCode();
//        System.out.println("status code: " + statusCode);

        String userDetails = responseEntity.getBody();
        System.out.println("User body: " + userDetails);

        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println("response Headers: " + responseHeaders);
    }
    // добавляем нового юзера
    private static void addUserByExchangeMethod(HttpEntity<Object> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);
//        HttpStatus statusCode = responseEntity.getStatusCode();
//        System.out.println("status code: " + statusCode);

        String userDetails = responseEntity.getBody();
        System.out.println("User body: " + userDetails);

        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println("response Headers: " + responseHeaders);
    }

    // метод GET
    private static ResponseEntity<List> getListByExchangeMethod(HttpEntity<Object> requestEntity) {
        ResponseEntity<List> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, requestEntity, List.class);
//        HttpStatus statusCode = responseEntity.getStatusCode();
//        System.out.println("status code: " + statusCode);

        List userBody = responseEntity.getBody();
        System.out.println("User body: " + userBody);

        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println("response Headers: " + responseHeaders);
        return responseEntity;
    }
}
