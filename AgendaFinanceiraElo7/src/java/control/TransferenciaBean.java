package control;
import java.io.Serializable;
import modelo.Transferencia;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;
import modelo.dao.TransferenciaDao;

@ManagedBean(name="transferenciaBean")
@SessionScoped
 
public class TransferenciaBean implements Serializable {
 
	Transferencia transferencia = new Transferencia(); 
	List<Transferencia> transferencias = new ArrayList<Transferencia>(); 
	private TransferenciaDao transferenciaDao = new TransferenciaDao();
 
	public TransferenciaBean(){
		transferencias = transferenciaDao.listar();
		transferencia = new Transferencia();
	}
 
	public void cadastrar(ActionEvent actionEvent){
		transferenciaDao.inserir(transferencia);
		transferencias = transferenciaDao.listar();
		transferencia = new Transferencia();
	}
 
	public void alterar(ActionEvent actionEvent){
		transferenciaDao.alterar(transferencia);
		transferencias = transferenciaDao.listar();
		transferencia = new Transferencia();
	}
	public void excluir(ActionEvent actionEvent){
		transferenciaDao.excluir(transferencia);
		transferencias = transferenciaDao.listar();
		transferencia = new Transferencia();
	}
 
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