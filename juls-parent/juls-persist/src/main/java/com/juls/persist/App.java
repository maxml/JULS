package com.juls.persist;

import java.util.Arrays;

import com.juls.model.Good;
import com.juls.model.User;
import com.juls.model.UserDetails;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        User user = new User("testmail@m.net", "12345");
        User user1 = new User("testmail1@m.net", "12345");
        User user2 = new User("testmail2@m.net", "12345");
        System.out.println(new UserDAOImpl().insert(user));
        System.out.println(new UserDAOImpl().insert(user1));
        System.out.println(new UserDAOImpl().insert(user2));
        Arrays.toString(new UserDAOImpl().getAll().toArray());
        user1.setEmail("changed@mail.net");
        System.out.println(new UserDAOImpl().update(user1));
        System.out.println(new UserDAOImpl().getById(user2.getId()));
        System.out.println(new UserDAOImpl().delete(user));
        System.out.println(new UserDAOImpl().delete(user1));
        System.out.println(new UserDAOImpl().delete(user2));
        System.out.println("Good");
        Good good = new Good("good1", 12.5f);
        Good good1 = new Good("good2", 1.35f);
        Good good2 = new Good("good3", 163.2f);
        System.out.println(new GoodDAOImpl().insert(good));
        System.out.println(new GoodDAOImpl().insert(good1));
        System.out.println(new GoodDAOImpl().insert(good2));
        Arrays.toString(new GoodDAOImpl().getAll().toArray());
        good1.setName("changed");
        System.out.println(new GoodDAOImpl().update(good1));
        System.out.println(new GoodDAOImpl().getById(good2.getId()));
        System.out.println(new GoodDAOImpl().delete(good));
        System.out.println(new GoodDAOImpl().delete(good1));
        System.out.println(new GoodDAOImpl().delete(good2));
        System.out.println( "User details!" );
        UserDetails userd = new UserDetails("testval", "12345", "testval", "anothertestval");
        UserDetails userd1 = new UserDetails("testval1", "12345", "testval1", "testtval1");
        UserDetails userd2 = new UserDetails("testval2", "12345", "testval2", "testtval2");
        System.out.println(new UserDetailsDAOImpl().insert(userd));
        System.out.println(new UserDetailsDAOImpl().insert(userd1));
        System.out.println(new UserDetailsDAOImpl().insert(userd2));
        Arrays.toString(new UserDetailsDAOImpl().getAll().toArray());
        userd1.setFirstName("changed");
        System.out.println(new UserDetailsDAOImpl().update(userd1));
        System.out.println(new UserDetailsDAOImpl().getById(userd2.getId()));
        System.out.println(new UserDetailsDAOImpl().delete(userd));
        System.out.println(new UserDetailsDAOImpl().delete(userd1));
        System.out.println(new UserDetailsDAOImpl().delete(userd2));
    }
}
