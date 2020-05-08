package com.oracle.persistence;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Definición de todos los contratos de comportamiento para el Acceso a Datos.
 * 
 * @author diegoprietotorres
 *
 * @param <E>  Entidad
 * @param <PK> Llave primaria
 */
public interface DAO<E extends Serializable, PK> {

	/**
	 * Contrato para retornar todas las entidades de tipo E.
	 * 
	 * @return
	 */
	public List<E> findAll() throws SQLException;

	/**
	 * Contrato para retornar una entidad de tipo E dado su llave primaria de tipo
	 * PK.
	 * 
	 * @param id - Llave primaria de la entidad E.
	 * @return
	 */
	public E findById(PK id) throws SQLException;

	/**
	 * Contrato para insertar una entidad tipo E en la Base de Datos.
	 * 
	 * @param entity
	 * @return
	 */
	public boolean insert(E entity) throws SQLException;

	/**
	 * Contrato para actualizar una entidad de tipo E en la base de Datos.
	 * 
	 * @param entity
	 * @return
	 */
	public boolean update(E entity) throws SQLException ;

	/**
	 * Contrato para eliminar una entidad E, dado su id tipo PK.
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(PK id) throws SQLException ;
}
