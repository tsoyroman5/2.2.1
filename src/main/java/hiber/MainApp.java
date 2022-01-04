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

      User user1 = new User("Tom", "Holland", "tomholland@gmail.com");
      User user2 = new User("Benedict", "Cumberbatch", "bencumberbatch@gmail.com");
      User user3 = new User("Jacob", "Batalon", "jbatalon@gmail.com");

      Car car1 = new Car(11111, "BMW");
      Car car2 = new Car(22222, "VOLVO");
      Car car3 = new Car(33333, "Ferrari");

      user1.setCar(car1);
      car1.setUser(user1);

      user2.setCar(car2);
      car2.setUser(user2);

      user3.setCar(car3);
      car3.setUser(user3);

      userService.add(user1);
      userService.addCar(car1);

      userService.add(user2);
      userService.addCar(car2);

      userService.add(user3);
      userService.addCar(car3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }

      userService.findUserByCarSeries(22222);

      context.close();
   }
}
