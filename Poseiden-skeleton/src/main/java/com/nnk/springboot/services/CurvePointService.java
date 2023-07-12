package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {

	@Autowired
	private CurvePointRepository curvePointRepository;
	
	/**
	 * Get all the Curve Point from the database.
	 * 
	 * @return
	 */
    public Iterable<CurvePoint> getCurvePointsList() {
        return curvePointRepository.findAll();
    }

    /**
     * Save/Update the curve point provided in database.
     * 
     * @param curvePoint
     * @return
     */
    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Get the info from a specific curve point from the database.
     * 
     * @param id
     * @return
     */
    public Optional<CurvePoint> getCurvePointById(Integer id) {
        return curvePointRepository.findById(id);
    }

    /**
     * Delete the specified curve list from the database.
     * 
     * @param curvePoint
     */
    public void deleteCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.delete(curvePoint);
    }

}
