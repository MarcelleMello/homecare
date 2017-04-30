package br.com.homecare.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.com.homecare.model.Comunidade;

@FacesConverter("comunidadeConverter")
public class ComunidadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		if (value == null){
			return null;
		}

		Comunidade comunidadeSelecionada = new Comunidade();
		comunidadeSelecionada.setId(Integer.valueOf(value));

		return comunidadeSelecionada;		
    }

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value == null) {
			return "";			
		}

		Comunidade comunidadeSelecionada = (Comunidade) value;
		
		if(comunidadeSelecionada.getId() == null) {
			return "";
		}

		return comunidadeSelecionada.getId().toString();
    }
}


