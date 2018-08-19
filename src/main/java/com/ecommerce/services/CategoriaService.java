package com.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ecommerce.DTO.CategoriaDTO;
import com.ecommerce.domain.Categoria;
import com.ecommerce.repositories.CategoriaRepository;
import com.ecommerce.services.exceptions.DataIntegrityException;
import com.ecommerce.services.exceptions.ObjectNotFoundException;

@Service 
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
	
	public Categoria update (Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return categoriaRepository.save(obj); 
	}


	public void delete(Integer id) {
		find(id);
		try {
			categoriaRepository.deleteById(id);	
			
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public List<Categoria> findAll() {
		
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
		
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
	private void updateData (Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
}
