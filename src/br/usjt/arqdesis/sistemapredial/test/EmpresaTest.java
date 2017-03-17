package br.usjt.arqdesis.sistemapredial.test;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.usjt.arqdesis.sistemapredial.model.Empresa;
import br.usjt.arqdesis.sistemapredial.model.Usuario;
import br.usjt.arqdesis.sistemapredial.service.EmpresaService;
import br.usjt.arqdesis.sistemapredial.service.UsuarioService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaTest {
	
	
	Empresa empresa, copia;
	EmpresaService empresaService;
	static int id = 0;

	/*
	 * Antes de rodar este teste, certifique-se que nao ha no banco nenhuma
	 * linha com o id igual ao do escolhido para o to instanciado abaixo. Se
	 * houver, delete. 
	 * Certifique-se tamb√©m que sobrecarregou o equals na classe
	 * Cliente. 
	 * Certifique-se que a fixture cliente1 foi criada no banco.
	 * Al√©m disso, a ordem de execu√ß√£o dos testes √© importante; por
	 * isso a anota√ß√£o FixMethodOrder logo acima do nome desta classe
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		empresa = new Empresa();
		empresa.setId(id);
		empresa.setCnpj("121212121212121");
		empresa.setRazaoSocial("Grande Empresa");
		empresa.setConjunto("11");
		empresa.setHorarioDeFuncionamento("8");
		empresa.setTemperaturaDoArCondicionado("23∫");
		empresa.setHorarioDoArCondicionado("Infotec");
		copia = new Empresa();
		copia.setId(id);
		copia.setCnpj("121212121212121");
		copia.setRazaoSocial("Grande Empresa");
		copia.setConjunto("11");
		copia.setHorarioDeFuncionamento("8");
		copia.setTemperaturaDoArCondicionado("23∫");
		copia.setHorarioDoArCondicionado("Infotec");
		empresaService = new EmpresaService();
		System.out.println(empresa);
		System.out.println(copia);
		System.out.println(id);
	}
	
	@Test
	public void teste00Carregar() {        //PARADA DAS ALTERA«’ES
			System.out.println("carregar");
			//para funcionar o usuario 1 deve ter sido carregado no banco por fora
			Empresa fixture = new Empresa();
			fixture.setId(1);
			fixture.setCnpj("111111111111");
			fixture.setRazaoSocial("Grande Empresa");
			fixture.setConjunto("22");
			fixture.setHorarioDeFuncionamento("8 h");
			fixture.setTemperaturaDoArCondicionado("20 ∫");
			fixture.setHorarioDoArCondicionado("8 h");
			EmpresaService empresaService = new EmpresaService();
			Empresa nova = empresaService.carregar(1);
			assertEquals("testa inclusao", nova, fixture);
		}


	@Test
	public void test01Criar() {
		System.out.println("criar");
		id = empresaService.criar(empresa);
		System.out.println(id);
		copia.setId(id);
		assertEquals("testa criacao", empresa, copia);
		
		System.out.println("criar");
		id = empresaService.criar(empresa);
		System.out.println(id);
		copia.setId(id);
		assertEquals("testa criacao", empresa, copia);
	}

	@Test
	public void test02Atualizar() {
		
		
		System.out.println("atualizar");
		empresa.setCnpj("1111111111");
		copia.setCnpj("1111111111");		
		empresaService.atualizar(empresa);
		empresa = empresaService.carregar(empresa.getId());
		assertEquals("testa atualizacao", empresa, copia);
	}

	@Test
	public void test03Excluir() {
	
		
		System.out.println("excluir");
		copia.setId(-1);
		copia.setCnpj("111111111111");
		copia.setRazaoSocial("Grande Empresa");
		copia.setConjunto("22");
		copia.setHorarioDeFuncionamento("8 h");
		copia.setTemperaturaDoArCondicionado("20 ∫");
		copia.setHorarioDoArCondicionado("8 h");
		empresaService.excluir(id);
		empresa = empresaService.carregar(id);
		assertEquals("testa exclusao", empresa, copia);
	}
	
	
}