package com.omnia.app.service;

import java.util.Collection;
import java.util.List;

import com.omnia.app.model.TableResto;
import com.omnia.app.response.RestoResponse;

public interface TableRestoService {
	
	public TableResto createAffectable(Long id,TableResto tbl);
	
	public TableResto getTable(Long restoId, Long tableId);
	
	public TableResto updateCurentTable(Long id,Long  RestoId);
	
	public  Boolean isTableAvaible(TableResto resto);
	
	public List<TableResto> getAvaibleTable(Long restoId);
	public Collection<TableResto>  getRestoTableAvaible(Long restoId);

}
