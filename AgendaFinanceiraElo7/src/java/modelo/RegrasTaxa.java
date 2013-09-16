package modelo;

import java.math.BigDecimal;
import java.util.Date;
import org.joda.time.Days;
import org.joda.time.LocalDate;

public class RegrasTaxa {
//design pattern ; strategy ou template?? TODO
    public synchronized static BigDecimal getTaxa(Transferencia transf) {
        BigDecimal valRetorno = null;
        switch (transf.getTipoOperacao()) {
            case 'A':
                valRetorno = regraA(transf);
                break;
            case 'B':
                valRetorno = regraB(transf);
                break;
            case 'C':
                valRetorno = regraC(transf);
                break;
            case 'D':
                valRetorno = regraD(transf);
                break;
        }
        return valRetorno;
    }

    private static BigDecimal regraD(Transferencia transf) {
        BigDecimal valRetorno = null;
        if (transf.getValor().compareTo(new BigDecimal("25000.00")) < 0) {
            valRetorno = regraA(transf);
        } else if (transf.getValor().compareTo(new BigDecimal("120000.00")) < 0) {
            valRetorno = regraB(transf);
        } else {
            valRetorno = regraC(transf);
        }
        return valRetorno;
    }

    private static BigDecimal regraC(Transferencia transf) {
        long dias = calculaDiasEntreDatas(transf.getCreated(), transf.getAgenda());
        BigDecimal valRetorno = null;
        if (dias > 30) {
            valRetorno = transf.getValor().multiply(new BigDecimal("0.012"));
        } else if (dias > 25) {
            valRetorno = transf.getValor().multiply(new BigDecimal("0.021"));
        } else if (dias > 20) {
            valRetorno = transf.getValor().multiply(new BigDecimal("0.043"));
        } else if (dias > 15) {
            valRetorno = transf.getValor().multiply(new BigDecimal("0.067"));
        } else if (dias > 10) {
            valRetorno = transf.getValor().multiply(new BigDecimal("0.074"));
        } else {
            valRetorno = transf.getValor().multiply(new BigDecimal("0.083"));
        }
        return valRetorno;
    }

    private static BigDecimal regraB(Transferencia transf) {
        long dias = calculaDiasEntreDatas(transf.getCreated(), transf.getAgenda());
        System.out.println(transf.getCreated() + "\n" + transf.getAgenda() + " " + dias);
        BigDecimal valRetorno = null;
        if (dias < 31) {
            valRetorno = new BigDecimal("10");
        } else {
            valRetorno = new BigDecimal("8");
        }
        System.out.println(dias);
        return valRetorno;
    }

    private static int calculaDiasEntreDatas(Date dataCriacao, Date dataExecucao) {
        LocalDate d1a = new LocalDate(dataCriacao.getTime());
        LocalDate d2a = new LocalDate(dataExecucao.getTime());
        int days = Days.daysBetween(d1a, d2a).getDays();
        return days;

    }

    private static BigDecimal regraA(Transferencia transf) {
        BigDecimal valRetorno = transf.getValor().multiply(new BigDecimal("0.03")).add(new BigDecimal("2"));
        return valRetorno;
    }
}