package edu.city.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.city.domain.PersonRequest;
import edu.city.domain.PersonResponse;
import edu.city.exception.PersonCheckException;

public class PersonCheckDao {
	
	private final static String SQL_REQUEST = "SELECT cap.temporal FROM cr_address_person cap "
			+ "INNER JOIN cr_person cp ON cp.person_id = cap.person_id "
			+ "INNER JOIN cr_address ca ON ca.address_id = cap.address_id "
			+ "WHERE "
			+ "CURRENT_DATE >= cap.start_date and (CURRENT_DATE < cap.end_date OR cap.end_date is null) and "
			+ "upper(cp.sur_name) = upper(?) and upper(cp.given_name) = upper(?) and upper(cp.patronymic) = upper(?) "
			+ "and cp.date_of_birth = ? "
			+ "and ca.street_code = ? "
			+ "and upper(ca.building) = upper(?) ";
	
	public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException{
		PersonResponse response = new PersonResponse();
		
		String sql = SQL_REQUEST;
		
		if (request.getExtension() != null) {
			sql += "and upper(ca.extension) = upper(?) ";
		} else {
			sql += "and ca.extension is null ";
		}
		if (request.getApartment() != null) {
			sql += "and upper(ca.apartment) = upper(?) ";
		} else {
			sql += "and ca.apartment is null";
		}
			
		try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
			int count = 1;
			stmt.setString(count++, request.getSurName());
			stmt.setString(count++, request.getGivenName());
			stmt.setString(count++, request.getPatronymic());
			stmt.setDate(count++, java.sql.Date.valueOf(request.getDateOfBirth()));
			stmt.setInt(count++, request.getStreetCode());
			stmt.setString(count++, request.getBuilding());
			if (request.getExtension() != null) {
			stmt.setString(count++, request.getExtension());
			}
			if (request.getApartment() != null) {
				stmt.setString(count++, request.getApartment());
			}
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				response.setRegister(true);
				response.setTemporal(rs.getBoolean("temporal"));
			}
			
		} catch (SQLException ex) {
			throw new PersonCheckException(ex);
		}
		
		return response;
	}

	private static Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:postgresql://localhost/city_register", "postgres", "pfqrfvjz");
	}

}
