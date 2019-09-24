/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import com.entity.AvailableProduct;
import com.entity.ProductPurches;
import com.entity.SellProduct;
import com.exam.util.NewHibernateUtil;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
@SessionScoped
public class Bean {
    SellProduct sellProduct = new SellProduct();
    ProductPurches  productPurches = new ProductPurches();
   AvailableProduct availableProduct = new AvailableProduct();

    public SellProduct getSellProduct() {
        return sellProduct;
    }

    public void setSellProduct(SellProduct sellProduct) {
        this.sellProduct = sellProduct;
    }

    public ProductPurches getProductPurches() {
        return productPurches;
    }

    public void setProductPurches(ProductPurches productPurches) {
        this.productPurches = productPurches;
    }

    public AvailableProduct getAvailableProduct() {
        return availableProduct;
    }

    public void setAvailableProduct(AvailableProduct availableProduct) {
        this.availableProduct = availableProduct;
    }
    
      public void total(){
        int a = sellProduct.getS_uprice();
        int b = sellProduct.getS_qty();
        int total = a*b;
        sellProduct.setS_amount(total);
    }
    //sell
    public String sell(){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        Transaction tx =null;              
        try {
            tx = session.beginTransaction();
            session.save(sellProduct);
            tx.commit();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Product sell Successfully!"));
        } catch (Exception e) {
            
        }
        
        finally{
            session.flush();
            
        }
        return "";      
   
    }
    
    
     public void totalp(){
        int a = productPurches.getU_price();
        int b = productPurches.getP_qty();
        int total = a*b;
        productPurches.setAmount(total);
    }
    
     //purches
     public String purches(){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        Transaction tx =null;
       try {
            tx = session.beginTransaction();
            session.save(productPurches);
            tx.commit();
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Purches Successfully!"));
        } catch (Exception e) {
           
        }
       try {
            tx = session.beginTransaction();
            availableProduct.setId(productPurches.getId());
            availableProduct.setP_name(productPurches.getP_name());
             availableProduct.setC_name(productPurches.getC_name());
              availableProduct.setG_name(productPurches.getG_name());
               availableProduct.setP_qty(productPurches.getP_qty());
            session.save(availableProduct);
            tx.commit();
        } catch (Exception e) {
            
        }finally{
            session.flush();
            
        }                
       
        return "";
    }
    //show products
     public List<AvailableProduct> show_p() {
       Session session = NewHibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       try {
           tx = session.beginTransaction();
           Query query = (Query) session.createQuery("FROM AvailableProduct");
           List<AvailableProduct> list = query.list();
           tx.commit();
           return list;
       } catch (Exception e) {
          
       } finally {
           session.flush();
       }
       return null;
   }
     
     //sell details
     
     public List<SellProduct> sellDetails() {
       Session session = NewHibernateUtil.getSessionFactory().openSession();
       Transaction tx = null;
       try {
           tx = session.beginTransaction();
           Query query = (Query) session.createQuery("FROM SellProduct");
           List<SellProduct> list = query.list();
           tx.commit();
           return list;
       } catch (Exception e) {
          
       } finally {
           session.flush();
       }
       return null;
   }
}
