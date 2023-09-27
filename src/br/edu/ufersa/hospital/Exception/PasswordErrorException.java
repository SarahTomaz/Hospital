package br.edu.ufersa.hospital.Exception;

public class PasswordErrorException extends Exception {
	private static final long serialVersionUID = 1L;

	public PasswordErrorException() {
		super("Campos de senha estão diferentes!");
	}
}
