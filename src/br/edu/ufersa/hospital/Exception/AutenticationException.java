package br.edu.ufersa.hospital.Exception;

public class AutenticationException extends Exception {
	private static final long serialVersionUID = 1L;

	public AutenticationException() {
		super("Username ou senha não encontrados!");
	}
}
