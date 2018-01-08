package br.com.controltraining.users;

import br.com.controltraining.util.DAOFactory;
import java.util.List;

public class UsersBO {

    private UsersDAO usersDAO;

    public UsersBO() {
        this.usersDAO = DAOFactory.criarUsersDAO();
    }

    public Users carregar(Integer codigo) {
        return this.usersDAO.carregar(codigo);
    }

    public Users buscarPorLogin(String login) {

        return this.usersDAO.buscarPorLogin(login);
    }

    public void salvar(Users usuario) {
        Integer codigo = usuario.getId();
        if(codigo==null || codigo==0){
            this.usersDAO.save(usuario);
        }else{
            this.usersDAO.atualizar(usuario);
        }
    }
    public void excluir (Users usuario){
        this.usersDAO.excluir(usuario);
    }
    
    public List<Users> listar(){
        return this.usersDAO.listar();
    }
}
