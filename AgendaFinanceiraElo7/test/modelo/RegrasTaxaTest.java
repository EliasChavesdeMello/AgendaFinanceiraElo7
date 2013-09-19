package modelo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import static modelo.RegrasTaxaTest.calend1;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class RegrasTaxaTest {
	private static BigDecimal val;
	static Calendar calend1;
	static Calendar calend2;
	private static Transferencia transf;
	BigDecimal expResult;
	BigDecimal result;
	static char tipoO;

	public RegrasTaxaTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		val = new BigDecimal("1000.00");
		calend1 = Calendar.getInstance();
		calend2 = Calendar.getInstance();
	}

	@Test
	public void paraTipoADeveCobrarUmaTaxaDeDoisMaisTresPorcento() {
		calend1.set(2013, 6, 1);
		calend2.set(2013, 7, 1);
		tipoO = 'A';
		transf = new Transferencia(calend1.getTime(), calend2.getTime(), "00001-1", "9999-9", tipoO, val);
		expResult = val.multiply(new BigDecimal("0.03")).add(new BigDecimal("2"));
		result = RegrasTaxa.getTaxa(transf);
		assertEquals("teste regra A", expResult, result);
	}

	@Test
	public void paraTipoBQuandoAgendadoAte30DiasDeveCobrarTaxa10() {
		tipoO = 'B';
		result = RegrasTaxa.getTaxa(transf);
		calend1.set(2013, 1, 1);
		calend2.set(2013, 1, 1);
		setValoresParaTeste(30, tipoO);
		expResult = new BigDecimal("10");
		result = RegrasTaxa.getTaxa(transf);
		assertEquals("teste regra B.1:", expResult, result);
	}

	@Test
	public void paraTipoBQuandoAgendadoAlem30DiasDevecobrarTaxa8() {
		calend1.set(2013, 1, 1);
		calend2.set(2013, 1, 1);
		tipoO = 'B';
		setValoresParaTeste(31, tipoO);
		expResult = new BigDecimal("8");
		result = RegrasTaxa.getTaxa(transf);
		assertEquals("teste regra B.2:", expResult, result);
	}

	@Test
	public void paraTipoCQuandoAgendadoAlem30DiasDeveCobrarTaxaDe1_2porCento() {
		calend1.set(2013, 1, 1);
		calend2.set(2013, 1, 1);
		tipoO = 'C';

		setValoresParaTeste(31, tipoO);
		expResult = val.multiply(new BigDecimal("0.012"));
		result = RegrasTaxa.getTaxa(transf);
		assertEquals("teste regra C.1:", expResult, result);
	}

	@Test
	public void paraTipoCQuandoAgendadoAte30DiasDeveCobrarTaxaDe2_1porCento() {
		calend1.set(2013, 1, 1);
		calend2.set(2013, 1, 1);
		tipoO = 'C';

		setValoresParaTeste(26, tipoO);
		expResult = val.multiply(new BigDecimal("0.021"));
		result = RegrasTaxa.getTaxa(transf);
		assertEquals("teste regra C.2:", expResult, result);
	}

	@Test
	public void paraTipoCQuandoAgendadoAte25DiasDeveCobrarTaxaDe4_3porCento() {
		calend1.set(2013, 1, 1);
		calend2.set(2013, 1, 1);
		tipoO = 'C';
		setValoresParaTeste(21, tipoO);
		expResult = val.multiply(new BigDecimal("0.043"));
		result = RegrasTaxa.getTaxa(transf);
		assertEquals("teste regra C.3:", expResult, result);
	}

	@Test
	public void paraTipoCQuandoAgendadoAte15DiasDeveCobrarTaxaDe6_7porCento() {
		calend1.set(2013, 1, 1);
		calend2.set(2013, 1, 1);
		tipoO = 'C';
		setValoresParaTeste(16, tipoO);
		expResult = val.multiply(new BigDecimal("0.067"));
		result = RegrasTaxa.getTaxa(transf);
		assertEquals("teste regra C.4:", expResult, result);
	}

	@Test
	public void paraTipoCQuandoAgendadoAte10DiasDeveCobrarTaxaDe7_4porCento() {
		calend1.set(2013, 1, 1);
		calend2.set(2013, 1, 1);
		tipoO = 'C';
		setValoresParaTeste(11, tipoO);
		expResult = val.multiply(new BigDecimal("0.074"));
		result = RegrasTaxa.getTaxa(transf);
		assertEquals("teste regra C.5:", expResult, result);
	}

	@Test
	public void paraTipoCQuandoAgendadoAte5DiasDeveCobrarTaxaDe8_3porCento() {
		calend1.set(2013, 1, 1);
		calend2.set(2013, 1, 1);
		tipoO = 'C';
		setValoresParaTeste(9, tipoO);
		expResult = val.multiply(new BigDecimal("0.083"));
		result = RegrasTaxa.getTaxa(transf);
		assertEquals("teste regra C.6:", expResult, result);

	}

	private void setValoresParaTeste(int dias, char tipoOperacao) {
		calend2.add(Calendar.DATE, dias);
		transf = new Transferencia(calend1.getTime(), calend2.getTime(), "00001-1", "9999-9", tipoOperacao, val);
	}

	// @Test
	// public void testD() {
	// assertTrue("testes for regra D.a", true);
	// testA(new BigDecimal("24999.99"));
	// assertTrue("testes for regra D.b", true);
	// testB(new BigDecimal("119999.99"));
	// assertTrue("testes for regra D.b", true);
	// testC(new BigDecimal("120000.00"));
	// }

}
