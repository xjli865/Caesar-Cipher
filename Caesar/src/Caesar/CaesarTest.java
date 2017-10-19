package Caesar;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CaesarTest {
	Caesar mySample;
	
	@Before
	public void setUp(){
		mySample = new Caesar();
	}

	@Test
	public void stringSettest() throws IOException {
		assertTrue(mySample.stringSet().contains("aahing"));
		assertFalse(mySample.stringSet().contains("aahixg"));
	}
	
	@Test
	public void enciphertest(){
		assertEquals("crrng", mySample.encipher(2, "apple"));
		assertEquals("CRRNG", mySample.encipher(2, "APPLE"));
		assertEquals("YnNjc&*^", mySample.encipher(-2, "ApPle&*^"));
		assertEquals("vjku ku c oguucig", mySample.encipher(2,"this is a message"));
		assertEquals("VJKU KU C OGUUCIG", mySample.encipher(2,"THIS IS A MESSAGE"));
		assertEquals("vjKU ku c oguuciG", mySample.encipher(2,"thIS is a messagE"));
		assertEquals("D*E=F", mySample.encipher(3, "A*B=C"));
		assertEquals("BkRRNg", mySample.encipher(2, "ZiPPLe"));
	}
	
	@Test
	public void deciphertest() throws IOException{
		assertEquals("APPLE", mySample.decipher("CRRNG"));
		assertEquals("this is a message", mySample.decipher("vjku ku c oguucig"));
		assertEquals("ApPle&*^", mySample.decipher("YnNjc&*^"));
		assertEquals("Hello, Java World! This Is J.", mySample.decipher("Dahhk, Fwrw Sknhz! Pdeo Eo F."));
	}

}
