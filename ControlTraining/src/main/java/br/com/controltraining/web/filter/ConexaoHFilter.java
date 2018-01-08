package br.com.controltraining.web.filter;

import br.com.controltraining.util.HibernateUtil;
import java.util.Collection;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.FilterDefinition;

@WebFilter(urlPatterns = {"*.jsf"})
public class ConexaoHFilter implements Filter {

    private SessionFactory sf;

    public void init(FilterConfig config) throws ServletException {
        this.sf = HibernateUtil.getSessionFactory();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException {
        Session currentSession = this.sf.getCurrentSession();

        Transaction transaction = null;
        try {
            transaction = currentSession.beginTransaction();
            chain.doFilter(servletRequest, servletResponse);
            transaction.commit();
            if (currentSession.isOpen()) {
                currentSession.close();
            }
        } catch (Throwable ex) {
            try {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
            throw new ServletException(ex);

        }
    }

    public void destroy(){
        
    }
    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FilterDefinition getFilterDefinition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filter setParameter(String string, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filter setParameterList(String string, Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filter setParameterList(String string, Object[] os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validate() throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
