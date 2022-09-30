package edu.city.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import edu.city.domain.PersonRequest;
import edu.city.domain.PersonResponse;
import edu.city.exception.PersonCheckException;

public class PersonCheckDaoTest {
	
	@Test
	public void checkPerson() throws PersonCheckException {
		PersonRequest request = new PersonRequest();
		request.setSurName("Васильев");
		request.setGivenName("Иван");
		request.setPatronymic("Петрович");
		request.setDateOfBirth(LocalDate.of(1991, 07, 23));
		request.setStreetCode(1);
		request.setBuilding("10");
		request.setExtension("2");
		request.setApartment("192");
		
		PersonCheckDao pcd = new PersonCheckDao();
		PersonResponse response = pcd.checkPerson(request);
		assertTrue(response.isRegistered());
		assertFalse(response.isTemporal());
	}
		
@Test
	public void checkPerson2() throws PersonCheckException {
		PersonRequest request = new PersonRequest();
		request.setSurName("Васильева");
		request.setGivenName("Ирина");
		request.setPatronymic("Николаевна");
		request.setDateOfBirth(LocalDate.of(1993, 01, 26));
		request.setStreetCode(1);
		request.setBuilding("230");
		request.setExtension(null);
		request.setApartment("13");
		
		PersonCheckDao pcd = new PersonCheckDao();
		PersonResponse response = pcd.checkPerson(request);
		assertTrue(response.isRegistered());
		assertFalse(response.isTemporal());
		
	}

@Test
	public void checkPerson3() throws PersonCheckException {
		PersonRequest request = new PersonRequest();
		request.setSurName("Васильева");
		request.setGivenName("Ирина");
		request.setPatronymic("Николаевна");
		request.setDateOfBirth(LocalDate.of(1993, 01, 26));
		request.setStreetCode(1);
		request.setBuilding("10");
		request.setExtension("2");
		request.setApartment("192");
		
		PersonCheckDao pcd = new PersonCheckDao();
		PersonResponse response = pcd.checkPerson(request);
		assertFalse(response.isRegistered());
		assertFalse(response.isTemporal());
		
	}

}
