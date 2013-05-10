package web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class CustomURLTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String value;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		String contextPath = pageContext.getServletContext().getContextPath();
		try {
			out.println(contextPath + "/" + value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
