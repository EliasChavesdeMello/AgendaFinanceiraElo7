package modelo;

import java.math.BigDecimal;
import java.util.Date;

public class RegrasTaxa {

    public static BigDecimal getTaxa(Transferencia transf) {
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
        } else if (transf.getValor().compareTo(new BigDecimal("25000.00")) < 0) {
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
        BigDecimal valRetorno = null;
        if (dias < 31) {
            valRetorno = new BigDecimal("10");
        } else {
            valRetorno = new BigDecimal("8");
        }
        return valRetorno;
    }

    private static long calculaDiasEntreDatas(Date dataCriacao, Date dataExecucao) {
        long dt = (dataExecucao.getTime() - dataCriacao.getTime()) + 3600000; // 1 hora para compensar horário de verão  
        long dias = dt / 86400000L;
        return dias;

    }

    private static BigDecimal regraA (Transferencia transf) {
        BigDecimal valRetorno = transf.getValor().multiply(new BigDecimal("0.03")).add(new BigDecimal("2"));
        return valRetorno;
    }

}
