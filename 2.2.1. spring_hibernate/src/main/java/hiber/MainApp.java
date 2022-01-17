package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
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

      Car car1 = new Car("Chevrolet Lacetti",123);
      Car car2 = new Car("Opel Corsa",456);
      Car car3 = new Car("Mitsubishi Lancer",789);
      Car car4 = new Car("BMW 318i",666);

      userService.add(new User("Viktor", "Kurkin", "viktor@mail.ru", car1));
      userService.add(new User("Mikhail", "Sukhanov", "misha@mail.ru", car2));
      userService.add(new User("Sergey", "Sorokopudov", "serega@mail.ru", car3));
      userService.add(new User("Leonid", "Sazanov", "leon@mail.ru", car4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println("************************************************");
      System.out.println(userService.getUserByCar("Chevrolet Lacetti",123));
      System.out.println(userService.getUserByCar("Mitsubishi Lancer",789));

      context.close();
   }
}
