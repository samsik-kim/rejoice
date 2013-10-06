package com.resttemplate.common.util;

import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.view.AbstractView;

public class JSONPView extends AbstractView{
 
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                               HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
        String callback = request.getParameter("callback");
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(model);
         
        Writer out = response.getWriter();
        out.append(callback).append("(").append(json).append(")");
    }
}