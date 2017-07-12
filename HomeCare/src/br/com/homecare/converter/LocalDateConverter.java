package br.com.homecare.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="localDateConverter")
public class LocalDateConverter implements Converter {
	
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	
    	if(value == null || "".equals(value)) {
    		return null;
    	}
    	DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			
		    LocalDate.parse(String.valueOf(value) , f);
		} catch (DateTimeParseException e) {
			return null;
		}
    	
        Locale BRAZIL = new Locale("pt", "BR");
        return LocalDate.parse(value, f.withLocale(BRAZIL));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
    	if(value == null || "".equals(value)) {
    		return "";
    	}
    	
    	try {
    		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate.parse(String.valueOf(value) , f);
		} catch (DateTimeParseException e) {
			return "";
		}
    	
    	LocalDate dateValue = (LocalDate) value;
    	
    	Locale BRAZIL = new Locale("pt", "BR");
    	DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateValue.format(f.withLocale(BRAZIL)).toString();

    }
    
}