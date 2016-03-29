package pl.bookstore.robot.searchpattern;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
public class TestsKKPattern {

	@Test
	public void jezeliSlowaSaTakieSame()
	{
		String tekst="adamlasota";
		String wzorzec="adam";
		
		KPPattern kPat = new KPPattern();
		
		assertThat(kPat.KP(tekst, wzorzec)).isTrue();
	}
	
	@Test
	public void jezeliSlowaSaRozne()
	{
		String tekst="adamlasota";
		String wzorzec="2312312";
		
		KPPattern kPat = new KPPattern();
		
		assertThat(kPat.KP(tekst, wzorzec)).isFalse();
	}
	@Test
	public void jezeliWzorzecMaPodobneZnakiTylkoWielkie()
	{
		String tekst="adamlasota";
		String wzorzec="Las";
		
		KPPattern kPat = new KPPattern();
		
		assertThat(kPat.KP(tekst, wzorzec)).isFalse();
	}
	@Test
	public void wyszukiwanieWzorcaWSrodku()
	{
		String tekst="adamlasota";
		String wzorzec="mla";
		
		KPPattern kPat = new KPPattern();
		
		assertThat(kPat.KP(tekst, wzorzec)).isTrue();
	}
	
	@Test
	public void wzorzecDluzszyNizTekst()
	{
		String tekst="adamlasota";
		String wzorzec="mlafsdsgfdgdfsgdfgdfsgd";
		
		KPPattern kPat = new KPPattern();
		
		assertThat(kPat.KP(tekst, wzorzec)).isFalse();
	}
}
