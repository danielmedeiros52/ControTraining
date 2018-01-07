package br.com.controltraining.users;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsersDAOH implements UsersDAO {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void save(Users user) {
        this.session.save(user);
    }

    @Override
    public void atualizar(Users user) {
        this.session.update(user);
    }

    @Override
    public void excluir(Users user) {
        this.session.delete(user);
    }

    @Override
    public Users carregar(Integer id) {
        return (Users) this.session.get(Users.class, id);
        
    }

    @Override
    public Users buscarPorLogin(String login) {
String hql ="select u from Users u where u.login =:login";
        Query consulta =this.session.createQuery(hql);
        consulta.setString("login",login);
        return (Users) consulta.uniqueResult();
  
    }

    @Override
    public List<Users> listar() {
        return this.session.createCriteria(Users.class).list();
      
    }

}
