package control;

import modelo.Transferencia;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import modelo.dao.TransferenciaDao;

@ManagedBean
@SessionScoped
 
public class TransferenciaBean {
 
	Transferencia transferencia = new Transferencia();
 
	List<Transferencia> transferencias = new ArrayList<Transferencia>(); 
 
	public TransferenciaBean(){
		transferencias = new TransferenciaDao().listar();
		transferencia = new Transferencia();
	}
 
	public void cadastrar(ActionEvent actionEvent){
		new TransferenciaDao().inserir(transferencia);
		transferencias = new TransferenciaDao().listar();
		transferencia = new Transferencia();
	}
 
	public void alterar(ActionEvent actionEvent){
		new TransferenciaDao().alterar(transferencia);
		transferencias = new TransferenciaDao().listar();
		transferencia = new Transferencia();
	}
	public void excluir(ActionEvent actionEvent){
		new TransferenciaDao().excluir(transferencia);
		transferencias = new TransferenciaDao().listar();
		transferencia = new Transferencia();
	}
 
	//getters and setters
	public Transferencia getTransferencia() {
		return transferencia;
	}
 
	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}
 
	public List getTransferencias() {
		return transferencias;
	}
 
	public void setTransferencias(List transferencias) {
		this.transferencias = transferencias;
	}
}