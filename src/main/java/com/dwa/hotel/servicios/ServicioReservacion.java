package com.dwa.hotel.servicios;

import java.security.MessageDigest;
import java.sql.Date;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.text.DateFormat;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dwa.hotel.models.Reservacion;


@Service
public class ServicioReservacion {
	
	@Autowired
	private RepoReservacion repoReservacion;
	private String Mensaje;
	
	public String getMensaje() {
		return Mensaje;
	}
	
	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}
	

	
	public boolean registrar(Reservacion r, HttpSession sesion) {
		try {
			//r.setFechaEntrada(Fecha(fechaEntrada));
			//se envia la info ala tabla de reservacion
			repoReservacion.save(r);
			sesion.setAttribute("idReservacion", r.getId());
			this.Mensaje = "Se ha almacenado tu informacion.";
			return true;
		}
		
		catch (Exception e) {
			this.Mensaje = "Error al registrar la reservacion.";
			return false;
		}
	}
	
	public Iterable<Reservacion> verTodos()
	{
		try {
			this.Mensaje="";
			return repoReservacion.findAll();
		}catch(Exception e) {
			this.Mensaje = e.getMessage();
			return null;
		}
	}
	
	
	public Reservacion buscar(int id) {
		try {
			Reservacion reservacion = repoReservacion.findById(id).get();
			return reservacion == null ? new Reservacion() : reservacion;
		}
		catch (Exception e) {
			this.Mensaje = e.getMessage();
			return null;
		}
	}
	
	

}
