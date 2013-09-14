package modelo.dao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelo.Transferencia;

public class TransferenciaDao implements Serializable {

    private List<Transferencia> transferencias;
    private String dirParaPersistir;

    public TransferenciaDao(String dirParaPersistir) {
        this.transferencias = new ArrayList<Transferencia>();
        this.dirParaPersistir = dirParaPersistir;
        if ( new File(dirParaPersistir).exists() ) {
            loadFromDisk();
        }
    }

    private void loadFromDisk() {
        XStream xs = new XStream(new DomDriver());

        try {
            FileInputStream fis = new FileInputStream(getDirParaPersistir());
            xs.fromXML(fis, this.getTransferencias());

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
