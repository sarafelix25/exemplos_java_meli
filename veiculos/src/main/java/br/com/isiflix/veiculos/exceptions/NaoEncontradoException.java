package br.com.isiflix.veiculos.exceptions;

public class NaoEncontradoException extends RuntimeException{
	public NaoEncontradoException(String message) {
		super(message);
	}

}