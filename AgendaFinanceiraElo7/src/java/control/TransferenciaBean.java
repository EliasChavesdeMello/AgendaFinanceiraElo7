package control;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import modelo.RegrasTaxa;
import modelo.Transferencia;
import modelo.dao.TransferenciaDao;

@ManagedBean(name = "transferenciaBean")
@SessionScoped
public class TransferenciaBean implements Serializable {
	private Transferencia transferencia = new Transferencia();
	@ManagedProperty(value = "#{transferenciaDao}")
	private TransferenciaDao transferenciaDao;

	public TransferenciaBean() {
		transferencia = new Transferencia();
	}

	public void cadastrar(ActionEvent actionEvent) {
		getTransferencia().setCreated(Calendar.getInstance().getTime());
		getTransferencia().setTaxa(RegrasTaxa.getTaxa(getTransferencia()));
		getTransferenciaDao().inserir(getTransferencia());
		setTransferencia(new Transferencia());
		mensagemOperacao("OK", "Transferência cadastrada");
	}

	private void mensagemOperacao(String titulo, String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(titulo, mensagem));
	}

	public void alterar(ActionEvent actionEvent) {
		getTransferencia().setTaxa(RegrasTaxa.getTaxa(getTransferencia()));
		getTransferenciaDao().alterar(getTransferencia());
		setTransferencia(new Transferencia());
		mensagemOperacao("OK", "Transferência alterada");
	}

	public void excluir(ActionEvent actionEvent) {
		getTransferenciaDao().excluir(getTransferencia());
		setTransferencia(new Transferencia());
		mensagemOperacao("OK", "Transferência excluída");
	}

	public Transferencia getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}

	public TransferenciaDao getTransferenciaDao() {
		return transferenciaDao;
	}

	public void setTransferenciaDao(TransferenciaDao transferenciaDao) {
		this.transferenciaDao = transferenciaDao;
	}
}