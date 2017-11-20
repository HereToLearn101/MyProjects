/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author tedis
 */
public class LoggingAdvice {
    VendingMachineAuditDao auditDao;
    
    public LoggingAdvice(VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
    }
    
    // We imported the aspectj tools to use JoinPoint.
    // The JoinPoint is an object that carries the information about the
    // particular join point, or your point cut.
    public void createAuditEntry(JoinPoint jp, Exception exception) {
        // Grabs the arguments and hand us back an array of objects. All of the
        // arguments/parameters are generally all object types.
        Object[] args = jp.getArgs();
        // Builds a String which will be our audit entry.
        // .getSignature() gets the signature of the method, and then .getName()
        // gets the name of the method
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        auditEntry += " |Exception thrown: " + exception.getClass().getSimpleName();
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
