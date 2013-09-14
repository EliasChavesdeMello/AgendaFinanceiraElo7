package control;

import java.io.Serializable;
import modelo.Transferencia;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Calendar;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import modelo.RegrasTaxa;
import modelo.dao.TransferenciaDao;

@ManagedBean(name = "transferenciaBean")
@SessionScoped
 
public class TransferenciaBean implements Serializable {
    private Transferencia transferencia = new Transferencia();
    private TransferenciaDao transferenciaDao;

    public TransferenciaBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String dirParaPersistir = ctx.getExternalContext().getInitParameter("dirParaPersistir");
        transferenciaDao = new TransferenciaDao(dirParaPersistir);
        transferencia = new Transferencia();
    }

    public void cadastrar(ActionEvent actionEvent) {
        getTransferencia().setCreated(Calendar.getInstance().getTime());
        getTransferencia().setTaxa(RegrasTaxa.getTaxa(getTransferencia()));
        getTransferenciaDao().inserir(getTransferencia());
        setTransferencia(new Transferencia());
    }

    public void alterar(ActionEvent actionEvent) {
        getTransferencia().setTaxa(RegrasTaxa.getTaxa(getTransferencia()));
        getTransferenciaDao().alterar(getTransferencia());
        setTransferencia(new Transferencia());
    }

    public void excluir(ActionEvent actionEvent) {
        getTransferenciaDao().excluir(getTransferencia());
        setTransferencia(new Transferencia());
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