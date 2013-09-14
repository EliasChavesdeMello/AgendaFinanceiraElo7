package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transferencia implements Serializable {
    private Date created;
    private Date agenda;
    private String contaDe;
    private String contaTo;
    private char tipoOperacao;
    private BigDecimal valor;
    private BigDecimal taxa;
    @Override
    public String toString() {
        StringBuilder show = new StringBuilder();       
        show.append("\nData Criação:").append(this.created).append("\nData Transf:").append(this.agenda)
                .append("\nde:").append(this.contaDe).append("\npara:").append(contaTo).append("\ntipo:")
                .append(this.tipoOperacao).append("\nvalor:").append(this.valor)
                .append("\ntaxa:").append(this.taxa);
        return show.toString();
    }
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getAgenda() {
        return agenda;
    }

    public void setAgenda(Date agenda) {
        this.agenda = agenda;
    }

    public String getContaDe() {
        return contaDe;
    }

    public void setContaDe(String contaDe) {
        this.contaDe = contaDe;
    }

    public String getContaTo() {
        return contaTo;
    }

    public void setContaTo(String contaTo) {
        this.contaTo = contaTo;
    }

    public char getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(char tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }
}
