package br.unifesp.profetas.business;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.util.ProfetasConstants;

public abstract class AbstractBusiness {
	
	private Locale locale = new Locale(ProfetasConstants.LOCALE_DEFAULT);
	private MessageSourceAccessor messages;
	
	@Autowired
    public void setMessages(MessageSource messageSource) {
        messages = new MessageSourceAccessor(messageSource);        
    }
	
	protected String getText(String messageKey) {
		try {
			return messages.getMessage(messageKey, locale);
		}
		catch(Exception ex) {
			return "";
		}
    }    
    protected String getText(String messageKey, String arg) {
        return getText(messageKey, new Object[] { arg });
    }    
    protected String getText(String messageKey, Object[] args) {
        return messages.getMessage(messageKey, args, locale);
    }
    
    protected int getTotalNumPages(int totalRows, int numRows)  {
        return (int)Math.ceil(((double)totalRows/(double)numRows));
    }	
	protected Integer getPage(Integer page) {
		return page == null ? 1 : page;
	}
	protected <T> WrapperGrid<T> getWrapper(List<T> list, String orderBy,
			OrderType orderType, int page, int numRows, int totalRows, String search){
		
		WrapperGrid<T> wrapper = new WrapperGrid<T>();
		wrapper.setRows(list);
		wrapper.setNumRows(numRows);
		wrapper.setCurrentNumRows((list == null ? 0 : list.size()));
		wrapper.setCurrentPage(getPage(page));
		wrapper.setOrderBy(orderBy);
		wrapper.setOrderType(orderType.getDescription());
		wrapper.setNumPages(getTotalNumPages(totalRows, numRows));
		wrapper.setTotal(totalRows);
		wrapper.setSearch(search);
		return wrapper;
	}
}