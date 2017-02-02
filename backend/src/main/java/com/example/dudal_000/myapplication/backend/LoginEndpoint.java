package com.example.dudal_000.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "loginApi",
        version = "v1",
        resource = "login",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.dudal_000.example.com",
                ownerName = "backend.myapplication.dudal_000.example.com",
                packagePath = ""
        )
)
public class LoginEndpoint {

    private static final Logger logger = Logger.getLogger(LoginEndpoint.class.getName());

    /**
     * This method gets the <code>Login</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>Login</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getLogin")
    public Login getLogin(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getLogin method");
        return null;
    }

    /**
     * This inserts a new <code>Login</code> object.
     *
     * @param login The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertLogin")
    public Login insertLogin(Login login) {
        // TODO: Implement this function
        logger.info("Calling insertLogin method");
        return login;
    }

}

