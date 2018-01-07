
package br.com.controltraining.users;

import java.util.List;

public interface UsersDAO {
    public void save(Users user);
    public void atualizar(Users user);
    public void excluir(Users user);
    public Users carregar (Integer id);
    public Users buscarPorLogin(String login);
    public  List<Users> listar();
}
