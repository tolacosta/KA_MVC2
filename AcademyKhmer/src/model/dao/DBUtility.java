package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.NamingException;

public class DBUtility {
	
	public Connection getConnect() throws NamingException, SQLException, ClassNotFoundException{
		Class.forName("org.postgresql.Driver");

		
//		Connection con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/khmeracademy", "postgres", "0001");
		
//		Connection con = DriverManager.getConnection("jdbc:postgresql://1.246.219.206:5432/KA2", "postgres", "kshrd@ka!@#$%^&*())(*&^%$#@!");
		
//		Connection con = DriverManager.getConnection("jdbc:postgresql://1.246.219.166:5432/KADB", "postgres", "KSHRD@KA");
		
		Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.178.254:5432/KhmerAcademy", "postgres", "123456");
		
		
		
		
//		Connection con = DriverManager.getConnection("jdbc:postgresql://192.168.178.182:5433/academykhmer", "postgres", "123");
//		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KhmerAcademy", "postgres", "1");
		
//		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/KhmerAcademy", "postgres", "123456");
	    return con;
	}
}

