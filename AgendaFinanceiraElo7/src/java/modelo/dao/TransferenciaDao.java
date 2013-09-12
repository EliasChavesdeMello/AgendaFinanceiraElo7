package modelo.dao;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelo.Transferencia;

public class TransferenciaDao implements Serializable{

    public List<Transferencia> transferencias;
    public PersistenceStrategy strategy;
    public List listPersistence;

    public TransferenciaDao() {
        this.transferencias = new ArrayList<Transferencia>();
        strategy = new FilePersistenceStrategy(new File("C:\\Users\\elias\\Desktop\\ELIAS-NAO-mexa\\lixo"));
        listPersistence = new XmlArrayList(strategy);
        for (Iterator it = listPersistence.iterator(); it.hasNext();) {
            Transferencia trf = (Transferencia) it.next();
            transferencias.add(trf);
        }
    }

    private void atualiza() {
        this.transferencias.clear();

        listPersistence = new XmlArrayList(strategy);
        for (Iterator it = listPersistence.iterator(); it.hasNext();) {
            Transferencia trf = (Transferencia) it.next();
            transferencias.add(trf);
        }
    }

    public List<Transferencia> listar() {
        return transferencias;
    }

    public void inserir(Transferencia transferencia) {

        try {
            listPersistence.add(transferencia);
        } finally {
            atualiza();
        }
    }

    public void alterar(Transferencia transferencia) {
        try {
            for (Iterator it = listPersistence.iterator(); it.hasNext();) {
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
            for (Iterator it = listPersistence.iterator(); it.hasNext();) {
                Transferencia trf = (Transferencia) it.next();
                if (trf.getCreated().equals(transferencia.getCreated())) {

                    it.remove();
                }
            }
        } finally {
            atualiza();
        }
    }
}
