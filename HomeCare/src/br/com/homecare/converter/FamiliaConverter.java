package br.com.homecare.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.homecare.model.Familia;

@FacesConverter("familiaConverter")
public class FamiliaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		if (value == null){
			return null;
		}

		Familia familiaSelecionada = new Familia();
		familiaSelecionada.setId(Integer.valueOf(value));

		return familiaSelecionada;		
    }

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value == null) {
			return "";			
		}

		Familia familiaSelecionada = (Familia) value;
		
		if(familiaSelecionada.getId() == null) {
			return "";
		}

		return familiaSelecionada.getId().toString();
    }
}


