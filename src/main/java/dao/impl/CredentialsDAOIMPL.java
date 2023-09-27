package dao.impl;

import dao.CredentialsDAO;
import model.Credential;

public class CredentialsDAOIMPL implements CredentialsDAO {
    @Override
    public Credential getCredential() {
        Credential credential = new Credential("Root", "dam2");
        return credential;
    }
}
