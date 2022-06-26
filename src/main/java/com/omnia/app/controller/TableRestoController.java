package com.omnia.app.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnia.app.model.TableResto;
import com.omnia.app.service.TableRestoService;

@RestController
@RequestMapping("/api/tabresto")
public class TableRestoController {
	
	
	@Autowired
	TableRestoService tblservice;
	
	
	@PostMapping("/createTable/{restoId}")
	public ResponseEntity<?>  createTableforResto(@RequestBody TableResto tbl ,@PathVariable("restoId")  Long restoId ){
		
		TableResto mes = tblservice.createAffectable(restoId, tbl);
		
		return ResponseEntity.ok().body(mes);
		
		
		
		
		
	}
	
	@GetMapping("/listTable/{restoId}")
	public ResponseEntity<?>  getAllTableAvaible(@PathVariable("restoId") Long restoId){
		
		Collection<TableResto>  alltbl = tblservice.getRestoTableAvaible(restoId);
		
		return ResponseEntity.ok().body(alltbl);
	}
}
