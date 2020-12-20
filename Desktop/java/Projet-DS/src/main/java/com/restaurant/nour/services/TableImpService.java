package com.restaurant.nour.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.nour.entities.TablePlace;
import com.restaurant.nour.repository.TableRepository;

@Service
public class TableImpService implements Tableservice{
	 
	 TableRepository repoTable;
	 
    @Autowired
	public TableImpService(TableRepository repoTable) {
		super();
		this.repoTable = repoTable;
	}

	@Override
	public List<TablePlace> rechercheToutesTables() {
		
		return repoTable.findAll();
	}

	@Override
	public TablePlace rechercheParNumT(int num) {
		 Optional<TablePlace> tb=repoTable.findById(num);
		 TablePlace table=null;
		 table=tb.orElseThrow(()-> new NoSuchElementException("Attention la table avec le numéro "+num+ " n'existe pas"));	 
		 return table;
	}

	@Override
	public TablePlace ajouterTable(TablePlace table) {
		   TablePlace newTable = repoTable.save(table);
		   return newTable;
	}

	@Override
	public TablePlace miseAjourTable(int num, TablePlace newTable) {
		// supposons que tous les attribus sont obligatoire -> pas de test de vérification à faire ici
		TablePlace oldTable = this.rechercheParNumT(num);
		oldTable.setNbrCouvert(newTable.getNbrCouvert());
		oldTable.setSupplement(newTable.getSupplement());
		oldTable.setType(newTable.getType());
		oldTable.setTickets(newTable.getTickets());
		 
	    return repoTable.save(oldTable);
	}

	@Override
	public TablePlace supprimerTable(int num) {
		TablePlace oldTable = this.rechercheParNumT(num);
		repoTable.delete(oldTable);
		return oldTable;
	}

}