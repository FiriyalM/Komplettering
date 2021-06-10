/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.te4.blogg.resources;

import com.te4.blogg.Bean.CredentialsBean;
import com.te4.blogg.entity.Credentials;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author Elev
 */
@Path("User")
public class CredentialResource {
    @EJB
    CredentialsBean credentialsbeans;
/**
 * 
 * @param authorization
 * @return an authorized credential object
 * 
 */
    @GET
    public Response checkUser(@HeaderParam("Authorization") String authorization){
        Credentials credentials = credentialsbeans.creatCredentials(authorization);
        if(credentialsbeans.cheackCredentials(credentials)){
            return Response.ok("Welcome to our secret Rest API").build();
        }else{
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
//
    @POST
    
/**
 * 
 */
    public Response createUser(@HeaderParam("Authorization") String authorization){
        Credentials credentials = credentialsbeans.creatCredentials(authorization);
        if(credentialsbeans.saveCredentials(credentials) == 1){
            return Response.status(Response.Status.CREATED).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    public Response saveInlagg(){
        Response response;
        try{
            credentialsbeans.persist();
            response = Response.status(Response.Status.CREATED).build();
        }catch(Exception e){
            response = Response.serverError().entity(e.getMessage()).build();
        }
        
        return response;
    }
}