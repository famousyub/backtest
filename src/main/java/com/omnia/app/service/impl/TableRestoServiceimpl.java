package com.omnia.app.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnia.app.enums.TableStatus;
import com.omnia.app.exception.ResourceNotFoundException;
import com.omnia.app.model.Restaurant;
import com.omnia.app.model.TableResto;
import com.omnia.app.repository.RestaurantRepository;
import com.omnia.app.repository.TableRestoRepository;
import com.omnia.app.service.TableRestoService;


@Service
public class TableRestoServiceimpl implements TableRestoService {
	
	
	
	@Autowired 
	TableRestoRepository tblrepo;
	
	@Autowired
	RestaurantRepository restorepo;

	@Override
	public TableResto createAffectable(Long id,TableResto tbl) {
		// TODO Auto-generated method stub
		
		Restaurant resto =restorepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("resto  not found"," with id",id));
		
		tbl.setRestaurant(resto);
		tbl.setTableavaible(TableStatus.AVAIBLE);
		
		
		return tblrepo.save(tbl);
	}

	@Override
	public TableResto getTable(Long restoId, Long tableId) {
		// TODO Auto-generated method stub
		TableResto tblres = new TableResto();
		Restaurant resto =restorepo.findById(restoId).orElseThrow(() -> new ResourceNotFoundException("resto  not found"," with id",restoId));
    
       for (TableResto tb  :  resto.getTablesresto()) {
    	   
		     if(tb.getId()==tableId) {
		    	 tblres = tb;
		    	 return tblres;
		     }
       	}
       
       return tblres;
		
		
	}

	@Override
	public TableResto updateCurentTable(Long id, Long RestoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isTableAvaible(TableResto resto) {
		
		
		return resto.getTableavaible().equals(TableStatus.AVAIBLE)==true ? true:false;
	}

	@Override
	public List<TableResto> getAvaibleTable(Long restoId) {
		
		return tblrepo.getTableInResturant(restoId).stream()
	            .filter(this::isTableAvaible)
	            .collect(Collectors.toList());
	}

	@Override
	public Collection<TableResto> getRestoTableAvaible(Long restoId) {
		
		return tblrepo.getTableInResturant(restoId).stream()
	            .filter(this::isTableAvaible)
	            .collect(Collectors.toList());
		
	}

}

/*

 return reciperepo.findAll().stream()
            .filter(this::isCool)
            .collect(Collectors.toList());
*/