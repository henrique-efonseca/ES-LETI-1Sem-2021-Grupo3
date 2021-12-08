package Tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import Backend.TrelloAPI;
import Frontend.Sprint;
import Frontend.Tabela;
//Junit tests
public class TrelloTests {
	static String s1= "a04256995af78e5ea7bff424d82cf477";
	static String s2 = "47fd71497e5ffcb377ea49fd0302a42f66ba0a411829da35dac1ade25025e501";
	static String s3 = "60eae4ca19ea426cbba3021a";
	static TrelloAPI app;
	static TrelloAPI comp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 app = new TrelloAPI();
		 comp = new TrelloAPI();
		 app.setUp(s1,s2,s3);
		 comp.setUp(s1,s2,s3);
	}

	@Test
	public void testToCSV() {	
		 try {
			assertNotNull(app.toCSV());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testNoArtifact() {
		List<Tabela> tables = app.noArtifact();
		assertNotNull(tables);
		boolean empty = tables.isEmpty();
		assertFalse(empty);
		for(Tabela t : tables){
			if(t.getTitle().equals("Total")){
				Assertions.assertEquals(75,(int) t.getObject(3,1));
				break;
				}
		}
	}

	@Test
	public void testArtifact() {
		List<Tabela> tables = app.artifact();
		assertNotNull(tables);
		boolean empty = tables.isEmpty();
		assertFalse(empty);
		for(Tabela t : tables){
			if(t.getTitle().equals("Total")){
				Assertions.assertEquals(11,(int) t.getObject(3,1));
				break;
				}
		}
	}
	
	@Test
	public void testGetSprints() {
		List<Sprint> list = app.getSprints();
		assertNotNull(list);
		boolean empty = list.isEmpty();
		assertFalse(empty);
	
		 for(Sprint sprint : list){
			 boolean statement = sprint.getName().contains("Sprint");	
			 assertTrue(statement);
		 }	
	}

	@Test
	public void testRecursosHumanos() {
		List<Tabela> tables = app.humanResourcesCost();
		assertNotNull(tables);
		boolean empty = tables.isEmpty();
		assertFalse(empty);
		for(Tabela t : tables){
			if(t.getTitle().equals("Total")){
				Assertions.assertEquals(1080.0,(double) t.getObject(3,1));
				break;
				}
		}
	}

	@Test
	public void testSprintTime() {
		List<Tabela> tables = app.sprintTime();
		assertNotNull(tables);
		boolean empty = tables.isEmpty();
		assertFalse(empty);
		for(Tabela t : tables){
			if(t.getTitle().equals("Total")){
				Assertions.assertEquals(54.0,(double)t.getObject(3,1));
				break;
				}
		}
	}

	@Test
	public void testFunctionDate() {
	
		 assertNotNull(app.functionDate());
	}

	@Test
	public void testSprintsText() {	
		 assertNotNull(app.sprintsText());
		 String txt=app.sprintsText();
		 String sprint1 ="Preparar e organizar tudo o que esteja relacionado com a metodologia de trabalho SCRUM e iniciar o GUI.";
		 String sprint2 ="**Sprint Goal**: Conectar todas as API's requisitadas (pelo enunciado);  Implementação das funcionalidades nas API's; Construção da aplicacao final; utilizar todas as frameworks/plugins requisitados (JavaDoc,).";
		 String sprint3	="**Sprint Goal**: Terminar Sprint2; Testes unitários; Relatório e correção de Code Smells; Total de horas realizadas em cada um dos Sprints realizados;";
		 assertTrue(txt.contains(sprint1));
		 assertTrue(txt.contains(sprint2));
		 assertTrue(txt.contains(sprint3));
	}

	@Test
	public void testProductBacklog() {
		 assertNotNull(app.productBacklog());
		 assertNotNull(comp.productBacklog());
		 assertEquals(app.productBacklog(),comp.productBacklog());
	}

	@Test
	public void testSprintDates() {
		 assertNotNull(app.getSprints());
		 List<Sprint> list = app.getSprints();
		 boolean empty = list.isEmpty();
		 assertFalse(empty);
		 String date="";
		 for(Sprint s : list){
		 boolean statement = s.getName().contains("Sprint");	
		 assertTrue(statement);
		 boolean statement2 = s.getDate() != null;	
		 assertTrue(statement2);
		 date += s.getDate()+"\n";
		 }		 
		 assertTrue(date.contains("**Início**: 1/Outubro/2021\n"
		 						+ "**Fim**: 31/Outubro/2021\n" + 
		 						  "**Duração**: 4 Semanas\n"));
		 				 		  
	}

	@Test
	public void testProjectDate() {
		assertNotNull(app.projectDate());	
		boolean statement = app.projectDate().contains("Mon Oct 25");	
		assertTrue(statement);
	}

	@Test
	public void testProjectID() {	
		 assertNotNull(app.projectID());	
		 assertTrue(app.projectID().contains("60eae4ca19ea426cbba3021a"));
	}

	@Test
	public void testMembers() {
		assertNotNull(app.members());
		String m = app.members();
		assertTrue(m.contains("ricardonunosilvapaulo"));
		assertTrue(m.contains("antoniocosta21_"));
		assertTrue(m.contains("henriquefonseca44"));
		assertTrue(m.contains("bernardomiguelluz"));
		
	}


}
