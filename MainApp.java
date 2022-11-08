package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);


        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user3@mail.ru");


        Car car1 = new Car("Volvo", 123);
        Car car2 = new Car("VW", 412);
        Car car3 = new Car("Hammer", 25);
        Car car4 = new Car("Bugatti", 125);


        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2);
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car2).setUser(user4));


        List<User> users = userService.listUsers();


        for (User user : users) {

            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println("User who has a car Bugatti = " + userService.getUserByCar("Bugatti", 125));
            System.out.println("User who has a car Hammer = " + userService.getUserByCar("Hammer", 25));
            System.out.println();
//            userService.cleanUsersTable(user);

        }

        userService.cleanUsersTable(user1);
        userService.cleanUsersTable(user2);
        userService.cleanUsersTable(user3);
        userService.cleanUsersTable(user4);


        context.close();
    }
}
