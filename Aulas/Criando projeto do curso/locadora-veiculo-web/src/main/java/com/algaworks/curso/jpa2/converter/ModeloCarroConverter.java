package com.algaworks.curso.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.curso.jpa2.dao.ModeloCarroDAO;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=ModeloCarro.class)
public class ModeloCarroConverter implements Converter {

	private ModeloCarroDAO modeloCarroDAO;
	
	public ModeloCarroConverter() {
		this.modeloCarroDAO = CDIServiceLocator.getBean(ModeloCarroDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ModeloCarro result = null;
		
		if (value != null) {
			result = this.modeloCarroDAO.buscarPeloCodigo(new Long(value));
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Long codigo = ((ModeloCarro) value).getCodigo();
			return codigo != null ? codigo.toString() : null; 
		}
		
		return "";
	}

}
