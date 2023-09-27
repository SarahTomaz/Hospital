module Hospital {
	
		exports br.edu.ufersa.hospital.model.entity;
		exports br.edu.ufersa.hospital.model.service;
		exports br.edu.ufersa.hospital.api.dto;
		exports br.edu.ufersa.hospital.model.dao;
		exports br.edu.ufersa.hospital.api.controller;
		exports br.edu.ufersa.hospital.view;
		exports br.edu.ufersa.hospital.Exception;

		requires java.sql;
		requires javafx.base;
		requires javafx.controls;
		requires javafx.fxml;
		requires javafx.graphics;
		
			opens br.edu.ufersa.hospital.view to javafx.graphics, javafx.fxml;
			opens br.edu.ufersa.hospital.api.controller to javafx.graphics, javafx.fxml;
	
}