package br.com.cowtysys.challenge.domain;

import java.io.Serializable;
import java.util.Calendar;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/*
public class AuditInterceptor extends EmptyInterceptor {

	private static final String DATE_MODEL = "dateModel";
	private static final long serialVersionUID = 8166620274312015048L;

	@Override
    public boolean onSave(Object entity, Serializable id, Object[] currentState, String[] propertyNames, Type[] types) {
        if (entity instanceof DomainModel) {
            for (int i=0; i < propertyNames.length; i++) {
                if (DATE_MODEL.equals(propertyNames[i])) {
                	Calendar calendar = Calendar.getInstance();
					currentState[i] = new DateModel(calendar, calendar);
                    return true;
                }
            }
        }
        return false;
    }
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        if (entity instanceof DomainModel) {
            for (int i=0; i < propertyNames.length; i++) {
                if (DATE_MODEL.equals( propertyNames[i])) {
                	DateModel dateModel = (DateModel) currentState[i];
                	if (dateModel != null) {
                		Calendar calendar = Calendar.getInstance();
                		dateModel.setUpdatedAt(calendar);
                		currentState[i] = dateModel;
                	}
                    return true;
                }
            }
        }
        return false;
	}
}
//*/