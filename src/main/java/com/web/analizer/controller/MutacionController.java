package com.web.analizer.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.web.analizer.payload.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.analizer.exception.AppException;
import com.web.analizer.model.Expediente;

import com.web.analizer.repository.ExpedienteRepository;
import com.web.analizer.repository.UserRepository;

//import java.util.List;
//import java.util.Arrays;
import com.web.analizer.service.Mutation;

//Request Response imports


@RestController
@RequestMapping("/")
public class MutacionController {
	
	@Autowired
    ExpedienteRepository expedienteRepository;
	
	@Autowired
    Mutation mutationService;

    @PostMapping("/expediente")
    public ResponseEntity<?> newExpediente(@RequestBody ExpedienteRequest expedienteRequest) {
    // Creating new Expediente
	
    ApiResponse response = new ApiResponse(false,"");
    
    Expediente expediente = new Expediente(expedienteRequest.getName(), expedienteRequest.getAdn(), expedienteRequest.getResult() );

		try {
		expedienteRepository.save(expediente);
		response.setMessage("created success");
		response.setSuccess(true);
		}
		catch(Exception e) {
		  response.setMessage(e.toString()+"");
		  response.setSuccess(false);
		}
		
    return ResponseEntity.ok(response);
    }
    
    @GetMapping("/expediente")
    public ResponseEntity<?> getExpedientes() {
    	List<Expediente> expedientes;
    	
    	expedientes = expedienteRepository.findAll();
    	
    	ExpedientesResponse response = new ExpedientesResponse(expedientes,"message");
    	
        return ResponseEntity.ok(response);
    }

	@GetMapping("/expediente/{id}")
	public ResponseEntity<?> getExpediente(@PathVariable Long id) {
		Optional<Expediente> expediente;

		expediente = expedienteRepository.findById(id);

		ExpedienteResponse response = new ExpedienteResponse(expediente,"message");

		return ResponseEntity.ok(response);
	}

	@PutMapping("/expediente/{id}")
	public ResponseEntity<?> updateExpedientes(@RequestBody ExpedienteRequest expedienteRequest, @PathVariable Long id) {

    	Optional<Expediente> expediente;

    	expediente = expedienteRepository.findById(id);

		ExpedienteResponse response;
		if(expediente.isPresent()){
			//Get first element
			Expediente exp = expediente.get();
			System.out.println("data:" + exp);
			//Set data to update
			exp.setName(expedienteRequest.getName());
			exp.setResult(expedienteRequest.getResult());
			//flush to database
			expedienteRepository.save(exp);

			response = new ExpedienteResponse(expediente,"update success");
		}else{
			response = new ExpedienteResponse("update error");
		}

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/expediente/{id}")
	public ResponseEntity<?> deleteExpedientes(@RequestBody IdentifierRequest identiferrequest, @PathVariable Long id) {

		Optional<Expediente> expediente;

		if( id == null ){
			id = identiferrequest.getId();
		}

		expediente = expedienteRepository.findById(id);

		ExpedienteResponse response;
		if(expediente.isPresent()){
			//Get first element
			Expediente exp = expediente.get();

			//flush to database
			expedienteRepository.delete(exp);

			response = new ExpedienteResponse("delete success");
		}else{
			response = new ExpedienteResponse("delete error");
		}

		return ResponseEntity.ok(response);
	}
}