/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.components;

import com.cristianroot.springrestsecurityexample.exceptions.generic.*;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({NotFoundException.class})
	public ResponseEntity handleNotFound(Exception exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorModel.of(exception.getMessage()));
	}

	@ExceptionHandler({ForbiddenException.class})
	public ResponseEntity handleForbidden(Exception exception) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorModel.of(exception.getMessage()));
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity handleBadRequest(Exception exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.of(exception.getMessage()));
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity handleConflict(Exception exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorModel.of(exception.getMessage()));
	}

	@ExceptionHandler({InternalServerErrorException.class, MessagingException.class})
	public ResponseEntity handleInternalServerError(Exception exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorModel.of(exception.getMessage()));
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity handleUnauthorized(Exception exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorModel.of(exception.getMessage()));
	}

}

