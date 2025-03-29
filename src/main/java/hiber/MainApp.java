package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Toyota", 123);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru", car1);
      userService.add(user1);

      Car car2 = new Car("BMW", 456);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru", car2);
      userService.add(user2);

      Car car3 = new Car("Audi", 789);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru", car3);
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());

         if (user.getCar() != null) {
            System.out.println("Car Model = " + user.getCar().getModel());
            System.out.println("Car Series = " + user.getCar().getSeries());
         } else {
            System.out.println("Машина не назначена.");
         }

         System.out.println();
      }

      List<User> foundUsers = userService.getUserByCar("BMW", 456);

      if (foundUsers.isEmpty()) {
         System.out.println("Пользователь с указанной машиной не найден.");
      } else {
         for (User user : foundUsers) {
            System.out.println("Найден пользователь: " + user.getFirstName() + " " + user.getLastName());
         }
      }

      context.close();
   }
}