package modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegrasTaxaTest {    
    
    public RegrasTaxaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        
    }
    private void testA( BigDecimal val ) {
        Calendar calend1 = Calendar.getInstance();
        Calendar calend2 = Calendar.getInstance();
        calend1.set(2013, 6, 1 );
        calend2.set(2013, 7, 1 );
        char tipoO = 'A';
        Transferencia transf = new Transferencia ( calend1.getTime(), calend2.getTime(), "00001-1", "9999-9", tipoO, val);
        BigDecimal expResult = val.multiply(new BigDecimal("0.03")).add(new BigDecimal("2"));
        BigDecimal result = RegrasTaxa.getTaxa(transf);
        assertEquals("teste regra A", expResult, result);        
    }
    private void testB ( BigDecimal val ) {
        Calendar calend1 = Calendar.getInstance();
        Calendar calend2 = Calendar.getInstance();
        char tipoO = 'B';
        Transferencia transf = new Transferencia ( calend1.getTime(), calend2.getTime(), "00001-1", "9999-9", tipoO, val);
        BigDecimal expResult;
        BigDecimal result = RegrasTaxa.getTaxa(transf);
        // rule B 1
        calend1.set(2013, 1, 1 );
        calend2.set(2013, 1, 1 );
        calend2.add(Calendar.DATE, 30);
        transf.setCreated(calend1.getTime());
        transf.setAgenda(calend2.getTime());
        transf.setTipoOperacao(tipoO);
        expResult = new BigDecimal("10");
        result = RegrasTaxa.getTaxa(transf);
        assertEquals("teste regra B.1:", expResult, result);
        // rule B 2
        calend1.set(2013, 1, 1 );
        calend2.set(2013, 1, 1 );
        calend2.add(Calendar.DATE, 31);
        tipoO = 'B';
        transf.setCreated(calend1.getTime());
        transf.setAgenda(calend2.getTime());
        transf.setTipoOperacao(tipoO);
        expResult = new BigDecimal("8");
        result = RegrasTaxa.getTaxa(transf);
        assertEquals("teste regra B.2:", expResult, result);        
    }
    private void testC ( BigDecimal val ) {
        Calendar calend1 = Calendar.getInstance();
        Calendar calend2 = Calendar.getInstance();
        calend1.set(2013, 6, 1 );
        calend2.set(2013, 7, 1 );
        char tipoO = 'C';
        Transferencia transf = new Transferencia ( calend1.getTime(), calend2.getTime(), "00001-1", "9999-9", tipoO, val);
        BigDecimal expResult;
        BigDecimal result;
  
// rule C 1
        calend1.set(2013, 1, 1 );
        calend2.set(2013, 1, 1 );
        calend2.add(Calendar.DATE, 31);
        tipoO = 'C';
        
        transf.setValor(val);
        transf.setCreated(calend1.getTime());
        transf.setAgenda(calend2.getTime());
        transf.setTipoOperacao(tipoO);
        expResult = val.multiply(new BigDecimal("0.012"));
        result = RegrasTaxa.getTaxa(transf);
        assertEquals("teste regra C.1:", expResult, result);
//rule C 2
        calend2.set(2013, 1, 1 );
        calend2.add(Calendar.DATE, 26);
        transf.setAgenda(calend2.getTime());
        expResult = val.multiply(new BigDecimal("0.021"));
        result = RegrasTaxa.getTaxa(transf);
        assertEquals("teste regra C.2:", expResult, result);
        //rule C 3
        calend2.set(2013, 1, 1 );
        calend2.add(Calendar.DATE, 21);
        transf.setAgenda(calend2.getTime());
        expResult = val.multiply(new BigDecimal("0.043"));
        result = RegrasTaxa.getTaxa(transf);
        assertEquals("teste regra C.3:", expResult, result);
//rule C 4
        calend2.set(2013, 1, 1 );
        calend2.add(Calendar.DATE, 16);
        transf.setAgenda(calend2.getTime());
        expResult = val.multiply(new BigDecimal("0.067"));
        result = RegrasTaxa.getTaxa(transf);
        assertEquals("teste regra C.4:", expResult, result);
//rule C 5
        calend2.set(2013, 1, 1 );
        calend2.add(Calendar.DATE, 11);
        transf.setAgenda(calend2.getTime());
        expResult = val.multiply(new BigDecimal("0.074"));
        result = RegrasTaxa.getTaxa(transf);
        assertEquals("teste regra C.5:", expResult, result);
//rule C 6
        calend2.set(2013, 1, 1 );
        calend2.add(Calendar.DATE, 9);
        transf.setAgenda(calend2.getTime());
        expResult = val.multiply(new BigDecimal("0.083"));
        result = RegrasTaxa.getTaxa(transf);
        assertEquals("teste regra C.6:", expResult, result);

    }
    private void testD() {
        assertTrue ( "testes for regra D.a", true);
        testA ( new BigDecimal("24999.99"));
        assertTrue ( "testes for regra D.b", true);
        testB ( new BigDecimal("119999.99"));
        assertTrue ( "testes for regra D.b", true);
        testC ( new BigDecimal("120000.00"));
       
    }
    
    /**
     * Test of getTaxa method, of class RegrasTaxa.
     */
    @Test
    public void testGetTaxa() {
        
        testA ( new BigDecimal ("1000.00"));
        testB ( new BigDecimal ("1000.00"));
        testC ( new BigDecimal ("1000.00"));
        testD();
    }
}