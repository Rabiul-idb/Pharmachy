/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import com.entity.AdminLogin;

import com.entity.SalemanLogin;
import com.exam.model.Salesman;
import com.exam.util.NewHibernateUtil;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Student
 */
@ManagedBean
@SessionScoped
public class BeaanControl {
    AdminLogin adminLogin =new AdminLogin ();
    Salesman salesman=new Salesman();
    SalemanLogin salemanLogin=new SalemanLogin();
   
   
   
    public AdminLogin getAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(AdminLogin adminLogin) {
        this.adminLogin = adminLogin;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public SalemanLogin getSalemanLogin() {
        return salemanLogin;
    }

    public void setSalemanLogin(SalemanLogin salemanLogin) {
        this.salemanLogin = salemanLogin;
    }

    
    
    
    //admin login
    public String loginAdd(){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        Transaction tx =null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from AdminLogin  where username= :username and password= :password");
            query.setString("username", adminLogin.getUsername());
            query.setString("password", adminLogin.getPassword());
            List list = query.list();
            if (list.size() == 1) {
                return "AdminPage";
            }else{
              return  "AdminLoginError";
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }
    //salesmanlogin
    public String loginSalesman(){
        Session session = NewHibernateUtil.getSessionFactory().openSession();        
        Transaction tx =null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from SalemanLogin  where username=:username and password=:password");
            query.setString("username", salemanLogin.getUsername());
            query.setString("password", salemanLogin.getPassword());
           
            
            List list = query.list();
            if (list.size() == 1) {
                return "SalesmanPage";
            }
            else{
            
            return "SalesmanLoginError";
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }
    
    
    
    
    //save methods
     public String save(){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        Transaction tx =null;
       try {
            tx = session.beginTransaction();
            session.save(salesman);
            tx.commit();
             session.flush();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Inserted Successfully!"));
        } catch (Exception e) {
           
        }
       try {
            tx = session.beginTransaction();
            salemanLogin.setUsername(salesman.getUsername());
            salemanLogin.setPassword(salesman.getPassword());
            session.save(salemanLogin);
            tx.commit();
        } catch (Exception e) {
            
        } finally {
            session.flush();
        }
        return "AdminPage";
    }
     
    
   //show method
      public List<Salesman> show() {
       Session session = NewHibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       try {
           tx = session.beginTransaction();
           Query query = (Query) session.createQuery("FROM Salesman");
           List<Salesman> list = query.list();
           tx.commit();
           return list;
       } catch (Exception e) {
          
       } finally {
           session.flush();
       }
       return null;
   }
    
    
    //update salesman
     public String update(){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        Transaction tx =null;
       try {
            tx = session.beginTransaction();
           
            session.update(salesman);
            tx.commit();
             session.flush();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("data updated Successfully!"));
        } catch (Exception e) {
           tx.rollback(); 
        }
       try {
            tx = session.beginTransaction();
            salemanLogin.setUsername(salesman.getUsername());
            salemanLogin.setPassword(salesman.getPassword());
            session.update(salemanLogin);
            tx.commit();
            session.flush();
        } catch (Exception e) {
          tx.rollback();  
        }
        return "AdminPage";
    }
     
     //delete salesman
     public String delete(Salesman d){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        Transaction tx =null;
       try {
            tx = session.beginTransaction();
            session.delete(d);
            tx.commit();
             session.flush();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("data deleted Successfully!"));
        } catch (Exception e) {
           tx.rollback(); 
        }
       try {
            tx = session.beginTransaction();
            salemanLogin.setUsername(d.getUsername());
            salemanLogin.setPassword(d.getPassword());
            session.delete(salemanLogin);
            tx.commit();
        } catch (Exception e) {
          tx.rollback();  
        } finally {
            session.flush();
        }
        return "SalesmanDetails";
    }

     //edit salesman
     public String edit(Salesman s){
         salesman.setId(s.getId());
         salesman.setName(s.getName());
         salesman.setUsername(s.getUsername());
         salesman.setPassword(s.getPassword());
         salesman.setPhone(s.getPhone());
         salesman.setAddress(s.getAddress());
         salesman.setGender(s.getGender());
         salesman.setDate(s.getDate());
         
         return "EditSalesman";
    }
   
     
      
   
}
