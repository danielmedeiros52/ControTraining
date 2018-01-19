package br.com.controltraining.web;

import br.com.controltraining.users.Users;
import br.com.controltraining.users.UsersBO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.component.html.HtmlDataTable;

@ManagedBean(name = "BeanUsers")
@RequestScoped
public class BeanUsers {

    private Users usuario = new Users();
    private String confirmarSenha;
    private List<Users> lista;
    private HtmlDataTable dataTable;

    public List<Users> listar() {
        if (this.lista == null) {
            UsersBO usersBO = new UsersBO();
            this.lista = usersBO.listar();
        }

        return this.lista;
    }

    public String users() {

        return "/publico/users";
    }

    public String cadastrar() {
        this.usuario = new Users();
        this.usuario.setAtivo(true);
        return "/publico/cadUsers";
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();

        String senha = this.usuario.getSenha();
        if (!senha.equals(this.confirmarSenha)) {
            FacesMessage facesMessage = new FacesMessage("A senha nao foi confirmada corretamente");
            context.addMessage(null, facesMessage);
            return null;
        }

        UsersBO usersBO = new UsersBO();
        usersBO.salvar(this.usuario);

        return "/publico/users";
    }

    public Users getUsuario() {
        return usuario;
    }

    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public String editar() {

//       this.usuario = usuario.getSelectedItem();
        this.confirmarSenha = this.usuario.getSenha();

        return "/publico/cadUsers";
    }

    public void setSelectedItem(ValueChangeEvent event) {

        usuario = (Users) dataTable.getRowData();

    }

    public void getSelectedItem() {

        ArrayList<Users> selectedDataList = new ArrayList<Users>();
        selectedDataList.add(usuario);

        
    }

    ;



         public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }
}
