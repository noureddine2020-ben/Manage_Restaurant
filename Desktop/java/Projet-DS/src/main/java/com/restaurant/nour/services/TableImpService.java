package com.restaurant.nour.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.nour.entities.Client;
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
   
	//**** chercher la table la plus demandé => l'idée est de calculer pour chaque table le nombre des tickets qui lui sont attribués
	 // ensuite de retourner la table qui possède le plus de tickets = c'est à dire réservation
	@Override
	public String TablePlusReserve() {
		
		List<TablePlace> tables= rechercheToutesTables();
		Map<Integer, Integer> data = new HashMap<>();
		int nb=0;
		
		for (TablePlace table : tables)
		{
			nb=table.getTickets().size();
			data.put(table.getNumTable(), nb);
		}
		
		System.out.println(data);
		
		Entry<Integer, Integer> entry = data.entrySet().stream().max((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue())).get(); // cherhcer la valeur maximale
		 
		return("La table la plus demandée est: (Numéro de la table =" + entry.getKey() + ", avec le nombre total des reservations =" + entry.getValue() + ")");
	}

}