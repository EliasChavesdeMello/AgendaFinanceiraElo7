package modelo.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import modelo.Transferencia;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

@ManagedBean(name = "transferenciaDao")
@ApplicationScoped
public class TransferenciaDao implements Serializable {
    private List<Transferencia> transferencias;
    private String dirParaPersistir;

	public TransferenciaDao() {
        this.transferencias = new ArrayList<Transferencia>();
		FacesContext ctx = FacesContext.getCurrentInstance();
		this.dirParaPersistir = ctx.getExternalContext().getInitParameter("dirParaPersistir");

        File file =  new File(dirParaPersistir);
        if ( file.exists() ) {
            loadFromDisk();
        }
    }

    private void loadFromDisk() {
        XStream xs = new XStream(new StaxDriver());

        try {
            FileInputStream fis = new FileInputStream(getDirParaPersistir());            
            this.setTransferencias((List<Transferencia>)xs.fromXML(fis));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void atualiza() {
        XStream xs = new XStream();
        try {
            FileOutputStream fs = new FileOutputStream(getDirParaPersistir());
            xs.toXML(this.getTransferencias(), fs);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public List<Transferencia> listar() {
        return getTransferencias();
    }

    public void inserir(Transferencia transferencia) {
        try {
            this.getTransferencias().add (transferencia);
        } finally {
            atualiza();
        }
    }

    public void alterar(Transferencia transferencia) {
        try {
            for (Iterator it = this.getTransferencias().iterator(); it.hasNext();) {
                Transferencia trf = (Transferencia) it.next();
                if (trf.getCreated().equals(transferencia.getCreated())) {
                    it.remove();
                }
            }
            inserir(transferencia);
        } finally {
            atualiza();
        }
    }

    public void excluir(Transferencia transferencia) {
        try {
            for (Iterator it = this.getTransferencias().iterator(); it.hasNext();) {
                Transferencia trf = (Transferencia) it.next();
                if ( trf.getCreated().equals(transferencia.getCreated()) &&
                        trf.getAgenda().equals(transferencia.getAgenda()) &&
                        trf.getTipoOperacao() == transferencia.getTipoOperacao()  &&
                        trf.getValor().equals(transferencia.getValor()) &&
                        trf.getContaDe().equals(transferencia.getContaDe()) && 
                        trf.getContaTo().equals(transferencia.getContaTo()) ) {
                    it.remove();
                }
            }
        } finally {
            atualiza();
        }
    }
    public List<Transferencia> getTransferencias() {
        return transferencias;
    }
    public void setTransferencias(List<Transferencia> transferencias) {
        this.transferencias = transferencias;
    }
    public String getDirParaPersistir() {
        return dirParaPersistir;
    }
    public void setDirParaPersistir(String dirParaPersistir) {
        this.dirParaPersistir = dirParaPersistir;
    }
}
