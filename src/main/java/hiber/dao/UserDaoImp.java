package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void addCar(Car car) { sessionFactory.getCurrentSession().save(car); }

   @Override
   public void findUserByCar(int series, String model) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("from User E where E.car.id = :car_id and E.car.model = :car_model");
      query.setParameter("car_id", series);
      query.setParameter("car_model", model);
      for (User user: query.getResultList()) {
         System.out.println(user);
      }
   }
}
