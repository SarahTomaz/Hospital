module Hospital {
	
		exports br.edu.ufersa.hospital.model.entity;
		exports br.edu.ufersa.hospital.model.service;
		exports br.edu.ufersa.hospital.api.dto;
		exports br.edu.ufersa.hospital.model.dao;
		exports br.edu.ufersa.hospital.api.controller;
		exports br.edu.ufersa.hospital.Exception;

			opens br.edu.ufersa.hospital.api.controller to javafx.graphics, javafx.fxml;
	
}