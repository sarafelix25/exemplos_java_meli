package br.com.isiflix.veiculos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.isiflix.veiculos.exceptions.NaoEncontradoException;
import br.com.isiflix.veiculos.exceptions.ValidacaoException;

@ControllerAdvice
public class VeiculoControllerAdvice {

	@ExceptionHandler(value = ValidacaoException.class)
	public ResponseEntity<?> veiculoInvalidoException(){
		return new ResponseEntity<String>("Dados inválidos ou mal formatados", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = NaoEncontradoException.class)
	public ResponseEntity<?> veiculoNaoEncontradoException() {
		return new ResponseEntity<String>("Veículo não encontrado.", HttpStatus.NOT_FOUND);
	}
}
