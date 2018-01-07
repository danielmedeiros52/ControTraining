package br.com.controltraining.util;

import br.com.controltraining.users.UsersDAO;
import br.com.controltraining.users.UsersDAOH;

public class DAOFactory {

    public static UsersDAO criarUsersDAO() {
        UsersDAOH usersDAO = new UsersDAOH();
        usersDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return usersDAO;
    }
}
