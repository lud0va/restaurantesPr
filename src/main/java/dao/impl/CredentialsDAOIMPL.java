package dao.impl;

import dao.CredentialsDAO;
import model.Credential;

public class CredentialsDAOIMPL implements CredentialsDAO {
    @Override
    public Credential getCredential() {
        return new Credential("Root", "dam2");
    }
}
