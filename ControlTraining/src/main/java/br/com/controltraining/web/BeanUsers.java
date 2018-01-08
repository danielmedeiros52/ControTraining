
package br.com.controltraining.web;

import br.com.controltraining.users.Users;
import br.com.controltraining.users.UsersBO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;



@ManagedBean(name = "BeanUsers")
@RequestScoped
public class BeanUsers {
    
    private Users usuario = new Users ();
	private String confirmarSenha;

	public String novo() {
		this.usuario = new Users();
		this.usuario.setAtivo(true);
		return "/publico/usuario";
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		String senha = this.usuario.getSenha();
		if (!senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null;
		}

		UsersBO UsersBO = new UsersBO();
		UsersBO.salvar(this.usuario);

		return "usuariosucesso";
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
    
}
